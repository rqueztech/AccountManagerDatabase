package com.rqueztech.ui;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.rqueztech.ui.user.UserChangeDefaultPasswordPanel;

public class PanelCentral extends JFrame {
	
	// --- Group 1: Panel Related Variables ---
	private static final long serialVersionUID = -652692111395861275L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	public Image image;
	
	// --- Group 2: Panel Key Variables ---
	private final String MAIN_LOGIN_PANEL = "MAIN_LOGIN_PANEL";
	private final String USER_PASSWORD_REENTRY_PANEL = "USER_PASSWORD_REENTRY_PANEL"; 
	
	private String currentPanel;
	
	// --- Group 3: Panel Selector Map ---
	private HashMap <String, JPanel> panelSelector
		= new HashMap <String, JPanel>();
	
	public PanelCentral() {
		// TODO Auto-generated constructor stub
		this.panelSelector.put(MAIN_LOGIN_PANEL, new MainLoginPanel(this)); // this = Current JFrame instance
		this.add(this.panelSelector.get(MAIN_LOGIN_PANEL));
		
		this.panelSelector.put(USER_PASSWORD_REENTRY_PANEL, new UserChangeDefaultPasswordPanel(this)); // this = Current JFrame instance
		this.add(this.panelSelector.get(USER_PASSWORD_REENTRY_PANEL));
		
		//this.setInitialPanel();
		
		this.panelSelector.get(USER_PASSWORD_REENTRY_PANEL);
		isInvokeGUI();
		
	}
	
	private void setInitialPanel() {
	}
	
	// --- Group 4: GUI Setup Methods ---
	private void isInvokeGUI() {
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setTitle("Database Project");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	// --- Group 5: Accessor Methods ---
	public HashMap<String, JPanel> getHashMap() {
		return this.panelSelector;
	}
	
	private String initialPanel() {
		if(true) {
			this.currentPanel = "MAIN_LOGIN_PANEL";
		}
		
		return this.currentPanel;
	}
	
	public void showPanel(String panelName) {
		JPanel newPanel = panelSelector.get(panelName);
		String currentPanel = this.currentPanel;
		
		if(newPanel != null && panelName != currentPanel) {
			this.panelSelector.get(this.currentPanel).setVisible(false);
			this.currentPanel = panelName;
			this.panelSelector.get(this.currentPanel).setVisible(true);
		}
	}
}
