package csvtests;

import static org.testng.AssertJUnit.assertTrue;
import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;
import java.io.File;
import java.io.IOException;
import java.nio.channels.AcceptPendingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import main.com.rqueztech.FileLocations;
import main.com.rqueztech.csv.admin.UserCsvManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserCsvManagerTest {
  private static final String TEST_FILE_PATH = FileLocations.getUserCsvLocationTest();
  private UserCsvManager userCsvManager;

  final String[] header = {"acctName", "fName", "lName", "gender",
      "encryptedPassword", "salt", "admNo"};

  private File existFile;

  @BeforeMethod
  public void setUp() {
    // Delete the test file after each test
    try {
      Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Create a new UserCsvManager instance with the test file path\
    this.existFile = new File(TEST_FILE_PATH);
    this.userCsvManager = new UserCsvManager(TEST_FILE_PATH);
  }

  @AfterMethod
  public void tearDown() {
    // Delete the test file after each test
    try {
      Files.deleteIfExists(Paths.get(TEST_FILE_PATH));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  public void addDataAlternating() {
  // Put together the local copy, We will have to add the header
  // Manually after passing it through the csv add data function
  // To avoid duplicates
    List<String[]> testData = new ArrayList<>();

    // This list will be the list retreived from the csv file.
    List<String[]> retreivedDataList = new ArrayList<>();

    List<String[]> newAddition = new ArrayList<>();

    String[] entryStrings = new String[] {"JohnDoe", "John", "Doe", "Male",
      "password", "salt", "123"};

    testData.add(entryStrings);

    // Add the data to the usv file
    try {
      userCsvManager.addData(testData);
      // Add the header for the local copy.
      testData.add(0, this.header);
      retreivedDataList = userCsvManager.retrieveData();

      for (int i = 0; i < testData.size(); i++) {
        assertTrue(Arrays.equals(testData.get(i), retreivedDataList.get(i)));
      }

      entryStrings = new String[] {"ParlsKunior", "John", "Doe", "Male",
            "password", "salt", "123"};

      testData.add(entryStrings);

      newAddition.add(entryStrings);

      userCsvManager.addData(newAddition);

          retreivedDataList = new ArrayList<>();
          retreivedDataList = userCsvManager.retrieveData();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

  @Test
  public void addData() throws IOException {
    // Create some test data
    List<String[]> testData = new ArrayList<>();
    testData.add(new String[]{"JohnDoe", "John", "Doe", "Male", "password", "salt", "123"});
    testData.add(new String[]{"JaneSmith", "Jane", "Smith", "Female", "password", "salt", "456"});

    // Call the addData method
    this.userCsvManager.addData(testData);

    // Add the header in the first element
    testData.add(0, header);

    //System.out.println("1 " + Arrays.deepToString(testData.toArray()));

    // Retrieve the data from the CSV file
    List<String[]> retrievedData = userCsvManager.retrieveData();

    int testDataSize = testData.size();

    // This will subtract one element, the element is the header that is retreived
    // From the csv file.
    int retreivedSize = retrievedData.size();

    // Assert that the retrieved data matches the test data
    assertEquals(testDataSize, retreivedSize);
    for (int i = 0; i < testData.size(); i++) {
      assertTrue(Arrays.equals(testData.get(i), retrievedData.get(i)));
    }
  }

  @Test
  public void removeData() throws IOException {
    // Create some test data
    List<String[]> testData = new ArrayList<>();
    testData.add(new String[]{"JohnDoe", "John", "Doe", "Male", "password", "salt", "123"});
    testData.add(new String[]{"JaneSmith", "Jane", "Smith", "Female", "password", "salt", "456"});

    // Add the test data to the CSV file
    userCsvManager.addData(testData);

    testData.add(this.header);

    // Remove one row of data
    String[] dataToRemove = testData.get(0);
    userCsvManager.removeData(dataToRemove);

    // Retrieve the updated data from the CSV file
    List<String[]> retrievedData = userCsvManager.retrieveData();

    // Assert that the retrieved data does not contain the removed row
    assertEquals(testData.size() - 1, retrievedData.size());
    for (String[] row : retrievedData) {
      assertNotEquals(dataToRemove, row);
    }
  }

  @Test
  public void retrieveData() throws IOException {
    // Create some test data
    List<String[]> testData = new ArrayList<>();

    testData.add(new String[]{"JohnDoe", "John", "Doe", "Male", "password", "salt", "123"});
    testData.add(new String[]{"JaneSmith", "Jane", "Smith", "Female", "password", "salt", "456"});

    // Add the test data to the CSV file
    userCsvManager.addData(testData);

    testData.add(0, header);

    // Retrieve the data from the CSV file
    List<String[]> retrievedData = userCsvManager.retrieveData();

    // Assert that the retrieved data matches the test data
    assertEquals(testData.size(), retrievedData.size());
    for (int i = 0; i < testData.size(); i++) {
      assertTrue(Arrays.equals(testData.get(i), retrievedData.get(i)));
    }
  }
}
