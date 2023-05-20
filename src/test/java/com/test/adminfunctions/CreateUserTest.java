package com.test.adminfunctions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import main.com.rqueztech.FileLocations;
import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.models.user.UserModel;
import main.com.rqueztech.swingworkers.admin.AdminAddUserWorkerTesting;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The test takes a list of names and passwords and inputs and uses said inputs
 to create administrator accounts.
 */
public class CreateUserTest {
  private List<UserModel> currentUser;
  private File createFile;

  // Create variables that will avoid magic numbers
  private final int firstNameColumn = 0;
  private final int lastNameColumn = 1;
  private final int passwordColumn = 2;

  private final String fileLocation = new FileLocations().getUserDbLocationTest();

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
    this.createFile = new File(fileLocation);

    if (this.createFile.exists()) {
      this.createFile.delete();
    }

    for (String[] arrayIterator : this.userData) {
      String firstName = arrayIterator[0];
      String lastName = arrayIterator[1];
      String gender = arrayIterator[2];


      AdminAddUserWorkerTesting adminAddUserWorkerTesting =
          new AdminAddUserWorkerTesting(
          firstName,
          lastName,
          gender,
              new FileLocations().getUserDbLocationTest() 
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

    for (String[] item : userData) {
      System.out.println(item[0] + item[1] + item[2] + item[3]);
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

}