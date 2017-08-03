package com.dental_surgery.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import com.dental_surgery.gui.DentalSurgeryView;
import com.dental_surgery.models.Patient;
import com.dental_surgery.models.Payment;
import com.dental_surgery.models.Procedure;

/**
 * Class which handles Report generation
 * 
 * @author Kyle Williamson R00110793
 *
 * @Extends {@link BaseController}
 */
public class ReportController extends BaseController {

	public ReportController(DentalSurgeryView view, ArrayList<Patient> patientList) {
		super(view, patientList);
		setListeners();
	}
	public ReportController(DentalSurgeryView view, ArrayList<Patient> patientList, ArrayList<Procedure> procList) {
		super(view, patientList, procList);
		setListeners();
	}
	public void setListeners() {
		getView().getgMenuBar().addGenReportListener(new GenReportListener());
		getView().getgMenuBar().addGenOwedReportListener(new GenOwedReportListener());
		getView().getgMenuBar().addGenProcAmountReportListener(new GenProcAmountReportListener());
		getView().getReportsView().addDisplayReportListener(new DisplayReportListener());
		getView().getReportsView().addDisplayOwedReportListener(new DisplayOwedReportListener());
		getView().getReportsView().addDisplayProcAmountReportListener(new DisplayProcAmountReportListener());
	}
	
	/**
	 *  Nested class which provides an interface for receiving action events from the Generate Report MenuItem Component
	 * 
	 * @author Kyle Williamson R00110793
	 *
	 */
	class GenReportListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Calls {@link generateReport()}
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				success = generateReport();
				if(!success) {
					JOptionPane.showMessageDialog(null, "Couldn't generate report.");
				} else {
					JOptionPane.showMessageDialog(null, "Report generated.");
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the Generate Owed Report MenuItem
	 * 
	 * @author Kyle Williamson R00110793
	 *
	 */
	class GenOwedReportListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Calls {@link generateOwedReport()}
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				success = generateOwedReport();
				if(!success) {
					JOptionPane.showMessageDialog(null, "Couldn't generate report.");
				} else {
					JOptionPane.showMessageDialog(null, "Report generated.");
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the Generate Procedure Amount Report MenuItem
	 * 
	 * @author Kyle Williamson R00110793
	 *
	 */
	class GenProcAmountReportListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Calls {@link generateProcedureAmountReportt()}
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				success = generateProcedureAmountReport();
				if(!success) {
					JOptionPane.showMessageDialog(null, "Couldn't generate report.");
				} else {
					JOptionPane.showMessageDialog(null, "Report generated.");
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	/**
	 * Nested class which provides an interface for receiving action events from the Display Report JButton
	 * 
	 * @author Kyle Williamson R00110793
	 *
	 */
	class DisplayReportListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Calls {@link displayReport()}
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				success = displayReport();
				if(!success) {
					JOptionPane.showMessageDialog(null, "Couldn't display report.");
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the Display Owed Report JButton
	 * 
	 * @author Kyle Williamson R00110793
	 *
	 */
	class DisplayOwedReportListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Calls {@link displayOwedReport()}
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				success = displayOwedReport();
				if(!success) {
					JOptionPane.showMessageDialog(null, "Couldn't display report.");
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Nested class which provides an interface for receiving action events from the Display Proc Amount Report JButton
	 * 
	 * @author Kyle Williamson R00110793
	 *
	 */
	class DisplayProcAmountReportListener implements ActionListener {
		
		/**
		 * Implemented abstract method which is executed when an action event is received
		 * 
		 * Calls {@link displayOwedReport()}
		 */
		public void actionPerformed(ActionEvent e) {
			try {
				boolean success = false;
				success = displayProcCountReport();
				if(!success) {
					JOptionPane.showMessageDialog(null, "Couldn't display report.");
				}
			} catch(Exception ex) {
				JOptionPane.showMessageDialog(null, "Error Occurred.");
			}
		}
	}
	
	/**
	 * Method which generates a report of all model data in the system
	 * Uses FileWriter, BufferedWriter and PrintWriter io classes
	 * 
	 * @return true if successful, false if failed
	 */
	public boolean generateReport() {
		try {
			FileWriter fw = new FileWriter("CompleteReport.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			for(Procedure current: getProcedureList()) {
				pw.println("Procedure: No." + current.getProcNo() 
						+ " Name: " + current.getProcName() 
						+ " Cost: " + current.getProcCost());
			}
			pw.println();
			printPatientList(pw, getPatientList());
			pw.close();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Method which generates a report of all Patients who owe money and have not made a payment within 6 months
	 * The list is printed in order of the highest amount owed
	 * 
	 * @return true if successful, false if failed
	 */
	public boolean generateOwedReport() {
		try {
			FileWriter fw = new FileWriter("OwedReport.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			ArrayList<Patient> sortedList = sortListByAmountOwed(findInDept());
			printPatientList(pw, sortedList);
			pw.close();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Method which generates report a report of all Procedures and the amount of times they have been performed
	 * 
	 * @return true if successful, false if failed
	 */
	public boolean generateProcedureAmountReport() {
		try {
			FileWriter fw = new FileWriter("ProcedureAmountReport.txt");
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter pw = new PrintWriter(bw);
			Map<String, Integer> map = generateProcCountList();
			
			pw.println("Procedures: \tAmount:");
			for ( Map.Entry<String, Integer> entry : map.entrySet() ) {
				pw.println(entry.getKey() + "\t" + entry.getValue());
			}
			pw.close();
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Method which generates a list of Patients who owe money and have not made a payment in 6 months 
	 *
	 * @return patientInDept generated Patient list
	 */
	public ArrayList<Patient> findInDept() {
		boolean deptFlag = false;
		boolean recentlyPaidFlag = false;
		ArrayList<Patient> patientInDept = new ArrayList<Patient>();
		for(Patient current: getPatientList()) {
			if(current.getP_paymentList().isEmpty()) {
				patientInDept.add(current);
			}
			for(Payment currentPayment: current.getP_paymentList()) {
				int monthDiff = checkMonth(currentPayment.getPaymentDate());
				if(!currentPayment.isPaid() && monthDiff <= -6) {
					deptFlag = true;
				} else if(currentPayment.isPaid() && monthDiff >= -6) {
					recentlyPaidFlag = true;
				}
			}
			if(deptFlag && !recentlyPaidFlag) {
				patientInDept.add(current);
			}
		}
		
		return patientInDept;
	}
	
	/**
	 * Method which compares a passed in Date to the Date 6 Months to the current date
	 * 
	 * @param date util.Date object 
	 * @return integer containing the month difference between the passed in Date and the Date 6 months ago
	 */
	public int checkMonth(Date date) {
		int paymentPeriod = 6;

		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(date);
		
		GregorianCalendar sixMonthsAgo = new GregorianCalendar();
		sixMonthsAgo.setTime(new Date());
		sixMonthsAgo.add(GregorianCalendar.MONTH, -paymentPeriod);
		
		int diffYear = sixMonthsAgo.get(Calendar.YEAR) - gc.get(Calendar.YEAR);
		int diffMonth = diffYear * 12 + sixMonthsAgo.get(Calendar.MONTH) - gc.get(Calendar.MONTH);
		
	    return diffMonth;
	}
	
	/**
	 * A Method which sorts a Patient ArrayList the Patients amount owed
	 * 
	 * @param list Patient ArrayList
	 * @return Sorted Patient ArrayList by Amount Owed
	 */
	public ArrayList<Patient> sortListByAmountOwed(ArrayList<Patient> list) {
		if(list.size() > 1) {
			for(int i = 0; i< list.size(); i++) {
				for(int j = 0; j < list.size(); i++) {
					Patient current = list.get(j);
					if(list.get(j).getAmountOwed() < list.get(j+1).getAmountOwed()) {
						list.remove(j);
						list.add(j+1, current);
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * Method which displays report on GUI
	 * 
	 * @return true if successful, false if failed
	 */
	public boolean displayReport() {
		try {
			JTextArea viewTextArea = getView().getReportsView().getTextArea();	
			viewTextArea.setText(null);
			displayPatientList(viewTextArea, getPatientList());
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Method that displays owed report on GUI
	 * 
	 * @return true if successful, false if failed
	 */
	public boolean displayOwedReport() {
		try {
			JTextArea viewTextArea = getView().getReportsView().getTextArea();	
			viewTextArea.setText(null);
			ArrayList<Patient> sortedList = sortListByAmountOwed(findInDept());
			displayPatientList(viewTextArea, sortedList);
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * Method that displays procedure count report on GUI
	 * 
	 * @return true if successful, false if failed
	 */
	public boolean displayProcCountReport() {
		try {
			JTextArea viewTextArea = getView().getReportsView().getTextArea();
			Map<String, Integer> map = generateProcCountList();
			viewTextArea.setText(null);
			viewTextArea.append("Procedures: \tAmount:\n");
			for ( Map.Entry<String, Integer> entry : map.entrySet() ) {
				viewTextArea.append(entry.getKey() + "\t" + entry.getValue() + "\n");
			}
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	/**
	 * Method that generates a map of each procedure and the amount of times it has been performed
	 * 
	 * @return Map<String, Integer> String of procName, Integer being count of times procedure has been performed
	 */
	private Map<String, Integer> generateProcCountList() {
		Map<String, Integer> map = new HashMap<String, Integer>();
		int countStore = 0;
		
		for(Procedure current : getProcedureList()) {
			countStore = 0;
			for(Patient currentPat: getPatientList()) {
				countStore += compareProcedures(current, currentPat);
				
			}
			map.put(current.getProcName(), countStore);
		}
		return map;
	}
	
	/**
	 * Method that counts the amount of times a Patient has had a particular Procedure
	 * 
	 * @param current current Procedure to be compared to
	 * @param currentPat current Patient to loop through procedures
	 * @return int count amount of times Patient has had current Procedure
	 */
	private int compareProcedures(Procedure current, Patient currentPat) {
		int procCount = 0;
		for(Procedure patProc : currentPat.getP_procList()) {
			if(patProc.getProcNo() == current.getProcNo()){
				procCount++;
			}
		}
		return procCount;
	}
	
	/**
	 * Method that prints out a Patient List to a files
	 * @param pw PrintWriter 
	 * @param list Patient ArrayList
	 */
	private void printPatientList(PrintWriter pw, ArrayList<Patient> list) {
		for(Patient current: list) {
			pw.println("Patient:" + current.getPatientNo());
			pw.println(current.getPatientName());
			pw.println(current.getPatientAdd());
			pw.println(current.getPatientPhone());
			pw.println("Procedures:");
			for(Procedure currentProc: current.getP_procList()) {
				pw.println("\tProcedure:" +currentProc.getProcNo());
				pw.println("\t" +currentProc.getProcName());
				pw.println("\t" +currentProc.getProcCost());
				pw.println();
			}
			pw.println("Payments:");
			for(Payment currentPayment: current.getP_paymentList()) {
				pw.println("\tPayment:" +currentPayment.getPaymentNo());
				pw.println("\t" +currentPayment.getPaymentAmt());
				pw.println("\t" +currentPayment.getPaymentDate());
				pw.println("\t" +currentPayment.isPaid());
				pw.println();
			}
			pw.println();
		}
	}
	
	/**
	 * Method that appends a Patient List to a given JTextArea
	 * @param viewTextArea JTextArea
	 * @param list Patient ArrayList
	 */
	private void displayPatientList(JTextArea viewTextArea, ArrayList<Patient> list) {
		for(Patient current: list) {
			viewTextArea.append("Patient:" + current.getPatientNo());
			viewTextArea.append("\n" + current.getPatientName());
			viewTextArea.append("\n" + current.getPatientAdd());
			viewTextArea.append("\n" + current.getPatientPhone());
			viewTextArea.append("\n" + "Procedures:");
			for(Procedure currentProc: current.getP_procList()) {
				viewTextArea.append("\n\tProcedure:" +currentProc.getProcNo());
				viewTextArea.append("\n\t" +currentProc.getProcName());
				viewTextArea.append("\n\t" +currentProc.getProcCost() + "\n");
			}
			viewTextArea.append("\n" + "Payments:");
			for(Payment currentPayment: current.getP_paymentList()) {
				viewTextArea.append("\n\tPayment:" +currentPayment.getPaymentNo());
				viewTextArea.append("\n\t" +currentPayment.getPaymentAmt());
				viewTextArea.append("\n\t" +currentPayment.getPaymentDate());
				viewTextArea.append("\n\t" +currentPayment.isPaid() + "\n");
			}
			viewTextArea.append("\n");
		}
	}
}
