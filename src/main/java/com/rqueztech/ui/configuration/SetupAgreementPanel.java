package com.rqueztech.ui.configuration;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import com.rqueztech.controllers.configuration.SetupAgreementController;
import com.rqueztech.ui.BaseFrame;
import com.rqueztech.ui.PanelCentral;
import com.rqueztech.ui.buttons.ButtonTemplates;

public class SetupAgreementPanel extends JPanel {

	// --- Group 1: Panel related variables ---
	private static final long serialVersionUID = 1151818027338195157L;
	private Image image;
	private GridBagConstraints grid;

	private final int TOP_INSET = 0;
	private final int LEFT_INSET = 0;
	private final int BOTTOM_INSET = 0;
	private final int RIGHT_INSET = 0;

	// --- Section 3: Login Button Component Keys
	private final String SETUPAGREEMENT_BUTTON_KEY = "SETUPAGREEMENT_BUTTON_KEY";
	private final String TEXTPANE = "TEXTPANE";

	private final int GRID_X_INITIAL = 0;
	private final int GRID_Y_INITIAL = 0;

	private final int GRIDX_IMAGEWEIGHT = 1;
	private final int GRIDY_IMAGEWEIGHT = 1;

	private PanelCentral panelCentral;

	// --- Group 2: Panel Map ---
	private ConcurrentHashMap<String, JComponent> components;
	private SetupAgreementController setupAgreementController;

	// ------------------------------------------------------------------------
	public SetupAgreementPanel(BaseFrame frame, GridBagLayout layout, PanelCentral panelCentral) {
		this.panelCentral = panelCentral;

		// Dispatch responsibilities on EDT.
		SwingUtilities.invokeLater(() -> {

			// Set the panel to the gridbaglayout, establish the preferred size,
			// And get the image that will be used in the background
			this.setLayout(layout);
	        this.setPreferredSize(new Dimension(frame.getHeight(), frame.getWidth()));
	        this.image = new ImageIcon(getClass().getResource("/images/backgroundd.jpg")).getImage();
	        this.components = new ConcurrentHashMap<String, JComponent> ();

	        // --- Start Constraints ---
	        // Set all of the constraints for the background image
	        this.setBackgroundImageConstraints();
	        frame.add(this, this.grid);
	        //--- Finish Constraints End ---

	        this.setComponentMainPosition();
	        this.setTextPane(TEXTPANE, "Welcome to the database. Click start configuration to begin the configuraiton.");
	        this.add(this.components.get(TEXTPANE));

	        this.grid.gridx = 0;
	        this.grid.gridy += 5;
	        this.setButton(SETUPAGREEMENT_BUTTON_KEY, "Start Configuration");
	        this.add(this.components.get(SETUPAGREEMENT_BUTTON_KEY), grid);

	        this.setupAgreementController = new SetupAgreementController(this);
		});
	}

	// ------------------------------------------------------------------------
	public ConcurrentHashMap<String, JComponent> getComponentsMap() {
		return this.components;
	}

	// ------------------------------------------------------------------------
	private void setComponentMainPosition() {
		this.grid.insets = new Insets(2, 2, 2, 2);
        this.grid.gridx = GRID_X_INITIAL;
        this.grid.gridy = GRID_Y_INITIAL;
	}

	// ------------------------------------------------------------------------
	private void setBackgroundImageConstraints() {
		// Set everything to initial status.
		this.grid = new GridBagConstraints(); // Set the gridbag constraints
        this.grid.fill = GridBagConstraints.BOTH; // Fill both vertically and horizontally
        this.grid.gridx = GRID_X_INITIAL;
        this.grid.gridx = GRID_Y_INITIAL;
        this.grid.weightx = GRIDX_IMAGEWEIGHT; // Value is 0: initial
        this.grid.weighty = GRIDY_IMAGEWEIGHT; // Value is 0: initial
        this.grid.insets = new Insets(TOP_INSET, LEFT_INSET, BOTTOM_INSET, RIGHT_INSET); // Insets values all 0 (Initial)
	}

	// ------------------------------------------------------------------------
	private void setButton(String buttonKey, String buttonText) {
		ButtonTemplates button = new ButtonTemplates(buttonText, Color.BLACK, Color.WHITE);

		this.grid.anchor = GridBagConstraints.CENTER;
        this.grid.fill = GridBagConstraints.NONE;
        this.grid.gridwidth = 1;
        this.grid.weightx = 0.0;
        this.grid.weighty = 0.0;

        this.components.put(buttonKey, button);
	}

	// ------------------------------------------------------------------------
	public PanelCentral getPanelCentral() {
		return this.panelCentral;
	}

	// ------------------------------------------------------------------------
	private void setTextPane(String textPaneKey, String textPaneMessage) {
	    JTextPane textPane = new JTextPane();

	    StyledDocument doc = textPane.getStyledDocument();
	    SimpleAttributeSet center = new SimpleAttributeSet();
	    StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
	    doc.setParagraphAttributes(0, doc.getLength(), center, false);

	    this.grid.anchor = GridBagConstraints.CENTER;
	    this.grid.fill = GridBagConstraints.NONE;

	    this.grid.gridwidth = 3;
	    this.grid.gridheight = 3;

	    textPane.setPreferredSize(new Dimension(200, 120));
	    textPane.setMargin(new Insets(0, 0, 0, 0));
	    textPane.setEnabled(false);

	    textPane.setBorder(getBorder());
	    textPane.setText(textPaneMessage);

	    textPane.setBackground(Color.BLACK);
	    textPane.setForeground(Color.WHITE);
	    textPane.setOpaque(true);

	    Font font = textPane.getFont(); // get the current font
	    float size = font.getSize() + 4.0f; // increase the font size by 2
	    font = font.deriveFont(Font.BOLD, size);
	    textPane.setFont(font); // set the new font size

	    Border border = BorderFactory.createLineBorder(Color.WHITE, 1);
	    textPane.setBorder(border);

	    this.components.put(textPaneKey, textPane);
	}

	@Override
	public void paintComponent(Graphics g) {

		Graphics2D g2d = (Graphics2D)g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(this.image, 0, 0, getWidth(), getHeight(), null);
	}
}
