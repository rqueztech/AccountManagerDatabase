package com.rqueztech.controllers.admin;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.rqueztech.ui.admin.AdminUserViewPanel;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.events.PasswordFieldListener;
import com.rqueztech.ui.events.TextFieldListener;

public class AdminUserViewController {
	private AdminUserViewPanel adminUserViewPanel;
	
	public AdminUserViewController(AdminUserViewPanel adminUserViewPanel) {
		this.adminUserViewPanel = adminUserViewPanel;
		this.invokeActionListeners();
	}
	
	// --------------------------------------------------------------------------------------
	public void invokeActionListeners() {
		this.userViewButtonListener();
        this.exitButtonActionListener();
        this.logoutButtonActionListener();
	}

	// --------------------------------------------------------------------------------------
	public void logoutButtonActionListener() {
		JButton userViewButton = (JButton) this.adminUserViewPanel.components().get("ADMIN_LOGOUT_BUTTON_KEY");

		userViewButton.addActionListener(e -> {
			this.adminUserViewPanel.setVisible(false);
			this.resetFields();
			this.adminUserViewPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.LOGOUT_SUCCESS_PANEL).setVisible(true);
		});
	}

	// --------------------------------------------------------------------------------------
	public void userViewButtonListener() {
		JButton userViewButton = (JButton) this.adminUserViewPanel.components().get("ADD_USER_BUTTON_KEY");

		userViewButton.addActionListener(e -> {
			this.adminUserViewPanel.setVisible(false);
			this.resetFields();
			this.adminUserViewPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.ADMIN_ADD_USER_PANEL).setVisible(true);
		});
	}

	// --------------------------------------------------------------------------------------
	public void exitButtonActionListener() {
		JButton adminLogin = (JButton) this.adminUserViewPanel.components().get("RETURN_CENTRAL_BUTTON_KEY");

		adminLogin.addActionListener(e -> {
			this.adminUserViewPanel.setVisible(false);
			this.resetFields();
			this.adminUserViewPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.ADMIN_CENTRAL_PANEL).setVisible(true);
		});
	}

	// --------------------------------------------------------------------------------------
	public void invokeDocumentListeners() {
		this.firstNameListener();
		this.lastNameListener();
		this.passphraseNameListener();
	}

	// --------------------------------------------------------------------------------------
	public void firstNameListener() {
		JTextField firstName = (JTextField) this.adminUserViewPanel.components().get("FIRSTNAME_TEXTFIELD_KEY");

		// Create a listener for the first name field
		TextFieldListener nameFieldListener =
				new TextFieldListener(firstName);

		firstName.getDocument().addDocumentListener(nameFieldListener);
	}

	// --------------------------------------------------------------------------------------
	public void lastNameListener() {
		JTextField lastName = (JTextField) this.adminUserViewPanel.components().get("LASTNAME_TEXTFIELD_KEY");

		// Listener for the last name field
		TextFieldListener lastNameFieldListener =
				new TextFieldListener(lastName);

		lastName.getDocument().addDocumentListener(lastNameFieldListener);
	}

	// --------------------------------------------------------------------------------------
	public void passphraseNameListener() {
		JPasswordField passphrase = (JPasswordField) this.adminUserViewPanel.components().get("PASSPHRASE_TEXTFIELD_KEY");
		// Listener for the last name field
		PasswordFieldListener passwordFieldListener =
				new PasswordFieldListener(passphrase);

		passphrase.getDocument().addDocumentListener(passwordFieldListener);
	}

	// --------------------------------------------------------------------------------------
	public void resetFields() {
		for(Component component : this.adminUserViewPanel.components().values()) {
			if(component instanceof JPasswordField) {
				((JPasswordField) component).setText("");
				Arrays.fill(((JPasswordField) component).getPassword(), '\0');
			}

			else if(component instanceof JTextField) {
				((JTextField) component).setText("");
			}
		}
	}
}
