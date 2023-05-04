package main.com.rqueztech.ui.admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import main.com.rqueztech.controllers.admin.AdminUserViewController;
import main.com.rqueztech.ui.BaseFrame;
import main.com.rqueztech.ui.PanelCentral;
import main.com.rqueztech.ui.admin.enums.AdminUserViewEnums;
import main.com.rqueztech.ui.buttons.ButtonTemplates;
import main.com.rqueztech.ui.textfields.TextfieldTemplates;

/**
 * The AdminUserViewPanel contains all of the respective components for the
 admin user view.
 *
 * @extends JPanel extends the JPanel class used to modify the current panel.
 */
public class AdminUserViewPanel extends JPanel {

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

  private final int textfieldSize = 10;

  private PanelCentral panelCentral;

  // --- Group 2: Panel Map ---
  private ConcurrentHashMap<AdminUserViewEnums, JComponent> components;

  private AdminUserViewController adminUserViewController;

  /**
   * Default constructor that takes parameters and initializes variables.
   *
   * @param frame the BaseFrame object that the panel will be added to
   * @param layout the GridBagLayout object used to set the layout of the panel
   * @param panelCentral the PanelCentral object that the panel will be added to
   */
  public AdminUserViewPanel(BaseFrame frame, GridBagLayout layout,
      PanelCentral panelCentral) {

    this.panelCentral = panelCentral;

    this.components = new ConcurrentHashMap<AdminUserViewEnums, JComponent>();

    // Dispatch responsibilities on EDT.
    SwingUtilities.invokeLater(() -> {

      this.setLayout(layout);
      this.setPreferredSize(new Dimension(frame.getHeight(), frame.getWidth()));
      this.image = new ImageIcon(getClass().getResource("/images/backgroundd.jpg")).getImage();
      this.components = new ConcurrentHashMap<AdminUserViewEnums, JComponent>();

      this.setBackgroundImageConstraints();
      frame.add(this, this.grid);

      // --- Start Constraints ---
      // Set all of the constraints for the background image
      this.setBackgroundImageConstraints();
      frame.add(this, this.grid);

      this.grid.gridx = 0;
      this.grid.gridy = 0;

      this.setLabelField(AdminUserViewEnums.CONFIRMPASSWORDPASSWORDLABELKEY, "User Table");
      this.add(this.components.get(AdminUserViewEnums.CONFIRMPASSWORDPASSWORDLABELKEY), grid);

      this.grid.gridy += 1;

      // One dimensional array representing table columns
      String[] columns = {"UsrName", "FName", "LName, EmpNo, "};

      // Two dimensional array representing data inside of table (row format)
      String[][] rows = {
        {"rquez", "Ricardo", "Quezada"},
        {"cmans", "Carl", "Mansfield"},
        {"rquez", "Ricardo", "Quezada"},
        {"cmans", "Carl", "Mansfield"},
        {"rquez", "Ricardo", "Quezada"},
        {"cmans", "Carl", "Mansfield"},
        {"rquez", "Ricardo", "Quezada"},
        {"cmans", "Carl", "Mansfield"},
        {"rquez", "Ricardo", "Quezada"},
        {"cmans", "Carl", "Mansfield"},
        {"rquez", "Ricardo", "Quezada"},
        {"cmans", "Carl", "Mansfield"},
        {"rquez", "Ricardo", "Quezada"},
        {"cmans", "Carl", "Mansfield"},
        {"rquez", "Ricardo", "Quezada"},
        {"cmans", "Carl", "Mansfield"},
        {"rquez", "Ricardo", "Quezada"},
        {"cmans", "Carl", "Mansfield"}
      };

      // Annonymous inner class implementing a table model
      DefaultTableModel model = new DefaultTableModel(rows, columns) {
        /**
         * Sets the UID for object serialization.
         */
        private static final long serialVersionUID = 8519481516680066111L;

        @Override
        public boolean isCellEditable(int row, int column) {
          return false; // make all cells not editable
        }
      };

      // Anonymous innerclass defining the table for the program
      JTable table = new JTable(model);

      TableRowSorter<TableModel> rowSorter =
          new TableRowSorter<>(table.getModel());

      table.setRowSorter(rowSorter);

      this.grid.fill = GridBagConstraints.BOTH;
      table.getTableHeader().setReorderingAllowed(false);
      table.setBackground(Color.BLACK);
      table.setForeground(Color.WHITE);

      JScrollPane scrollPane = new JScrollPane(table);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane
              .VERTICAL_SCROLLBAR_ALWAYS);

      scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {

        @Override
      protected void configureScrollBarColors() {
          this.thumbColor = Color.BLACK;
          this.trackColor = Color.DARK_GRAY;
        }

        @Override
      protected JButton createDecreaseButton(int orientation) {
          JButton button = super.createDecreaseButton(orientation);
          button.setForeground(Color.WHITE);
          button.setBackground(Color.BLACK);
          return button;
        }

        @Override
      protected JButton createIncreaseButton(int orientation) {
          JButton button = super.createIncreaseButton(orientation);
          button.setForeground(Color.WHITE);
          button.setBackground(Color.BLACK);
          return button;
        }

        @Override
      protected void paintTrack(Graphics g, JComponent c,
            Rectangle trackBounds) {

          // paint the track with your desired color
          g.setColor(Color.DARK_GRAY);
          g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width,
              trackBounds.height);
        }
      });

      // Set custom header renderer
      JTableHeader header = table.getTableHeader();
      header.setDefaultRenderer(new DefaultTableCellRenderer() {
        /**
         * Serial UID that serializes the panel object.
         */
        private static final long serialVersionUID = -4480519341127736765L;

        @Override
      public Component getTableCellRendererComponent(JTable table,
            Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {

          JLabel label = (JLabel)
              super.getTableCellRendererComponent(table, value,
              isSelected, hasFocus, row, column);

          label.setForeground(Color.WHITE);
          label.setBackground(Color.BLACK);
          return label;
        }
      });

      scrollPane.setPreferredSize(new Dimension(350, 160));
      scrollPane.setBackground(Color.BLACK);

      this.grid.gridwidth = 4;

      table.setBorder(null);
      scrollPane.setBorder(null);
      scrollPane.setViewportBorder(null);
      this.grid.insets = new Insets(5, 0, 0, 0);

      this.add(scrollPane, this.grid);

      this.grid.gridy += 1;
      this.setTextField(AdminUserViewEnums.RETURNCENTRALTEXTFIELDKEY);

      this.add(this.components.get(AdminUserViewEnums
              .RETURNCENTRALTEXTFIELDKEY), this.grid);

      JTextField textFilterField = (JTextField)
          this.components.get(AdminUserViewEnums
                  .RETURNCENTRALTEXTFIELDKEY);

      textFilterField.getDocument().addDocumentListener(new DocumentListener() {

        @Override
      public void removeUpdate(DocumentEvent e) {
          // TODO Auto-generated method stub
          String text = textFilterField.getText();

          if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
          } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
          }
        }

        @Override
      public void insertUpdate(DocumentEvent e) {
          // TODO Auto-generated method stub
          String text = textFilterField.getText();

          if (text.trim().length() == 0) {
            rowSorter.setRowFilter(null);
          } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
          }
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            // TODO Auto-generated method stub

            //To change body of generated methods, choose Tools | Templates.
            throw new UnsupportedOperationException("Not supported yet.");
        }
      });

      this.grid.gridx += 1;

      this.setButton(AdminUserViewEnums
           .DELETEUSERBUTTONKEY, "Delete User");

      this.add(this.components.get(AdminUserViewEnums
           .DELETEUSERBUTTONKEY), this.grid);

      this.grid.gridx = 0;
      this.grid.gridy += 1;
      this.setButton(AdminUserViewEnums
           .RETURNCENTRALBUTTONKEY, "Go Back");

      this.add(this.components.get(AdminUserViewEnums
           .RETURNCENTRALBUTTONKEY), this.grid);

      this.grid.gridx += 1;
      this.setButton(AdminUserViewEnums.ADDUSERBUTTONKEY, "Add User");
      this.add(this.components.get(AdminUserViewEnums.ADDUSERBUTTONKEY), this.grid);

      this.grid.gridx += 1;
      this.setButton(AdminUserViewEnums.DELETEUSERBUTTONKEY, "Delete User");
      this.add(this.components.get(AdminUserViewEnums.DELETEUSERBUTTONKEY), this.grid);

      this.grid.gridy += 1;
      this.grid.gridx = 0;
      this.setButton(AdminUserViewEnums.ADMINLOGOUTBUTTONKEY, "Logout");
      this.add(this.components.get(AdminUserViewEnums
            .ADMINLOGOUTBUTTONKEY), this.grid);

      this.adminUserViewController = new AdminUserViewController(this);
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
  private void setTextField(AdminUserViewEnums textFieldKey) {
    final TextfieldTemplates textField = new TextfieldTemplates(
        Color.DARK_GRAY, Color.WHITE, textfieldSize);

    //this.grid.anchor = GridBagConstraints.CENTER;
    //textField.setFont(new Font("Arial", Font.PLAIN, 14));

    this.grid.gridwidth = 2;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;
    this.grid.gridx = gridxInitial;
    this.grid.gridy += 1;

    this.components.put(textFieldKey, textField);
    this.add(this.components.get(textFieldKey), grid);
  }

  // --------------------------------------------------------------------------
  private void setButton(AdminUserViewEnums buttonKey, String buttonText) {
    final ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK,
          Color.WHITE);
    this.grid.anchor = GridBagConstraints.CENTER;
    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(buttonKey, button);
  }

  // --------------------------------------------------------------------------
  private void setLabelField(AdminUserViewEnums labelKey, String labelText) {
    JLabel labelField = new JLabel(labelText);
    this.grid.anchor = GridBagConstraints.CENTER;
    labelField.setBackground(Color.BLACK);
    labelField.setForeground(Color.WHITE);
    this.grid.gridwidth = 1;
    this.grid.weightx = 0.0;
    this.grid.weighty = 0.0;

    this.components.put(labelKey, labelField);
  }

  public ConcurrentHashMap<AdminUserViewEnums, JComponent> getComponentsMap() {
    return this.components;
  }

  public PanelCentral getPanelCentral() {
    return this.panelCentral;
  }

  @Override
  public void paintComponent(Graphics g) {

    Graphics2D g2d = (Graphics2D)  g;
    g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
  }
}
