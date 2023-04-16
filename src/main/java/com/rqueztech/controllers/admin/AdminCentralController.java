package com.rqueztech.controllers.admin;

import javax.swing.JButton;

import com.rqueztech.ui.admin.AdminCentralPanel;
import com.rqueztech.ui.admin.enums.AdminCentralPanelEnums;
import com.rqueztech.ui.enums.PanelCentralEnums;

public class AdminCentralController {
	private AdminCentralPanel adminCentralPanel;
	
	public AdminCentralController(AdminCentralPanel adminCentralPanel) {
		this.adminCentralPanel = adminCentralPanel;
		this.setActionListeners();
	}
	
	private void setActionListeners() {
		this.adminAddUserButtonListener();
        this.logoutButtonActionListener();
        this.userViewActionListener();
	}
	
	// --------------------------------------------------------------------------------------
	private void userViewActionListener() {
		JButton userViewButton = (JButton) this.adminCentralPanel.getComponentsMap().get(AdminCentralPanelEnums.USER_VIEW_BUTTON_KEY);
		
		userViewButton.addActionListener(e -> {
			this.adminCentralPanel.setVisible(false);
			this.adminCentralPanel.panelCentral.getCurrentPanel().get(PanelCentralEnums.ADMIN_USER_VIEW_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void adminAddUserButtonListener() {
		JButton adminLogin = (JButton) this.adminCentralPanel.getComponentsMap().get(AdminCentralPanelEnums.ADMIN_ADD_USER_BUTTON_KEY);
		
		adminLogin.addActionListener(e -> {
			this.adminCentralPanel.setVisible(false);
			this.adminCentralPanel.panelCentral.getCurrentPanel().get(PanelCentralEnums.ADMIN_ADD_USER_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void logoutButtonActionListener() {
		JButton adminLogin = (JButton) this.adminCentralPanel.getComponentsMap().get(AdminCentralPanelEnums.ADMIN_LOGOUT_BUTTON_KEY);
		
		adminLogin.addActionListener(e -> {
			this.adminCentralPanel.setVisible(false);
			this.adminCentralPanel.panelCentral.getCurrentPanel().get(PanelCentralEnums.LOGOUT_SUCCESS_PANEL).setVisible(true);
		});
	}
}
