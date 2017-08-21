package ie.kyle.memorygame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import ie.kyle.data.IScore;
import ie.kyle.data.ScoreDAO;
import ie.kyle.game_logic.IGameConstants;
/**
 ********************************************************************
 * This activity class is responsible for controlling the <i>activity_game_end.xml</i>
 *
 * It is the screen players are brought to after they finish a game.
 *
 * @extends {@link BaseActivity}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class GameEndActivity extends BaseActivity {

    private TextView _gameOverTV;
    private TextView _levelTV;
    private TextView _scoreTV;
    private Button _saveScore;

    private int _level;
    private int _score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_end);

        _gameOverTV = (TextView) findViewById(R.id.gameend_tv_gameover);
        _levelTV = (TextView) findViewById(R.id.gameend_tv_curlevel);
        _scoreTV = (TextView) findViewById(R.id.gameend_tv_score);
        _saveScore = (Button) findViewById(R.id.gameend_btn_save);

        _saveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveScoreDialog();
            }
        });

        Intent i = getIntent();

        _level = i.getIntExtra(IGameConstants.LEVEL, 0);
        _score = i.getIntExtra(IGameConstants.SCORE, 0);

        String endType = i.getStringExtra(IGameConstants.END_TYPE);

        if(endType.equals(IGameConstants.GAME_OVER)) {
            _gameOverTV.setText(R.string.game_over);
        } else if(endType.equals(IGameConstants.GAME_SUCCESS)) {
            _gameOverTV.setText(R.string.completed);
        }

        _levelTV.setText("" + _level);
        _scoreTV.setText("" + _score);
    }

    @Override
    public void onBackPressed() {
        gotoNewGame();
    }

    /**
     * Create a dialog with a text field to allow users to save their score
     */
    private void saveScoreDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setTitle(R.string.save_score);
        builder.setMessage(R.string.enter_name);

        builder.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                saveScore(input.getText().toString());
                gotoScoreboard();
            }
        });

        builder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    /**
     * Save a score to the Score DAO
     * @param name name of player
     */
    private void saveScore(String name) {
        IScore score = ScoreDAO.getInstance(this).createScore(name, _score);
        ScoreDAO.getInstance(this).updateScore(score);
    }
}
