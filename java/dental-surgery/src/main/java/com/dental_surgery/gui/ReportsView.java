package com.dental_surgery.gui;

import java.awt.FlowLayout;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ReportsView extends JPanel {

	private static final long serialVersionUID = -7540746067769699331L;
	
	private JButton displayReport;
	private JButton displayOwedReport;
	private JButton displayProcAmountReport;
	
	private JTextArea reportView;
	private JScrollPane scrollPane;

	public ReportsView() {
		super();
		initializeComponents();
		
		createView();
	}
	
	/**
	 * Method that initializes component objects used
	 */
	private void initializeComponents() {
		displayReport = new JButton("Display Report");
		displayOwedReport = new JButton("Display Owed Report");
		displayProcAmountReport = new JButton("Display Procedure Amount Report");
		reportView = new JTextArea(40, 100);
		scrollPane = new JScrollPane(reportView);
		
		reportView.setEditable(false);
	}
	
	/**
	 * Method that constructs the view by adding layouts and components
	 */
	private void createView() {
		
		//Creating additional JPanels
		JPanel top = new JPanel();
		JPanel bot = new JPanel();

		//Layouts are given to additional JPanels
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		top.setLayout(new FlowLayout());
		bot.setLayout(new FlowLayout());
		
		//Components are added according to their desired position in the GUI
		top.add(displayReport);
		top.add(displayOwedReport);
		top.add(displayProcAmountReport);
		
		bot.add(scrollPane);
		
		this.add(top);
		this.add(bot);
		
	}
	
	// Get and Set Methods
	
	public JTextArea getTextArea() {
		return reportView;
	}
	
	
	//Action Listeners
	public void addDisplayReportListener(ActionListener listenForDisplayReport) {
		displayReport.addActionListener(listenForDisplayReport);
	}
	
	public void addDisplayOwedReportListener(ActionListener listenForDisplayOwedReport) {
		displayOwedReport.addActionListener(listenForDisplayOwedReport);
	}	
	
	public void addDisplayProcAmountReportListener(ActionListener listenForDisplayProcAmountReport) {
		displayProcAmountReport.addActionListener(listenForDisplayProcAmountReport);
	}
	
}
