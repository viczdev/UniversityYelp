package persistence;

import model.FoodType;
import model.Restaurant;
import model.RestaurantList;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// inspired by the Demo App https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            JsonWriter jsonWriter = new JsonWriter("./data/my\0illegal:fileName.json");
            jsonWriter.open();
            fail("FileNotFoundException was expected");
        } catch (FileNotFoundException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyRestaurantList() {
        try {
            RestaurantList rl = new RestaurantList();
            JsonWriter jsonWriter = new JsonWriter("./data/testWriterEmptyRestaurantList.json");
            jsonWriter.open();
            jsonWriter.write(rl);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/testWriterEmptyRestaurantList.json");
            rl = jsonReader.read();
            assertEquals(0, rl.getSize());
        } catch (IOException e) {
            fail("Should not have exception");
        }
    }

    @Test
    void testWriterGeneralRestaurantList() {
        try {
            RestaurantList rl = new RestaurantList();
            Restaurant r1 = new Restaurant("Sesame","University Blvd", FoodType.CHINESE);
            r1.makeRating(3);
            r1.makeComment("Soso");
            Restaurant r2 = new Restaurant("Nori", "University St", FoodType.JAPANESE);
            r2.makeRating(4);
            r2.makeComment("good!");
            rl.addRestaurant(r1);
            rl.addRestaurant(r2);

            JsonWriter jsonWriter = new JsonWriter("./data/testWriterGeneralRestaurantList.json");
            jsonWriter.open();
            jsonWriter.write(rl);
            jsonWriter.close();

            JsonReader jsonReader = new JsonReader("./data/testWriterGeneralRestaurantList.json");
            rl = jsonReader.read();
            assertEquals(2, rl.getSize());
            checkRestaurant(rl.getRestaurant(0), "Sesame","University Blvd", FoodType.CHINESE,3,1);
            checkRestaurant(rl.getRestaurant(1), "Nori","University St", FoodType.JAPANESE,4,1);
        } catch (IOException e) {
            fail("Should not have exception");
        }
    }
}
