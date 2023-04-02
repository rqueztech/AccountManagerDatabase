package com.rqueztech.interfaces.admin;

import com.rqueztech.models.user.UserModel;

// This interface will send the information from the 
// Swing worker and send it to the model
public interface AdminAddUserModelControlInterface {
	UserModel getUser();
	
	void setUserAccountNameName(String userAccountName);
    void setUserFirstName(String userFirstName);
    void setUserLastName(String userLastName);
    void setUserPassword(char[] userPassword);
    void setUserSalt(byte userSalt);
    void setUserNumber(int userNumber);
}
