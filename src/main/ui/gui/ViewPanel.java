package ui.gui;

import javax.swing.*;

// Represent view panel that shows information of restaurant(s)
public class ViewPanel extends PanelTemplate {

    private JLabel viewLabel;

    /*
     * MODIFIES: this
     * EFFECTS: initialize viewPanel
     */
    public ViewPanel(Layout layoutClass) {
        super(layoutClass);
        viewLabel = new JLabel("No Restaurants in the System yet");
        this.add(viewLabel);
        setLoc();
    }

    public void setViewLabel(String label) {
        viewLabel.setText(label);
    }

    /*
     * MODIFIES: this
     * EFFECTS: set locations of label and button
     */
    private void setLoc() {
        setComponentLoc(viewLabel, 170, 10);
        setComponentLoc(backButton, 190, 200);
    }

}
