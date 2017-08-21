package ie.kyle.memorygame;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import ie.kyle.audio.GameAudioPlayer;
import ie.kyle.data.ISettingsPreferencesConstants;


/**
 ********************************************************************
 * This activity class is responsible for controlling the <i>activity_settings.xml</i>
 *
 * This screen contains all the settings the user can change
 *
 * @extends {@link BaseActivity}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class SettingsActivity extends BaseActivity {

    private SharedPreferences _pref;

    private Button _submitBtn;
    private RadioGroup _radioGroup;

    private RadioButton _musicOn;
    private RadioButton _musicOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        _submitBtn = (Button) findViewById(R.id.settings_btn_submit);
        _radioGroup = (RadioGroup) findViewById(R.id.settings_rg_audio);
        _musicOn = (RadioButton) findViewById(R.id.settings_rb_musicon);
        _musicOff = (RadioButton) findViewById(R.id.settings_rb_musicoff);

        _pref = getSharedPreferences(ISettingsPreferencesConstants.PREFERENCES_KEY, MODE_PRIVATE);

        boolean musicFlag = _pref.getBoolean(ISettingsPreferencesConstants.MUSIC_FLAG, true);

        if(musicFlag) {
            _musicOn.toggle();
        } else {
            _musicOff.toggle();
        }

        _submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor prefEditor = _pref.edit();
                switch(_radioGroup.getCheckedRadioButtonId()) {
                    case R.id.settings_rb_musicon:
                        GameAudioPlayer.getInstance( SettingsActivity.this).playAudio(true);
                        prefEditor.putBoolean(ISettingsPreferencesConstants.MUSIC_FLAG, true);
                        break;
                    case R.id.settings_rb_musicoff:
                        GameAudioPlayer.getInstance( SettingsActivity.this).playAudio(false);
                        prefEditor.putBoolean(ISettingsPreferencesConstants.MUSIC_FLAG, false);
                        break;
                }
                prefEditor.commit();
                gotoHome();
            }
        });
    }
}
