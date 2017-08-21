package ie.kyle.timer;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ie.kyle.memorygame.BaseActivity;
import ie.kyle.memorygame.R;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 ********************************************************************
 * Wrapper class for a dialog that allows a user to select a time
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class TimeSelectionDialog {

    private Dialog _timeSelectionDialog;
    private SeekBar _timeSelectBar;
    private Button _selectBtn;

    private TextView _minView;
    private TextView _maxView;
    private TextView _selectedView;

    private ITimable _timableObj;

    public TimeSelectionDialog(BaseActivity context, ITimable timable) {
        _timableObj = timable;

        _timeSelectionDialog = new Dialog(context);
        _timeSelectionDialog.setTitle(R.string.select_time_title);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.time_select_dialog, (ViewGroup) context.findViewById(R.id.timesel_lyt));
        _timeSelectionDialog.setContentView(layout);

        _timeSelectBar = (SeekBar) layout.findViewById(R.id.timesel_sb_timesel);
        _minView = (TextView) layout.findViewById(R.id.timesel_tv_min);
        _maxView = (TextView) layout.findViewById(R.id.timesel_tv_max);
        _selectedView = (TextView) layout.findViewById(R.id.timesel_tv_selected);

        int max = timable.getMaxTime() - timable.getMinTime();
        _timeSelectBar.setMax(max);
        _selectBtn = (Button) layout.findViewById(R.id.timesel_btn_ok);
        _timeSelectionDialog.setCancelable(false);

        _timeSelectBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            private int _progress = 10;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                _progress = progress  + _timableObj.getMinTime();;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                _selectedView.setText("" + _progress);
            }
        });

        _minView.setText("" + timable.getMinTime());
        _maxView.setText("" + timable.getMaxTime());
        _selectedView.setText("" + timable.getMinTime());
    }

    /**
     * Display the dialog
     */
    public void show() {
        _timeSelectionDialog.show();
    }

    /**
     * Assign an onclick listener to the dialogs button
     *
     * @param listener {@link android.view.View.OnClickListener}
     */
    public void setOnClickListener(View.OnClickListener listener) {
        _selectBtn.setOnClickListener(listener);
    }

    /**
     * Get time the user selected
     *
     * @return selected time
     */
    public int getSelectedTime() {
        return _timeSelectBar.getProgress() + _timableObj.getMinTime();
    }

    /**
     * Dismiss the dialog
     */
    public void dismiss() {
        _timeSelectionDialog.dismiss();
    }
}
