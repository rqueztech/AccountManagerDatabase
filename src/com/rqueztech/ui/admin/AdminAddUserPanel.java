package com.rqueztech.ui.admin;

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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.rqueztech.controllers.admin.AdminAddUserController;
import com.rqueztech.ui.BaseFrame;
import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.admin.enums.AdminAddUserEnums;
import com.rqueztech.ui.buttons.ButtonTemplates;
import com.rqueztech.ui.passwordfields.PasswordFieldTemplates;
import com.rqueztech.ui.textfields.TextfieldTemplates;

public class AdminAddUserPanel extends JPanel {
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private Image image;
	private GridBagConstraints grid;

	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 0;
	private final int RIGHT_INSET = 0;
	
	// --- Section 4: Set Combo Box
	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;
	
	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEWEIGHT = 1;
	
	private final int TEXTFIELD_SIZE = 5;
	
	// --- Group 2: Panel Map ---
	private ConcurrentHashMap <AdminAddUserEnums, JComponent> components;
	private JComboBox<String> gender;
	private PanelCentral panelCentral;
	String[] genderOptions = { "Select", "Male", "Female", "Undisclosed" };
	
	private AdminAddUserController adminAddUserController;
	
	// --------------------------------------------------------------------------------------
	public AdminAddUserPanel(BaseFrame frame, GridBagLayout layout, PanelCentral panelCentral) {
		// Function that will toggle visibility on and off in password
		// Field found in this class
		
		// Dispatch responsibilities on EDT.
		SwingUtilities.invokeLater(() -> {
	        
			this.panelCentral = panelCentral;
			
			// Set the panel to the gridbaglayout, establish the preferred size,
			// And get the image that will be used in the background
			this.setLayout(layout);
	        this.setPreferredSize(new Dimension(frame.getHeight(), frame.getWidth()));
	        this.image = new ImageIcon("backgroundd.jpg").getImage();
	        this.components = new ConcurrentHashMap <AdminAddUserEnums, JComponent> ();
	        
	        // --- Start Constraints ---
	        // Set all of the constraints for the background image
	        this.setBackgroundImageConstraints();
	        frame.add(this, this.grid);
	        //--- Finish Constraints End ---
	        this.gender = new JComboBox<String>(genderOptions);
	        
	        this.setComponentMainPosition();
	        this.grid.gridx = 0;
	        this.grid.gridy = 0;
	        
	        this.setLabelField(AdminAddUserEnums.FIRSTNAME_LABEL_KEY, "Enter First Name");
	        this.add(this.components.get(AdminAddUserEnums.FIRSTNAME_LABEL_KEY), grid);
	        
	        // Firstname textfield
	        this.grid.gridy += 1;	// Increase vertical grid by one
	        this.setTextField(AdminAddUserEnums.FIRSTNAME_TEXTFIELD_KEY);	// Set the textfield key
	        this.add(this.components.get(AdminAddUserEnums.FIRSTNAME_TEXTFIELD_KEY), grid);	// Add the component to the grid
	        
	        this.grid.gridy += 1;
	        this.setLabelField(AdminAddUserEnums.LASTNAME_LABEL_KEY, "Enter Last Name");
	        this.add(this.components.get(AdminAddUserEnums.LASTNAME_LABEL_KEY), grid);
	        
	        // Lastname textfield
	        this.grid.gridy += 1;
	        this.setTextField(AdminAddUserEnums.LASTNAME_TEXTFIELD_KEY);
	        this.add(this.components.get(AdminAddUserEnums.LASTNAME_TEXTFIELD_KEY), grid);
	        
	        // Passphrase Label
	        this.grid.gridy += 1;
	        this.setLabelField(AdminAddUserEnums.PASSPHRASE_LABEL_KEY, "Enter Passphrase");
	        this.add(this.components.get(AdminAddUserEnums.PASSPHRASE_LABEL_KEY), grid);
	        
	        // Passphrase textfield
	        this.grid.gridy += 1;
	        this.setPasswordField(AdminAddUserEnums.PASSPHRASE_TEXTFIELD_KEY);
	        this.add(this.components.get(AdminAddUserEnums.PASSPHRASE_TEXTFIELD_KEY), grid);
	        
	        // Passphrase button
	        this.grid.gridx += 2;
	        this.setButton(AdminAddUserEnums.PASSPHRASE_VISIBILITY_BUTTON_KEY, "Visibility");
	        this.add(this.components.get(AdminAddUserEnums.PASSPHRASE_VISIBILITY_BUTTON_KEY), grid);
	        
	        // Combo Box. Note: ComboBox is not added to the existing
	        // 
	        this.grid.gridx = 0;
	        this.grid.gridy += 1;
	        this.setComboBox();
	        this.add(this.gender, this.grid);
	        
	        // Set the exit button
	        this.grid.gridy += 1;
	        this.setButton(AdminAddUserEnums.CANCEL_BUTTON_KEY, "Cancel");
	        this.add(this.components.get(AdminAddUserEnums.CANCEL_BUTTON_KEY), grid);
	        
	        this.grid.gridx += 1;
	        this.setAddButton(AdminAddUserEnums.ADD_USER_BUTTON_KEY, "Add user");
	        this.add(this.components.get(AdminAddUserEnums.ADD_USER_BUTTON_KEY), grid);
	        
	        this.adminAddUserController = new AdminAddUserController(this, gender);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void setComponentMainPosition() {
		this.grid.insets = new Insets(2, 2, 2, 2);
        this.grid.gridx = GRID_X_INITIAL;
        this.grid.gridy = GRID_Y_INITIAL;
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
	private void setTextField(AdminAddUserEnums textFieldKey) {
		TextfieldTemplates textField = new TextfieldTemplates(Color.WHITE, Color.BLACK, TEXTFIELD_SIZE);
		
		//this.grid.anchor = GridBagConstraints.CENTER;
		//textField.setFont(new Font("Arial", Font.PLAIN, 14));
		
		this.grid.gridwidth = 2;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        this.grid.gridx = GRID_X_INITIAL;
        this.grid.gridy += 1;
        
        this.components.put(textFieldKey, textField);
        this.add(this.components.get(textFieldKey), grid);
	}
	
	// --------------------------------------------------------------------------------------
	private void setButton(AdminAddUserEnums buttonKey, String buttonText) {
		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
        
		this.grid.anchor = GridBagConstraints.CENTER;
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(buttonKey, button);
	}
	
	// --------------------------------------------------------------------------------------
	private void setAddButton(AdminAddUserEnums buttonKey, String buttonText) {
		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
        
		this.grid.anchor = GridBagConstraints.CENTER;
        button.setEnabled(false);
        button.setOpaque(false);
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(buttonKey, button);
	}
	
	// --------------------------------------------------------------------------------------
	private void setPasswordField(AdminAddUserEnums passwordFieldKey) {
		PasswordFieldTemplates passwordField = new PasswordFieldTemplates(Color.WHITE, Color.BLACK, TEXTFIELD_SIZE);
		
		this.grid.anchor = GridBagConstraints.CENTER;
		this.grid.gridwidth = 2;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(passwordFieldKey, passwordField);
	}
	
	// --------------------------------------------------------------------------------------
	private void setComboBox() {
		// Gender Dropbox
		this.gender.setForeground(Color.white);
		this.gender.setBackground(Color.black);
		
		this.grid.gridwidth = 1;
		this.grid.gridheight = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        this.grid.insets = new Insets(2, 0, 10, 0); 
	}
	
	// --------------------------------------------------------------------------------------
	private void setLabelField(AdminAddUserEnums labelKey, String labelText) {
		JLabel labelField = new JLabel(labelText);
		this.grid.anchor = GridBagConstraints.CENTER;
		labelField.setBackground(Color.BLACK);
		labelField.setForeground(Color.WHITE);
		this.grid.gridwidth = 3;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(labelKey, labelField);
	}
	
	// --------------------------------------------------------------------------------------
	public ConcurrentHashMap<AdminAddUserEnums, JComponent> getComponentsMap() {
		return this.components;
	}
	
	// --------------------------------------------------------------------------------------
	public JComboBox<String> getGender() {
		return this.gender;
	}
	
	// --------------------------------------------------------------------------------------
	public PanelCentral getPanelCentral() {
		return this.panelCentral;
	}
	
	// --------------------------------------------------------------------------------------
	@Override
	public void paintComponent(Graphics g) {
	    
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
	}
}
