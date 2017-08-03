package com.space_invaders.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GameFrame extends Canvas {
	
	private static final long serialVersionUID = -5846275025747854209L;
	
	private final static String FRAME_TITLE = "Space Invaders";

	private final static int LEFT_SCREEN_END = 10;
	
	private final static int RIGHT_SCREEN_END = 750;
	
	private final static int BOTTOM_SCREEN_END = 570;
	
	private BufferStrategy strategy;
	
	private JFrame container;
	private JPanel panel;
	
	public GameFrame() {
		container = new JFrame(FRAME_TITLE);
		panel = (JPanel) container.getContentPane();
		
		panel.setPreferredSize(new Dimension(800, 600));
		panel.setLayout(null);
		
		setBounds(0, 0, 800, 600);
		panel.add(this);
		
		setIgnoreRepaint(true);
		
		container.pack();
		container.setResizable(false);
		container.setVisible(true);
		
		container.addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		
		requestFocus();
		createBufferStrategy(2);
		strategy = getBufferStrategy();
	}
	
	public Graphics2D drawGraphics() {
		Graphics2D graphics = (Graphics2D) strategy.getDrawGraphics();
		graphics.setColor(Color.BLACK);
		graphics.fillRect(0, 0, 800, 600);
		
		return graphics;
	}
	
	public BufferStrategy getBuffStrategy() {
		return strategy;
	}
	
	public int getScreenEndLeft(){ 
		return LEFT_SCREEN_END;
	}
	
	public int getScreenEndRight() {
		return RIGHT_SCREEN_END;
	}
	
	public int getScreenEndBottom() {
		return BOTTOM_SCREEN_END;
	}
	
	public JFrame getFrame() {
		return container;
	}
	
}
