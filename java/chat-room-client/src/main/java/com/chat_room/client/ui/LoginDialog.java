package com.chat_room.client.ui;

import java.awt.Container;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.chat_room.client.rmi.RMIConnector;
import com.chat_room.client.ui.constants.IUIConstants;
import com.chat_room.client.ui.validation.NumericFieldKeyHandler;

/**
 ********************************************************************
 * Dialog for users to login to the server
 * 
 * @extends {@link JDialog}
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class LoginDialog extends JDialog {
	
	private static final int ROWS = 3;
	private static final int COLUMNS = 2;
	
	private static final long serialVersionUID = 7681352870744942262L;

	private Container _content;
	
	private JLabel _usernameLbl;
	private JTextField _usernameField;
	
	private JLabel _ageLbl;
	private JTextField _ageField;
	

	public LoginDialog(JFrame parent) {
		super(parent, Dialog.ModalityType.APPLICATION_MODAL);
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.setTitle(IUIConstants.LOGIN_DIALOG);

		initViewComponents();
		setLocationRelativeTo(parent);
		pack();
		setVisible(true);
	}
	
	/**
	 * Initialise view components for the dialog
	 */
	private void initViewComponents() {
		_content = getContentPane();
		_content.setLayout(new GridLayout(ROWS, COLUMNS));
		
		_usernameLbl = new JLabel(IUIConstants.USERNAME);
		_usernameField = new JTextField();
		

		_ageLbl = new JLabel(IUIConstants.AGE);
		_ageField = new JTextField();
		_ageField.addKeyListener(new NumericFieldKeyHandler());
		
		JButton _submit = new JButton(IUIConstants.SUBMIT_LOGIN);
		_submit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int age = 0;
				if(!_ageField.getText().equals("")) {
					age = Integer.parseInt(_ageField.getText());
				}
				RMIConnector.getInstance().login(_usernameField.getText(), age);
				RMIConnector.getInstance().startSession();
				setVisible(false);
			}
			
		});
		_content.add(_usernameLbl);
		_content.add(_usernameField);
		_content.add(_ageLbl);
		_content.add(_ageField);
		_content.add(_submit);
		
	}
}
