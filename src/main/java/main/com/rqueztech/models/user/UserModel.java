package main.com.rqueztech.models.user;

import main.com.rqueztech.ui.validation.InputValidations;

/**
 * The UserModel sets the model used in the user file.
 */
public class UserModel {
  private String userAccountName;
  private String userFirstName;
  private String userLastName;
  private String gender;
  private byte[] userHashedPassword;
  private byte[] userSalt;
  private int userNumber;

  private InputValidations inputValidations;

  /**
   * The user model stores the information required for an user account.
   *
   * @param userAccountName the account name as (a String)
   * @param userFirstName the user's first name as (a String)
   * @param userLastName the user's last name as (a String)
   * @param gender the user's gender as (a String)
   * @param userHashedPassword the user's password as (a byte array)
   * @param userSalt the user's salt as (a byte array)
   * @param userNumber the user's number as (an integer)
   */
  public UserModel(
      String userAccountName,
      String userFirstName,
      String userLastName,
      String gender,
      byte[] userHashedPassword,
      byte[] userSalt,
      int userNumber) {

    this.inputValidations = new InputValidations();

    this.setUserName(userAccountName);
    this.setUserFirstName(userFirstName);
    this.setUserLastName(userLastName);
    this.setGender(gender);
    this.setUserPassword(userHashedPassword);
    this.setUserSalt(userSalt);
    this.setUserNumber(userNumber);
  }

  // --------------------------------------------------------------------------
  public String getGender() {
    return this.gender;
  }

  // --------------------------------------------------------------------------
  public void setGender(String gender) {
    this.gender = gender;
  }

  // --------------------------------------------------------------------------
  public String getUserName() {
    return this.userAccountName;
  }

  // --------------------------------------------------------------------------
  public void setUserName(String userAccountName) {
    this.userAccountName = userAccountName;
  }

  // --------------------------------------------------------------------------
  public String getUserFirstName() {
    return userFirstName;
  }

  /**
  * Sets the user's first name.
  *
  * @param userFirstName The user's first name to set.
  * @throws IllegalArgumentException If the userFirstName parameter contains non-letter characters.
  */
  public void setUserFirstName(String userFirstName) {
    if (this.inputValidations.isOnlyLetterCharacters(userFirstName)) {
      this.userFirstName = userFirstName;
    }
  }

  /**
  * Sets the user's last name.
  *
  * @param userLastName The user's last name to set.
  * @throws IllegalArgumentException If the userLastName parameter contains non-letter characters.
  */
  public void setUserLastName(String userLastName) {
    if (this.inputValidations.isOnlyLetterCharacters(userLastName)) {
      this.userLastName = userLastName;
    }
  }

  // --------------------------------------------------------------------------
  public String getUserLastName() {
    return userLastName;
  }

  // --------------------------------------------------------------------------
  public byte[] getUserPassword() {
    return userHashedPassword;
  }

  // --------------------------------------------------------------------------
  public void setUserPassword(byte[] userHashedPassword) {
    this.userHashedPassword = userHashedPassword;
  }

  // --------------------------------------------------------------------------
  public byte[] getUserSalt() {
    return this.userSalt;
  }

  // --------------------------------------------------------------------------
  public void setUserSalt(byte[] userSalt) {
    this.userSalt = userSalt;
  }

  // --------------------------------------------------------------------------
  public int getUserNumber() {
    return userNumber;
  }

  // --------------------------------------------------------------------------
  public void setUserNumber(int userNumber) {
    this.userNumber = userNumber;
  }
}
