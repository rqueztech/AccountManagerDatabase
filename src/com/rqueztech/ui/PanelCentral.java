/**
This class represents the central panel containing all the other panels using CardLayout.
It extends JFrame and contains a ConcurrentHashMap of all the panels as well as an image to be used as the background.
*/

package com.rqueztech.ui;

import java.util.concurrent.ConcurrentHashMap;

import javax.swing.JPanel;

import com.rqueztech.ui.enums.PanelCentralEnums;

public class PanelCentral extends JPanel {
	
	private static final long serialVersionUID = -652692111395861275L;
	
	
	// Reference to the BaseFrame objects
	private final ConcurrentHashMap <PanelCentralEnums, JPanel> panels;
	
	public PanelCentral(BaseFrame frame) {
		// Create all panels and add them to the card layout
		this.panels = new ConcurrentHashMap<PanelCentralEnums, JPanel>();
		
		PanelCreationWorker panelCreationWorker = new PanelCreationWorker(this, frame, this.panels);
		panelCreationWorker.execute();
	}
	
	// 
	public ConcurrentHashMap<PanelCentralEnums, JPanel> getCurrentPanel() {
		return this.panels;
	}
	
	void setConcurrentHashMap(PanelCentralEnums panelCentralEnum, JPanel panel) {
		this.panels.put(panelCentralEnum, panel);
	}
}
