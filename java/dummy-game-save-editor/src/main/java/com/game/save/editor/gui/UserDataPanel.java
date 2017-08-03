package com.game.save.editor.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.game.save.editor.difficulty.DifficultyManager;
import com.game.save.editor.logging.Logger;
import com.game.save.editor.user_data.IUserDataModelReader;

/**
 * UserDataPanel, contains fields relating to the UserDataModel
 * 
 * @author Kyle Williamson
 *
 */
public class UserDataPanel extends JPanel {
	
	private static final long serialVersionUID = 4528231467576686928L;
	private static final int GRID_ROWS = 3;
	private static final int GRID_COLS = 2;
	private static final int GRID_V_GAP = 10;
	private static final int GRID_H_GAP = 10;
	
	private JLabel _userNameLbl;
	private JLabel _scoreLbl;
	private JLabel _difficultyLbl;
	
	private JTextField _userNameField;
	private JTextField _scoreField;
	private JComboBox<String> _difficultyField;
	
	private FlowLayout _fl;
	private GridLayout _gl;

	public UserDataPanel() {
		_fl = new FlowLayout();
		
		this.setLayout(_fl);
		
		createView();
		
	}
	
	/**
	 * Set data within the difficulty combo box
	 * @param difficulties String[]
	 */
	public void setDifficultyComboBox(String[] difficulties) {
		Logger.info("Setting difficulty combobox");
		_difficultyField.removeAllItems();
		for(String difficulty : difficulties) {
			_difficultyField.addItem(difficulty);
		}
	}
	
	/**
	 * Update view with data from the given UserDataModelReader
	 * @param reader IUserDataModelReader
	 */
	public void updateView(IUserDataModelReader reader) {
		Logger.info("Updating view based on model data");
		Map difficultyMap = DifficultyManager.getInstance().getDifficultyMap();
		_userNameField.setText(reader.getUsername());
		_scoreField.setText(new Integer(reader.getScore()).toString());
		_difficultyField.setSelectedItem(difficultyMap.get(reader.getDifficulty()));
	}
	
	/**
	 * Initialise view components
	 */
	private void createView() {
		_userNameLbl = new JLabel(IUIStrings.USERNAME_LBL);
		_scoreLbl = new JLabel(IUIStrings.SCORE_LBL);
		_difficultyLbl = new JLabel(IUIStrings.DT_LBL);
		
		_userNameField = new JTextField();
		_scoreField = new JTextField();
		_difficultyField = new JComboBox<String>();
		
		_scoreField.setText("0");
		_scoreField.addKeyListener(new NumericFieldKeyHandler());
		
		JPanel form = new JPanel();
		_gl = new GridLayout(GRID_ROWS, GRID_COLS, GRID_H_GAP, GRID_V_GAP);
		form.setLayout(_gl);
		
		form.add(_userNameLbl);
		form.add(_userNameField);
		form.add(_scoreLbl);
		form.add(_scoreField);
		form.add(_difficultyLbl);
		form.add(_difficultyField);
		
		this.add(form);
		
	}
	
	public JTextField getNameField() {
		return _userNameField;
	}
	
	public JTextField getScoreField() {
		return _scoreField;
	}
	
	public JComboBox getDifField() {
		return _difficultyField;
	}
	
	public String getName() {
		return _userNameField.getText();
	}
	
	public int getScore() {
		if(_scoreField.getText().equals("")) {
			return 0;
		}
		return Integer.parseInt(_scoreField.getText());
	}
}
