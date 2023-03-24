package com.rqueztech.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.rqueztech.ui.enums.PanelCentralEnums;

public class LogoutSuccessPanel extends JPanel {
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private Image image;
	private GridBagConstraints grid;

	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 0;
	private final int RIGHT_INSET = 0;
	
	// --- Section 3: Login Button Component Keys
	private final String LOGOUT_SUCCESS_BUTTON_KEY = "LOGOUT_SUCCESS_BUTTON_KEY";	
	private PanelCentral panelCentral;
	
	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;
	
	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEWEIGHT = 1;
	
	private final int TEXTFIELD_SIZE = 10;
	
	// --- Group 2: Panel Map ---
	private ConcurrentHashMap <String, JComponent> components;
	
	// --------------------------------------------------------------------------------------
	public LogoutSuccessPanel(BaseFrame frame, GridBagLayout layout, PanelCentral panelCentral) {
		// Function that will toggle visibility on and off in password
		// Field found in this class
		this.panelCentral = panelCentral; 
		
		// Dispatch responsibilities on EDT.
		SwingUtilities.invokeLater(() -> {
	        
			// Set the panel to the gridbaglayout, establish the preferred size,
			// And get the image that will be used in the background
			this.setLayout(layout);
	        this.setPreferredSize(new Dimension(frame.getHeight(), frame.getWidth()));
	        this.image = new ImageIcon("backgroundd.jpg").getImage();
	        this.components = new ConcurrentHashMap <String, JComponent> ();
	        
	        // --- Start Constraints ---
	        // Set all of the constraints for the background image
	        this.setBackgroundImageConstraints();
	        frame.add(this, this.grid);
	        //--- Finish Constraints End ---
	        
	        this.setComponentMainPosition();
	        
	        this.grid.gridx = 0; // Buttons are not fixed, therefore coordinates are custom set
	        this.setButton(LOGOUT_SUCCESS_BUTTON_KEY, "Logout Successful");
	        this.add(this.components.get(LOGOUT_SUCCESS_BUTTON_KEY), grid);
	        
	        this.logoutActionListener();
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void logoutActionListener() {
		JButton logoutButton = (JButton) this.components.get(LOGOUT_SUCCESS_BUTTON_KEY);
		
		logoutButton.addActionListener(e -> {
			this.setVisible(false);
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.MAIN_LOGIN_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void setComponentMainPosition() {
		this.grid.insets = new Insets(2, 2, 2, 2);
        this.grid.gridx = GRID_X_INITIAL;
        this.grid.gridy = GRID_Y_INITIAL;
	}
	
	// --------------------------------------------------------------------------------------
	// This will set the label down one
	public void setNewLabelPosition() {
		this.grid.gridx = 0;
        this.grid.gridy += 1;
	}
	
	// --------------------------------------------------------------------------------------
	public void setNewTextfieldPosition() {
		this.grid.gridx = 0;
		this.grid.gridy += 1;
	}
	
	// --------------------------------------------------------------------------------------
	public void setBackgroundImageConstraints() {
		// Set everything to initial status.
		this.grid = new GridBagConstraints(); // Set the gridbag constraints
        this.grid.fill = GridBagConstraints.BOTH; // Fill both vertically and horizontally
        this.grid.gridx = GRID_X_INITIAL;
        this.grid.gridx = GRID_Y_INITIAL;
        this.grid.weightx = GRIDX_IMAGEWEIGHT; // Value is 0: initial
        this.grid.weighty = GRIDY_IMAGEWEIGHT; // Value is 0: initial
        this.grid.insets = new Insets(TOP_INSET, LEFT_INSET, BOTTOM_INSET, RIGHT_INSET); // Insets values all 0 (Initial)
	}
	
	// --------------------------------------------------------------------------------------
	public void setButton(String buttonKey, String buttonText) {
		JButton button = new JButton(buttonText);
        this.grid.anchor = GridBagConstraints.CENTER;
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(buttonKey, button);
	}
	
	// --------------------------------------------------------------------------------------
	public void setTextField(String textFieldKey) {
		JTextField textField = new JTextField(TEXTFIELD_SIZE);
		this.grid.anchor = GridBagConstraints.CENTER;
		
		textField.setBackground(Color.WHITE);
		textField.setForeground(Color.BLACK);
		this.grid.gridwidth = 2;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(textFieldKey, textField);
	}
	
	// --------------------------------------------------------------------------------------
	public void setPasswordField(String passwordFieldKey) {
		JPasswordField passwordField = new JPasswordField(TEXTFIELD_SIZE);
		this.grid.anchor = GridBagConstraints.CENTER;
		passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
		passwordField.setBackground(Color.WHITE);
		passwordField.setForeground(Color.BLACK);
		this.grid.gridwidth = 2;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(passwordFieldKey, passwordField);
	}
	
	// --------------------------------------------------------------------------------------
	public void setLabelField(String labelKey, String labelText) {
		JLabel labelField = new JLabel(labelText);
		this.grid.anchor = GridBagConstraints.CENTER;
		labelField.setBackground(Color.BLACK);
		labelField.setForeground(Color.WHITE);
		this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(labelKey, labelField);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
	}
}
