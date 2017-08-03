package com.dental_surgery.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 * Custom JMenuBar Class for DentalSurgeryView JFrame
 * 
 * @author Kyle Williamson R00110793
 * 
 * @Extends {@link JMenuBar}
 */
public class MenuBar extends JMenuBar {

	private static final long serialVersionUID = 2610996006928875015L;

	private JMenu fileMenu;
	private JMenu reportMenu;
	
	private JMenuItem saveData;
	private JMenuItem genReport;
	private JMenuItem genDeptReport;
	private JMenuItem genProcAmountReport;
	private JMenuItem quit;
	
	/**
	 * GenericMenuBar Constructor
	 * 
	 * Adds JMenu Objects to self
	 * Adds JMenuItems to JMenu Object
	 */
	public MenuBar() {
		super();
		
		initializeComponents();

		createView();
		
		addQuitListener();
	}
	
	/**
	 * Method that initializes components objects used
	 */
	private void initializeComponents() {
		fileMenu = new JMenu("File");
		reportMenu = new JMenu("Reports");
		
		saveData = new JMenuItem("Save");
		genReport = new JMenuItem("Generate Report");
		genDeptReport = new JMenuItem("Generate Dept Report");
		genProcAmountReport = new JMenuItem("Generate Procedure Amount Report");
		quit = new JMenuItem("Quit");
	}
	
	/**
	 * Method that creates menu bar with menu items
	 */
	private void createView() {
		this.add(fileMenu);
		this.add(reportMenu);
		
		fileMenu.add(saveData);
		fileMenu.add(quit);
		
		reportMenu.add(genReport);
		reportMenu.add(genDeptReport);
		reportMenu.add(genProcAmountReport);
	}
	
	// ActionListeners
	
	public void addSaveDataListener(ActionListener listenForSaveData) {
		saveData.addActionListener(listenForSaveData);
	}
	
	public void addGenReportListener(ActionListener listenForGenReport) {
		genReport.addActionListener(listenForGenReport);
	}
	
	public void addGenOwedReportListener(ActionListener listenForGenOwedReport) {
		genDeptReport.addActionListener(listenForGenOwedReport);
	}
	
	public void addGenProcAmountReportListener(ActionListener listenForGenProcAmountReport) {
		genProcAmountReport.addActionListener(listenForGenProcAmountReport);
	}
	
	public void addQuitListener() {
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int choice = JOptionPane.showConfirmDialog(null, "Do you want to quit without saving?");
				
				if(choice == JOptionPane.YES_OPTION) {
					System.exit(0);
				}
			}
		});
	}
}
