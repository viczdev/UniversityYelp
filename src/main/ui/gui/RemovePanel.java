package ui.gui;

import java.awt.event.ActionEvent;

// Represent panel that ask user select restaurant to remove
public class RemovePanel extends DetailPanel {

    public RemovePanel(Layout mainLayoutClass) {
        super(mainLayoutClass);
        submitButton.setText("Remove");
    }

    /*
     * EFFECTS: add action events to buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getSource().equals(submitButton)) {
            int index = resSelList.getSelectedIndex();
            mainLayoutClass.removeRestaurant(index);
            mainLayout.show(mainPanels, "menu");
        }
    }
}
