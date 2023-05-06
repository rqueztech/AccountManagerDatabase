/**
 * The controller class for the user central panel.
 */

package main.com.rqueztech.controllers.user;

import javax.swing.JButton;
import main.com.rqueztech.ui.enums.PanelCentralEnums;
import main.com.rqueztech.ui.user.UserCentralPanel;
import main.com.rqueztech.ui.user.enums.UserCentralPanelEnums;

/**
 * The user central controller class contains all of the listeners and
 respective controllers for the user central controller class.
 */
public class UserCentralController {

  private UserCentralPanel userCentralPanel;

  /**
   * Constructs a new instance of the UserCentralController.
   *
   * @param userCentralPanel the user central panel to control
   */
  public UserCentralController(UserCentralPanel userCentralPanel) {
    this.userCentralPanel = userCentralPanel;
    this.logoutButtonActionListener();
  }

  /**
   * Adds an action listener to the logout button that logs the user out
   * and shows the logout success panel.
   */
  public void logoutButtonActionListener() {
    JButton logoutButton = (JButton) this.userCentralPanel.getComponentsMap()
        .get(UserCentralPanelEnums.USERLOGOUTBUTTONKEY);

    logoutButton.addActionListener(e -> {
      this.userCentralPanel.setVisible(false);
      this.userCentralPanel.getPanelCentral().getPanelsHashMap()
        .get(PanelCentralEnums.LOGOUTSUCCESSPANEL).setVisible(true);
    });
  }
}
