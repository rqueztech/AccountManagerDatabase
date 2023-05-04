package main.com.rqueztech.ui.configuration;

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
import main.com.rqueztech.controllers.configuration.SetupConfigurationController;
import main.com.rqueztech.ui.BaseFrame;
import main.com.rqueztech.ui.PanelCentral;
import main.com.rqueztech.ui.buttons.ButtonTemplates;
import main.com.rqueztech.ui.configuration.enums.SetupConfigurationPanelEnums;
import main.com.rqueztech.ui.passwordfields.PasswordFieldTemplates;
import main.com.rqueztech.ui.textfields.TextfieldTemplates;

/**
 * The SetupConfigurationPanel class is responsible for setting up the UI elements for the
 Setup Configuration Panel. It extends JPanel and implements the ActionListener interface.
 It initializes the necessary components needed for the panel, such as labels, text fields,
 and buttons.
 The class also contains methods for setting up the constraints for the background image and the
 preferred size of the panel.
 The class makes use of the SetupConfigurationPanelEnums enum to identify the UI components
 and their corresponding keys. It also uses the SetupConfigurationController class to handle
 the actions performed
 by the user on the panel.
 *
 * @extends JPanel
 */
public class SetupConfigurationPanel extends JPanel {

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

  private int gridxCurrent = 0;
  private int gridyCurrent = 0;

  private final int gridxImageWeight = 1;
  private final int gridyImageWeight = 1;

  private final int textfieldSize = 15;

  private PanelCentral panelCentral;

  // --- Group 2: Panel Map ---
  private ConcurrentHashMap<SetupConfigurationPanelEnums, JComponent> components;
  private SetupConfigurationController setupConfigurationController;

  /**
   * Default constructor that takes parameters and initializes variables.
   *
   * @param frame the BaseFrame object that the panel will be added to
   * @param layout the GridBagLayout object used to set the layout of the panel
   * @param panelCentral the PanelCentral object that the panel will be added to
   */
  public SetupConfigurationPanel(BaseFrame frame, GridBagLayout layout, PanelCentral panelCentral) {
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
      this.components = new ConcurrentHashMap<SetupConfigurationPanelEnums, JComponent>();

      // --- Start Constraints ---
      // Set all of the constraints for the background image
      this.setBackgroundImageConstraints();
      frame.add(this, this.grid);
      //--- Finish Constraints End ---

      // --- Set the initial values for the grid
      this.grid.fill = GridBagConstraints.HORIZONTAL;
      this.grid.anchor = GridBagConstraints.CENTER;
      this.grid.insets = new Insets(2, 2, 2, 2);
      this.grid.gridx = gridxInitial;
      this.grid.gridy = gridyInitial;

      this.setLabelField(SetupConfigurationPanelEnums
              .FIRSTNAMELABELKEY, "Enter FirstName");

      this.setTextField(SetupConfigurationPanelEnums
              .FIRSTNAMETEXTFIELDKEY);

      this.setLabelField(SetupConfigurationPanelEnums
              .LASTNAMELABELKEY, "Enter LastName");

      this.setTextField(SetupConfigurationPanelEnums
              .LASTNAMETEXTFIELDKEY);

      this.setLabelField(SetupConfigurationPanelEnums
              .PASSPHRASELABELKEY, "Enter Passphrase");

      this.setPasswordField(SetupConfigurationPanelEnums
              .PASSPHRASETEXTFIELDKEY);

      this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
      this.setButton(SetupConfigurationPanelEnums
              .PASSPHRASEVISIBILITYBUTTONKEY, "Visible");

      this.add(this.components.get(SetupConfigurationPanelEnums
              .PASSPHRASEVISIBILITYBUTTONKEY), grid);

      this.setLabelField(SetupConfigurationPanelEnums
              .CONFIRMPASSPHRASELABELKEY, "Confirm Passphrase");

      this.setPasswordField(SetupConfigurationPanelEnums
               .CONFIRMPASSPHRASETEXTFIELDKEY);

      this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
      this.setButton(SetupConfigurationPanelEnums
          .CONFIRMPASSPHRASEVISIBILITYBUTTONKEY, "Visible");

      this.add(this.components.get(SetupConfigurationPanelEnums
          .CONFIRMPASSPHRASEVISIBILITYBUTTONKEY), grid);

      this.setLabelField(SetupConfigurationPanelEnums
          .PASSWORDLABELKEY, "Enter New Password");

      this.setPasswordField(SetupConfigurationPanelEnums
          .PASSWORDTEXTFIELDKEY);

      this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
      this.setButton(SetupConfigurationPanelEnums
          .PASSWORDVISIBILITYBUTTONKEY, "Visible");

      this.add(this.components.get(SetupConfigurationPanelEnums
          .PASSWORDVISIBILITYBUTTONKEY), grid);

      this.setLabelField(SetupConfigurationPanelEnums
          .CONFIRMPASSWORDLABELKEY, "Confirm New Password");

      this.setPasswordField(SetupConfigurationPanelEnums
          .CONFIRMPASSWORDTEXTFIELDKEY);

      this.grid.gridx += 3; // Buttons are not fixed, therefore coordinate are custom set.
      this.setButton(SetupConfigurationPanelEnums
          .CONFIRMPASSWORDVISIBILITYBUTTONKEY, "Visible");

      this.add(this.components.get(SetupConfigurationPanelEnums
          .CONFIRMPASSWORDVISIBILITYBUTTONKEY), grid);

      this.grid.fill = GridBagConstraints.HORIZONTAL;
      this.grid.anchor = GridBagConstraints.EAST;
      this.grid.gridx = gridxCurrent; // Buttons are not fixed, therefore coordinates are custom set
      this.grid.gridy += gridyCurrent;
      this.setButton(SetupConfigurationPanelEnums.EXITBUTTONKEY, "Cancel");
      this.add(this.components.get(SetupConfigurationPanelEnums.EXITBUTTONKEY), grid);

      this.grid.anchor = GridBagConstraints.WEST;
      this.grid.gridx += 1;
      this.setAdminButton(SetupConfigurationPanelEnums.SUBMITBUTTONKEY, "Submit");
      this.add(this.components.get(SetupConfigurationPanelEnums.SUBMITBUTTONKEY), grid);

      this.setupConfigurationController = new SetupConfigurationController(this);
    });
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
  private void setButton(SetupConfigurationPanelEnums buttonKey, String buttonText) {

    final ButtonTemplates button = new ButtonTemplates(
        buttonText, Color.BLACK, Color.WHITE);

    //this.grid.anchor = GridBagConstraints.CENTER;
    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(buttonKey, button);
  }

  // --------------------------------------------------------------------------
  private void setAdminButton(SetupConfigurationPanelEnums buttonKey, String buttonText) {

    ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);
    //this.grid.anchor = GridBagConstraints.CENTER;
    button.setEnabled(false);

    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;
    button.setOpaque(false);

    this.components.put(buttonKey, button);
  }

  /**
  * Sets a text field with the given configuration key in the setup configuration panel.
  *
  * @param textFieldKey The key of the text field in the configuration panel.
  */
  // --------------------------------------------------------------------------
  public void setTextField(SetupConfigurationPanelEnums textFieldKey) {
    final TextfieldTemplates textField = new TextfieldTemplates(Color.WHITE,
        Color.BLACK, textfieldSize);

    //this.grid.anchor = GridBagConstraints.CENTER;
    //textField.setFont(new Font("Arial", Font.PLAIN, 14));
    this.grid.gridwidth = 2;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;
    this.grid.gridx = gridxInitial;
    this.gridyCurrent += 1;
    this.grid.gridy += gridyCurrent;

    this.components.put(textFieldKey, textField);
    this.add(this.components.get(textFieldKey), grid);
  }

  /**
   * Sets a password field with the given configuration key in the setup configuration panel.
   *
   * @param passwordFieldKey The key of the text field in the configuration panel.
   */
  // --------------------------------------------------------------------------
  public void setPasswordField(SetupConfigurationPanelEnums passwordFieldKey) {
    final PasswordFieldTemplates passwordField = new PasswordFieldTemplates(
          Color.WHITE, Color.BLACK, textfieldSize);

    this.grid.gridwidth = 2;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;
    this.grid.gridx = gridxInitial;
    this.gridyCurrent += 1;
    this.grid.gridy += gridyCurrent;

    this.components.put(passwordFieldKey, passwordField);
    this.add(this.components.get(passwordFieldKey), grid);
  }

  // --------------------------------------------------------------------------
  private void setLabelField(SetupConfigurationPanelEnums labelKey, String labelText) {
    JLabel labelField = new JLabel(labelText);
    //this.grid.anchor = GridBagConstraints.CENTER;
    labelField.setBackground(Color.BLACK);
    labelField.setForeground(Color.WHITE);
    this.grid.gridwidth = 2;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;
    this.grid.gridx = gridxInitial;
    this.gridyCurrent += 1;
    this.grid.gridy += 1;

    this.components.put(labelKey, labelField);
    this.add(this.components.get(labelKey), grid);
  }

  // --------------------------------------------------------------------------
  public PanelCentral getPanelCentral() {
    return this.panelCentral;
  }

  // --------------------------------------------------------------------------
  public ConcurrentHashMap<SetupConfigurationPanelEnums, JComponent> getComponentsMap() {
    return this.components;
  }

  // --------------------------------------------------------------------------
  @Override
  public void paintComponent(Graphics g) {

    Graphics2D g2d = (Graphics2D)  g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
  }
}
