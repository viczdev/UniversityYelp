package ui.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;

// Represent panel that shows detail of restaurant
public class DetailPanel extends PanelTemplate {
    protected JButton submitButton;
    private JLabel selDetailLabel;
    protected JList<String> resSelList;
    private JScrollPane listScroller;

    /*
     * MODIFIES: this
     * EFFECTS: initialize detailPanel
     */
    public DetailPanel(Layout mainLayoutClass) {
        super(mainLayoutClass);
        submitButton = new JButton("Submit");
        selDetailLabel = new JLabel("Select the restaurant: ");
        this.add(submitButton);
        this.add(selDetailLabel);
        submitButton.addActionListener(this);
        setLoc();
        resSelList = new JList<>();
        listScroller = new JScrollPane(resSelList);
        this.add(listScroller);

    }

    /*
     * MODIFIES: this
     * EFFECTS: set locations of buttons and labels
     */
    private void setLoc() {
        setComponentLoc(selDetailLabel, 120, 8);
        setComponentLoc(submitButton, 415, 180);
        setComponentLoc(backButton, 335, 180);
    }

    /*
     * MODIFIES: this
     * EFFECTS: set panel content
     */
    public void setDetailPage() {
        this.remove(listScroller);
        resSelList = new JList(mainLayoutClass.getResInfoList().toArray());
        listScroller = new JScrollPane(resSelList);
        this.add(listScroller);
        setComponentLoc(listScroller, 200, 30);
    }

    /*
     * EFFECTS: add action events to buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getSource().equals(submitButton)) {
            int index = resSelList.getSelectedIndex();
            mainLayoutClass.updateViewLabel(index);
            mainLayout.show(mainPanels, "view");
        }
    }
}
