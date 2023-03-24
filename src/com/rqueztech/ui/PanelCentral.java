/**
This class represents the central panel containing all the other panels using CardLayout.
It extends JFrame and contains a ConcurrentHashMap of all the panels as well as an image to be used as the background.
*/

package com.rqueztech.ui;

import java.awt.Image;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JPanel;

import com.rqueztech.ui.enums.PanelCentralEnums;

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
	}
	
	// 
	public ConcurrentHashMap<PanelCentralEnums, JPanel> getCurrentPanel() {
		return this.panels;
	}
	
	public void setConcurrentHashMap(PanelCentralEnums panelCentralEnum, JPanel panel) {
		this.panels.put(panelCentralEnum, panel);
	}
}
