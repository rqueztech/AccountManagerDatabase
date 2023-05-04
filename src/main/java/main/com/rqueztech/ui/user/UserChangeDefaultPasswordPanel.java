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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import main.com.rqueztech.controllers.user.UserChangeDefaultPasswordController;
import main.com.rqueztech.ui.BaseFrame;
import main.com.rqueztech.ui.PanelCentral;
import main.com.rqueztech.ui.buttons.ButtonTemplates;
import main.com.rqueztech.ui.passwordfields.PasswordFieldTemplates;
import main.com.rqueztech.ui.user.enums.UserChangeDefaultPasswordEnums;

/**
 * Sets all of the components used to change the default password, stores them
 into a components hashmap, and puts the panel on the frame.
 */
public class UserChangeDefaultPasswordPanel extends JPanel {

  private PanelCentral panelCentral;

  // --- Group 1: Panel related variables ---
  private static final long serialVersionUID = 1151818027338195157L;
  private Image image;
  private GridBagConstraints grid;

  private final int topInset = 0;
  private final int leftInset = 0;
  private final int bottomInset = 0;
  private final int rightInset = 0;

  private final int gridxInitial = 0;
  private final int gridyInitial = 0;

  private final int gridxImageWeight = 1;
  private final int gridyImageWeight = 1;

  private final int textfieldSize = 10;

  private UserChangeDefaultPasswordController userChangeDefaultPasswordController;

  // --- Group 2: Panel Map ---
  private ConcurrentHashMap<UserChangeDefaultPasswordEnums, JComponent> components;

  /**
   * Default constructor that takes parameters and initializes variables.
   *
   * @param frame the BaseFrame object that the panel will be added to
   * @param layout the GridBagLayout object used to set the layout of the panel
   * @param panelCentral the PanelCentral object that the panel will be added to
   */
  // --------------------------------------------------------------------------
  public UserChangeDefaultPasswordPanel(BaseFrame frame,
      GridBagLayout layout, PanelCentral panelCentral) {
    // Function that will toggle visibility on and off in password
    // Field found in this class
    this.panelCentral = panelCentral;

    // Dispatch responsibilities on EDT.
    SwingUtilities.invokeLater(() -> {

      this.setLayout(layout);  // Set the layout to the inherited Gridbag

      this.setPreferredSize(
          new Dimension(frame.getHeight(), frame.getWidth())); // Set the size to the
      // dimensions of the BaseFrame]

      this.image = new ImageIcon(getClass().getResource("/images/backgroundd.jpg")).getImage();
      this.components = new ConcurrentHashMap<UserChangeDefaultPasswordEnums, JComponent>();

      // --- Start Constraints ---
      // Set all of the constraints for the background image
      this.setBackgroundImageConstraints();
      frame.add(this, this.grid);
      //--- Finish Constraints End ---


      this.setComponentMainPosition();
      this.setLabelField(UserChangeDefaultPasswordEnums
          .ENTERPASSWORDLABELKEY, "Enter New Password");

      this.add(this.components.get(UserChangeDefaultPasswordEnums
          .ENTERPASSWORDLABELKEY), grid);

      this.grid.gridx = 0;
      this.grid.gridy += 1;
      this.setPasswordField(UserChangeDefaultPasswordEnums
          .ENTERPASSWORDTEXTFIELDKEY);

      this.add(this.components.get(UserChangeDefaultPasswordEnums
          .ENTERPASSWORDTEXTFIELDKEY), grid);

      this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
      this.setButton(UserChangeDefaultPasswordEnums
          .ENTERPASSWORDVISIBILITYBUTTONKEY, "Visible");

      this.add(this.components.get(UserChangeDefaultPasswordEnums
          .ENTERPASSWORDVISIBILITYBUTTONKEY), grid);


      this.grid.gridx = 0;
      this.grid.gridy += 1;
      this.setLabelField(UserChangeDefaultPasswordEnums
            .CONFIRMPASSWORDPASSWORDLABELKEY, "Confirm New Password");

      this.add(this.components.get(UserChangeDefaultPasswordEnums
            .CONFIRMPASSWORDPASSWORDLABELKEY), grid);

      this.grid.gridx = 0;
      this.grid.gridy += 1;
      this.setPasswordField(UserChangeDefaultPasswordEnums
          .CONFIRMPASSWORDPASSWORDTEXTFIELDKEY);

      this.add(this.components.get(UserChangeDefaultPasswordEnums
          .CONFIRMPASSWORDPASSWORDTEXTFIELDKEY), grid);

      this.grid.gridx += 3; // Buttons are not fixed, therefore coordinates are custom set
      this.setButton(UserChangeDefaultPasswordEnums
          .CONFIRMPASSWORDVISIBILITYBUTTONKEY, "Visibile");

      this.add(this.components.get(UserChangeDefaultPasswordEnums
          .CONFIRMPASSWORDVISIBILITYBUTTONKEY), grid);

      this.grid.gridx = 0;
      this.grid.gridy += 1;
      this.setButton(UserChangeDefaultPasswordEnums
          .CANCELCHANGEBUTTONKEY, "Cancel");

      this.add(this.components.get(UserChangeDefaultPasswordEnums
          .CANCELCHANGEBUTTONKEY), grid);

      this.grid.gridx = 1;
      this.setSubmitButton(UserChangeDefaultPasswordEnums
          .SUBMITLOGINBUTTONKEY, "Submit");

      this.add(this.components.get(UserChangeDefaultPasswordEnums.SUBMITLOGINBUTTONKEY), grid);

      this.userChangeDefaultPasswordController = new UserChangeDefaultPasswordController(this);
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
  private void setButton(UserChangeDefaultPasswordEnums buttonKey, String buttonText) {
    final ButtonTemplates button = new ButtonTemplates(
        buttonText, Color.BLACK, Color.WHITE);

    this.grid.anchor = GridBagConstraints.CENTER;
    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(buttonKey, button);
  }

  // --------------------------------------------------------------------------
  private void setSubmitButton(UserChangeDefaultPasswordEnums buttonKey,
      String buttonText) {

    ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
    this.grid.anchor = GridBagConstraints.CENTER;

    button.setOpaque(false);
    button.setEnabled(false);
    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(buttonKey, button);
  }

  /**
  * Sets the password field with the specified key to a new
  PasswordFieldTemplates object with the given colors and size, and adds it to
  the components map.
  *
  * @param passwordFieldKey the key representing the password field to be set
  */
  public void setPasswordField(UserChangeDefaultPasswordEnums passwordFieldKey) {

    final PasswordFieldTemplates passwordField = new PasswordFieldTemplates(
        Color.WHITE, Color.BLACK, textfieldSize);

    this.grid.anchor = GridBagConstraints.CENTER;
    this.grid.gridwidth = 2;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(passwordFieldKey, passwordField);
  }

  // --------------------------------------------------------------------------
  private void setLabelField(UserChangeDefaultPasswordEnums labelKey, String labelText) {
    JLabel labelField = new JLabel(labelText);
    this.grid.anchor = GridBagConstraints.CENTER;
    labelField.setBackground(Color.BLACK);
    labelField.setForeground(Color.WHITE);
    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(labelKey, labelField);
  }

  // --------------------------------------------------------------------------
  public ConcurrentHashMap<UserChangeDefaultPasswordEnums, JComponent> getComponentsMap() {
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
}
