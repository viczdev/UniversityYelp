package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represent a list of Restaurant
public class RestaurantList implements Writable {

    private ArrayList<Restaurant> restaurantList;

    /*
     * MODIFIES: this
     * EFFECTS: creates empty restaurant array list
     */
    public RestaurantList() {
        restaurantList = new ArrayList<>();
    }

    /*
     * EFFECTS: returns the number of restaurant in the restaurant list
     */
    public Integer getSize() {
        return restaurantList.size();
    }

    /*
     * EFFECTS: returns true if the given restaurant name is in the restaurant list, otherwise returns false
     */
    public boolean contains(Restaurant res) {
        for (Restaurant r: restaurantList) {
            if (r.equals(res)) {
                return true;
            }
        }
        return false;
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds new restaurant to the restaurant list if the list does not contain it
     */
    public void addRestaurant(Restaurant newRest) {
        if (!contains(newRest)) {
            restaurantList.add(newRest);
            EventLog.getInstance().logEvent(new Event(newRest.getName() + " Restaurant Added"));
        }
    }

    public Restaurant getRestaurant(int index) {
        return restaurantList.get(index);
    }

    /*
     * MODIFIES: this
     * EFFECTS: removes restaurant from the restaurant list by its index
     */
    public void remove(int index) {
        EventLog.getInstance().logEvent(new Event(restaurantList.get(index).getName() + " Restaurant Removed"));
        restaurantList.remove(index);
    }

    /*
     * EFFECTS: returns a string representation of restaurant list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < restaurantList.size(); x++) {
            int num = x + 1;
            sb.append(num + ". " + restaurantList.get(x).toString() + "\n");
        }
        return sb.toString();
    }

    // EFFECTS: convert restaurantList to json
    @Override
    public JSONObject toJson() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("RestaurantList", restaurantListToJson());
        return jsonObj;
    }

    // EFFECTS: convert restaurantList to json
    public JSONArray restaurantListToJson() {
        JSONArray jsonArray = new JSONArray();
        for (Restaurant r: restaurantList) {
            jsonArray.put(r.toJson());
        }
        return jsonArray;
    }
}
