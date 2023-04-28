package main.com.rqueztech.ui.textfields;

import java.awt.Color;
import javax.swing.JTextField;

/**
 * Textfield templates is a simple class that sets the text field components
 * to avoid repetition in setting attributes when creating new textfields.
 */
public class TextfieldTemplates extends JTextField {
  /**
   * Serializes the current java swing textfield.
   */
  private static final long serialVersionUID = 4552544287796735981L;

  /**
   * TextFieldTemplates is responsible for setting the colors and size for the textfield.
   *
   * @param backgroundColor sets the background color for the textfield as (a Color)
   * @param foregroundColor sets the background color for the textfield as (a Color)
   * @param size sets the size for the textfield as (a integer)
   */
  public TextfieldTemplates(Color backgroundColor, Color foregroundColor, int size) {
    super(size);
    this.setBackground(backgroundColor);
    this.setForeground(foregroundColor);
  }
}
