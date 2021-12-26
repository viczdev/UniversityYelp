package model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FoodTypeTest {

    @Test
    void testFoodTypeToString() {
        FoodType foodChinese = FoodType.CHINESE;
        FoodType foodJap = FoodType.JAPANESE;
        FoodType foodPiz = FoodType.PIZZA;
        FoodType foodMEF = FoodType.MIDDLEEASTERNFOOD;
        FoodType foodCof = FoodType.COFFEE;
        FoodType foodBbt = FoodType.BUBBLETEA;
        assertEquals("Chinese", foodChinese.toString());
        assertEquals("Japanese", foodJap.toString());
        assertEquals("Pizza", foodPiz.toString());
        assertEquals("Middle Eastern Food", foodMEF.toString());
        assertEquals("Coffee", foodCof.toString());
        assertEquals("Bubble Tea", foodBbt.toString());
    }

    @Test
    void testFoodTypeToList() {
        List<String> foodTypeList = FoodType.foodTypeList();
        assertEquals(foodTypeList.size(), 6);
    }

}
