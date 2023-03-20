package com.rqueztech.ui;

import java.awt.GridBagLayout;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import com.rqueztech.ui.admin.AdminAddUserPanel;
import com.rqueztech.ui.admin.AdminCentralPanel;
import com.rqueztech.ui.configuration.SetupAgreementPanel;
import com.rqueztech.ui.configuration.SetupConfigurationPanel;
import com.rqueztech.ui.enums.PanelCentralEnums;
import com.rqueztech.ui.user.UserCentralPanel;
import com.rqueztech.ui.user.UserChangeDefaultPasswordPanel;

class PanelCreationWorker extends SwingWorker<ConcurrentHashMap<PanelCentralEnums, JPanel>, Void> {

	PanelCentral panelCentral;
	BaseFrame frame;
	ConcurrentHashMap<PanelCentralEnums, JPanel> panels;
	
	public PanelCreationWorker(PanelCentral panelCentral, BaseFrame frame, ConcurrentHashMap <PanelCentralEnums, JPanel> panels) {
		// TODO Auto-generated constructor stub
		this.panelCentral = panelCentral;
		this.frame = frame;
		this.panels = panels;
	}
	
	@Override
	protected ConcurrentHashMap<PanelCentralEnums, JPanel> doInBackground() throws Exception {
		// TODO Auto-generated method stub
		
		this.panels.put(PanelCentralEnums.MAIN_LOGIN_PANEL, new MainLoginPanel(this.frame, new GridBagLayout(), this.panelCentral));
		this.panels.put(PanelCentralEnums.USER_CHANGE_DEFAULT_PASSWORD_PANEL, new UserChangeDefaultPasswordPanel(this.frame, new GridBagLayout(), this.panelCentral));
		this.panels.put(PanelCentralEnums.USER_CENTRAL_PANEL, new UserCentralPanel(this.frame, new GridBagLayout(), this.panelCentral));
		this.panels.put(PanelCentralEnums.SETUP_AGREEMENT_PANEL, new SetupAgreementPanel(this.frame, new GridBagLayout(), this.panelCentral));
		this.panels.put(PanelCentralEnums.SETUP_CONFIGURATION_PANEL, new SetupConfigurationPanel(this.frame, new GridBagLayout(), this.panelCentral));
		this.panels.put(PanelCentralEnums.LOGOUT_SUCCESS_PANEL, new LogoutSuccessPanel(this.frame, new GridBagLayout(), this.panelCentral));
		this.panels.put(PanelCentralEnums.ADMIN_CENTRAL_PANEL, new AdminCentralPanel(this.frame, new GridBagLayout(), this.panelCentral));
		this.panels.put(PanelCentralEnums.ADMIN_ADD_USER_PANEL, new AdminAddUserPanel(this.frame, new GridBagLayout(), this.panelCentral));
		
		return this.panels;
	}
	
	@Override
	protected void done() {
		// TODO Auto-generated method stub
		super.done();
		
		try {
			ConcurrentHashMap<PanelCentralEnums, JPanel> concurrentHashMap = get();
		
			System.out.println(this.panels);
			for(ConcurrentHashMap.Entry<PanelCentralEnums, JPanel> entry : this.panels.entrySet()) {
				panelCentral.setConcurrentHashMap(entry.getKey(), entry.getValue());
			}
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}