package main.com.rqueztech.controllers;

import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.com.rqueztech.swingworkers.admin.AdminLoginWorker;
import main.com.rqueztech.swingworkers.user.UserLoginWorker;
import main.com.rqueztech.ui.MainLoginPanel;
import main.com.rqueztech.ui.PanelCentral;
import main.com.rqueztech.ui.enums.MainLoginPanelEnums;
import main.com.rqueztech.ui.events.TogglePasswordVisibility;


/**
 * The MainLoginControl class is responsible for controlling action listeners,
 document listeners, and event handling for the main login panel.
 */
public class MainLoginController {
  private MainLoginPanel mainLoginPanel;
  private PanelCentral panelCentral;
  private TogglePasswordVisibility togglePasswordVisibility;

  /**
   * MainLoginControl constructor takes a main login panel and panel central
   * as arguments to control all form events.
   *
   * @param mainLoginPanel takes the main login panel as an argument as (a MainLoginPanel object)
   * @param panelCentral takes the panel central as an argument as (a PanelCentral object)
   */
  public MainLoginController(MainLoginPanel mainLoginPanel,
      PanelCentral panelCentral) {

    this.mainLoginPanel = mainLoginPanel;
    this.panelCentral = panelCentral;
    this.togglePasswordVisibility = new TogglePasswordVisibility();

    this.setControllerActionListeners();
  }

  // --------------------------------------------------------------------------
  private void setControllerActionListeners() {
    this.userButtonActionListener();
    this.adminButtonActionListener();
    this.togglePasswordVisibility();
  }

  // --------------------------------------------------------------------------
  private void userButtonActionListener() {
    JButton userButton = (JButton) this.mainLoginPanel.getComponentsMap()
        .get(MainLoginPanelEnums.USERLOGINBUTTONKEY);

    JTextField userName = (JTextField) this.mainLoginPanel.getComponentsMap()
        .get(MainLoginPanelEnums.USERNAMETEXTFIELDKEY);

    JPasswordField userPassword = (JPasswordField) this.mainLoginPanel
        .getComponentsMap().get(MainLoginPanelEnums.PASSWORDTEXTFIELDKEY);

    userButton.addActionListener(e -> {
      UserLoginWorker userLoginWorker =
          new UserLoginWorker(userName.getText(), userPassword
          .getPassword(), this.panelCentral);

      userLoginWorker.execute();
      this.clearFields();
    });
  }

  /**
   * The method takes the user field and password field components from the
   component hash map, clears inputs, and zeroizes the character array storing
   the password.
   */
  public void clearFields() {
    JTextField userField = (JTextField) this.mainLoginPanel.getComponentsMap()
        .get(MainLoginPanelEnums.USERNAMETEXTFIELDKEY);
    userField.setText("");

    JPasswordField passwordField = (JPasswordField) this.mainLoginPanel.getComponentsMap()
        .get(MainLoginPanelEnums.PASSWORDTEXTFIELDKEY);

    passwordField.setText("");
    Arrays.fill(passwordField.getPassword(), '\0');
  }

  // --------------------------------------------------------------------------
  private void adminButtonActionListener() {

    JButton adminButton = (JButton) this.mainLoginPanel.getComponentsMap()
        .get(MainLoginPanelEnums.ADMINLOGINBUTTONKEY);

    JTextField adminName = (JTextField) this.mainLoginPanel.getComponentsMap()
        .get(MainLoginPanelEnums.USERNAMETEXTFIELDKEY);

    JPasswordField adminPassword = (JPasswordField) this.mainLoginPanel
        .getComponentsMap().get(MainLoginPanelEnums.PASSWORDTEXTFIELDKEY);

    adminButton.addActionListener(e -> {
      AdminLoginWorker adminLoginWorker =
          new AdminLoginWorker(adminName.getText(), adminPassword
          .getPassword(), this.panelCentral);

      adminLoginWorker.execute();
      this.clearFields();
    });
  }

  // --------------------------------------------------------------------------
  private void togglePasswordVisibility() {
    JButton toggleButton = (JButton) this.mainLoginPanel.getComponentsMap()
        .get(MainLoginPanelEnums.VISIBILITYBUTTONKEY);

    toggleButton.addActionListener(e -> {
      JPasswordField passwordTextField =
          (JPasswordField) this.mainLoginPanel
            .getComponentsMap()
            .get(MainLoginPanelEnums.PASSWORDTEXTFIELDKEY);

      this.togglePasswordVisibility.passwordToggler(passwordTextField);
    });
  }
}
