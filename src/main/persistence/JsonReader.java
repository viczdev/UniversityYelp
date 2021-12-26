package persistence;

import model.FoodType;
import model.Restaurant;
import model.RestaurantList;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// inspired by the Demo App https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a reader that reads workroom from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads RestaurantList from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RestaurantList read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseRestaurantList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses RestaurantList from JSON object and returns it
    private RestaurantList parseRestaurantList(JSONObject jsonObject) {
        RestaurantList rl = new RestaurantList();
        addRestaurants(rl, jsonObject);
        return rl;
    }

    // MODIFIES: rl (RestaurantList)
    // EFFECTS: parses Restaurant from JSON object and adds them to RestaurantList
    private void addRestaurants(RestaurantList rl, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("RestaurantList");
        for (Object json : jsonArray) {
            JSONObject nextRestaurant = (JSONObject) json;
            addRestaurant(rl, nextRestaurant);
        }
    }

    // MODIFIES: rl (RestaurantList)
    // EFFECTS: parses Restaurant from JSON object and adds it to RestaurantList
    private void addRestaurant(RestaurantList rl, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        String address = jsonObject.getString("address");
        FoodType foodType = FoodType.valueOf(jsonObject.getString("foodType"));
        Restaurant res = new Restaurant(name, address, foodType);

        JSONArray ratingJsonArray = jsonObject.getJSONArray("rating");
        for (int i = 0; i < ratingJsonArray.length(); i++) {
            res.makeRating(ratingJsonArray.getInt(i));
        }

        JSONArray commentsJsonArray = jsonObject.getJSONArray("comments");
        for (int i = 0; i < commentsJsonArray.length(); i++) {
            res.makeComment(commentsJsonArray.getString(i));
        }

        rl.addRestaurant(res);
    }
}
