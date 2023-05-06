package com.test.adminfunctions;

import org.testng.annotations.BeforeMethod;

import main.com.rqueztech.swingworkers.admin.AdminPassphraseConfirmWorker;

public class ConfirmAdminPassphrase {
  @BeforeMethod
  public void setUp() {
	  AdminPassphraseConfirmWorker adminPassphraseConfirmWorker =
			  new AdminPassphraseConfirmWorker(null);
  }
}
