package com.appl_maint_mngt.common.views;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.appl_maint_mngt.R;
import com.appl_maint_mngt.account.models.constants.UserType;
import com.appl_maint_mngt.account.models.interfaces.IAccountReadable;
import com.appl_maint_mngt.common.integration.IntegrationController;
import com.appl_maint_mngt.property_appliance.models.interfaces.IPropertyApplianceReadable;
import com.appl_maint_mngt.property_appliance.views.constants.IPropertyApplianceViewConstants;
import com.appl_maint_mngt.property_appliance.views.utility.PropertyApplianceIntentBuilder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Kyle on 08/04/2017.
 */

public abstract class ANFCActivity extends ACommonActivity {

    private NfcAdapter nfcAdapter;
    private PendingIntent pendingIntent;
    private IntentFilter[] writeTagFilters;

    private boolean writeMode;
    private Tag tag;

    private boolean canRead;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        nfcAdapter = NfcAdapter.getDefaultAdapter(this);

        readFromIntent(getIntent());
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        writeTagFilters = new IntentFilter[] {tagDetected};
        this.canRead = true;
    }

    private void readFromIntent(Intent intent) {
        if(!canRead) return;
        String action = intent.getAction();
        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action) || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            NdefMessage[] msgs = null;
            if(rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for (int i = 0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            }
            toPropertyAppliance(msgs);
        }
    }

    private void toPropertyAppliance(NdefMessage[] msgs) {
        if (msgs == null || msgs.length == 0) return;
        byte[] payload = msgs[0].getRecords()[0].getPayload();
        String textEncoding;
        if ((payload[0] & 128) == 0) textEncoding = "UTF-8";
        else textEncoding = "UTF-16";
        int languageCodeLength = payload[0] & 0063;

        Long propApplId = null;
        try {
            String text = new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);
            propApplId = Long.parseLong(text);
        } catch(UnsupportedEncodingException e) {
            return;
        } catch(NumberFormatException ne) {
            return;
        }


        IPropertyApplianceReadable propAppl = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getPropertyApplianceRepository().getForId(propApplId);
        if(propAppl == null) {
            Toast.makeText(this, R.string.nfc_err_no_permission_or_not_loaded, Toast.LENGTH_LONG).show();
            return;
        }
        toPropertyApplianceView(propApplId);
    }

    private void toPropertyApplianceView(Long id) {
        Intent propertyApplianceIntent = new PropertyApplianceIntentBuilder().build(this, id);
        propertyApplianceIntent.putExtra(IPropertyApplianceViewConstants.UPDATE_KEY, true);
        startActivity(propertyApplianceIntent);
    }

    @Override
    public void onPause(){
        super.onPause();
        if(isNFCEnabled()) WriteModeOff();
    }

    @Override
    public void onResume(){
        super.onResume();
        if(isNFCEnabled()) WriteModeOn();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        readFromIntent(intent);
        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
        }
    }

    private void WriteModeOn(){
        writeMode = true;
        nfcAdapter.enableForegroundDispatch(this, pendingIntent, writeTagFilters, null);
    }

    private void WriteModeOff(){
        writeMode = false;
        nfcAdapter.disableForegroundDispatch(this);
    }

    protected void writeToTag(String text) throws IOException, FormatException, NullPointerException {
        IAccountReadable account = IntegrationController.getInstance().getRepositoryController().getReadableRepositoryRetriever().getAccountRepository().get();
        if(!account.getUserType().equals(UserType.PROPERTY_MANAGER)) return;
        NdefRecord[] records = { createRecord(text) };
        NdefMessage message = new NdefMessage(records);
        Ndef ndef = Ndef.get(tag);
        ndef.connect();
        ndef.writeNdefMessage(message);
        ndef.close();
    }

    private NdefRecord createRecord(String text) throws UnsupportedEncodingException {
        String lang       = "en";
        byte[] textBytes  = text.getBytes();
        byte[] langBytes  = lang.getBytes("US-ASCII");
        int    langLength = langBytes.length;
        int    textLength = textBytes.length;
        byte[] payload    = new byte[1 + langLength + textLength];

        payload[0] = (byte) langLength;

        System.arraycopy(langBytes, 0, payload, 1,              langLength);
        System.arraycopy(textBytes, 0, payload, 1 + langLength, textLength);

        NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,  NdefRecord.RTD_TEXT,  new byte[0], payload);

        return recordNFC;
    }

    private boolean isNFCEnabled() {
        return nfcAdapter != null;
    }

    protected void enableNFCRead() {
        canRead = true;
    }

    protected void disableNFCRead() {
        canRead = false;
    }
}
