/**
This class represents the central panel containing all the other panels using CardLayout.
It extends JFrame and contains a HashMap of all the panels as well as an image to be used as the background.
*/

package com.rqueztech.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JPanel;

import com.rqueztech.ui.configuration.SetupAgreementPanel;
import com.rqueztech.ui.configuration.SetupConfigurationPanel;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.user.UserCentralPanel;
import com.rqueztech.ui.user.UserChangeDefaultPasswordPanel;
import com.rqueztech.ui.validation.InputValidations;

public class PanelCentral extends JPanel {
	
	private static final long serialVersionUID = -652692111395861275L;
	
	// Variables for the image and the card layout
	public Image image;
	private JPanel cards;
	
	// Reference to the BaseFrame objects
	private final BaseFrame frame;
	private InputValidations inputValidations = new InputValidations();
	
	
	public PanelCentral(BaseFrame frame) {
		
		
		// Create all panels and add them to the card layout
		this.frame = frame;
		this.setBackground(Color.black);
		this.createAllPanels();
		
		// Add this panel to the BaseFrame object
		frame.addPanel(this);
		frame.setVisible(true);
		
		// Show the SetupAgreementPanel by default
		CardLayout cl = (CardLayout)(cards.getLayout());
		cl.show(cards, PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL.toString());
	}
	
	// Create all panels to be 	
	public void createAllPanels() {
		
		// Create a new JPanel with CardLayout
	    cards = new JPanel(new CardLayout());
	    
	    // Add all the panels to the cards JPanel
	    //cards.setPreferredSize(new Dimension(600,600));
	    
	    cards.add(new MainLoginPanel(frame), PanelCentralEnums.MAIN_LOGIN_PANEL.toString());
	    cards.add(new UserChangeDefaultPasswordPanel(frame, this.inputValidations), PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL.toString());
	    cards.add(new UserCentralPanel(frame), PanelCentralEnums.USER_CENTRAL_PANEL.toString());
	    cards.add(new SetupConfigurationPanel(frame), PanelCentralEnums.SETUP_CONFIGURATION_PANEL.toString());
	    cards.add(new SetupAgreementPanel(frame), PanelCentralEnums.SETUP_AGREEMENT_PANEL.toString());
	    
	    this.add(cards);
	}
}
