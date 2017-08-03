package com.game.save.editor.user_data;
import com.game.save.editor.constants.CommonConstants;
import com.game.save.editor.difficulty.DifficultyManager.DifficultyType;
import com.game.save.editor.logging.Logger;

/**
 * This class models User Save Data.
 * 
 * @author Kyle Williamson
 *
 */
public class UserDataModel extends AUserDataModel {

	private String _username;
	private int _score;
	private DifficultyType _difficulty;
	
	public UserDataModel() {
		clearModel();
	}
	
	public String getUsername() {
		return _username;
	}

	public int getScore() {
		return _score;
	}

	public DifficultyType getDifficulty() {
		return _difficulty;
	}

	public void setUsername(String username) {
		Logger.debug("Setting username to: " + username);
		_username = username;
		performNotification(IUserDataConstants.MODEL_CHANGE_PROPERTIES);
	}

	public void setScore(int score) {
		Logger.debug("Setting score to: " + score);
		_score = score;
		performNotification(IUserDataConstants.MODEL_CHANGE_PROPERTIES);
		
	}

	public void setDifficulty(DifficultyType difficulty) {
		_difficulty = difficulty;
		performNotification(IUserDataConstants.MODEL_CHANGE_PROPERTIES);
		
	}

	public void clearModel() {
		Logger.info("Clearing model.");
		_username = CommonConstants.EMPTY_STRING;
		_score = 0;
		_difficulty = DifficultyType.NORMAL;
		
		performNotification(IUserDataConstants.MODEL_CLEARED);
	}
	
	/**
	 * Worker method to notify observers
	 * 
	 * @param update
	 */
	private void performNotification(String update) {
		Logger.info("Performing notification to observers from model: " + update);
		//Informs observers this object has changed
		setChanged();
		//This will notify all observers if the changed variable is true
		notifyObservers(update);
		//Reset the changed variable now that observers have been notified.
		clearChanged();
	}
}
