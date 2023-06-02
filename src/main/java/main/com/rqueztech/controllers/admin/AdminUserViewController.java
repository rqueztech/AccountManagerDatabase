package main.com.rqueztech.controllers.admin;

import java.awt.Component;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.com.rqueztech.FileLocations;
import main.com.rqueztech.swingworkers.admin.AdminDeleteUserWorker;
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
    this.deleteButtonActionListener();
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
      this.adminUserViewPanel.getPanelCentral().getPanelsHashMap()
              .get(PanelCentralEnums.LOGOUTSUCCESSPANEL).setVisible(true);
    });
  }

  //--------------------------------------------------------------------------
  private void deleteButtonActionListener() {
    JButton userDeleteButton = (JButton) this.adminUserViewPanel
        .getComponentsMap()
        .get(AdminUserViewEnums.DELETEUSERBUTTONKEY);

    userDeleteButton.addActionListener(e -> {
      System.out.println(this.adminUserViewPanel.getCurrentUser());

      if (this.adminUserViewPanel.getCurrentUser() != null) {
        AdminDeleteUserWorker adminDeleteUserWorker =
            new AdminDeleteUserWorker(this.adminUserViewPanel, this.adminUserViewPanel.getCurrentUser(),
            FileLocations.getUserDbLocationMain());

        String message = String.format("Are you sure you would like to "
            + "delete User: %s", this.adminUserViewPanel.getCurrentUser());

        if (JOptionPane.showConfirmDialog(null, message) == JOptionPane.OK_OPTION) {
          adminDeleteUserWorker.execute();
        } else {
          JOptionPane.showMessageDialog(null, "No User Found");
        }
      } else {
        JOptionPane.showMessageDialog(null, "Nothing Selected");
      }

      this.resetFields();
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
      this.adminUserViewPanel.getPanelCentral().getPanelsHashMap()
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
      this.adminUserViewPanel.getPanelCentral().getPanelsHashMap()
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
