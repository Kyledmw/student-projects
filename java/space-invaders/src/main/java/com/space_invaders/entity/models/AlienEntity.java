package com.space_invaders.entity.models;

import com.space_invaders.global.difficulty.Difficulty;
import com.space_invaders.global.difficulty.IDifficulty;

public class AlienEntity extends Entity implements IDifficulty {
	
	private final String type = "alien";
	private static int movementPerTurn;
	private static int velocity;

	public AlienEntity(String sprite, int xPosition, int yPosition) {
		super(sprite, xPosition, yPosition);
	}
	
	public boolean move(long delta, int screenEndLeft, int screenEndRight) {

		boolean logicRequired = false;
		
		if((horizontalMovement < 0) && (xPosition < screenEndLeft)) {
			logicRequired = true;
		}
		
		if((horizontalMovement > 0) && (xPosition > screenEndRight)) {
			logicRequired = true;
		}
		
		super.move(delta);
		
		return logicRequired;
	}
	
	public int getMovementPerTurn() {
		return movementPerTurn;
	}

	
	@Override
	public String getType() {
		return type;
	}

	public void applyDifficulty(Difficulty difficulty) {
		movementPerTurn = difficulty.getAlienMovementPerTurn();
		velocity = difficulty.getAlienVelocity();
		horizontalMovement = velocity;
	}
}
