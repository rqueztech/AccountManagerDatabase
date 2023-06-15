package main.com.rqueztech.controllers.admin;

import java.awt.Component;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import main.com.rqueztech.FileLocations;
import main.com.rqueztech.encryption.PasswordEncryption;
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
  private boolean optionSelected = false;
  
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

      // If no user is selected, break out and print message
      if (this.adminUserViewPanel.getCurrentUser() == null) {
        JOptionPane.showMessageDialog(null, "No User Selected");
        return;
      }
      
      // If user is selected, ask if they want to proceed with deletion
      if (JOptionPane.showConfirmDialog(null,
          "Proceed with deletion of user? ")
          != JOptionPane.OK_OPTION) {
        return;
      }
      
      // Prompt the user to enter the password
      JPasswordField passwordField = new JPasswordField();
      int option = JOptionPane.showConfirmDialog(null, passwordField,
          "Enter your password:", JOptionPane.OK_CANCEL_OPTION,
           JOptionPane.PLAIN_MESSAGE);

      if (option == JOptionPane.OK_OPTION) {
    	  
        char[] passwordChars = passwordField.getPassword();
        // Handle the password as needed
          
        AdminDeleteUserWorker adminDeleteUserWorker =
            new AdminDeleteUserWorker(this.adminUserViewPanel,
              this.adminUserViewPanel.getCurrentUser(),
            FileLocations.getUserDbLocationMain());
        
        adminDeleteUserWorker.execute();
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
