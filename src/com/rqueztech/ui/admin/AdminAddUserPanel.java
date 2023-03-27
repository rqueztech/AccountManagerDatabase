package com.rqueztech.ui.admin;

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
import javax.swing.JComboBox;
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
import com.rqueztech.ui.events.AddUserDocumentListener;
import com.rqueztech.ui.events.PasswordFieldListener;
import com.rqueztech.ui.events.TextFieldListener;
import com.rqueztech.ui.events.TogglePasswordVisibility;
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
	
	// --- Section 1: Adminname Component Keys
	private final String FIRSTNAME_LABEL_KEY = "FIRSTNAME_LABEL_KEY";
	private final String FIRSTNAME_TEXTFIELD_KEY = "FIRSTNAME_TEXTFIELD_KEY";

	// --- Section 1: Adminname Component Keys
	private final String LASTNAME_LABEL_KEY = "LASTNAME_LABEL_KEY";
	private final String LASTNAME_TEXTFIELD_KEY = "LASTNAME_TEXTFIELD_KEY";
	
	// --- Section 3: Login Button Component Keys
	private final String CANCEL_BUTTON_KEY = "CANCEL_BUTTON_KEY";
	private final String ADD_USER_BUTTON_KEY = "ADD_USER_BUTTON_KEY";
	
	private final String PASSPHRASE_LABEL_KEY = "PASSPHRASE_LABEL_KEY";
	private final String PASSPHRASE_TEXTFIELD_KEY = "PASSPHRASE_TEXTFIELD_KEY";
	private final String PASSPHRASE_VISIBILITY_BUTTON_KEY = "PASSPHRASE_VISIBILITY_BUTTON_KEY";
	
	// --- Section 4: Set Combo Box
	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;
	
	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEWEIGHT = 1;
	
	private final int TEXTFIELD_SIZE = 5;
	
	private PanelCentral panelCentral;
	
	// --- Group 2: Panel Map ---
	private ConcurrentHashMap <String, JComponent> components;
	private JComboBox<String> gender;
	
	private TogglePasswordVisibility togglePasswordVisibility;
	
	// --------------------------------------------------------------------------------------
	public AdminAddUserPanel(BaseFrame frame, GridBagLayout layout, PanelCentral panelCentral) {
		// Function that will toggle visibility on and off in password
		// Field found in this class
		this.panelCentral = panelCentral;
		
		this.togglePasswordVisibility = new TogglePasswordVisibility();
		
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
	        String[] genderOptions = { "Select", "Male", "Female", "Undisclosed" };
	        this.gender = new JComboBox<String>(genderOptions);
	        
	        this.setComponentMainPosition();
	        this.grid.gridx = 0;
	        this.grid.gridy = 0;
	        
	        this.setLabelField(FIRSTNAME_LABEL_KEY, "Enter First Name");
	        this.add(this.components.get(FIRSTNAME_LABEL_KEY), grid);
	        
	        // Firstname textfield
	        this.grid.gridy += 1;	// Increase vertical grid by one
	        this.setTextField(FIRSTNAME_TEXTFIELD_KEY);	// Set the textfield key
	        this.add(this.components.get(FIRSTNAME_TEXTFIELD_KEY), grid);	// Add the component to the grid
	        
	        this.grid.gridy += 1;
	        this.setLabelField(LASTNAME_LABEL_KEY, "Enter Last Name");
	        this.add(this.components.get(LASTNAME_LABEL_KEY), grid);
	        
	        // Lastname textfield
	        this.grid.gridy += 1;
	        this.setTextField(LASTNAME_TEXTFIELD_KEY);
	        this.add(this.components.get(LASTNAME_TEXTFIELD_KEY), grid);
	        
	        // Passphrase Label
	        this.grid.gridy += 1;
	        this.setLabelField(PASSPHRASE_LABEL_KEY, "Enter Passphrase");
	        this.add(this.components.get(PASSPHRASE_LABEL_KEY), grid);
	        
	        // Passphrase textfield
	        this.grid.gridy += 1;
	        this.setPasswordField(PASSPHRASE_TEXTFIELD_KEY);
	        this.add(this.components.get(PASSPHRASE_TEXTFIELD_KEY), grid);
	        
	        // Passphrase button
	        this.grid.gridx += 2;
	        this.setButton(PASSPHRASE_VISIBILITY_BUTTON_KEY, "Visibility");
	        this.add(this.components.get(PASSPHRASE_VISIBILITY_BUTTON_KEY), grid);
	        
	        // Combo Box. Note: ComboBox is not added to the existing
	        // 
	        this.grid.gridx = 0;
	        this.grid.gridy += 1;
	        this.setComboBox();
	        this.add(this.gender, this.grid);
	        
	        // Set the exit button
	        this.grid.gridy += 1;
	        this.setButton(CANCEL_BUTTON_KEY, "Cancel");
	        this.add(this.components.get(CANCEL_BUTTON_KEY), grid);
	        
	        this.grid.gridx += 1;
	        this.setAddButton(ADD_USER_BUTTON_KEY, "Add user");
	        this.add(this.components.get(ADD_USER_BUTTON_KEY), grid);
	        
	        this.invokeActionListeners();
	        this.invokeDocumentListeners();
	        
	        this.enablePasswordTogglers();
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void enablePasswordTogglers() {
		this.toggleEnterPasswordVisibility();
	}
	
	// --------------------------------------------------------------------------------------
	public void toggleEnterPasswordVisibility() {
		JButton toggleButton = (JButton) this.components.get(PASSPHRASE_VISIBILITY_BUTTON_KEY);
		
		toggleButton.addActionListener( e -> {
			JPasswordField enterPasswordTextField = (JPasswordField) this.components.get(PASSPHRASE_TEXTFIELD_KEY);
			this.togglePasswordVisibility.passwordToggler(enterPasswordTextField);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void invokeActionListeners() {
		this.userViewButtonListener();
        this.cancelButtonActionListener();
	}
	
	// --------------------------------------------------------------------------------------
	public void userViewButtonListener() {
		JButton userViewButton = (JButton) this.components.get(ADD_USER_BUTTON_KEY);
		
		userViewButton.addActionListener(e -> {
			this.setVisible(false);
			this.resetFields();
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.ADMIN_USER_VIEW_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void invokeDocumentListeners() {
		this.firstNameListener();
		this.lastNameListener();
		this.passphraseNameListener();
		this.addUserButtonListener();
	}
	
	// --------------------------------------------------------------------------------------
	public void addUserButtonListener() {
		JTextField firstName = (JTextField) this.components.get(FIRSTNAME_TEXTFIELD_KEY);
		JTextField lastName = (JTextField) this.components.get(LASTNAME_TEXTFIELD_KEY);
		JPasswordField passphrase = (JPasswordField) this.components.get(PASSPHRASE_TEXTFIELD_KEY);
		
		JButton addUserButton = (JButton) this.components.get(ADD_USER_BUTTON_KEY);
		
		AddUserDocumentListener addUserDocumentListener = 
				new AddUserDocumentListener(addUserButton, firstName, lastName,
						passphrase, this.gender);
		
		firstName.getDocument().addDocumentListener(addUserDocumentListener);
		lastName.getDocument().addDocumentListener(addUserDocumentListener);
		passphrase.getDocument().addDocumentListener(addUserDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void firstNameListener() {
		JTextField firstName = (JTextField) this.components.get(FIRSTNAME_TEXTFIELD_KEY);
		
		// Create a listener for the first name field
		TextFieldListener nameFieldListener = 
				new TextFieldListener(firstName);
		
		firstName.getDocument().addDocumentListener(nameFieldListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void lastNameListener() {
		JTextField lastName = (JTextField) this.components.get(LASTNAME_TEXTFIELD_KEY);
		
		// Listener for the last name field
		TextFieldListener lastNameFieldListener = 
				new TextFieldListener(lastName);
		
		lastName.getDocument().addDocumentListener(lastNameFieldListener);
	}
	
	// --------------------------------------------------------------------------------------
	public void passphraseNameListener() {
		JPasswordField passphrase = (JPasswordField) this.components.get(PASSPHRASE_TEXTFIELD_KEY);
		// Listener for the last name field
		PasswordFieldListener passwordFieldListener = 
				new PasswordFieldListener(passphrase);

		passphrase.getDocument().addDocumentListener(passwordFieldListener);
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
		
		this.gender.setSelectedIndex(0);
	}
	
	// --------------------------------------------------------------------------------------
	public void cancelButtonActionListener() {
		JButton adminLogin = (JButton) this.components.get(CANCEL_BUTTON_KEY);
		
		adminLogin.addActionListener(e -> {
			this.setVisible(false);
			this.resetFields();
			
			// Take the user back to the Admin User View Panel
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.ADMIN_USER_VIEW_PANEL).setVisible(true);
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
	public void setTextField(String textFieldKey) {
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
	public void setButton(String buttonKey, String buttonText) {
		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
        
		this.grid.anchor = GridBagConstraints.CENTER;
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(buttonKey, button);
	}
	
	// --------------------------------------------------------------------------------------
	public void setAddButton(String buttonKey, String buttonText) {
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
	public void setPasswordField(String passwordFieldKey) {
		PasswordFieldTemplates passwordField = new PasswordFieldTemplates(Color.WHITE, Color.BLACK, TEXTFIELD_SIZE);
		
		this.grid.anchor = GridBagConstraints.CENTER;
		this.grid.gridwidth = 2;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(passwordFieldKey, passwordField);
	}
	
	// --------------------------------------------------------------------------------------
	public void setComboBox() {
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
	public void setLabelField(String labelKey, String labelText) {
		JLabel labelField = new JLabel(labelText);
		this.grid.anchor = GridBagConstraints.CENTER;
		labelField.setBackground(Color.BLACK);
		labelField.setForeground(Color.WHITE);
		this.grid.gridwidth = 3;
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
