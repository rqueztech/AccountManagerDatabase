package main.com.rqueztech.ui.events;

import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.com.rqueztech.ui.validation.InputValidations;


/**
 * The ChangePasswordDocumentListener class sets all of the events to process
 the changing of the users password.
 *
 * @implements DocumentListener
 */
public class ChangePasswordDocumentListener implements DocumentListener {
  private JButton adminButton;
  private JPasswordField passwordField;
  private JPasswordField confirmPasswordField;
  private InputValidations inputValidations;

  /**
   * The ChangePasswordDocumentListener constructor sets the action listeners
   and components to be used in the Change Password form.
   *
   * @param adminButton passes the button to be used in the form as (JButton)
   * @param passwordField the password field to be listened to as (JPasswordField)
   * @param confirmPasswordField the password field to be listened to as
   (JPasswordField)
   */
  public ChangePasswordDocumentListener(
      JButton adminButton,
      JPasswordField passwordField,
      JPasswordField confirmPasswordField) {

    this.inputValidations = new InputValidations();
    this.adminButton = adminButton;
    this.passwordField = passwordField;
    this.confirmPasswordField = confirmPasswordField;

  }

  /**
   * Checks if the password fields are not null and have a length greater than 0.
   *
   * @return true if both password fields are not null and have a length greater
   than 0, false otherwise.
   */
  public boolean isNullCheckPass() {
    // Check to see that none of the passwords are equal to null. There
    // Is no point to proceed if so.
    if (this.passwordField != null && this.confirmPasswordField != null
          && this.passwordField.getPassword().length > 0
          && this.confirmPasswordField.getPassword().length > 0) {
      return true;
    }

    return false;
  }

  /**
   * The isPasswordMatch function ensures that both the password and confirm password field
   values are equal before being input.
   */
  public boolean isPasswordsMatch() {
    // If the password fields match, return true. Passwords do match
    if (Arrays.equals(passwordField.getPassword(),
        confirmPasswordField.getPassword())) {
      return true;
    }

    // If the password fields don't match, return false
    return false;
  }

  /**
   * The isPasswordValid function ensures that both the password and confirm password field
   meet the minimum requirements and contain no illegal characters.
   */
  public boolean isPasswordsValid() {
    if (this.inputValidations.validatePassword(this.passwordField.getPassword())
          && this.inputValidations.validatePassword(this.confirmPasswordField.getPassword())) {
      return true;
    }

    return false;
  }

  // Toggle the opacity
  private void opacityToggleOn() {
    this.adminButton.setOpaque(true);
    this.adminButton.setEnabled(true);
  }

  private void opacityToggleOff() {
    this.adminButton.setOpaque(false);
    this.adminButton.setEnabled(false);
  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    if (this.isNullCheckPass() && this.isPasswordsValid()
          && this.isPasswordsMatch()) {
      this.opacityToggleOn();
    } else {
      this.opacityToggleOff();
    }

  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    if (this.isNullCheckPass() && this.isPasswordsValid()
          && this.isPasswordsMatch()) {
      this.opacityToggleOn();
    } else {
      this.opacityToggleOff();
    }

  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    if (this.isNullCheckPass() && this.isPasswordsValid()
          && this.isPasswordsMatch()) {
      this.opacityToggleOn();
    } else {
      this.opacityToggleOff();
    }
  }
}
