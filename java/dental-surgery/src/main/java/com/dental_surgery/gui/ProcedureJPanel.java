package com.dental_surgery.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * JPanel Class which contains all content for the Procedure Section of DentalSurgeryView
 * 
 * @author Kyle Williamson R00110793
 *
 * @Extends {@link BaseJPanel}
 */
public class ProcedureJPanel extends BaseJPanel {
	
	private static final long serialVersionUID = -5021750073422578021L;
	
	private JComboBox<String> procedureDropDown = new JComboBox<String>();
	
	private JComboBox<String> patientDropDown;
	private JTextField procField;
	private JTextField amountField;
	private JComboBox<String> patientProcDropDown;
	
	private JButton addProctoPatientBtn;
	private JButton removeProcFromPatientBtn;
	private JTextArea textArea;
	private JScrollPane scrollPane; 
	
	private JButton editBtn;
	
	/**
	 * ProcedureJPanel Constructor
	 * 
	 * Adds UI Components to self
	 * Does required configuration for UI Components
	 */
	public ProcedureJPanel() {
		super("Add Procedure", "Remove Procedure", "List Procedure");
		intializeComponents();
		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		createView();
	}
	
	// Get and Set Methods
	public String getProcedureFieldText() {
		return procField.getText();
	}
	
	/**
	 * Method that gets text from amount field and parses it to a double
	 * @return parsed double value of the amount textfield if successful, 0 if failed
	 */
	public double getAmount() {
		try {
			return Double.parseDouble(amountField.getText());
		}catch(Exception e) {
			return 0;
		}
	}
	
	/**
	 * Method which adds procedures into the procedure drop down
	 * 
	 * @param procedures String Array of Procedure Names
	 */
	public void setProcedureDropDown(String[] procedures) {
		for(String current: procedures) {
			procedureDropDown.addItem(current);
		}
	}
	
	public JComboBox<String> getProcedureDropDown() {
		return procedureDropDown;
	}
	public void updateProcedureDropDown(String procedure) {
		clearTextFields();
		procedureDropDown.addItem(procedure);
	}
	
	/**
	 * Method which adds patients into the drop down
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
	 * Method that initializes component objects used
	 */
	private void intializeComponents() {
		procedureDropDown = new JComboBox<String>();
		
		patientDropDown = new JComboBox<String>();
		procField = new JTextField();
		amountField = new JTextField();
		patientProcDropDown = new JComboBox<String>();
		
		addProctoPatientBtn = new JButton("Add Proc. to Patient");
		removeProcFromPatientBtn = new JButton("Remove Proc. From Patient");
		textArea = new JTextArea(10, 40);
		scrollPane = new JScrollPane(textArea); 
		
		editBtn = new JButton("Edit Procedure");
		
		textArea.setEditable(false);
	}
	
	/**
	 * Method that creates layouts and adds components
	 */
	private void createView() {
		
		//Additional JPanels are created
		JPanel top = new JPanel();
		JPanel centre = new JPanel();
		JPanel bot = new JPanel();
		
		JPanel topLeft = new JPanel();
		JPanel topRight = new JPanel();
		JPanel centreContent = new JPanel();
		JPanel list = new JPanel();
		
		//Layouts are given to additional JPanels
		top.setLayout(new FlowLayout());
		centre.setLayout(new FlowLayout());
		bot.setLayout(new FlowLayout());
		
		topLeft.setLayout(new GridLayout(4, 2));
		topRight.setLayout(new GridLayout(3, 2));
		centreContent.setLayout(new GridLayout(5, 2));
		list.setLayout(new BoxLayout(list, BoxLayout.Y_AXIS));
		
		//Components are added according to their desired position in the GUI
		topLeft.add(new JLabel("<html><b> Procedure Form:</b></html>"));
		topLeft.add(Box.createVerticalGlue());
		topLeft.add(new JLabel("Name:"));
		topLeft.add(procField);
		topLeft.add(new JLabel("Amount:"));
		topLeft.add(amountField);
		topLeft.add(getAddBtn());
		
		topRight.add(new JLabel("<html><b>Current Procedure:</b></html>"));
		topRight.add(Box.createVerticalGlue());
		topRight.add(procedureDropDown);
		topRight.add(getRemoveBtn());
		topRight.add(new JLabel("Edit Procedure with form: "));
		topRight.add(editBtn);
		
		centreContent.add(new JLabel("<html><b>Patients Procedure</b></html>"));
		centreContent.add(new JLabel(""));
		centreContent.add(new JLabel("Patient:"));
		centreContent.add(patientDropDown);
		centreContent.add(new JLabel("Patients Procedures:"));
		centreContent.add(patientProcDropDown);
		centreContent.add(new JLabel("Add selected Procedure to Patient: "));
		centreContent.add(addProctoPatientBtn);
		centreContent.add(removeProcFromPatientBtn);
		
		list.add(getListBtn());
		list.add(scrollPane);
		
		top.add(topLeft);
		top.add(topRight);
		centre.add(centreContent);
		bot.add(list);
		
		this.add(top);
		this.add(centre);
		this.add(bot);
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
	 * Method which sets/updates the Procedure drop down
	 * Calls {@link clearTextFields()}
	 * Clears previous items in the drop down and adds new data from the String array
	 * 
	 * @param payments String Array of Procedure names
	 */
	public void setPatientProcDropDown(String[] procedures) {
		clearTextFields();
		patientProcDropDown.removeAllItems();
		for(String current : procedures) {
			patientProcDropDown.addItem(current);
		}
	}
	
	public JComboBox<String> getPatientProcDropDown() {
		return patientProcDropDown;
	}
	
	/**
	 * Method which displays text into the textArea object
	 * It clears the current textArea
	 * Content is generated from a 2D Array which contains Procedure names and costs
	 * 
	 * @param patientName name of the currently selected patient
	 * @param procAndCostList a 2D array containing each Procedure name and cost
	 * @param totalCost total cost of current patients procedures
	 */
	public void setTextArea(String patientName, String[][] procAndCostList, String totalCost, String totalPaid, String owed) {
		textArea.setText(null);
		textArea.append("Patient: " + patientName + "\n\n");
		for(int i = 0; i < procAndCostList.length; i++) {
			textArea.append(procAndCostList[i][0] + " " + procAndCostList[i][1] + "\n");
		}
		textArea.append("\nTotal: " + totalCost + "\n\n");
		textArea.append("Paid: " + totalPaid + "\n\n" );
		textArea.append("Amount Owed: " + owed);
	}
	
	/**
	 * Method which clears content in the classes textfields
	 */
	public void clearTextFields() {
		procField.setText(null);
		amountField.setText(null);
	}
	
	// Event and Item Listeners
	public void addPatientDropDownListener(ItemListener listenForItemSelect) {
		patientDropDown.addItemListener(listenForItemSelect);
	}
	
	public void addEditProcedureListener(ActionListener listenForEditBtn) {
		editBtn.addActionListener(listenForEditBtn);
	}
	
	public void addAddProcToPatientListener(ActionListener listenForProcToPatBtn) {
		addProctoPatientBtn.addActionListener(listenForProcToPatBtn);
	}
	
	public void addRemoveProcFromPatientListener(ActionListener listForProcFromPatBtn) {
		removeProcFromPatientBtn.addActionListener(listForProcFromPatBtn);
	}
}
