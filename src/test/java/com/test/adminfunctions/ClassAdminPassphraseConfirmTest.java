package com.test.adminfunctions;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.concurrent.ExecutionException;
import main.com.rqueztech.FileLocations;
import main.com.rqueztech.csv.configuration.ConfigurationCsvManager;
import main.com.rqueztech.swingworkers.admin.AdminValidPassphraseWorker;
import main.com.rqueztech.swingworkers.configuration.SetupConfigurationWorker;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ClassAdminPassphraseConfirmTest {
  private FileLocations fileLocations;
  private String fileLocation;
  private File file;
  
  private char[] characterPassword = "Charlie9@".toCharArray();

  @BeforeMethod
  public void setUp() {
    this.fileLocations = new FileLocations();
    this.fileLocation = this.fileLocations.getConfigLocationTest();
    this.file = new File(this.fileLocation);
  }
  
  @Test
  public void createConfigurationFile() {
    if (!this.file.exists()) {
      SetupConfigurationWorker setupConfigurationWorker =
          new SetupConfigurationWorker(
          this.characterPassword,
          this.fileLocation);
    
      setupConfigurationWorker.execute();
    }
  }
  
  @Test
  public void validatePassword() {
    AdminValidPassphraseWorker adminValidPassphrase =
        new AdminValidPassphraseWorker(this.characterPassword, this.fileLocation);

    adminValidPassphrase.execute();

    try {
      assertEquals(true, adminValidPassphrase.get());
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
