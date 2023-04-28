package main.com.rqueztech.ui.events;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.com.rqueztech.ui.validation.InputValidations;


/**
 * The AddUserDocumentListener class implements all of the document listeners
 for input that is required to add a user into the system.
 */
public class AddUserDocumentListener implements DocumentListener {
  private JButton addUserButton;
  private JTextField firstName;
  private JTextField lastName;
  private JPasswordField passphraseField;
  private JComboBox<String> comboBox;
  private InputValidations inputValidations;

  /**
   * The AddUserDocumentListener class implements all of the document listeners
   for input that is required to add a user into the system.
   */
  public AddUserDocumentListener(JButton addUserButton, JTextField firstName,
      JTextField lastName, JPasswordField passphraseField,
      JComboBox<String> comboBox) {

    this.inputValidations = new InputValidations();

    this.addUserButton = addUserButton;
    this.comboBox = comboBox;

    this.firstName = firstName;
    this.lastName = lastName;
    this.passphraseField = passphraseField;

    this.comboBox.addActionListener(e -> {
      if (this.isNullCheckPass()
              && this.isCompleteUserEntry()
              && this.comboBox.getSelectedIndex() != 0) {
        this.opacityToggleOn();
      } else {
        this.opacityToggleOff();
      }
    });
  }

  /**
   * The isNullCheckPass class verifies that none of the fields are null.
   This will prevent the document listeners from doing any unnecessary
   Checks.
   */
  public boolean isNullCheckPass() {
    // Check to see that none of the passwords are equal to null. There
    // Is no point to proceed if so.
    if (this.firstName != null && this.lastName != null
          && this.passphraseField != null
          && this.comboBox.getSelectedIndex() != 0
          && this.firstName.getText().length() > 0
          && this.lastName.getText().length() > 0
          && this.passphraseField.getPassword().length > 0) {
      return true;
    }

    return false;
  }

  /**
   * The isCompleteUserEntry function ensures that all input is complete and meets
   requirements.
   *
   * @return isMeetsLegalEntryRequirements returns either true or false based on
   whether the admin has entered complete and valid entries to add a user.
   */

  public boolean isCompleteUserEntry() {
    // Check if the first name, last name, and password fields are not empty and
    // only contain valid characters
    boolean isFirstNameValid = this.inputValidations.isOnlyLetterCharacters(
        this.firstName.getText().toCharArray());

    boolean isLastNameValid = this.inputValidations.isOnlyLetterCharacters(
        this.lastName.getText().toCharArray());

    boolean isPasswordValid = this.inputValidations.validatePassword(
        this.passphraseField.getPassword());

    // Check if the selected index of the combo box is not 0
    boolean isComboBoxSelected = (this.comboBox.getSelectedIndex() != 0);

    // Return true if all fields are valid, false otherwise
    return isFirstNameValid && isLastNameValid && isPasswordValid && isComboBoxSelected;
  }


  // Toggle the opacity
  // --------------------------------------------------------------------------
  private void opacityToggleOn() {
    this.addUserButton.setOpaque(true);
    this.addUserButton.setEnabled(true);
  }

  // --------------------------------------------------------------------------
  private void opacityToggleOff() {
    this.addUserButton.setOpaque(false);
    this.addUserButton.setEnabled(false);
  }

  // --------------------------------------------------------------------------
  @Override
  public void insertUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    if (this.isNullCheckPass() && this.isCompleteUserEntry()) {
      this.opacityToggleOn();
    } else {
      this.opacityToggleOff();
    }

  }

  // --------------------------------------------------------------------------
  @Override
  public void removeUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    if (this.isNullCheckPass() && this.isCompleteUserEntry()) {
      this.opacityToggleOn();
    } else {
      this.opacityToggleOff();
    }
  }

  // --------------------------------------------------------------------------
  @Override
  public void changedUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    if (this.isNullCheckPass() && this.isCompleteUserEntry()) {
      this.opacityToggleOn();
    } else {
      this.opacityToggleOff();
    }
  }
}
