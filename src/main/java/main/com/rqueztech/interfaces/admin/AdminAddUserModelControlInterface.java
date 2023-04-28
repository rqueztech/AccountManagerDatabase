package main.com.rqueztech.interfaces.admin;

import main.com.rqueztech.models.user.UserModel;


/**
 * This interface defines the methods for adding a new user. The
 * interface requires the implementation for the methods to get
 * all of the relevant user information
 */
public interface AdminAddUserModelControlInterface {
  UserModel getUser();

  /**
   * Set the user account name for a new user.

   * @param userAccountName Sets the name for the user account as (a String)
   */
  void setUserAccountName(String userAccountName);

  /**
   * Set the first name for the new user.

   * @param userFirstName Sets the first name for the user as (a String)
   */
  void setUserFirstName(String userFirstName);

  /**
   * Set the last name for the new user.

   * @param userLastName Sets the last name for the user as (a String)
   */
  void setUserLastName(String userLastName);

  /**
   * Set the password of the user.

   * @param userPassword sets the user password as (a String)
   */
  void setUserPassword(char[] userPassword);

  /**
   * Set the salt for the user.

   * @param userSalt Sets the salt for the user password as (a byte array)
   */
  void setUserSalt(byte userSalt);

  /**
   * Set the current user number.

   * @param userNumber Sets the salt for the user password as (a integer)
   */
  void setUserNumber(int userNumber);
}
