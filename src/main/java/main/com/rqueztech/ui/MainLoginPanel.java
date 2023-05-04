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
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import main.com.rqueztech.controllers.MainLoginController;
import main.com.rqueztech.ui.buttons.ButtonTemplates;
import main.com.rqueztech.ui.enums.MainLoginPanelEnums;
import main.com.rqueztech.ui.enums.PanelCentralEnums;
import main.com.rqueztech.ui.passwordfields.PasswordFieldTemplates;
import main.com.rqueztech.ui.textfields.TextfieldTemplates;

/**
 * The MainLoginPanel contains all of the necessary components, views, and
 background images for the admin add user panel.
 *
 * @extends JPanel extends the JPanel class used to modify the current panel.
 */
public class MainLoginPanel extends JPanel {

  // --- Group 1: Panel related variables ---
  private static final long serialVersionUID = 1151818027338195157L;
  private Image image;
  private GridBagConstraints grid;

  // Insets for the GridBagLayout
  private final int topInset = 0;
  private final int leftInset = 0;
  private final int bottomInset = 0;
  private final int rightInset = 0;

  private final int gridxInitial = 0;
  private final int gridyInitial = 0;

  // Weight of image gridvalues
  private final int gridxImageWeight = 1;
  private final int gridyImageWeight = 1;

  private final int textfieldSize = 10;

  private MainLoginController mainLoginControl;

  // --- Group 2: Panel Map ---
  private ConcurrentHashMap<MainLoginPanelEnums, JComponent> components;

  /**
   * The MainLoginPanel displays the main login panel and all respective
   components.
   *
   * @param frame the BaseFrame object that the panel will be added to
   * @param layout the GridbagLayout object used to set the layout of the panel
   * @param panelCentral takes the panel central as an argument
   */
  // --------------------------------------------------------------------------
  public MainLoginPanel(BaseFrame frame, GridBagLayout layout,
      PanelCentral panelCentral) {

    // Function that will toggle visibility on and off in password
    // Field found in this class

    // Dispatch responsibilities on EDT.
    SwingUtilities.invokeLater(() -> {

      // Set the panel to the gridbaglayout, establish the preferred size,
      // And get the image that will be used in the background
      this.setLayout(layout);
      this.setPreferredSize(new Dimension(frame.getHeight(), frame.getWidth()));
      this.image = new ImageIcon(getClass().getResource("/images/backgroundd.jpg")).getImage();
      this.components = new ConcurrentHashMap<MainLoginPanelEnums, JComponent>();

      // --- Start Constraints ---
      // Set all of the constraints for the background image
      this.setBackgroundImageConstraints();
      frame.add(this, this.grid);
      //--- Finish Constraints End ---

      // Set the Username
      this.setComponentMainPosition();
      this.setLabelField(MainLoginPanelEnums
          .USERNAMELABELKEY, "Enter Username");

      this.add(this.components.get(MainLoginPanelEnums
          .USERNAMELABELKEY), grid);

      this.setNewTextfieldPosition(); // All textfields are fixed
      this.setTextField(MainLoginPanelEnums.USERNAMETEXTFIELDKEY);

      this.add(this.components.get(MainLoginPanelEnums
          .USERNAMETEXTFIELDKEY), grid);

      this.setNewLabelPosition(); // All labels are fixed
      this.setLabelField(MainLoginPanelEnums
          .PASSWORDLABELKEY, "Enter Password");

      this.add(this.components.get(MainLoginPanelEnums
          .PASSWORDLABELKEY), grid);

      this.setNewTextfieldPosition(); // All textfields are fixed,
      //therefore coordinates set in function

      this.setPasswordField(MainLoginPanelEnums.PASSWORDTEXTFIELDKEY);
      this.add(this.components.get(MainLoginPanelEnums
          .PASSWORDTEXTFIELDKEY), grid);

      // Creates the button to toggle visibility on/off in visibility button key
      this.grid.gridx += 3;
      this.setButton(MainLoginPanelEnums.VISIBILITYBUTTONKEY, "Visible");
      this.add(this.components.get(MainLoginPanelEnums.VISIBILITYBUTTONKEY), grid);

      // Creates the user login button
      this.grid.gridx = 0;
      this.grid.gridy += 1;
      this.setButton(MainLoginPanelEnums.USERLOGINBUTTONKEY, "User");
      this.add(this.components.get(MainLoginPanelEnums.USERLOGINBUTTONKEY), grid);

      // Creates the admin login button
      this.grid.gridx += 1;
      this.setButton(MainLoginPanelEnums.ADMINLOGINBUTTONKEY, "Admin");
      this.add(this.components.get(MainLoginPanelEnums.ADMINLOGINBUTTONKEY), grid);

      // Calls the mainlogin control function to set the listeners for the
      // Current view
      this.mainLoginControl = new MainLoginController(this, panelCentral);
    });
  }

  // --------------------------------------------------------------------------
  private void setComponentMainPosition() {
    this.grid.insets = new Insets(2, 2, 2, 2);
    this.grid.gridx = gridxInitial;
    this.grid.gridy = gridyInitial;
  }

  // --------------------------------------------------------------------------
  // This will set the label down one
  private void setNewLabelPosition() {
    this.grid.gridx = 0;
    this.grid.gridy += 1;
  }

  // --------------------------------------------------------------------------
  private void setNewTextfieldPosition() {
    this.grid.gridx = 0;
    this.grid.gridy += 1;
  }

  // --------------------------------------------------------------------------
  private void setBackgroundImageConstraints() {
    // Set everything to initial status.
    this.grid = new GridBagConstraints(); // Set the gridbag constraints

    // Fill both vertically and horizontally
    this.grid.fill = GridBagConstraints.BOTH;
    this.grid.gridx = gridxInitial;
    this.grid.gridx = gridyInitial;
    this.grid.weightx = gridxImageWeight; // Value is 0: initial
    this.grid.weighty = gridyImageWeight; // Value is 0: initial

    // Insets values all 0 (Initial)
    this.grid.insets = new Insets(topInset, leftInset, bottomInset, rightInset);
  }

  // --------------------------------------------------------------------------
  private void setButton(MainLoginPanelEnums buttonKey, String buttonText) {
    final ButtonTemplates button = new ButtonTemplates(
        buttonText, Color.BLACK, Color.WHITE);

    this.grid.anchor = GridBagConstraints.CENTER;
    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(buttonKey, button);
  }

  // --------------------------------------------------------------------------
  private void setTextField(MainLoginPanelEnums textFieldKey) {
    final TextfieldTemplates textField = new TextfieldTemplates(Color.WHITE,
         Color.BLACK, textfieldSize);

    this.grid.anchor = GridBagConstraints.CENTER;
    this.grid.gridwidth = 2;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(textFieldKey, textField);
  }

  // --------------------------------------------------------------------------
  private void setPasswordField(MainLoginPanelEnums passwordFieldKey) {
    final PasswordFieldTemplates passwordField = new PasswordFieldTemplates(
          Color.WHITE, Color.BLACK, textfieldSize);

    this.grid.anchor = GridBagConstraints.CENTER;
    this.grid.gridwidth = 2;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(passwordFieldKey, passwordField);
  }

  // --------------------------------------------------------------------------
  private void setLabelField(MainLoginPanelEnums labelKey, String labelText) {
    JLabel labelField = new JLabel(labelText);
    labelField.setBackground(Color.BLACK);
    labelField.setForeground(Color.WHITE);

    this.grid.anchor = GridBagConstraints.CENTER;
    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(labelKey, labelField);
  }


  public ConcurrentHashMap<MainLoginPanelEnums, JComponent> getComponentsMap() {
    return this.components;
  }

  @Override
  public void paintComponent(Graphics g) {

    Graphics2D g2d = (Graphics2D)  g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
  }
}
