package main.com.rqueztech.swingworkers.admin;

import main.com.rqueztech.models.user.UserModel;

/**
 * The class creates an instance of the AdminAddUserWorker for testing to
 access the protected doInBackground() class.
 */
public class AdminAddUserWorkerTesting extends AdminAddUserWorker {
/*
  private String userAccountName;
  private String userFirstName;
  private String userLastName;

  private char[] newUserPassword;
  private byte[] encryptedUserPassword;

  private String genderString;
  private int adminNumber;
*/

  public AdminAddUserWorkerTesting(String userFirstName, String userLastName,
      String gender, final String fileLocation, final String configLocation) {
    super(userFirstName, userLastName, gender, fileLocation, configLocation);
    // TODO Auto-generated constructor stub
  }

  /**
   * Creates an instance of the user model class.
   *
   * @return null value
   */
  public UserModel testDoInBackground() {
    try {
      UserModel userModel = doInBackground();
      return userModel;
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    return null;
  }
}
