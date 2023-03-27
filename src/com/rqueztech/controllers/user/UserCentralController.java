package com.rqueztech.controllers.user;

import javax.swing.JButton;

import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.user.UserCentralPanel;

public class UserCentralController {
	private UserCentralPanel userCentralPanel;
	
	public UserCentralController(UserCentralPanel userCentralPanel) {
		this.userCentralPanel = userCentralPanel;
		this.logoutButtonActionListener();
	}
	
	// --------------------------------------------------------------------------------------
	public void logoutButtonActionListener() {
		JButton adminLogin = (JButton) this.userCentralPanel.components().get("USER_LOGOUT_BUTTON_KEY");
		
		adminLogin.addActionListener(e -> {
			this.userCentralPanel.setVisible(false);
			this.userCentralPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.LOGOUT_SUCCESS_PANEL).setVisible(true);
		});
	}
}