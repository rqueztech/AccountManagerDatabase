package com.rqueztech.ui;

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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.rqueztech.ui.events.TogglePasswordVisibility;

public class MainLoginPanel extends JPanel {
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private JFrame frame;
	private Image image;
	private GridBagConstraints grid;

	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 2;
	private final int RIGHT_INSET = 0;
	private int GRID_X = 0;
	private int GRID_Y = 0;
	private final int GRID_Y_INCREMENT = 1;
	private final int TEXTFIELD_LENGTH = 15;
	
	private final String USERNAME_LABEL = "Enter Username";
	private final String USERNAME_FIELD = "USERNAME_FIELD";
	private final String VISIBILITY_BUTTON = "Visible";
	private final String PASSWORD_LABEL = "Enter Password";
	private final String PASSWORD_FIELD = "PASSWORD_FIELD";
	private final String USER_BUTTON = "User";
	private final String ADMIN_BUTTON = "Admin";
	
	private TogglePasswordVisibility togglePasswordVisibility;
	
	private int newYValue = 0;
	
	// --- Group 2: Panel Map ---
	private HashMap <String, JComponent> components;
	
	public MainLoginPanel(JFrame frame) {
		this.setLayout(new GridBagLayout());
		this.grid = new GridBagConstraints();
		
		this.components = new HashMap <String, JComponent> ();
		
		this.togglePasswordVisibility = new TogglePasswordVisibility();
		
		this.frame = frame;
		this.grid.anchor = GridBagConstraints.CENTER;
		
		this.setPreferredSize(new Dimension(600, 600));
		this.image = new ImageIcon("backgroundd.jpg").getImage();
		
		// Dispatch responsibilities on EDT.
		SwingUtilities.invokeLater(() -> {
			this.setMainLoginComponents();
			this.callActionListeners();
		});
		
		this.frame.add(this);
	}
	
	// --- Group: Action Listeners -> All button functionalities
	public void callActionListeners() {
		passwordVisibility();
		adminButton();
	}
	
	public void passwordVisibility() {
		JButton visibilityButton = (JButton) this.components.get(VISIBILITY_BUTTON);
		
		visibilityButton.addActionListener(e -> {
			JPasswordField passwordField = (JPasswordField) this.components.get(PASSWORD_FIELD);
			this.togglePasswordVisibility.passwordToggler(passwordField);
		});
	}
	
	public void adminButton() {
		JButton admin = (JButton) this.components.get(ADMIN_BUTTON);
		
		admin.addActionListener(e -> {
			JOptionPane.showMessageDialog(null, "Clicked Admin");
		});
	}
	
	// --- Group 3: Component Calls (Add Into Panel)
	private void setMainLoginComponents() {
		this.grid.insets = new Insets(TOP_INSET, LEFT_INSET, BOTTOM_INSET, RIGHT_INSET);
		this.grid.gridx = GRID_X;
		this.grid.gridy = GRID_Y;
		
		// -> Username Rows. Username label/password
		this.addLabel(USERNAME_LABEL, GRID_X);
		this.addTextField(USERNAME_FIELD, GRID_X);
		this.grid.gridy += 1;
		this.addRightButton(VISIBILITY_BUTTON, GRID_X + 3);
		this.grid.gridy -= 1;
		
		// -> Password Rows. Password label/password
		this.addLabel(PASSWORD_LABEL,GRID_X);
		
		this.addPasswordField(PASSWORD_FIELD, GRID_X);
		
		// -> User/Admin Buttons. Individual row.
		this.addRightButton(USER_BUTTON, GRID_X);
		this.addLeftButton(ADMIN_BUTTON, GRID_X + 1);
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
	
	// --- Group 4: Panel Components ---
	private void addPasswordField(String passwordFieldName, int xCoordinate) {
		JPasswordField passwordField = new JPasswordField(TEXTFIELD_LENGTH); // Set Object (passwordfield) to size
		passwordField.setForeground(Color.BLACK); // Set the color
		
		this.grid.gridwidth = 2;
		this.grid.gridx = xCoordinate;
		this.grid.anchor = GridBagConstraints.WEST;
		this.grid.fill = GridBagConstraints.HORIZONTAL;
		this.components.put(passwordFieldName, passwordField);
		
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
		this.grid.gridy += GRID_Y_INCREMENT;
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
		
		this.add(rightButton, this.grid);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(this.image, 0, 0, null);
	}
}
