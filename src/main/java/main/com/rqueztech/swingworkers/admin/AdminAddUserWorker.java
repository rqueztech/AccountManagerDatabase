package main.com.rqueztech.swingworkers.admin;

import java.awt.Panel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.print.event.PrintJobAttributeEvent;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import main.com.rqueztech.FileLocations;
import main.com.rqueztech.csv.admin.UserCsvManager;
import main.com.rqueztech.csv.configuration.ConfigurationCsvManager;
import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.interfaces.admin.AdminModelViewAddUserInterface;
import main.com.rqueztech.models.user.UserModel;
import main.com.rqueztech.ui.admin.usernamecreation.CreateUserName;

/**
 * The AdminAddUserWorker class is a SwingWorker that creates a new user model
 with the specified user information. It also generates a default password for
 the user and hashes the password using a salt.
 *
 * @extends SwingWorker independent swing worker to ensure that separate thread
 is used when performing long-running operations that may block the EDT.
 * @implements AdminModelViewAddUserInterface contains all of the functions to
 be implemented in the AdminAddUser Swing Worker.
 */
public class AdminAddUserWorker extends SwingWorker<UserModel, Void>
    implements AdminModelViewAddUserInterface {

  // NOTE: Creation of the default user password is automatic. No User Password field
  // Gets passed here.
  private String userFirstName;
  private String userLastName;
  private String gender;

  // Varibles that will be created in this class.
  private String userAccountName;
  private byte[] newUserSalt;
  private char[] defaultUserPassword;
  private char[] adminPassphraseAttempt;

  // This is the current user's number
  private int userNumber;

  // hash the password from all of these parameters
  private byte[] userNewHashedPassword;
  private final String userDatabaseFileLocationString;
  private UserCsvManager userCsvManager;
  private ConfigurationCsvManager configurationCsvManager;
  private String configurationFileLocation;

  private CreateUserName createUserName;

  /**
   * Constructs a new AdminAddUserWorker with the specified user information.
   *
   * @param userFirstName The first name of the user to be added
   * @param userLastName The last name of the user to be added
   * @param gender The gender of the user to be added
   */
  public AdminAddUserWorker(
      String userFirstName,
      String userLastName,
      String gender,
      final String userDatabaseFileLocation,
      final String configurationFileLocation) {

    this.userFirstName = userFirstName;
    this.userLastName = userLastName;
    this.gender = gender;
    this.userDatabaseFileLocationString = userDatabaseFileLocation;
    this.configurationFileLocation = configurationFileLocation;
  }

  // --------------------------------------------------------------------------
  @Override
  protected UserModel doInBackground() throws Exception {
    this.userCsvManager = new UserCsvManager(this.userDatabaseFileLocationString);
    this.createUserName = new CreateUserName(FileLocations
      .getUserDbLocationMain());

    this.configurationCsvManager = new ConfigurationCsvManager(this
      .configurationFileLocation);

    this.userAccountName = this.createUserName
      .createUserAccountName(this.userFirstName, this.userLastName);

    // Call the function to set the default user password
    this.defaultUserPassword = this.createUserName
        .setUserDefaultPassword(this.userLastName);

    // Generate the salt that will be used in hashing the user's password
    this.newUserSalt = this.generateUserSalt();

    // hash the user's password. This will be the
    // Password stored in the final model.
    this.userNewHashedPassword = this.hashUserPassword();               

    // TODO Auto-generated method stub
    if (PasswordEncryption.validateEnteredPassword(this.adminPassphraseAttempt,
        this.userNewHashedPassword, this.newUserSalt)) {
      return null;
    }

    this.userFirstName = this.createUserName.cleanStrings(this.userFirstName);
    this.userLastName = this.createUserName.cleanStrings(this.userLastName);

    // Note: Please change in the future
    List<String[]> configurationFile = this.configurationCsvManager
        .retrieveData();  // Current number is 0. Placeholder.
    
    this.userNumber = Integer.parseInt(configurationFile.get(1)[1]);
    this.increaseEmployeeNumber();
    configurationFile.get(1)[1] = Integer.toString(this.userNumber);
    
    this.configurationCsvManager.createConfigurationFile(configurationFile);

    this.writeToCsvFile();
    
    this.clearUserPassword();

    // Finally: Create the new user model with all of the appropriate information
    return new UserModel(this.userAccountName, this.userFirstName,
      this.userLastName, this.gender, this.userNewHashedPassword,
      this.newUserSalt, this.userNumber
    );
  }

  // --------------------------------------------------------------------------
  @Override
  protected void done() {
    try {
      // Get the result of the SwingWorker's background task
      UserModel result = get();

      if (result != null) {
        // Handle the successful completion of the task here
        String message = "User added successfully";
        JOptionPane.showMessageDialog(null, message);

      } else {
        JOptionPane.showMessageDialog(null, "Password Incorrect");
      }

    } catch (InterruptedException | ExecutionException ex) {
      // Handle any exceptions that were thrown during the background task here
      String errorMessage = "Error: " + ex.getMessage();
      JOptionPane.showMessageDialog(null, errorMessage, "Error",
          JOptionPane.ERROR_MESSAGE);
    } catch (CancellationException ex) {
      // Handle cancellation of the background task here
      String message = "The task was cancelled.";
      JOptionPane.showMessageDialog(null, message);
    }
  }

  /**
   * Writes the configuration data to a CSV file using the UserCsvManager class.
   The method converts the password to base64 encoding and adds it to a list of
   configuration data.
   *
   * @throws IOException if there is an error writing to the CSV file.
  */
  public void writeToCsvFile() {
    UserCsvManager userCsvManager = new UserCsvManager(this.userDatabaseFileLocationString);
    List<String[]> userData = new ArrayList<String[]>();

    // Convert the password to base 64 encoding
    userData.add(new String[] {
        this.userAccountName,
        this.userFirstName,
        this.userLastName,
        this.gender,
        Base64.getEncoder().encodeToString(this.userNewHashedPassword),
        Base64.getEncoder().encodeToString(this.newUserSalt),
        Integer.toString(this.userNumber)
    });

    try {
      userCsvManager.addData(userData);
      List<String[]> hereItIs = userCsvManager.retrieveData();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  // --------------------------------------------------------------------------
  private byte[] generateUserSalt() {
    // Generate a salt in byte[] format
    return PasswordEncryption.generateSalt();
  }

  // --------------------------------------------------------------------------
  private byte[] hashUserPassword() {
    return PasswordEncryption.hashPassword(this.defaultUserPassword, this.newUserSalt);
  }

  private void clearUserPassword() {
    Arrays.fill(this.defaultUserPassword, '\0');
  }

  private void increaseEmployeeNumber() {
    this.userNumber++;
  }

  private int getEmployeeNumber() {
    return this.userNumber;
  }

  @Override
  public UserModel getUser() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setUserFirstName(String userFirstName) {
    // TODO Auto-generated method stub
    this.userFirstName = userFirstName;
  }

  @Override
  public void setUserLastName(String userLastName) {
    // TODO Auto-generated method stub
    this.userLastName = userLastName;
  }

  @Override
  public void setUserGender(String gender) {
    // TODO Auto-generated method stub
    this.gender = gender;
  }
}
