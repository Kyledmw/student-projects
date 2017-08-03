package com.space_invaders.entity.controllers;

import com.space_invaders.Game;
import com.space_invaders.entity.models.AlienEntity;
import com.space_invaders.entity.models.Entity;
import com.space_invaders.entity.models.ShipEntity;
import com.space_invaders.gui.GameFrame;


public class ShipController extends AEntityController<ShipEntity> {

	public ShipController(Game game) {
		super(game);
	}

	@Override
	public void moveEntity(ShipEntity e, long delta) {
		GameFrame gui = game.getGUI();
		
		if(e.move(delta, gui.getScreenEndLeft(), gui.getScreenEndRight())) {
			doLogic(e);
		}
		
	}

	@Override
	public void doLogic(ShipEntity e) {
		return;
	}

	@Override
	public void collide(ShipEntity e, Entity e2) {
		if (e2 instanceof AlienEntity) {
			game.notifyDeath();
		}
	}
}
