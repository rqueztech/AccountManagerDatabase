package main.com.rqueztech.models.panels;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JPanel;
import main.com.rqueztech.ui.enums.PanelCentralEnums;

/**
 * Holds every panel used by the program in a hash map.
 */
public class PanelsHashMap {
  private ConcurrentHashMap<PanelCentralEnums, JPanel> panelsHashMap;
  private Lock lock;

  public PanelsHashMap() {
    this.panelsHashMap = new ConcurrentHashMap<PanelCentralEnums, JPanel>();
    this.lock = new ReentrantLock();
  }

  /**
   * Creates instance of the panel hash map storing the panels.
   *
   * @param panelsHashMap hashmap storing all display panels as a Enum JPanel
   key pair.
   */
  public void setPanelsHashMap(
      ConcurrentHashMap<PanelCentralEnums, JPanel> panelsHashMap) {
    lock.lock();
    try {
      this.panelsHashMap = panelsHashMap;
    } finally {
      lock.unlock();
    }
  }

  /**
   * The class is responsible for returning the panel HashMap.
   *
   * @return getPanelsHashMap as (a ConcurrentHashMap).
   */
  public ConcurrentHashMap<PanelCentralEnums, JPanel> getPanelsHashMap() {
    lock.lock();

    try {
      return this.panelsHashMap;
    } finally {
      lock.unlock();
    }
  }
}
