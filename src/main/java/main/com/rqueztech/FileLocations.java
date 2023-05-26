package main.com.rqueztech;

/**
 * The purpose of class is to store file locations as final strings that
 can be used for writing to csv files throughout the program. This allows for
 certain locations to be written to depending on test or operations.
 */
public class FileLocations {
  // Store files in the main location
  private static final String configLocationMain = "src/main/resources/data/configurationdatabase.csv";
  private static final String adminDbLocationMain = "src/main/resources/data/admindatabase.csv";
  private static final String userDbLocationMain = "src/main/resources/data/userdatabase.csv";

  private static final String configLocationTest = "src/test/resources/data/configurationdatabase.csv";
  private static final String adminDbLocationTest = "src/test/resources/data/admindatabase.csv";
  private static final String userDbLocationTest = "src/test/resources/data/userdatabase.csv";

  private static final String userCsvLocationTest = "src/test/resources/csvdata/userdatabase.csv";

  public static String getConfigLocationMain() {
    return configLocationMain;
  }

  public static String getAdminDbLocationMain() {
    return adminDbLocationMain;
  }

  public static String getUserDbLocationMain() {
    return userDbLocationMain;
  }

  public static String getConfigLocationTest() {
    return configLocationTest;
  }

  public static String getAdminDbLocationTest() {
    return adminDbLocationTest;
  }

  public static String getUserDbLocationTest() {
    return userDbLocationTest;
  }

  public static String getUserCsvLocationTest() {
    return userCsvLocationTest;
  }
}
