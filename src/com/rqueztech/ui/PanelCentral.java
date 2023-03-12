package com.rqueztech.ui;

import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class PanelCentral extends JFrame {
	
	// --- Group 1: Panel Related Variables ---
	private static final long serialVersionUID = -652692111395861275L;
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	public Image image;
	
	// --- Group 2: Panel Key Variables ---
	private static final String MAIN_LOGIN_PANEL = "MAIN_LOGIN_PANEL";
	
	// --- Group 3: Panel Selector Map ---
	private HashMap <String, JPanel> panelSelector
		= new HashMap <String, JPanel>();
	
	public PanelCentral() {
		// TODO Auto-generated constructor stub
		this.isInvokeGUI();
		
		this.panelSelector.put(MAIN_LOGIN_PANEL, new MainLoginPanel(this)); // this = Current JFrame instance
		
		SwingUtilities.invokeLater(() -> {
			this.isInvokeGUI();
			
			// Temp?
			this.panelSelector.get(MAIN_LOGIN_PANEL);
		});
	}
	
	// --- Group 4: GUI Setup Methods ---
	private void isInvokeGUI() {
		this.setSize(WIDTH, HEIGHT);
		this.setLocationRelativeTo(null);
		this.setTitle("Database Project");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	// --- Group 5: Accessor Methods ---
	public HashMap<String, JPanel> getHashMap() {
		return this.panelSelector;
	}
	
	
}
