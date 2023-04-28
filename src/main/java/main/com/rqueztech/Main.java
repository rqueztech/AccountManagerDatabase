package main.com.rqueztech;

import main.com.rqueztech.ui.BaseFrame;
import main.com.rqueztech.ui.PanelCentral;
/**
 * The Main class is the entry point for the application. It creates a new
 * instance of a BaseFrameand passes it to a new instance of a PanelCentral,
 * which is responsible for displaying the main UI of the application.
 */

public class Main {
  /**
     * The main method is the entry point for the application. It creates a
     * new instance of a BaseFrame and passes it to a new instance of a
     * PanelCentral, which is responsible for displaying the main UI of the
     * application.
     *
     * @param args The command-line arguments passed to the application
     */
  public static void main(String[] args) {
    // TODO Auto-generated method stub
    BaseFrame baseFrame = new BaseFrame();
    new PanelCentral(baseFrame);
  }
}