package ui.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represent the panel template
public class PanelTemplate extends JPanel implements ActionListener {

    protected JButton backButton;
    protected JPanel mainPanels;
    protected CardLayout mainLayout;
    protected Layout mainLayoutClass;
    protected SpringLayout innerPanelLayout;

    /*
     * MODIFIES: this
     * EFFECTS: initialize PanelTemplate
     */
    public PanelTemplate(Layout mainLayoutClass) {
        this(mainLayoutClass,"Back");
    }

    /*
     * MODIFIES: this
     * EFFECTS: initialize PanelTemplate
     */
    public PanelTemplate(Layout mainLayoutClass, String buttonName) {
        backButton = new JButton(buttonName);
        this.add(backButton);
        backButton.addActionListener(this);
        this.mainLayoutClass = mainLayoutClass;
        innerPanelLayout = new SpringLayout();
        this.setLayout(innerPanelLayout);
    }

    /*
     * MODIFIES: this
     * EFFECTS: set the position of given component
     */
    public void setComponentLoc(Component cpt, int x, int y) {
        innerPanelLayout.putConstraint(SpringLayout.WEST, cpt, x, SpringLayout.WEST, this);
        innerPanelLayout.putConstraint(SpringLayout.NORTH, cpt, y, SpringLayout.NORTH, this);
    }

    /*
     * MODIFIES: this
     * EFFECTS: set the location of back button
     */
    public void setBackButton(int x, int y) {
        setComponentLoc(backButton, x, y);
    }

    /*
     * EFFECTS: add action events to buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        mainPanels = (JPanel) this.getParent();
        mainLayout = (CardLayout) mainPanels.getLayout();
        if (e.getSource().equals(backButton)) {
            mainLayout.show(mainPanels, "menu");
        }
    }
}
