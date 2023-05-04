package main.com.rqueztech.models.admin;

import java.util.HashMap;
import main.com.rqueztech.models.configuration.ConfigurationModel;

/**
 * Holds the hashmap with all of the configuration data.
 */
public class ConfigurationHashMap {
  private HashMap<String, ConfigurationModel> configurationHashMap;

  public HashMap<String, ConfigurationModel> getConfigurationHashMap() {
    return this.configurationHashMap;
  }
}
