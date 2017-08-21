package ie.kyle.memorygame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Observable;
import java.util.Observer;

import ie.kyle.alert_dialog_factories.GenericDialogFactory;
import ie.kyle.alert_dialog_factories.IAlertDialogFactory;
import ie.kyle.audio.GameAudioPlayer;
import ie.kyle.data.ISettingsPreferencesConstants;
import ie.kyle.game_logic.IGameConstants;
import ie.kyle.game_logic.MemoryGame;
import ie.kyle.timer.TimeProgressBar;
import ie.kyle.timer.TimeSelectionDialog;
import ie.kyle.ui.GameRectangle;
import ie.kyle.ui.GameView;


/**
 ********************************************************************
 * This activity class is responsible for controlling the <i>activity_game_level.xml</i>
 *
 * Activity for the actual memory game
 *
 * Observes {@link MemoryGame}
 *
 * @implements {@link android.gesture.GestureOverlayView.OnGesturePerformedListener}, {@link Observer}
 *
 * @extends {@link BaseActivity}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class GameLevelActivity extends BaseActivity implements GestureOverlayView.OnGesturePerformedListener, Observer {

    private IAlertDialogFactory _dialogFactory;
    private GestureLibrary gameGestures;
    private GameAudioPlayer _audioPlayer;

    private static MemoryGame _game;

    private TimeProgressBar _countdownBar;

    private GameView _gameView;
    private TextView _scoreView;
    private TextView _levelView;
    private Button _startBtn;
    private GestureOverlayView _gestureOverlay;

    private boolean _dialogActive;
    private int _curMovementIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_level);
        //check intent, see if new game or next level
        Intent i = getIntent();
        String type = i.getStringExtra(IGameLevelConstants.INTENT_TYPE);

        if (type.equals(IGameLevelConstants.NEW_GAME)) {
            _game = new MemoryGame();
            timeSelectionDialog();
        }

        _audioPlayer = GameAudioPlayer.getInstance(this);
        SharedPreferences pref = getSharedPreferences(ISettingsPreferencesConstants.PREFERENCES_KEY, MODE_PRIVATE);
        boolean musicFlag = pref.getBoolean(ISettingsPreferencesConstants.MUSIC_FLAG, true);
        _audioPlayer.playAudio(musicFlag);

        _game.addObserver(this);
        _game.newLevel();
        _audioPlayer.startBGMusic();

        getViewComponents();

        _dialogActive = false;
        _curMovementIndex = 0;

        _levelView.setText("" + _game.getCurrentLevel());
        _scoreView.setText("" + _game.getScore());
        _gameView.setGame(_game);
        _gameView.setClickable(false);
        showCorrectMovements();

        _dialogFactory = new GenericDialogFactory();
        gameGestures = GestureLibraries.fromRawResource(this, R.raw.gestures);
        _gestureOverlay.addOnGesturePerformedListener(this);

    }

    @Override
    public void onBackPressed() {
        _audioPlayer.stop();
        gotoHome();
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        if(_game.isPaused()) {
            _game.setPaused(false);
        } else {
            _game.setPaused(true);
        }
    }

    @Override
    public void update(Observable observable, final Object data) {
        GameLevelActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (data.equals(IGameConstants.GAME_SUCCESS)) {
                    _audioPlayer.playSuccess();
                    successDialog();
                } else if (data.equals(IGameConstants.LEVEL)) {
                    _audioPlayer.playLevelComplete();
                    levelCompleteDialog();
                } else if (data.equals(IGameConstants.GAME_OVER)) {
                    if (!_dialogActive) {
                        _audioPlayer.playGameOver();
                        gameOverDialog();
                    }
                } else if (data.equals(IGameConstants.SCORE)) {
                    _scoreView.setText("" + _game.getScore());
                }
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        _audioPlayer.stop();
        return super.onOptionsItemSelected(item);
    }

    /**
     * Assign the view components to instance variables
     */
    private void getViewComponents() {
        _gameView = (GameView) findViewById(R.id.game_cv_gameview);
        _levelView = (TextView) findViewById(R.id.game_tv_curlevel);
        _scoreView = (TextView) findViewById(R.id.game_tv_curscore);
        _startBtn = (Button) findViewById(R.id.game_btn_start);
        _gestureOverlay = (GestureOverlayView) findViewById(R.id.game_gov_gestures);

    }

    /**
     * Method that setups up the listener for the user to view the correct movements
     */
    private void showCorrectMovements() {

        _startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (_curMovementIndex < _game.getCorrectMovements().size()) {
                    if(_curMovementIndex == _game.getCorrectMovements().size() - 1) {
                        _startBtn.setText(getString(R.string.start));
                    } else {
                        _startBtn.setText(getString(R.string.next));
                    }
                    _gameView.getGameRects().get(_game.getCorrectMovements().get(_curMovementIndex)).hide(true);
                    _gameView.invalidate();
                    _curMovementIndex++;
                } else {
                    for (GameRectangle rect : _gameView.getGameRects()) {
                        rect.hide(false);
                    }
                    _gameView.invalidate();
                    start();
                }
            }
        });

    }

    /**
     * Method that sets up the view for a new game, calls start method on the MemoryGame
     */
    private void start() {
        _countdownBar = new TimeProgressBar((ProgressBar) findViewById(R.id.game_pb_countdown), _game);
        _gameView.setClickable(true);
        _startBtn.setEnabled(false);
        _game.startGame();
        _countdownBar.startTimer();
    }

    /**
     * Method that setups the intent to go to the {@link GameEndActivity}
     * @param type way in which game ended, level finished, game over, success
     */
    private void gotoGameEnd(String type) {
        Intent gameEndView = new Intent(GameLevelActivity.this, GameEndActivity.class);
        gameEndView.putExtra(IGameConstants.END_TYPE, type);
        gameEndView.putExtra(IGameConstants.LEVEL, _game.getCurrentLevel());
        gameEndView.putExtra(IGameConstants.SCORE, _game.getScore());
        startActivity(gameEndView);
    }

    /**
     * Sets up the intent to go to the next level of the game
     */
    private void nextLevel() {
        Intent nextLevel = new Intent(GameLevelActivity.this, GameLevelActivity.class);
        nextLevel.putExtra(IGameLevelConstants.INTENT_TYPE, IGameLevelConstants.NEXT_LEVEl);
        startActivity(nextLevel);
    }

    /**
     * Sets up and displays the game over dialog
     */
    private void gameOverDialog() {
        AlertDialog dialog = _dialogFactory.createDialog(this, R.string.lost, R.string.game_over);

        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                gotoGameEnd(IGameConstants.GAME_OVER);
            }
        });

        dialog.setCancelable(false);
        _dialogActive = true;
        dialog.show();
    }

    /**
     * Sets up and displays the success dialog
     */
    private void successDialog() {
        AlertDialog dialog = _dialogFactory.createDialog(this, R.string.completed, R.string.success);

        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, getString(R.string.ok), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                gotoGameEnd(IGameConstants.GAME_SUCCESS);
            }
        });

        dialog.setCancelable(false);
        _dialogActive = true;
        dialog.show();
    }

    /**
     * Creates and displays the time selection dialog
     */
    private void timeSelectionDialog() {
        final TimeSelectionDialog timeSelectDialog = new TimeSelectionDialog(this, _game);
        timeSelectDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _game.setTime(timeSelectDialog.getSelectedTime());
                timeSelectDialog.dismiss();
            }
        });
        timeSelectDialog.show();
    }

    /**
     * Sets up and displays the level complete dialog
     */
    private void levelCompleteDialog() {
        AlertDialog dialog = _dialogFactory.createDialog(this, R.string.level_complete_long, R.string.level_complete);

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.next_level), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                nextLevel();
            }
        });


        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.finish), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                gotoGameEnd(IGameConstants.GAME_OVER);
            }
        });

        dialog.setCancelable(false);
        _dialogActive = true;
        dialog.show();
    }
}