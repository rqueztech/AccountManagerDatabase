package com.rqueztech.interfaces.admin;

import com.rqueztech.models.user.UserModel;

// This interface is responsible for taking the information that
// Was entered in the panel and send it to the controller
public interface AdminModelViewAddUserInterface {
	UserModel getUser();

	void setUserFirstName(String userFirstName);
	void setUserLastName(String userLastName);
	void setUserGender(String gender);
}