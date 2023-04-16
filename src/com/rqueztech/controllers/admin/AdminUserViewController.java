package com.rqueztech.controllers.admin;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.rqueztech.ui.admin.AdminUserViewPanel;
import com.rqueztech.ui.admin.enums.AdminUserViewEnums;
import com.rqueztech.ui.enums.PanelCentralEnums;

public class AdminUserViewController {
	private AdminUserViewPanel adminUserViewPanel;
	
	public AdminUserViewController(AdminUserViewPanel adminUserViewPanel) {
		this.adminUserViewPanel = adminUserViewPanel;
		this.invokeActionListeners();
	}
	
	// --------------------------------------------------------------------------------------
	private void invokeActionListeners() {
		this.userViewButtonListener();
        this.exitButtonActionListener();
        this.logoutButtonActionListener();
	}

	// --------------------------------------------------------------------------------------
	private void logoutButtonActionListener() {
		JButton userViewButton = (JButton) this.adminUserViewPanel.getComponentsMap().get(AdminUserViewEnums.ADMIN_LOGOUT_BUTTON_KEY);

		userViewButton.addActionListener(e -> {
			this.adminUserViewPanel.setVisible(false);
			this.resetFields();
			this.adminUserViewPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.LOGOUT_SUCCESS_PANEL).setVisible(true);
		});
	}

	// --------------------------------------------------------------------------------------
	private void userViewButtonListener() {
		JButton userViewButton = (JButton) this.adminUserViewPanel.getComponentsMap().get(AdminUserViewEnums.ADD_USER_BUTTON_KEY);

		userViewButton.addActionListener(e -> {
			this.adminUserViewPanel.setVisible(false);
			this.resetFields();
			this.adminUserViewPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.ADMIN_ADD_USER_PANEL).setVisible(true);
		});
	}

	// --------------------------------------------------------------------------------------
	private void exitButtonActionListener() {
		JButton adminLogin = (JButton) this.adminUserViewPanel.getComponentsMap().get(AdminUserViewEnums.RETURN_CENTRAL_BUTTON_KEY);

		adminLogin.addActionListener(e -> {
			this.adminUserViewPanel.setVisible(false);
			this.resetFields();
			this.adminUserViewPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.ADMIN_CENTRAL_PANEL).setVisible(true);
		});
	}

	// --------------------------------------------------------------------------------------
	private void resetFields() {
		for(Component component : this.adminUserViewPanel.getComponentsMap().values()) {
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
