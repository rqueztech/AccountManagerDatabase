package main.com.rqueztech;

/**
 * The purpose of this class is to store file locations as final strings that
 can be used for writing to csv files throughout the program. This allows for
 certain locations to be written to depending on test or operations.
 */
public class FileLocations {
  // Store files in the main location
  private final String configLocationMain = "src/main/resources/data/configurationdatabase.csv";
  private final String adminDbLocationMain = "src/main/resources/data/admindatabase.csv";
  private final String userDbLocationMain = "src/main/resources/data/userdatabase.csv";

  private final String configLocationTest = "src/test/resources/data/configurationdatabase.csv";
  private final String adminDbLocationTest = "src/test/resources/data/admindatabase.csv";
  private final String userDbLocationTest = "src/test/resources/data/userdatabase.csv";  

  public String getConfigLocationMain() {
    return this.configLocationMain;
  }

  public String getAdminDbLocationMain() {
    return this.adminDbLocationMain;
  }

  public String getUserDbLocationMain() {
    return this.userDbLocationMain;
  }

  public String getConfigLocationTest() {
    return this.configLocationTest;
  }

  public String getAdminDbLocationTest() {
    return this.adminDbLocationTest;
  }

  public String getUserDbLocationTest() {
    return this.userDbLocationTest;
  }
}
