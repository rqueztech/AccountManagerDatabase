module com.rqueztech {
	exports com.rqueztech;
	exports com.rqueztech.ui;
	exports com.rqueztech.ui.admin;
	exports com.rqueztech.ui.user;
	exports com.rqueztech.ui.enums;
	
	opens com.rqueztech.ui;
	opens com.rqueztech.ui.admin;
	opens com.rqueztech.ui.user;
	opens com.rqueztech.ui.enums;
	
	requires transitive java.desktop;
}