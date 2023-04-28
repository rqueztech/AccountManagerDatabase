package main.com.rqueztech.swingworkers.admin;

import main.com.rqueztech.models.user.UserModel;

public class AdminAddUserWorkerTesting
    extends AdminAddUserWorker {

  public AdminAddUserWorkerTesting(String userFirstName, String userLastName,
      String gender) {
    super(userFirstName, userLastName, gender);
    // TODO Auto-generated constructor stub
  }

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
