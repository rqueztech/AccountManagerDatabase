module com.rqueztech {
	exports src.com.rqueztech;
	exports src.com.rqueztech.ui;
	exports src.com.rqueztech.ui.admin;
	exports src.com.rqueztech.encryption;
	exports src.com.rqueztech.ui.enums;
	exports src.com.rqueztech.ui.events;
	exports src.com.rqueztech.ui.user;
	exports src.com.rqueztech.ui.validation;
	exports src.com.rqueztech.models.admin;
	exports src.com.rqueztech.models.user;
	exports src.com.tests;
	
	opens src.com.rqueztech.ui;
	opens src.com.rqueztech.ui.admin;
	exports src.com.rqueztech.encryption;
	opens src.com.rqueztech.ui.enums;
	opens src.com.rqueztech.ui.events;
	opens src.com.rqueztech.ui.user;
	opens src.com.rqueztech.ui.validation;
	opens src.com.rqueztech.models.admin;
	opens src.com.rqueztech.models.user;
	opens src.com.tests;
	
	requires transitive java.desktop;
	requires transitive org.junit.jupiter.api;
	requires com.opencsv;
}