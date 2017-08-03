package com.space_invaders.entity.models;

public class ShipEntity extends Entity {
	
	private final String type = "ship";

	public ShipEntity(String ref, int xPosition, int yPosition) {
		super(ref, xPosition, yPosition);
	}
	
	public boolean move(long delta, int screenEndLeft, int screenEndRight) {
		
		if ((horizontalMovement < 0) && (xPosition < screenEndLeft)) {
			return false;
		}
		
		if((horizontalMovement > 0) && (xPosition > screenEndRight)) {
			return false;
		}
		
		return super.move(delta);
		
	}
	
	@Override
	public String getType() {
		return type;
	}

}
