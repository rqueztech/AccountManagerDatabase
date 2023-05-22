package main.com.rqueztech.swingworkers.admin;

import java.util.Iterator;
import java.util.List;

import javax.swing.SwingWorker;
import main.com.rqueztech.ui.admin.AdminUserViewPanel;
import main.com.rqueztech.FileLocations;
import main.com.rqueztech.csv.admin.AdminCsvManager;
import main.com.rqueztech.csv.admin.UserCsvManager;

public class AdminDeleteUserWorker extends SwingWorker<Void, Void> {
    
  private String currentUser;
  private UserCsvManager userCsvManager;
  private AdminUserViewPanel adminUserViewPanel;
  private String currentFilePath;
  
  public AdminDeleteUserWorker(AdminUserViewPanel adminUserViewPanel,
      String currentUser, String currentFilePath) {
    this.adminUserViewPanel = adminUserViewPanel;
    this.currentUser = currentUser;
    this.currentFilePath = currentFilePath;
  }
  
  @Override
  protected Void doInBackground() throws Exception {
    // Perform your long-running task here
    // This method runs in a background thread
    // You can update the UI using the publish() method if needed
    // You can return a result if necessary (change the Void type parameter)
        
    // Example:
      
    this.userCsvManager = new UserCsvManager(this.currentFilePath);
    
    List<String[]> currentData = this.userCsvManager.retrieveData();
    String[] accountToDelete = this.userCsvManager.retrieveAccountData(this.currentUser);
    
    this.userCsvManager.removeData(accountToDelete);
    
    currentData = this.userCsvManager.retrieveData();
    
    // Assuming currentData is a List<String[]> containing String arrays

    for (String[] dataArray : currentData) {
        for (String data : dataArray) {
            // Perform actions with each data element
            System.out.println(data);
        }
    }

    
    this.adminUserViewPanel.refreshTable();
    
    return null;
  }
    
  @Override
  protected void done() {
    // This method is called when the doInBackground() method is finished
    // You can update the UI or perform any post-processing here
  }
    
  private void deleteUser() {
    // Implement your logic to delete a user here
  }
}
