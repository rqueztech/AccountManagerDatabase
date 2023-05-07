package main.com.rqueztech.swingworkers.admin;

import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import main.com.rqueztech.FileLocations;
import main.com.rqueztech.csv.admin.AdminCsvManager;
import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.ui.PanelCentral;
import main.com.rqueztech.ui.admin.AdminCentralPanel;
import main.com.rqueztech.ui.enums.PanelCentralEnums;

/**
 * Responsible for authenticating the administrator login.
 *
 * @extends SwingWorker
 */
public class AdminLoginWorker extends SwingWorker<Boolean, Void> {

  private final String adminName;
  private final char[] adminPassword;
  private boolean authenticated;
  private PanelCentral panelCentral;
  private FileLocations fileLocations;

  /**
   * Create an instance of the admin login worker class.
   *
   * @param adminName the administrator username as (a string)
   * @param adminPassword the administrator password as (a character array)
   */
  public AdminLoginWorker(String adminName, char[] adminPassword,
      PanelCentral panelCentral) {
    this.adminName = adminName;
    this.adminPassword = adminPassword;
    this.authenticated = false;
    this.panelCentral = panelCentral;
    this.fileLocations = new FileLocations();
  }

  @Override
  protected Boolean doInBackground() throws Exception {
    // Check adminName and adminPassword here
    // Set authenticated variable accordingly
    // You can also update any progress or status here
    try {
      AdminCsvManager adminCsvManager = new AdminCsvManager(this.fileLocations.getAdminDbLocationMain());
      String[] accountNameData = adminCsvManager.retrieveAccountData(this.adminName);

      if (accountNameData == null) {
        return false;
      }

      byte[] currentSalt = Base64.getDecoder().decode(accountNameData[4]);
      byte[] storedPasswordHash = Base64.getDecoder().decode(accountNameData[3]);

      this.authenticated = PasswordEncryption.validateEnteredPassword(
        this.adminPassword, currentSalt, storedPasswordHash
      );
    } catch (IOException e) {
      e.printStackTrace();
    }

    return authenticated;
  }

  @Override
  protected void done() {
    try {
      boolean authenticated = get(); // Retrieve the authenticated from doInBackground()

      this.panelCentral.getPanelsHashMap()
        .get(PanelCentralEnums.MAINLOGINPANEL)
          .setVisible(false);

      if (authenticated) {
        // Authentication was successful, update UI accordingly
        AdminCentralPanel newPanel = (AdminCentralPanel) 
            this.panelCentral.getPanelsHashMap()
            .get(PanelCentralEnums.ADMINCENTRALPANEL);

        newPanel.getLoggedInAdmin().setLoggedInAdmin(adminName.toCharArray());

        this.panelCentral.getPanelsHashMap()
          .get(PanelCentralEnums.ADMINCENTRALPANEL)
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
