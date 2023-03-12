package com.rqueztech.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MainLoginPanel extends JPanel {
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195207L;
	private JFrame frame;
	private Image image;
	private GridBagConstraints grid;
	private HashMap <String, JComponent> components;
	
	public MainLoginPanel(JFrame frame) {
		this.components = new HashMap <String, JComponent> ();
		this.setLayout(new GridBagLayout());
		this.grid = new GridBagConstraints();
		
		this.frame = frame;
		this.image = new ImageIcon("backgroundd.jpg").getImage();
		this.frame.add(this);
		
		SwingUtilities.invokeLater(() -> {
			this.grid.insets = new Insets(0, 0, 2, 0);
			this.grid.gridx = 0;
			this.grid.gridy = 0;
			
			this.addLabel("UserName", 0);
			this.addTextField("UserNameEntry", 0, 15);
			
			this.addLabel("Password",0);
			this.addTextField("PasswordEntry", 0, 15);
			
			this.addRightButton("User", 0);
			this.addLeftButton("Admin", 1);
		});
	}
	
	private void addLeftButton(String leftButtonName, int xCoordinate) {
		JButton leftButton = new JButton(leftButtonName);
		
		leftButton.setForeground(Color.GRAY);
		leftButton.setBackground(Color.BLACK);
		
		this.grid.gridx = xCoordinate;
		this.grid.anchor = GridBagConstraints.WEST;
		this.components.put(leftButtonName, leftButton);
		
		this.add(leftButton, grid);
	}
	
	private void addRightButton(String rightButtonName, int xCoordinate) {
		JButton rightButton = new JButton(rightButtonName);
		rightButton.setForeground(Color.GRAY);
		rightButton.setBackground(Color.BLACK);
		
		this.grid.gridwidth = 1;
		this.grid.gridx = xCoordinate;
		this.grid.anchor = GridBagConstraints.EAST;
		this.grid.fill = GridBagConstraints.HORIZONTAL;
		this.components.put(rightButtonName, rightButton);
		
		this.add(rightButton, grid);
	}
	
	private void addLabel(String labelName, int xCoordinate) {
		JLabel label = new JLabel(labelName);
		label.setForeground(Color.WHITE);
		
		this.grid.gridwidth = 1;
		this.grid.gridx = xCoordinate;
		this.grid.anchor = GridBagConstraints.WEST;
		this.components.put(labelName, label);
		
		this.add(label, grid);
		this.grid.gridy += 1;
	}
	
	private void addTextField(String textFieldName, int xCoordinate, int size) {
		JTextField textField = new JTextField(size); // Set Object (textfield) to size
		textField.setForeground(Color.WHITE); // Set the color
		
		this.grid.gridwidth = 2;
		this.grid.gridx = xCoordinate;
		this.grid.anchor = GridBagConstraints.WEST;
		this.components.put(textFieldName, textField);
		
		this.add(textField, grid); // Add to the current grid
		this.grid.gridy += 1; // Append by one for the next element in use
	}
	
	 
	
	@Override
	public void paintComponent(Graphics g) {
	    super.paintComponent(g);
		g.drawImage(image, 0, 0, null);
	}
}
