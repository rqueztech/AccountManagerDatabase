package com.rqueztech.ui.admin;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.rqueztech.ui.BaseFrame;
import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.events.TogglePasswordVisibility;

public class AdminUserViewPanel extends JPanel {
	
	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private JFrame frame;
	private Image image;
	private GridBagConstraints grid;

	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 5;
	private final int RIGHT_INSET = 0;
	
	// --- Section 1: Adminname Component Keys
	private final String ENTERPASSWORD_LABEL_KEY = "ENTERPASSWORD_LABEL_KEY";
	private final String ENTERPASSWORD_TEXTFIELD_KEY = "ENTERPASSWORD_TEXTFIELD_KEY";
	private final String ENTERPASSWORD_VISIBILITY_BUTTON_KEY = "ENTERPASSWORD_VISIBILITY_BUTTON_KEY";
	
	// --- Section 2: Password Component Keys
	private final String CONFIRMPASSWORDPASSWORD_LABEL_KEY = "CONFIRMPASSWORDPASSWORD_LABEL_KEY";
	private final String CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY = "CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY";
	private final String CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY = "CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY";
	
	// --- Section 3: Login Button Component Keys
	private final String ADMIN_LOGOUT_BUTTON_KEY = "ADMIN_LOGOUT_BUTTON_KEY";
	private final String ADMIN_ADD_USER_BUTTON_KEY = "ADMIN_ADD_USER_BUTTON_KEY";
	
	private final String USER_VIEW_BUTTON_KEY = "USER_VIEW_BUTTON_KEY";
	
	// --- Section 2: Password Component Keys
	private final String RETURN_CENTRAL_LABEL_KEY = "RETURN_CENTRAL_LABEL_KEY";
	private final String RETURN_CENTRAL_TEXTFIELD_KEY = "RETURN_CENTRAL_TEXTFIELD_KEY";
	private final String RETURN_CENTRAL_VISIBILITY_BUTTON_KEY = "RETURN_CENTRAL_VISIBILITY_BUTTON_KEY";
	
	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;
	
	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEWEIGHT = 1;
	
	private final int TEXTFIELD_SIZE = 10;
	
	private TogglePasswordVisibility togglePasswordVisibility;
	
	private int newYValue = 0;
	private PanelCentral panelCentral;
	
	// --- Group 2: Panel Map ---
	private ConcurrentHashMap <String, JComponent> components;
	
	// --------------------------------------------------------------------------------------
	public AdminUserViewPanel(BaseFrame frame, GridBagLayout layout, PanelCentral panelCentral) {
		// Function that will toggle visibility on and off in password
		// Field found in this class
		this.togglePasswordVisibility = new TogglePasswordVisibility();
		this.panelCentral = panelCentral;
		
		// Dispatch responsibilities on EDT.
		SwingUtilities.invokeLater(() -> {
		
			// Set the panel to the gridbaglayout, establish the preferred size,
			// And get the image that will be used in the background
			this.setLayout(layout);
	        this.setPreferredSize(new Dimension(frame.getHeight(), frame.getWidth()));
	        this.image = new ImageIcon("backgroundd.jpg").getImage();
	        this.components = new ConcurrentHashMap <String, JComponent> ();
	        
	        // --- Start Constraints ---
	        // Set all of the constraints for the background image
	        this.setBackgroundImageConstraints();
	        frame.add(this, this.grid);
	        
	        this.grid.gridx = 0;
	        this.grid.gridy = 0;
	        
	        this.setLabelField(CONFIRMPASSWORDPASSWORD_LABEL_KEY, "User Table");
	        this.add(this.components.get(CONFIRMPASSWORDPASSWORD_LABEL_KEY), grid);
	    
	        this.grid.gridy += 1;
	        
	        // One dimensional array representing table columns
	        String[] columns = {"UsrName", "FName", "LName"};
	        
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
				 * 
				 */
				private static final long serialVersionUID = 8519481516680066111L;

				@Override
	            public boolean isCellEditable(int row, int column) {
	                return false; // make all cells not editable
	            }
	        };
	        
	        // Anonymous innerclass defining the table for the program
	        JTable table = new JTable(model);
	        
	        this.grid.fill = GridBagConstraints.BOTH;
	        table.getTableHeader().setReorderingAllowed(false);
	        table.setBackground(Color.BLACK);
	        table.setForeground(Color.WHITE);
	        
	        JScrollPane scrollPane = new JScrollPane(table);
	        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
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
	            protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
	                // paint the track with your desired color
	                g.setColor(Color.DARK_GRAY);
	                g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
	            }
	        });

	        
	        // Set custom header renderer
	        JTableHeader header = table.getTableHeader();
	        header.setDefaultRenderer(new DefaultTableCellRenderer() {
	            /**
				 * 
				 */
				private static final long serialVersionUID = -4480519341127736765L;

				@Override
	            public Component getTableCellRendererComponent(JTable table, Object value,
	                    boolean isSelected, boolean hasFocus, int row, int column) {
	                JLabel label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	                label.setForeground(Color.WHITE);
	                label.setBackground(Color.BLACK);
	                return label;
	            }
	        });
	        
	        scrollPane.setPreferredSize(new Dimension(350, 160));
	        scrollPane.setBackground(Color.BLACK);
	        
	        table.setBorder(null);
	        scrollPane.setBorder(null);
	        scrollPane.setViewportBorder(null);
	        
	        this.add(scrollPane, this.grid);
	        
	        this.grid.gridy += 1;
	        
	        this.grid.gridx = 0;
	        this.setButton(RETURN_CENTRAL_VISIBILITY_BUTTON_KEY, "Go Back");
	        this.add(this.components.get(RETURN_CENTRAL_VISIBILITY_BUTTON_KEY), grid);
	        
	        this.grid.gridx += 1;
	        this.setButton(ADMIN_ADD_USER_BUTTON_KEY, "Add User");
	        this.add(this.components.get(ADMIN_ADD_USER_BUTTON_KEY), grid);
	        
	        this.grid.gridy += 1;
	        this.grid.gridx = 0;
	        this.setButton(ADMIN_LOGOUT_BUTTON_KEY, "Logout");
	        this.add(this.components.get(ADMIN_LOGOUT_BUTTON_KEY), grid);
	        
	        this.invokeActionListeners();
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void invokeActionListeners() {
		this.addUserButtonActionListener();
        this.logoutButtonActionListener();
        this.returnButtonActionListener();
	}
	
	// --------------------------------------------------------------------------------------
	public void returnButtonActionListener() {
		JButton addUserButton = (JButton) this.components.get(RETURN_CENTRAL_VISIBILITY_BUTTON_KEY);
		
		addUserButton.addActionListener(e -> {
			this.setVisible(false);
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.ADMIN_CENTRAL_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void addUserButtonActionListener() {
		JButton addUserButton = (JButton) this.components.get(ADMIN_ADD_USER_BUTTON_KEY);
		
		addUserButton.addActionListener(e -> {
			this.setVisible(false);
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.ADMIN_ADD_USER_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void clearFields() {
		for(Component component : this.components.values()) {
			if(component instanceof JPasswordField) {
				((JPasswordField) component).setText("");
				Arrays.fill(((JPasswordField) component).getPassword(), '\0');
			}
			
			else if(component instanceof JTextField) {
				((JTextField) component).setText("");
			}
		}
	}
	
	// --------------------------------------------------------------------------------------
	public void adminAddUserButtonListener() {
		JButton adminLogin = (JButton) this.components.get(ADMIN_ADD_USER_BUTTON_KEY);
		
		adminLogin.addActionListener(e -> {
			this.setVisible(false);
			this.clearFields();
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.ADMIN_ADD_USER_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void logoutButtonActionListener() {
		JButton adminLogin = (JButton) this.components.get(ADMIN_LOGOUT_BUTTON_KEY);
		
		adminLogin.addActionListener(e -> {
			this.setVisible(false);
			this.clearFields();
			this.panelCentral.getCurrentPanel().get(PanelCentralEnums.LOGOUT_SUCCESS_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void enablePasswordTogglers() {
		this.toggleEnterPasswordVisibility();
        this.toggleConfirmPasswordVisibility();
	}
	
	// --------------------------------------------------------------------------------------
	public void toggleEnterPasswordVisibility() {
		JButton toggleButton = (JButton) this.components.get(ENTERPASSWORD_VISIBILITY_BUTTON_KEY);
		
		toggleButton.addActionListener( e -> {
			JPasswordField enterPasswordTextField = (JPasswordField) this.components.get(ENTERPASSWORD_TEXTFIELD_KEY);
			this.togglePasswordVisibility.passwordToggler(enterPasswordTextField);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void toggleConfirmPasswordVisibility() {
		JButton toggleButton = (JButton) this.components.get(CONFIRMPASSWORD_VISIBILITY_BUTTON_KEY);
		
		toggleButton.addActionListener( e -> {
			JPasswordField confirmPasswordTextField = (JPasswordField) this.components.get(CONFIRMPASSWORDPASSWORD_TEXTFIELD_KEY);
			this.togglePasswordVisibility.passwordToggler(confirmPasswordTextField);
		});
	}
	
	// --------------------------------------------------------------------------------------
	public void setComponentMainPosition() {
		this.grid.insets = new Insets(2, 2, 2, 2);
        this.grid.gridx = GRID_X_INITIAL;
        this.grid.gridy = GRID_Y_INITIAL;
	}
	
	// --------------------------------------------------------------------------------------
	public void setBackgroundImageConstraints() {
		// Set everything to initial status.
		this.grid = new GridBagConstraints(); // Set the gridbag constraints
        this.grid.fill = GridBagConstraints.BOTH; // Fill both vertically and horizontally
        this.grid.gridx = GRID_X_INITIAL;
        this.grid.gridx = GRID_Y_INITIAL;
        this.grid.weightx = GRIDX_IMAGEWEIGHT; // Value is 0: initial
        this.grid.weighty = GRIDY_IMAGEWEIGHT; // Value is 0: initial
        this.grid.insets = new Insets(TOP_INSET, LEFT_INSET, BOTTOM_INSET, RIGHT_INSET); // Insets values all 0 (Initial)
	}
	
	// --------------------------------------------------------------------------------------
	public void setButton(String buttonKey, String buttonText) {
		JButton button = new JButton(buttonText);
        this.grid.anchor = GridBagConstraints.CENTER;
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        this.grid.gridwidth = GridBagConstraints.REMAINDER;
        this.grid.fill = GridBagConstraints.NONE;
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        this.grid.insets = new Insets(TOP_INSET, LEFT_INSET, BOTTOM_INSET, 5);
        
        this.components.put(buttonKey, button);
	}
	
	// --------------------------------------------------------------------------------------
	public void setPasswordField(String passwordFieldKey) {
		JPasswordField passwordField = new JPasswordField(TEXTFIELD_SIZE);
		this.grid.anchor = GridBagConstraints.CENTER;
		passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
		passwordField.setBackground(Color.WHITE);
		passwordField.setForeground(Color.BLACK);
		this.grid.gridwidth = 2;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.components.put(passwordFieldKey, passwordField);
	}
	
	// --------------------------------------------------------------------------------------
	public void setLabelField(String labelKey, String labelText) {
		JLabel labelField = new JLabel(labelText);
		this.grid.anchor = GridBagConstraints.CENTER;
		this.grid.fill = GridBagConstraints.HORIZONTAL;
		
		this.grid.gridwidth = 5;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;
        
        this.grid.insets = new Insets(0, 0, 5, 0);
        
		//labelField.setBackground(Color.BLACK);
		labelField.setForeground(Color.WHITE);
		//labelField.setOpaque(true);
        
        this.components.put(labelKey, labelField);
	}
	
	@Override
	public void paintComponent(Graphics g) {
	    
		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
	}
}
