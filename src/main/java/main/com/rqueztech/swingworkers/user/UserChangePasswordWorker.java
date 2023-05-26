package main.com.rqueztech.swingworkers.user;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

import main.com.rqueztech.FileLocations;
import main.com.rqueztech.csv.admin.UserCsvManager;
import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.ui.user.UserCentralPanel;

/**
 * Responsible for authenticating the user login. The class takes the user name
 and password, searches the csv file to see if the password exists, and checks
 if the password entered for the user is valid or not.
 *
 * @extends SwingWorker SwingWorker extends the swing worker to execute
 processes on the swing worker and prevent locking up the EDT
 */
public class UserChangePasswordWorker extends SwingWorker<Boolean, Void> {
  private String userName;
  private char[] userNewPassword;
  private char[] userNewPasswordRepeat;

  private CSVReader csvReader;
  private CSVWriter csvWriter;
  private boolean isErrorWriting;
  private FileLocations fileLocations;

  private final String userFileString = "src/main/resources/data/userdatabase.csv";

  /**
   * Create the instance that passes the necessary variables to perform the
   operations.
   *
   * @param userName user name account as (a String)
   * @param userNewPassword user password as (a char array)
   * @param userNewPasswordRepeat confirmation as (a char array)
   */
  public UserChangePasswordWorker(char[] userName, char[] userNewPassword,
      char[] userNewPasswordRepeat) {

    this.userName = new String(userName);
    this.userNewPassword = userNewPassword;
    this.userNewPasswordRepeat = userNewPasswordRepeat;

  }

  @Override
  protected Boolean doInBackground() throws Exception {
    this.fileLocations = new FileLocations();

    UserCsvManager userCsvManager = new UserCsvManager(FileLocations.getUserDbLocationMain());
    /*
     * Initialize the file reader and return true/false value to indicate
     whether a file exists or not.
     */
    if (!initializeReader()
        || !Arrays.equals(this.userNewPassword,
        this.userNewPasswordRepeat)) {
      return false; // Return false, no file was found.
    }

    byte[] newSalt = PasswordEncryption.generateSalt();
    byte[] hashedNewPassword = PasswordEncryption.hashPassword(userNewPassword, newSalt);

    // Read the csv file the rows String[] list
    List<String[]> rows = userCsvManager.retrieveData();

    // Iterate through the rows String[] list
    for (String[] row : rows) {
      if (row[0].equals(this.userName)) {
        row[4] = Base64.getEncoder().encodeToString(hashedNewPassword); // Hashed Password
        row[5] = Base64.getEncoder().encodeToString(newSalt); // New User Salt
        this.initializeWriter();

        csvWriter.writeAll(rows);
        csvWriter.flush();
        csvWriter.close();
        return true; // Return True, Update was successful
      }
    }

    this.isErrorWriting = true;
    return false;
  }

  @Override
  protected void done() {
    try {
      boolean result = get(); // Retrieve the result from doInBackground()

      if (result && !this.isErrorWriting) {
        JOptionPane.showMessageDialog(null, "Updated Successfully");
      } else if (!result && this.isErrorWriting) {
        JOptionPane.showMessageDialog(null, "Error Writing");
      } else {
        System.out.println(JOptionPane.showMessageDialog(null, );
      }
    } catch (InterruptedException | ExecutionException e) {
      e.printStackTrace();
    }
  }

  /**
   * Initializes the reader and catches exceptions if the file is not found.
   */
  public boolean initializeReader() {
    /*
     * Create the file reader that will read the information from the CSV file.
     */
    try {
      this.csvReader = new CSVReader(new FileReader(userFileString));
      return true;

    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Initialize the csv writer.
   */
  public void initializeWriter() {
    /*
     * Create the file writer that will write the new information into the CSV
     * file.
     */
    try {
      this.csvWriter = new CSVWriter(new FileWriter(userFileString));

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
