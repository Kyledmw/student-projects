package com.dental_surgery.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.dental_surgery.gui.DentalSurgeryView;
import com.dental_surgery.models.Patient;
import com.dental_surgery.models.ProcAndPatientListWrapper;
import com.dental_surgery.models.Procedure;

/**
 * Class which handles saving and loading model data into XML
 * This is handled through javax.xml libraries
 * 
 * @author Kyle Williamson R00110793
 *
 * @Extends {@link BaseController}
 */
public class XMLController extends BaseController {

	/**
	 * Constructor for XMLController Class
	 * Sets up Event Listeners
	 * 
	 * @param view reference of the JFrame class which controls the GUI
	 * @param patientList reference of ArrayList of Patient objects
	 */
	public XMLController(DentalSurgeryView view, ArrayList<Patient> patientList, ArrayList<Procedure> procedureList) {
		super(view, patientList, procedureList);
		setListeners();
	}
	
	public void setListeners() {
		getView().getgMenuBar().addSaveDataListener(new SaveDataListener());
	}
	/**
	 * Nested class which provides an interface for receiving action events from the Save MenuItem Component
	 * 
	 * @author Kyle Williamson R00110793
	 *
	 * @Implements {@link ActionListener}
	 */
	class SaveDataListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Calls {@link save()}
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				success = save();
				if(!success) {
					JOptionPane.showMessageDialog(null, "Couldn't save data.");
				} else {
					JOptionPane.showMessageDialog(null, "Save successful.");
					System.exit(0);
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}	
	}
	
	/**
	 * Method which saves model data into an XML file
	 * This is achieved through using javax.xml libraries, 
	 * wrapping the Patient ArrayList, 
	 * proper model annotation and the OutputStream Class
	 * 
	 * @return true if successful, false if failed
	 */
	public boolean save() {
		
		ProcAndPatientListWrapper list = new ProcAndPatientListWrapper(getPatientList(), getProcedureList());
		
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(ProcAndPatientListWrapper.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "https://github.com/Kyledmw DentalSurgery_v1.xsd");
			
			jaxbMarshaller.marshal(list, System.out);
			
			OutputStream os = new FileOutputStream("src/main/resources/Patients.xml");
			jaxbMarshaller.marshal(list, os);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Method which loads model data from an XML file
	 * This is achieved through using javax.xml libraries,
	 * InputStream Class,
	 * proper model annotation,
	 * unwrapping the PatientList
	 * and calling the {@link setModelTotals()} method
	 * 
	 * @return true if successful, false if failed
	 */
	public boolean load() {
		try {
			InputStream is = new FileInputStream("src/main/resources/Patients.xml");
			
			JAXBContext jaxbContext = JAXBContext.newInstance(ProcAndPatientListWrapper.class);
			
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			ProcAndPatientListWrapper list = (ProcAndPatientListWrapper) jaxbUnmarshaller.unmarshal(is);

			updatePatientList(list.getPatientList());
			updateProcedureList(list.getProcedureList());
			setModelTotals();
			
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
