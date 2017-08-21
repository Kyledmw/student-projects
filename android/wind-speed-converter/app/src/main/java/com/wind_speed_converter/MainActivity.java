package com.wind_speed_converter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wind_speed_converter.conversion.constants.IWindConversionConstants;
import com.wind_speed_converter.conversion.controller.WindConversionController;
import com.wind_speed_converter.units.constants.IModelConstants;
import com.wind_speed_converter.units.controller.WindUnitsModelController;
import com.wind_speed_converter.units.model.IWindUnitsModelReader;
import com.wind_speed_converter.view.activities.AddUnitsActivity;
import com.wind_speed_converter.view.activities.GraphicActivity;
import com.wind_speed_converter.view.constants.IActivityConstants;
import com.wind_speed_converter.view.util.UnitSelectionFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 ********************************************************************
 * This activity class is responsible for controlling the <i>activity_wconverterverter.xml</i>
 *
 * This is also the entry point of the application
 *
 * @extends {@link android.support.v7.app.AppCompatActivity}
 *
 *
 * @author Kyle Williamson <a href="http://kyle.ie/">kyle.ie</a>
 * @version 1.0.0
 * <a href="https://github.com/Kyledmw">Github</a>
 ********************************************************************
 */
public class MainActivity extends AppCompatActivity implements Observer {

    private static final IWindConversionConstants.WindUnitType DEFAULT_INPUT_TYPE = IWindConversionConstants.WindUnitType.METRIC;

    private IWindUnitsModelReader _modelReader;
    private WindConversionController _conversionCtrl;

    private TextView _metricValTV;
    private TextView _knotValTV;
    private TextView _beauValTV;

    private EditText _input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Retrieve model reader and observe the model
        _modelReader = WindUnitsModelController.getInstance().getModelReader();
        _modelReader.addObserver(this);

        //retrieve references of component objects
        _metricValTV = (TextView) findViewById(R.id.main_tv_metricval);
        _knotValTV = (TextView) findViewById(R.id.main_tv_knotval);
        _beauValTV = (TextView) findViewById(R.id.main_tv_beaufortval);
        _input = (EditText) findViewById(R.id.main_tf_units);

        //initialise conversion controller and the input type
        _conversionCtrl = new WindConversionController(WindUnitsModelController.getInstance().getModelWriter());
        _conversionCtrl.setType(DEFAULT_INPUT_TYPE);


        TextView typeField = (TextView) findViewById(R.id.main_tv_curunit);
        typeField.setText(R.string.unit_metric);

        initViewListeners();
    }

    /**
     * This method is responsible for creating listeners for the view components
     * This is called when the activity is created
     */
    private void initViewListeners() {

        Button changeInputTypeBtn = (Button) findViewById(R.id.main_btn_inputtype);
        changeInputTypeBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                createUnitSelectDialog();
            }
        });

        Button calculateUnitsBtn = (Button) findViewById(R.id.main_btn_calc);
        calculateUnitsBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String input = _input.getText().toString();

                //if input is empty, initialise to 0
                double val = (input.equals("")) ? 0 : Double.parseDouble(input);

                _conversionCtrl.performConversion(val);
            }
        });


        Button toGraphicViewBtn = (Button) findViewById(R.id.main_btn_graphview);

        //Listener for launching the GraphicView Activity.
        toGraphicViewBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //create intent for going to the GraphicActivity
                Intent graphicView = new Intent(MainActivity.this, GraphicActivity.class);
                //pass the calculated values to the GraphicActivity to be displayed.
                graphicView.putExtra(IActivityConstants.METRIC_KEY, _modelReader.getMetricVal());
                graphicView.putExtra(IActivityConstants.KNOT_KEY, _modelReader.getKnotsVal());
                graphicView.putExtra(IActivityConstants.BEUA_KEY, _modelReader.getBeaufortVal());
                startActivity(graphicView);
            }
        });


        Button toAddUnitsViewBtn = (Button) findViewById(R.id.main_btn_addunits);
        toAddUnitsViewBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //create intent for going to the AddUnitsActivity
                Intent addUnitsView = new Intent(MainActivity.this, AddUnitsActivity.class);
                startActivityForResult(addUnitsView, IActivityConstants.ADD_UNIT_REQ_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Check request code to determine what result we are processing
        if(requestCode == IActivityConstants.ADD_UNIT_REQ_CODE && resultCode == RESULT_OK) {

            //Get data of different unit types, perform addition and conversion, display results
            Map<IWindConversionConstants.WindUnitType, Double> mapToAdd = new HashMap<IWindConversionConstants.WindUnitType, Double>();
            mapToAdd.put(IWindConversionConstants.WindUnitType.METRIC, data.getExtras().getDouble(IActivityConstants.METRIC_KEY));
            mapToAdd.put(IWindConversionConstants.WindUnitType.KNOT, data.getExtras().getDouble(IActivityConstants.KNOT_KEY));
            mapToAdd.put(IWindConversionConstants.WindUnitType.BEAUFORT, data.getExtras().getDouble(IActivityConstants.BEUA_KEY));
            double result = _conversionCtrl.performAddition(mapToAdd);

            _input.setText("" + result);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        //Save values displayed in the GUI on orientation change.
        savedInstanceState.putCharSequence(IActivityConstants.INPUT_KEY, _input.getText());
        savedInstanceState.putCharSequence(IActivityConstants.METRIC_KEY, _metricValTV.getText());
        savedInstanceState.putCharSequence(IActivityConstants.KNOT_KEY, _knotValTV.getText());
        savedInstanceState.putCharSequence(IActivityConstants.BEUA_KEY, _beauValTV.getText());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        //Re-assign values to their components
        _input.setText(savedInstanceState.getCharSequence(IActivityConstants.INPUT_KEY));
        _metricValTV.setText(savedInstanceState.getCharSequence(IActivityConstants.METRIC_KEY));
        _knotValTV.setText(savedInstanceState.getCharSequence(IActivityConstants.KNOT_KEY));
        _beauValTV.setText(savedInstanceState.getCharSequence(IActivityConstants.BEUA_KEY));
    }

    @Override
    public void update(Observable observable, Object data) {
        //If data in model has changed, update view
        if(data.equals(IModelConstants.MODEL_CHANGE_PROPERTIES)) {
            _metricValTV.setText("" + _modelReader.getMetricVal());
            _knotValTV.setText("" + _modelReader.getKnotsVal());
            _beauValTV.setText("" + _modelReader.getBeaufortVal());
        }
    }

    /**
     * This method creates an alert dialog that allows a user to select the input type
     * used by the application
     *
     */
    private void createUnitSelectDialog() {
        AlertDialog dialog = new UnitSelectionFactory().createDialog(this);

        dialog.setButton(DialogInterface.BUTTON_NEUTRAL, getString(R.string.unit_knots), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                _conversionCtrl.setType(IWindConversionConstants.WindUnitType.KNOT);
                TextView typeField = (TextView) findViewById(R.id.main_tv_curunit);
                typeField.setText(R.string.unit_knots);
            }
        });

        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, getString(R.string.unit_metric), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                _conversionCtrl.setType(IWindConversionConstants.WindUnitType.METRIC);
                TextView typeField = (TextView) findViewById(R.id.main_tv_curunit);
                typeField.setText(R.string.unit_metric);
            }
        });

        dialog.setButton(DialogInterface.BUTTON_POSITIVE, getString(R.string.unit_beaufort), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                _conversionCtrl.setType(IWindConversionConstants.WindUnitType.BEAUFORT);
                TextView typeField = (TextView) findViewById(R.id.main_tv_curunit);
                typeField.setText(R.string.unit_beaufort);
            }
        });

        dialog.show();
    }
}