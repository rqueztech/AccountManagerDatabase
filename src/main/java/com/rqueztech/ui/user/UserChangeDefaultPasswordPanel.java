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
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.rqueztech.controllers.user.UserChangeDefaultPasswordController;
import com.rqueztech.ui.BaseFrame;
import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.buttons.ButtonTemplates;
import com.rqueztech.ui.passwordfields.PasswordFieldTemplates;
import com.rqueztech.ui.user.enums.UserChangeDefaultPasswordEnums;

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

	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;

	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEWEIGHT = 1;

	private final int TEXTFIELD_SIZE = 10;

	private UserChangeDefaultPasswordController userChangeDefaultPasswordController;

	// --- Group 2: Panel Map ---
	private ConcurrentHashMap<UserChangeDefaultPasswordEnums, JComponent> components;

	// ------------------------------------------------------------------------
	public UserChangeDefaultPasswordPanel(BaseFrame frame,
			GridBagLayout layout, PanelCentral panelCentral) {
		// Function that will toggle visibility on and off in password
		// Field found in this class
		this.panelCentral = panelCentral;

		// Dispatch responsibilities on EDT.
		SwingUtilities.invokeLater(() -> {

			this.setLayout(layout);	// Set the layout to the inherited Gridbag 
									//Layout (layout)

			this.setPreferredSize(
					new Dimension(frame.getHeight(), frame.getWidth())); // Set the size to the
	        																			// dimensions of the BaseFrame
	        this.image = new ImageIcon(getClass().getResource("/images/backgroundd.jpg")).getImage();
	        this.components = new ConcurrentHashMap<UserChangeDefaultPasswordEnums, JComponent> ();

	        // --- Start Constraints ---
	        // Set all of the constraints for the background image
	        this.setBackgroundImageConstraints();
	        frame.add(this, this.grid);
	        //--- Finish Constraints End ---


	        this.setComponentMainPosition();
	        this.setLabelField(UserChangeDefaultPasswordEnums.ENTERPASSWORD_LABEL_KEY, "Enter New Password");
	        this.add(this.components.get(UserChangeDefaultPasswordEnums.ENTERPASSWORD_LABEL_KEY), grid);

	        this.grid.gridx = 0;
	        this.grid.gridy += 1;
	        this.setPasswordField(UserChangeDefaultPasswordEnums.ENTERPASSWORD_TEXTFIELD_KEY);
	        this.add(this.components.get(UserChangeDefaultPasswordEnums.ENTERPASSWORD_TEXTFIELD_KEY), grid);

	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
	        this.setButton(UserChangeDefaultPasswordEnums.ENTERPASSWORD_VISIBILITY_BUTTON_KEY, "Visible");
	        this.add(this.components.get(UserChangeDefaultPasswordEnums.ENTERPASSWORD_VISIBILITY_BUTTON_KEY), grid);


	        this.grid.gridx = 0;
	        this.grid.gridy += 1;
	        this.setLabelField(UserChangeDefaultPasswordEnums.CONFIRMPASSWORDPASSWORD_LABEL_KEY, "Confirm New Password");
	        this.add(this.components.get(UserChangeDefaultPasswordEnums.CONFIRMPASSWORDPASSWORD_LABEL_KEY), grid);

	        this.grid.gridx = 0;
			this.grid.gridy += 1;
	        this.setPasswordField(UserChangeDefaultPasswordEnums.CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY);
	        this.add(this.components.get(UserChangeDefaultPasswordEnums.CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY), grid);

	        this.grid.gridx += 3; // Buttons are not fixed, therefore coordinates are custom set
	        this.setButton(UserChangeDefaultPasswordEnums.CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY, "Visibile");
	        this.add(this.components.get(UserChangeDefaultPasswordEnums.CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY), grid);

	        this.grid.gridx = 0;
	        this.grid.gridy += 1;
	        this.setButton(UserChangeDefaultPasswordEnums.CANCEL_CHANGE_BUTTON_KEY, "Cancel");
	        this.add(this.components.get(UserChangeDefaultPasswordEnums.CANCEL_CHANGE_BUTTON_KEY), grid);

	        this.grid.gridx = 1;
	        this.setSubmitButton(UserChangeDefaultPasswordEnums.SUBMIT_LOGIN_BUTTON_KEY, "Submit");
	        this.add(this.components.get(UserChangeDefaultPasswordEnums.SUBMIT_LOGIN_BUTTON_KEY), grid);

	        this.userChangeDefaultPasswordController = new UserChangeDefaultPasswordController(this);
		});
	}

	// ------------------------------------------------------------------------
	private void setComponentMainPosition() {
		this.grid.insets = new Insets(2, 2, 2, 2);
        this.grid.gridx = GRID_X_INITIAL;
        this.grid.gridy = GRID_Y_INITIAL;
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
	private void setButton(UserChangeDefaultPasswordEnums buttonKey, String buttonText) {
		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
        this.grid.anchor = GridBagConstraints.CENTER;
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;

        this.components.put(buttonKey, button);
	}

	// ------------------------------------------------------------------------
	private void setSubmitButton(UserChangeDefaultPasswordEnums buttonKey, String buttonText) {
		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
        this.grid.anchor = GridBagConstraints.CENTER;

        button.setOpaque(false);
        button.setEnabled(false);
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;

        this.components.put(buttonKey, button);
	}

	// ------------------------------------------------------------------------
	public void setPasswordField(UserChangeDefaultPasswordEnums passwordFieldKey) {
		PasswordFieldTemplates passwordField = new PasswordFieldTemplates(Color.WHITE, Color.BLACK, TEXTFIELD_SIZE);
		this.grid.anchor = GridBagConstraints.CENTER;
		this.grid.gridwidth = 2;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;

        this.components.put(passwordFieldKey, passwordField);
	}

	// ------------------------------------------------------------------------
	private void setLabelField(UserChangeDefaultPasswordEnums labelKey, String labelText) {
		JLabel labelField = new JLabel(labelText);
		this.grid.anchor = GridBagConstraints.CENTER;
		labelField.setBackground(Color.BLACK);
		labelField.setForeground(Color.WHITE);
		this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;

        this.components.put(labelKey, labelField);
	}

	// ------------------------------------------------------------------------
	public ConcurrentHashMap<UserChangeDefaultPasswordEnums, JComponent> getComponentsMap() {
		return this.components;
	}

	// ------------------------------------------------------------------------
	public PanelCentral getPanelCentral() {
		return this.panelCentral;
	}

	// ------------------------------------------------------------------------
	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
	}
}
