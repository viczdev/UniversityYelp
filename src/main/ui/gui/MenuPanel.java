package ui.gui;

import model.Event;
import model.EventLog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represent menu panel
public class MenuPanel extends JPanel implements ActionListener {
    private static final String PHOTO_STORE = "./src/UBCFullSignatureBlue.png";
    private JButton loadButton;
    private JButton saveButton;
    private JButton viewButton;
    private JButton addResButton;
    private JButton removeResButton;
    private JButton detailButton;
    private JButton ratingButton;
    private JButton commentButton;
    private JButton quitButton;
    private Layout layoutClass;

    /*
     * MODIFIES: this
     * EFFECTS: initialize menuPanel
     */
    public MenuPanel(Layout mainLayOut) {
        setPhoto();
        setButtons();
        this.layoutClass = mainLayOut;
        setActionListener();
    }

    private void setPhoto() {
        ImageIcon ubcLogo = new ImageIcon(PHOTO_STORE);
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(ubcLogo);
        this.add(logoLabel);
    }

    private void setButtons() {
        loadButton = new JButton("Load Data");
        saveButton = new JButton("Save Data");
        viewButton = new JButton("View Restaurant List");
        addResButton = new JButton("Add Restaurants");
        removeResButton = new JButton("Remove Restaurants");
        detailButton = new JButton("View Details");
        ratingButton = new JButton("Make Ratings");
        commentButton = new JButton("Make Comments");
        quitButton = new JButton("Quit");
        this.add(loadButton);
        this.add(saveButton);
        this.add(viewButton);
        this.add(addResButton);
        this.add(removeResButton);
        this.add(detailButton);
        this.add(ratingButton);
        this.add(commentButton);
        this.add(quitButton);
    }

    private void setActionListener() {
        loadButton.addActionListener(this);
        saveButton.addActionListener(this);
        viewButton.addActionListener(this);
        addResButton.addActionListener(this);
        detailButton.addActionListener(this);
        removeResButton.addActionListener(this);
        quitButton.addActionListener(this);
    }

    public void closeApp() {
        for (Event evt: EventLog.getInstance()) {
            System.out.println(evt.toString());
        }
        System.exit(0);
    }

    /*
     * MODIFIES: this
     * EFFECTS: add action events to buttons
     */
    @Override
    @SuppressWarnings("methodlength")
    public void actionPerformed(ActionEvent e) {
        JPanel mainPanels = (JPanel) this.getParent();
        CardLayout mainLayout = (CardLayout) mainPanels.getLayout();
        if (e.getSource().equals(loadButton)) {
            layoutClass.loadRestaurantList();
        } else if (e.getSource().equals(saveButton)) {
            layoutClass.saveRestaurantList();
        } else if (e.getSource().equals(viewButton)) {
            layoutClass.updateViewLabelAll();
            mainLayout.show(mainPanels, "view");
        } else if (e.getSource().equals(addResButton)) {
            mainLayout.show(mainPanels, "add");
        } else if (e.getSource().equals(detailButton)) {
            if (layoutClass.getRestaurantListSize() < 1) {
                mainLayout.show(mainPanels, "view");
            } else {
                layoutClass.setDetailPages();
                mainLayout.show(mainPanels, "detail");
            }
        } else if (e.getSource().equals(removeResButton)) {
            if (layoutClass.getRestaurantListSize() < 1) {
                mainLayout.show(mainPanels, "view");
            } else {
                layoutClass.setRemovePages();
                mainLayout.show(mainPanels, "remove");
            }
        } else if (e.getSource().equals(quitButton)) {
            closeApp();
        }
    }
}
