package main.com.rqueztech.csv.configuration;

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


/**
 * The ConfigurationCsvManager class handles read, write, create, and delete to csv.
 */
public class ConfigurationCsvManager {

  /**
   * Sets the default path for the configuration database csv.
   */
  private final String filePath;

  public ConfigurationCsvManager(final String filePath) {
    this.filePath = filePath;
  }

  /**
   * Appends a string of arrays to add to the end of a csv file.
   *
   * @param data the list of string arrays to add as (a list of string arrays)
   * @throws IOException throws IOException if an I/O error occurs
   */
  public void createConfigurationFile(List<String[]> data) throws IOException {
    Path path = Paths.get(this.filePath);
    boolean fileExists = Files.exists(path);

    // Create a new file with a header row
    FileWriter writer = new FileWriter(filePath);
    CSVWriter csvWriter = new CSVWriter(writer);
    String[] header = {"numAdmins", "numUsers", "admPassphrase", "admSalt"};
    csvWriter.writeNext(header);
    csvWriter.writeAll(data);
    csvWriter.close();
    writer.close();
  }
  
  public void modifyConfigurationFile(char[] password, char[] salt)
      throws IOException, CsvException {
  
    Path path = Paths.get(this.filePath);
    boolean fileExists = Files.exists(path);

    if (!fileExists) {
      return;
    }

    try (CSVReader csvReader = new CSVReader(Files.newBufferedReader(path));
         CSVWriter csvWriter = new CSVWriter(Files.newBufferedWriter(path))) {

      List<String[]> rows = csvReader.readAll();
  
      // Locate the specific row for modification (index 1 as data starts from second row)
      if (rows.size() > 1) {
        String[] dataRow = rows.get(1);
  
        // Modify the third and fourth elements (index 2 and 3) with password and salt
        dataRow[2] = new String(password);
        dataRow[3] = new String(salt);
      }

      // Write the modified data back to the CSV file
      csvWriter.writeAll(rows);
    }
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

    if (!this.isFileExists()) {
      return null;
    }
  
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

    // Return the List of rows
    return rows;
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
  
  private boolean isFileExists() {
    Path path = Paths.get(this.filePath);
    return Files.exists(path);
  }
}
