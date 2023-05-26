package main.com.rqueztech.swingworkers.user;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import main.com.rqueztech.FileLocations;
import main.com.rqueztech.csv.admin.UserCsvManager;
import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.ui.PanelCentral;
import main.com.rqueztech.ui.enums.PanelCentralEnums;
import main.com.rqueztech.ui.user.UserCentralPanel;

/**
 * Responsible for authenticating the user login.
 *
 * @extends SwingWorker SwingWorker extends the swing worker to execute
 processes on the swing worker and prevent locking up the EDT
 */
public class UserLoginWorker extends SwingWorker<Boolean, Void> {

  private final String userName;
  private final char[] userPassword;
  private boolean authenticated;
  private PanelCentral panelCentral;

  private FileLocations fileLocations;

  /**
   * Create an instance of the user login worker class.
   *
   * @param userName the user name as (a string)
   * @param userPassword the useristrator password as (a character array)
   */
  public UserLoginWorker(String userName, char[] userPassword,
      PanelCentral panelCentral) {
    this.userName = userName;
    this.userPassword = userPassword;
    this.authenticated = false;
    this.panelCentral = panelCentral;
  }

  @Override
  protected Boolean doInBackground() throws Exception {
    // Check userName and userPassword here
    // Set authenticated variable accordingly
    // You can also update any progress or status here
    try {
      this.fileLocations = new FileLocations();
      UserCsvManager userCsvManager = new UserCsvManager(FileLocations.getUserDbLocationMain());
      String[] accountNameData = userCsvManager.retrieveAccountData(this.userName);

      if (accountNameData == null) {
        return false;
      }

      byte[] storedPasswordHash = Base64.getDecoder().decode(accountNameData[4]);
      byte[] currentSalt = Base64.getDecoder().decode(accountNameData[5]);

      this.authenticated = PasswordEncryption.validateEnteredPassword(
        this.userPassword, currentSalt, storedPasswordHash
      );
    } catch (IOException e) {
      e.printStackTrace();
    }

    return authenticated;
  }

  @Override
  protected void done() {
    try {
      boolean authenticated = get(); // Retrieve the result from doInBackground()

      this.panelCentral.getPanelsHashMap()
        .get(PanelCentralEnums.MAINLOGINPANEL)
          .setVisible(false);

      /* If the password entered is less than 8 characters and the result is
      true, this indicates that the default password is detected.
      */
      boolean isDefaultPassword = this.userPassword.length < 8 && authenticated;

      if (isDefaultPassword && authenticated) {
        UserCentralPanel userCentralPanel = (UserCentralPanel) this.panelCentral.getPanelsHashMap()
            .get(PanelCentralEnums.USERCENTRALPANEL);

        userCentralPanel.getLoggedInUser().setLoggedInUser(userName.toCharArray());

        System.out.println(
            userCentralPanel.getLoggedInUser().getCurrentLoggedInUser()
        );

        this.panelCentral.getPanelsHashMap()
            .get(PanelCentralEnums.USERCHANGEDEFAULTPASSWORDPANEL)
              .setVisible(true);
      } else if (authenticated) {
        // Authentication was successful, update UI accordingly
        this.panelCentral.getPanelsHashMap()
          .get(PanelCentralEnums.USERCENTRALPANEL)
            .setVisible(true);
      } else {
        // Authentication failed, show warning message
        this.panelCentral.getPanelsHashMap()
        .get(PanelCentralEnums.LOGININCORRECTERRORPANEL)
            .setVisible(true);
      }
    } catch (InterruptedException | ExecutionException e) {
      // Handle any exceptions that occurred during the background processing
      e.printStackTrace();
    }
  }

}
