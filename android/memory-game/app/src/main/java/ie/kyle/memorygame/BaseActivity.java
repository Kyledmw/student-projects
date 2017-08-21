package ie.kyle.memorygame;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 ********************************************************************
 * Abstract class that provides common OptionsMenu and Intent implementations
 *
 * @extends {@link android.support.v7.app.AppCompatActivity}
 *
 *
 * @author Kyle Williamson <a href="http://kyle.ie/">kyle.ie</a>
 * @version 1.0.0
 * <a href="https://github.com/Kyledmw">Github</a>
 ********************************************************************
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.

        switch(item.getItemId()) {
            case(R.id.action_home):
                gotoHome();
                return true;
            case(R.id.action_score):
                gotoScoreboard();
                return true;
            case(R.id.action_newgame):
                gotoNewGame();
                return true;
            case(R.id.action_settings):
                gotoSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Goto the home activity {@link HomeScreenActivity}
     */
    protected void gotoHome() {
        Intent homeView = new Intent(BaseActivity.this, HomeScreenActivity.class);
        startActivity(homeView);
    }

    /**
     * Goto the Scoreboard activity {@link ScoreboardActivity}
     */
    protected void gotoScoreboard() {
        Intent scoreboardView = new Intent(BaseActivity.this, ScoreboardActivity.class);
        startActivity(scoreboardView);
    }

    /**
     * Goto the GameLevel activity at the initial level {@link GameLevelActivity}
     */
    protected void gotoNewGame() {
        Intent gameView = new Intent(BaseActivity.this, GameLevelActivity.class);
        gameView.putExtra(IGameLevelConstants.INTENT_TYPE, IGameLevelConstants.NEW_GAME);
        startActivity(gameView);
    }

    /**
     * Goto the Settings activity {@link SettingsActivity}
     */
    protected void gotoSettings() {
        Intent settingsView = new Intent(BaseActivity.this, SettingsActivity.class);
        startActivity(settingsView);
    }
}
