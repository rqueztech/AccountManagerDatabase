package com.rqueztech.ui;

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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import com.rqueztech.ui.events.TogglePasswordVisibility;

public class MainLoginPanel extends JPanel {
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private JFrame frame;
	private Image image;
	private GridBagConstraints grid;

	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 0;
	private final int RIGHT_INSET = 0;
	private final String USERNAME_LABEL = "Enter Username";
	private final String USERNAME_FIELD = "USERNAME_FIELD";
	private final String VISIBILITY_BUTTON = "Visible";
	private final String PASSWORD_LABEL = "Enter Password";
	private final String PASSWORD_FIELD = "PASSWORD_FIELD";
	private final String USER_BUTTON = "User";
	private final String ADMIN_BUTTON = "Admin";
	
	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;
	
	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEHEIGHT = 1;
	
	private final int TEXTFIELD_SIZE = 20;
	
	private TogglePasswordVisibility togglePasswordVisibility;
	
	private int newYValue = 0;
	
	// --- Group 2: Panel Map ---
	private ConcurrentHashMap <String, JComponent> components;
	
	// --------------------------------------------------------------------------------------
	public MainLoginPanel(BaseFrame frame, GridBagLayout layout) {
		// Dispatch responsibilities on EDT.
		SwingUtilities.invokeLater(() -> {
	        
			// Set the panel to the gridbaglayout, establish the preferred size,
			// And get the image that will be used in the background
			this.setLayout(layout);
	        this.setPreferredSize(new Dimension(BaseFrame.WIDTH, BaseFrame.HEIGHT));
	        this.image = new ImageIcon("backgroundd.jpg").getImage();
	        
	        // --- Start Constraints ---
	        // Set all of the constraints for the background image
	        this.setBackgroundImageConstraints();
	        frame.add(this, this.grid);
	        //--- Finish Constraints End ---
	        
	        this.grid.insets = new Insets(2, 2, 2, 2);
	        
	        this.grid.gridx = GRID_X_INITIAL;
	        this.grid.gridy = GRID_Y_INITIAL;
	        
	        this.setLabelField(USERNAME_LABEL);
	        
	        this.grid.gridy += 1;
	        this.setTextField();
	        
	        this.grid.gridx = 0;
	        this.grid.gridy += 1;
	        
	        this.setLabelField(PASSWORD_LABEL);
	        
	        this.grid.gridy += 1;
	        this.setTextField();
	        
	        this.grid.gridx += 3;
	        this.setButton(VISIBILITY_BUTTON);
	        
	        this.grid.gridy += 1;
	        this.grid.gridx = 0;
	        this.setButton(USER_BUTTON);
	        
	        this.grid.gridx = 1;
	        this.setButton(ADMIN_BUTTON);
	    });
	}


	
	// --------------------------------------------------------------------------------------
	public void setBackgroundImageConstraints() {
		// Set layout, width, and image in the background
		this.grid = new GridBagConstraints();
        this.grid.fill = GridBagConstraints.BOTH;
        this.grid.weightx = GRIDX_IMAGEWEIGHT;
        this.grid.weighty = GRIDY_IMAGEHEIGHT;
        this.grid.insets = new Insets(TOP_INSET, LEFT_INSET, BOTTOM_INSET, RIGHT_INSET);
	}
	
	// --------------------------------------------------------------------------------------
	public void setButton(String buttonName) {
		JButton button = new JButton(buttonName);
        this.grid.anchor = GridBagConstraints.CENTER;
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        this.add(button, this.grid);
	}
	
	// --------------------------------------------------------------------------------------
	public void setTextField() {
		JTextField textField = new JTextField(TEXTFIELD_SIZE);
		this.grid.anchor = GridBagConstraints.CENTER;
		textField.setBackground(Color.WHITE);
		textField.setForeground(Color.BLACK);
		this.grid.gridwidth = 2;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        this.add(textField, this.grid); 
	}
	
	// --------------------------------------------------------------------------------------
	public void setLabelField(String labelName) {
		JLabel labelField = new JLabel(labelName);
		this.grid.anchor = GridBagConstraints.CENTER;
		labelField.setBackground(Color.BLACK);
		labelField.setForeground(Color.WHITE);
		this.grid.gridwidth = 2;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        this.add(labelField, this.grid); 
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
	}
}
