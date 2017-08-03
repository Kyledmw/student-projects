package com.space_invaders;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.space_invaders.entity.controllers.AEntityController;
import com.space_invaders.entity.controllers.AlienController;
import com.space_invaders.entity.controllers.ShipController;
import com.space_invaders.entity.controllers.ShotController;
import com.space_invaders.entity.models.AlienEntity;
import com.space_invaders.entity.models.Entity;
import com.space_invaders.entity.models.ShipEntity;
import com.space_invaders.entity.models.ShotEntity;
import com.space_invaders.global.difficulty.Difficulty;
import com.space_invaders.global.difficulty.DifficultyManager;
import com.space_invaders.global.difficulty.DifficultyManager.DifficultyType;
import com.space_invaders.global.difficulty.IDifficulty;
import com.space_invaders.gui.GameFrame;
import com.space_invaders.gui.KeyInputHandler;

public class Game implements IDifficulty {
	
	private final static String LOST_MESSAGE = "Oh no! They got you, try again?";
	
	private final static String VICTORY_MESSAGE = "Well done! You Win!";
	
	private final static String ANY_KEY = "Press any key";
	
	private final static int DISTANCE_BETWEEN_ALIENS = 50;
	
	private final static int ALIEN_DISTANCE_FROM_TOP = 50;
	
	private final static int DISTANCE_BELOW_ALIEN = 30;
	
	private GameFrame gui;
	private KeyInputHandler keyInput;

	private  double moveSpeed;
	private long firingInterval;
	private double alienSpeedIncrease;
	private int horizontalAlienAmount;
	private int verticalAlienAmount;
	
	private boolean paused;
	private boolean gameRunning;
	private boolean logicRequiredThisLoop;
	
	private long lastFire;
	
	private Difficulty currentDifficulty;
	private List<IDifficulty> difficultyList;
	
	private HashMap<String, AEntityController> entityControllers;
	
	private Entity ship;
	private int alienCount;
	
	private List<Entity> entities;
	private List<Entity> removeList;
	
	private String message;
	
	public Game() {
		
		gui = new GameFrame();	
		keyInput = new KeyInputHandler(this); 
		gui.addKeyListener(keyInput);
	
		entities = new ArrayList<Entity>();
		removeList = new ArrayList<Entity>();
		
		initGameVars();
		initEntityControllers();
		initDifficulties();
		initEntities();
	}
	
	private void initEntityControllers() {
		entityControllers = new HashMap<String, AEntityController>();
		entityControllers.put(AEntityController.ALIEN, new AlienController(this)); 
		entityControllers.put(AEntityController.SHIP, new ShipController(this)); 
		entityControllers.put(AEntityController.SHOT, new ShotController(this)); 
	}
	
	private void initGameVars() {
		gameRunning = true;
		paused = false;
		message = "";
		lastFire = 0;
		
		currentDifficulty = DifficultyManager.getInstance().getDifficultyChoice(DifficultyType.NORMAL);
		logicRequiredThisLoop = false;
	}
	
	private void initDifficulties() {
		difficultyList = new ArrayList<IDifficulty>();
		difficultyList.add(this);
		updateDifficulty(currentDifficulty);
	}
	
	private void initEntities() {
		ship = new ShipEntity("sprites/ship.png", 370, 550);
		entities.add(ship);
		
		alienCount = 0;
		
		for(int row = 1; row <= horizontalAlienAmount; row++) {
			int currentAlienDistance = row * DISTANCE_BETWEEN_ALIENS;
			for(int column = 0; column < verticalAlienAmount; column++) {
				AlienEntity alien = new AlienEntity("sprites/alien.png", currentAlienDistance, (ALIEN_DISTANCE_FROM_TOP) + column * DISTANCE_BELOW_ALIEN);

				difficultyList.add(alien);
				entities.add(alien);
				alienCount++;
			}
		}

		updateDifficulty(currentDifficulty);
	}
	
	public void updateLogic() {
		logicRequiredThisLoop = true;
	}
	
	public void notifyDeath() {
		message = LOST_MESSAGE;
		keyInput.setWaitingForKeyPress(true);
	}
	
	public void notifyWin() {
		message = VICTORY_MESSAGE;
		keyInput.setWaitingForKeyPress(true);
	}
	
	public GameFrame getGUI() {
		return gui;
	}
	
	public Difficulty getCurDifficulty() {
		return currentDifficulty;
	}
	
	public boolean pauseControl() {
		paused = !paused;
		return paused;
	}
	
	public void updateDifficulty(Difficulty difficulty) {
		currentDifficulty = difficulty;
		
		for(IDifficulty current : difficultyList) {
			current.applyDifficulty(difficulty);
		}
	}
	
	public void startGame() {
		entities.clear();
		initEntities();
	}
	
	public void removeEntity(Entity e) {
		removeList.add(e);
	}
	
	public void notifyAlienKilled() {
		alienCount--;
		
		if(alienCount == 0) {
			notifyWin();
		}
		
		for(Entity current : entities) {
			
			if(current instanceof AlienEntity) {
				current.setHorizontalMovement(current.getHorizontalMovement() * alienSpeedIncrease);
			}
		}
	}
	
	public void tryToFire() {
		if (System.currentTimeMillis() - lastFire < firingInterval) {
			return;
		}
		
		lastFire = System.currentTimeMillis();
		ShotEntity shot = new ShotEntity("sprites/shot.png", ship.getXPosition() + 10, ship.getYPosition() - 30);
		entities.add(shot);
	}
	
	public void gameLoop() {	
		long lastLoopTime = System.currentTimeMillis();
		
		while(gameRunning) {
			long delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			
			Graphics2D graphics = gui.drawGraphics();
			
			if(!paused) {
				if (!keyInput.isWaiting()) {
					for (Entity current : entities) {
						AEntityController entityController = entityControllers.get(current.getType());
						entityController.moveEntity(current, delta);
					}
				}
				
				for (Entity current : entities) {
					current.draw(graphics);
				}
				
				for (int p=0; p<entities.size(); p++) {
					for (int s=p+1; s<entities.size(); s++) {
						Entity me = (Entity) entities.get(p);
						Entity him = (Entity) entities.get(s);
						AEntityController entityController = entityControllers.get(me.getType());
						AEntityController entityController2 = entityControllers.get(him.getType());
						
						if (entityController.collidesWith(me, him)) {
							entityController.collide(me, him);
							entityController2.collide(him, me);
						}
					}
				}
				
				entities.removeAll(removeList);
				removeList.clear();
	
				if (logicRequiredThisLoop) {
					for (Entity entity : entities) {
						AEntityController entityController = entityControllers.get(entity.getType());
						entityController.doLogic(entity);
						
					}
					
					logicRequiredThisLoop = false;
				}
				
				if (keyInput.isWaiting()) {
					graphics.setColor(Color.WHITE);
					graphics.drawString(message,(800 - graphics.getFontMetrics().stringWidth(message)) / 2, 250);
					graphics.drawString(ANY_KEY, (800 - graphics.getFontMetrics().stringWidth(ANY_KEY)) / 2, 300);
				}
				
				graphics.dispose();
				gui.getBufferStrategy().show();
				
				ship.setHorizontalMovement(0);
				
				boolean leftPressed = keyInput.isLeftPressed();
				boolean rightPressed = keyInput.isRightPressed();
				
				if ((leftPressed) && (!rightPressed)) {
					ship.setHorizontalMovement(-moveSpeed);
				} else if ((rightPressed) && (!leftPressed)) {
					ship.setHorizontalMovement(moveSpeed);
				}
				
				if (keyInput.isFirePressed()) {
					tryToFire();
				}
			}
			
			try {
				Thread.sleep(10);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String args[]) {
		Game game = new Game();	
		game.gameLoop();
	}

	public void applyDifficulty(Difficulty difficulty) {
		moveSpeed = difficulty.getShipMoveSpeed();
		firingInterval = difficulty.getFiringInterval();
		alienSpeedIncrease = difficulty.getAlienSpeedIncrease();
		horizontalAlienAmount = difficulty.getHorizontalAlienAmount();
		verticalAlienAmount = difficulty.getVerticalAlienAmount();
	}
	
}
