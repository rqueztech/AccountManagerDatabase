package main.com.rqueztech.ui.admin;

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
import main.com.rqueztech.controllers.admin.AdminCentralController;
import main.com.rqueztech.models.loggedinadmin.LoggedInAdmin;
import main.com.rqueztech.ui.BaseFrame;
import main.com.rqueztech.ui.PanelCentral;
import main.com.rqueztech.ui.admin.enums.AdminCentralPanelEnums;
import main.com.rqueztech.ui.buttons.ButtonTemplates;

/**
 * The class contains all of the panel components and keys necessary for the
 * admin central panel.
 */
public class AdminCentralPanel extends JPanel {

  // --- Group 1: Panel related variables ---
  private static final long serialVersionUID = 1151818027338195157L;
  private Image image;
  private GridBagConstraints grid;

  public LoggedInAdmin loggedInAdmin;

  private final int topInset = 0;
  private final int leftInset = 0;
  private final int bottomInset = 0;
  private final int rightInset = 0;

  private final int gridxInitial = 0;
  private final int gridyInitial = 0;

  private final int gridxImageWeight = 1;
  private final int gridyImageWeight = 1;

  private AdminCentralController adminCentralController;

  // --- Group 2: Panel Map ---
  private ConcurrentHashMap<AdminCentralPanelEnums, JComponent> components;
  public PanelCentral panelCentral;

  /**
   * Default constructor that takes parameters and initializes variables.
   *
   * @param frame the BaseFrame object that the panel will be added to
   * @param layout the GridBagLayout object used to set the layout of the panel
   * @param panelCentral the PanelCentral object that the panel will be added to
   */
  public AdminCentralPanel(BaseFrame frame, GridBagLayout layout,
      PanelCentral panelCentral) {

    // Create an instance to the login admin panel
    this.loggedInAdmin = new LoggedInAdmin();

    // Dispatch responsibilities on EDT.
    SwingUtilities.invokeLater(() -> {
      // Set the panel to the gridbaglayout, establish the preferred size,
      // And get the image that will be used in the background
      this.setLayout(layout);

      this.panelCentral = panelCentral;

      this.setPreferredSize(new Dimension(frame.getHeight(), frame.getWidth()));
      this.image = new ImageIcon(getClass()
              .getResource("/images/backgroundd.jpg"))
              .getImage();
      this.components =
          new ConcurrentHashMap<AdminCentralPanelEnums,
      JComponent>();

      // --- Start Constraints ---
      // Set all of the constraints for the background image
      this.setBackgroundImageConstraints();
      frame.add(this, this.grid);
      //--- Finish Constraints End ---


      this.setComponentMainPosition();

      this.grid.gridx = 0;
      this.grid.gridy = 0;
      this.setButton(AdminCentralPanelEnums
              .ADMINLOGOUTBUTTONKEY, "Logout");

      this.add(this.components.get(AdminCentralPanelEnums
              .ADMINLOGOUTBUTTONKEY), grid);

      this.grid.gridx += 1;
      this.setButton(AdminCentralPanelEnums
              .USERVIEWBUTTONKEY, "User View");

      this.add(this.components.get(AdminCentralPanelEnums
              .USERVIEWBUTTONKEY), grid);

      this.grid.gridx += 1;
      this.setButton(AdminCentralPanelEnums
              .ADMINADDUSERBUTTONKEY, "Add user");

      this.add(this.components.get(AdminCentralPanelEnums
              .ADMINADDUSERBUTTONKEY), grid);

      this.adminCentralController = new AdminCentralController(this);
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
  private void setButton(AdminCentralPanelEnums buttonKey, String buttonText) {
    final ButtonTemplates button =
          new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
    this.grid.anchor = GridBagConstraints.CENTER;
    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(buttonKey, button);
  }

  // --------------------------------------------------------------------------
  public ConcurrentHashMap<AdminCentralPanelEnums, JComponent>
      getComponentsMap() {
    return this.components;
  }

  @Override
  public void paintComponent(Graphics g) {

    Graphics2D g2d = (Graphics2D)  g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
  }

  //--------------------------------------------------------------------------
  public LoggedInAdmin getLoggedInAdmin() {
    return this.loggedInAdmin;
  }
}
