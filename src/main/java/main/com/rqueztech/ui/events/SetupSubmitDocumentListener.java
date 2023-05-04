package main.com.rqueztech.ui.events;

import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.com.rqueztech.ui.validation.InputValidations;

/**
 * The SetupSubmitDocumentListener is responsible for making the initial setup
 panel responsive to user input. It toggles buttons green and red based on
 valid or invalid feedback, toggles the submit button, and performs checks
 on current input.
 */
public class SetupSubmitDocumentListener implements DocumentListener {
  private JButton submitButton;
  private JTextField firstNameTextField;
  private JTextField lastNameTextField;
  private JPasswordField passphraseField;
  private JPasswordField confirmPassphraseField;
  private JPasswordField passwordField;
  private JPasswordField confirmPasswordField;
  private InputValidations inputValidations;

  /**
   * Creates a new instance of the SetupSubmitDocumentListener.
   *
   * @param submitButton the button responsible for submitting the new admin
   as (a JButton)
   * @param firstNameTextField stores first name as (a JTextField)
   * @param lastNameTextField stores the last name as (a JTextField)
   * @param passphraseField stores the admin passphrase as (a JPasswordField)
   * @param confirmPassphraseField stores the admin passphrase confirmation as
   (a JPasswordField)
   * @param passwordField stores the admin password as (a JPasswordField)
   * @param confirmPasswordField stores the admin password confirmation as
   */
  public SetupSubmitDocumentListener(JButton submitButton, JTextField firstNameTextField,
      JTextField lastNameTextField, JPasswordField passphraseField,
      JPasswordField confirmPassphraseField, JPasswordField passwordField,
      JPasswordField confirmPasswordField) {

    this.inputValidations = new InputValidations();

    this.submitButton = submitButton;

    this.firstNameTextField = firstNameTextField;
    this.lastNameTextField = lastNameTextField;
    this.passphraseField = passphraseField;
    this.confirmPassphraseField = confirmPassphraseField;
    this.passwordField = passwordField;
    this.confirmPasswordField = confirmPasswordField;
  }

  /**
  * This method checks whether all password fields and first name/last name are
  not null and whether they have non-zero length.
  *
  * @return true if none of the passwords are equal to null and have non-zero
  length; false otherwise.
  */
  public boolean isNullCheckPass() {
    // Check to see that none of the passwords are equal to null. There
    // Is no point to proceed if so.
    if (this.firstNameTextField != null && this.lastNameTextField != null
          && this.passphraseField != null && this.confirmPassphraseField != null
          && this.passwordField != null && this.confirmPasswordField != null
          && this.firstNameTextField.getText().length() > 0
          && this.lastNameTextField.getText().length() > 0
          && this.passphraseField.getPassword().length > 0
          && this.confirmPassphraseField.getPassword().length > 0
          && this.passwordField.getPassword().length > 0
          && this.confirmPasswordField.getPassword().length > 0) {
      return true;
    }

    return false;
  }

  /**
  * Determines whether the password fields match or not.
  *
  * @return boolean value {@code true} if the password fields match, {@code false} otherwise.
  */
  public boolean isPasswordsMatch() {
    // If the password fields match, return true. Passwords do match
    if (Arrays.equals(passwordField.getPassword(), confirmPasswordField.getPassword())) {   
      return true;
    }

    // If the password fields don't match, return false
    return false;
  }

  /**
  * Checks if all entered passwords are valid, including the passphrase and confirmation fields.
  *
  * @return {@code true} if all passwords are valid, {@code false} otherwise.
  */
  public boolean isPasswordsValid() {
    if (this.inputValidations.isOnlyLetterCharacters(
             this.firstNameTextField.getText())
          && this.inputValidations.isOnlyLetterCharacters(
             this.lastNameTextField.getText())
          && this.inputValidations.validatePassword(
             this.passphraseField.getPassword())
          && this.inputValidations.validatePassword(
             this.confirmPassphraseField.getPassword())
          && this.inputValidations.validatePassword(
             this.passwordField.getPassword())
          && this.inputValidations.validatePassword(
             this.confirmPasswordField.getPassword())) {

      return true;
    }

    return false;
  }

  public void printRealTime() {
    System.out.println(this.firstNameTextField.getText());
    System.out.println(this.lastNameTextField.getText());
  }

  public boolean isValidFirstName() {
    return this.inputValidations.isOnlyLetterCharacters(this.firstNameTextField.getText());
  }

  public boolean isValidLastName() {
    return this.inputValidations.isOnlyLetterCharacters(this.lastNameTextField.getText()); 
  }

  /**
  * Checks if the entered passphrase matches the confirmed passphrase.
  *
  * @return {@code true} if the entered passphrase matches the confirmed passphrase,
  {@code false} otherwise.
  */
  public boolean isPassphrasePasswordMatch() {
    if (Arrays.equals(passphraseField.getPassword(), confirmPassphraseField.getPassword())) {
      return true;
    }

    return false;
  }

  // Toggle the opacity
  private void opacityToggleOn() {
    this.submitButton.setOpaque(true);
    this.submitButton.setEnabled(true);
  }

  private void opacityToggleOff() {
    this.submitButton.setOpaque(false);
    this.submitButton.setEnabled(false);
  }

  private boolean passwordsConflict() {
    if (Arrays.equals(this.passphraseField.getPassword(), this.passwordField.getPassword())) {
      return true;
    }

    return false;
  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    if (this.isNullCheckPass() && this.isPasswordsValid()
          && this.isPassphrasePasswordMatch() && this.isPasswordsMatch()
          && !this.passwordsConflict()) {
      this.opacityToggleOn();
    } else {

      this.opacityToggleOff();
    }
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    if (this.isNullCheckPass() && this.isPasswordsValid()
          && this.isPassphrasePasswordMatch() && this.isPasswordsMatch()
          && !this.passwordsConflict()) {
      this.opacityToggleOn();
    } else {
      this.opacityToggleOff();
    }
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    if (this.isNullCheckPass() && this.isPasswordsValid()
          && this.isPassphrasePasswordMatch() && this.isPasswordsMatch()
          && !this.passwordsConflict()) {
      this.opacityToggleOn();
    } else {
      this.opacityToggleOff();
    }
  }
}
