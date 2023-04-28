package main.com.rqueztech.models.admin;

/**
 * The AdminModel sets the model used in the admin file.
 */
public class AdminModel {
  private String adminAccountName;
  private String adminFirstName;
  private String adminLastName;
  private byte[] adminPassword;
  private byte[] adminSalt;
  private int adminNumber;

  /**
   * The admin model stores the information required for an admin account.
   *
   * @param adminAccountName the account name as (a String)
   * @param adminFirstName the admin's first name as (a String)
   * @param adminLastName the admin's last name as (a String)
   * @param adminPassword the admin's password as (a byte array)
   * @param adminSalt the admin's salt as (a byte array)
   * @param adminNumber the admin's number as (an integer)
   */
  public AdminModel(
      String adminAccountName,
      String adminFirstName,
      String adminLastName,
      byte[] adminPassword,
      byte[] adminSalt,
      int adminNumber) {

    this.setAdminAccountName(adminAccountName);
    this.setAdminFirstName(adminFirstName);
    this.setAdminLastName(adminLastName);
    this.setAdminPassword(adminPassword);
    this.setAdminSalt(adminSalt);
    this.setAdminNumber(adminNumber);
  }

  // --------------------------------------------------------------------------
  public String getAdminName() {
    return this.adminAccountName;
  }

  // --------------------------------------------------------------------------
  public void setAdminAccountName(String adminAccountName) {
    this.adminAccountName = adminAccountName;
  }

  // --------------------------------------------------------------------------
  public String getAdminFirstName() {
    return adminFirstName;
  }

  // --------------------------------------------------------------------------
  public void setAdminFirstName(String adminFirstName) {
    this.adminFirstName = adminFirstName;
  }

  // --------------------------------------------------------------------------
  public String getAdminLastName() {
    return adminLastName;
  }

  // --------------------------------------------------------------------------
  public void setAdminLastName(String adminLastName) {
    this.adminLastName = adminLastName;
  }

  // --------------------------------------------------------------------------
  public byte[] getAdminPassword() {
    return adminPassword;
  }

  // --------------------------------------------------------------------------
  public void setAdminPassword(byte[] adminPassword) {
    this.adminPassword = adminPassword;
  }

  // --------------------------------------------------------------------------
  public byte[] getAdminSalt() {
    return adminSalt;
  }

  // --------------------------------------------------------------------------
  public void setAdminSalt(byte[] adminSalt) {
    this.adminSalt = adminSalt;
  }

  // --------------------------------------------------------------------------
  public int getAdminNumber() {
    return adminNumber;
  }

  // --------------------------------------------------------------------------
  public void setAdminNumber(int adminNumber) {
    this.adminNumber = adminNumber;
  }
}
