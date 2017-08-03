package com.game.save.editor.difficulty;


public class EasyDifficultyFactory extends DifficultyFactory {

	@Override
	public Difficulty getDifficulty() {
		return new EasyDifficulty();
	}

}
