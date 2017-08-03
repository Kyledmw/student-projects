package com.game.save.editor.gui;

import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import com.game.save.editor.logging.Logger;

/**
 * MainFrame of the application
 * 
 * @author Kyle Williamson
 *
 */
public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = -1702013595610208076L;
	private static final int WINDOW_WIDTH = 300;
	private static final int WINDOW_HEIGHT = 200;
	
	private JMenuBar _menuBar;
	
	private JPanel main;
	private BoxLayout _layout;
	
	private FileMenu _fileMenu;
	private UserDataPanel _userDataPanel;
	private JButton _clear;
	
	
	public MainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle(IUIStrings.WINDOW_LBL);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		
		main = new JPanel();
		
		_layout = new BoxLayout(main, BoxLayout.Y_AXIS);
		main.setLayout(_layout);
		this.add(main);
		
		createView();
		
		this.setVisible(true);
	}
	
	/**
	 * Worker method that initialised view.
	 */
	private void createView() {
		Logger.info("Creating view components");
		_menuBar = new JMenuBar();
		_fileMenu = new FileMenu();
		_menuBar.add(_fileMenu);
		
		_userDataPanel = new UserDataPanel();
		_clear = new JButton(IUIStrings.CLEAR_LBL);
		
		this.setJMenuBar(_menuBar);
		main.add(_userDataPanel);
		main.add(_clear);
		
	}
	
	/**
	 * Returns current filemenu
	 * @return FileMenu
	 */
	public FileMenu getFileMenu() {
		return _fileMenu;
	}
	
	/**
	 * Returns UserDataPanel
	 * @return UserDataPanel
	 */
	public UserDataPanel getUserDataPanel() {
		return _userDataPanel;
	}
	
	/**
	 * Add action listener to the clear button
	 * @param listener ActionListener
	 */
	public void addClearActionListener(ActionListener listener) {
		_clear.addActionListener(listener);
	}

}
