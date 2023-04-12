package com.rqueztech.controllers.admin;

import java.awt.Component;
import java.util.Arrays;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.jar.JarOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.rqueztech.models.user.UserModel;
import com.rqueztech.swingworkers.admin.AdminAddUserWorker;
import com.rqueztech.ui.admin.AdminAddUserPanel;
import com.rqueztech.ui.admin.enums.AdminAddUserEnums;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.events.AddUserDocumentListener;
import com.rqueztech.ui.events.PasswordFieldListener;
import com.rqueztech.ui.events.TextFieldListener;
import com.rqueztech.ui.events.TogglePasswordVisibility;
import com.rqueztech.ui.validation.InputValidations;

public class AdminAddUserController {
	
	private AdminAddUserPanel adminAddUserPanel;
	private TogglePasswordVisibility togglePasswordVisibility;
	private ConcurrentHashMap <AdminAddUserEnums, JComponent> components;
	
	private InputValidations inputValidations;
	
	private JComboBox <String> gender;
	
	private String userFirstName;
	private String userLastName;
	
	public AdminAddUserController(AdminAddUserPanel adminAddUserPanel, JComboBox<String> gender) {
		this.togglePasswordVisibility = new TogglePasswordVisibility();
		this.inputValidations = new InputValidations(); 
		
		this.adminAddUserPanel = adminAddUserPanel;
		this.components = adminAddUserPanel.getComponentsMap();
		this.gender = gender;
		
		this.invokeActionListeners();
        this.invokeDocumentListeners();
        this.enablePasswordTogglers();
	}
	
	// --------------------------------------------------------------------------------------
	private void invokeActionListeners() {
		this.userAddUserButtonListener();
        this.cancelButtonActionListener();
	}
	
	// --------------------------------------------------------------------------------------
	private void invokeDocumentListeners() {
		this.firstNameListener();
		this.lastNameListener();
		this.passphraseNameListener();
		this.addUserButtonListener();
	}
	
	// --------------------------------------------------------------------------------------
	private void userAddUserButtonListener() {
		JButton addUserButton = (JButton) this.adminAddUserPanel.getComponentsMap().get(AdminAddUserEnums.ADD_USER_BUTTON_KEY);
		
		addUserButton.addActionListener(e -> {
			// NOTE: Creation of the default user password is automatic. No User Password field
			// Gets passed here.
			
			String gender = (String) this.gender.getSelectedItem();
			String userFirstName = ((JTextField) this.components.get(AdminAddUserEnums.FIRSTNAME_TEXTFIELD_KEY)).getText(); 
			String userLastName =  ((JTextField) this.components.get(AdminAddUserEnums.LASTNAME_TEXTFIELD_KEY)).getText();
			
			this.adminAddUserPanel.setVisible(false);
			this.adminAddUserPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.ADMIN_USER_VIEW_PANEL).setVisible(true);
			
			AdminAddUserWorker adminAddUserWorker =
					new AdminAddUserWorker(userFirstName, userLastName, gender) {
				
				@Override
				protected void done() {
				    try {
				        // Get the result of the SwingWorker's background task
				        UserModel result = get();
				        
				        // Handle the successful completion of the task here
				        String message = "User added successfully.\n"
				        		+ result.getUserName() + "\n"
				        		+ result.getUserFirstName() + "\n"
				        		+ result.getUserLastName() + "\n"
				        		+ result.getGender() + "\n"
				        		+ result.getUserPassword() + "\n"
				        		+ result.getUserLastName() + "\n"
				        		+ result.getUserNumber();
				        JOptionPane.showMessageDialog(null, message);
				        
				    } catch (InterruptedException | ExecutionException ex) {
				        // Handle any exceptions that were thrown during the background task here
				        String errorMessage = "Error: " + ex.getMessage();
				        JOptionPane.showMessageDialog(null, errorMessage, "Error", JOptionPane.ERROR_MESSAGE);
				    } catch (CancellationException ex) {
				        // Handle cancellation of the background task here
				        String message = "The task was cancelled.";
				        JOptionPane.showMessageDialog(null, message);
				    }
				}

			};
			
			this.resetInstanceFields();
			this.resetFields();
			
			adminAddUserWorker.execute();
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void resetInstanceFields() {
		this.userFirstName = "";
		this.userLastName = "";
	}
	
	// --------------------------------------------------------------------------------------
	private void resetFields() {
		for(Component component : this.adminAddUserPanel.getComponentsMap().values()) {
			if(component instanceof JPasswordField) {
				((JPasswordField) component).setText("");
				Arrays.fill(((JPasswordField) component).getPassword(), '\0');
			}
			
			else if(component instanceof JTextField) {
				((JTextField) component).setText("");
			}
		}
		
		this.adminAddUserPanel.getGender().setSelectedIndex(0);
	}
	
	// --------------------------------------------------------------------------------------
	private void cancelButtonActionListener() {
		JButton adminLogin = (JButton) this.adminAddUserPanel.getComponentsMap().get(AdminAddUserEnums.CANCEL_BUTTON_KEY);
		
		adminLogin.addActionListener(e -> {
			this.adminAddUserPanel.setVisible(false);
			this.resetFields();
			
			// Take the user back to the Admin User View Panel
			this.adminAddUserPanel.getPanelCentral().getCurrentPanel().get(PanelCentralEnums.ADMIN_USER_VIEW_PANEL).setVisible(true);
		});
	}
	
	// --------------------------------------------------------------------------------------
	private void lastNameListener() {
		JTextField userLastName = (JTextField) this.adminAddUserPanel.getComponentsMap().get(AdminAddUserEnums.LASTNAME_TEXTFIELD_KEY);
		
		// Listener for the last name field
		TextFieldListener lastNameFieldListener = 
				new TextFieldListener(userLastName);
		
		userLastName.getDocument().addDocumentListener(lastNameFieldListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void addUserButtonListener() {
		JTextField userFirstName = (JTextField) this.adminAddUserPanel.getComponentsMap().get(AdminAddUserEnums.FIRSTNAME_TEXTFIELD_KEY);
		JTextField userLastName = (JTextField) this.adminAddUserPanel.getComponentsMap().get(AdminAddUserEnums.LASTNAME_TEXTFIELD_KEY);
		JPasswordField passphrase = (JPasswordField) this.adminAddUserPanel.getComponentsMap().get(AdminAddUserEnums.PASSPHRASE_TEXTFIELD_KEY);
		
		JButton addUserButton = (JButton) this.adminAddUserPanel.getComponentsMap().get(AdminAddUserEnums.ADD_USER_BUTTON_KEY);
		
		AddUserDocumentListener addUserDocumentListener = 
				new AddUserDocumentListener(addUserButton, userFirstName, userLastName,
						passphrase, this.adminAddUserPanel.getGender());
		
		userFirstName.getDocument().addDocumentListener(addUserDocumentListener);
		userLastName.getDocument().addDocumentListener(addUserDocumentListener);
		passphrase.getDocument().addDocumentListener(addUserDocumentListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void firstNameListener() {
		JTextField userFirstName = (JTextField) this.adminAddUserPanel.getComponentsMap().get(AdminAddUserEnums.FIRSTNAME_TEXTFIELD_KEY);
		
		// Create a listener for the first name field
		TextFieldListener nameFieldListener = 
				new TextFieldListener(userFirstName);
		
		userFirstName.getDocument().addDocumentListener(nameFieldListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void passphraseNameListener() {
		JPasswordField passphrase = (JPasswordField) this.adminAddUserPanel.getComponentsMap().get(AdminAddUserEnums.PASSPHRASE_TEXTFIELD_KEY);
		// Listener for the last name field
		PasswordFieldListener passwordFieldListener = 
				new PasswordFieldListener(passphrase);

		passphrase.getDocument().addDocumentListener(passwordFieldListener);
	}
	
	// --------------------------------------------------------------------------------------
	private void enablePasswordTogglers() {
		this.toggleEnterPasswordVisibility();
	}
	
	// --------------------------------------------------------------------------------------
	private void toggleEnterPasswordVisibility() {
		JButton toggleButton = (JButton) this.adminAddUserPanel.getComponentsMap().get(AdminAddUserEnums.PASSPHRASE_VISIBILITY_BUTTON_KEY);
		
		toggleButton.addActionListener( e -> {
			JPasswordField enterPasswordTextField = (JPasswordField) this.adminAddUserPanel.getComponentsMap().get(AdminAddUserEnums.PASSPHRASE_TEXTFIELD_KEY);
			this.togglePasswordVisibility.passwordToggler(enterPasswordTextField);
		});
	}
}
