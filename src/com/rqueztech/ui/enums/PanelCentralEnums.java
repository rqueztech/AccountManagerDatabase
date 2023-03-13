package com.rqueztech.ui.enums;

public enum PanelCentralEnums {
    // --- Group 2: Panel Key Variables ---
    MAIN_LOGIN_PANEL("MAIN_LOGIN_PANEL"),
    DEFAULT_PASSWORD_CHANGE_USER_PANEL("DEFAULT_PASSWORD_CHANGE_USER_PANEL"), 
    USER_CENTRAL_PANEL("USER_CENTRAL_PANEL"), 
    SETUP_AGREEMENT_PANEL("SETUP_AGREEMENT_PANEL"),
    SETUP_CONFIGURATION_PANEL("SETUP_CONFIGURATION_PANEL");

    private final String panelName;

    private PanelCentralEnums(String panelName) {
        this.panelName = panelName;
    }

    public String getPanelName() {
        return panelName;
    }
}