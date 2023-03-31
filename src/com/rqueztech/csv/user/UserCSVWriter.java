package com.rqueztech.csv.user;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

import com.opencsv.CSVWriter;

public class UserCSVWriter {	
	private final String userCsvHeader = "userName,firstName,lastName,hashedPassword,passwordSalt,userNumber";
	private final String filePath = "userdatabase.csv";
	
	public void writeToFile(String userName, String firstName, String lastName, 
			byte[] hashedPassword, byte[] passwordSalt, int userNumber) throws IOException {
		// Create a file writer to create an initial file
		FileWriter userdatabase = new FileWriter(filePath);
		
		// Create a CSVWriter object. Uses all of the default values which are default
		// From the csv writer class.
		CSVWriter csvWriter = new CSVWriter(userdatabase, ',', CSVWriter.DEFAULT_QUOTE_CHARACTER,
	            CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
		
		// Write to the next header row
		csvWriter.writeNext(userCsvHeader.split(","));
		
		String hashedPasswordString = Base64.getEncoder().encodeToString(hashedPassword);
		String passwordSaltString = Base64.getEncoder().encodeToString(passwordSalt);
		String userNumberString = Integer.toString(userNumber);
	}
}
