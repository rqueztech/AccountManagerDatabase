/**
This class represents the central panel containing all the other panels using CardLayout.
It extends JFrame and contains a ConcurrentHashMap of all the panels as well as an image to be used as the background.
*/

package com.rqueztech.ui;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

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
	private final ConcurrentHashMap <PanelCentralEnums, JPanel> panels;
	
	public PanelCentral(BaseFrame frame) {
		// Create all panels and add them to the card layout
		this.frame = frame;
		this.panels = new ConcurrentHashMap<PanelCentralEnums, JPanel>();
		
		PanelCreationWorker panelCreationWorker = new PanelCreationWorker(this, frame, this.panels);
		panelCreationWorker.execute();
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int number = sc.nextInt();
			
			for(JPanel panel : panels.values()) {
				panel.setVisible(false);
			}
			
			System.out.println(this.panels);
			
			switch(number) {
			
				case 1:
					//panels.put(PanelCentralEnums.MAIN_LOGIN_PANEL, new MainLoginPanel(frame));
					//panels.get(PanelCentralEnums.MAIN_LOGIN_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.MAIN_LOGIN_PANEL).setVisible(true);
					System.out.println(panels.get(PanelCentralEnums.MAIN_LOGIN_PANEL));
					break;
					
					
				case 2:
					//panels.put(PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL, new UserChangeDefaultPasswordPanel(frame));
					//panels.get(PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL).setVisible(true);
					System.out.println(panels.get(PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL));
					break;
					
					
				case 3:
					//panels.put(PanelCentralEnums.USER_CENTRAL_PANEL, new UserCentralPanel(frame));
					//panels.get(PanelCentralEnums.USER_CENTRAL_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.USER_CENTRAL_PANEL).setVisible(true);
					System.out.println(panels.get(PanelCentralEnums.USER_CENTRAL_PANEL));
					break;
					
					
				case 4:
					//panels.put(PanelCentralEnums.SETUP_CONFIGURATION_PANEL, new SetupConfigurationPanel(frame));
					//panels.get(PanelCentralEnums.SETUP_CONFIGURATION_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.SETUP_CONFIGURATION_PANEL).setVisible(true);
					System.out.println(panels.get(PanelCentralEnums.SETUP_CONFIGURATION_PANEL));
					break;
					
					
				case 5:
					//panels.put(PanelCentralEnums.SETUP_AGREEMENT_PANEL, new SetupAgreementPanel(frame));
					//panels.get(PanelCentralEnums.SETUP_AGREEMENT_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.SETUP_AGREEMENT_PANEL).setVisible(true);
					System.out.println(panels.get(PanelCentralEnums.SETUP_AGREEMENT_PANEL));
					break;
		
				case 6:
					//panels.put(PanelCentralEnums.LOGOUT_SUCCESS_PANEL, new SetupAgreementPanel(frame));
					//panels.get(PanelCentralEnums.LOGOUT_SUCCESS_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.LOGOUT_SUCCESS_PANEL).setVisible(true);
					System.out.println(panels.get(PanelCentralEnums.LOGOUT_SUCCESS_PANEL));
					break;
					
				case 7:
					//panels.put(PanelCentralEnums.ADMIN_CENTRAL_PANEL, new SetupAgreementPanel(frame));
					//panels.get(PanelCentralEnums.ADMIN_CENTRAL_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.ADMIN_CENTRAL_PANEL).setVisible(true);
					System.out.println(panels.get(PanelCentralEnums.ADMIN_CENTRAL_PANEL));
					break;
					
				case 8:
					//panels.put(PanelCentralEnums.ADMIN_CENTRAL_PANEL, new SetupAgreementPanel(frame));
					//panels.get(PanelCentralEnums.ADMIN_CENTRAL_PANEL).setVisible(false);
					panels.get(PanelCentralEnums.ADMIN_CENTRAL_PANEL).setVisible(true);
					System.out.println(panels.get(PanelCentralEnums.ADMIN_CENTRAL_PANEL));
					break;
			}
			
			frame.setVisible(true);
		}
		
	}
	
	public ConcurrentHashMap<PanelCentralEnums, JPanel> getPanel() {
		return this.panels;
	}
	
	public ConcurrentHashMap<PanelCentralEnums, JPanel> getConcurrentHashMap() {
		return this.panels;
	}
	
	public void setConcurrentHashMap(PanelCentralEnums panelCentralEnum, JPanel panel) {
		this.panels.put(panelCentralEnum, panel);
	}
}
