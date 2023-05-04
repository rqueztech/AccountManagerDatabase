package main.com.rqueztech.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import main.com.rqueztech.controllers.LoginFailController;
import main.com.rqueztech.ui.buttons.ButtonTemplates;

/**
 * The LogoutIncorrectErrorPanel displays the logout error message and all
 respective components.
 *
 * @extends JPanel extends the JPanel class used to modify the current panel.
 */
public class LoginFailPanel extends JPanel {

  // --- Group 1: Panel related variables ---
  private static final long serialVersionUID = 1151818027338195157L;
  private Image image;
  private GridBagConstraints grid;

  private final int topInset = 0;
  private final int leftInset = 0;
  private final int bottomInset = 0;
  private final int rightInset = 0;

  // --- Section 3: Login Button Component Keys
  private final String loginFailureButtonKey = "LoginFailureButtonKey"; 
  private PanelCentral panelCentral;
  private LoginFailController loginIncorrectErrorController;

  private final int gridxInitial = 0;
  private final int gridyInitial = 0;

  private final int gridxImageWeight = 1;
  private final int gridyImageWeight = 1;

  // --- Group 2: Panel Map ---
  private ConcurrentHashMap<String, JComponent> components;

  /**
   * The LoginIncorrectErrorPanel displays the error panel with the message
   Explaining why the user login is incorrect.
   *
   * @params baseFrame passes the object of the basebaseFrame as (a BaseFrame)
   * @params layout passes the layout to be used in the panel as (a GridBagLayout)
   * @params panelCentral passes the central panel as (a JPanel)
   */
  // --------------------------------------------------------------------------
  public LoginFailPanel(BaseFrame baseFrame, GridBagLayout layout,
      PanelCentral panelCentral) {
    // Function that will toggle visibility on and off in password
    // Field found in this class
    this.panelCentral = panelCentral;

    // Dispatch responsibilities on EDT.
    SwingUtilities.invokeLater(() -> {

      // Set the panel to the gridbaglayout, establish the preferred size,
      // And get the image that will be used in the background
      this.setLayout(layout);
      this.setPreferredSize(new Dimension(baseFrame.getHeight(), baseFrame.getWidth()));
      //this.image = new ImageIcon(getClass().getResource("/images/backgroundd.jpg")).getImage();
      this.image = new ImageIcon(getClass().getResource("/images/backgroundd.jpg")).getImage();
      this.components = new ConcurrentHashMap<String, JComponent>();

      // --- Start Constraints ---
      // Set all of the constraints for the background image
      this.setBackgroundImageConstraints();
      baseFrame.add(this, this.grid);
      //--- Finish Constraints End ---

      this.setComponentMainPosition();


      this.grid.gridx = 0; // Buttons are not fixed, therefore coordinates are custom set
      this.setButton(loginFailureButtonKey, "Main Menu");
      this.add(this.components.get(loginFailureButtonKey), grid);

      this.loginIncorrectErrorController = new LoginFailController(this, this.panelCentral);
    });
  }

  // --------------------------------------------------------------------------
  private void setComponentMainPosition() {
    this.grid.insets = new Insets(2, 2, 2, 2);
    this.grid.gridx = gridxInitial;
    this.grid.gridy = gridyInitial;
  }

  // --------------------------------------------------------------------------
  private void setBackgroundImageConstraints() {
    // Set everything to initial status.
    this.grid = new GridBagConstraints(); // Set the gridbag constraints
    this.grid.fill = GridBagConstraints.BOTH; // Fill both vertically and horizontally
    this.grid.gridx = gridxInitial;
    this.grid.gridx = gridyInitial;
    this.grid.weightx = gridxImageWeight; // Value is 0: initial
    this.grid.weighty = gridyImageWeight; // Value is 0: initial

    // Insets values all 0 (Initial)
    this.grid.insets = new Insets(topInset, leftInset, bottomInset, rightInset);
  }

  // --------------------------------------------------------------------------
  private void setButton(String buttonKey, String buttonText) {
    final ButtonTemplates button = new ButtonTemplates(
        buttonText, Color.BLACK, Color.WHITE);

    this.grid.anchor = GridBagConstraints.CENTER;
    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(buttonKey, button);
  }

  @Override
  public void paintComponent(Graphics g) {

    Graphics2D g2d = (Graphics2D)  g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints
        .VALUE_ANTIALIAS_ON);

    g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
  }

  public ConcurrentHashMap<String, JComponent> getComponentsMap() {
    return this.components;
  }
}
