package persistence;

import model.RestaurantList;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

// inspired by the Demo App https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
// Represents a writer that writes JSON representation of restaurant to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(destination);
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of restaurant to file
    public void write(RestaurantList rl) {
        JSONObject jo = rl.toJson();
        writer.print(jo.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: close writer
    public void close() {
        writer.close();
    }

}
