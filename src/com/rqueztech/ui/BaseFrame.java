package com.rqueztech.ui;

import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class BaseFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5468259630596736460L;
	
	// --- Frame Size ---
	public static final int WIDTH = 600;
	public static final int HEIGHT = 600;
	
	public BaseFrame() {
		SwingUtilities.invokeLater(() -> {
			this.setSize(WIDTH, HEIGHT);
			this.setLayout(new GridBagLayout());
			this.setLocationRelativeTo(null);
			this.setTitle("Database Project");
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		});
	}
}
