package main.com.rqueztech.swingworkers.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import javax.swing.SwingWorker;
import main.com.rqueztech.FileLocations;
import main.com.rqueztech.csv.configuration.ConfigurationCsvManager;
import main.com.rqueztech.encryption.PasswordEncryption;

/**
 * The SetupConfigurationWorker performs the generation of salt, encryption,
 and write operation to the csv file.
 *
 * @extends SwingWorker
 */
public class SetupConfigurationWorker extends SwingWorker<Boolean, Void> {
  private char[] newAdminPassphrase;

  private byte[] encryptedAdminPassphrase;
  private byte[] newAdminPassphraseSalt;
  private final String fileLocation;

  public SetupConfigurationWorker(char[] newAdminPassphrase,
      final String fileLocation) {

    this.newAdminPassphrase = newAdminPassphrase;
    this.fileLocation = fileLocation;
  }

  // --------------------------------------------------------------------------
  @Override
  protected Boolean doInBackground() throws Exception {
    // TODO Auto-generated method stub
    this.newAdminPassphraseSalt = this.generateNewAdminPassphraseSalt();
    this.encryptedAdminPassphrase = this.generateEncryptedAdminPassphrase();
    this.zeroizeArray();

    this.writeToCsvFile();

    return null;
  }

  /**
   * Writes the configuration data to a CSV file using the ConfigurationCsvManager class.
   The method converts the password to base64 encoding and adds it to a list of configuration data.
   *
   * @throws IOException if there is an error writing to the CSV file.
  */
  public void writeToCsvFile() {
    ConfigurationCsvManager configurationCsvManager = new
        ConfigurationCsvManager(this.fileLocation);

    List<String[]> configurationData = new ArrayList<String[]>();

    // Convert the password to base 64 encoding
    configurationData.add(new String[] {
        String.valueOf(0),
        String.valueOf(0),
        Base64.getEncoder().encodeToString(this.encryptedAdminPassphrase),
        Base64.getEncoder().encodeToString(this.newAdminPassphraseSalt),

    });

    try {
      configurationCsvManager.createConfigurationFile(configurationData);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  //--------------------------------------------------------------------------
  private void zeroizeArray() {
    Arrays.fill(this.newAdminPassphrase, '\0');
  }

  //--------------------------------------------------------------------------
  private byte[] generateEncryptedAdminPassphrase() {
    return PasswordEncryption.hashPassword(
          this.newAdminPassphrase, this.newAdminPassphraseSalt);
  }

  // --------------------------------------------------------------------------
  private byte[] generateNewAdminPassphraseSalt() {
    return PasswordEncryption.generateSalt();
  }

}
