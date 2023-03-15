package com.rqueztech.ui.user;

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
import javax.swing.SwingUtilities;

import com.rqueztech.ui.PasswordDocumentListener;
import com.rqueztech.ui.TogglePasswordVisibility;
import com.rqueztech.ui.validation.InputValidations;

public class UserChangeDefaultPasswordPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6576995148312400023L;
	// --- Group 1: Panel related variables ---
	private JFrame frame;
	private Image image;
	private GridBagConstraints grid;
	
	// --- Group 2: Constants (to prevent magic numbers)
	// Inset variables
	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 2;
	private final int RIGHT_INSET = 0;
	
	// Grid coordinate predefinitions
	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;
	private final int GRID_Y_INCREMENT = 1;
	private final int GRID_X_INCREMENT = 1;
	private int GRID_Y_CURRENT = 0;
	private int GRID_X_CURRENT = 0;
	private final int GRID_X_LEFT = 0;
	private final int GRID_X_MIDDLE = 2;
	private final int GRID_X_RIGHT = 3;
	
	// Textfield variable
	private final int TEXTFIELD_LENGTH = 20;
	
	// Hashmap variable names
	private final String DEFAULT_PASSWORD_DETECTED = "Default Password Detected";
	private final String MUST_ENTER_NEWPASSWORD = "Must Enter new Password";
	private final String ENTER_NEWPASSWORD_LABEL = "Enter New Password";
	private final String ENTER_NEWPASSWORD_FIELD = "Enter New Password Field";
	private final String NEWPASSWORD_BUTTON = "Yes 1";
	private final String CONFIRM_NEWPASSWORD_LABEL = "Confirm New Password";
	private final String CONFIRM_NEWPASSWORD_FIELD = "Confirm New Password Field";
	private final String CONFIRM_NEWPASSWORD_BUTTON = "Yes 2";
	private final String CANCEL_BUTTON = "Cancel";
	private final String SUBMIT_BUTTON = "Submit";
	private final String PASSWORDS_MATCH = "Match";
	
	private HashMap <String, JComponent> components;
	
	public InputValidations inputValidations;
	public PasswordDocumentListener passwordDocumentListener;
	public TogglePasswordVisibility togglePasswordVisibility;
	
	public UserChangeDefaultPasswordPanel(JFrame frame, InputValidations inputValidations) {
		
		this.inputValidations = new InputValidations();
		this.togglePasswordVisibility = new TogglePasswordVisibility();
		
		this.components = new HashMap <String, JComponent> ();
		this.setLayout(new GridBagLayout());
		this.grid = new GridBagConstraints();
		
		this.frame = frame;
		this.image = new ImageIcon("backgroundd.jpg").getImage();
		
		this.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
		this.frame.add(this);
		
		SwingUtilities.invokeLater(() -> {
			this.grid.insets = new Insets(TOP_INSET, LEFT_INSET, BOTTOM_INSET, RIGHT_INSET);
			this.grid.gridx = GRID_X_INITIAL;
			this.grid.gridy = GRID_Y_INITIAL;
			this.grid.insets = new Insets(1, 5, 1, 5);
			
			this.addLabel(this.DEFAULT_PASSWORD_DETECTED);
			
			this.setIncreaseY();
			this.addLabel(this.MUST_ENTER_NEWPASSWORD);
			
			this.setIncreaseY();
			this.setXLeft();
			this.addLeftButton(this.PASSWORDS_MATCH);
			
			this.setIncreaseY();
			this.addLabel(this.ENTER_NEWPASSWORD_LABEL);
			
			this.setIncreaseY();
			this.addPasswordField(this.ENTER_NEWPASSWORD_FIELD);
			
			this.setXRight();
			this.addRightButton(this.NEWPASSWORD_BUTTON);
			
			this.setXLeft();
			this.setIncreaseY();
			this.addLabel(this.CONFIRM_NEWPASSWORD_LABEL);
			
			this.setIncreaseY();
			this.addPasswordField(this.CONFIRM_NEWPASSWORD_FIELD);
			
			this.setXRight();
			this.addRightButton(this.CONFIRM_NEWPASSWORD_BUTTON);
			
			this.setXLeft();
			this.setIncreaseY();
			this.addLeftButton(this.CANCEL_BUTTON);
			
			this.setXMiddle();
			this.addRightButton(this.SUBMIT_BUTTON);
			
			this.yes1Button();
			this.yes2Button();
			
			// Initialize the two document listeners
			this.newPasswordValidator(); //New Password Document Listener
			this.confirmPasswordValidator(); //Confirm Password Document Listener
		});
	}
	
	public void newPasswordValidator() {
		JButton newButton = (JButton) this.components.get(this.NEWPASSWORD_BUTTON);
		
		JPasswordField passwordField = (JPasswordField) this.components.get(this.ENTER_NEWPASSWORD_FIELD);
		passwordField.getDocument().addDocumentListener(new PasswordDocumentListener(passwordField, newButton));
	}
	
	public void confirmPasswordValidator() {
		JButton confirmButton = (JButton) this.components.get(this.CONFIRM_NEWPASSWORD_BUTTON);
		
		JPasswordField passwordField = (JPasswordField) this.components.get(this.CONFIRM_NEWPASSWORD_FIELD);
		passwordField.getDocument().addDocumentListener(new PasswordDocumentListener(passwordField, confirmButton));
	}
		
	public void yes1Button() {
		JButton yes1Button = (JButton) this.components.get(NEWPASSWORD_BUTTON);
		
		yes1Button.addActionListener(e -> {
			JPasswordField yes1PasswordField = (JPasswordField) this.components.get(ENTER_NEWPASSWORD_FIELD);
			this.togglePasswordVisibility.passwordToggler(yes1PasswordField);
		});
	}
	
	public void yes2Button() {
		JButton yes2Button = (JButton) this.components.get(CONFIRM_NEWPASSWORD_BUTTON);
		
		yes2Button.addActionListener(e -> {
			JPasswordField yes2PasswordField = (JPasswordField) this.components.get(CONFIRM_NEWPASSWORD_FIELD);	
			this.togglePasswordVisibility.passwordToggler(yes2PasswordField);
		});
	}
	
	
	public void setXLeft() {
		this.grid.gridx = GRID_X_LEFT;
	}
	
	public void setXMiddle() {
		this.grid.gridx += GRID_X_MIDDLE;
	}

	public void setXRight() {
		this.grid.gridx += GRID_X_RIGHT;
	}
	
	public void setIncreaseY() {
		this.grid.gridy += GRID_Y_INCREMENT;
	}
	
	
	private void addPasswordField(String newPasswordName) {
		JPasswordField newPassword = new JPasswordField(TEXTFIELD_LENGTH); // Set Object (passwordfield) to size
		newPassword.setForeground(Color.BLACK); // Set the color
		
		this.grid.gridwidth = 3;
		
		this.grid.anchor = GridBagConstraints.WEST;
		this.grid.fill = GridBagConstraints.NONE;
		String message = newPasswordName + " Minimum 8 Characters, 1 Uppercase, 1 Lowercase, 1 Symbol"; 
		
		newPassword.setToolTipText(message);
		this.components.put(newPasswordName, newPassword);
		
		this.add(newPassword, this.grid); // Add to the current grid
	}

	private void addLabel(String labelName) {
		JLabel label = new JLabel(labelName);
		label.setForeground(Color.WHITE);
		
		this.grid.gridwidth = 3;
		this.grid.anchor = GridBagConstraints.WEST;
		this.grid.fill = GridBagConstraints.NONE;
		this.components.put(labelName, label);
		
		this.add(label, this.grid);
	}
	
	private void addLeftButton(String leftButtonName) {
		JButton leftButton = new JButton(leftButtonName);
		
		leftButton.setForeground(Color.GRAY);
		leftButton.setBackground(Color.BLACK);
		
		this.grid.gridwidth = 1;
		this.grid.anchor = GridBagConstraints.WEST;
		this.grid.fill = GridBagConstraints.NONE;
		this.components.put(leftButtonName, leftButton);
		
		this.add(leftButton, this.grid);
	}
	
	private void addRightButton(String rightButtonName) {
		JButton rightButton = new JButton(rightButtonName);
		
		rightButton.setForeground(Color.GRAY);
		rightButton.setBackground(Color.BLACK);
		
		this.grid.gridwidth = 1;
		this.grid.anchor = GridBagConstraints.EAST;
		this.grid.fill = GridBagConstraints.NONE;
		this.components.put(rightButtonName, rightButton);
		
		this.add(rightButton, this.grid);
	}
	
	// Getter to retrieve components from the components hashmap.
	// Components include textfields, labels, and buttons used in program
	public JComponent getComponents(String key) {
		return components.get(key);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(image, 0, 0, null);
	}
}
