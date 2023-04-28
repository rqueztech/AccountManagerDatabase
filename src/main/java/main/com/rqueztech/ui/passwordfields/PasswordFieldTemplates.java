package main.com.rqueztech.ui.passwordfields;

import java.awt.Color;
import javax.swing.JPasswordField;
/**
 * The PasswordFieldTemplates class extends the JPasswordField class to create
 * custom password fields with a specified background color, foreground color, and size.
 *
 * @author Ricardo Quezada
 * @version 1.0
 *
 */

public class PasswordFieldTemplates extends JPasswordField {

  private static final long serialVersionUID = 5199761523331905717L;

  /**
   * Constructs a new PasswordFieldTemplates with the specified background
   * color, foreground color, and size.
   *
   * @param backgroundColor The background color of the password field
   * @param foregroundColor The foreground color of the password field
   * @param size The number of columns to use to calculate the preferred width
   */
  public PasswordFieldTemplates(Color backgroundColor, Color foregroundColor, int size) {
    super(size);
    this.setBackground(backgroundColor);
    this.setForeground(foregroundColor);
  }
}
