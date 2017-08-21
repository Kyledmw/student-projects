package ie.kyle.timer;

/**
 ********************************************************************
 * Producer thread that counts down for the given time
 *
 * @extends {@link Thread}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class CountDownThread extends Thread {

    private static final int ONE_SECOND = 1000;

    private ITimable _timableObj;
    private int _time;
    private boolean _paused;
    private boolean _end;

    public CountDownThread(ITimable timableObj, int time) {
        _timableObj = timableObj;
        _time = time;
        _paused = false;
        _end = false;
    }

    @Override
    public void run() {
        int count = 1;
        ProducerLoop:while(count <= _time) {
            if(!_paused) {
                try {
                    Thread.sleep(ONE_SECOND);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                _timableObj.setTimeElapsed(count);
                count++;
            }
            if(_end) {
                break ProducerLoop;
            }
        }
        _timableObj.finished();
    }

    /**
     * Inform the thread to stop executing
     */
    public void end() {
        _end = true;
    }

    /**
     * Pause/unpause the thread
     * @param paused flag determining if the thread will be paused or not
     */
    public void setPaused(boolean paused) {
        _paused = paused;
    }
}
