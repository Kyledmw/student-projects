package com.space_invaders.entity.controllers;

import com.space_invaders.Game;
import com.space_invaders.entity.models.AlienEntity;
import com.space_invaders.entity.models.Entity;
import com.space_invaders.entity.models.ShotEntity;

public class ShotController extends AEntityController<ShotEntity> {

	public ShotController(Game game) {
		super(game);
	}

	@Override
	public void moveEntity(ShotEntity e, long delta) {
		e.move(delta);
		
	}

	@Override
	public void doLogic(ShotEntity e) {
		return;
	}

	@Override
	public void collide(ShotEntity e, Entity e2) {
		if(e.isUsed()) {
			return;
		}	
	
		if(e2 instanceof AlienEntity) {
			game.removeEntity(e);
			game.removeEntity(e2);
			
			game.notifyAlienKilled();
			e.setUsed(true);
		}
		
	}

}
