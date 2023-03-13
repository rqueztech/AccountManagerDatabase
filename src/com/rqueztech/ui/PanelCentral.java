/**
This class represents the central panel containing all the other panels using CardLayout.
It extends JFrame and contains a HashMap of all the panels as well as an image to be used as the background.
*/

package com.rqueztech.ui;

import java.awt.CardLayout;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.rqueztech.ui.configuration.SetupAgreementPanel;
import com.rqueztech.ui.configuration.SetupConfigurationPanel;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.user.UserCentralPanel;
import com.rqueztech.ui.user.UserChangeDefaultPasswordPanel;

public class PanelCentral extends JPanel {
	
	private static final long serialVersionUID = -652692111395861275L;
	
	// --- 
	public Image image;
	private JPanel cards;
	
	private final BaseFrame frame;
	
	public PanelCentral(BaseFrame frame) {
		
		// Create all panels and add them to the card layout
		this.frame = frame;
		this.createAllPanels();
		
		frame.addPanel(this);
		
		CardLayout cl = (CardLayout)(cards.getLayout());
		cl.show(cards, PanelCentralEnums.SETUP_AGREEMENT_PANEL.toString());
	}
	
	public void createAllPanels() {
	    cards = new JPanel(new CardLayout());

	    cards.add(new MainLoginPanel(frame), PanelCentralEnums.MAIN_LOGIN_PANEL.toString());
	    cards.add(new UserChangeDefaultPasswordPanel(frame), PanelCentralEnums.DEFAULT_PASSWORD_CHANGE_USER_PANEL.toString());
	    cards.add(new UserCentralPanel(frame), PanelCentralEnums.USER_CENTRAL_PANEL.toString());
	    cards.add(new SetupConfigurationPanel(frame), PanelCentralEnums.SETUP_CONFIGURATION_PANEL.toString());
	    cards.add(new SetupAgreementPanel(frame), PanelCentralEnums.SETUP_AGREEMENT_PANEL.toString());

	    this.add(cards);
	}
}
