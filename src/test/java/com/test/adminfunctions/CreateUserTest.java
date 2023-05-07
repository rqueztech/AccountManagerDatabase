package com.test.adminfunctions;

import main.com.rqueztech.FileLocations;
import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.swingworkers.admin.AdminAddUserWorkerTesting;
import org.testng.annotations.Test;

/**
 * The test sets a model with all of the information required to create a user,
 and tests to see if the user was properly created.h
 */
public class CreateUserTest {
  @Test
  public void testDoInBackground() throws Exception {
    String firstName = "Jonathan";
    String lastName = "Doeington";
    String gender = "male";

    AdminAddUserWorkerTesting adminAddUserWorkerTesting =
        new AdminAddUserWorkerTesting(firstName, lastName, gender,
            new FileLocations().getUserDbLocationTest());

    adminAddUserWorkerTesting.execute();

    String userName = adminAddUserWorkerTesting.get().getUserName();
    String userFirstName = adminAddUserWorkerTesting.get().getUserFirstName();
    String userLastName = adminAddUserWorkerTesting.get().getUserLastName();
    String userGender = adminAddUserWorkerTesting.get().getGender();
    byte[] userPassword = adminAddUserWorkerTesting.get().getUserPassword();
    byte[] userSalt = adminAddUserWorkerTesting.get().getUserSalt();
    int userNumber = adminAddUserWorkerTesting.get().getUserNumber();

    System.out.println(
        userName + "\n"
        + userFirstName + "\n"
        + userLastName + "\n"
        + userGender + "\n"
        + userPassword + "\n"
        + userSalt + "\n"
        + userNumber + "\n"
    );

    System.out.println(
        PasswordEncryption.validateEnteredPassword("abcJ".toCharArray(), userSalt, userPassword)
    );
  }
}