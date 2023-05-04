package main.com.rqueztech.controllers;


import javax.swing.JButton;
import main.com.rqueztech.controllers.LoginFailController;
import main.com.rqueztech.ui.LoginFailPanel;
import main.com.rqueztech.ui.PanelCentral;
import main.com.rqueztech.ui.enums.PanelCentralEnums;

/**
 *The LoginFailController class is responsible for managing the
 behavior of the LoginIncorrectErrorPanel. It adds an ActionListener to the
 LoginIncorrectErrorButton and sets the current panel to the LoginErrorPanel
 when the button is clicked.
 */
public class LoginFailController {
  private LoginFailPanel loginIncorrectErrorPanel;
  private PanelCentral panelCentral;

  /**
   * Creates instance of login incorrect controller class.
   *
   * @param loginIncorrectErrorPanel the object holding the components that create
   the login incorrect error panel page.
   *
   * @param panelCentral the PanelCentral object that the panel will be added to
   */
  public LoginFailController(
      LoginFailPanel loginIncorrectErrorPanel, PanelCentral panelCentral) {
    this.panelCentral = panelCentral;
    this.loginIncorrectErrorPanel = loginIncorrectErrorPanel;
    this.loginIncorrectActionListener();
  }

  /**
   * Adds an ActionListener to the LoginIncorrectButton.
   When the LoginIncorrectButton is clicked, the current panel is set to the
   LoginIncorrectErrorPanel.
   */
  // --------------------------------------------------------------------------
  public void loginIncorrectActionListener() {
    JButton configurationButton = (JButton) this.loginIncorrectErrorPanel
              .getComponentsMap().get("LoginFailureButtonKey");

    configurationButton.addActionListener(e -> {
      this.loginIncorrectErrorPanel.setVisible(false);
      this.panelCentral.getCurrentPanel()
        .get(PanelCentralEnums.MAINLOGINPANEL)
          .setVisible(true);
    });
  }
}
