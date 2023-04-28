package main.com.rqueztech.controllers.user;

import java.awt.Component;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.com.rqueztech.ui.enums.PanelCentralEnums;
import main.com.rqueztech.ui.events.ChangePasswordDocumentListener;
import main.com.rqueztech.ui.events.PasswordValidationDocumentListener;
import main.com.rqueztech.ui.events.TogglePasswordVisibility;
import main.com.rqueztech.ui.user.UserChangeDefaultPasswordPanel;
import main.com.rqueztech.ui.user.enums.UserChangeDefaultPasswordEnums;

public class UserChangeDefaultPasswordController {
  private UserChangeDefaultPasswordPanel userChangeDefaultPasswordPanel;
  private TogglePasswordVisibility togglePasswordVisibility;

  public UserChangeDefaultPasswordController(UserChangeDefaultPasswordPanel
      userChangeDefaultPasswordPanel) {

    this.userChangeDefaultPasswordPanel = userChangeDefaultPasswordPanel;
    this.togglePasswordVisibility = new TogglePasswordVisibility();

    this.submitButtonActionListener();
    this.enablePasswordTogglers();
    this.setListeners();
    this.submitButtonListener();
    this.cancelButtonActionListener();
  }

  // --------------------------------------------------------------------------
  private void resetFields() {

    for (Component component : this.userChangeDefaultPasswordPanel
              .getComponentsMap().values()) {
      if (component instanceof JPasswordField) {
        ((JPasswordField) component).setText("");
        Arrays.fill(((JPasswordField) component).getPassword(), '\0');
      } else if (component instanceof JTextField) {
        ((JTextField) component).setText("");
      }
    }
  }

  // --------------------------------------------------------------------------
  private void submitButtonActionListener() {
    JButton submitButton = (JButton) this.userChangeDefaultPasswordPanel
              .getComponentsMap().get(UserChangeDefaultPasswordEnums
                .SUBMITLOGINBUTTONKEY);

    submitButton.addActionListener(e -> {
      this.userChangeDefaultPasswordPanel.setVisible(false);
      this.resetFields();
      this.userChangeDefaultPasswordPanel.getPanelCentral()
              .getCurrentPanel().get(PanelCentralEnums.USERCENTRALPANEL)
              .setVisible(true);
    });
  }

  // --------------------------------------------------------------------------
  private void cancelButtonActionListener() {
    JButton cancelButton = (JButton) this.userChangeDefaultPasswordPanel
              .getComponentsMap().get(UserChangeDefaultPasswordEnums
                .CANCELCHANGEBUTTONKEY);

    cancelButton.addActionListener(e -> {
      this.userChangeDefaultPasswordPanel.setVisible(false);
      this.resetFields();
      this.userChangeDefaultPasswordPanel.getPanelCentral()
              .getCurrentPanel().get(PanelCentralEnums.MAINLOGINPANEL)
              .setVisible(true);
    });
  }

  // --------------------------------------------------------------------------
  private void enablePasswordTogglers() {
    this.toggleEnterPasswordVisibility();
    this.toggleConfirmPasswordVisibility();
  }

  // --------------------------------------------------------------------------
  private void setListeners() {
    this.passwordListener();
    this.confirmPasswordListener();
    this.submitButtonListener();
  }

  // --------------------------------------------------------------------------
  private void submitButtonListener() {
    JButton submitButton = (JButton) this.userChangeDefaultPasswordPanel
              .getComponentsMap().get(UserChangeDefaultPasswordEnums
                .SUBMITLOGINBUTTONKEY);

    JPasswordField enterPassword = (JPasswordField)
        this.userChangeDefaultPasswordPanel.getComponentsMap()
              .get(UserChangeDefaultPasswordEnums
                .ENTERPASSWORDTEXTFIELDKEY);

    JPasswordField confirmPassword = (JPasswordField)
        this.userChangeDefaultPasswordPanel.getComponentsMap()
              .get(UserChangeDefaultPasswordEnums
                .CONFIRMPASSWORDPASSWORDTEXTFIELDKEY);

    ChangePasswordDocumentListener changePasswordDocumentListener
        = new ChangePasswordDocumentListener(submitButton, enterPassword,
        confirmPassword);

    enterPassword.getDocument().addDocumentListener(
        changePasswordDocumentListener);

    confirmPassword.getDocument().addDocumentListener(
        changePasswordDocumentListener);
  }

  // --------------------------------------------------------------------------
  private void passwordListener() {
    JButton passwordButton = (JButton) this.userChangeDefaultPasswordPanel
              .getComponentsMap().get(UserChangeDefaultPasswordEnums
                .ENTERPASSWORDVISIBILITYBUTTONKEY);

    JPasswordField passwordField = (JPasswordField)
        this.userChangeDefaultPasswordPanel.getComponentsMap()
              .get(UserChangeDefaultPasswordEnums
                .ENTERPASSWORDTEXTFIELDKEY);

    PasswordValidationDocumentListener passwordDocumentListener
            = new PasswordValidationDocumentListener(passwordField, passwordButton);

    passwordField.getDocument().addDocumentListener(passwordDocumentListener);
  }

  // --------------------------------------------------------------------------
  private void confirmPasswordListener() {
    JButton passphraseButton = (JButton)
        this.userChangeDefaultPasswordPanel
              .getComponentsMap().get(UserChangeDefaultPasswordEnums
                .CONFIRMPASSWORDVISIBILITYBUTTONKEY);

    JPasswordField passphraseField = (JPasswordField)
        this.userChangeDefaultPasswordPanel
              .getComponentsMap().get(UserChangeDefaultPasswordEnums
                .CONFIRMPASSWORDPASSWORDTEXTFIELDKEY);

    PasswordValidationDocumentListener passwordDocumentListener =
          new PasswordValidationDocumentListener(passphraseField,
        passphraseButton);
    passphraseField.getDocument().addDocumentListener(passwordDocumentListener);
  }

  // --------------------------------------------------------------------------
  private void toggleEnterPasswordVisibility() {
    JButton toggleButton = (JButton) this.userChangeDefaultPasswordPanel
              .getComponentsMap().get(UserChangeDefaultPasswordEnums
                .ENTERPASSWORDVISIBILITYBUTTONKEY);

    toggleButton.addActionListener(e -> {
      JPasswordField enterPasswordTextField = (JPasswordField)
          this.userChangeDefaultPasswordPanel.getComponentsMap()
              .get(UserChangeDefaultPasswordEnums
                  .ENTERPASSWORDTEXTFIELDKEY);
      this.togglePasswordVisibility.passwordToggler(enterPasswordTextField);
    });
  }

  // --------------------------------------------------------------------------
  void toggleConfirmPasswordVisibility() {
    JButton toggleButton = (JButton) this.userChangeDefaultPasswordPanel
              .getComponentsMap().get(UserChangeDefaultPasswordEnums
                .CONFIRMPASSWORDVISIBILITYBUTTONKEY);

    toggleButton.addActionListener(e -> {
      JPasswordField confirmPasswordTextField = (JPasswordField)
          this.userChangeDefaultPasswordPanel.getComponentsMap()
              .get(UserChangeDefaultPasswordEnums
                  .CONFIRMPASSWORDPASSWORDTEXTFIELDKEY);

      this.togglePasswordVisibility.passwordToggler(
          confirmPasswordTextField);
    });
  }
}
