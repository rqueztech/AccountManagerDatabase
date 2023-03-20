package com.rqueztech.ui.admin;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

import com.rqueztech.ui.BaseFrame;
import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.events.TogglePasswordVisibility;

public class AdminCentralPanel extends JPanel {
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private JFrame frame;
	private Image image;
	private GridBagConstraints grid;

	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 0;
	private final int RIGHT_INSET = 0;
	
	// --- Section 1: Adminname Component Keys
	private final String ENTERPASSWORD_LABEL_KEY = "ENTERPASSWORD_LABEL_KEY";
	private final String ENTERPASSWORD_TEXTFIELD_KEY = "ENTERPASSWORD_TEXTFIELD_KEY";
	private final String ENTERPASSWORD_VISIBILITY_BUTTON_KEY = "ENTERPASSWORD_VISIBILITY_BUTTON_KEY";
	
	// --- Section 2: Password Component Keys
	private final String CONFIRMPASSWORDPASSWORD_LABEL_KEY = "CONFIRMPASSWORDPASSWORD_LABEL_KEY";
	private final String CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY = "CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY";
	private final String CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY = "CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY";
	
	// --- Section 3: Login Button Component Keys
	private final String ADMIN_LOGOUT_BUTTON_KEY = "ADMIN_LOGOUT_BUTTON_KEY";
	private final String ADMIN_ADD_USER_BUTTON_KEY = "ADMIN_ADD_USER_BUTTON_KEY";
	
	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;
	
	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEWEIGHT = 1;
	
	private final int TEXTFIELD_SIZE = 10;
	
	private TogglePasswordVisibility togglePasswordVisibility;
	
	private int newYValue = 0;
	private PanelCentral panelCentral;
	
	// --- Group 2: Panel Map ---
	private ConcurrentHashMap <String, JComponent> components;
	
	// --------------------------------------------------------------------------------------
	public AdminCentralPanel(BaseFrame frame, GridBagLayout layout, PanelCentral panelCentral) {
		// Function that will toggle visibility on and off in password
		// Field found in this class
		this.togglePasswordVisibility = new TogglePasswordVisibility();
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
	        
	        this.grid.gridx = 0;
	        this.grid.gridy = 0;
	        this.setButton(ADMIN_LOGOUT_BUTTON_KEY, "Logout");
	        this.add(this.components.get(ADMIN_LOGOUT_BUTTON_KEY), grid);
	        
	        this.grid.gridx += 1;
	        this.setButton(ADMIN_ADD_USER_BUTTON_KEY, "Add user");
	        this.add(this.components.get(ADMIN_ADD_USER_BUTTON_KEY), grid);
	        
	        this.adminAddUserButtonListener();
	        this.logoutButtonActionListener();
	        
	        //this.enablePasswordTogglers();
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void adminAddUserButtonListener() {
		JButton adminLogin = (JButton) this.components.get(ADMIN_ADD_USER_BUTTON_KEY);
		
		adminLogin.addActionListener(e -> {
			this.setVisible(false);
			this.panelCentral.getPanel().get(PanelCentralEnums.ADMIN_ADD_USER_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void logoutButtonActionListener() {
		JButton adminLogin = (JButton) this.components.get(ADMIN_LOGOUT_BUTTON_KEY);
		
		adminLogin.addActionListener(e -> {
			this.setVisible(false);
			this.panelCentral.getPanel().get(PanelCentralEnums.LOGOUT_SUCCESS_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void enablePasswordTogglers() {
		this.toggleEnterPasswordVisibility();
        this.toggleConfirmPasswordVisibility();
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
