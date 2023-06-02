package com.test.adminfunctions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import main.com.rqueztech.FileLocations;
import main.com.rqueztech.encryption.PasswordEncryption;
import main.com.rqueztech.models.admin.AdminModel;
import main.com.rqueztech.swingworkers.admin.AdminAddAdminWorker;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * The test takes a list of names and passwords and inputs and uses said inputs
 to create administrator accounts.
 */
public class CreateConfigurationTest {

  String[][] fakeData = {
    {"ABIGAIL", "ADAMS", "P@ssw0rd1$"},
    {"ALEXANDER", "ANDERSON", "T3st!ngP@ss"},
    {"AMANDA", "ARMSTRONG", "S3cur3#Word1"},
    {"ANDREW", "ANDREWS", "!ncredibleP@ss"},
    {"ANGELA", "BROWN", "P@ssw0rd!2023"},
    {"BENJAMIN", "BAKER", "B@nk3rP@ssw0rd"},
    {"BRIAN", "BARNES", "P@ssw0rd!1234"},
    {"CAROLINE", "CAMPBELL", "C@mpbell123!"},
    {"CHARLES", "CLARK", "Pa$$w0rd1!"},
    {"CHRISTINA", "COLEMAN", "S@f3P@ssw0rd"},
    {"CHRISTOPHER", "COOPER", "C00p3r!P@ssw0rd"},
    {"CLAIRE", "CARTER", "C@rt3r123!"},
    {"DANIEL", "DAVIS", "D@visP@ssw0rd1"},
    {"DEBORAH", "DIAZ", "D14zP@ss!"},
    {"DIANA", "EDWARDS", "3dw@rd$P@ssw0rd"},
    {"DONALD", "EVANS", "3v@nsP@$$w0rd"},
    {"EDWARD", "EDWARDS", "3dw@rd$P@$$"},
    {"ELIZABETH", "FISHER", "F!sh3rP@ss"},
    {"EMILY", "FOSTER", "F0st3rP@ss!"},
    {"ERIC", "GARCIA", "G@rc!@P@ssw0rd"},
    {"EVELYN", "GONZALEZ", "G0nz@lez!P@ss"},
    {"FRANK", "GRAY", "Gr@yP@$$w0rd"},
    {"GABRIEL", "GREEN", "Gr33nP@$$!"},
    {"GRACE", "GRIFFIN", "Gr!ff!nP@ssw0rd"},
    {"HENRY", "HALL", "H@llP@ssw0rd!"},
    {"ISABELLA", "HARRIS", "H@rr!s123"},
    {"JACKSON", "HILL", "H!llP@ssw0rd"},
    {"JAMES", "HOWARD", "H0w@rdP@ssw0rd!"},
    {"JASMINE", "HUGHES", "Hughe$P@ss1"},
    {"JASON", "JACKSON", "J@cksonP@ss!"},
    {"JENNIFER", "JOHNSON", "J0hn$onP@$$w0rd"},
    {"JOHN", "JONES", "J0n3sP@ssw0rd!"},
    {"JULIA", "KING", "K1ngP@$$w0rd"},
    {"JUSTIN", "LEE", "L33P@$$w0rd!"},
    {"KATHERINE", "LEWIS", "L3w!sP@ssw0rd"},
    {"KENNETH", "LONG", "L0ngP@$$!"},
    {"LAURA", "LOPEZ", "L0p3zP@ssw0rd!"},
    {"LEO", "MARTIN", "M@rt1nP@$$"},
    {"LILY", "MARTINEZ", "M@rt1n3z!P@ss"},
    {"LUCAS", "MILLER", "M!ll3rP@ssw0rd"},
    {"MADISON", "MITCHELL", "M!tch3llP@ss"},
    {"MATTHEW", "MOORE", "M00r3P@$$w0rd"},
    {"MEGAN", "MORRIS", "M0rr!sP@ssw0rd"},
    {"MICHAEL", "NELSON", "N3ls0nP@ss!"},
    {"NATALIE", "PARKER", "P@rk3r123!"},
    {"NATHAN", "PATTERSON", "P@tt3rs0nP@$$"},
    {"OLIVIA", "PEREZ", "P3r3zP@ssw0rd!"},
    {"PATRICK", "PERRY", "P3rryP@$$w0rd"},
    {"RACHEL", "PRICE", "Pr!c3P@ssw0rd"},
    {"REBECCA", "RAMIREZ", "R@m!r3zP@ss!"},
    {"RICHARD", "REED", "R33dP@ssw0rd!"},
    {"ROBERT", "RICHARDSON", "R!ch@rd$0nP@$$"},
    {"SAMUEL", "RIVERA", "R!v3r@P@$$w0rd"},
    {"SARAH", "ROBERTS", "R0b3rt$P@ss"},
    {"SCOTT", "ROBINSON", "R0b!n$0nP@ss"},
    {"SOPHIA", "RODRIGUEZ", "R0dr!gu3zP@ss"},
    {"STEVEN", "ROGERS", "Rog3r$P@$$w0rd"},
    {"THOMAS", "ROSS", "R0ssP@$$w0rd!"},
    {"VICTORIA", "RUSSELL", "Rus$3llP@ss"},
    {"WILLIAM", "SANCHEZ", "S@nch3zP@ss!"},
    {"ZOE", "SCOTT", "Sc0tt!P@ssw0rd"}
    // Add more data as needed
  };

  private ConcurrentHashMap<String, AdminModel> adminAccounts = 
      

  private AdminAddAdminWorker adminAddAdminWorker;

  @BeforeMethod
  public void setUp() {
    
    
    for (int counter = 0; counter < fakeData.length; counter++) {  
      this.adminAddAdminWorker =
           new AdminAddAdminWorker(
         this.fakeData[counter][0],
         this.fakeData[counter][1],
         this.fakeData[counter][2].toCharArray(),
         FileLocations.getConfigLocationTest()
       );

      this.adminAddAdminWorker.execute();

    }
  }

  @Test
  public void adminInformationValid() throws Exception {

  }

}