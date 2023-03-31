package com.rqueztech.csv.user;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Base64;

import com.rqueztech.encryption.PasswordEncryption;

public class UserCSVReader {
	public void readFromCSV() {
		String path = "userdatabase.csv";
		
		/*
    	// Read from CSV
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            
            // Second hash/byte comparison
    		char[] readPeople;
    		byte[] storedSalt;
    		byte[] storedPassword;
            
    		int count = 0;
    		
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                storedSalt = Base64.getDecoder().decode(data[0]);
                storedPassword = Base64.getDecoder().decode(data[1]);
                
                readPeople = "Peoplearetheones".toCharArray();
                
                if(count > 0) {
                	System.out.println(data[0] + " : " + data[1] + "\n" + PasswordEncryption.validateEnteredPassword(readPeople, storedSalt, storedPassword));
                }
                	
                count++;
            }
        } catch (IOException e) {
            System.err.println("Error reading from CSV file: " + e.getMessage());
        }
        */
    }
}
