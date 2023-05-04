/**
This class represents the central panel containing all the other panels
using CardLayout. It extends JFrame and contains a ConcurrentHashMap of
all the panels as well as an image to be used as the background.
*/

package main.com.rqueztech.ui;

import java.awt.GridBagLayout;
import java.io.File;
import java.util.concurrent.ConcurrentHashMap;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import main.com.rqueztech.models.panels.PanelsHashMap;
import main.com.rqueztech.ui.admin.AdminAddUserPanel;
import main.com.rqueztech.ui.admin.AdminCentralPanel;
import main.com.rqueztech.ui.admin.AdminUserViewPanel;
import main.com.rqueztech.ui.configuration.SetupAgreementPanel;
import main.com.rqueztech.ui.configuration.SetupConfigurationPanel;
import main.com.rqueztech.ui.enums.PanelCentralEnums;
import main.com.rqueztech.ui.user.UserCentralPanel;
import main.com.rqueztech.ui.user.UserChangeDefaultPasswordPanel;

/**
 * Contains a hashmap of all panels and calls the panel creation swing worker
 to create the panels.
 *
 * @author quest
 *
 */
public class PanelCentral extends JPanel {

  private static final long serialVersionUID = -652692111395861275L;

  private final String fileLocation = "src/main/resources/data/admindatabase.csv";
  private File file = new File(fileLocation);

  // Reference to the BaseFrame objects
  private final PanelsHashMap panelsHashMap;

  /**
   * Creates a new instance of the PanelCentral passing the BaseFrame class as
   an argument. 
   *
   * @param frame contains the main frame for the program as (BaseFrame object)
   */
  public PanelCentral(BaseFrame frame) {
    this.panelsHashMap = new PanelsHashMap();

    SwingUtilities.invokeLater(() -> {
      // Create all panels and add them to the card layout

      this.panelsHashMap.getPanelsHashMap().put(
          PanelCentralEnums.MAINLOGINPANEL,
             new MainLoginPanel(frame,
             new GridBagLayout(), this));

      this.panelsHashMap.getPanelsHashMap().put(
          PanelCentralEnums.USERCHANGEDEFAULTPASSWORDPANEL,
             new UserChangeDefaultPasswordPanel(frame,
             new GridBagLayout(), this));

      panelsHashMap.getPanelsHashMap().put(
          PanelCentralEnums.USERCENTRALPANEL,
             new UserCentralPanel(frame,
             new GridBagLayout(), this));

      panelsHashMap.getPanelsHashMap().put(
          PanelCentralEnums.SETUPAGREEMENTPANEL,
             new SetupAgreementPanel(frame,
             new GridBagLayout(), this));

      panelsHashMap.getPanelsHashMap().put(
          PanelCentralEnums.SETUPCONFIGURATIONPANEL,
             new SetupConfigurationPanel(frame,
             new GridBagLayout(), this));

      panelsHashMap.getPanelsHashMap().put(
          PanelCentralEnums.LOGOUTSUCCESSPANEL,
            new LogoutSuccessPanel(frame,
            new GridBagLayout(), this));

      panelsHashMap.getPanelsHashMap().put(
          PanelCentralEnums.ADMINCENTRALPANEL,
            new AdminCentralPanel(frame,
            new GridBagLayout(), this));

      panelsHashMap.getPanelsHashMap().put(
          PanelCentralEnums.LOGININCORRECTERRORPANEL,
            new LoginFailPanel(frame,
            new GridBagLayout(), this));

      panelsHashMap.getPanelsHashMap().put(
          PanelCentralEnums.ADMINADDUSERPANEL,
            new AdminAddUserPanel(frame,
            new GridBagLayout(), this));

      panelsHashMap.getPanelsHashMap().put(
          PanelCentralEnums.ADMINUSERVIEWPANEL,
            new AdminUserViewPanel(frame,
            new GridBagLayout(), this));


      for (JPanel panel : panelsHashMap.getPanelsHashMap().values()) {
        frame.add(panel);
      }

      for (JPanel panel : panelsHashMap.getPanelsHashMap().values()) {
        panel.setVisible(false);
      }

      if (file.exists()) {
        panelsHashMap.getPanelsHashMap().get(
            PanelCentralEnums.MAINLOGINPANEL).setVisible(true);
      } else {
        panelsHashMap.getPanelsHashMap().get(
            PanelCentralEnums.SETUPAGREEMENTPANEL).setVisible(true);
      }

      frame.setVisible(true);

    });
  }

  //
  public ConcurrentHashMap<PanelCentralEnums, JPanel> getCurrentPanel() {
    return this.panelsHashMap.getPanelsHashMap();
  }
}
