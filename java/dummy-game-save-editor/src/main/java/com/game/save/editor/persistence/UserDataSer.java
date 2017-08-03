package com.game.save.editor.persistence;

import java.io.Serializable;

import com.game.save.editor.difficulty.DifficultyManager.DifficultyType;
import com.game.save.editor.logging.Logger;
import com.game.save.editor.user_data.IUserDataModelReader;

/**
 * Serializable object version of UserDataModel
 * 
 * @author Kyle Williamson
 *
 */
class UserDataSer implements Serializable, IUserDataModelReader {
	
	private static final long serialVersionUID = 1L;
	
	private String _userName;
	private int _score;
	private DifficultyType _dif;
	
	public UserDataSer(String userName, int score, DifficultyType dif) {
		Logger.info("Serializable object created");
		_userName = userName;
		_score = score;
		_dif = dif;
	}
	
	public String getUsername() {
		return _userName;
	}
	
	public int getScore() {
		return _score;
	}
	
	public DifficultyType getDifficulty() {
		return _dif;
	}
}