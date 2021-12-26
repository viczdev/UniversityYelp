package persistence;

import model.FoodType;
import model.Restaurant;

import static org.junit.jupiter.api.Assertions.assertEquals;

// inspired by the Demo App https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonTest {
    protected void checkRestaurant(Restaurant r, String name, String address, FoodType foodType, int rating, int numComments) {
        assertEquals(name, r.getName());
        assertEquals(address, r.getAddress());
        assertEquals(foodType, r.getFoodType());
        assertEquals(rating, r.calculateRating());
        assertEquals(numComments, r.getComments().size());
    }
}
