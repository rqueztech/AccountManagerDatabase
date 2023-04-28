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

/**
 * The UserCsvManager class handles read, write, create, and delete to csv.
 */
public class UserCsvManager {
  private final String filePath;

  /**
   * Sets the default path for the user database csv.
   */
  public UserCsvManager() {
    this.filePath = "src/main/resources/data/userdatabase.csv";
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
    if (fileExists) {
      System.out.println("The file exists");
    } else {
      System.out.println("The file does not exist");
      // Create a new file with a header row
      FileWriter writer = new FileWriter(filePath);
      CSVWriter csvWriter = new CSVWriter(writer);
      String[] header = {"acctName", "fName", "lName", "gender", "password", "salt"};
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

    csvReader.close();
    reader.close();

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
}
