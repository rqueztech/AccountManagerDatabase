module com.rqueztech {
	exports com.rqueztech;
	exports com.rqueztech.ui;
	exports com.rqueztech.ui.admin;
	exports com.rqueztech.ui.user;
	exports com.rqueztech.ui.enums;
	exports com.rqueztech.ui.validation;
	exports com.rqueztech.ui.events;
	exports com.rqueztech.models.admin;
	exports com.rqueztech.models.user;
	
	opens com.rqueztech.ui;
	opens com.rqueztech.ui.admin;
	opens com.rqueztech.ui.user;
	opens com.rqueztech.ui.enums;
	opens com.rqueztech.ui.validation;
	opens com.rqueztech.ui.events;
	opens com.rqueztech.models.admin;
	opens com.rqueztech.models.user;
	
	requires transitive java.desktop;
}