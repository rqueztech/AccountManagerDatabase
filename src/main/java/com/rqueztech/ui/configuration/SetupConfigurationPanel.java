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

import com.rqueztech.controllers.configuration.SetupConfigurationController;
import com.rqueztech.ui.BaseFrame;
import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.buttons.ButtonTemplates;
import com.rqueztech.ui.configuration.enums.SetupConfigurationPanelEnums;
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

	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;

	private int GRID_X_CURRENT = 0;
	private int GRID_Y_CURRENT = 0;

	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEWEIGHT = 1;

	private final int TEXTFIELD_SIZE = 15;

	private PanelCentral panelCentral;

	// --- Group 2: Panel Map ---
	private ConcurrentHashMap<SetupConfigurationPanelEnums, JComponent> components;
	private SetupConfigurationController setupConfigurationController;

	// ------------------------------------------------------------------------
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
	        this.image = new ImageIcon(getClass().getResource("/images/backgroundd.jpg")).getImage();
	        this.components = new ConcurrentHashMap<SetupConfigurationPanelEnums, JComponent> ();

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

	        this.setLabelField(SetupConfigurationPanelEnums.FIRSTNAME_LABEL_KEY, "Enter FirstName");
	        this.setTextField(SetupConfigurationPanelEnums.FIRSTNAME_TEXTFIELD_KEY);

	        this.setLabelField(SetupConfigurationPanelEnums.LASTNAME_LABEL_KEY, "Enter LastName");
	        this.setTextField(SetupConfigurationPanelEnums.LASTNAME_TEXTFIELD_KEY);

	        this.setLabelField(SetupConfigurationPanelEnums.PASSPHRASE_LABEL_KEY, "Enter Passphrase");
	        this.setPasswordField(SetupConfigurationPanelEnums.PASSPHRASE_TEXTFIELD_KEY);

	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
	        this.setButton(SetupConfigurationPanelEnums.PASSPHRASE_VISIBILITY_BUTTON_KEY, "Visible");
	        this.add(this.components.get(SetupConfigurationPanelEnums.PASSPHRASE_VISIBILITY_BUTTON_KEY), grid);

	        this.setLabelField(SetupConfigurationPanelEnums.CONFIRM_PASSPHRASE_LABEL_KEY, "Confirm Passphrase");
	        this.setPasswordField(SetupConfigurationPanelEnums.CONFIRM_PASSPHRASE_TEXTFIELD_KEY);

	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
	        this.setButton(SetupConfigurationPanelEnums.CONFIRM_PASSPHRASE_VISIBILITY_BUTTON_KEY, "Visible");
	        this.add(this.components.get(SetupConfigurationPanelEnums.CONFIRM_PASSPHRASE_VISIBILITY_BUTTON_KEY), grid);

	        this.setLabelField(SetupConfigurationPanelEnums.PASSWORD_LABEL_KEY, "Enter New Password");
	        this.setPasswordField(SetupConfigurationPanelEnums.PASSWORD_TEXTFIELD_KEY);

	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
	        this.setButton(SetupConfigurationPanelEnums.PASSWORD_VISIBILITY_BUTTON_KEY, "Visible");
	        this.add(this.components.get(SetupConfigurationPanelEnums.PASSWORD_VISIBILITY_BUTTON_KEY), grid);

	        this.setLabelField(SetupConfigurationPanelEnums.CONFIRM_PASSWORD_LABEL_KEY, "Confirm New Password");
	        this.setPasswordField(SetupConfigurationPanelEnums.CONFIRM_PASSWORD_TEXTFIELD_KEY);

	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
	        this.setButton(SetupConfigurationPanelEnums.CONFIRM_PASSWORD_VISIBILITY_BUTTON_KEY, "Visible");
	        this.add(this.components.get(SetupConfigurationPanelEnums.CONFIRM_PASSWORD_VISIBILITY_BUTTON_KEY), grid);

	        this.grid.fill = GridBagConstraints.HORIZONTAL;
	        this.grid.anchor = GridBagConstraints.EAST;
	        this.grid.gridx = GRID_X_CURRENT; // Buttons are not fixed, therefore coordinates are custom set
	        this.grid.gridy += GRID_Y_CURRENT;
	        this.setButton(SetupConfigurationPanelEnums.EXIT_BUTTON_KEY, "Cancel");
	        this.add(this.components.get(SetupConfigurationPanelEnums.EXIT_BUTTON_KEY), grid);

	        this.grid.anchor = GridBagConstraints.WEST;
	        this.grid.gridx += 1;
	        this.setAdminButton(SetupConfigurationPanelEnums.SUBMIT_BUTTON_KEY, "Submit");
	        this.add(this.components.get(SetupConfigurationPanelEnums.SUBMIT_BUTTON_KEY), grid);

	        this.setupConfigurationController = new SetupConfigurationController(this);
		});
	}

	// ------------------------------------------------------------------------
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

	// ------------------------------------------------------------------------
	private void setButton(SetupConfigurationPanelEnums buttonKey, String buttonText) {

		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
        //this.grid.anchor = GridBagConstraints.CENTER;
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;

        this.components.put(buttonKey, button);
	}

	// ------------------------------------------------------------------------
	private void setAdminButton(SetupConfigurationPanelEnums buttonKey, String buttonText) {

		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
        //this.grid.anchor = GridBagConstraints.CENTER;
		button.setEnabled(false);

        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        button.setOpaque(false);

        this.components.put(buttonKey, button);
	}

	// ------------------------------------------------------------------------
	public void setTextField(SetupConfigurationPanelEnums textFieldKey) {
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

	// ------------------------------------------------------------------------
	public void setPasswordField(SetupConfigurationPanelEnums passwordFieldKey) {
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

	// ------------------------------------------------------------------------
	private void setLabelField(SetupConfigurationPanelEnums labelKey, String labelText) {
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

	// ------------------------------------------------------------------------
	public PanelCentral getPanelCentral() {
		return this.panelCentral;
	}

	// ------------------------------------------------------------------------
	public ConcurrentHashMap<SetupConfigurationPanelEnums, JComponent> getComponentsMap() {
		return this.components;
	}

	// ------------------------------------------------------------------------
	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
	}
}
