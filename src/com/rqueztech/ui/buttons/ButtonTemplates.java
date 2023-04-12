package com.rqueztech.ui.buttons;

import java.awt.Color;

import javax.swing.JButton;

public class ButtonTemplates extends JButton {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6771142241681869396L;

	// --------------------------------------------------------------------------------------
	public ButtonTemplates(String buttonText, Color backgroundColor, Color foregroundColor) {
		super(buttonText);
        this.setBackground(backgroundColor);
        this.setForeground(foregroundColor);
	}
}
