package com.dental_surgery.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.dental_surgery.gui.DentalSurgeryView;
import com.dental_surgery.models.Patient;
import com.dental_surgery.models.Procedure;

/**
 * 
 * Class which controls all data flow into the Procedure Class
 * Controls ProcedureJPanel View
 * @author Kyle Williamson R00110793
 *
 * @Extends {@link BaseController}
 */
public class ProcedureController extends BaseController {
	
	/**
	 * Constructor for ProcedureController Class
	 * Sends Patient names into the ProceduretJPanel Patient drop down
	 * Sends Procedure names of the initial Patient into ProcedureJPanel Procedure drop down
	 * Sets up Event Listeners
	 * 
	 * @param view reference of the JFrame class which controls the GUI
	 * @param patientList reference of ArrayList of Patient objects
	 * @param procedureList reference of ArrayList of Procedure objects
	 */
	public ProcedureController(DentalSurgeryView view, ArrayList<Patient> patientList, ArrayList<Procedure> procedureList) {
		super(view, patientList, procedureList);
		
		getView().getProcedurePanel().setProcedureDropDown(getProcedureNames());
		getView().getProcedurePanel().setPatientDropDown(getPatientNames());
		getView().getProcedurePanel().setPatientProcDropDown(getProcNamesForPatient(0));
		setListeners();
	}
	
	public void setListeners() {
		getView().getProcedurePanel().addAddBtnListener(new AddProcedureListener());
		getView().getProcedurePanel().addRemoveBtnListener(new RemoveProcedureListener());
		getView().getProcedurePanel().addEditProcedureListener(new EditProcedureListener());
		getView().getProcedurePanel().addPatientDropDownListener(new PatientDropDownListener());
		getView().getProcedurePanel().addAddProcToPatientListener(new AddProcToPatientListener());
		getView().getProcedurePanel().addRemoveProcFromPatientListener(new RemoveProcFromPatientListener());
		getView().getProcedurePanel().addListBtnListener(new ListProceduresForPatientListener());
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the AddProcedure Button Component
	 * 
	 * @author Kyle Williamson R00110793
	 * 
	 * @Implements {@link ActionListener}
	 */
	class AddProcedureListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 *  
		 * Gets data from Procedure form and calls {@link addProcedure()}
		 * Updates the Procedure drop down in the GUI
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				String procName = getView().getProcedurePanel().getProcedureFieldText();
				double procCost = getView().getProcedurePanel().getAmount();
				if(procName.equals("") || procCost == 0) {
					JOptionPane.showMessageDialog(null, "Form fields can't be empty or 0.");
				} else {
					success = addProcedure(procName, procCost);
					if(!success) {
						JOptionPane.showMessageDialog(null, "Adding Procedure failed.");
					} else {
						getView().getProcedurePanel().updateProcedureDropDown(procName);
					}
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the RemoveProcedure Button Component
	 * 
	 * @author Kyle Williamson R00110793
	 * 
	 * @Implements {@link ActionListener}
	 */
	class RemoveProcedureListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 *  
		 * Remove currently selected Procedure
		 * Calls {@link deleteProcedure(int index)}
		 * Updates the Procedure drop down in the GUI
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				int index =  getView().getProcedurePanel().getProcedureDropDown().getSelectedIndex();
				if(index == -1) {
					JOptionPane.showMessageDialog(null, "No Procedure selected.");
				} else {
					success = deleteProcedure(index);
					if(!success) {
						JOptionPane.showMessageDialog(null, "Deleting Procedure failed.");
					} else {
						getView().getProcedurePanel().getProcedureDropDown().removeItemAt(index);	
					}	
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Nested class which provides an interface for receiving item events from the Edit Button Component
	 * 
	 * @author R00110793
	 *
	 * @Implements {@link ActionListener}
	 */
	class EditProcedureListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Method that edits the currently selected procedure data
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				int index = getView().getProcedurePanel().getProcedureDropDown().getSelectedIndex();
				String procName = getView().getProcedurePanel().getProcedureFieldText();
				double procCost = getView().getProcedurePanel().getAmount();
				if(procName.equals("") || procCost == 0) {
					JOptionPane.showMessageDialog(null, "Form fields can't be empty or 0.");
				} else if(index == -1) {
					JOptionPane.showMessageDialog(null, "No Prcoedure selected.");
				}else {
					success = editProcedure(index, procName, procCost);
					if(!success) {
						JOptionPane.showMessageDialog(null, "Edit Procedure failed.");
					} else {
						getView().getProcedurePanel().getProcedureDropDown().removeItemAt(index);
						getView().getProcedurePanel().getProcedureDropDown().insertItemAt(procName, index);
						getView().getProcedurePanel().getProcedureDropDown().setSelectedItem(procName);
						getView().getProcedurePanel().clearTextFields();
					}
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
			
		}
		
	}
	
	/**
	 * Nested class which provides an interface for receiving item events from the Patient ComboBox Component
	 * 
	 * @author Kyle Williamson R00110793
	 * 
	 * @Implements {@link ItemListener}
	 */
	class PatientDropDownListener implements ItemListener {
		
		/**
		 * Implemented abstract method which is executed when an item event is received
		 * 
		 * Method that changes Procedure drop down based on which Patient is selected in the Patient drop down
		 */
		public void itemStateChanged(ItemEvent e) {
			try {
				int patientIndex = getView().getProcedurePanel().getPatientDropDown().getSelectedIndex();
				if(patientIndex == -1) {
					getView().getProcedurePanel().getPatientProcDropDown().removeAllItems();
				} else {
					getView().getProcedurePanel().setPatientProcDropDown(getProcNamesForPatient(patientIndex));	
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Nested class which provides an interface for receiving item events from the AddProctoPatient JButton Component
	 * 
	 * @author KyleWilliamson R00110793
	 *
	 * @Implements {@link ActionListener}
	 */
	class AddProcToPatientListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Method that adds currently selected procedure to the currently selected patient
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				int patientIndex = getView().getProcedurePanel().getPatientDropDown().getSelectedIndex();
				int procedureIndex = getView().getProcedurePanel().getProcedureDropDown().getSelectedIndex();
				if(patientIndex == -1 || procedureIndex == -1 ) {
					JOptionPane.showMessageDialog(null, "No Patient or Procedure selected.");
				} else {
					success = addProcedureForPatient(patientIndex, procedureIndex);
					if(!success) {
						JOptionPane.showMessageDialog(null, "Adding Procedure to Patient failed.");
					} else {
						getView().getProcedurePanel().setPatientProcDropDown(getProcNamesForPatient(patientIndex));
					}
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	/**
	 * Nested class which provides an interface for receiving item events from the RemoveProcFromPatient JButton Component
	 * 
	 * @author KyleWilliamson R00110793
	 *
	 * @Implements {@link ActionListener}
	 */
	class RemoveProcFromPatientListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Method that removes currently selected procedure that belongs to a selected patient
		 */
		public void actionPerformed(ActionEvent e) {
			boolean success = false;
			int patientIndex = getView().getProcedurePanel().getPatientDropDown().getSelectedIndex();
			int procedureIndex = getView().getProcedurePanel().getPatientProcDropDown().getSelectedIndex();
			if(patientIndex ==  -1 || procedureIndex == -1) {
				JOptionPane.showMessageDialog(null, "No Patient or Procedure selected.");
			} else {
				success = deleteProcedureForPatient(patientIndex, procedureIndex);
				if(!success) {
					JOptionPane.showMessageDialog(null, "Deleting Patient Procedure failed.");
				} else {
					getView().getProcedurePanel().setPatientProcDropDown(getProcNamesForPatient(patientIndex));
				}
			}
		}
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the ListProcedures Button Component
	 * 
	 * @author Kyle Williamson R00110793
	 * 
	 * @Implements {@link ActionListener}
	 */
	class ListProceduresForPatientListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Method generates a 2D Array which contains Procedure name as well as the Procedure cost.
		 * 
		 * This is passed into the view to display in a text area
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				int patientIndex = getView().getProcedurePanel().getPatientDropDown().getSelectedIndex();
				Patient currentPatient = getPatientList().get(patientIndex);
				String patientName = currentPatient.getPatientName();
				String totalCost = String.valueOf(currentPatient.getTotalCost());
				String totalPaid = String.valueOf(currentPatient.getAmountPaid());
				String owed = String.valueOf(currentPatient.getAmountOwed());
				String[][] procAndCostList = new String[currentPatient.getP_procList().size()][2];
				for(int i = 0; i < currentPatient.getP_procList().size(); i++) {
					procAndCostList[i][0] = currentPatient.getP_procList().get(i).getProcName();
					procAndCostList[i][1] = String.valueOf(currentPatient.getP_procList().get(i).getProcCost());
				}
				getView().getProcedurePanel().setTextArea(patientName, procAndCostList, totalCost, totalPaid, owed);
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Method that creates a new procedure and adds it to the Procedure List
	 * @param procName name of the procedure from the view
	 * @param procCost cost of the procedure from the view
	 * @return true if successful, false if failed
	 */
	public boolean addProcedure(String procName, double procCost) {
		try {
			getProcedureList().add(new Procedure(procName, procCost));
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Method that removes the a Procedure at index from the Procedure ArrayList
	 * @param index index of the Procedure to be removed
	 * @return true if successful, false if failed
	 */
	public boolean deleteProcedure(int index) {
		try {
			getProcedureList().remove(index);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Method which edits the currently selected Procedure
	 * 
	 * @param index index of the currently Procedure in the Procedure drop down
	 * @param procName name entered in the Procedure name text field
	 * @param procCost cost entered in the Procedure cost text field
	 * @return true if successful, false if failed
	 */
	public boolean editProcedure(int index, String procName, double procCost) {
		try {
			Procedure current = getProcedureList().get(index);
			current.setProcName(procName);
			current.setProcCost(procCost);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Method which adds a Procedure to the Patient at index
	 * 
	 * @param patientIndex index of the Patient selected from the Patient drop down
	 * @param procName Procedure name entered in GUI text field
	 * @param procCost Procedure cost entered in GUI text field
	 * @return true if successful, false if failed
	 */
	public boolean addProcedureForPatient(int patientIndex, int procedureIndex) {
		Patient current = getPatientList().get(patientIndex);
		if(current != null) { 
			current.getP_procList().add(getProcedureList().get(procedureIndex));
			return true;
		}
		return false;
	}
	
	/**
	 * Method which deletes the currently selected Procedure for the currently selected Patient
	 * 
	 * @param patientIndex index of the Patient selected in the Patient drop down
	 * @param index index of the currently selected Procedure in the Procedure drop down
	 * @return true if successful, false if failed
	 */
	public boolean deleteProcedureForPatient(int patientIndex, int procedureIndex) {
		boolean success = false;
		try {
			Patient patient = getPatientList().get(patientIndex);
			if(patient != null) {
				patient.getP_procList().remove(procedureIndex);
				success = true;
			}
			return success;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Method which generates a String array of Procedure names for the current selected Patient
	 * 
	 * @param patientIndex index of currently selected Patient
	 * @return procedures String array if successful, null if failed
	 */
	public String[] getProcNamesForPatient(int patientIndex) {
		try {
			String[] procedures = new String[getPatientList().get(patientIndex).getP_procList().size()];
			for(int i = 0; i< procedures.length; i++) {
				procedures[i] = getPatientList().get(patientIndex).getP_procList().get(i).getProcName();
			}
			return procedures;
		} catch(Exception e) {
			return null;
		}
	}
	
	
}
