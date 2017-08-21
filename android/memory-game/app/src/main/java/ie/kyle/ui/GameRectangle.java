package ie.kyle.ui;

import android.graphics.Rect;


/**
 ********************************************************************
 * Wrapper class for a {@link Rect} to be used for our game buttons
 *
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class GameRectangle {

    private Rect _rectangle;
    private int _color;
    private boolean _hidden;

    public GameRectangle(int left, int top, int right, int bottom, int color) {
        _rectangle = new Rect(left, top, right, bottom);
        _color = color;
        _hidden = false;
    }

    /**
     * Hide/Unhide the button based on the given flag
     * @param hide flag dtermining visibility
     */
    public void hide(boolean hide) {
        _hidden = hide;
    }

    /**
     * Check if the rectangle is currently hidden
     * @return flag
     */
    public boolean isHidden() {
        return _hidden;
    }

    /**
     * Retrieve the actual rectangle class
     * @return {@link Rect} object
     */
    public Rect getRectangle() {
        return _rectangle;
    }

    /**
     * Get hex value of the color of this rectangle
     * @return
     */
    public int getColor() {
        return _color;
    }

    /**
     * Check if the rectangle contains the given co-ordinate
     * @param x x position on the canvas
     * @param y y position on the canvas
     * @return boolean determining if it contains the co-ordinate
     */
    public boolean contains(int x, int y) {
        return _rectangle.contains(x, y);
    }
}
