package com.game.save.editor.user_data;
import java.util.Observer;

import com.game.save.editor.difficulty.DifficultyManager.DifficultyType;

/**
 * Writer interface for the UserDataModel
 * This interface only exposes methods that change data in the model.
 * 
 * @author Kyle Williamson
 *
 */
public interface IUserDataModelWriter {

	public void setUsername(String username);
	
	public void setScore(int score);
	
	public void setDifficulty(DifficultyType difficulty);
	
	public void clearModel();
	
	/**
	 *  We expose this here to allow for the use of the observerable method through polymorphism
	 * @param observer
	 */
	public void addObserver(Observer observer);
}
