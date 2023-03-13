package com.rqueztech.ui.configuration;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class SetupAgreementPanel extends JPanel {
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private JFrame frame;
	private Image image;
	private GridBagConstraints grid;
	
	// --- Group 2: Panel Map ---
	private HashMap <String, JComponent> components;
	
	public SetupAgreementPanel(JFrame frame) {
		this.components = new HashMap <String, JComponent> ();
		this.setLayout(new GridBagLayout());
		this.grid = new GridBagConstraints();
		
		this.frame = frame;
		this.image = new ImageIcon("background.jpg").getImage();
		this.frame.add(this);
		
		// Dispatch responsibilities on EDT.
		SwingUtilities.invokeLater(() -> {
			this.setMainLoginComponents();
		});
	}
	
	// --- Group 3: Component Calls (Add Into Panel)
	private void setMainLoginComponents() {
		this.grid.insets = new Insets(0, 0, 2, 0);
		this.grid.gridx = 0;
		this.grid.gridy = 0;
		
		// -> User/Admin Buttons. Individual row.
		this.addLabel("Welcome To Initial Configuraion", 0);
		this.addAgreementButton("Start Configuration", 0);
	}
	
	private void addLabel(String labelName, int xCoordinate) {
		JLabel label = new JLabel(labelName);
		label.setForeground(Color.WHITE);
		
		this.grid.gridwidth = 2;
		this.grid.gridx = xCoordinate;
		this.grid.anchor = GridBagConstraints.WEST;
		this.grid.fill = GridBagConstraints.HORIZONTAL;
		this.components.put(labelName, label);
		
		this.add(label, this.grid);
		this.grid.gridy += 1;
	}
	
	private void addAgreementButton(String leftButtonName, int xCoordinate) {
		JButton leftButton = new JButton(leftButtonName);
		
		leftButton.setForeground(Color.GRAY);
		leftButton.setBackground(Color.BLACK);
		
		this.grid.gridwidth = 2;
		this.grid.gridx = xCoordinate;
		this.grid.anchor = GridBagConstraints.WEST;
		this.grid.fill = GridBagConstraints.HORIZONTAL;
		this.components.put(leftButtonName, leftButton);
		
		this.add(leftButton, this.grid);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
		g.drawImage(this.image, 0, 0, null);
	}
}
