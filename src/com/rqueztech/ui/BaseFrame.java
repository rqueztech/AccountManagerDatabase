package com.rqueztech.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BaseFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5468259630596736460L;
	
	// --- Frame Size ---
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	public BaseFrame() {
		this.setSize(WIDTH, HEIGHT);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setTitle("Database Project");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	public void addPanel(JPanel panel) {
		this.getContentPane().add(panel);
	}
}
