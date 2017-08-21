package ie.kyle.audio;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;

import java.io.IOException;

import ie.kyle.memorygame.R;

/**
 ********************************************************************
 * A Singleton class that provides other objects access
 * to playing and pausing music playing within the app
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class GameAudioPlayer implements MediaPlayer.OnPreparedListener {

    private static final String RESOURCE_PATH = "android.resource://ie.kyle.memorygame/";

    private static GameAudioPlayer _instance;

    private Context _context;

    private MediaPlayer _audioPlayer;

    private boolean _playAudio;

    public GameAudioPlayer(Context context) {
        _context = context;
        _audioPlayer = new MediaPlayer();
        _audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        _audioPlayer.setOnPreparedListener(this);
        _playAudio = true;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    /**
     * Retrieve singleton instance of this class
     *
     * @param context
     *          context required to create the singleton if it has not been instantiated yet
     *
     * @return GameAudioPlayer
     *              Singleton instance of this class
     */
    public static GameAudioPlayer getInstance(Context context) {
        if(_instance == null) {
            _instance = new GameAudioPlayer(context);
        }
        return _instance;
    }

    /**
     * Set a flag if the app should play music or not
     *
     * @param play boolean determinign if audio should be played or not
     *
     */
    public void playAudio(boolean play) {
        _playAudio = play;
    }

    /**
     * Stops the audio player from playing music
     */
    public void stop() {
        _audioPlayer.stop();
    }

    /**
     * Start the background music for the memory game
     */
    public void startBGMusic() {
        Uri track = Uri.parse(RESOURCE_PATH + R.raw.game_bgmusic);
        genericPlay(true, track);
    }

    /**
     * Plays the sound associated with a game over
     */
    public void playGameOver() {
        Uri track = Uri.parse(RESOURCE_PATH + R.raw.game_over);
        genericPlay(false, track);
    }

    /**
     * Plays the sound associated when a level is completed
     */
    public void playLevelComplete() {
        Uri track = Uri.parse(RESOURCE_PATH + R.raw.game_levelcomplete);
        genericPlay(false, track);
    }

    /**
     * Play the sound associated when a player has completed all the levels
     */
    public void playSuccess() {
        Uri track = Uri.parse(RESOURCE_PATH + R.raw.game_success);
        genericPlay(false, track);
    }

    /**
     * Generic method used for setting up the MediaPlayer to play a track
     * @param looping Set weather we want the track to loop
     * @param track Uri to the track
     */
    private void genericPlay(boolean looping, Uri track) {
        if(_playAudio) {
            _audioPlayer.reset();
            _audioPlayer.setLooping(looping);
            try {
                _audioPlayer.setDataSource(_context, track);
                _audioPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
