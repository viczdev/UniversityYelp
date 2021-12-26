package persistence;

import model.FoodType;
import model.RestaurantList;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// inspired by the Demo App https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyRestaurantList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyRestaurantList.json");
        try {
            RestaurantList rl = reader.read();
            assertEquals(0, rl.getSize());
        } catch (IOException e) {
            fail("Can not read from file");
        }
    }

    @Test
    void testReaderGeneralRestaurantList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralRestaurantList.json");
        try {
            RestaurantList rl = reader.read();
            assertEquals(2, rl.getSize());
            checkRestaurant(rl.getRestaurant(0), "Sesame","University Blvd", FoodType.CHINESE,4,3);
            checkRestaurant(rl.getRestaurant(1), "Nori","University St",FoodType.JAPANESE,3,2);
        } catch (IOException e) {
            fail("Can not read from file");
        }
    }
}
