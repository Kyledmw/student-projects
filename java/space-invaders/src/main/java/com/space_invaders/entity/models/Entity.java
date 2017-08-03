package com.space_invaders.entity.models;

import java.awt.Graphics;

import com.space_invaders.resources.Sprite;
import com.space_invaders.resources.SpriteManager;

public abstract class Entity {
	
	protected final static int ADJUSTMENT_VALUE = 1000;
	
	protected double xPosition;
	protected double yPosition;
	
	protected double horizontalMovement;
	protected double verticalMovement;
	
	protected Sprite sprite;
	
	public Entity(String sprite, int xPosition, int yPosition) {
		this.sprite = SpriteManager.getInstance().getSprite(sprite);
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}
	
	public boolean move(long delta) {
		xPosition += (delta * horizontalMovement) / ADJUSTMENT_VALUE;
		yPosition += (delta * verticalMovement) / ADJUSTMENT_VALUE;
		return false;
	}	
	
	public void draw(Graphics g) {
		sprite.draw(g, (int) xPosition, (int) yPosition);
	}
	
	public void setHorizontalMovement(double horizontalMovement) {
		this.horizontalMovement = horizontalMovement;
	}
	
	public void setVerticalMovement(double verticalMovement) {
		this.verticalMovement = verticalMovement;
	}
	
	public double getHorizontalMovement() {
		return horizontalMovement;
	}
	
	public double getVerticalMovement() {
		return verticalMovement;
	}
	
	public int getXPosition() {
		return (int) xPosition;
	}
	
	public int getYPosition() {
		return (int) yPosition;
	}
	
	public void setYPosition(double yPosition) {
		this.yPosition = yPosition;
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public abstract String getType();
	
}
