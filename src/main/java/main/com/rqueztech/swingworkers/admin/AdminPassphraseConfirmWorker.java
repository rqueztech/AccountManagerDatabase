package main.com.rqueztech.swingworkers.admin;

import java.util.concurrent.ExecutionException;

import javax.swing.SwingWorker;

import main.com.rqueztech.FileLocations;
import main.com.rqueztech.csv.configuration.ConfigurationCsvManager;
import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.models.configuration.ConfigurationModel;

/**
 * The class is going to confirm the user passphrase entered by the manager is
 entered correctly. This grants access to add and delete functions.
 */
public class AdminPassphraseConfirmWorker extends SwingWorker<ConfigurationModel, Void> {
  private char[] adminPassphrase;
  private String adminConfigurationPassphrase;
  private String adminConfigurationSalt;

  private ConfigurationCsvManager configurationCsvManager;
  private FileLocations fileLocations;

  public AdminPassphraseConfirmWorker(char[] adminPassphrase) {
    this.adminPassphrase = adminPassphrase;
  }

  @Override
  protected ConfigurationModel doInBackground() throws Exception {
    // TODO Auto-generated method stub
    this.fileLocations = new FileLocations();

    this.configurationCsvManager = new ConfigurationCsvManager(FileLocations.getConfigLocationMain());

    return null;
  }

  @Override
  protected void done() {
    try {
      ConfigurationModel result = get();
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
