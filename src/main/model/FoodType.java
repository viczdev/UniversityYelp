package model;

import java.util.ArrayList;
import java.util.List;

// Represent FoodType
public enum FoodType {
    CHINESE("Chinese"),
    JAPANESE("Japanese"),
    PIZZA("Pizza"),
    MIDDLEEASTERNFOOD("Middle Eastern Food"),
    COFFEE("Coffee"),
    BUBBLETEA("Bubble Tea");

    String value;

    // EFFECTS: return value of FoodType
    FoodType(String value) {
        this.value = value;
    }

    // EFFECTS: return a list of foodtype value
    public static List<String> foodTypeList() {
        List<String> foodTypeList = new ArrayList<>();
        for (FoodType ft: FoodType.values()) {
            foodTypeList.add(ft.toString());
        }
        return foodTypeList;
    }

    // EFFECTS: return value of foodtype
    @Override
    public String toString() {
        return this.value;
    }
}
