package com.chat_room.client.ui.validation;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 ********************************************************************
 * Key Listener that makes the field only accept numeric values
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class NumericFieldKeyHandler extends KeyAdapter {

	public void keyTyped(KeyEvent e) {
		char c = e.getKeyChar();
		if(!Character.isDigit(c)) {
			e.consume();
		} 
	}
}
