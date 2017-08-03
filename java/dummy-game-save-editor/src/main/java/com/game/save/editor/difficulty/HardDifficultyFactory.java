package com.game.save.editor.difficulty;

public class HardDifficultyFactory extends DifficultyFactory {

	@Override
	public Difficulty getDifficulty() {
		return new HardDifficulty();
	}

}
