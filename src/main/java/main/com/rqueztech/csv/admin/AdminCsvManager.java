package main.com.rqueztech.csv.admin;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;


/**
 * The AdminCsvManager class handles read, write, create, and delete to csv.
 */
public class AdminCsvManager {

  /**
   * Sets the default path for the admin database csv.
   */
  private final String filePath;

  public AdminCsvManager(final String filePath) {
    this.filePath = filePath;
  }

  /**
   * Appends a string of arrays to add to the end of a csv file.
   *
   * @param data the list of string arrays to add as (a list of string arrays)
   * @throws IOException throws IOException if an I/O error occurs
   */
  public void addData(List<String[]> data) throws IOException {
    Path path = Paths.get(this.filePath);
    boolean fileExists = Files.exists(path);

    if (!fileExists) {
      JOptionPane.showMessageDialog(null, "This file does not exist");

      // Create a new file with a header row
      FileWriter writer = new FileWriter(filePath);
      CSVWriter csvWriter = new CSVWriter(writer);
      String[] header = {"acctName", "fName", "lName", "encryptedPassword", "salt", "admNo"};
      csvWriter.writeNext(header);
      csvWriter.close();
      writer.close();
    }

    // Append data to the file
    FileWriter writer = new FileWriter(filePath, true);
    CSVWriter csvWriter = new CSVWriter(writer);
    csvWriter.writeAll(data);
    csvWriter.close();
    writer.close();
  }

  /**
   * Removes the data from a csv file.
   *
   * @param dataToRemove contains the information of what to remove as (a String array)
   */
  public void removeData(String[] dataToRemove) throws IOException {
    FileReader reader = new FileReader(filePath);
    CSVReader csvReader = new CSVReaderBuilder(reader).build();

    List<String[]> rows = null;

    try {
      rows = csvReader.readAll();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CsvException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    List<String[]> updatedRows = new ArrayList<>();

    for (String[] row : rows) {
      if (!isEqual(row, dataToRemove)) {
        updatedRows.add(row);
      }
    }

    csvReader.close();
    reader.close();

    FileWriter writer = new FileWriter(filePath);
    CSVWriter csvWriter = new CSVWriter(writer);

    csvWriter.writeAll(updatedRows);

    csvWriter.close();
    writer.close();
  }


  /**
   * Retrieves data from a CSV file located at the given file path.
   *
   * @return a List of String arrays, where each array represents a row in the CSV file.
   * @throws IOException if an I/O error occurs while reading the CSV file.
   */
  public List<String[]> retrieveData() throws IOException {
    // Create a FileReader object to read the CSV file
    FileReader reader = new FileReader(filePath);

    // Create a CSVReader object using the FileReader object
    CSVReader csvReader = new CSVReaderBuilder(reader).build();

    List<String[]> rows = null;
    try {
      // Read all the rows from the CSV file and store them in a List
      rows = csvReader.readAll();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CsvException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    if (rows.get(0)[0].equals("acctName")) {
      rows.remove(0);
    }
    
    // Close the CSVReader and FileReader objects
    csvReader.close();
    reader.close();

    // Return the List of rows
    return rows;
  }

  /**
   * Retrieves the row from a CSV file located at the given file path where the value of the
   * first column matches the given `acctName`.
   *
   * @param acctName the account name to match against the first column of the CSV file
   * @return a String array representing the first matching row in the CSV file, or null if no
   *         matching row is found
   * @throws IOException if an I/O error occurs while reading the CSV file.
   */
  public String[] retrieveAccountData(String acctName) throws IOException {
    // Create a FileReader object to read the CSV file
    FileReader reader = new FileReader(filePath);

    // Create a CSVReader object using the FileReader object
    CSVReader csvReader = new CSVReaderBuilder(reader).build();

    List<String[]> rows = null;
    try {
      // Read all the rows from the CSV file and store them in a List
      rows = csvReader.readAll();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (CsvException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    // Close the CSVReader and FileReader objects
    csvReader.close();
    reader.close();

    // Search for the first row where the value of the first column matches the given `acctName`
    for (String[] row : rows) {
      if (row[0].equals(acctName)) {
        return row;
      }
    }

    // If no matching row is found, return null
    return null;
  }

  private boolean isEqual(String[] arr1, String[] arr2) {
    if (arr1.length != arr2.length) {
      return false;
    }

    for (int i = 0; i < arr1.length; i++) {
      if (!arr1[i].equals(arr2[i])) {
        return false;
      }
    }

    return true;
  }
}