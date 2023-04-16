package com.rqueztech.ui.passwordfields;

import java.awt.Color;

import javax.swing.JPasswordField;

public class PasswordFieldTemplates extends JPasswordField {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5199761523331905717L;

	public PasswordFieldTemplates(Color backgroundColor, Color foregroundColor, int size) {
		super(size);
		this.setBackground(backgroundColor);
		this.setForeground(foregroundColor);
	}
}
