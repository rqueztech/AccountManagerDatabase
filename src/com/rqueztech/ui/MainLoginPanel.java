package com.rqueztech.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
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

import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.events.TogglePasswordVisibility;

public class MainLoginPanel extends JPanel {
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private Image image;
	private GridBagConstraints grid;

	// Insets for the GridBagLayout
	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 0;
	private final int RIGHT_INSET = 0;
	
	// --- Section 1: Username Component Keys
	private final String USERNAME_LABEL_KEY = "USERNAME_LABEL_KEY";
	private final String USERNAME_TEXTFIELD_KEY = "USERNAME_TEXTFIELD_KEY";
	
	// --- Section 2: Password Component Keys
	private final String PASSWORD_LABEL_KEY = "PASSWORD_LABEL_KEY";
	private final String PASSWORD_TEXTFIELD_KEY = "PASSWORD_TEXTFIELD_KEY";
	private final String VISIBILITY_BUTTON_KEY = "VISIBILITY_BUTTON_KEY";
	
	// --- Section 3: Login Button Component Keys
	private final String USER_LOGIN_BUTTON_KEY = "USER_LOGIN_BUTTON_KEY";
	private final String ADMIN_LOGIN_BUTTON_KEY = "ADMIN_LOGIN_BUTTON_KEY";
	
	// Initial GridBagLayout values
	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;
	
	// Weight of image gridvalues
	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEWEIGHT = 1;
	
	private final int TEXTFIELD_SIZE = 10;
	
	private TogglePasswordVisibility togglePasswordVisibility;
	
	private PanelCentral panelCentral;
	
	// --- Group 2: Panel Map ---
	private ConcurrentHashMap <String, JComponent> components;
	
	// --------------------------------------------------------------------------------------
	public MainLoginPanel(BaseFrame frame, GridBagLayout layout, PanelCentral panelCentral) {
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
	        
	        // Set the Username 
	        this.setComponentMainPosition();
	        this.setLabelField(USERNAME_LABEL_KEY, "Enter Username");
	        this.add(this.components.get(USERNAME_LABEL_KEY), grid);
	        
	        this.setNewTextfieldPosition(); // All textfields are fixed, therefore coordinates set in function
	        this.setTextField(USERNAME_TEXTFIELD_KEY);
	        this.add(this.components.get(USERNAME_TEXTFIELD_KEY), grid);
	        
	        this.setNewLabelPosition(); // All labels are fixed, therefore coordinates set in function
	        this.setLabelField(PASSWORD_LABEL_KEY, "Enter Password");
	        this.add(this.components.get(PASSWORD_LABEL_KEY), grid);
	        
	        this.setNewTextfieldPosition(); // All textfields are fixed, therefore coordinates set in function
	        this.setPasswordField(PASSWORD_TEXTFIELD_KEY);
	        this.add(this.components.get(PASSWORD_TEXTFIELD_KEY), grid);
	        
	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
	        this.setButton(VISIBILITY_BUTTON_KEY, "Visible");
	        this.add(this.components.get(VISIBILITY_BUTTON_KEY), grid);
	        
	        this.grid.gridx = 0; // Buttons are not fixed, therefore coordinates are custom set
	        this.grid.gridy += 1;
	        this.setButton(USER_LOGIN_BUTTON_KEY, "User");
	        this.add(this.components.get(USER_LOGIN_BUTTON_KEY), grid);
	        
	        this.grid.gridx += 1;
	        this.setButton(ADMIN_LOGIN_BUTTON_KEY, "Admin");
	        this.add(this.components.get(ADMIN_LOGIN_BUTTON_KEY), grid);
	        
	        this.userButtonActionListener();
	        this.adminButtonActionListener();
	        this.togglePasswordVisibility();
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void clearFields() {
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
	public void userButtonActionListener() {
		JButton userButton = (JButton) this.components.get(USER_LOGIN_BUTTON_KEY);
		
		userButton.addActionListener(e -> {
			this.setVisible(false);
			this.clearFields();
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void adminButtonActionListener() {
		JButton adminButton = (JButton) this.components.get(ADMIN_LOGIN_BUTTON_KEY);
		
		adminButton.addActionListener(e -> {
			this.setVisible(false);
			this.clearFields();
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.ADMIN_CENTRAL_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void togglePasswordVisibility() {
		JButton toggleButton = (JButton) this.components.get(VISIBILITY_BUTTON_KEY);
		
		toggleButton.addActionListener( e -> {
			JPasswordField passwordTextField = (JPasswordField) this.components.get(PASSWORD_TEXTFIELD_KEY);
			this.togglePasswordVisibility.passwordToggler(passwordTextField);
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
