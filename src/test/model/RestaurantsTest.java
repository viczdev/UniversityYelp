package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantsTest {
    private Restaurant testRestaurant;

    @BeforeEach
    void runBefore() {
        testRestaurant = new Restaurant("Sesame", "#103 6111 University Blvd", FoodType.CHINESE);
    }

    @Test
    void testConstructor() {
        assertEquals("Sesame", testRestaurant.getName());
        assertEquals("#103 6111 University Blvd", testRestaurant.getAddress());
        assertEquals(FoodType.CHINESE, testRestaurant.getFoodType());
        assertEquals(0, testRestaurant.getComments().size());
    }

    @Test
    void testMakeRating() {
        assertNull(testRestaurant.calculateRating());
        testRestaurant.makeRating(3);
        testRestaurant.makeRating(2);
        testRestaurant.makeRating(4);
        assertEquals(3, testRestaurant.calculateRating());
        testRestaurant.makeRating(1);
        assertEquals(2.5, testRestaurant.calculateRating());
    }

    @Test
    void testMakeComment() {
        testRestaurant.makeComment("It is tasty!");
        testRestaurant.makeComment("Do not order Ginger Beef!");
        assertEquals(2, testRestaurant.getComments().size());
        assertEquals("It is tasty!", testRestaurant.getComments().get(0));
        assertEquals("Do not order Ginger Beef!", testRestaurant.getComments().get(1));
    }

    @Test
    void testToString() {
        assertEquals("Sesame, Chinese, No rating yet", testRestaurant.toString());
        testRestaurant.makeRating(3);
        assertEquals("Sesame, Chinese, 3.00", testRestaurant.toString());
    }

    @Test
    void testEqual() {
        assertFalse(testRestaurant.equals(null));
        String s = "t";
        assertFalse(testRestaurant.equals(s));
        int hashCode = testRestaurant.hashCode();
        assertEquals(Objects.hash("Sesame"), hashCode);
    }
}
