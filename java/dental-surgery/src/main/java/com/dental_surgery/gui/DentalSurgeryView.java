package com.dental_surgery.gui;

import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 * JFrame class which contains all GUI Components
 * 
 * @author Kyle Williamson R00110793
 *
 * @Extends {@link JFrame}
 */
public class DentalSurgeryView extends JFrame {
	
	private static final long serialVersionUID = 2495440577252730578L;
	
	private MenuBar gMenuBar;
	private PatientJPanel patientPanel;
	private ProcedureJPanel procedurePanel;
	private PaymentJPanel paymentPanel;
	private ReportsView reportView;
	
	/**
	 * DentalSurgeryView Constructor
	 * Sets up JFrame Properties
	 * 
	 * Calls {@link createView()}
	 */
	public DentalSurgeryView() {
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setLocationRelativeTo(null);
		this.setTitle("Dental Surgery Form");
		
		setLookAndFeel();
		initializeComponents();
		createView();
	}
	
	// Get and Set Methods
	
	public void setMenuBar(MenuBar gMenuBar) {
		this.gMenuBar = gMenuBar;
	}

	public MenuBar getgMenuBar() {
		return gMenuBar;
	}

	public void setgMenuBar(MenuBar gMenuBar) {
		this.gMenuBar = gMenuBar;
	}

	public PatientJPanel getPatientPanel() {
		return patientPanel;
	}

	public void setPatientPanel(PatientJPanel patientPanel) {
		this.patientPanel = patientPanel;
	}

	public ProcedureJPanel getProcedurePanel() {
		return procedurePanel;
	}

	public void setProcedurePanel(ProcedureJPanel procedurePanel) {
		this.procedurePanel = procedurePanel;
	}

	public PaymentJPanel getPaymentPanel() {
		return paymentPanel;
	}

	public void setPaymentPanel(PaymentJPanel paymentPanel) {
		this.paymentPanel = paymentPanel;
	}
	
	public ReportsView getReportsView() {
		return reportView;
	}
	
	public void setReportsView(ReportsView reportView) {
		this.reportView = reportView;
	}
	
	/**
	 * Method that initializes component objects used
	 */
	private void initializeComponents() {
		gMenuBar = new MenuBar();
		patientPanel = new PatientJPanel();
		procedurePanel = new ProcedureJPanel();
		paymentPanel = new PaymentJPanel();
		reportView = new ReportsView();
		
	}
	
	/**
	 * Method which generates the content to be displayed
	 * Creates instances of GenericMenuBar,
	 * PatientJPanel, PaymentJPanel, ProcedureJPanel
	 */
	private void createView() {
		JTabbedPane tabbedPane = new JTabbedPane();
		
		this.setJMenuBar(gMenuBar);
		
		createTabs(tabbedPane);
		
		this.add(tabbedPane);
		
	}
	
	/**
	 * Method that creates each tab
	 * @param tabbedPane JTabbedPane
	 */
	private void createTabs(JTabbedPane tabbedPane) {
		tabbedPane.addTab("Patient", patientPanel);
		tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
		tabbedPane.addTab("Procedure", procedurePanel);
		tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
		tabbedPane.addTab("Payment", paymentPanel);
		tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
		tabbedPane.addTab("Reports", reportView);
		tabbedPane.setMnemonicAt(3, KeyEvent.VK_4);
	}
	
	/**
	 * Method that attempts to set the Look and Feel as the current OS
	 */
	private void setLookAndFeel() {
		try {
	        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	    } catch (UnsupportedLookAndFeelException e) {	
	    } catch (ClassNotFoundException e) {
	    } catch (InstantiationException e) {
	    } catch (IllegalAccessException e) {
	    }
	}
}
