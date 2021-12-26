package ui.gui;

import javafx.scene.control.ComboBox;
import model.FoodType;
import model.Restaurant;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Vector;

// Represent panel that user can add restaurant
public class AddResPanel extends PanelTemplate {
    private static final int X_AXIS = 130;
    private static final int Y_AXIS = 50;
    private JLabel resNameLabel;
    private JLabel resAddressLabel;
    private JLabel resTypeLabel;
    private JTextField resNameText;
    private JTextField resAddressText;
    private JButton addResButton;
    private JComboBox comboBox;

    /*
     * MODIFIES: this
     * EFFECTS: initialize add restaurant panel
     */
    public AddResPanel(Layout layoutClass) {
        super(layoutClass);
        init();
        setLoc();
    }

    private void init() {
        resNameLabel = new JLabel("Restaurant Name:");
        resNameText = new JTextField(18);
        resAddressLabel = new JLabel("Restaurant Address:");
        resAddressText = new JTextField(17);
        addResButton = new JButton("Add");
        resTypeLabel = new JLabel("Restaurant Type:");
        Vector<String> vector = new Vector<>(FoodType.foodTypeList());
        comboBox = new JComboBox(vector);
        this.add(resNameLabel);
        this.add(resNameText);
        this.add(resAddressLabel);
        this.add(resAddressText);
        this.add(addResButton);
        addResButton.addActionListener(this);
        this.add(resTypeLabel);
        this.add(comboBox);
        comboBox.addActionListener(this);
    }

    /*
     * MODIFIES: this
     * EFFECTS: set location of buttons, labels, texts
     */
    private void setLoc() {
        setComponentLoc(resNameLabel, X_AXIS,Y_AXIS + 5);
        setComponentLoc(resNameText, X_AXIS + 115, Y_AXIS);
        setComponentLoc(resAddressLabel, X_AXIS, Y_AXIS + 40);
        setComponentLoc(resAddressText, X_AXIS + 130, Y_AXIS + 35);
        setComponentLoc(resTypeLabel, X_AXIS, Y_AXIS + 75);
        setComponentLoc(comboBox, X_AXIS + 110, Y_AXIS + 72);
        setComponentLoc(backButton, X_AXIS + 200, Y_AXIS + 105);
        setComponentLoc(addResButton, X_AXIS + 280, Y_AXIS + 105);
    }

    /*
     * EFFECTS: add action events to buttons
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        if (e.getSource().equals(addResButton)) {
            String restaurantName = resNameText.getText();
            String restaurantAddress = resAddressText.getText();
            int foodTypeIndex = comboBox.getSelectedIndex();
            Restaurant newRes = new Restaurant(restaurantName, restaurantAddress, FoodType.values()[foodTypeIndex]);
            mainLayoutClass.addRestaurant(newRes);
            mainLayout.show(mainPanels, "menu");
        }
    }
}
