package com.dental_surgery.gui;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Class which provides common functionality for all custom JPanels
 * @author Kyle Williamson R00110793
 *
 */
public abstract class BaseJPanel extends JPanel {

	private static final long serialVersionUID = -8093928541378576574L;
	private JButton addBtn;
	private JButton removeBtn;
	private JButton listBtn;
	
	public BaseJPanel(String addBtnText, String removeBtnText, String listBtnText) {
		addBtn = new JButton(addBtnText);
		removeBtn = new JButton(removeBtnText);
		listBtn = new JButton(listBtnText);
	}
	
	// Get and Set Methods
	
	public void setAddBtn(JButton addBtn) {
		this.addBtn = addBtn;
	}

	public JButton getAddBtn() {
		return addBtn;
	}
	
	public void setRemoveBtn(JButton removeBtn) {
		this.removeBtn = removeBtn;
	}
	
	public JButton getRemoveBtn() {
		return removeBtn;
	}

	public void setListBtn(JButton listBtn) {
		this.listBtn = listBtn;
	}
	
	public JButton getListBtn() {
		return listBtn;
	}
	
	// ActionListeners
	public void addAddBtnListener(ActionListener listenForAddBtn) {
		addBtn.addActionListener(listenForAddBtn);
	}
	public void addRemoveBtnListener(ActionListener listenForRemoveBtn) {
		removeBtn.addActionListener(listenForRemoveBtn);
	}
	public void addListBtnListener(ActionListener listenForListBtn) {
		listBtn.addActionListener(listenForListBtn);
	}
	
	// Abstract Methods
	public abstract void clearTextFields();
}
