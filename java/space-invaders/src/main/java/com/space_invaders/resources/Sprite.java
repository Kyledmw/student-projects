package com.space_invaders.resources;

import java.awt.Graphics;
import java.awt.Image;

public class Sprite {
	
	private Image sprite;

	public Sprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public int getWidth() {
		return sprite.getWidth(null);
	}
	
	public int getHeight() {
		return sprite.getHeight(null);
	}
	
	public void draw(Graphics g, int x, int y) {
		g.drawImage(sprite, x, y, null);
	}
}
