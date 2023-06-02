package main.com.rqueztech.ui.admin.usernamecreation;

import java.io.IOException;
import main.com.rqueztech.csv.admin.UserCsvManager;

public class CreateUserName {
  private UserCsvManager userCsvManager;

  public CreateUserName(String filePath) {
    this.userCsvManager = new UserCsvManager(filePath);
  }

  // --------------------------------------------------------------------------
  // NOTE: This should not be default. It is best to make a randomized password.
  // For the sake of simplicity, we will make it default to a simple default. Please
  // Change in the future.
  public char[] setUserDefaultPassword(String userLastName) {
    // Initial base for the default password goes here
    char[] defaultUserPassword = {'a', 'b', 'c', userLastName.charAt(0)};
    return defaultUserPassword;
  }

  // --------------------------------------------------------------------------
  public String createUserAccountName(String firstName, String lastName) {
    // Get the first string of the account name.
    StringBuilder accountNameCandidate = new StringBuilder();
    accountNameCandidate.append(firstName.substring(0, 1).toLowerCase());

    int lastNameLength = lastName.length();

    if (lastNameLength >= 2 && lastNameLength < 4) {
      accountNameCandidate.append(lastName.substring(0, lastNameLength).toLowerCase());
    } else {
      accountNameCandidate.append(lastName.substring(0, 4).toLowerCase());
    }

    try {    
      // 
      boolean createNewAccount = false;

      String[] allUsers = this.userCsvManager
          .retrieveAccountData(accountNameCandidate.toString());

      if (allUsers == null) {
        return accountNameCandidate.toString();
      }

      int counter = 1;

      while (!createNewAccount) {
        allUsers = this.userCsvManager
          .retrieveAccountData(accountNameCandidate.toString() + counter);
        if (allUsers == null) {
          accountNameCandidate.append(counter);
          createNewAccount = true;
        } else {
          counter++;
        }
      }
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return accountNameCandidate.toString();
  }

  /**
   * The cleanStrings function takes the name desired to be cleaned as a
   * parameter, cleaned format follows first character to upper case followed
   * by all lowercase.
   *
   * @param name First and Last name string variable of string to be cleaned.
   * @return Returns the toString version of the string builder used.
   */
  public String cleanStrings(String name) {
    StringBuilder sb = new StringBuilder();

    sb.append(name.substring(0, 1).toUpperCase());
    sb.append(name.substring(1).toLowerCase());

    return sb.toString();
  }
}
