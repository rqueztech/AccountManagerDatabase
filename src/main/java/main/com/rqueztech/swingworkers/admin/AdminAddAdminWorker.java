package main.com.rqueztech.swingworkers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import main.com.rqueztech.FileLocations;
import main.com.rqueztech.csv.admin.AdminCsvManager;
import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.models.admin.AdminModel;

/**
 * The AdminAddAdminWorker performs the heavy lifting of creating all of the
 functions required to add an admin to the CSV file. This is done to prevent
 locking up the EDT.
 *
 * @extends SwingWorker extends the swing worker to execute processes on the
 swing worker and prevent locking up the EDT
 */
public class AdminAddAdminWorker extends SwingWorker<AdminModel, Void> {
  private String adminAccountName;
  private String adminFirstName;
  private String adminLastName;

  private char[] newAdminPassword;
  private byte[] encryptedAdminPassword;
  private byte[] newAdminPasswordSalt;

  private String fileLocationString;
  private int adminNumber;


  /**
   * The specific constructor for AdminAddWorker that takes the parameters
   * from the user required to add the user properly.
   *
   * @param adminFirstName takes the admin first name as (a String)
   * @param adminLastName takes the admin last name as (a String)
   * @param newAdminPassword takes the new admin password as (a char array)
   */
  public AdminAddAdminWorker(
      String adminFirstName,
      String adminLastName,
      char[] newAdminPassword,
      final String fileLocation) {

    this.adminFirstName = adminFirstName;
    this.adminLastName = adminLastName;
    this.newAdminPassword = newAdminPassword;
    this.fileLocationString = fileLocation;
  }

  // --------------------------------------------------------------------------
  @Override
  protected AdminModel doInBackground() throws Exception {
    // TODO Auto-generated method stub

    this.adminAccountName = createAdminAccountName();

    // Create the salt that will be used for the admin password
    this.newAdminPasswordSalt = this.generateNewAdminPasswordSalt();

    this.encryptedAdminPassword = PasswordEncryption  // Create the hashed encrypted password
              .hashPassword(this.newAdminPassword,
          this.newAdminPasswordSalt);

    this.adminFirstName = this.cleanStrings(this.adminFirstName);
    this.adminLastName = this.cleanStrings(this.adminLastName);

    this.writeToCsvFile();

    return new AdminModel(
      this.adminAccountName,
      this.adminFirstName,
      this.adminLastName,
      this.encryptedAdminPassword,
      this.newAdminPasswordSalt,
      this.adminNumber
    );
  }

  // --------------------------------------------------------------------------
  @Override
  protected void done() {
    try {
      // Get the result of the SwingWorker's background task
      AdminModel result = get();

      // Handle the successful completion of the task here
      String message = "Admin added successfully.\n"
            + result.getAdminName() + "\n"
            + result.getAdminFirstName() + "\n"
            + result.getAdminLastName() + "\n"
            + result.getAdminPassword() + "\n"
            + result.getAdminSalt() + "\n"
            + result.getAdminNumber() + "\n"; // End String message

      //JOptionPane.showMessageDialog(null, message);

    } catch (InterruptedException | ExecutionException ex) {
      // Handle any exceptions that were thrown during the background task here
      String errorMessage = "Error: " + ex.getMessage();
      JOptionPane.showMessageDialog(null, errorMessage, "Error",
          JOptionPane.ERROR_MESSAGE);

      ex.printStackTrace();
    } catch (CancellationException ex) {
      // Handle cancellation of the background task here
      String message = "The task was cancelled.";
      JOptionPane.showMessageDialog(null, message);
    }
  }

  /**
   * Writes data to the admin CSV manager file.
   */
  public void writeToCsvFile() {
    AdminCsvManager adminCsvManager = new AdminCsvManager(this.fileLocationString);
    List<String[]> adminData = new ArrayList<String[]>();

    // Convert the password to base 64 encoding
    adminData.add(new String[] {
        this.adminAccountName,
        this.adminFirstName,
        this.adminLastName,
        Base64.getEncoder().encodeToString(this.encryptedAdminPassword),
        Base64.getEncoder().encodeToString(this.newAdminPasswordSalt),
        Integer.toString(this.adminNumber)
    });

    try {
      adminCsvManager.addData(adminData);
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // --------------------------------------------------------------------------
  private String createAdminAccountName() {
    // Get the first string of the account name.
    String firstAccountNameString = this.adminFirstName.substring(0, 1).toLowerCase();
    String secondAccountNameString = "";

    int lastNameLength = this.adminLastName.length();

    if (lastNameLength < 4) {
      secondAccountNameString = this.adminLastName.toLowerCase();
    } else {
      secondAccountNameString = this.adminLastName.substring(0, 4).toLowerCase();
    }

    return String.format("%s%s", firstAccountNameString, secondAccountNameString);
  }

  //--------------------------------------------------------------------------
  public String cleanStrings(String name) {
    StringBuilder sb = new StringBuilder();

    sb.append(name.substring(0, 1).toUpperCase());
    sb.append(name.substring(1).toLowerCase());

    return sb.toString();
  }

  // --------------------------------------------------------------------------
  private byte[] generateNewAdminPasswordSalt() {
    return PasswordEncryption.generateSalt();
  }

}
