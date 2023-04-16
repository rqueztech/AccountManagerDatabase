package com.tests;

import org.junit.jupiter.api.Test;

import com.rqueztech.ui.BaseFrame;
import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.enums.PanelCentralEnums;

class ConfigurationPanelTest {
	private BaseFrame baseFrame = new BaseFrame();
	private PanelCentral panelCentral = new PanelCentral(baseFrame);
	
	@Test
	void test() {
		panelCentral.getCurrentPanel().get(PanelCentralEnums.SETUP_CONFIGURATION_PANEL).setVisible(true);;
	}

}
