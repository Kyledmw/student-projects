package com.chat_room.client.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import com.chat_room.client.constants.IObserverConstants;
import com.chat_room.client.rmi.RMIConnector;
import com.chat_room.client.security.ObjectUnsigner;
import com.chat_room.client.ui.constants.IUIConstants;
import com.chat_room.rmi.interfaces.IRemoteMessage;

/**
 ********************************************************************
 * Main view, contains chat room and user messaging panel
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class MainFrame extends JFrame implements Observer {
	
	private static final long serialVersionUID = -1709895231893595068L;
	
	private static final int WINDOW_WIDTH = 800;
	private static final int WINDOW_HEIGHT = 600;
	
	private static final int INPUT_ROWS = 1;
	private static final int INPUT_COLS = 3;
	
	private static final int CHATLOG_ROWS = 30;
	private static final int CHATLOG_COLS = 60;

	private JTextArea _chatLog;
	private JTextField _messageInput;	
	private JLabel _messageLbl;
	private JButton _sendMessageBtn;
	
	public MainFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setTitle(IUIConstants.CHAT_CLIENT);
		
		initViewComponents();
		setVisible(true);
	}
	

	/**
	 * Initialise view components for the dialog
	 */
	private void initViewComponents() {
		JPanel main = new JPanel();
		main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
		
		JPanel chatViewPanel = new JPanel();
		chatViewPanel.setBorder(new TitledBorder(new EtchedBorder(), IUIConstants.CHAT_ROOM));
		
		_chatLog = new JTextArea(CHATLOG_ROWS, CHATLOG_COLS);
		_chatLog.setEditable(false);
		
		JScrollPane scroll = new JScrollPane(_chatLog);
		
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(INPUT_ROWS, INPUT_COLS));
		
		_messageLbl = new JLabel(IUIConstants.ENTER_MESSAGE);
		_sendMessageBtn = new JButton(IUIConstants.SEND_MESSAGE);
		_messageInput = new JTextField();
		
		_sendMessageBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				RMIConnector.getInstance().sendMessage(_messageInput.getText());
			}
			
		});
		
		inputPanel.add(_messageLbl);
		inputPanel.add(_messageInput);
		inputPanel.add(_sendMessageBtn);
		
		chatViewPanel.add(scroll);
		main.add(chatViewPanel);
		main.add(inputPanel);
		this.add(main);
	}
	
	/**
	 * Add action listener to the send message button
	 * 
	 * @param al actionlistener
	 */
	public void addSendMesageActionListener(ActionListener al) {
		_sendMessageBtn.addActionListener(al);
	}

	public void update(Observable o, Object arg) {
		if(arg.equals(IObserverConstants.MESSAGE_UPDATE)) {
			try {
				List<IRemoteMessage> messages  = RMIConnector.getInstance().getMessages();
				updateMessageBox(messages);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Update the chat log with a list of remote messages
	 * 
	 * @param messages List of {@link IRemoteMessage} objects
	 * @throws RemoteException
	 */
	private void updateMessageBox(List<IRemoteMessage> messages) throws RemoteException {
		_chatLog.setText("");
		for(IRemoteMessage cur: messages) {
			String message = "";
			try {
				message = (String) ObjectUnsigner.getInstance().unsignObject(cur.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
			_chatLog.append(cur.getUsername() + IUIConstants.COLON + message + IUIConstants.NEW_LINE);
		}
	}
}
