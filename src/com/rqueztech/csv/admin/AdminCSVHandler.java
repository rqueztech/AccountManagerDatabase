package com.rqueztech.csv.admin;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

public class AdminCSVHandler {
	/*
	private String csvHeader;
	private String path;
	
	public AdminCSVHandler() {
		this.csvHeader = "adminName,firstName,lastName,hashedPassword,passwordSalt,adminNumber";
		this.path = "adminDatabase.csv";
	}
    
	public void writeToCSV() {
		// Write to CSV
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
	        writer.write(csvHeader);
	        writer.newLine();
	        writer.write(Base64.getEncoder().encodeToString(originalSalt));
	        writer.write(",");
	        writer.write(Base64.getEncoder().encodeToString(hashedPassword));
	        writer.newLine();
	    } catch (IOException e) {
	        System.err.println("Error writing to CSV file: " + e.getMessage());
	    }
	}
	
    public void readFromCSV() {
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
    }
    */
}
