package com.test.adminfunctions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import main.com.rqueztech.FileLocations;
import main.com.rqueztech.csv.admin.UserCsvManager;
import main.com.rqueztech.csv.configuration.ConfigurationCsvManager;
import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.models.user.UserModel;
import main.com.rqueztech.swingworkers.admin.AdminAddAdminWorker;
import main.com.rqueztech.swingworkers.admin.AdminAddUserWorker;
import main.com.rqueztech.swingworkers.admin.AdminAddUserWorkerTesting;
import main.com.rqueztech.swingworkers.configuration.SetupConfigurationWorker;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The test takes a list of names and passwords and inputs and uses said inputs
 to create administrator accounts.
 */
public class CreateUserTest {
  private List<UserModel> currentUser;
  private AdminAddUserWorkerTesting adminAddUserWorkerTesting;
  private SetupConfigurationWorker setupConfigurationWorker;
  
  // Create variables that will avoid magic numbers
  private final int firstNameColumn = 0;
  private final int lastNameColumn = 1;
  private final int passwordColumn = 2;
  
  private String[][] userData = {
    {"Alina", "Walls", "Male"},
    {"Rufus", "Kennedy", "Male"},
    {"Rueben", "Lindsay", "Male"},
    {"Leuan", "Page", "Male"},
    {"Michelle", "Nolan", "Male"},
    {"Habiba", "Barlow", "Male"},
    {"Agnes", "Quinn", "Male"},
    {"Maizie", "Goodman", "Male"},
    {"Hannah", "Foley", "Male"},
    {"Maisey", "Gamble", "Male"},
    {"Hashim", "Campbell", "Male"},
    {"Nina", "Richardson", "Male"},
    {"Nicola", "Walton", "Male"},
    {"Carol", "Bates", "Male"},
    {"Marina", "Glenn", "Male"},
    {"Kathleen", "Morrow", "Male"},
    {"Alia", "Dotson", "Male"},
    {"Elouise", "Cook", "Male"},
    {"Rehan", "Perkins", "Male"},
    {"Velma", "Hardin", "Male"},
    {"Brooklyn", "Rodgers", "Male"},
    {"Lacey", "Pierce", "Male"},
    {"Edward", "Pineda", "Male"},
    {"Omari", "Carroll", "Male"},
    {"Rabia", "Hawkins", "Male"},
    {"Gilbert", "Hendrix", "Male"},
    {"Holly", "Bennett", "Male"},
    {"Amie", "Curtis", "Male"},
    {"Josh", "Frazier", "Male"},
    {"Leroy", "Donnelly", "Male"},
    {"Fatma", "Cuevas", "Male"},
    {"Keiran", "Ross", "Male"},
    {"Timothy", "George", "Male"},
    {"Musa", "Olson", "Male"},
    {"Amin", "Heath", "Male"},
    {"Ethan", "Tran", "Male"},
    {"Cindy", "Chapman", "Male"},
    {"Keyaan", "Dorsey", "Male"},
    {"Aimee", "Dawson", "Male"},
    {"Austin", "Castillo", "Male"}
  };

  @BeforeMethod
  public void setUp() {
    this.currentUser = new ArrayList<UserModel>();
    
    boolean configCsvExists = this.isFileExists(FileLocations
        .getConfigLocationTest());
    
    if (!configCsvExists) {
      this.setupConfigurationWorker = new SetupConfigurationWorker("1qaz#EDC"
        .toCharArray(), FileLocations.getConfigLocationTest());
    }

    for (String[] arrayIterator : this.userData) {
      String firstName = arrayIterator[0];
      String lastName = arrayIterator[1];
      String gender = arrayIterator[2];


      this.adminAddUserWorkerTesting =
          new AdminAddUserWorkerTesting(
        firstName,
        lastName,
        gender,
        FileLocations.getUserDbLocationTest(),
        FileLocations.getConfigLocationTest()
      );

      adminAddUserWorkerTesting.execute();

      try {
        UserModel currentUser = adminAddUserWorkerTesting.get();
        this.currentUser.add(currentUser);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (ExecutionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }

  @Test
  public void nullValueFoundTest() throws Exception {
    Iterator<UserModel> currentUserIterator = currentUser.iterator();
    while (currentUserIterator.hasNext()) {
      UserModel currentUser = currentUserIterator.next();
      Assert.assertNotNull(currentUser.getUserName());
      Assert.assertNotNull(currentUser.getUserFirstName());
      Assert.assertNotNull(currentUser.getUserLastName());
      Assert.assertNotNull(currentUser.getUserPassword());
      Assert.assertNotNull(currentUser.getUserSalt());
      Assert.assertNotNull(currentUser.getUserNumber());
    }
  }

  @Test
  public void firstNameValid() throws Exception {
    Iterator<UserModel> currentUserIterator = currentUser.iterator();
    int rowCurrent = 0;

    while (currentUserIterator.hasNext()) {
      UserModel currentUser = currentUserIterator.next();

      Assert.assertEquals(currentUser.getUserFirstName(),
          this.userData[rowCurrent][firstNameColumn]);

      rowCurrent++;
    }
  }

  @Test
  public void lastNameValid() throws Exception {
    Iterator<UserModel> currentUserIterator = currentUser.iterator();
    int rowCurrent = 0;

    while (currentUserIterator.hasNext()) {
      UserModel currentUser = currentUserIterator.next();

      Assert.assertEquals(currentUser.getUserLastName(),
          this.userData[rowCurrent][lastNameColumn]);

      rowCurrent++;
    }
  }

  @Test
  public void saltValid() throws Exception {
    Iterator<UserModel> currentUserIterator = currentUser.iterator();
    int rowCurrent = 0;

    while (currentUserIterator.hasNext()) {
      UserModel currentUser = currentUserIterator.next();

      char[] currentPasswordAttempted =
        this.userData[rowCurrent][passwordColumn]
        .toCharArray();

      Assert.assertNotNull(currentUser.getUserSalt(), "User Salt Null");
      Assert.assertTrue(currentUser.getUserSalt().length == 16, "User Salt Byte Size Incorrect");


      assertTrue(PasswordEncryption.validateEnteredPassword(
          currentPasswordAttempted,
          currentUser.getUserSalt(),
          currentUser.getUserPassword()
      ));

      rowCurrent++;
    }
  }

  @Test
  public void passwordValid() throws Exception {
    Iterator<UserModel> currentUserIterator = currentUser.iterator();
    int rowCurrent = 0;

    while (currentUserIterator.hasNext()) {
      UserModel currentUser = currentUserIterator.next();

      char[] currentPasswordAttempted =
        this.userData[rowCurrent][passwordColumn].toCharArray();

      assertTrue(PasswordEncryption.validateEnteredPassword(
          currentPasswordAttempted,
          currentUser.getUserSalt(),
          currentUser.getUserPassword()
      ));

      rowCurrent++;
    }
  }
  
  private boolean isFileExists(final String pathToCheck) {
    Path path = Paths.get(pathToCheck);
    return Files.exists(path);
  }
}