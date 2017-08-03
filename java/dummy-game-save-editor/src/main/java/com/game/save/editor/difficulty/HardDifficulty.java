package com.game.save.editor.difficulty;

import com.game.save.editor.difficulty.DifficultyManager.DifficultyType;

public class HardDifficulty extends Difficulty {
	
	private static final int ALIEN_VELOCITY = 40;
	private static final int ALIEN_MOVEMENT_PER_TURN = 6;
	private static final double SHOT_VELOCITY = -400;
	private static final double SHIP_MOVE_SPEED = 500;
	private static final long FIRING_INTERVAL = 600;
	private static final double ALIEN_SPEED_INCREASE = 1.03;
	private final static int HORIZONTAL_ALIEN_AMOUNT = 15;
	private final static int VERTICAL_ALIEN_AMOUNT = 6;
	
	public HardDifficulty() {
		alienVelocity = ALIEN_VELOCITY;
		alienMovementPerTurn = ALIEN_MOVEMENT_PER_TURN;
		shotVelocity = SHOT_VELOCITY;
		shipMoveSpeed = SHIP_MOVE_SPEED;
		firingInterval = FIRING_INTERVAL;
		alienSpeedIncrease = ALIEN_SPEED_INCREASE;
		horizontalAlienAmount = HORIZONTAL_ALIEN_AMOUNT;
		verticalAlienAmount = VERTICAL_ALIEN_AMOUNT;
	}
	
	public DifficultyType getType() {
		return DifficultyType.HARD;
	}
}
