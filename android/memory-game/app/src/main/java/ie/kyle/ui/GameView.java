package ie.kyle.ui;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ie.kyle.audio.GameAudioPlayer;
import ie.kyle.common.RandomNumberGenerator;
import ie.kyle.game_logic.MemoryGame;

/**
 ********************************************************************
 * Main view for the Memory Game
 *
 * Contains logic to create the view and listen to touch events
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class GameView extends View {
    private final static int BG_COLOR = Color.WHITE;
    private final static Integer[] COLORS  = {Color.BLUE, Color.RED, Color.CYAN, Color.GREEN,
                                            Color.YELLOW, Color.GRAY, Color.LTGRAY, Color.BLACK,
                                            Color.DKGRAY, Color.MAGENTA, Color.rgb(153, 102, 255), Color.rgb(102, 204, 255),
                                            Color.rgb(153, 255, 102), Color.rgb(153, 153, 102), Color.rgb(51, 102, 0), Color.rgb(51, 51, 153)};

    private final static int RECTS_PER_ROW = 4;

    private ArrayList<GameRectangle> _gameRects;
    private boolean _clickable;
    private int _viewWidth;

    private MemoryGame _game;
    private GameAudioPlayer _audioPlayer;

    public GameView(Context context) {
        super(context);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _audioPlayer = GameAudioPlayer.getInstance(context);
        setFocusable(true);
        setClickable(true);
        setBackgroundColor(BG_COLOR);
        _gameRects = new ArrayList<GameRectangle>();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        _viewWidth = canvas.getWidth();
        Paint paint = new Paint();

            for(GameRectangle rect: _gameRects) {
                if(rect.isHidden()) {
                    paint.setColor(BG_COLOR);
                } else {
                    paint.setColor(rect.getColor());
                }
                canvas.drawRect(rect.getRectangle(), paint);
            }

    }

    @Override
     public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            int count = 0;
            if (_game != null) {
                for (GameRectangle rect : _gameRects) {
                    if (rect.contains(touchX, touchY) && _clickable && !_game.isPaused()) {
                        if (count == _game.nextMove()) {
                            rect.hide(true);
                            invalidate();
                            _game.correctGuess();
                        } else {
                           _game.incorrectGuess();
                        }
                    }
                    count++;
                }
            }
        }
        return true;
    }

    @Override
     protected void onSizeChanged(int xNew, int yNew, int xOld, int yOld){
        super.onSizeChanged(xNew, yNew, xOld, yOld);

        _viewWidth = xNew;
        createView();
    }


    /**
     * Create the view based off the current level
     */
    private void createView() {
        List<Integer> colors = new ArrayList<Integer>(Arrays.asList(COLORS));
        int size = _viewWidth / RECTS_PER_ROW;

        int left = 0;
        int top = 0;

        int right = size;
        int bottom = size;

        int newRowCount = 0;

        int amountToCreate = _game.getCurrentLevel() + 1;
        for(int i = 0; i< amountToCreate ; i++) {
            if(newRowCount == RECTS_PER_ROW) {
                top = bottom;
                bottom += size;
                left = 0;
                right = size;
                newRowCount = 0;
            }

            RandomNumberGenerator rng = new RandomNumberGenerator(0, colors.size()- 1);
            int colorIndex = rng.generate();
            _gameRects.add(new GameRectangle(left, top, right, bottom, colors.get(colorIndex)));
            colors.remove(colorIndex);
            left = right;
            right += size;
            newRowCount++;
        }
        invalidate();
    }

    /**
     * Retrieve a list of the {@link GameRectangle} objects
     * @return List of GameRectangles
     */
    public ArrayList<GameRectangle> getGameRects() {
        return _gameRects;
    }

    /**
     * Make the view into a clickable/unclickable state
     * @param clickable
     */
    public void setClickable(boolean clickable) {
        _clickable = clickable;
    }

    /**
     * Assign the {@link MemoryGame} object to this view
     * @param memGame
     */
    public void setGame(MemoryGame memGame) {
        _game = memGame;
    }

}
