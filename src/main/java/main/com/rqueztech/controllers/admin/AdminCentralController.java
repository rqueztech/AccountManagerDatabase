package main.com.rqueztech.controllers.admin;

import javax.swing.JButton;
import javax.swing.JPanel;
import main.com.rqueztech.ui.admin.AdminCentralPanel;
import main.com.rqueztech.ui.admin.enums.AdminCentralPanelEnums;
import main.com.rqueztech.ui.enums.PanelCentralEnums;

/**
 * This class represents a controller for the admin central panel in the UI.
 It is responsible for setting action listeners for the buttons in the admin
 central panel.
 */
public class AdminCentralController {
  private AdminCentralPanel adminCentralPanel;

  public AdminCentralController(AdminCentralPanel adminCentralPanel) {
    this.adminCentralPanel = adminCentralPanel;
    this.setActionListeners();
  }

  private void setActionListeners() {
    this.adminAddUserButtonListener();
    this.logoutButtonActionListener();
    this.userViewActionListener();
  }

  // --------------------------------------------------------------------------
  private void userViewActionListener() {
    JButton userViewButton = (JButton) this.adminCentralPanel
        .getComponentsMap().get(AdminCentralPanelEnums.USERVIEWBUTTONKEY);

    userViewButton.addActionListener(e -> {
      this.adminCentralPanel.setVisible(false);
      this.adminCentralPanel.panelCentral.getPanelsHashMap()
          .get(PanelCentralEnums.ADMINUSERVIEWPANEL).setVisible(true);
    });
  }

  // --------------------------------------------------------------------------
  private void adminAddUserButtonListener() {
    JButton adminLogin = (JButton) this.adminCentralPanel
        .getComponentsMap().get(AdminCentralPanelEnums
        .ADMINADDUSERBUTTONKEY);

    adminLogin.addActionListener(e -> {
      this.adminCentralPanel.setVisible(false);

      this.adminCentralPanel.panelCentral.getPanelsHashMap()
          .get(PanelCentralEnums.ADMINADDUSERPANEL).setVisible(true);
    });
  }

  // --------------------------------------------------------------------------
  private void logoutButtonActionListener() {
    JButton adminLogin = (JButton) this.adminCentralPanel
        .getComponentsMap().get(AdminCentralPanelEnums
        .ADMINLOGOUTBUTTONKEY);

    adminLogin.addActionListener(e -> {

      this.adminCentralPanel.getLoggedInAdmin().clearLoggedInAdmin();

      this.adminCentralPanel.setVisible(false);
      this.adminCentralPanel.panelCentral.getPanelsHashMap()
        .get(PanelCentralEnums.LOGOUTSUCCESSPANEL).setVisible(true);
    });
  }
}
