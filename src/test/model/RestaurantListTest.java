package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RestaurantListTest {

    private RestaurantList restaurantList;
    private Restaurant r1;
    private Restaurant r2;
    private Restaurant r3;
    private Restaurant r4;

    @BeforeEach
    void runBefore() {
        restaurantList = new RestaurantList();
        r1 = new Restaurant("Sesame", "#103 6111 University Blvd", FoodType.CHINESE);
        r2 = new Restaurant("Uncle Faith's Pizza", "6045 University Blvd", FoodType.PIZZA);
        r3 = new Restaurant("Nori Bento", "6055 University Blvd", FoodType.JAPANESE);
        restaurantList.addRestaurant(r1);
        restaurantList.addRestaurant(r2);
        restaurantList.addRestaurant(r3);
        r4 = new Restaurant("test", "test address", FoodType.JAPANESE);
    }

    @Test
    public void testAddRestaurant() {
        assertEquals(3, restaurantList.getSize());
        restaurantList.addRestaurant(r3);
        assertEquals(3, restaurantList.getSize());
    }

    @Test
    public void testGetRestaurant() {
        assertEquals("Sesame", restaurantList.getRestaurant(0).getName());
        assertEquals("Uncle Faith's Pizza", restaurantList.getRestaurant(1).getName());
        assertEquals("Nori Bento", restaurantList.getRestaurant(2).getName());
    }

    @Test
    public void testContains() {
        assertTrue(restaurantList.contains(r1));
        assertTrue(restaurantList.contains(r3));
        assertFalse(restaurantList.contains(r4));
    }

    @Test
    public void testToString() {
        assertEquals("1. Sesame, Chinese, No rating yet\n" +
                "2. Uncle Faith's Pizza, Pizza, No rating yet\n" +
                "3. Nori Bento, Japanese, No rating yet\n", restaurantList.toString());
    }

    @Test
    public void testRemove() {
        restaurantList.remove(1);
        assertEquals(2, restaurantList.getSize());
        assertEquals("Nori Bento", restaurantList.getRestaurant(1).getName());
    }
}
