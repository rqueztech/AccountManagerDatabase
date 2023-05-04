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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import main.com.rqueztech.controllers.admin.AdminAddUserController;
import main.com.rqueztech.ui.BaseFrame;
import main.com.rqueztech.ui.PanelCentral;
import main.com.rqueztech.ui.admin.enums.AdminAddUserEnums;
import main.com.rqueztech.ui.buttons.ButtonTemplates;
import main.com.rqueztech.ui.passwordfields.PasswordFieldTemplates;
import main.com.rqueztech.ui.textfields.TextfieldTemplates;

/**
 * The AdminAddUserPanel contains all of the necessary components, views, and
 background images for the admin add user panel.
 *
 * @extends JPanel extends the JPanel class used to modify the current panel.
 */
public class AdminAddUserPanel extends JPanel {

  // --- Group 1: Panel related variables ---
  private static final long serialVersionUID = 1151818027338195157L;
  private Image image;
  private GridBagConstraints grid;

  private final int topInset = 0;
  private final int leftInset = 0;
  private final int bottomInset = 0;
  private final int rightInset = 0;

  // --- Section 4: Set Combo Box
  private final int gridxInitial = 0;
  private final int gridyInitial = 0;

  private final int gridxImageWeight = 1;
  private final int gridyImageWeight = 1;

  private final int textfieldSize = 5;

  // --- Group 2: Panel Map ---
  private ConcurrentHashMap<AdminAddUserEnums, JComponent> components;
  private JComboBox<String> gender;
  private PanelCentral panelCentral;
  String[] genderOptions = { "Select", "Male", "Female", "Undisclosed" };

  /**
   * Sets all of the JPanel components used in the creation of the Admin Add
   User Panel and stores them as a concurrent hashmap.
   */
  private AdminAddUserController adminAddUserController;

  /**
   * Default constructor that takes parameters and initializes variables.
   *
   * @param frame the BaseFrame object that the panel will be added to
   * @param layout the GridBagLayout object used to set the layout of the panel
   * @param panelCentral the PanelCentral object that the panel will be added to
   */
  public AdminAddUserPanel(BaseFrame frame, GridBagLayout layout,
      PanelCentral panelCentral) {
    // Function that will toggle visibility on and off in password
    // Field found in this class

    // Dispatch responsibilities on EDT.
    SwingUtilities.invokeLater(() -> {

      this.panelCentral = panelCentral;

      // Set the panel to the gridbaglayout, establish the preferred size,
      // And get the image that will be used in the background
      this.setLayout(layout);

      this.setPreferredSize(new Dimension(
          frame.getHeight(), frame.getWidth()));

      this.image = new ImageIcon(getClass()
              .getResource("/images/backgroundd.jpg")).getImage();

      this.components =
          new ConcurrentHashMap<AdminAddUserEnums, JComponent>();

      // --- Start Constraints ---
      // Set all of the constraints for the background image
      this.setBackgroundImageConstraints();
      frame.add(this, this.grid);
      //--- Finish Constraints End ---
      this.gender = new JComboBox<String>(genderOptions);

      this.setComponentMainPosition();
      this.grid.gridx = 0;
      this.grid.gridy = 0;

      this.setLabelField(AdminAddUserEnums
              .FIRSTNAMELABELKEY, "Enter First Name");

      this.add(this.components.get(AdminAddUserEnums
              .FIRSTNAMELABELKEY), this.grid);

      // Firstname textfield
      this.grid.gridy += 1;  // Increase vertical grid by one
      this.setTextField(AdminAddUserEnums
              .FIRSTNAMETEXTFIELDKEY);  // Set the textfield key

      this.add(this.components.get(AdminAddUserEnums
              .FIRSTNAMETEXTFIELDKEY), this.grid);  // Add the component to the grid

      this.grid.gridy += 1;
      this.setLabelField(AdminAddUserEnums
              .LASTNAMELABELKEY, "Enter Last Name");

      this.add(this.components.get(AdminAddUserEnums
              .LASTNAMELABELKEY), this.grid);

      // Lastname textfield
      this.grid.gridy += 1;
      this.setTextField(AdminAddUserEnums.LASTNAMETEXTFIELDKEY);

      this.add(this.components.get(AdminAddUserEnums
              .LASTNAMETEXTFIELDKEY), this.grid);

      // Passphrase Label
      this.grid.gridy += 1;
      this.setLabelField(AdminAddUserEnums
              .PASSPHRASELABELKEY, "Enter Passphrase");

      this.add(this.components.get(AdminAddUserEnums
              .PASSPHRASELABELKEY), this.grid);

      // Passphrase textfield
      this.grid.gridy += 1;
      this.setPasswordField(AdminAddUserEnums.PASSPHRASETEXTFIELDKEY);

      this.add(this.components.get(AdminAddUserEnums
              .PASSPHRASETEXTFIELDKEY), this.grid);

      // Passphrase button
      this.grid.gridx += 2;
      this.setButton(AdminAddUserEnums
              .PASSPHRASEVISIBILITYBUTTONKEY, "Visibility");

      this.add(this.components.get(AdminAddUserEnums
              .PASSPHRASEVISIBILITYBUTTONKEY), this.grid);

      // Combo Box. Note: ComboBox is not added to the existing
      //
      this.grid.gridx = 0;
      this.grid.gridy += 1;
      this.setComboBox();
      this.add(this.gender, this.grid);

      // Set the exit button
      this.grid.gridy += 1;
      this.setButton(AdminAddUserEnums.CANCELBUTTONKEY, "Cancel");

      this.add(this.components.get(AdminAddUserEnums
              .CANCELBUTTONKEY), this.grid);

      this.grid.gridx += 1;
      this.setAddButton(AdminAddUserEnums.ADDUSERBUTTONKEY, "Add user");

      this.add(this.components.get(AdminAddUserEnums
              .ADDUSERBUTTONKEY), this.grid);

      this.adminAddUserController = new AdminAddUserController(this, gender);
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
  private void setTextField(AdminAddUserEnums textFieldKey) {
    final TextfieldTemplates textField = new TextfieldTemplates(Color.WHITE,
          Color.BLACK, textfieldSize);

    //this.grid.anchor = GridBagConstraints.CENTER;
    //textField.setFont(new Font("Arial", Font.PLAIN, 14));

    this.grid.gridwidth = 2;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;
    this.grid.gridx = gridxInitial;
    this.grid.gridy += 1;

    this.components.put(textFieldKey, textField);
    this.add(this.components.get(textFieldKey), this.grid);
  }

  // --------------------------------------------------------------------------
  private void setButton(AdminAddUserEnums buttonKey, String buttonText) {
    final ButtonTemplates button = new ButtonTemplates(
        buttonText, Color.BLACK, Color.WHITE);

    this.grid.anchor = GridBagConstraints.CENTER;
    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(buttonKey, button);
  }

  // --------------------------------------------------------------------------
  private void setAddButton(AdminAddUserEnums buttonKey, String buttonText) {
    final ButtonTemplates button = new ButtonTemplates(
        buttonText, Color.BLACK, Color.WHITE);

    this.grid.anchor = GridBagConstraints.CENTER;
    button.setEnabled(false);
    button.setOpaque(false);
    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(buttonKey, button);
  }

  // --------------------------------------------------------------------------
  private void setPasswordField(AdminAddUserEnums passwordFieldKey) {
    final PasswordFieldTemplates passwordField = new PasswordFieldTemplates(
          Color.WHITE, Color.BLACK, textfieldSize);

    this.grid.anchor = GridBagConstraints.CENTER;
    this.grid.gridwidth = 2;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(passwordFieldKey, passwordField);
  }

  // --------------------------------------------------------------------------
  private void setComboBox() {
    // Gender Dropbox
    this.gender.setForeground(Color.white);
    this.gender.setBackground(Color.black);

    this.grid.gridwidth = 1;
    this.grid.gridheight = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;
    this.grid.insets = new Insets(2, 0, 10, 0);
  }

  // --------------------------------------------------------------------------
  private void setLabelField(AdminAddUserEnums labelKey, String labelText) {
    JLabel labelField = new JLabel(labelText);
    this.grid.anchor = GridBagConstraints.CENTER;
    labelField.setBackground(Color.BLACK);
    labelField.setForeground(Color.WHITE);
    this.grid.gridwidth = 3;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(labelKey, labelField);
  }

  // --------------------------------------------------------------------------
  public ConcurrentHashMap<AdminAddUserEnums, JComponent> getComponentsMap() {
    return this.components;
  }

  // --------------------------------------------------------------------------
  public JComboBox<String> getGender() {
    return this.gender;
  }

  // --------------------------------------------------------------------------
  public PanelCentral getPanelCentral() {
    return this.panelCentral;
  }

  // --------------------------------------------------------------------------
  @Override
  public void paintComponent(Graphics g) {

    Graphics2D g2d = (Graphics2D)  g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);

    g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
  }
}
