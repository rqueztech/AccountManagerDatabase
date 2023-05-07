package main.com.rqueztech.ui.user;

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
import main.com.rqueztech.controllers.user.UserCentralController;
import main.com.rqueztech.models.loggedinadmin.LoggedInAdmin;
import main.com.rqueztech.models.loggedinuser.LoggedInUser;
import main.com.rqueztech.ui.BaseFrame;
import main.com.rqueztech.ui.PanelCentral;
import main.com.rqueztech.ui.buttons.ButtonTemplates;
import main.com.rqueztech.ui.user.enums.UserCentralPanelEnums;

/**
 * The UserCentralPanel class is responsible for setting the background image,
 dimensions, and all of the components necessary to navigate the user
 interface.
 *
 * @extends JPanel
 */
public class UserCentralPanel extends JPanel {

  // --- Group 1: Panel related variables ---
  private static final long serialVersionUID = 1151818027338195157L;
  private Image image;
  private GridBagConstraints grid;

  public LoggedInUser loggedInUser;

  private final int topInset = 0;
  private final int leftInset = 0;
  private final int bottomInset = 0;
  private final int rightInset = 0;

  private final int gridxInitial = 0;
  private final int gridyInitial = 0;

  private final int gridxImageWeight = 1;
  private final int gridyImageWeight = 1;


  private UserCentralController userCentralController;

  // --- Group 2: Panel Map ---
  private ConcurrentHashMap<UserCentralPanelEnums, JComponent> components;
  private PanelCentral panelCentral;

  /**
   * Default constructor that takes parameters and initializes variables.
   *
   * @param frame the BaseFrame object that the panel will be added to
   * @param layout the GridBagLayout object used to set the layout of the panel
   * @param panelCentral the PanelCentral object that the panel will be added to
   */
  public UserCentralPanel(BaseFrame frame, GridBagLayout layout, PanelCentral panelCentral) {
    // Create an instance to the login user panel
    this.loggedInUser = new LoggedInUser();

    // Function that will toggle visibility on and off in password
    // Field found in this class
    this.panelCentral = panelCentral;

    // Dispatch responsibilities on EDT.
    SwingUtilities.invokeLater(() -> {

      // Set the panel to the gridbaglayout, establish the preferred size,
      // And get the image that will be used in the background
      this.setLayout(layout);
      this.setPreferredSize(new Dimension(frame.getHeight(), frame.getWidth()));
      this.image = new ImageIcon(getClass().getResource("/images/backgroundd.jpg")).getImage();
      this.components = new ConcurrentHashMap<UserCentralPanelEnums, JComponent>();

      // --- Start Constraints ---
      // Set all of the constraints for the background image
      this.setBackgroundImageConstraints();
      frame.add(this, this.grid);
      //--- Finish Constraints End ---


      this.setComponentMainPosition();

      this.grid.gridx = 0;
      this.grid.gridy = 0;
      this.setButton(UserCentralPanelEnums
              .USERLOGOUTBUTTONKEY, "Logout");

      this.add(this.components.get(UserCentralPanelEnums.USERLOGOUTBUTTONKEY), grid);

      this.grid.gridx += 1;
      this.setButton(UserCentralPanelEnums.ADMINLOGINBUTTONKEY, "Under Construction");
      this.add(this.components.get(UserCentralPanelEnums.ADMINLOGINBUTTONKEY), grid);

      this.userCentralController = new UserCentralController(this);
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
  private void setButton(UserCentralPanelEnums buttonKey, String buttonText) {
    final ButtonTemplates button = new ButtonTemplates(
        buttonText, Color.BLACK, Color.WHITE);

    this.grid.anchor = GridBagConstraints.CENTER;

    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(buttonKey, button);
  }

  // --------------------------------------------------------------------------
  public ConcurrentHashMap<UserCentralPanelEnums, JComponent> getComponentsMap() {
    return this.components;
  }

  // --------------------------------------------------------------------------
  public PanelCentral getPanelCentral() {
    return this.panelCentral;
  }

  // --------------------------------------------------------------------------
  @Override
  public void paintComponent(Graphics g) {

    Graphics2D g2d = (Graphics2D)  g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
  }

  //--------------------------------------------------------------------------
  public LoggedInUser getLoggedInUser() {
    return this.loggedInUser;
  }
}
