package main.com.rqueztech.models.loggedinuser;

import java.util.Arrays;

/**
 * Sets and gets the current user that is logged into account. Do not use long
 term, for presentation only.
 */
public class LoggedInUser {

  private char[] currentUserLogged;

  public char[] getCurrentLoggedInUser() {
    return this.currentUserLogged;
  }

  public void setLoggedInUser(char[] currentUserLogged) {
    this.currentUserLogged = currentUserLogged;
  }

  public void clearLoggedInUser() {
    Arrays.fill(this.currentUserLogged, '\0');
    this.currentUserLogged = new char[] {};
  }
}