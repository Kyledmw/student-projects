package com.space_invaders.entity.models;

public class ShotEntity extends Entity {
	
	private final String type = "shot";
	
	private final double VELOCITY = -300;
	
	private final double OFFSCREEN_CHECK = -100;
	
	private boolean used;

	public ShotEntity(String sprite, int xPosition, int yPosition) {
		super(sprite, xPosition, yPosition);
		
		used = false;
		
		verticalMovement = VELOCITY;
	}
	
	public boolean move(long delta) {
		super.move(delta);
		
		if(yPosition < OFFSCREEN_CHECK) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public String getType() {
		return type;
	}
	
	public boolean isUsed() {
		return used;
	}
	
	public void setUsed(boolean used) {
		this.used = used;
	}
}
