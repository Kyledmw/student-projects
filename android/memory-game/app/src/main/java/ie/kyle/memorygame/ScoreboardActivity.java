package ie.kyle.memorygame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Collections;
import java.util.List;

import ie.kyle.alert_dialog_factories.GenericDialogFactory;
import ie.kyle.data.IScore;
import ie.kyle.data.IScoreDAO;
import ie.kyle.data.ScoreDAO;
import ie.kyle.nfc.NfcNotSupportedException;
import ie.kyle.nfc.ScoreBoardNfcHandler;
import ie.kyle.ui.ScoreListAdapter;

/**
 ********************************************************************
 * This activity class is responsible for controlling the <i>activity_scoreboard.xml</i>
 *
 * This activity displays all the saved scores to the user.
 * It also provides a p2p nfc implementation
 *
 * @extends {@link BaseActivity}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ScoreboardActivity extends BaseActivity {

    private final static int SCORE_DELETE_ID = 1;

    private IScoreDAO _scores;
    private List<IScore> _scoresList;

    private ScoreListAdapter _adapter;

    private ScoreBoardNfcHandler _nfcHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);
        _scores = ScoreDAO.getInstance(getApplicationContext());
        prepareScoreList();
        nfcSetUp();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle(getString(R.string.score_title));
        menu.add(0, SCORE_DELETE_ID, 0, getString(R.string.score_delete));
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if(item.getItemId() == SCORE_DELETE_ID) {
            scoreCleanup(_scoresList.get(info.position));
            return true;
        }
        return false;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        _nfcHandler.onNewIntent(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(_nfcHandler != null) {
            if(NfcAdapter.ACTION_NDEF_DISCOVERED.equals(getIntent().getAction())) {
                NdefMessage[] messages = _nfcHandler.getNdefMessages(getIntent());
                byte[] payload = messages[0].getRecords()[0].getPayload();
                System.out.println("OOF "  + new String(payload));
                setIntent(new Intent()); // Consume this intent.
            }
            _nfcHandler.enableNdefExchangeMode();
        }
    }

    /**
     * Deletes a score from the DAO, Adapter and the list within the activity
     * @param score IScore object to remove
     */
    private void scoreCleanup(IScore score) {
        _scoresList.remove(score);
        _adapter.remove(score);
        _scores.deleteScore(score);
    }

    /**
     * Retrieves the IScore objects from the DAO, sorts them and sets up the list adapter
     */
    private void prepareScoreList() {
        _scoresList = _scores.getAllScores();
        Collections.sort(_scoresList);
        _adapter = new ScoreListAdapter(this, _scoresList);

        ListView listView = (ListView) findViewById(R.id.score_lv_scorelist);
        listView.setAdapter(_adapter);

        registerForContextMenu(listView);
    }

    /**
     * This method is called from the {@link ScoreBoardNfcHandler} when data has been exchanged from another device running this app
     *
     * It creates an alert dialog allowing the player to accept the
     *
     * @param msg {@Link NdefMessage} message received from the other device
     */
    public void promptForContent(final NdefMessage msg) {
        AlertDialog dialog = new GenericDialogFactory().createDialog(this, R.string.add_score_full, R.string.add_score);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.yes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String score = new String(msg.getRecords()[0].getPayload());
                String name = new String(msg.getRecords()[1].getPayload());
                IScore retrievedScore = _scores.createScore(name, new Integer(score));
                _scores.updateScore(retrievedScore);
                _adapter.add(retrievedScore);
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.no), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });

        dialog.show();
    }

    /**
     * Setup the handler if the device supports nfc
     */
    private void nfcSetUp() {
        try {
            if(_scoresList.size() > 0) {
                _nfcHandler = new ScoreBoardNfcHandler(this, _scoresList.get(0));
            }
        } catch (NfcNotSupportedException e) {
            e.printStackTrace();
        }
    }

}
