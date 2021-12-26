//package ui;
//
//import model.FoodType;
//import model.Restaurant;
//import model.RestaurantList;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.*;
//
//// Represents YelpUBC application
//public class YelpUBC {
//    private static final List<String> COMMANDLIST =
//            Collections.unmodifiableList(Arrays.asList("a", "c", "d", "l", "r", "rm", "q"));
//    private static final String JSON_STORE = "./data/RestaurantList.json";
//    private Scanner input;
//    private RestaurantList resList;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//    // EFFECTS: runs the YelpUBC application
//    public YelpUBC() {
//        runYelpUBC();
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user input
//    public void runYelpUBC() {
//        boolean keepGoing = true;
//        String command;
//        init();
//        while (keepGoing) {
//            displayMenu();
//            command = input.next();
//            command = command.toLowerCase();
//
//            if (command.equals("q")) {
//                keepGoing = false;
//            } else {
//                processCommand(command);
//            }
//        }
//        System.out.println("Thank you for using!");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: initializes restaurant list and user input
//    private void init() {
//        input = new Scanner(System.in);
//        input.useDelimiter("\n");
//        resList = new RestaurantList();
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//    }
//
//    // EFFECTS: displays menu of options to user
//    public void displayMenu() {
//        System.out.println("\n Please Select from:");
//        System.out.println("\ta -> Add restaurant info");
//        System.out.println("\tc -> Leave a comment");
//        System.out.println("\td -> Get detail of a restaurant");
//        System.out.println("\tl -> List all restaurants");
//        System.out.println("\tr -> Rate a restaurant");
//        System.out.println("\trm -> Remove a restaurant");
//        System.out.println("\tld -> Load restaurants info from files");
//        System.out.println("\ts -> Save restaurants info");
//        System.out.println("\tq -> quit");
//    }
//
//    // MODIFIES: this
//    // EFFECTS: processes user command
//    public void processCommand(String command) {
//        if (command.equals("a")) {
//            addRestaurant();
//        } else if (COMMANDLIST.contains(command) && resList.getSize() == 0) {
//            System.out.println("There is no Restaurant Information in the YelpUBC!");
//        } else if (command.equals("c")) {
//            leaveComment();
//        } else if (command.equals("d")) {
//            getDetail();
//        } else if (command.equals("l")) {
//            listAll();
//        } else if (command.equals("r")) {
//            rateRestaurant();
//        } else if (command.equals("rm")) {
//            removeRestaurant();
//        } else if (command.equals("s")) {
//            saveRestaurantList();
//        } else if (command.equals("ld")) {
//            loadRestaurantList();
//        } else {
//            System.out.println("Not an valid Command...");
//        }
//    }
//
//    private void addRestaurant() {
//        System.out.println("Please Enter the name of restaurant");
//        String restaurantName = input.next();
//        System.out.println("Please Enter the address of restaurant");
//        String restaurantAddress = input.next();
//        displayFoodType();
//        int foodType = input.nextInt();
//        if (foodType < 1 || foodType > FoodType.values().length) {
//            System.out.println("Invalid number for Food Type");
//        } else {
//            Restaurant newRes = new Restaurant(restaurantName, restaurantAddress, FoodType.values()[foodType - 1]);
//            resList.addRestaurant(newRes);
//        }
//    }
//
//    // EFFECTS: print detail of selected restaurant
//    private void getDetail() {
//        Integer restaurantNum = selectRestaurant();
//        if (restaurantNum != null) {
//            Restaurant res = resList.getRestaurant(restaurantNum);
//            printRestaurantDetail(res);
//        }
//    }
//
//    // EFFECTS: select restaurant
//    private Integer selectRestaurant() {
//        System.out.println("Please enter the number of restaurant:");
//        listAll();
//        int restaurantNum = input.nextInt();
//        if (restaurantNum < 1 || restaurantNum > resList.getSize()) {
//            System.out.println("Invalid rating for restaurant");
//        } else {
//            return restaurantNum - 1;
//        }
//        return null;
//    }
//
//    // EFFECTS: print detail of selected restaurant
//    private void printRestaurantDetail(Restaurant res) {
//        System.out.println("Name: " + res.getName());
//        System.out.println("Address: " + res.getAddress());
//        System.out.println("Food Type: " + res.getFoodType());
//        if (res.calculateRating() == null) {
//            System.out.println("Rating: No rating yet");
//        } else {
//            System.out.println("Rating: " + String.format("%.2f", res.calculateRating()));
//        }
//        if (res.getComments().size() == 0) {
//            System.out.println("Customer Comments: No comments yet");
//        } else {
//            System.out.println("Customer Comments: ");
//            int i = 1;
//            for (String c : res.getComments()) {
//                System.out.println("\t\t" + i + ". " + c);
//                i++;
//            }
//        }
//    }
//
//    // EFFECTS: print all restaurant in the restaurant list
//    private void listAll() {
//        System.out.println(resList);
//    }
//
//    // MODIFIES: this
//    // EFFECTS: rate the selected restaurant
//    private void rateRestaurant() {
//        Integer restaurantNum = selectRestaurant();
//        if (restaurantNum != null) {
//            Restaurant res = resList.getRestaurant(restaurantNum);
//            System.out.println("Please enter rating from 1 to 5");
//            int rating = input.nextInt();
//            if (rating < 1 || rating > 5) {
//                System.out.println("Rating must between 1 to 5");
//            } else {
//                res.makeRating(rating);
//            }
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: leave a comment to the selected restaurant
//    private void leaveComment() {
//        Integer restaurantNum = selectRestaurant();
//        if (restaurantNum != null) {
//            Restaurant res = resList.getRestaurant(restaurantNum);
//            System.out.println("Please enter your comment");
//            String comment = input.next();
//            res.makeComment(comment);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: remove selected restaurant from restaurant list
//    private void removeRestaurant() {
//        Integer restaurantNum = selectRestaurant();
//        if (restaurantNum != null) {
//            resList.remove(restaurantNum);
//        }
//    }
//
//    // EFFECTS: print supported food type
//    private void displayFoodType() {
//        System.out.println("\n Please enter the number of food types:");
//        for (int i = 1; i <= FoodType.values().length; i++) {
//            System.out.println("\t" + i + ". -> " + FoodType.values()[i - 1]);
//        }
//    }
//
//    // EFFECTS: saves resList to file
//    private void saveRestaurantList() {
//        try {
//            jsonWriter.open();
//            jsonWriter.write(resList);
//            jsonWriter.close();
//            System.out.println("Saved restaurants information to " + JSON_STORE);
//        } catch (FileNotFoundException e) {
//            System.out.println("Unable to write to file: " + JSON_STORE);
//        }
//    }
//
//    // MODIFIES: this
//    // EFFECTS: loads resList from file
//    private void loadRestaurantList() {
//        try {
//            resList = jsonReader.read();
//            System.out.println("Loaded RestaurantList info from " + JSON_STORE);
//        } catch (IOException e) {
//            System.out.println("Unable to read from file: " + JSON_STORE);
//        }
//    }
//
//}
