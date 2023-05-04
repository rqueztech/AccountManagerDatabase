package main.com.rqueztech.models.admin;

import java.util.HashMap;

/**
 * Holds admin account data used by the program in a hash map.
 */
public class AdminHashMap {
  private HashMap<String, AdminModel> adminHashMap;

  public HashMap<String, AdminModel> getAdminHashMap() {
    return this.adminHashMap;
  }
}
