package com.rqueztech.ui.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.rqueztech.controllers.admin.AdminCentralController;
import com.rqueztech.ui.BaseFrame;
import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.admin.enums.AdminCentralPanelEnums;
import com.rqueztech.ui.buttons.ButtonTemplates;

public class AdminCentralPanel extends JPanel {
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private Image image;
	private GridBagConstraints grid;

	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 0;
	private final int RIGHT_INSET = 0;
	
	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;
	
	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEWEIGHT = 1;
	
	private AdminCentralController adminCentralController;
	
	// --- Group 2: Panel Map ---
	private ConcurrentHashMap <AdminCentralPanelEnums, JComponent> components;
	public PanelCentral panelCentral;
	
	// --------------------------------------------------------------------------------------
	public AdminCentralPanel(BaseFrame frame, GridBagLayout layout, PanelCentral panelCentral) {
		
		
		// Dispatch responsibilities on EDT.
		SwingUtilities.invokeLater(() -> {
	        
			// Set the panel to the gridbaglayout, establish the preferred size,
			// And get the image that will be used in the background
			this.setLayout(layout);
	        
			this.panelCentral = panelCentral;
			
			this.setPreferredSize(new Dimension(frame.getHeight(), frame.getWidth()));
	        this.image = new ImageIcon("backgroundd.jpg").getImage();
	        this.components = new ConcurrentHashMap <AdminCentralPanelEnums, JComponent> ();
	        
	        // --- Start Constraints ---
	        // Set all of the constraints for the background image
	        this.setBackgroundImageConstraints();
	        frame.add(this, this.grid);
	        //--- Finish Constraints End ---
	        
	        
	        this.setComponentMainPosition();
	        
	        this.grid.gridx = 0;
	        this.grid.gridy = 0;
	        this.setButton(AdminCentralPanelEnums.ADMIN_LOGOUT_BUTTON_KEY, "Logout");
	        this.add(this.components.get(AdminCentralPanelEnums.ADMIN_LOGOUT_BUTTON_KEY), grid);
	        
	        this.grid.gridx += 1;
	        this.setButton(AdminCentralPanelEnums.USER_VIEW_BUTTON_KEY, "User View");
	        this.add(this.components.get(AdminCentralPanelEnums.USER_VIEW_BUTTON_KEY), grid);
	        
	        this.grid.gridx += 1;
	        this.setButton(AdminCentralPanelEnums.ADMIN_ADD_USER_BUTTON_KEY, "Add user");
	        this.add(this.components.get(AdminCentralPanelEnums.ADMIN_ADD_USER_BUTTON_KEY), grid);
	        
	        this.adminCentralController = new AdminCentralController(this);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void setComponentMainPosition() {
		this.grid.insets = new Insets(2, 2, 2, 2);
        this.grid.gridx = GRID_X_INITIAL;
        this.grid.gridy = GRID_Y_INITIAL;
	}
	
	// --------------------------------------------------------------------------------------
	private void setBackgroundImageConstraints() {
		// Set everything to initial status.
		this.grid = new GridBagConstraints(); // Set the gridbag constraints
        this.grid.fill = GridBagConstraints.BOTH; // Fill both vertically and horizontally
        this.grid.gridx = GRID_X_INITIAL;
        this.grid.gridx = GRID_Y_INITIAL;
        this.grid.weightx = GRIDX_IMAGEWEIGHT; // Value is 0: initial
        this.grid.weighty = GRIDY_IMAGEWEIGHT; // Value is 0: initial
        this.grid.insets = new Insets(TOP_INSET, LEFT_INSET, BOTTOM_INSET, RIGHT_INSET); // Insets values all 0 (Initial)
	}
	
	// --------------------------------------------------------------------------------------
	private void setButton(AdminCentralPanelEnums buttonKey, String buttonText) {
		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
        this.grid.anchor = GridBagConstraints.CENTER;
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(buttonKey, button);
	}

	// --------------------------------------------------------------------------------------
	public ConcurrentHashMap<AdminCentralPanelEnums, JComponent> getComponentsMap() {
		return this.components;
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
	}
}
