package main.com.rqueztech.models.configuration;

/**
 * The ConfigurationModel sets the model used in the configuration file.
 */
public class ConfigurationModel {
  private int numberOfUsers;
  private int numberOfAdmins;
  private byte[] passphrase;
  private byte[] salt;

  /**
   * The ConfigurationModel sets the model used in the configuration file.
   *
   * @param numberOfUsers holds the number of users in the current csv as (a int)
   * @param numberOfAdmins holds the number of admins in the current csv as (a int)
   * @param passphrase holds the administrator passphrase as (a byte array)
   * @param salt holds the administrator passphrase salt as (a byte array)
   */
  public ConfigurationModel(int numberOfUsers, int numberOfAdmins, byte[] passphrase, byte[] salt) {
    this.numberOfUsers = numberOfUsers;
    this.numberOfAdmins = numberOfAdmins;
    this.passphrase = passphrase;
    this.salt = salt;
  }

  public int getUserNumber() {
    return numberOfUsers;
  }

  public void setUserNumber(int numberOfUsers) {
    this.numberOfUsers = numberOfUsers;
  }

  public int getAdminNumber() {
    return numberOfAdmins;
  }

  public void setAdminNumber(int numberOfAdmins) {
    this.numberOfAdmins = numberOfAdmins;
  }

  public byte[] getPassword() {
    return passphrase;
  }

  public void setPassword(byte[] passphrase) {
    this.passphrase = passphrase;
  }

  public byte[] getSalt() {
    return salt;
  }

  public void setSalt(byte[] salt) {
    this.salt = salt;
  }
}
