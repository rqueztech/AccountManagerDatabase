package main.com.rqueztech.swingworkers.admin;

import java.io.File;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.swing.SwingWorker;
import main.com.rqueztech.csv.configuration.ConfigurationCsvManager;
import main.com.rqueztech.encryption.PasswordEncryption;

/**
 * Validate the administrator passphrase. The class takes the passphrase as
 input and validates that it is the default administrator passphrase.
 */
public class AdminValidPassphraseWorker extends SwingWorker<Boolean, Void> {

  private char[] adminPassphrase;

  private ConfigurationCsvManager configurationCsvManager;
  private String fileLocations;
  private File file;

  public AdminValidPassphraseWorker(char[] adminPassphrase, String fileLocations) {
    this.file = new File(fileLocations);
    this.fileLocations = fileLocations;
    this.adminPassphrase = adminPassphrase;
  }

  @Override
  protected Boolean doInBackground() throws Exception {
    // Perform background tasks here
    // This method runs on a separate worker thread

    boolean isValid = false;

    if (!this.file.exists()) {
      return false;
    }

    this.configurationCsvManager = new ConfigurationCsvManager(
      this.fileLocations);

    List<String[]> configurationData = this.configurationCsvManager.retrieveData();

    byte[] configurationPassword = Base64.getDecoder()
      .decode(configurationData.get(1)[2]);

    byte[] configurationSalt = Base64.getDecoder()
      .decode(configurationData.get(1)[3]);

    isValid = PasswordEncryption.validateEnteredPassword(
      this.adminPassphrase, configurationSalt, configurationPassword
    );

    return isValid;
  }

  @Override
  protected void done() {
    // This method is called when the background task is completed

    try {

      // Perform any UI updates or post-processing based on the result

      // Example: Updating UI components
      if (get()) {
        // Do something when the passphrase is valid
        System.out.println("Is Valid Passphrase");
        System.out.println(get());
      } else {
        // Do something when the passphrase is invalid
        System.out.println("Is Not Valid Passphrase");
        System.out.println(get());
      }
    } catch (Exception ex) {
      // Handle any exceptions that occurred during doInBackground()
    }
  }
}
