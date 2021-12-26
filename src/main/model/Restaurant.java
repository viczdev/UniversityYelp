package model;

import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Objects;

// Represent a restaurant with name, address, food type, customer rating and comments
public class Restaurant implements Writable {
    private String name;
    private String address;
    private FoodType foodType;
    private ArrayList<Integer> rating;
    private ArrayList<String> comments;

    /*
     * MODIFIES: this
     * EFFECTS: creates a restaurant object with the given name, address and food type
     */
    public Restaurant(String name, String address, FoodType foodType) {
        this.name = name;
        this.address = address;
        this.foodType = foodType;
        this.rating = new ArrayList<>();
        this.comments = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public FoodType getFoodType() {
        return foodType;
    }

    public ArrayList<String> getComments() {
        return comments;
    }

    /*
     * EFFECTS: calculates and returns the average rating in the rating ArrayList, return null if empty
     */
    public Double calculateRating() {
        if (this.rating.size() == 0) {
            return null;
        } else {
            Integer totalRating = 0;
            for (Integer x : this.rating) {
                totalRating += x;
            }
            return (double) totalRating / this.rating.size();
        }
    }

    /*
     * REQUIRES: userRating must be integer between 0 - 5
     * MODIFIES: this
     * EFFECTS: adds a rating to the rating list
     */
    public void makeRating(int userRating) {
        rating.add(userRating);
    }

    /*
     * REQUIRES: userComment is not empty String
     * MODIFIES: this
     * EFFECTS: adds a comment to the comments list
     */
    public void makeComment(String userComment) {
        comments.add(userComment);
    }

    /*
     * EFFECTS: returns a string representation of restaurant
     */
    @Override
    public String toString() {
        if (calculateRating() == null) {
            return this.name + ", " + this.foodType + ", " + "No rating yet";
        } else {
            return this.name + ", " + this.foodType + ", " + String.format("%.2f", calculateRating());
        }

    }

    // EFFECTS: convert restaurant to json
    @Override
    public JSONObject toJson() {
        JSONObject jsonObj = new JSONObject();
        jsonObj.put("name", name);
        jsonObj.put("address", address);
        jsonObj.put("foodType", foodType);
        jsonObj.put("rating", rating);
        jsonObj.put("comments", comments);
        return jsonObj;
    }

    // EFFECTS: compare objects
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Restaurant that = (Restaurant) o;
        return name.equals(that.name);
    }

    // EFFECTS: generate hashcode for object
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
