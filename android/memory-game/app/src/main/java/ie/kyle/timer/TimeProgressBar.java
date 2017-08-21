package ie.kyle.timer;

import android.widget.ProgressBar;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 ********************************************************************
 * Wrapper class of a Progress bar  that tracks time
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class TimeProgressBar {

    private ProgressBar _progressBar;
    private ITimable _timableObj;
    private ExecutorService _threadExecutor;

    public TimeProgressBar(ProgressBar progressBar, ITimable timable) {
        _timableObj = timable;
        _progressBar = progressBar;
        _progressBar.setMax(_timableObj.getTime());
        _threadExecutor = Executors.newSingleThreadExecutor();
    }

    /**
     * Start timer, asks as a consumer thread for the timableObj
     */
    public void startTimer() {
        _threadExecutor.execute(new Runnable() {

            @Override
            public void run() {
                while(!_timableObj.isFinished()) {
                    int count = _timableObj.getTimeElapsed();
                    _progressBar.setProgress(count);
                    _progressBar.setSecondaryProgress((_timableObj.getTime() / _timableObj.getMinTime()) * count);
                }
            }
        });

        _threadExecutor.shutdown();
    }
}
