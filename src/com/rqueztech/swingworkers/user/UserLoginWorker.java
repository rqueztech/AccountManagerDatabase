package com.rqueztech.swingworkers.user;

import javax.swing.SwingWorker;

public class UserLoginWorker extends SwingWorker<Boolean, Void> {
	private String userName;
	private char[] enteredPassword;
	private byte storedUserSalt;
	
	public UserLoginWorker(String userName, char[] enteredPassword) {
		this.userName = userName;
		this.enteredPassword = enteredPassword;
	}
	
	@Override
	protected Boolean doInBackground() throws Exception {
		boolean success = get();
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void done() {
		
	}
}
