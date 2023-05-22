package main.com.rqueztech.swingworkers.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import main.com.rqueztech.FileLocations;
import main.com.rqueztech.csv.admin.UserCsvManager;
import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.interfaces.admin.AdminModelViewAddUserInterface;
import main.com.rqueztech.models.user.UserModel;

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
  private final String fileLocationString;

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
      final String fileLocation) {

    this.userFirstName = userFirstName;
    this.userLastName = userLastName;
    this.gender = gender;
    this.fileLocationString = fileLocation;
  }

  // --------------------------------------------------------------------------
  @Override
  protected UserModel doInBackground() throws Exception {

    this.createUserAccountName();

    // Key to append: This character will append to the end of
    // The default password. This can be changed later. Later iterations
    // Of the program will set randomized default passwords for users.
    char defaultUserPasswordAppend = this.userFirstName.toUpperCase().charAt(0);

    // Call the function to set the default user password
    this.setUserDefaultPassword(defaultUserPasswordAppend);

    // Generate the salt that will be used in hashing the user's password
    this.newUserSalt = this.generateUserSalt(); 

    // hash the user's password. This will be the
    // Password stored in the final model.
    this.userNewHashedPassword = this.hashUserPassword();                         

    // TODO Auto-generated method stub
    if (PasswordEncryption.validateEnteredPassword(adminPassphraseAttempt,
        userNewHashedPassword, newUserSalt)) {
      return null;
    }

    this.writeToCsvFile();

    // Note: Please change in the future
    this.userNumber = 0;  // Current number is 0. Placeholder.

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
    UserCsvManager userCsvManager = new UserCsvManager(this.fileLocationString);
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
  // NOTE: This should not be default. It is best to make a randomized password.
  // For the sake of simplicity, we will make it default to a simple default. Please
  // Change in the future.
  private void setUserDefaultPassword(char userFirstNameLetter) {
    // Initial base for the default password goes here
    char[] defaultUserPassword = {'a', 'b', 'c', userFirstNameLetter};
    this.defaultUserPassword = defaultUserPassword;
  }

  // --------------------------------------------------------------------------
  private void createUserAccountName() {
    // Get the first string of the account name.
    String firstAccountNameString = this.userFirstName.substring(0, 1);
    String secondAccountNameString = "";

    int lastNameLength = this.userLastName.length();

    if (lastNameLength >= 2 && lastNameLength < 4) {
      secondAccountNameString = this.userLastName.substring(0, lastNameLength);
    } else {
      secondAccountNameString = this.userLastName.substring(0, 4);
    }

    this.userAccountName = String.format("%s%s", firstAccountNameString, secondAccountNameString);
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
