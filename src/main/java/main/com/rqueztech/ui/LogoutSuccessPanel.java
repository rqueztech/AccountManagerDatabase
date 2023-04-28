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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import main.com.rqueztech.ui.buttons.ButtonTemplates;
import main.com.rqueztech.ui.enums.PanelCentralEnums;

/**
 * The LogoutSuccessPanel displays the logout success message and all
 respective components.
 *
 * @extends JPanel extends the JPanel class used to modify the current panel.
 */
public class LogoutSuccessPanel extends JPanel {

  // --- Group 1: Panel related variables ---
  private static final long serialVersionUID = 1151818027338195157L;
  private Image image;
  private GridBagConstraints grid;

  private final int topInset = 0;
  private final int leftInset = 0;
  private final int bottomInset = 0;
  private final int rightInset = 0;

  // --- Section 3: Login Button Component Keys
  private final String logoutSuccessButtonKey = "LogoutSuccessButtonKey"; 
  private PanelCentral panelCentral;

  private final int gridxInitial = 0;
  private final int gridyInitial = 0;

  private final int gridxImageWeight = 1;
  private final int gridyImageWeight = 1;

  // --- Group 2: Panel Map ---
  private ConcurrentHashMap<String, JComponent> components;

  /**
   * The BaseFrame class sets the JFrame used throughout the project including
   * the default size and properties.
   *
   * @params baseFrame passes the object of the basebaseFrame as (a BaseFrame)
   * @params layout passes the layout to be used in the panel as (a GridBagLayout)
   * @params panelCentral passes the central panel as (a JPanel)
   */
  // --------------------------------------------------------------------------
  public LogoutSuccessPanel(BaseFrame baseFrame, GridBagLayout layout, PanelCentral panelCentral) {
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
      this.setButton(logoutSuccessButtonKey, "Logout Successful");
      this.add(this.components.get(logoutSuccessButtonKey), grid);

      this.logoutActionListener();
    });
  }

  // --------------------------------------------------------------------------
  private void logoutActionListener() {
    JButton logoutButton = (JButton) this.components
        .get(logoutSuccessButtonKey);

    logoutButton.addActionListener(e -> {
      this.setVisible(false);
      this.panelCentral.getCurrentPanel().get(PanelCentralEnums.MAINLOGINPANEL)
        .setVisible(true);
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
}
