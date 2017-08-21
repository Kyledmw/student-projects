package ie.kyle.game_logic;

import java.util.LinkedList;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ie.kyle.common.RandomNumberGenerator;
import ie.kyle.timer.CountDownThread;
import ie.kyle.timer.ITimable;

/**
 ********************************************************************
 * Class that contains the main logic for the memory game
 *
 * @extends {@link Observable}
 *
 * @implements {@link ITimable}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class MemoryGame extends Observable implements ITimable {

    private static final int THREAD_AMOUNT = 2;

    private static final int MAX_LEVEL = 15;

    private static final int SCORE_PER_CLICK = 50;
    private static final int MIN_TIME = 10;
    private static final int MAX_TIME = 30;

    private RandomNumberGenerator _rng;
    private ExecutorService _threadExecutor;
    private CountDownThread _countDownThread;

    private int _currentLevel;
    private int _currentScore;
    private int _selectedTime;

    private LinkedList<Integer> _correctMovements;
    private boolean _paused;
    private boolean _finished;

    private int _curTimeElapsed;
    private boolean _timeElapsedAvailable;

    public MemoryGame() {
        _currentLevel = 0;
        _currentScore = 0;
    }

    /**
     * Shutdown previous {@link ExecutorService} if one exists
     *
     * Create a new {@link ExecutorService}
     */
    private void initExecutorService() {
        if(_threadExecutor != null) {
            _threadExecutor.shutdown();
        }
        _threadExecutor = Executors.newFixedThreadPool(THREAD_AMOUNT);
    }

    /**
     * Set object up for a new memory game level
     */
    public void newLevel() {
        _finished = false;
        _currentLevel++;
        _correctMovements = new LinkedList<Integer>();
        initExecutorService();
        calculateMovements();
    }

    /**
     * Retrieve the current level
     * @return current level the game is on
     */
    public int getCurrentLevel() {
        return _currentLevel;
    }

    /**
     * Retrieve the current integer score of the players game session
     * @return integer value of current score
     */
    public int getScore() {
        return _currentScore;
    }

    /**
     * Change the games pause flag off the given value
     * @param paused flag to pause/unpause the game
     */
    public void setPaused(boolean paused) {
        _paused = paused;
        _countDownThread.setPaused(_paused);
    }

    /**
     * Check if the game is paused or not
     * @return flag determining if the game is paused or not
     */
    public boolean isPaused() {
        return _paused;
    }

    /**
     * Retrieve the next correct move
     * @return index of the correct move
     */
    public int nextMove() {
        return _correctMovements.getFirst();
    }

    /**
     * Retrieve {@link LinkedList<Integer>} of the correct movements
     * @return list containing correct movements
     */
    public LinkedList<Integer> getCorrectMovements() {
        return _correctMovements;
    }

    /**
     * Notify the game that the user has made a correct guess
     * Notifies all observers of this object
     */
    public void correctGuess() {
        _correctMovements.removeFirst();
        _currentScore += SCORE_PER_CLICK;
        performNotification(IGameConstants.SCORE);

        if(_correctMovements.isEmpty() && _currentLevel == MAX_LEVEL) {
            performNotification(IGameConstants.GAME_SUCCESS);
            _countDownThread.end();
        } else if(_correctMovements.isEmpty()) {
            performNotification(IGameConstants.LEVEL);
            _countDownThread.end();
        }
    }

    /**
     * Notify the game that the user has guessed incorrectly
     * This will trigger a game over
     */
    public void incorrectGuess() {
        performNotification(IGameConstants.GAME_OVER);
        _countDownThread.end();
    }

    /**
     * Start the game session
     */
    public void startGame() {
        _countDownThread = new CountDownThread(this, _selectedTime);
        setPaused(false);
        startTimer();
    }

    /**
     * Creates a thread to generate the correct movements
     */
    private void calculateMovements() {
        _threadExecutor.execute(new Runnable() {
            @Override
            public void run() {
                int options = _currentLevel + 1; //There is always 1 additional colour
                _rng = new RandomNumberGenerator(0, options - 1);
                while (_correctMovements.size() < options) {
                    int movement = _rng.generate();
                    while (_correctMovements.contains(movement)) {
                        movement = _rng.generate();
                    }
                    _correctMovements.addLast(movement);
                }
            }
        });
    }

    /**
     * Start the thread that countdowns, acts as a producer thread
     */
    private void startTimer() {
        _timeElapsedAvailable = false;
        _threadExecutor.execute(_countDownThread);
    }

    /**
     * Perform notifications to all observers with a given argument
     * @param update
     *          Constant used to signify the type of update
     */
    private void performNotification(String update) {
        //Informs observers this object has changed
        setChanged();
        //This will notify all observers if the changed variable is true
        notifyObservers(update);
        //Reset the changed variable now that observers have been notified.
        clearChanged();
    }

    @Override
    public void setTime(int time) {
        _selectedTime = time;
    }

    @Override
    public int getTime() {
        return _selectedTime;
    }

    @Override
    public synchronized void setTimeElapsed(int time) {
        while(_timeElapsedAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Produced: " + time);
        _curTimeElapsed = time;
        _timeElapsedAvailable = true;
        notifyAll();
    }

    @Override
    public synchronized int getTimeElapsed() {
        while(!_timeElapsedAvailable) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Consumed: " + _curTimeElapsed);
        _timeElapsedAvailable = false;
        notifyAll();
        return _curTimeElapsed;
    }

    @Override
    public void finished() {
        _finished = true;
        performNotification(IGameConstants.GAME_OVER);
    }

    @Override
    public boolean isFinished() {
        return _finished;
    }

    @Override
    public int getMinTime() {
        return MIN_TIME;
    }

    @Override
    public int getMaxTime() {
        return MAX_TIME;
    }
}
