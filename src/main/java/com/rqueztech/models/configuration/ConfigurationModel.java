package com.rqueztech.models.configuration;

public class ConfigurationModel {
	private int userNumber;
	
	public ConfigurationModel(int userNumber, int adminNumber, String password, String salt) {
		this.userNumber = userNumber;
		this.adminNumber = adminNumber;
		this.password = password;
		this.salt = salt;
	}
	public int getUserNumber() {
		return userNumber;
	}
	public void setUserNumber(int userNumber) {
		this.userNumber = userNumber;
	}
	public int getAdminNumber() {
		return adminNumber;
	}
	public void setAdminNumber(int adminNumber) {
		this.adminNumber = adminNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	private int adminNumber;
	private String password;
	private String salt;
}
