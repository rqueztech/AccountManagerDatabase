package com.rqueztech.controllers;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.rqueztech.ui.MainLoginPanel;
import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.enums.MainLoginPanelEnums;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.events.TogglePasswordVisibility;

public class MainLoginControl {
	private MainLoginPanel mainLoginPanel;
	private PanelCentral panelCentral;
	private TogglePasswordVisibility togglePasswordVisibility;

	public MainLoginControl(MainLoginPanel mainLoginPanel,
			PanelCentral panelCentral) {

		this.mainLoginPanel = mainLoginPanel;
		this.panelCentral = panelCentral;
		this.togglePasswordVisibility = new TogglePasswordVisibility();

		this.setControllerActionListeners();
	}

	// ------------------------------------------------------------------------
	private void setControllerActionListeners() {
		this.userButtonActionListener();
        this.adminButtonActionListener();
        this.togglePasswordVisibility();
	}

	// ------------------------------------------------------------------------
	private void resetFields() {
		for(Component component : this.mainLoginPanel
				.getComponentsMap().values()) {

			if (component instanceof JPasswordField) {
				((JPasswordField) component).setText("");
				Arrays.fill(((JPasswordField) component).getPassword(), '\0');
			}

			else if (component instanceof JTextField) {
				((JTextField) component).setText("");
			}
		}
	}

	// ------------------------------------------------------------------------
	private void userButtonActionListener() {
		JButton userButton = (JButton) this.mainLoginPanel.getComponentsMap()
				.get(MainLoginPanelEnums.USER_LOGIN_BUTTON_KEY);

		userButton.addActionListener(e -> {
			this.cleanUpNavigateAway();
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums
					.USER_CHANGE_DEFAULT_PASSWORD_PANEL).setVisible(true);
		});
	}

	// ------------------------------------------------------------------------
	private void adminButtonActionListener() {
		JButton adminButton = (JButton) this.mainLoginPanel.getComponentsMap()
				.get(MainLoginPanelEnums.ADMIN_LOGIN_BUTTON_KEY);

		adminButton.addActionListener(e -> {
			this.cleanUpNavigateAway();
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums
					.ADMIN_CENTRAL_PANEL).setVisible(true);
		});
	}

	// ------------------------------------------------------------------------
	private void togglePasswordVisibility() {
		JButton toggleButton = (JButton) this.mainLoginPanel.getComponentsMap().
				get(MainLoginPanelEnums.VISIBILITY_BUTTON_KEY);

		toggleButton.addActionListener( e -> {
			JPasswordField passwordTextField =
					(JPasswordField) this.mainLoginPanel
					.getComponentsMap()
					.get(MainLoginPanelEnums.PASSWORD_TEXTFIELD_KEY);

			this.togglePasswordVisibility.passwordToggler(passwordTextField);
		});
	}

	// ------------------------------------------------------------------------
	private void cleanUpNavigateAway() {
		this.mainLoginPanel.setVisible(false);
		this.resetFields();
	}
}
