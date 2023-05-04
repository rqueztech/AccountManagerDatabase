package main.com.rqueztech.controllers.admin;

import java.util.concurrent.ConcurrentHashMap;

public class AdminCurrentLoginMap {
  private ConcurrentHashMap<String, Boolean> adminCurrentLoginMap;

  public ConcurrentHashMap<String, Boolean> getAdminCurrentLoginMap() {
    return this.adminCurrentLoginMap;
  }
}
