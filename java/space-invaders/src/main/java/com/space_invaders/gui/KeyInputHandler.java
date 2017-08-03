package com.space_invaders.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.space_invaders.Game;

public class KeyInputHandler extends KeyAdapter {
	
	private final static int ESC_KEY = 27;
	
	private boolean waitingForKeyPress;
	
	private boolean leftPressed;
	private boolean rightPressed;
	private boolean firePressed;
	
	private int pressCount;
	
	private Game game;
	
	public KeyInputHandler(Game game) {
		this.game = game;
		
		waitingForKeyPress = true;
		initKeyPress();
		
		pressCount = 0;
	}
	
	public void initKeyPress() {
		leftPressed = false;
		rightPressed = false;
		firePressed = false;
	}

	public void keyPressed(KeyEvent e) {
		if(waitingForKeyPress) {
			return;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			firePressed = true;
		}
	}
	
	public void keyReleased(KeyEvent e) {	
		if(waitingForKeyPress) {
			return;
		}
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			firePressed = false;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		if (waitingForKeyPress) {
			if (pressCount == 1) {
				waitingForKeyPress = false;
				game.startGame();
				pressCount = 0;
			} else {
				pressCount++;
			}
		}
		
		
		if (e.getKeyChar() == ESC_KEY) {
			new MenuDialog(game.getGUI(), game);
		}
	}
	
	public void setWaitingForKeyPress(boolean waiting) {
		waitingForKeyPress = waiting;
	}
	
	public boolean isWaiting() {
		return waitingForKeyPress;
	}
	
	public boolean isLeftPressed() {
		return leftPressed;
	}
	
	public boolean isRightPressed() {
		return rightPressed;
	}
	
	public boolean isFirePressed() {
		return firePressed;
	}
}
