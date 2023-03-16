/**
This class represents the central panel containing all the other panels using CardLayout.
It extends JFrame and contains a HashMap of all the panels as well as an image to be used as the background.
*/

package com.rqueztech.ui;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JPanel;

import com.rqueztech.ui.configuration.SetupAgreementPanel;
import com.rqueztech.ui.configuration.SetupConfigurationPanel;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.user.UserCentralPanel;
import com.rqueztech.ui.user.UserChangeDefaultPasswordPanel;

public class PanelCentral extends JPanel {
	
	private static final long serialVersionUID = -652692111395861275L;
	
	// Variables for the image and the card layout
	public Image image;
	
	// Reference to the BaseFrame objects
	private final BaseFrame frame;
	private final ConcurrentHashMap <PanelCentralEnums, JPanel> panels = new ConcurrentHashMap<PanelCentralEnums, JPanel>();
	
	public PanelCentral(BaseFrame frame) {
		// Create all panels and add them to the card layout
		this.frame = frame;
		this.setLayout(null);
		this.setLayout(new GridBagLayout());
		
		Scanner sc = new Scanner(System.in);
		
		panels.put(PanelCentralEnums.MAIN_LOGIN_PANEL, new MainLoginPanel(frame));
		panels.put(PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL, new UserChangeDefaultPasswordPanel(frame));
		panels.put(PanelCentralEnums.USER_CENTRAL_PANEL, new UserCentralPanel(frame));
		panels.put(PanelCentralEnums.SETUP_CONFIGURATION_PANEL, new SetupConfigurationPanel(frame));
		panels.put(PanelCentralEnums.SETUP_AGREEMENT_PANEL, new SetupAgreementPanel(frame));
		
		System.out.println(panels);
		
		while(true) {
			int number = sc.nextInt();
			
			for(JPanel panel : panels.values()) {
				panel.setVisible(false);
			}
			
			switch(number) {
			
				case 1:
					//panels.put(PanelCentralEnums.MAIN_LOGIN_PANEL, new MainLoginPanel(frame));
					//panels.get(PanelCentralEnums.MAIN_LOGIN_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.MAIN_LOGIN_PANEL).setVisible(true);
					break;
					
					
				case 2:
					//panels.put(PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL, new UserChangeDefaultPasswordPanel(frame));
					//panels.get(PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL).setVisible(true);
					break;
					
					
				case 3:
					//panels.put(PanelCentralEnums.USER_CENTRAL_PANEL, new UserCentralPanel(frame));
					//panels.get(PanelCentralEnums.USER_CENTRAL_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.USER_CENTRAL_PANEL).setVisible(true);
					break;
					
					
				case 4:
					//panels.put(PanelCentralEnums.SETUP_CONFIGURATION_PANEL, new SetupConfigurationPanel(frame));
					//panels.get(PanelCentralEnums.SETUP_CONFIGURATION_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.SETUP_CONFIGURATION_PANEL).setVisible(true);
					break;
					
					
				case 5:
					//panels.put(PanelCentralEnums.SETUP_AGREEMENT_PANEL, new SetupAgreementPanel(frame));
					//panels.get(PanelCentralEnums.SETUP_AGREEMENT_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.SETUP_AGREEMENT_PANEL).setVisible(true);
					break;
		
			}
			
			frame.setVisible(true);
		}
	}
}
