package com.rqueztech.controllers.configuration;

import javax.swing.JButton;

import com.rqueztech.ui.configuration.SetupAgreementPanel;
import com.rqueztech.ui.enums.PanelCentralEnums;

public class SetupAgreementController {
	private SetupAgreementPanel setupAgreementPanel;
	
	public SetupAgreementController(SetupAgreementPanel setupAgreementPanel) {
		this.setupAgreementPanel = setupAgreementPanel;
		this.setupAgreementActionListener();
	}
	
	// --------------------------------------------------------------------------------------
	public void setupAgreementActionListener() {
		JButton configurationButton = (JButton) this.setupAgreementPanel.getComponentsMap().get("SETUPAGREEMENT_BUTTON_KEY");
		
		configurationButton.addActionListener(e -> {
			this.setupAgreementPanel.setVisible(false);
			this.setupAgreementPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.SETUP_CONFIGURATION_PANEL).setVisible(true);
		});
	}
}
