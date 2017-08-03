package com.space_invaders.entity.controllers;

import java.awt.Rectangle;

import com.space_invaders.Game;
import com.space_invaders.entity.models.Entity;
import com.space_invaders.resources.Sprite;
public abstract class AEntityController<E extends Entity> {
	
	protected Game game;
	
	public final static String ALIEN = "alien";
	public final static String SHIP = "ship";
	public final static String SHOT = "shot";
	
	
	public AEntityController(Game game) {
		this.game = game;
	}
	
	public abstract void moveEntity(E e, long delta);
	
	public abstract void doLogic(E e);
	
	public abstract void collide(E e, Entity e2);
	
	public boolean collidesWith(Entity e, Entity e2) {
		Rectangle eCollision = new Rectangle();
		Rectangle e2Collision = new Rectangle();
		
		Sprite eSprite = e.getSprite();
		Sprite e2Sprite = e2.getSprite();
		
		eCollision.setBounds(e.getXPosition(), e.getYPosition(), eSprite.getWidth(), eSprite.getHeight());
		e2Collision.setBounds(e2.getXPosition(), e2.getYPosition(), e2Sprite.getWidth(), e2Sprite.getHeight());
		return eCollision.intersects(e2Collision);
	}


}
