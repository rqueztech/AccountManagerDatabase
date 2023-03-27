package com.rqueztech.controllers;

import java.awt.Component;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.rqueztech.ui.MainLoginPanel;
import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.events.TogglePasswordVisibility;

public class MainLoginControl {
	private MainLoginPanel mainLoginPanel;
	private PanelCentral panelCentral;
	private TogglePasswordVisibility togglePasswordVisibility;
	
	public MainLoginControl(MainLoginPanel mainLoginPanel, PanelCentral panelCentral) {
		this.mainLoginPanel = mainLoginPanel;
		this.panelCentral = panelCentral;
		this.togglePasswordVisibility = new TogglePasswordVisibility();
		
		this.setControllerActionListeners();
	}
	
	// --------------------------------------------------------------------------------------
	public void setControllerActionListeners() {
		this.userButtonActionListener();
        this.adminButtonActionListener();
        this.togglePasswordVisibility();
	}
	
	// --------------------------------------------------------------------------------------
	public void resetFields() {
		for(Component component : this.mainLoginPanel.components().values()) {
			if(component instanceof JPasswordField) {
				((JPasswordField) component).setText("");
				Arrays.fill(((JPasswordField) component).getPassword(), '\0');
			}
			
			else if(component instanceof JTextField) {
				((JTextField) component).setText("");
			}
		}
	}
	
	// --------------------------------------------------------------------------------------
	public void userButtonActionListener() {
		JButton userButton = (JButton) this.mainLoginPanel.components().get("USER_LOGIN_BUTTON_KEY");
		
		userButton.addActionListener(e -> {
			this.cleanUpNavigateAway();
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void adminButtonActionListener() {
		JButton adminButton = (JButton) this.mainLoginPanel.components().get("ADMIN_LOGIN_BUTTON_KEY");
		
		adminButton.addActionListener(e -> {
			this.cleanUpNavigateAway();
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.ADMIN_CENTRAL_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void togglePasswordVisibility() {
		JButton toggleButton = (JButton) this.mainLoginPanel.components().get("VISIBILITY_BUTTON_KEY");
		
		toggleButton.addActionListener( e -> {
			JPasswordField passwordTextField = (JPasswordField) this.mainLoginPanel.components().get("PASSWORD_TEXTFIELD_KEY");
			this.togglePasswordVisibility.passwordToggler(passwordTextField);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void cleanUpNavigateAway() {
		this.mainLoginPanel.setVisible(false);
		this.resetFields();
	}
}
