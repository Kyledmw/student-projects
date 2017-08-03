package com.space_invaders.resources;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class SpriteManager {
	
	private static SpriteManager instance = new SpriteManager();
	
	private ClassLoader classLoader;
	private HashMap<String, Sprite> sprites;
	
	private SpriteManager() {
		sprites = new HashMap<String, Sprite>();
		classLoader = getClass().getClassLoader();
	}
	
	public static SpriteManager getInstance() {
		return instance;
	}
	
	public Sprite getSprite(String ref) {
		
		if(sprites.get(ref) != null) {
			return sprites.get(ref);
		}
		
		BufferedImage srcImage = null;
		
		try {
			srcImage = ImageIO.read(new File(classLoader.getResource(ref).getFile()));
		} catch(IOException e) {
			System.err.println(e.toString());
		}
		
		GraphicsConfiguration gc = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();
		Image image = gc.createCompatibleImage(srcImage.getWidth(),srcImage.getHeight(),Transparency.BITMASK);
		
		image.getGraphics().drawImage(srcImage, 0, 0, null);
		
		Sprite sprite = new Sprite(image);
		
		sprites.put(ref, sprite);
		
		return sprite;
	}
}
