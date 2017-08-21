package com.wind_speed_converter.view.components;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.Map;

/**
 ********************************************************************
 * This view class is responsible for the creating and drawing of a custom BarChart component
 *
 * @extends {@link android.view.View}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class BarChartView extends View {

    private static final int BASE_LEFT = 20;

    private static final int BASE_WIDTH = 580;

    private static final int BASE_HEIGHT = 80;

    private static final int BASE_TOP = 850;

    private static final int BASE_RIGHT = BASE_LEFT + BASE_WIDTH;

    private static final int BASE_BOTTOM = BASE_TOP + BASE_HEIGHT;

    private static final int BAR_WIDTH = 150;

    private static final int BAR_SCALING = 2;

    private static final double DISTANCE_BETWEEN_BARS = 200;

    private static final float HEADING_TEXT_SIZE = 30f;

    private static final float VALUE_TEXT_SIZE = 25f;

    private Map<String, Double> _chartData;

    private Paint _paint;

    public BarChartView(Context context) {
        super(context);
        _paint = new Paint();
    }

    public BarChartView(Context context, AttributeSet attrs) {
        super(context, attrs);
        _paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawBase(canvas);
        drawBars(canvas);
    }

    /**
     * This method is responsible for setting the data to be displayed by the bar chart
     *
     * @param doubleMap
     *              A map of double objects. This is the data used for creating the bar chart.
     *              Strings are used as labels, the double is used to calculate the bar height.
     */
    public void setBarchartData(Map<String, Double> doubleMap) {
        _chartData = doubleMap;
    }

    /**
     * This method is responsible for drawing the base horizontal line of the bar chart.
     *
     * @param canvas
     *             A {@link android.graphics.Canvas} object responsible for drawing the components.
     */
    private void drawBase(Canvas canvas) {
        _paint.setColor(Color.RED);
        canvas.drawRect(BASE_LEFT, BASE_TOP, BASE_RIGHT, BASE_BOTTOM, _paint);
    }

    /**
     * This method is responsible for drawing the bars of the bar chart based off the _chartData
     *
     * @param canvas
     *             A {@link android.graphics.Canvas} object responsible for drawing the components.
     */
    private void drawBars(Canvas canvas) {

        //Check if chartData has been set
        if(_chartData != null) {

            int leftPosition = 0;

            for(Map.Entry<String, Double> entry : _chartData.entrySet()) {

                double val = entry.getValue();
                int scaledVal = (int) val * BAR_SCALING;

                //calculations for generating bars
                int barLeft = BASE_LEFT + leftPosition;
                int barTop = BASE_TOP - scaledVal;
                int barRight = barLeft + BAR_WIDTH;

                //draw bar
                _paint.setColor(Color.BLUE);
                canvas.drawRect(barLeft, barTop, barRight, BASE_TOP, _paint);

                //draw associated text
                _paint.setColor(Color.BLACK);
                _paint.setTextSize(HEADING_TEXT_SIZE);
                canvas.drawText(entry.getKey(), barLeft, BASE_BOTTOM + BASE_HEIGHT, _paint);

                _paint.setColor(Color.WHITE);
                _paint.setTextSize(VALUE_TEXT_SIZE);
                canvas.drawText("" + val, barLeft, BASE_TOP, _paint);

                //increase position for next bar chart to be drawn
                leftPosition += DISTANCE_BETWEEN_BARS;

            }
        }
    }
}
