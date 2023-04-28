package main.com.rqueztech.controllers;

import java.awt.Component;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.com.rqueztech.ui.MainLoginPanel;
import main.com.rqueztech.ui.PanelCentral;
import main.com.rqueztech.ui.enums.MainLoginPanelEnums;
import main.com.rqueztech.ui.enums.PanelCentralEnums;
import main.com.rqueztech.ui.events.TogglePasswordVisibility;


/**
 * The MainLoginControl class is responsible for controlling action listeners,
 document listeners, and event handling for the main login panel.
 */
public class MainLoginControl {
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
  public MainLoginControl(MainLoginPanel mainLoginPanel,
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
  private void resetFields() {
    for (Component component : this.mainLoginPanel
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
  private void userButtonActionListener() {
    JButton userButton = (JButton) this.mainLoginPanel.getComponentsMap()
              .get(MainLoginPanelEnums.USERLOGINBUTTONKEY);

    userButton.addActionListener(e -> {
      this.cleanUpNavigateAway();
      this.panelCentral.getCurrentPanel().get(PanelCentralEnums
              .USERCHANGEDEFAULTPASSWORDPANEL).setVisible(true);
    });
  }

  // --------------------------------------------------------------------------
  private void adminButtonActionListener() {
    JButton adminButton = (JButton) this.mainLoginPanel.getComponentsMap()
              .get(MainLoginPanelEnums.ADMINLOGINBUTTONKEY);

    adminButton.addActionListener(e -> {
      this.cleanUpNavigateAway();
      this.panelCentral.getCurrentPanel().get(PanelCentralEnums
              .ADMINCENTRALPANEL).setVisible(true);
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

  // --------------------------------------------------------------------------
  private void cleanUpNavigateAway() {
    this.mainLoginPanel.setVisible(false);
    this.resetFields();
  }
}
