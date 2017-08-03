package com.game.save.editor.gui;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Key Listener that makes the field only accept numeric values
 * 
 * @author Kyle Williamson
 *
 */
public class NumericFieldKeyHandler extends KeyAdapter {

	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if(!Character.isDigit(c)) {
			e.consume();
		}
	}
}
