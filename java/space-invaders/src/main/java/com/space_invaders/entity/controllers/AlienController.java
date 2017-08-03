package com.space_invaders.entity.controllers;

import com.space_invaders.Game;
import com.space_invaders.entity.models.AlienEntity;
import com.space_invaders.entity.models.Entity;
import com.space_invaders.gui.GameFrame;

public class AlienController extends AEntityController<AlienEntity> {

	
	
	public AlienController(Game game) {
		super(game);
	}

	@Override
	public void moveEntity(AlienEntity e, long delta) {
		GameFrame gui = game.getGUI();
		
		if(e.move(delta, gui.getScreenEndLeft(), gui.getScreenEndRight())) {
			game.updateLogic();
		}

	}

	@Override
	public void doLogic(AlienEntity e) {
		System.out.println("here");
		GameFrame gui = game.getGUI();
		
		double horizontalMovement = e.getHorizontalMovement();
		
		e.setHorizontalMovement(-horizontalMovement);
		
		double yPosition = e.getYPosition();
		
		e.setYPosition(yPosition + e.getMovementPerTurn());
		
		if(e.getYPosition() > gui.getScreenEndBottom()) {
			game.notifyDeath();
		}

	}

	@Override
	public void collide(AlienEntity e, Entity e2) {
		return;
	}

}
