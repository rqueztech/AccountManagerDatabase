package main.com.rqueztech.models.loggedinuser;

/**
 * Sets and gets the current user that is logged into account. Do not use long
 term, for presentation only.
 */
public class LoggedInUser {
  private String currentUserLogged;

  public LoggedInUser(String currentUserLogged) {
    this.currentUserLogged = currentUserLogged;
  }

  public String getCurrentLoggedInUser() {
    return this.currentUserLogged;
  }
}
