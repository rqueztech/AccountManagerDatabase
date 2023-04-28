package main.com.rqueztech.ui.events;

import javax.swing.JPasswordField;

/**
* The TogglePasswordVisibility class contains a method for toggling the
visibility of a JPasswordField input.
*/
public class TogglePasswordVisibility {
  private final int passwordPlainTextAscii = 0;
  private final int passwordEncryptedTextAscii = 8226;

  /**
  * The TogglePasswordVisibility constructor taking the password field
  as an argument.
  *
  * @param passwordField object holding the current password as
  (a JPasswordField)
  */
  public void passwordToggler(JPasswordField passwordField) {

    // Get the current character representing input from the user
    char currentEchoChar = passwordField.getEchoChar();

    // TURN ON VISIBILITY: If the input is encrypted characters, convert it to plain text
    // TURN OFF VISIBILITY: If the input is plaintext, encrypt it again
    if (currentEchoChar == (char) passwordEncryptedTextAscii) {
      passwordField.setEchoChar((char) passwordPlainTextAscii);
    } else {
      passwordField.setEchoChar((char) passwordEncryptedTextAscii);
    }
  }
}
