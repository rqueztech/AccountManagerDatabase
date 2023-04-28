package main.com.rqueztech.ui;

import java.awt.GridBagLayout;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import main.com.rqueztech.ui.admin.AdminAddUserPanel;
import main.com.rqueztech.ui.admin.AdminCentralPanel;
import main.com.rqueztech.ui.admin.AdminUserViewPanel;
import main.com.rqueztech.ui.configuration.SetupAgreementPanel;
import main.com.rqueztech.ui.configuration.SetupConfigurationPanel;
import main.com.rqueztech.ui.enums.PanelCentralEnums;
import main.com.rqueztech.ui.user.UserCentralPanel;
import main.com.rqueztech.ui.user.UserChangeDefaultPasswordPanel;

class PanelCreationWorker extends
    SwingWorker<ConcurrentHashMap<PanelCentralEnums, JPanel>, Void> {

  private PanelCentral panelCentral;
  private BaseFrame frame;
  private ConcurrentHashMap<PanelCentralEnums, JPanel> panels;

  private final String filePath;
  private File file;

  public PanelCreationWorker(PanelCentral panelCentral, BaseFrame frame,
      ConcurrentHashMap<PanelCentralEnums, JPanel> panels) {
    // TODO Auto-generated constructor stub
    this.panelCentral = panelCentral;
    this.frame = frame;
    this.panels = panels;
    
    this.filePath = "src/main/resources/data/admindatabase.csv";
    this.file = new File(filePath);
  }

  @Override
  protected ConcurrentHashMap<PanelCentralEnums, JPanel> doInBackground() throws Exception {
    // TODO Auto-generated method stub

    this.panels.put(PanelCentralEnums.MAINLOGINPANEL, new MainLoginPanel(
        this.frame, new GridBagLayout(), this.panelCentral));

    this.panels.put(PanelCentralEnums.USERCHANGEDEFAULTPASSWORDPANEL,
        new UserChangeDefaultPasswordPanel(this.frame,
          new GridBagLayout(), this.panelCentral));

    this.panels.put(PanelCentralEnums.USERCENTRALPANEL,
        new UserCentralPanel(this.frame,
          new GridBagLayout(), this.panelCentral));
    this.panels.put(PanelCentralEnums.SETUPAGREEMENTPANEL,
        new SetupAgreementPanel(this.frame,
          new GridBagLayout(), this.panelCentral));

    this.panels.put(PanelCentralEnums.SETUPCONFIGURATIONPANEL,
        new SetupConfigurationPanel(this.frame,
          new GridBagLayout(), this.panelCentral));

    this.panels.put(PanelCentralEnums.LOGOUTSUCCESSPANEL,
        new LogoutSuccessPanel(this.frame,
           new GridBagLayout(), this.panelCentral));

    this.panels.put(PanelCentralEnums.ADMINCENTRALPANEL,
        new AdminCentralPanel(this.frame,
           new GridBagLayout(), this.panelCentral));

    this.panels.put(PanelCentralEnums.ADMINADDUSERPANEL,
        new AdminAddUserPanel(this.frame,
           new GridBagLayout(), this.panelCentral));

    this.panels.put(PanelCentralEnums.ADMINUSERVIEWPANEL,
        new AdminUserViewPanel(this.frame,
           new GridBagLayout(), this.panelCentral));

    this.clearPanels();

    return this.panels;
  }

  public void clearPanels() {
    for (JPanel panel : panels.values()) {
      panel.setVisible(false);
    }
  }
  
  @Override
  protected void done() {
    // TODO Auto-generated method stub
    super.done();

    try {
      get();

      for (ConcurrentHashMap.Entry<PanelCentralEnums, JPanel> entry : this.panels.entrySet()) {
        this.panelCentral.setConcurrentHashMap(entry.getKey(), entry.getValue());
      }

      if (this.file.exists()) {
        this.panels.get(PanelCentralEnums.SETUPAGREEMENTPANEL).setVisible(true);
      } else {
        this.panels.get(PanelCentralEnums.MAINLOGINPANEL).setVisible(true);
      }

      frame.setVisible(true);

    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}