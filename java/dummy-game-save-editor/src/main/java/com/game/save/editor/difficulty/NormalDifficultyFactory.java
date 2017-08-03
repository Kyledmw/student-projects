package com.game.save.editor.difficulty;

public class NormalDifficultyFactory extends DifficultyFactory {

	@Override
	public Difficulty getDifficulty() {
		return new NormalDifficulty();
	}

}
