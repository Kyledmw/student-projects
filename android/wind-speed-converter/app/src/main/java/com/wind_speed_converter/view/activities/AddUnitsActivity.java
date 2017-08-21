package com.wind_speed_converter.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.wind_speed_converter.R;
import com.wind_speed_converter.view.constants.IActivityConstants;

/**
 ********************************************************************
 * This activity class is responsible for controlling the <i>activity_add_units.xml</i>
 *
 * @extends {@link android.support.v7.app.AppCompatActivity}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class AddUnitsActivity extends AppCompatActivity {

    private EditText _metricTxtField;
    private EditText _knotTxtField;
    private EditText _beuaTxtField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_units);

        _metricTxtField = (EditText) findViewById(R.id.add_tf_metric);
        _knotTxtField = (EditText) findViewById(R.id.add_tf_knots);
        _beuaTxtField = (EditText) findViewById(R.id.add_tf_beaufort);

        initViewListeners();
    }

    /**
     * This method is responsible for creating listeners for the view components
     * This is called when the activity is created
     */
    private void initViewListeners() {
        Button calcBtn = (Button) findViewById(R.id.add_btn_calc);

        calcBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String metricInput = _metricTxtField.getText().toString();
                String knotInput = _knotTxtField.getText().toString();
                String beuaInput = _beuaTxtField.getText().toString();

                //If any fields are empty, initialise them to 0
                double metric = (metricInput.equals("")) ? 0 : Double.parseDouble(metricInput);
                double knot = (knotInput.equals("")) ? 0 : Double.parseDouble(knotInput);
                double beua = (beuaInput.equals("")) ? 0 : Double.parseDouble(beuaInput);

                //Pass data back to main activity for calculation and display
                Intent i = getIntent();
                i.putExtra(IActivityConstants.METRIC_KEY, metric);
                i.putExtra(IActivityConstants.KNOT_KEY, knot);
                i.putExtra(IActivityConstants.BEUA_KEY, beua);
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }
}
