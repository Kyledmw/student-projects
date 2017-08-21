package ie.kyle.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Parcelable;

import java.io.IOException;

import ie.kyle.data.IScore;
import ie.kyle.memorygame.ScoreboardActivity;

/**
 ********************************************************************
 * Class that handles the majority of nfc setup and tasks within this app
 *
 * Tightly coupled to {@link ScoreboardActivity}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ScoreBoardNfcHandler {

    private static final String TEXT_DATATYPE = "text/plain";

    private ScoreboardActivity _activity;
    private IScore _dataToSend;

    private NfcAdapter _nfcAdapter;
    private PendingIntent _pendingIntent;
    private IntentFilter[] _ndefExchangeFilters;

    /**
     * Initialises objects associated with working with nfc
     *
     * @param activity ScoreboardActivity to work with
     * @param dataToSend IScore object to send to other devices
     * @throws NfcNotSupportedException thrown if device does not support nfc
     */
    public ScoreBoardNfcHandler(ScoreboardActivity activity, IScore dataToSend) throws NfcNotSupportedException {
        _activity = activity;
        _dataToSend = dataToSend;
        _nfcAdapter = NfcAdapter.getDefaultAdapter(activity);
        if(_nfcAdapter == null) {
            throw new NfcNotSupportedException();
        }

        //Setting up a pending intent to be used to pick up the activity on the other device
        _pendingIntent = PendingIntent.getActivity(activity, 0, new Intent(activity, activity.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);

        IntentFilter ndefDetected = new IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED);
        try {
            ndefDetected.addDataType(TEXT_DATATYPE);
        } catch (IntentFilter.MalformedMimeTypeException e) {
            e.printStackTrace();
        }
        _ndefExchangeFilters = new IntentFilter[] {ndefDetected};
    }

    /**
     * Enable pushing and receiving NDEF messages.
     *
     * This should only be called in the activities onResume() method
     */
    public void enableNdefExchangeMode() {
        _nfcAdapter.enableForegroundNdefPush(_activity, getDataAsNdef());
        _nfcAdapter.enableForegroundDispatch(_activity, _pendingIntent, _ndefExchangeFilters, null);
    }

    /**
     * Build an {@link NdefMessage} object with the given {@link IScore} data to send
     *
     * @return built NdefMessage to send to other devices
     */
    private NdefMessage getDataAsNdef() {
        //Convert data to byte arraysd to be sent as MIME data type
        byte[] scoreBytes = Integer.toString(_dataToSend.getScore()).getBytes();
        byte[] nameBytes = _dataToSend.getName().getBytes();
        //Build the NdefMessage to send accross nfc
        NdefRecord scoreRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, TEXT_DATATYPE.getBytes(),
                new byte[] {}, scoreBytes);
        NdefRecord nameRecord = new NdefRecord(NdefRecord.TNF_MIME_MEDIA, TEXT_DATATYPE.getBytes(),
                new byte[] {}, nameBytes);
        return new NdefMessage(new NdefRecord[] {
                scoreRecord, nameRecord
        });
    }

    /**
     * Retrieve a {@link NdefMessage[]} from the given {@link Intent}
     * The intent should be an intent received from nfc communication with another ScoreBoardActivity
     *
     * @param intent Intent received from another device
     * @return array of NDefMessages from the intent
     */
    public NdefMessage[] getNdefMessages(Intent intent) {
        NdefMessage[] msgs = null;
        String action = intent.getAction();
        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            //If message received, get as NdefMessage array
            if(rawMsgs != null) {
                msgs = new NdefMessage[rawMsgs.length];
                for(int i=0; i < rawMsgs.length; i++) {
                    msgs[i] = (NdefMessage) rawMsgs[i];
                }
            } else {
                //Create empty Ndef Message
                byte[] empty = new byte[] {};
                NdefRecord record = new NdefRecord(NdefRecord.TNF_UNKNOWN, empty, empty, empty);
                NdefMessage msg = new NdefMessage(new NdefRecord[] {
                        record
                });
                msgs = new NdefMessage[] {
                        msg
                };
            }
        } else {
            //Unknown intent
        }
        return msgs;
    }

    /**
     * Provides nfc functionality required to be called on the activities onNewIntent() method
     *
     * If the intent is in exchange mode it will trigger a prompt to on the activity
     *
     * If the intent is in writing mode it will attempt to write data to the other devices nfc chip
     *
     * @param intent received intent
     */
    public void onNewIntent(Intent intent) {
        //Exchange Mode
        if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(intent.getAction())) {
            NdefMessage[] msgs = getNdefMessages(intent);
            _activity.promptForContent(msgs[0]);
        }

        //Writing mode
        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())) {
            Tag detectedTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            writeTag(getDataAsNdef(), detectedTag);
        }
    }

    /**
     * Write {@link NdefMessage} to other devices nfc tag
     *
     * @param message {@link NdefMessage} containing data to write
     * @param tag object representing other devices tag
     * @return boolean if successful or not
     */
    private boolean writeTag(NdefMessage message, Tag tag) {
        int size = message.toByteArray().length;

        try {
            Ndef ndef = Ndef.get(tag);
            if(ndef != null) {
                ndef.connect();
                if(!ndef.isWritable()) {
                    return false;
                }
                if(ndef.getMaxSize() < size) {
                    return false;
                }

                ndef.writeNdefMessage(message);
                return true;

            } else {
                NdefFormatable format = NdefFormatable.get(tag);
                if(format != null) {
                    format.connect();
                    format.format(message);
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FormatException e) {
            e.printStackTrace();
        }
        return false;
    }

}
