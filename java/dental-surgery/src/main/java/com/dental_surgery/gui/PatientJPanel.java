package com.dental_surgery.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * JPanel Class which contains all content for the Patient Section of DentalSurgeryView
 * 
 * @author Kyle Williamson R00110793
 *
 * @Extends {@link BaseJPanel}
 */
public class PatientJPanel extends BaseJPanel {

	private static final long serialVersionUID = 2173217446590705712L;
	
	private JTextField nameField;
	private JTextField addressField;
	private JTextField contactNoField;
	private JComboBox<String> dropDown;
	private JTextArea textArea;
	private JScrollPane scrollPane; 
	
	/**
	 * PatientJPanel Constructor
	 * 
	 * Adds UI Components to self
	 * Does required configuration for UI Components
	 * 
	 */
	public PatientJPanel() {
		super("Add Patient", "Remove Patient", "List Patients");
		initializeComponents();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	
		createView();
	}
	 
	
	// Get and Set Methods
	
	public String getNameFieldText() {
		return nameField.getText();
	}
	
	public String getAddressFieldText() {
		return addressField.getText();
	}
	
	public String getContactNoFieldText() {
		return contactNoField.getText();
	}
	
	/**
	 * Method which adds patients into the drop down
	 * Sets current selected Patient to initial value
	 * 
	 * @param patients String Array of Patient names
	 */
	public void setDropDown(String[] patients) {
		for(String current : patients) {
			dropDown.addItem(current);
		}
	}
	
	public JComboBox<String> getDropDown() {
		return dropDown;
	}
	
	/**
	 * Method that initializes component objects used
	 */
	private void initializeComponents() {
		nameField = new JTextField();
		addressField = new JTextField();
		contactNoField = new JTextField();
		dropDown = new JComboBox<String>();
		textArea = new JTextArea(10, 40);
		scrollPane = new JScrollPane(textArea);
		textArea.setEditable(false);
	}
	
	/**
	 * Method that constructs the view by adding layouts and components
	 */
	private void createView() {
		
		//Creating additional JPanels
		JPanel top = new JPanel();
		JPanel bot = new JPanel();
		
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		JPanel list = new JPanel();
		
		//Layouts are given to additional JPanels
		bot.setLayout(new FlowLayout());
		left.setLayout(new GridLayout(5, 2));
		right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
		
		
		//Components are added according to their desired position in the GUI
		left.add(new JLabel("<html><b>Patient Form:</b></html>"));
		left.add(Box.createVerticalGlue());
		left.add(new JLabel("Patient Name:"));
		left.add(nameField);
		left.add(new JLabel("Address"));
		left.add(addressField);
		left.add(new JLabel("Contact Number:"));
		left.add(contactNoField);
		left.add(getAddBtn());
		
		right.add(new JLabel("<html><b>Current Patients:</b></html>"));
		right.add(dropDown);
		right.add(getRemoveBtn());
		
		list.add(getListBtn());
		list.add(scrollPane);
		
		top.add(left);
		top.add(right);
		bot.add(list);
		
		this.add(top);
		this.add(bot);
	}
	
	/**
	 * Method that updates the drop down when a new Patient is created
	 * Calls {@link clearTextFields()}
	 * 
	 * @param patientName String containing newly created Patient name
	 */
	public void updateDropDown(String patientName) {
		clearTextFields();
		dropDown.addItem(patientName);
	}
	
	/**
	 * Method which displays text into the textArea object
	 * It clears the current textArea
	 * Content is generated from a 2D Array which contains Patient Names and Total Owed
	 * 
	 * @param nameAndCostList 2D Array containing Patient names and total owed
	 */
	public void setTextArea(String[][] patientDetailsList) {
		textArea.setText(null);
		textArea.append("No: \t" + "Name: \t" + "Address: \t" + "Phone: \n\n");
		for(int i = 0; i < patientDetailsList.length; i++) {
			textArea.append(patientDetailsList[i][0] + "\t" 
						+ patientDetailsList[i][1] + "\t"
						+ patientDetailsList[i][2] + "\t" 
						+ patientDetailsList[i][3] + "\t" + "\n");
		}
	}
	
	/**
	 * Method which clears content in the classes textfields
	 */
	public void clearTextFields() {
		nameField.setText(null);
		addressField.setText(null);
		contactNoField.setText(null);
	}
}
