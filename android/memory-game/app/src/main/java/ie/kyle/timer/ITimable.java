package ie.kyle.timer;

/**
 ********************************************************************
 * Generic interface of an object that is timable
 *
 * This allows it to be used within GUI elements that are determined by time
 * As well as threads using time
 *
 * This can act as a shared buffer between producer and consumer threads
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface ITimable {

    /**
     * Set the current time elapsed
     *
     * @param time current time elapsed
     */
    void setTimeElapsed(int time);

    /**
     * Retrieve the current time elapsed
     *
     * @return current time elapsed
     */
    int getTimeElapsed();

    /**
     * Notify the timable object that the timer is finished
     */
    void finished();

    /**
     * Check if the timer is finished
     *
     * @return flag determined by finished state
     */
    boolean isFinished();

    /**
     * Get minimum time available
     *
     * @return min time
     */
    int getMinTime();

    /**
     * Get maximum time available
     *
     * @return max time
     */
    int getMaxTime();

    /**
     * Set time of this timable object
     *
     * @param time time between min and max
     */
    void setTime(int time);

    /**
     * Retrieve set time of this object
     *
     * @return time between min and max
     */
    int getTime();
}
