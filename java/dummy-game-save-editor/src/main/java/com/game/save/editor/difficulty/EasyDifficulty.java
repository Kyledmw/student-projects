package com.game.save.editor.difficulty;

import com.game.save.editor.difficulty.DifficultyManager.DifficultyType;

public class EasyDifficulty extends Difficulty {

	private static final int ALIEN_VELOCITY = 20;
	private static final int ALIEN_MOVEMENT_PER_TURN = 4;
	private static final double SHOT_VELOCITY = -400;
	private static final double SHIP_MOVE_SPEED = 400;
	private static final long FIRING_INTERVAL = 400;
	private static final double ALIEN_SPEED_INCREASE = 1.01;
	private static final int HORIZONTAL_ALIEN_AMOUNT = 10;
	private static final int VERTICAL_ALIEN_AMOUNT = 4;
	
	public EasyDifficulty() {
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
		return DifficultyType.EASY;
	}

}
