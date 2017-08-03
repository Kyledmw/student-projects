package com.space_invaders.gui;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;

import com.space_invaders.Game;
import com.space_invaders.global.difficulty.DifficultyManager;
import com.space_invaders.global.difficulty.DifficultyManager.DifficultyType;

public class MenuDialog extends JDialog {
	
	private final static String DIALOG = "Menu";
	private final static String CONTINUE_BUTTON = "Continue";
	private final static String EXIT_BUTTON = "Exit";
	
	private Container content;
	
	private JButton cont;
	private JButton exit;
	private JComboBox difficultySelection;
	
	private Game game;

	private static final long serialVersionUID = 280103025078196876L;

	public MenuDialog(GameFrame parent, Game game) {
		super(parent.getFrame(), Dialog.ModalityType.APPLICATION_MODAL);
		this.game = game;
		content = getContentPane();
		
		setLocationRelativeTo(parent);
		setTitle(DIALOG);
		game.pauseControl();
		createGUI();
		pack();
		setVisible(true);
	}
	
	public void createGUI() {
		content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
		
		cont = new JButton(CONTINUE_BUTTON);
		exit = new JButton(EXIT_BUTTON);
		
		cont.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				game.pauseControl();
				setVisible(false);
			}
		});
		
		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}

			
		});
		
		createDifficultySelector();
		
		content.add(cont);
		content.add(difficultySelection);
		content.add(exit);

	}
	
	private void createDifficultySelector() {
		
		final DifficultyManager dm = DifficultyManager.getInstance();
		
		final Map<DifficultyType, String> difficultyMap = dm.getDifficultyMap();
				
		String[] difficulties = dm.getDifficultyStrings();
		
		difficultySelection = new JComboBox(difficulties);
		
		String cur = difficultyMap.get(game.getCurDifficulty().getType());
		
		difficultySelection.setSelectedItem(cur);
		
		difficultySelection.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for(DifficultyType cur : difficultyMap.keySet()) {
					if(difficultyMap.get(cur).equals(difficultySelection.getSelectedItem())) {
						game.updateDifficulty(dm.getDifficultyChoice(cur));
						break;
					}
				}
			}
			
		});
		
	}
}
