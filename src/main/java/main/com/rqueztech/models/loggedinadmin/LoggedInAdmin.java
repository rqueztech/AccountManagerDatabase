package main.com.rqueztech.models.loggedinadmin;

import java.util.Arrays;

/**
 * Sets and gets the current admin that is logged into account. Do not use long
 term, for presentation only.
 */
public class LoggedInAdmin {

  private char[] currentAdminLogged;

  public char[] getCurrentLoggedInAdmin() {
    return this.currentAdminLogged;
  }

  public void setLoggedInAdmin(char[] currentAdminLogged) {
    this.currentAdminLogged = currentAdminLogged;
  }

  public void clearLoggedInAdmin() {
    Arrays.fill(this.currentAdminLogged, '\0');
    this.currentAdminLogged = new char[] {};
  }
}
