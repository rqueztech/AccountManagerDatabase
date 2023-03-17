package com.rqueztech.ui.user;

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
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import com.rqueztech.ui.BaseFrame;

public class UserCentralPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6576995148312400023L;
	// --- Group 1: Panel related variables ---
	private JFrame frame;
	private Image image;
	private GridBagConstraints grid;

	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 2;
	private final int RIGHT_INSET = 0;
	private final int GRID_X = 0;
	private final int GRID_Y = 0;
	
	/*
	private final int GRID_Y_INCREMENT = 1;
	private final int TEXTFIELD_LENGTH = 15;
	*/
	
	private ConcurrentHashMap <String, JComponent> components;
	
	public UserCentralPanel(BaseFrame frame, GridBagLayout layout) {
		SwingUtilities.invokeLater(() -> {
			this.frame = frame;
			
			this.setLayout(layout);
			this.grid = new GridBagConstraints();
			this.setPreferredSize(new Dimension(frame.getWidth(), frame.getHeight()));
			
			this.image = new ImageIcon("backgroundd.jpg").getImage();
			this.components = new ConcurrentHashMap <String, JComponent> ();
			
			this.setUserCentralComponents();
		
			this.frame.add(this);
		});
	}
	
	public void setUserCentralComponents() {
		this.grid.insets = new Insets(TOP_INSET, LEFT_INSET, BOTTOM_INSET, RIGHT_INSET);
		this.grid.gridx = GRID_X;
		this.grid.gridy = GRID_Y;
				
		this.addRightButton("Balance", 0);
		this.addLeftButton("Add", 1);
	}
	
	private void addLeftButton(String leftButtonName, int xCoordinate) {
		JButton leftButton = new JButton(leftButtonName);
		
		leftButton.setForeground(Color.GRAY);
		leftButton.setBackground(Color.BLACK);
		
		this.grid.gridwidth = 1;
		this.grid.gridx = xCoordinate;
		this.grid.anchor = GridBagConstraints.WEST;
		this.grid.fill = GridBagConstraints.HORIZONTAL;
		this.components.put(leftButtonName, leftButton);
		
		this.add(leftButton, this.grid);
	}
	
	private void addRightButton(String rightButtonName, int xCoordinate) {
		JButton rightButton = new JButton(rightButtonName);
		
		rightButton.setForeground(Color.GRAY);
		rightButton.setBackground(Color.BLACK);
		
		this.grid.gridwidth = 1;
		this.grid.gridx = xCoordinate;
		this.grid.anchor = GridBagConstraints.EAST;
		this.grid.fill = GridBagConstraints.HORIZONTAL;
		this.components.put(rightButtonName, rightButton);
		
		this.add(rightButton, this.grid);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
	}
}
