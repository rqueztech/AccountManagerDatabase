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
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.rqueztech.ui.BaseFrame;
import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.buttons.ButtonTemplates;
import com.rqueztech.ui.passwordfields.PasswordFieldTemplates;
import com.rqueztech.ui.textfields.TextfieldTemplates;

public class SetupConfigurationPanel extends JPanel {
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private Image image;
	private GridBagConstraints grid;

	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 0;
	private final int RIGHT_INSET = 0;
	
	// --- Section 1: Username Component Keys
	private final String FIRSTNAME_LABEL_KEY = "FIRSTNAME_LABEL_KEY";
	private final String FIRSTNAME_TEXTFIELD_KEY = "FIRSTNAME_TEXTFIELD_KEY";
	
	// --- Section 2: Password Component Keys
	private final String LASTNAME_LABEL_KEY = "LASTNAME_LABEL_KEY";
	private final String LASTNAME_TEXTFIELD_KEY = "LASTNAME_TEXTFIELD_KEY";
	
	// --- Section 3: Login Button Component Keys
	private final String EXIT_BUTTON_KEY = "EXIT_BUTTON_KEY";
	private final String SUBMIT_BUTTON_KEY = "SUBMIT_BUTTON_KEY";
	
	private final String PASSPHRASE_LABEL_KEY = "PASSPHRASE_LABEL_KEY";
	private final String PASSPHRASE_TEXTFIELD_KEY = "PASSPHRASE_TEXTFIELD_KEY";
	private final String PASSPHRASE_VISIBILITY_BUTTON_KEY = "PASSPHRASE_VISIBILITY_BUTTON_KEY";
	
	private final String CONFIRM_PASSPHRASE_LABEL_KEY = "CONFIRM_PASSPHRASE_LABEL_KEY";
	private final String CONFIRM_PASSPHRASE_TEXTFIELD_KEY = "CONFIRM_PASSPHRASE_TEXTFIELD_KEY";
	private final String CONFIRM_PASSPHRASE_VISIBILITY_BUTTON_KEY = "CONFIRM_PASSPHRASE_VISIBILITY_BUTTON_KEY";
	
	private final String PASSWORD_LABEL_KEY = "PASSWORD_LABEL_KEY";
	private final String PASSWORD_TEXTFIELD_KEY = "PASSWORD_TEXTFIELD_KEY";
	private final String PASSWORD_VISIBILITY_BUTTON_KEY = "PASSWORD_VISIBILITY_BUTTON_KEY";
	
	private final String CONFIRM_PASSWORD_LABEL_KEY = "CONFIRM_PASSWORD_LABEL_KEY";
	private final String CONFIRM_PASSWORD_TEXTFIELD_KEY = "CONFIRM_PASSWORD_TEXTFIELD_KEY";
	private final String CONFIRM_PASSWORD_VISIBILITY_BUTTON_KEY = "CONFIRM_PASSWORD_VISIBILITY_BUTTON_KEY";
	
	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;
	
	private int GRID_X_CURRENT = 0;
	private int GRID_Y_CURRENT = 0;
	
	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEWEIGHT = 1;
	
	private final int TEXTFIELD_SIZE = 15;
	
	private PanelCentral panelCentral;
	
	// --- Group 2: Panel Map ---
	private ConcurrentHashMap <String, JComponent> components;
	private SetupConfigurationController setupConfigurationController;
	
	// --------------------------------------------------------------------------------------
	public SetupConfigurationPanel(BaseFrame frame, GridBagLayout layout, PanelCentral panelCentral) {
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
	        
	        // --- Set the initial values for the grid
	        this.grid.fill = GridBagConstraints.HORIZONTAL;
	        this.grid.anchor = GridBagConstraints.CENTER;
	        this.grid.insets = new Insets(2, 2, 2, 2);
	        this.grid.gridx = GRID_X_INITIAL;
	        this.grid.gridy = GRID_Y_INITIAL;
	        
	        this.setLabelField(FIRSTNAME_LABEL_KEY, "Enter FirstName");
	        this.setTextField(FIRSTNAME_TEXTFIELD_KEY);
	        
	        this.setLabelField(LASTNAME_LABEL_KEY, "Enter LastName");
	        this.setTextField(LASTNAME_TEXTFIELD_KEY);
	        
	        this.setLabelField(PASSPHRASE_LABEL_KEY, "Enter Passphrase");
	        this.setPasswordField(PASSPHRASE_TEXTFIELD_KEY);
	        
	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
	        this.setButton(PASSPHRASE_VISIBILITY_BUTTON_KEY, "Visible");
	        this.add(this.components.get(PASSPHRASE_VISIBILITY_BUTTON_KEY), grid);
	        
	        this.setLabelField(CONFIRM_PASSPHRASE_LABEL_KEY, "Confirm Passphrase");
	        this.setPasswordField(CONFIRM_PASSPHRASE_TEXTFIELD_KEY);
	        
	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
	        this.setButton(CONFIRM_PASSPHRASE_VISIBILITY_BUTTON_KEY, "Visible");
	        this.add(this.components.get(CONFIRM_PASSPHRASE_VISIBILITY_BUTTON_KEY), grid);
	        
	        this.setLabelField(PASSWORD_LABEL_KEY, "Enter New Password");
	        this.setPasswordField(PASSWORD_TEXTFIELD_KEY);
	        
	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
	        this.setButton(PASSWORD_VISIBILITY_BUTTON_KEY, "Visible");
	        this.add(this.components.get(PASSWORD_VISIBILITY_BUTTON_KEY), grid);
	        
	        this.setLabelField(CONFIRM_PASSWORD_LABEL_KEY, "Confirm New Password");
	        this.setPasswordField(CONFIRM_PASSWORD_TEXTFIELD_KEY);
	        
	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
	        this.setButton(CONFIRM_PASSWORD_VISIBILITY_BUTTON_KEY, "Visible");
	        this.add(this.components.get(CONFIRM_PASSWORD_VISIBILITY_BUTTON_KEY), grid);
	        
	        this.grid.fill = GridBagConstraints.HORIZONTAL;
	        this.grid.anchor = GridBagConstraints.EAST;
	        this.grid.gridx = GRID_X_CURRENT; // Buttons are not fixed, therefore coordinates are custom set
	        this.grid.gridy += GRID_Y_CURRENT;
	        this.setButton(EXIT_BUTTON_KEY, "Cancel");
	        this.add(this.components.get(EXIT_BUTTON_KEY), grid);
	        
	        this.grid.anchor = GridBagConstraints.WEST;
	        this.grid.gridx += 1;
	        this.setAdminButton(SUBMIT_BUTTON_KEY, "Submit");
	        this.add(this.components.get(SUBMIT_BUTTON_KEY), grid);
	        
	        this.setupConfigurationController = new SetupConfigurationController(this);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void setBackgroundImageConstraints() {
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
	private void setButton(String buttonKey, String buttonText) {
		
		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
        //this.grid.anchor = GridBagConstraints.CENTER;
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(buttonKey, button);
	}
	
	// --------------------------------------------------------------------------------------
	private void setAdminButton(String buttonKey, String buttonText) {
		
		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
        //this.grid.anchor = GridBagConstraints.CENTER;
		button.setEnabled(false);
        
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        button.setOpaque(false);
        
        this.components.put(buttonKey, button);
	}
	
	// --------------------------------------------------------------------------------------
	public void setTextField(String textFieldKey) {
		TextfieldTemplates textField = new TextfieldTemplates(Color.WHITE, Color.BLACK, TEXTFIELD_SIZE);
		
		//this.grid.anchor = GridBagConstraints.CENTER;
		//textField.setFont(new Font("Arial", Font.PLAIN, 14));
		this.grid.gridwidth = 2;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        this.grid.gridx = GRID_X_INITIAL;
		this.GRID_Y_CURRENT += 1;
        this.grid.gridy += GRID_Y_CURRENT;
        
        this.components.put(textFieldKey, textField);
        this.add(this.components.get(textFieldKey), grid);
	}
	
	// --------------------------------------------------------------------------------------
	public void setPasswordField(String passwordFieldKey) {
		PasswordFieldTemplates passwordField = new PasswordFieldTemplates(Color.WHITE, Color.BLACK, TEXTFIELD_SIZE);
		
		this.grid.gridwidth = 2;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        this.grid.gridx = GRID_X_INITIAL;
        this.GRID_Y_CURRENT += 1;
        this.grid.gridy += GRID_Y_CURRENT;
        
        this.components.put(passwordFieldKey, passwordField);
        this.add(this.components.get(passwordFieldKey), grid);
	}
	
	// --------------------------------------------------------------------------------------
	private void setLabelField(String labelKey, String labelText) {
		JLabel labelField = new JLabel(labelText);
		//this.grid.anchor = GridBagConstraints.CENTER;
		labelField.setBackground(Color.BLACK);
		labelField.setForeground(Color.WHITE);
		this.grid.gridwidth = 2;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        this.grid.gridx = GRID_X_INITIAL;
        this.GRID_Y_CURRENT += 1;
        this.grid.gridy += 1;
        
        this.components.put(labelKey, labelField);
        this.add(this.components.get(labelKey), grid);
	}
	
	// --------------------------------------------------------------------------------------
	public PanelCentral getPanelCentral() {
		return this.panelCentral;
	}
	
	// --------------------------------------------------------------------------------------
	public ConcurrentHashMap<String, JComponent> getComponentsMap() {
		return this.components;
	}
	
	// --------------------------------------------------------------------------------------
	@Override
	public void paintComponent(Graphics g) {
	    
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
	}
}
