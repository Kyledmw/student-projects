package com.space_invaders.global.difficulty;

import com.space_invaders.global.difficulty.DifficultyManager.DifficultyType;

/**
 * Class that modals Difficulty
 * 
 * @author KyleWilliamson
 *
 */
public abstract class Difficulty {

	protected int alienVelocity;
	protected int alienMovementPerTurn;
	protected double shotVelocity;
	protected double shipMoveSpeed;
	protected long firingInterval;
	protected double alienSpeedIncrease;
	protected int horizontalAlienAmount;
	protected int verticalAlienAmount;
	
	public int getAlienVelocity() {
		return alienVelocity;
	}
	
	public int getAlienMovementPerTurn() {
		return alienMovementPerTurn;
	}
	
	public double getShotVelocity() {
		return shotVelocity;
	}
	
	public double getShipMoveSpeed() {
		return shipMoveSpeed;
	}
	
	public long getFiringInterval() {
		return firingInterval;
	}
	
	public double getAlienSpeedIncrease() {
		return alienSpeedIncrease;
	}
	
	public int getHorizontalAlienAmount() {
		return horizontalAlienAmount;
	}
	
	public int getVerticalAlienAmount() {
		return verticalAlienAmount;
	}
	
	public abstract DifficultyType getType();
	
	
}
