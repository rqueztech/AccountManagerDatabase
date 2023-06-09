package com.test.panels.admin;

import java.awt.GridBagLayout;
import javax.swing.JPanel;
import main.com.rqueztech.ui.BaseFrame;
import main.com.rqueztech.ui.PanelCentral;
import main.com.rqueztech.ui.configuration.SetupConfigurationPanel;
import main.com.rqueztech.ui.enums.PanelCentralEnums;
import org.assertj.swing.edt.FailOnThreadViolationRepaintManager;
import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.edt.GuiQuery;
import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.timing.Pause;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SetupConfigurationPanelTest {

  private FrameFixture window;
  private BaseFrame frame = new BaseFrame();
  private PanelCentral panelCentral = new PanelCentral(frame);

  @BeforeClass
  private static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @BeforeMethod
  public void setUp() {
    SetupConfigurationPanel setupConfigurationPanel =
        GuiActionRunner.execute(() -> new SetupConfigurationPanel(
          frame, new GridBagLayout(), this.panelCentral));

    this.panelCentral.clearPanels();

    window = new FrameFixture(frame);

    window.show();
  }

  @Test
  public void isValidEntry() {
    System.out.println("Print this out");
  }

  @AfterClass
  public void tearDown() {
    Pause.pause(50000);
    window.cleanUp();
  }
}
