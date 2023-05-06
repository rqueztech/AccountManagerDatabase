package com.test.adminfunctions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.models.admin.AdminModel;
import main.com.rqueztech.swingworkers.admin.AdminAddAdminWorkerTesting;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The test creates a list of 40 users and passwords, and checks to make sure 
 the users are being created properly. This tests that everything is properly
 being added into the model, the passwords are correct, and the salting
 happening properly.
 */
public class CreateAdminTest {
  private List<AdminModel> currentAdmin;

  // Create variables that will avoid magic numbers
  private final int firstNameColumn = 0;
  private final int lastNameColumn = 1;
  private final int passwordColumn = 2;
  
  private String[][] userData = {
    {"Alina", "Walls", "Wj1*Cn5#"},
    {"Rufus", "Kennedy", "Ns5!Uw4&"},
    {"Rueben", "Lindsay", "Li6&Xx1&"},
    {"Leuan", "Page", "Kn3?Hn9@"},
    {"Michelle", "Nolan", "Kg6?Wy9#"},
    {"Habiba", "Barlow", "Tc8$Av3&"},
    {"Agnes", "Quinn", "Nh0&Al2#"},
    {"Maizie", "Goodman", "Xg2$Ls1%"},
    {"Hannah", "Foley", "Or3&Xb5@"},
    {"Maisey", "Gamble", "Dg4!Uc4@"},
    {"Hashim", "Campbell", "Nq0%Cz2@"},
    {"Nina", "Richardson", "Cq6*Xk7%"},
    {"Nicola", "Walton", "Jt7@Ct9#"},
    {"Carol", "Bates", "Ka6&Bb3#"},
    {"Marina", "Glenn", "Pr3!Fm1%"},
    {"Kathleen", "Morrow", "Tp5*Pv7$"},
    {"Alia", "Dotson", "Dl8!Pp9*"},
    {"Elouise", "Cook", "Nu4$Od8#"},
    {"Rehan", "Perkins", "Jk5$Bv6#"},
    {"Velma", "Hardin", "Lj7#Qq4%"},
    {"Brooklyn", "Rodgers", "Bl3$Ux6#"},
    {"Lacey", "Pierce", "Py7&As6?"},
    {"Edward", "Pineda", "Hn4@Lp6%"},
    {"Omari", "Carroll", "Lp7$Li8%"},
    {"Rabia", "Hawkins", "Ha1*Wz2!"},
    {"Gilbert", "Hendrix", "Jv4$Go3@"},
    {"Holly", "Bennett", "If3$Fi1$"},
    {"Amie", "Curtis", "Wa7!Ew9@"},
    {"Josh", "Frazier", "Jh6#Qo4!"},
    {"Leroy", "Donnelly", "Nk7&Km6@"},
    {"Fatma", "Cuevas", "Ek0&Lk7!"},
    {"Keiran", "Ross", "Gx1*Cw8$"},
    {"Timothy", "George", "Az2*Wu8%"},
    {"Musa", "Olson", "Rt6@Uf9*"},
    {"Amin", "Heath", "Jz1*Sx9$"},
    {"Ethan", "Tran", "Hr3#Do0*"},
    {"Cindy", "Chapman", "Ln2@Ss4&"},
    {"Keyaan", "Dorsey", "Ce4&Bd0?"},
    {"Aimee", "Dawson", "Rx0%Cq1!"},
    {"Austin", "Castillo", "Os5*Ep6?"}
  };


  @BeforeMethod
  public void setUp() {
    this.currentAdmin = new ArrayList<AdminModel>();

    for (String[] arrayIterator : this.userData) {
      AdminAddAdminWorkerTesting adminAddAdminWorkerTesting =
          new AdminAddAdminWorkerTesting(
          arrayIterator[0],
          arrayIterator[1],
          arrayIterator[2].toCharArray()
        );
  
      adminAddAdminWorkerTesting.execute();
      
      try {
        AdminModel currentAdmin = adminAddAdminWorkerTesting.get();
        this.currentAdmin.add(currentAdmin);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      } catch (ExecutionException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
  }
  
  @Test
  public void nullValueFoundTest() throws Exception {  
    Iterator<AdminModel> currentAdminIterator = currentAdmin.iterator();
    while (currentAdminIterator.hasNext()) {
      AdminModel currentAdmin = currentAdminIterator.next();
      Assert.assertNotNull(currentAdmin.getAdminName());
      Assert.assertNotNull(currentAdmin.getAdminFirstName());
      Assert.assertNotNull(currentAdmin.getAdminLastName());
      Assert.assertNotNull(currentAdmin.getAdminPassword());
      Assert.assertNotNull(currentAdmin.getAdminSalt());
      Assert.assertNotNull(currentAdmin.getAdminNumber());
    }
  }
  
  @Test
  public void firstNameValid() throws Exception {  
    Iterator<AdminModel> currentAdminIterator = currentAdmin.iterator();
    int rowCurrent = 0;
    
    while (currentAdminIterator.hasNext()) {
      AdminModel currentAdmin = currentAdminIterator.next();
      
      Assert.assertEquals(currentAdmin.getAdminFirstName(),
          this.userData[rowCurrent][firstNameColumn]);
      
      rowCurrent++;
    }
  }
  
  @Test
  public void lastNameValid() throws Exception {  
    Iterator<AdminModel> currentAdminIterator = currentAdmin.iterator();
    int rowCurrent = 0;
    
    while (currentAdminIterator.hasNext()) {
      AdminModel currentAdmin = currentAdminIterator.next();
      
      Assert.assertEquals(currentAdmin.getAdminLastName(),
          this.userData[rowCurrent][lastNameColumn]);
      
      rowCurrent++;
    }
  }
  
  @Test
  public void saltValid() throws Exception {  
    Iterator<AdminModel> currentAdminIterator = currentAdmin.iterator();
    int rowCurrent = 0;
    
    while (currentAdminIterator.hasNext()) {
      AdminModel currentAdmin = currentAdminIterator.next();
      
      char[] currentPasswordAttempted = 
    	        this.userData[rowCurrent][passwordColumn]
    	        .toCharArray();
      
      Assert.assertNotNull(currentAdmin.getAdminSalt());
      Assert.assertTrue(currentAdmin.getAdminSalt().length == 16);
      
      
      assertTrue(PasswordEncryption.validateEnteredPassword(
              currentPasswordAttempted,
              this.currentAdmin.get(rowCurrent).getAdminSalt(),
              this.currentAdmin.get(rowCurrent).getAdminPassword()
          ));
      
      rowCurrent++;
    }
  }
  
  @Test
  public void passwordValid() throws Exception {  
    Iterator<AdminModel> currentAdminIterator = currentAdmin.iterator();
    int rowCurrent = 0;
    
    // Iterate through the current 2D Admin array
    while (currentAdminIterator.hasNext()) {
      char[] currentPasswordAttempted = 
        this.userData[rowCurrent][passwordColumn]
        .toCharArray();
      
      assertTrue(PasswordEncryption.validateEnteredPassword(
          currentPasswordAttempted,
          this.currentAdmin.get(rowCurrent).getAdminSalt(),
          this.currentAdmin.get(rowCurrent).getAdminPassword()
      ));
      
      rowCurrent++;
    }
  }
}
