package com.rqueztech.ui.configuration;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
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
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class SetupConfigurationPanel extends JPanel {
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private JFrame frame;
	private Image image;
	private GridBagConstraints grid;

	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 2;
	private final int RIGHT_INSET = 0;
	private final int GRID_X = 0;
	private final int GRID_Y = 0;
	private final int GRID_Y_INCREMENT = 1;
	private final int TEXTFIELD_LENGTH = 15;
	
	// --- Group 2: Panel Map ---
	private HashMap <String, JComponent> components;
	
	public SetupConfigurationPanel(JFrame frame) {
		
		
		this.components = new HashMap <String, JComponent> ();
		this.setLayout(new GridBagLayout());
		this.grid = new GridBagConstraints();
		
		this.frame = frame;
		this.image = new ImageIcon("background.jpg").getImage();
		this.setPreferredSize(new Dimension(600, 600));
		this.frame.add(this);
		
		// Dispatch responsibilities on EDT.
		SwingUtilities.invokeLater(() -> {
			this.setMainLoginComponents();
		});
	}
	
	// --- Group 3: Component Calls (Add Into Panel)
	private void setMainLoginComponents() {
		this.grid.insets = new Insets(TOP_INSET, LEFT_INSET, BOTTOM_INSET, RIGHT_INSET);
		this.grid.gridx = GRID_X;
		this.grid.gridy = GRID_Y;
		
		// -> FirstName Rows. FirstName label/password
		this.addLabel("FirstName", GRID_X);
		this.addTextField("FirstNameEntry", GRID_X);
		
		// -> LastName Rows. LastName label/password
		this.addLabel("LastName",GRID_X);
		this.addTextField("LastNameEntry", GRID_X);
		
		// -> Password Rows. Password label/password
		this.addLabel("Password",GRID_X);
		this.addPasswordField("PasswordEntry", GRID_X);
		
		// -> ConfirmPassword Rows. ConfirmPassword label/password
		this.addLabel("ConfirmPassword",GRID_X);
		this.addPasswordField("ConfirmPasswordEntry", GRID_X);
		
		// -> PassphraseEntry Rows. PassphraseEntry label/password
		this.addLabel("PassphraseEntry",GRID_X);
		this.addPasswordField("PassphraseEntryEntry", GRID_X);
		
		// -> User/Admin Buttons. Individual row.
		this.addLeftButton("Cancel", GRID_X);
		this.addRightButton("Submit", GRID_X + 1);
		
	}
	
	// --- Group 4: Panel Components ---
	private void addTextField(String textFieldName, int xCoordinate) {
		JTextField textField = new JTextField(TEXTFIELD_LENGTH); // Set Object (textfield) to size
		textField.setForeground(Color.BLACK); // Set the color
		
		this.grid.gridwidth = 2;
		this.grid.gridx = xCoordinate;
		this.grid.anchor = GridBagConstraints.WEST;
		this.grid.fill = GridBagConstraints.HORIZONTAL;
		this.components.put(textFieldName, textField);
		
		this.add(textField, this.grid); // Add to the current grid
		this.grid.gridy += GRID_Y_INCREMENT; // Append by one for the next element in use
	}
	
	private void addPasswordField(String passwordFieldName, int xCoordinate) {
		JPasswordField passwordField = new JPasswordField(TEXTFIELD_LENGTH); // Set Object (passwordfield) to size
		passwordField.setForeground(Color.BLACK); // Set the color
		
		this.grid.gridwidth = 2;
		this.grid.gridx = xCoordinate;
		this.grid.anchor = GridBagConstraints.WEST;
		this.grid.fill = GridBagConstraints.HORIZONTAL;
		this.components.put(passwordFieldName, passwordField);
		
		passwordField.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(new String(passwordField.getPassword()).equals("p")) {
					SetupConfigurationPanel.this.components.get("Submit").setBackground(Color.GRAY);
				}
				
				else {
					SetupConfigurationPanel.this.components.get("Submit").setBackground(Color.BLACK);
				}
			}
			
			// Document listener 
			@Override
			public void insertUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(new String(passwordField.getPassword()).equals("p")) {
					SetupConfigurationPanel.this.components.get("Submit").setBackground(Color.GRAY);
				}
				
				else {
					SetupConfigurationPanel.this.components.get("Submit").setBackground(Color.BLACK);
				}
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				// TODO Auto-generated method stub
				if(new String(passwordField.getPassword()).equals("p")) {
					SetupConfigurationPanel.this.components.get("Submit").setBackground(Color.GRAY);
				}
				
				else {
					SetupConfigurationPanel.this.components.get("Submit").setBackground(Color.BLACK);
				}
			}
		});
				
		this.add(passwordField, this.grid); // Add to the current grid
		this.grid.gridy += GRID_Y_INCREMENT; // Append by one for the next element in use
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
		this.grid.gridy += GRID_Y_INCREMENT;
	}
	
	private void addLeftButton(String leftButtonName, int xCoordinate) {
		JButton leftButton = new JButton(leftButtonName);
		
		leftButton.setForeground(Color.GRAY);
		leftButton.setBackground(Color.BLACK);
		
		this.grid.gridwidth = 1;
		this.grid.gridx = xCoordinate;
		this.grid.anchor = GridBagConstraints.WEST;
		this.grid.fill = GridBagConstraints.HORIZONTAL;
		this.components.put(leftButtonName, leftButton);
		
		this.add(leftButton, this.grid);
	}
	
	private void addRightButton(String rightButtonName, int xCoordinate) {
		JButton rightButton = new JButton(rightButtonName);
		rightButton.setForeground(Color.GRAY);
		rightButton.setBackground(Color.BLACK);
		
		this.grid.gridwidth = 1;
		this.grid.gridx += 1;
		this.grid.anchor = GridBagConstraints.EAST;
		this.grid.fill = GridBagConstraints.HORIZONTAL;
		this.components.put(rightButtonName, rightButton);
		
		this.add(rightButton, this.grid);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(this.image, 0, 0, null);
	}
}
