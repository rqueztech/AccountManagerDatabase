package com.test.adminfunctions;

import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.swingworkers.admin.AdminAddAdminWorkerTesting;
import org.testng.annotations.Test;

public class CreateAdminTest {
  @Test
  public void testDoInBackground() throws Exception {
    String firstName = "Admington";
    String lastName = "Gladmington";
    char[] password = "P@ssword1234".toCharArray();

    AdminAddAdminWorkerTesting adminAddAdminWorkerTesting =
        new AdminAddAdminWorkerTesting(firstName, lastName, password);

    adminAddAdminWorkerTesting.execute();

    String adminName = adminAddAdminWorkerTesting.get().getAdminName();
    String adminFirstName = adminAddAdminWorkerTesting.get().getAdminFirstName();
    String adminLastName = adminAddAdminWorkerTesting.get().getAdminLastName();
    byte[] adminPassword = adminAddAdminWorkerTesting.get().getAdminPassword();
    byte[] adminSalt = adminAddAdminWorkerTesting.get().getAdminSalt();
    int adminNumber = adminAddAdminWorkerTesting.get().getAdminNumber();

    System.out.println(
        adminName + "\n"
        + adminFirstName + "\n"
        + adminLastName + "\n"
        + adminPassword + "\n"
        + adminSalt + "\n"
        + adminNumber + "\n"
    );

    System.out.println(
        PasswordEncryption.validateEnteredPassword(password, adminSalt, adminPassword)
    );
  }
}
