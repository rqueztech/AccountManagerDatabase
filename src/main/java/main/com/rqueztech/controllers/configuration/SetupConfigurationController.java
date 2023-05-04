package main.com.rqueztech.controllers.configuration;

import java.awt.Component;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.com.rqueztech.swingworkers.admin.AdminAddAdminWorker;
import main.com.rqueztech.swingworkers.configuration.SetupConfigurationWorker;
import main.com.rqueztech.ui.configuration.SetupConfigurationPanel;
import main.com.rqueztech.ui.configuration.enums.SetupConfigurationPanelEnums;
import main.com.rqueztech.ui.enums.PanelCentralEnums;
import main.com.rqueztech.ui.events.PasswordValidationDocumentListener;
import main.com.rqueztech.ui.events.SetupSubmitDocumentListener;
import main.com.rqueztech.ui.events.TextFieldListener;
import main.com.rqueztech.ui.events.TogglePasswordVisibility;

/**
 * The SetupConfigurationController is responsible for controlling the events
　and　listeners for the adminAddUser panel. It implements ActionListeners and
 DocumentListeners to handle the events.
 */
public class SetupConfigurationController {
  private SetupConfigurationPanel setupConfigurationPanel;
  private TogglePasswordVisibility togglePasswordVisibility;
  private ConcurrentHashMap<SetupConfigurationPanelEnums, JComponent>
      components;

  /**
   * Stores all of the events and listeners responsible for the panel's
   functionality.
   *
   * @param setupConfigurationPanel stores all of the components that create
   that set the setup configuration panel.
   */
  public SetupConfigurationController(SetupConfigurationPanel
      setupConfigurationPanel) {

    this.setupConfigurationPanel = setupConfigurationPanel;
    this.togglePasswordVisibility = new TogglePasswordVisibility();

    this.components = setupConfigurationPanel.getComponentsMap();

    this.enableDocumentListeners();
    this.enableTogglers();

    this.submitActionListener();
    this.exitActionListener();
  }

  // --------------------------------------------------------------------------
  private void exitActionListener() {
    JButton adminLogin = (JButton) this.components
              .get(SetupConfigurationPanelEnums.EXITBUTTONKEY);

    adminLogin.addActionListener(e -> {
      this.setupConfigurationPanel.setVisible(false);
      this.resetFields();
      this.setupConfigurationPanel.getPanelCentral()
              .getCurrentPanel().get(PanelCentralEnums.SETUPAGREEMENTPANEL)
              .setVisible(true);
    });
  }

  // --------------------------------------------------------------------------
  private void enableTogglers() {
    this.togglePassphraseVisibility();
    this.togglePasswordVisibility();
    this.toggleConfirmPasswordVisibility();
    this.toggleConfirmPassphraseVisibility();
  }

  // --------------------------------------------------------------------------
  private void resetFields() {

    for (Component component : this.components.values()) {
      if (component instanceof JPasswordField) {
        ((JPasswordField) component).setText("");
        Arrays.fill(((JPasswordField) component).getPassword(), '\0');
      } else if (component instanceof JTextField) {
        ((JTextField) component).setText("");
      }
    }
  }

  // --------------------------------------------------------------------------
  private void toggleConfirmPassphraseVisibility() {
    JButton toggleButton = (JButton) this.components
              .get(SetupConfigurationPanelEnums
                .CONFIRMPASSPHRASEVISIBILITYBUTTONKEY);

    toggleButton.addActionListener(e -> {
      JPasswordField confirmPassphraseTextField = (JPasswordField)
           this.components.get(SetupConfigurationPanelEnums
                  .CONFIRMPASSPHRASETEXTFIELDKEY);

      this.togglePasswordVisibility.passwordToggler(confirmPassphraseTextField);
    });
  }

  // --------------------------------------------------------------------------
  private void togglePasswordVisibility() {
    JButton toggleButton = (JButton) this.components
              .get(SetupConfigurationPanelEnums
                .PASSWORDVISIBILITYBUTTONKEY);

    toggleButton.addActionListener(e -> {
      JPasswordField passphraseTextField = (JPasswordField)
          this.components.get(SetupConfigurationPanelEnums
                  .PASSWORDTEXTFIELDKEY);
      this.togglePasswordVisibility.passwordToggler(passphraseTextField);
    });
  }

  // --------------------------------------------------------------------------
  private void toggleConfirmPasswordVisibility() {
    JButton toggleButton = (JButton) this.components
              .get(SetupConfigurationPanelEnums
                .CONFIRMPASSWORDVISIBILITYBUTTONKEY);

    toggleButton.addActionListener(e -> {
      JPasswordField passphraseTextField = (JPasswordField)
           this.components.get(SetupConfigurationPanelEnums
                  .CONFIRMPASSWORDTEXTFIELDKEY);

      this.togglePasswordVisibility.passwordToggler(passphraseTextField);
    });
  }

  // --------------------------------------------------------------------------
  private void togglePassphraseVisibility() {
    JButton toggleButton = (JButton) this.components
              .get(SetupConfigurationPanelEnums
                .PASSPHRASEVISIBILITYBUTTONKEY);

    toggleButton.addActionListener(e -> {
      JPasswordField passphraseTextField = (JPasswordField)
          this.components.get(SetupConfigurationPanelEnums
                  .PASSPHRASETEXTFIELDKEY);

      this.togglePasswordVisibility.passwordToggler(passphraseTextField);
    });
  }

  private void enableDocumentListeners() {
    this.setValidPassphraseFeedback();
    this.setValidConfirmPassphraseFeedback();

    this.setValidPasswordFeedback();
    this.setValidConfirmPasswordFeedback();
    this.setSubmitButtonFeedback();
  }

  // --------------------------------------------------------------------------
  private void submitActionListener() {
    JButton adminLogin = (JButton) this.components
              .get(SetupConfigurationPanelEnums.SUBMITBUTTONKEY);

    adminLogin.addActionListener(e -> {
      // Set the first and last name, admin passphrase, and password
      String adminFirstName = ((JTextField) this.components
              .get(SetupConfigurationPanelEnums.FIRSTNAMETEXTFIELDKEY))
              .getText();

      String adminLastName = ((JTextField) this.components
              .get(SetupConfigurationPanelEnums.LASTNAMETEXTFIELDKEY))
              .getText();

      char[] adminPassword = ((JPasswordField) this.components
              .get(SetupConfigurationPanelEnums.PASSWORDTEXTFIELDKEY))
              .getPassword();

      char[] adminPassphrase = ((JPasswordField) this.components
              .get(SetupConfigurationPanelEnums.PASSPHRASETEXTFIELDKEY)
          ).getPassword();

      AdminAddAdminWorker adminAddAdminWorker =
          new AdminAddAdminWorker(adminFirstName, adminLastName,
            adminPassword);

      adminAddAdminWorker.execute();

      SetupConfigurationWorker setupConfigurationWorker =
          new SetupConfigurationWorker(adminPassphrase);

      setupConfigurationWorker.execute();

      this.setupConfigurationPanel.setVisible(false);
      this.resetFields();
      this.setupConfigurationPanel.getPanelCentral().getCurrentPanel()
              .get(PanelCentralEnums.MAINLOGINPANEL).setVisible(true);
    });
  }

  // --------------------------------------------------------------------------
  private void setSubmitButtonFeedback() {
    JButton adminSubmitButton = (JButton) this.components
              .get(SetupConfigurationPanelEnums.SUBMITBUTTONKEY);

    JTextField firstName = (JTextField) this.components
              .get(SetupConfigurationPanelEnums.FIRSTNAMETEXTFIELDKEY);

    JTextField lastName = (JTextField) this.components
              .get(SetupConfigurationPanelEnums.LASTNAMETEXTFIELDKEY);

    JPasswordField passphraseField = (JPasswordField) this.components
              .get(SetupConfigurationPanelEnums.PASSPHRASETEXTFIELDKEY);

    JPasswordField confirmPassphraseField = (JPasswordField) this.components
              .get(SetupConfigurationPanelEnums
                .CONFIRMPASSPHRASETEXTFIELDKEY);

    JPasswordField passwordField = (JPasswordField) this.components
              .get(SetupConfigurationPanelEnums.PASSWORDTEXTFIELDKEY);

    JPasswordField confirmPasswordField = (JPasswordField) this.components
              .get(SetupConfigurationPanelEnums
                .CONFIRMPASSWORDTEXTFIELDKEY);

    // Create a listener for the first name field
    TextFieldListener nameFieldListener =
          new TextFieldListener(firstName);

    // Listener for the last name field
    TextFieldListener lastNameFieldListener =
          new TextFieldListener(lastName);

    firstName.getDocument().addDocumentListener(nameFieldListener);

    lastName.getDocument().addDocumentListener(lastNameFieldListener);

    SetupSubmitDocumentListener submitDocumentListener =
          new SetupSubmitDocumentListener(adminSubmitButton, firstName,
          lastName, passphraseField, confirmPassphraseField,
          passwordField, confirmPasswordField);

    // Listen to the input of the first and last name in real time
    firstName.getDocument().addDocumentListener(submitDocumentListener);
    lastName.getDocument().addDocumentListener(submitDocumentListener);

    passphraseField.getDocument().addDocumentListener(submitDocumentListener);

    confirmPassphraseField.getDocument()
          .addDocumentListener(submitDocumentListener);

    passwordField.getDocument().addDocumentListener(submitDocumentListener);

    confirmPasswordField.getDocument()
          .addDocumentListener(submitDocumentListener);
  }

  // --------------------------------------------------------------------------
  private void setValidPassphraseFeedback() {
    JButton passphraseButton = (JButton) this.components
              .get(SetupConfigurationPanelEnums
                .PASSPHRASEVISIBILITYBUTTONKEY);

    JPasswordField passphraseField = (JPasswordField) this.components
              .get(SetupConfigurationPanelEnums.PASSPHRASETEXTFIELDKEY);

    PasswordValidationDocumentListener passphraseDocumentListener =
          new PasswordValidationDocumentListener(passphraseField,
          passphraseButton);

    passphraseField.getDocument().addDocumentListener(passphraseDocumentListener);
  }

  // --------------------------------------------------------------------------
  private void setValidConfirmPassphraseFeedback() {
    JButton confirmPassphraseButton = (JButton) this.components
              .get(SetupConfigurationPanelEnums
                .CONFIRMPASSPHRASEVISIBILITYBUTTONKEY);

    JPasswordField confirmPassphraseField = (JPasswordField) this.components
              .get(SetupConfigurationPanelEnums
                .CONFIRMPASSPHRASETEXTFIELDKEY);

    PasswordValidationDocumentListener confirmPassphraseDocumentListener =
          new PasswordValidationDocumentListener(confirmPassphraseField,
          confirmPassphraseButton);

    confirmPassphraseField.getDocument()
          .addDocumentListener(confirmPassphraseDocumentListener);
  }

  // --------------------------------------------------------------------------
  private void setValidPasswordFeedback() {
    JButton passwordButton = (JButton) this.components
              .get(SetupConfigurationPanelEnums
                .PASSWORDVISIBILITYBUTTONKEY);

    JPasswordField passwordField = (JPasswordField) this.components
              .get(SetupConfigurationPanelEnums.PASSWORDTEXTFIELDKEY);

    PasswordValidationDocumentListener passwordDocumentListener =
          new PasswordValidationDocumentListener(passwordField,
          passwordButton);
    passwordField.getDocument().addDocumentListener(passwordDocumentListener);

  }

  // --------------------------------------------------------------------------
  private void setValidConfirmPasswordFeedback() {
    JButton confirmPasswordButton = (JButton) this.components
              .get(SetupConfigurationPanelEnums
                .CONFIRMPASSWORDVISIBILITYBUTTONKEY);

    JPasswordField confirmPasswordField = (JPasswordField)
        this.components.get(SetupConfigurationPanelEnums
                .CONFIRMPASSWORDTEXTFIELDKEY);

    PasswordValidationDocumentListener confirmPasswordDocumentListener =
          new PasswordValidationDocumentListener(confirmPasswordField,
          confirmPasswordButton);

    confirmPasswordField.getDocument()
          .addDocumentListener(confirmPasswordDocumentListener);
  }
}
