package com.game.save.editor.user_data;
import com.game.save.editor.difficulty.DifficultyManager.DifficultyType;

/**
 * Model reader interface
 * This interface only exposes the get methods
 * 
 * @author Kyle Williamson
 *
 */
public interface IUserDataModelReader {

	public String getUsername();
	
	public int getScore();
	
	public DifficultyType getDifficulty();
}
