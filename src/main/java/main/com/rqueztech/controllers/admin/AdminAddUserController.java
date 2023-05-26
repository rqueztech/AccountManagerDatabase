package main.com.rqueztech.controllers.admin;

import java.awt.Component;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import main.com.rqueztech.FileLocations;

import main.com.rqueztech.swingworkers.admin.AdminAddUserWorker;
import main.com.rqueztech.swingworkers.admin.AdminValidPassphraseWorker;
import main.com.rqueztech.ui.admin.AdminAddUserPanel;
import main.com.rqueztech.ui.admin.AdminUserViewPanel;
import main.com.rqueztech.ui.admin.enums.AdminAddUserEnums;
import main.com.rqueztech.ui.configuration.enums.SetupConfigurationPanelEnums;
import main.com.rqueztech.ui.enums.PanelCentralEnums;
import main.com.rqueztech.ui.events.AddUserDocumentListener;
import main.com.rqueztech.ui.events.PasswordFieldListener;
import main.com.rqueztech.ui.events.TextFieldListener;
import main.com.rqueztech.ui.events.TogglePasswordVisibility;
import main.com.rqueztech.ui.validation.InputValidations;

/**
 * The AdminAddUserController is responsible for controlling the events and
 listeners for the adminAddUser panel. It implements ActionListeners and
 DocumentListeners to handle the events.
 */
public class AdminAddUserController {

  private AdminAddUserPanel adminAddUserPanel;
  private TogglePasswordVisibility togglePasswordVisibility;
  private ConcurrentHashMap<AdminAddUserEnums, JComponent> components;
  private InputValidations inputValidations;

  private JComboBox<String> gender;

  /**
   * The AdminAddUserController default controller takes all parameters
   required.
   *
   * @param adminAddUserPanel passes the admin add user panel as
   (AdminAddUserPanel)
   * @param gender takes input on user gender as (a JComponent)
   */
  public AdminAddUserController(AdminAddUserPanel adminAddUserPanel,
      JComboBox<String> gender) {
    this.togglePasswordVisibility = new TogglePasswordVisibility();

    this.inputValidations = new InputValidations();

    this.adminAddUserPanel = adminAddUserPanel;
    this.components = adminAddUserPanel.getComponentsMap();
    this.gender = gender;

    this.invokeActionListeners();
    this.invokeDocumentListeners();
    this.enablePasswordTogglers();
  }

  // --------------------------------------------------------------------------
  private void invokeActionListeners() {
    this.userAddUserButtonListener();
    this.cancelButtonActionListener();
  }

  // --------------------------------------------------------------------------
  private void invokeDocumentListeners() {
    this.firstNameListener();
    this.lastNameListener();
    this.passphraseNameListener();
    this.addUserButtonListener();
  }

  // --------------------------------------------------------------------------
  private void userAddUserButtonListener() {
    JButton addUserButton = (JButton) this.adminAddUserPanel.getComponentsMap()
        .get(AdminAddUserEnums.ADDUSERBUTTONKEY);

    addUserButton.addActionListener(e -> {
      // NOTE: Creation of the default user password is automatic.
      // No User Password field Gets passed here.

      String gender = (String) this.gender.getSelectedItem();

      String userFirstName = ((JTextField) this.components
          .get(AdminAddUserEnums.FIRSTNAMETEXTFIELDKEY))
          .getText();

      String userLastName =  ((JTextField) this.components
          .get(AdminAddUserEnums.LASTNAMETEXTFIELDKEY))
          .getText();

      char[] adminPassphrase =  ((JPasswordField) this.components
        .get(AdminAddUserEnums.PASSPHRASETEXTFIELDKEY))
        .getPassword();


      AdminValidPassphraseWorker adminValidPassphraseWorker =
          new AdminValidPassphraseWorker(adminPassphrase, FileLocations
          .getConfigLocationMain());

      adminValidPassphraseWorker.execute();

      try {
        if (adminValidPassphraseWorker.get()) {
          AdminAddUserWorker adminAddUserWorker =
              new AdminAddUserWorker(userFirstName, userLastName, gender,
                FileLocations.getUserDbLocationMain());

          this.resetFields();

          adminAddUserWorker.execute();

          AdminUserViewPanel panel = (AdminUserViewPanel) this.adminAddUserPanel
              .getPanelCentral().getPanelsHashMap().get(PanelCentralEnums
              .ADMINUSERVIEWPANEL);

          // Create a sleeper function to give enough time for the update to take effect
          Thread.sleep(200);
          panel.refreshTable();

          this.adminAddUserPanel.setVisible(false);
          this.adminAddUserPanel.getPanelCentral().getPanelsHashMap()
              .get(PanelCentralEnums.ADMINUSERVIEWPANEL).setVisible(true);
        } else {
          JOptionPane.showMessageDialog(null, "NOT ADDED PROPERLY");
        }
      } catch (InterruptedException | ExecutionException e1) {
        // TODO Auto-generated catch block
        e1.printStackTrace();
      }
    });
  }

  // --------------------------------------------------------------------------
  private void resetFields() {
    for (Component component : this.adminAddUserPanel
        .getComponentsMap().values()) {
      if (component instanceof JPasswordField) {
        ((JPasswordField) component).setText("");
        Arrays.fill(((JPasswordField) component).getPassword(), '\0');
      } else if (component instanceof JTextField) {
        ((JTextField) component).setText("");
      }
    }

    this.adminAddUserPanel.getGender().setSelectedIndex(0);
  }

  // --------------------------------------------------------------------------
  private void cancelButtonActionListener() {
    JButton adminLogin = (JButton) this.adminAddUserPanel
        .getComponentsMap().get(AdminAddUserEnums.CANCELBUTTONKEY);

    adminLogin.addActionListener(e -> {
      this.adminAddUserPanel.setVisible(false);
      this.resetFields();

      // Take the user back to the Admin User View Panel
      this.adminAddUserPanel.getPanelCentral().getPanelsHashMap()
        .get(PanelCentralEnums.ADMINUSERVIEWPANEL).setVisible(true);
    });
  }

  // --------------------------------------------------------------------------
  private void lastNameListener() {
    JTextField userLastName = (JTextField) this.adminAddUserPanel
        .getComponentsMap().get(AdminAddUserEnums.LASTNAMETEXTFIELDKEY);

    // Listener for the last name field
    TextFieldListener lastNameFieldListener =
          new TextFieldListener(userLastName);

    userLastName.getDocument().addDocumentListener(lastNameFieldListener);
  }

  // --------------------------------------------------------------------------
  private void addUserButtonListener() {
    JTextField userFirstName = (JTextField) this.adminAddUserPanel
        .getComponentsMap().get(AdminAddUserEnums.FIRSTNAMETEXTFIELDKEY);

    JTextField userLastName = (JTextField) this.adminAddUserPanel
        .getComponentsMap().get(AdminAddUserEnums.LASTNAMETEXTFIELDKEY);

    JPasswordField passphrase = (JPasswordField) this.adminAddUserPanel
        .getComponentsMap().get(AdminAddUserEnums
        .PASSPHRASETEXTFIELDKEY);

    JButton addUserButton = (JButton) this.adminAddUserPanel
        .getComponentsMap().get(AdminAddUserEnums.ADDUSERBUTTONKEY);

    AddUserDocumentListener addUserDocumentListener =
        new AddUserDocumentListener(addUserButton, userFirstName,
        userLastName, passphrase,
        this.adminAddUserPanel.getGender());

    userFirstName.getDocument()
        .addDocumentListener(addUserDocumentListener);

    userLastName.getDocument()
        .addDocumentListener(addUserDocumentListener);

    passphrase.getDocument()
        .addDocumentListener(addUserDocumentListener);
  }

  // --------------------------------------------------------------------------
  private void firstNameListener() {
    JTextField userFirstName = (JTextField) this.adminAddUserPanel
        .getComponentsMap().get(AdminAddUserEnums.FIRSTNAMETEXTFIELDKEY);

    // Create a listener for the first name field
    TextFieldListener nameFieldListener =
        new TextFieldListener(userFirstName);

    userFirstName.getDocument().addDocumentListener(nameFieldListener);
  }

  // --------------------------------------------------------------------------
  private void passphraseNameListener() {
    JPasswordField passphrase = (JPasswordField) this.adminAddUserPanel
        .getComponentsMap().get(AdminAddUserEnums
        .PASSPHRASETEXTFIELDKEY);
    // Listener for the last name field
    PasswordFieldListener passwordFieldListener =
        new PasswordFieldListener(passphrase);

    passphrase.getDocument().addDocumentListener(passwordFieldListener);
  }

  // --------------------------------------------------------------------------
  private void enablePasswordTogglers() {
    this.toggleEnterPasswordVisibility();
  }

  // --------------------------------------------------------------------------
  private void toggleEnterPasswordVisibility() {
    JButton toggleButton = (JButton) this.adminAddUserPanel
        .getComponentsMap().get(AdminAddUserEnums
        .PASSPHRASEVISIBILITYBUTTONKEY);

    toggleButton.addActionListener(e -> {
      JPasswordField enterPasswordTextField =
          (JPasswordField) this.adminAddUserPanel.getComponentsMap()
          .get(AdminAddUserEnums.PASSPHRASETEXTFIELDKEY);

      this.togglePasswordVisibility.passwordToggler(enterPasswordTextField);

    });
  }
}
