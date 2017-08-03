package com.game.save.editor.difficulty;

/**
 * Abstract DifficultyFactory
 * 
 * @author Kyle Williamson
 *
 */
public abstract class DifficultyFactory {

	/**
	 * Factory method that creates relevant Difficulty object.
	 * 
	 * @return Difficulty
	 */
	public abstract Difficulty getDifficulty();
}
