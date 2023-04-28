package main.com.rqueztech.interfaces.admin;

import main.com.rqueztech.models.user.UserModel;

/**
 * The AdminModelViewAddUserInterface implements the functions for the manual
 inputs required for setting the user. This includes the First name, last name,
 and gender of the user.
 */
public interface AdminModelViewAddUserInterface {
  UserModel getUser();

  void setUserFirstName(String userFirstName);

  void setUserLastName(String userLastName);

  void setUserGender(String gender);
}