package ui.gui;

import model.Restaurant;
import model.RestaurantList;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

// The main layout of GUI
public class Layout extends JFrame {

    private static final String JSON_STORE = "./data/RestaurantList.json";
    public static final int WIDTH = 600;
    public static final int HEIGHT = 300;
    private JsonReader jsonReader;
    private JsonWriter jsonWriter;
    private RestaurantList restaurantList;
    private JFrame frame;
    private CardLayout cardLayout;
    private Container mainLayoutPanel;
    private MenuPanel menuPanel;
    private ViewPanel viewPanel;
    private AddResPanel addResPanel;
    private DetailPanel detailResPanel;
    private RemovePanel removePanel;
    private RatingPanel ratingPanel;
    private CommentPanel commentPanel;

    /*
     * MODIFIES: this
     * EFFECTS: initialize JFrame
     */
    public Layout() {
        init();
        setPanels();
        setJFrame();
    }

    /*
     * MODIFIES: this
     * EFFECTS: initialize fields
     */
    private void init() {
        frame = new JFrame("YelpUBC");
        cardLayout = new CardLayout();
        mainLayoutPanel = new JPanel();
        jsonReader = new JsonReader(JSON_STORE);
        jsonWriter = new JsonWriter(JSON_STORE);
        mainLayoutPanel.setLayout(cardLayout);
        restaurantList = new RestaurantList();
    }

    /*
     * MODIFIES: this
     * EFFECTS: add panels to main panel
     */
    private void setPanels() {
        menuPanel = new MenuPanel(this);
        viewPanel = new ViewPanel(this);
        addResPanel = new AddResPanel(this);
        detailResPanel = new DetailPanel(this);
        removePanel = new RemovePanel(this);
        ratingPanel = new RatingPanel(this);
        commentPanel = new CommentPanel(this);
        mainLayoutPanel.add(menuPanel, "menu");
        mainLayoutPanel.add(viewPanel, "view");
        mainLayoutPanel.add(addResPanel, "add");
        mainLayoutPanel.add(removePanel, "remove");
        mainLayoutPanel.add(detailResPanel, "detail");
        mainLayoutPanel.add(ratingPanel, "rating");
        mainLayoutPanel.add(commentPanel, "comment");
        cardLayout.show(mainLayoutPanel, "menu");
    }

    /*
     * MODIFIES: this
     * EFFECTS: set Jframe
     */
    private void setJFrame() {
        frame.add(mainLayoutPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                menuPanel.closeApp();
            }
        });
        frame.setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: loads resList from file
    public void loadRestaurantList() {
        try {
            restaurantList = jsonReader.read();
            System.out.println("Loaded RestaurantList info from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

    // EFFECTS: saves resList to file
    public void saveRestaurantList() {
        try {
            jsonWriter.open();
            jsonWriter.write(restaurantList);
            jsonWriter.close();
            System.out.println("Saved restaurants information to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: update label in view panel to the latest restaurant list
     */
    public void updateViewLabelAll() {
        if (restaurantList.getSize() >= 1) {
            viewPanel.setBackButton(390, (getRestaurantListSize() + 1) * 19);
            viewPanel.setViewLabel(buildResListView());
        }
    }

    /*
     * MODIFIES: this
     * EFFECTS: update label in view panel to the detail of selected restaurant
     */
    public void updateViewLabel(int index) {
        viewPanel.setBackButton(160, (6 + restaurantList.getRestaurant(index).getComments().size()) * 19);
        viewPanel.setViewLabel(buildResDetailView(index));
    }

    public void setDetailPages() {
        detailResPanel.setDetailPage();
    }

    public void setRemovePages() {
        removePanel.setDetailPage();
    }

    /*
     * MODIFIES: this
     * EFFECTS: remove selected restaurant from restaurant list
     */
    public void removeRestaurant(int index) {
        restaurantList.remove(index);
    }

    /*
     * MODIFIES: this
     * EFFECTS: add the given restaurant to the restaurant list
     */
    public void addRestaurant(Restaurant r) {
        restaurantList.addRestaurant(r);
    }

    /*
     * EFFECTS: get the current size of restaurant
     */
    public Integer getRestaurantListSize() {
        return this.restaurantList.getSize();
    }

    /*
     * EFFECTS: return detail of selected restaurant
     */
    private String buildResDetailView(int index) {
        Restaurant res = restaurantList.getRestaurant(index);
        StringBuilder sb = new StringBuilder();
        sb.append("<html> Restaurant Detail: <br/>");
        sb.append("Name: " + res.getName() + "<br/>");
        sb.append("Address: " + res.getAddress() + "<br/>");
        sb.append("Food Type: " + res.getFoodType() + "<br/>");
        buildRatingComments(res, sb);
        sb.append("<html/>");
        return sb.toString();
    }

    /*
     * EFFECTS: helper function
     */
    private void buildRatingComments(Restaurant res, StringBuilder sb) {
        if (res.calculateRating() == null) {
            sb.append("Rating: Rating: No rating yet! <br/>");
        } else {
            sb.append("Rating: " + String.format("%.2f", res.calculateRating()) + "<br/>");
        }

        if (res.getComments().size() == 0) {
            sb.append("Customer Comments: No comments yet" + "<br/>");
        } else {
            sb.append("Customer Comments: <br/>");
            int i = 1;
            for (String c : res.getComments()) {
                sb.append(i + ". " + c + "<br/>");
                i++;
            }
        }
    }

    /*
     * EFFECTS: return string represent list of restaurant
     */
    public String buildResListView() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html> Restaurants:<br/>");
        for (int x = 0; x < restaurantList.getSize(); x++) {
            int num = x + 1;
            sb.append(num + ". " + restaurantList.getRestaurant(x).toString() + "<br/>");
        }
        sb.append("<html>");
        return sb.toString();
    }

    /*
     * EFFECTS: return a list represents basic info of restaurant
     */
    public ArrayList<String> getResInfoList() {
        ArrayList<String> resInfoList = new ArrayList<>();
        for (int i = 0; i < restaurantList.getSize(); i++) {
            resInfoList.add((i + 1) + ". " + restaurantList.getRestaurant(i).toString());
        }
        return resInfoList;
    }
}
