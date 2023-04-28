package main.com.rqueztech.ui.events;

import java.awt.Color;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import main.com.rqueztech.ui.validation.InputValidations;

/**
 * The PasswordValidationDocuemntListner adds a document listener which allows
 the active processing of password input to verify it meets all requirements.
 *
 * @implements DocumentListener
 */
public class PasswordValidationDocumentListener implements DocumentListener {

  private JPasswordField passwordField;
  private JButton currentButton;
  private InputValidations inputValidations;

  // Represents 1 in binary
  private final int hasUpperCase = 1;

  // Push one to the left once, 10 in binary
  private final int hasLowerCase = 1 << 1;

  // Push one to the left once, 100 in binary
  private final int hasSymbol = 1 << 2;

  // Push one to the left once, 1000 in binary
  private final int hasNumber = 1 << 3;

  // Push one to the left once, 10000 in binary
  private final int hasIllegal = 1 << 4;

  // Push one to the left once, 10000 in binary
  private final int hasMinCharacters = 1 << 5;

  private StringBuilder sb;
  private ConcurrentHashMap<Integer, String> stringMessage;

  /**
   * Specific constructor which passes all of the components that will be
   handled by the document listener.
   *
   * @param passwordField password field holding the user password input.
   * @param currentButton the current button being processed by the listener.
   */
  public PasswordValidationDocumentListener(
      JPasswordField passwordField,
      JButton currentButton) {

    this.sb = new StringBuilder();

    this.stringMessage = new ConcurrentHashMap<Integer, String>();

    this.inputValidations = new InputValidations();
    this.passwordField = passwordField;
    this.currentButton = currentButton;

    this.stringMessage.put(hasMinCharacters, "8 Characters");
    this.stringMessage.put(hasUpperCase, "1 Uppercase");
    this.stringMessage.put(hasLowerCase, "1 Lowercase");
    this.stringMessage.put(hasSymbol, "1 Symbol");
    this.stringMessage.put(hasNumber, "1 Number");
    this.stringMessage.put(hasIllegal, "No Illegal");

    this.updateToolTip();
  }

  private void updateButton() {
    if (this.inputValidations.validatePassword(this.passwordField.getPassword())) {
      this.currentButton.setBackground(Color.GREEN);
      this.passwordField.setToolTipText("Meets Requirements");
    } else {
      this.currentButton.setBackground(Color.RED);
    }

    this.updateToolTip();
  }

  private void updateToolTip() {
    int flags = 0;

    if (this.passwordField.getPassword().length < 8) {
      flags |= hasMinCharacters;
    }

    if (!this.inputValidations.containsLegalCharacters(this.passwordField.getPassword())) {
      flags |= hasIllegal;
    }

    if (this.inputValidations.isNoUpperCaseCharacters(this.passwordField.getPassword())) {
      flags |= hasUpperCase;
    }

    if (this.inputValidations.isNoLowerCaseCharacters(this.passwordField.getPassword())) {
      flags |= hasLowerCase;
    }

    if (this.inputValidations.isNoSpecialCharacters(this.passwordField.getPassword())) {
      flags |= hasSymbol;
    }

    if (this.inputValidations.isNoNumbersFound(this.passwordField.getPassword())) {
      flags |= hasNumber;
    }

    sb = new StringBuilder();
    for (Integer flag : this.stringMessage.keySet()) {
      if ((flags & flag) == flag) {
        sb.append(this.stringMessage.get(flag)).append(" ");
      }
    }

    if (sb.toString().equals("")) {
      sb.setLength(0);
      sb.append("Meets Requirements");
    }

    passwordField.setToolTipText(sb.toString());
  }

  private void resetButton() {
    if (this.passwordField.getPassword().length == 0) {
      this.currentButton.setBackground(Color.BLACK);
      this.sb.setLength(0);
      this.sb.append("Field Must Not Be Blank");
    }
  }

  private void updateStatus() {
    if (this.passwordField.getPassword().length >= 8
          && this.inputValidations.containsLegalCharacters(
        this.passwordField.getPassword())) {
      this.currentButton.setBackground(Color.GREEN);
      this.currentButton.setOpaque(true);
    } else if (this.passwordField.getPassword().length == 0) {
      resetButton();
    } else {
      this.currentButton.setBackground(Color.RED);
    }
  }

  @Override
  public void insertUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    updateButton();
  }

  @Override
  public void removeUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub
    updateStatus();
  }

  @Override
  public void changedUpdate(DocumentEvent e) {
    // TODO Auto-generated method stub

  }
}
