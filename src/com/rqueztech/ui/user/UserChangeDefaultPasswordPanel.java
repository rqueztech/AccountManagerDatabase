package com.rqueztech.ui.user;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.rqueztech.ui.BaseFrame;
import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.buttons.ButtonTemplates;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.events.ChangePasswordDocumentListener;
import com.rqueztech.ui.events.PasswordValidationDocumentListener;
import com.rqueztech.ui.events.TogglePasswordVisibility;
import com.rqueztech.ui.passwordfields.PasswordFieldTemplates;

public class UserChangeDefaultPasswordPanel extends JPanel {
	
	private PanelCentral panelCentral;
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private Image image;
	private GridBagConstraints grid;

	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 0;
	private final int RIGHT_INSET = 0;
	
	// --- Section 1: Username Component Keys
	private final String ENTERPASSWORD_LABEL_KEY = "ENTERPASSWORD_LABEL_KEY";
	private final String ENTERPASSWORD_TEXTFIELD_KEY = "ENTERPASSWORD_TEXTFIELD_KEY";
	private final String ENTERPASSWORD_VISIBILITY_BUTTON_KEY = "ENTERPASSWORD_VISIBILITY_BUTTON_KEY";
	
	// --- Section 2: Password Component Keys
	private final String CONFIRMPASSWORDPASSWORD_LABEL_KEY = "CONFIRMPASSWORDPASSWORD_LABEL_KEY";
	private final String CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY = "CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY";
	private final String CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY = "CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY";
	
	// --- Section 3: Login Button Component Keys
	private final String CANCEL_CHANGE_BUTTON_KEY = "CANCEL_CHANGE_BUTTON_KEY";
	private final String SUBMIT_LOGIN_BUTTON_KEY = "SUBMIT_LOGIN_BUTTON_KEY";
	
	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;
	
	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEWEIGHT = 1;
	
	private final int TEXTFIELD_SIZE = 10;
	
	private TogglePasswordVisibility togglePasswordVisibility;
	
	// --- Group 2: Panel Map ---
	private ConcurrentHashMap <String, JComponent> components;
	
	// --------------------------------------------------------------------------------------
	public UserChangeDefaultPasswordPanel(BaseFrame frame, GridBagLayout layout, PanelCentral panelCentral) {
		// Function that will toggle visibility on and off in password
		// Field found in this class
		this.togglePasswordVisibility = new TogglePasswordVisibility();
		this.panelCentral = panelCentral;
		
		// Dispatch responsibilities on EDT.
		SwingUtilities.invokeLater(() -> {
	        
			this.setLayout(layout);	// Set the layout to the inherited Gridbag Layout (layout)
	        
			this.setPreferredSize(
					new Dimension(frame.getHeight(), frame.getWidth())); // Set the size to the 
	        																			// dimensions of the BaseFrame
	        this.image = new ImageIcon("backgroundd.jpg").getImage();
	        this.components = new ConcurrentHashMap <String, JComponent> ();
	        
	        // --- Start Constraints ---
	        // Set all of the constraints for the background image
	        this.setBackgroundImageConstraints();
	        frame.add(this, this.grid);
	        //--- Finish Constraints End ---
	        
	        
	        this.setComponentMainPosition();
	        this.setLabelField(ENTERPASSWORD_LABEL_KEY, "Enter New Password");
	        this.add(this.components.get(ENTERPASSWORD_LABEL_KEY), grid);
	        
	        this.grid.gridx = 0;
	        this.grid.gridy += 1;
	        this.setPasswordField(ENTERPASSWORD_TEXTFIELD_KEY);
	        this.add(this.components.get(ENTERPASSWORD_TEXTFIELD_KEY), grid);
	        
	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
	        this.setButton(ENTERPASSWORD_VISIBILITY_BUTTON_KEY, "Visible");
	        this.add(this.components.get(ENTERPASSWORD_VISIBILITY_BUTTON_KEY), grid);
	        
	        
	        this.grid.gridx = 0;
	        this.grid.gridy += 1;
	        this.setLabelField(CONFIRMPASSWORDPASSWORD_LABEL_KEY, "Confirm New Password");
	        this.add(this.components.get(CONFIRMPASSWORDPASSWORD_LABEL_KEY), grid);
	        
	        this.grid.gridx = 0;
			this.grid.gridy += 1;
	        this.setPasswordField(CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY);
	        this.add(this.components.get(CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY), grid);
	        
	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinates are custom set
	        this.setButton(CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY, "Visibile");
	        this.add(this.components.get(CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY), grid);
	        
	        this.grid.gridx = 0;
	        this.grid.gridy += 1;
	        this.setButton(CANCEL_CHANGE_BUTTON_KEY, "Cancel");
	        this.add(this.components.get(CANCEL_CHANGE_BUTTON_KEY), grid);
	        
	        this.grid.gridx = 1;
	        this.setSubmitButton(SUBMIT_LOGIN_BUTTON_KEY, "Submit");
	        this.add(this.components.get(SUBMIT_LOGIN_BUTTON_KEY), grid);
	        
	        this.submitButtonActionListener();
	        this.enablePasswordTogglers();
	        this.setListeners();
	        this.submitButtonListener();
	        this.cancelButtonActionListener();
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void resetFields() {

		for(Component component : this.components.values()) {
			if(component instanceof JPasswordField) {
				((JPasswordField) component).setText("");
				Arrays.fill(((JPasswordField) component).getPassword(), '\0');
			}
			
			else if(component instanceof JTextField) {
				((JTextField) component).setText("");
			}
		}
	}
	
	// --------------------------------------------------------------------------------------
	public void submitButtonActionListener() {
		JButton submitButton = (JButton) this.components.get(SUBMIT_LOGIN_BUTTON_KEY);
		
		submitButton.addActionListener(e -> {
			this.setVisible(false);
			this.resetFields();
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.USER_CENTRAL_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void cancelButtonActionListener() {
		JButton cancelButton = (JButton) this.components.get(CANCEL_CHANGE_BUTTON_KEY);
		
		cancelButton.addActionListener(e -> {
			this.setVisible(false);
			this.resetFields();
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.MAIN_LOGIN_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void enablePasswordTogglers() {
		this.toggleEnterPasswordVisibility();
        this.toggleConfirmPasswordVisibility();
	}
	
	// --------------------------------------------------------------------------------------
	public void setListeners() {
		this.passwordListener();
		this.confirmPasswordListener();
		this.submitButtonListener();
	}
	
	// --------------------------------------------------------------------------------------
	public void submitButtonListener() {
		JButton submitButton = (JButton) this.components.get(SUBMIT_LOGIN_BUTTON_KEY);
		JPasswordField enterPassword = (JPasswordField) this.components.get(ENTERPASSWORD_TEXTFIELD_KEY);
		JPasswordField confirmPassword = (JPasswordField) this.components.get(CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY);
		
		ChangePasswordDocumentListener changePasswordDocumentListener
			= new ChangePasswordDocumentListener(submitButton, enterPassword, confirmPassword);
		
		enterPassword.getDocument().addDocumentListener(changePasswordDocumentListener);
		confirmPassword.getDocument().addDocumentListener(changePasswordDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void passwordListener() {
		JButton passwordButton = (JButton) this.components.get(ENTERPASSWORD_VISIBILITY_BUTTON_KEY);
		JPasswordField passwordField = (JPasswordField) this.components.get(ENTERPASSWORD_TEXTFIELD_KEY);
		
		PasswordValidationDocumentListener passwordDocumentListener = new PasswordValidationDocumentListener(passwordField, passwordButton);
		passwordField.getDocument().addDocumentListener(passwordDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void confirmPasswordListener() {
		JButton passphraseButton = (JButton) this.components.get(CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY);
		JPasswordField passphraseField = (JPasswordField) this.components.get(CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY);
		
		PasswordValidationDocumentListener passwordDocumentListener = new PasswordValidationDocumentListener(passphraseField, passphraseButton);
		passphraseField.getDocument().addDocumentListener(passwordDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void toggleEnterPasswordVisibility() {
		JButton toggleButton = (JButton) this.components.get(ENTERPASSWORD_VISIBILITY_BUTTON_KEY);
		
		toggleButton.addActionListener( e -> {
			JPasswordField enterPasswordTextField = (JPasswordField) this.components.get(ENTERPASSWORD_TEXTFIELD_KEY);
			this.togglePasswordVisibility.passwordToggler(enterPasswordTextField);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void toggleConfirmPasswordVisibility() {
		JButton toggleButton = (JButton) this.components.get(CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY);
		
		toggleButton.addActionListener( e -> {
			JPasswordField confirmPasswordTextField = (JPasswordField) this.components.get(CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY);
			this.togglePasswordVisibility.passwordToggler(confirmPasswordTextField);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void setComponentMainPosition() {
		this.grid.insets = new Insets(2, 2, 2, 2);
        this.grid.gridx = GRID_X_INITIAL;
        this.grid.gridy = GRID_Y_INITIAL;
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
		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
        this.grid.anchor = GridBagConstraints.CENTER;
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(buttonKey, button);
	}
	
	// --------------------------------------------------------------------------------------
	public void setSubmitButton(String buttonKey, String buttonText) {
		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
        this.grid.anchor = GridBagConstraints.CENTER;
        
        button.setOpaque(false);
        button.setEnabled(false);
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(buttonKey, button);
	}
	
	// --------------------------------------------------------------------------------------
	public void setPasswordField(String passwordFieldKey) {
		PasswordFieldTemplates passwordField = new PasswordFieldTemplates(Color.WHITE, Color.BLACK, TEXTFIELD_SIZE);
		this.grid.anchor = GridBagConstraints.CENTER;
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
