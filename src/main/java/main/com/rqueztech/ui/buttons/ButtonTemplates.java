package main.com.rqueztech.ui.buttons;

import java.awt.Color;
import javax.swing.JButton;

/**
 * The button templates class is responsible for setting the properties for
 button components in the project.
 *
 * @extends JButton
 */
public class ButtonTemplates extends JButton {
  /**
   * Serialize the UID object.
   */
  private static final long serialVersionUID = -6771142241681869396L;

  /**
   * Sets the button colors and text.
   *
   * @param buttonText sets the text for the button as (a String)
   * @param backgroundColor sets the background color for the button as (a Color Object)
   * @param foregroundColor sets the foreground color for the button as (a Color Object)
   */
  public ButtonTemplates(String buttonText, Color backgroundColor, Color foregroundColor) {
    super(buttonText);
    this.setBackground(backgroundColor);
    this.setForeground(foregroundColor);
  }
}
