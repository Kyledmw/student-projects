package com.game.save.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

import com.game.save.editor.difficulty.Difficulty;
import com.game.save.editor.difficulty.DifficultyManager;
import com.game.save.editor.difficulty.DifficultyManager.DifficultyType;
import com.game.save.editor.gui.FileMenu;
import com.game.save.editor.gui.IUIStrings;
import com.game.save.editor.gui.MainFrame;
import com.game.save.editor.logging.Logger;
import com.game.save.editor.persistence.IFileController;
import com.game.save.editor.persistence.UserDataFileController;
import com.game.save.editor.user_data.IUserDataConstants;
import com.game.save.editor.user_data.IUserDataModelController;
import com.game.save.editor.user_data.IUserDataModelReader;

/**
 * Controller which handles the view, observes the model controller
 * 
 * @author Kyle Williamson
 *
 */
public class UserDataEditorController implements Observer {
	
	private IUserDataModelController _modelController;
	private IFileController _fileController;
	private DifficultyManager dm;
	private Difficulty currentDifficulty;
	private MainFrame view;
	
	public UserDataEditorController(IUserDataModelController modelController) {
		_modelController = modelController;
		_modelController.addObserver(this);
		
		_fileController = new UserDataFileController(_modelController);
		
		Logger.info("Creating View");
		view = new MainFrame();
		dm = DifficultyManager.getInstance();
		
		FileMenu fm = view.getFileMenu();
		fm.addOpenActionListener(new OpenListener());
		fm.addSaveActionListener(new SaveListener());
		view.addClearActionListener(new ClearListener());
		
		view.getUserDataPanel().setDifficultyComboBox(dm.getDifficultyStrings());
		
		/*
		 * Listener that updates the modal when the user types into the name field.
		 */
		view.getUserDataPanel().getNameField().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Logger.info("User entered data in username field");
				_modelController.getModelWriter().setUsername(view.getUserDataPanel().getName());
			}
		});
		
		/*
		 * Listener that updates the modal when the user types into the score field
		 */
		view.getUserDataPanel().getScoreField().addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				Logger.info("User entered data in score field");
				_modelController.getModelWriter().setScore(view.getUserDataPanel().getScore());
			}
		});
		
		/*
		 * Listener that updates the modal when an item is selected in the difficulty dropdown
		 */
		view.getUserDataPanel().getDifField().addItemListener(new ItemListener() {

			public void itemStateChanged(ItemEvent e) {
				Logger.info("User selected a difficulty");
				Map<DifficultyType, String> diffMap = DifficultyManager.getInstance().getDifficultyMap();
				for(Map.Entry<DifficultyType, String> cur : diffMap.entrySet()) {
					if(cur.getValue().equals(e.getItem())) {
						_modelController.getModelWriter().setDifficulty(cur.getKey());
					}
				}
			}
			
		});
		
	}

	public void update(Observable o, Object arg) {
		Logger.info("In UserDataEditorController update");
		//if argument given means the modal has been changed or cleared
		if(arg.equals(IUserDataConstants.MODEL_CHANGE_PROPERTIES) || arg.equals(IUserDataConstants.MODEL_CLEARED)) {
			Logger.info("Arg is " + arg);
			
			//update view
			IUserDataModelReader reader = _modelController.getModelReader();
			view.getUserDataPanel().updateView(reader);	
			currentDifficulty = dm.getDifficultyChoice(reader.getDifficulty());
			Logger.info("Current Difficulty: " + currentDifficulty.toString());
		}
	}
	
	
	/**
	 * Listener for the open menu item
	 * 
	 * @author Kyle Williamson
	 *
	 */
	class OpenListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				Logger.info("User clicked open menu item");
				_fileController.open();
			} catch (IOException e1) {
				Logger.err(IUIStrings.OPEN_FILE_ERR);
				Logger.err(e1.toString());
				JOptionPane.showMessageDialog(view, IUIStrings.OPEN_FILE_ERR);
			}
			
		}
		
	}
	
	/**
	 * Listener for the save menu item
	 * 
	 * @author Kyle Williamson
	 *
	 */
	class SaveListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			try {
				Logger.info("User clicked save menu item");
				_fileController.save();
			} catch (IOException e1) {
				Logger.err(IUIStrings.SAVE_FILE_ERR);
				Logger.err(e1.toString());
				JOptionPane.showMessageDialog(view, IUIStrings.SAVE_FILE_ERR);
			}
			
		}
	}
	
	/**
	 * Listener for the clear button
	 * 
	 * @author Kyle
	 *
	 */
	class ClearListener implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			Logger.info("User clicked clear button");
			_modelController.getModelWriter().clearModel();
		}
	}

}
