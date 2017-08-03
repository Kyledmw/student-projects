package com.space_invaders.global.difficulty;

public class DifficultyException extends RuntimeException {
	
	private static final long serialVersionUID = -362824093817866532L;

	public DifficultyException() {
		super("Difficulty not supported");
	}

}
