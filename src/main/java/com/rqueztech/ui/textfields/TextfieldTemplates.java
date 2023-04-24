package com.rqueztech.ui.textfields;

import java.awt.Color;

import javax.swing.JTextField;

public class TextfieldTemplates extends JTextField {
	/**
	 *
	 */
	private static final long serialVersionUID = 4552544287796735981L;

	public TextfieldTemplates(Color backgroundColor, Color foregroundColor, int size) {
		super(size);
		this.setBackground(backgroundColor);
		this.setForeground(foregroundColor);
	}
}
