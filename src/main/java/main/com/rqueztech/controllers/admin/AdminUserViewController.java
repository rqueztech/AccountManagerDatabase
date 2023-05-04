package main.com.rqueztech.controllers.admin;

import java.awt.Component;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.com.rqueztech.ui.admin.AdminUserViewPanel;
import main.com.rqueztech.ui.admin.enums.AdminUserViewEnums;
import main.com.rqueztech.ui.enums.PanelCentralEnums;

/**
 * The class contains all of the action listeners and document listeners that
 control the components in the admin user view controller panel.
 */
public class AdminUserViewController {
  private AdminUserViewPanel adminUserViewPanel;

  public AdminUserViewController(AdminUserViewPanel adminUserViewPanel) {
    this.adminUserViewPanel = adminUserViewPanel;
    this.invokeActionListeners();
  }

  // --------------------------------------------------------------------------
  private void invokeActionListeners() {
    this.userViewButtonListener();
    this.exitButtonActionListener();
    this.logoutButtonActionListener();
  }

  // --------------------------------------------------------------------------
  private void logoutButtonActionListener() {
    JButton userViewButton = (JButton) this.adminUserViewPanel
        .getComponentsMap()
        .get(AdminUserViewEnums.ADMINLOGOUTBUTTONKEY);

    userViewButton.addActionListener(e -> {
      this.adminUserViewPanel.setVisible(false);
      this.resetFields();
      this.adminUserViewPanel.getPanelCentral().getCurrentPanel()
              .get(PanelCentralEnums.LOGOUTSUCCESSPANEL).setVisible(true);
    });
  }

  // --------------------------------------------------------------------------
  private void userViewButtonListener() {
    JButton userViewButton = (JButton) this.adminUserViewPanel
        .getComponentsMap()
        .get(AdminUserViewEnums.ADDUSERBUTTONKEY);

    userViewButton.addActionListener(e -> {
      this.adminUserViewPanel.setVisible(false);
      this.resetFields();
      this.adminUserViewPanel.getPanelCentral().getCurrentPanel()
        .get(PanelCentralEnums.ADMINADDUSERPANEL)
        .setVisible(true);
    });
  }

  // --------------------------------------------------------------------------
  private void exitButtonActionListener() {
    JButton adminLogin = (JButton) this.adminUserViewPanel
        .getComponentsMap()
        .get(AdminUserViewEnums.RETURNCENTRALBUTTONKEY);

    adminLogin.addActionListener(e -> {
      this.adminUserViewPanel.setVisible(false);
      this.resetFields();
      this.adminUserViewPanel.getPanelCentral().getCurrentPanel()
        .get(PanelCentralEnums.ADMINCENTRALPANEL)
        .setVisible(true);
    });
  }

  // --------------------------------------------------------------------------
  private void resetFields() {
    for (Component component : this.adminUserViewPanel
              .getComponentsMap().values()) {

      if (component instanceof JPasswordField) {
        ((JPasswordField) component).setText("");
        Arrays.fill(((JPasswordField) component).getPassword(), '\0');
      } else if (component instanceof JTextField) {
        ((JTextField) component).setText("");
      }
    }
  }
}
