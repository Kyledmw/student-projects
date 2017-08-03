package com.game.save.editor.gui;

import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class FileMenu extends JMenu {
	
	private static final long serialVersionUID = 3660471212639953828L;
	private JMenuItem _open;
	private JMenuItem _save;
	
	public FileMenu() {
		this.setText(IUIStrings.FILE_LBL);
		_open = new JMenuItem(IUIStrings.OPEN_LBL);
		_save = new JMenuItem(IUIStrings.SAVE_LBL);
		
		this.add(_open);
		this.add(_save);
	}
	
	public void addOpenActionListener(ActionListener listener) {
		_open.addActionListener(listener);
	}
	
	public void addSaveActionListener(ActionListener listener) {
		_save.addActionListener(listener);
	}
	
	
}
