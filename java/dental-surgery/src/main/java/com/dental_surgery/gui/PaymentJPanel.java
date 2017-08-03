package com.dental_surgery.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * JPanel Class which contains all content for the Payment Section of DentalSurgeryView
 * 
 * @author Kyle Williamson R00110793
 *
 * @Extends {@link BaseJPanel}
 */
public class PaymentJPanel extends BaseJPanel{

	private static final long serialVersionUID = -1746827817868693603L;
	
	private JTextField amountField;
	private JComboBox<String> patientDropDown;
	private JComboBox<String> paymentDropDown;
	
	private JTextArea textArea;
	private JScrollPane scrollPane; 
	
	/**
	 * PaymentJPanel Constructor
	 * 
	 * Adds UI Components to self
	 * Does required configuration for UI Components
	 */
	public PaymentJPanel() {
		super("Add Payment", "Remove Payment", "List Payments");
		initializeComponents();
		
		createView();
	}
	
	// Get and Set Methods
	public double getAmount() {
		try {
			return Double.parseDouble(amountField.getText());
		}catch(Exception e) {
			return 0;
		}
	}
	
	/**
	 * Method which adds patients into the drop down
	 * Sets current selected Patient to initial value
	 * 
	 * @param patients String Array of Patient names
	 */
	public void setPatientDropDown(String[] patients) {
		for(String current : patients) {
			patientDropDown.addItem(current);
		}
	}
	
	public JComboBox<String> getPatientDropDown() {
		return patientDropDown;
	}
	
	/**
	 * Method that updates the drop down when a new Patient is created
	 * 
	 * @param patientName String containing newly created Patient name
	 */
	public void updatePatientDropDown(String patientName) {
		patientDropDown.addItem(patientName);
	}
	
	/**
	 * Method which sets/updates the Payment drop down
	 * Calls {@link clearTextFields()}
	 * Clears previous items in the drop down and adds new data from the String array
	 * 
	 * @param payments String Array of Payment amounts
	 */
	public void setPaymentDropDown(String[] payments) {
		clearTextFields();
		paymentDropDown.removeAllItems();
		for(String current : payments) {
			paymentDropDown.addItem(current);
		}
	}
	
	public JComboBox<String> getPaymentDropDown() {
		return paymentDropDown;
	}
	
	/**
	 * Method that initializes component objects used
	 */
	private void initializeComponents() {
		
		amountField = new JTextField();
		patientDropDown = new JComboBox<String>();
		paymentDropDown = new JComboBox<String>();
		
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
		
		JPanel topRight = new JPanel();
		JPanel topLeft = new JPanel();
		JPanel list = new JPanel();
		
		//Layouts are given to additional JPanels
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		top.setLayout(new FlowLayout());
		bot.setLayout(new FlowLayout());
		
		topRight.setLayout(new GridLayout(4, 2));
		topLeft.setLayout(new GridLayout(2, 2));
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
		
		//Components are added according to their desired position in the GUI
		topRight.add(new JLabel("<html><b>Payment Form:</b></html>"));
		topRight.add(Box.createVerticalGlue());
		topRight.add(new JLabel("Patient:"));
		topRight.add(patientDropDown);
		topRight.add(new JLabel("Amount:"));
		topRight.add(amountField);
		topRight.add(getAddBtn());
		
		topLeft.add(new JLabel("<html><b>Patients Payments:</b></html>"));
		topLeft.add(Box.createVerticalGlue());
		topLeft.add(paymentDropDown);
		topLeft.add(getRemoveBtn());
		
		list.add(getListBtn());
		list.add(scrollPane);
		
		top.add(topRight);
		top.add(topLeft);
		
		bot.add(list);
		
		this.add(top);
		this.add(bot);

	}
	
	/**
	 * Method which displays text into the textArea object
	 * It clears the current textArea
	 * Content is generated from a 2D Array which contains Payment datess and amounts
	 * 
	 * @param patientName name of the currently selected patient
	 * @param paymentDateAndCostList a 2D array containing each payment date and amount
	 * @param totalPayment total amount paid by the current patient
	 */
	public void setTextArea(String patientName, String[][] paymentDateAndCostList, String totalPayment) {
		textArea.setText(null);
		textArea.append("Patient: \t" + patientName + "\n\n");
		for(int i = 0; i < paymentDateAndCostList.length; i++) {
			textArea.append(paymentDateAndCostList[i][0] + "\t" + paymentDateAndCostList[i][1] + "\n");
		}
		textArea.append("\nTotal Amount Paid: \t" + totalPayment);
	}
	
	/**
	 * Method which clears content in the classes textfields
	 */
	public void clearTextFields() {
		amountField.setText(null);
	}
	
	// Event and Item Listeners
	public void addPatientDropDownListener(ItemListener listenForItemSelect) {
		patientDropDown.addItemListener(listenForItemSelect);
	}
}
