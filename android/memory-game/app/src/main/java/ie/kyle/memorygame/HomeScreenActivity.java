package ie.kyle.memorygame;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 ********************************************************************
 * This activity class is responsible for controlling the <i>activity_home_screen.xml</i>
 *
 * This is also the entry point of the application
 * Uses the fling gesture to go between the Settings and Scoreboard activities
 *
 * @extends {@link BaseActivity}
 *
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class HomeScreenActivity extends BaseActivity {

    private static final int LARGE_MOVE = 60;

    private GestureDetector _gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        _gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {

            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {


                if (e1.getX() - e2.getX() > LARGE_MOVE) {
                    //Fling left
                    gotoSettings();
                    return true;
                } else if (e2.getX() - e1.getX() > LARGE_MOVE) {
                    //Fling right
                    gotoScoreboard();
                    return true;
                }

                return false;
            }
        });

        Button gameViewBtn = (Button) findViewById(R.id.home_btn_newgame);
        gameViewBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    gotoNewGame();
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return _gestureDetector.onTouchEvent(event);
    }

    @Override
    public void onBackPressed() {
        return;
    }

}