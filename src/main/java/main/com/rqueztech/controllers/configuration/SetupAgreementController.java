package main.com.rqueztech.controllers.configuration;

import javax.swing.JButton;
import main.com.rqueztech.ui.configuration.SetupAgreementPanel;
import main.com.rqueztech.ui.enums.PanelCentralEnums;

/**
 *The SetupAgreementController class is responsible for managing the behavior
 of the SetupAgreementPanel. It adds an ActionListener to the
 SetupAgreementButton and sets the current panel to the SetupConfigurationPanel
 when the button is clicked.
 */
public class SetupAgreementController {
  private SetupAgreementPanel setupAgreementPanel;

  public SetupAgreementController(SetupAgreementPanel setupAgreementPanel) {
    this.setupAgreementPanel = setupAgreementPanel;
    this.setupAgreementActionListener();
  }

  /**
   * Adds an ActionListener to the SetupAgreementButton.
   * When the SetupAgreementButton is clicked, the current panel is set to the
   * SetupConfigurationPanel.
   */
  // --------------------------------------------------------------------------
  public void setupAgreementActionListener() {
    JButton configurationButton = (JButton) this.setupAgreementPanel
              .getComponentsMap().get("SetupAgreementButtonKey");

    configurationButton.addActionListener(e -> {
      this.setupAgreementPanel.setVisible(false);
      this.setupAgreementPanel.getPanelCentral().getCurrentPanel()
              .get(PanelCentralEnums.SETUPCONFIGURATIONPANEL).setVisible(true);
    });
  }
}
