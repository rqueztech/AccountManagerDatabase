package main.com.rqueztech.swingworkers.admin;

import java.util.function.Supplier;

import main.com.rqueztech.FileLocations;
import main.com.rqueztech.models.admin.AdminModel;

/**
 * The AdminAddAdminWorkerTesting class is used for testing the functionality
 of the AdminAddAdminWorker class. It extends the AdminAddAdminWorker class and
 provides a getInstance method to get an instance of this class and a
 testDoInBackground method to test the doInBackground method of the
 AdminAddAdminWorker class.
 *
 *@extends AdminAddAdminWorkerTesting
*/
public class AdminAddAdminWorkerTesting extends AdminAddAdminWorker {

  private static final Supplier<AdminAddAdminWorkerTesting> INSTANCE =
      () -> new AdminAddAdminWorkerTesting("adminFirstName",
      "adminLastName", new char[0], FileLocations
      .getAdminDbLocationTest());

  public AdminAddAdminWorkerTesting(String adminFirstName, String adminLastName,
        char[] newAdminpassword, final String fileLocation) {
    super(adminFirstName, adminLastName, newAdminpassword, FileLocations
        .getAdminDbLocationTest());
  }

  public static AdminAddAdminWorkerTesting getInstance() {
    return INSTANCE.get();
  }

  /**
   * Calls the doInBackground method of the AdminAddAdminWorker class and
   returns the result. If an exception is thrown, it will print the stack
   trace and return null.
   *
   * @return an instance of the AdminModel class representing the admin
   added, or null if an exception occurs.
   */
  public AdminModel testDoInBackground() {
    try {
      AdminModel adminModel = doInBackground();
      return adminModel;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
