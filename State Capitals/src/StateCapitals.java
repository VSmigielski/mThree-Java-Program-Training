import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

public class StateCapitals {
    public static void main(String[] args) {
        // Creates a new map
        Map<String, String> stateCapitals = new HashMap<>();

        // Adds Alabama with capital Montgomery
        stateCapitals.put("Alabama", "Montgomery");

        // Adds Alaska with capital Juneau
        stateCapitals.put("Alaska", "Juneau");

        // Adds Arizona with capital Phoenix
        stateCapitals.put("Arizona", "Phoenix");

        // Adds Arkansas with capital Little Rock
        stateCapitals.put("Arkansas", "Little Rock");

        // Adds California with capital Sacramento
        stateCapitals.put("California", "Sacramento");

        // Adds Colorado with capital Denver
        stateCapitals.put("Colorado", "Denver");

        // Adds Connecticut with capital Hartford
        stateCapitals.put("Connecticut", "Hartford");

        // Adds Delaware with capital Dover
        stateCapitals.put("Delaware", "Dover");

        // Adds Florida with capital Tallahassee
        stateCapitals.put("Florida", "Tallahassee");

        // Adds Georgia with capital Atlanta
        stateCapitals.put("Georgia", "Atlanta");

        // Adds Hawaii with capital Honolulu
        stateCapitals.put("Hawaii", "Honolulu");

        // Adds Idaho with capital Boise
        stateCapitals.put("Idaho", "Boise");

        // Adds Illinois with capital Springfield
        stateCapitals.put("Illinois", "Springfield");

        // Adds Indiana with capital Indianapolis
        stateCapitals.put("Indiana", "Indianapolis");

        // Adds Iowa with capital Des Moines
        stateCapitals.put("Iowa", "Des Moines");

        // Adds Kansas with capital Topeka
        stateCapitals.put("Kansas", "Topeka");

        // Adds Kentucky with capital Frankfort
        stateCapitals.put("Kentucky", "Frankfort");

        // Adds Louisiana with capital Baton Rouge
        stateCapitals.put("Louisiana", "Baton Rouge");

        // Adds Maine with capital Augusta
        stateCapitals.put("Maine", "Augusta");

        // Adds Maryland with capital Annapolis
        stateCapitals.put("Maryland", "Annapolis");

        // Adds Massachusetts with capital Boston
        stateCapitals.put("Massachusetts", "Boston");

        // Adds Michigan with capital Lansing
        stateCapitals.put("Michigan", "Lansing");

        // Adds Minnesota with capital Saint Paul
        stateCapitals.put("Minnesota", "Saint Paul");

        // Adds Mississippi with capital Jackson
        stateCapitals.put("Mississippi", "Jackson");

        // Adds Missouri with capital Jefferson City
        stateCapitals.put("Missouri", "Jefferson City");

        // Adds Montana with capital Helena
        stateCapitals.put("Montana", "Helena");

        // Adds Nebraska with capital Lincoln
        stateCapitals.put("Nebraska", "Lincoln");

        // Adds Nevada with capital Carson City
        stateCapitals.put("Nevada", "Carson City");

        // Adds New Hampshire with capital Concord
        stateCapitals.put("New Hampshire", "Concord");

        // Adds New Jersey with capital Trenton
        stateCapitals.put("New Jersey", "Trenton");

        // Adds New Mexico with capital Santa Fe
        stateCapitals.put("New Mexico", "Santa Fe");

        // Adds New York with capital Albany
        stateCapitals.put("New York", "Albany");

        // Adds North Carolina with capital Raleigh
        stateCapitals.put("North Carolina", "Raleigh");

        // Adds North Dakota with capital Bismarck
        stateCapitals.put("North Dakota", "Bismarck");

        // Adds Ohio with capital Columbus
        stateCapitals.put("Ohio", "Columbus");

        // Adds Oklahoma with capital Oklahoma City
        stateCapitals.put("Oklahoma", "Oklahoma City");

        // Adds Oregon with capital Salem
        stateCapitals.put("Oregon", "Salem");

        // Adds Pennsylvania with capital Harrisburg
        stateCapitals.put("Pennsylvania", "Harrisburg");

        // Adds South Carolina with capital Providence
        stateCapitals.put("Rhode Island", "Providence");

        // Adds South Carolina with capital Columbia
        stateCapitals.put("South Carolina", "Columbia");

        // Adds South Dakota with capital Pierre
        stateCapitals.put("South Dakota", "Pierre");

        // Adds Tennessee with capital Nashville
        stateCapitals.put("Tennessee", "Nashville");

        // Adds Texas with capital Austin
        stateCapitals.put("Texas", "Austin");

        // Adds Utah with capital Salt Lake City
        stateCapitals.put("Utah", "Salt Lake City");

        // Adds Vermont with capital Montpelier
        stateCapitals.put("Vermont", "Montpelier");

        // Adds Virginia with capital Richmond
        stateCapitals.put("Virginia", "Richmond");

        // Adds Washington with capital Olympia
        stateCapitals.put("Washington", "Olympia");

        // Adds West Virginia with capital Charleston
        stateCapitals.put("West Virginia", "Charleston");

        // Adds Wisconsin with capital Madison
        stateCapitals.put("Wisconsin", "Madison");

        // Adds Wyoming with capital Cheyenne
        stateCapitals.put("Wyoming", "Cheyenne");

        // Display on screen the map size
        System.out.println("Map size is: " + stateCapitals.size());

        // Create a keyset
        Set<String> keys = stateCapitals.keySet();

        // Enhanced for loop for states
        // Enhanced for loop
        for(String currentKey : keys) {
            // Display population values on screen
            System.out.println(currentKey);
        }

        // Create a collection of the values of capitals, this is an unordered collection
        Collection<String> capitalValues = stateCapitals.values();

        // Enhanced for loop
        for(String currentState : capitalValues) {
            // Display population values on screen
            System.out.println(currentState);
        }

        // Enhanced for loop for keys & values
        for(String currentKey : keys) {
            // Grab a key out one by one
            String currentState = stateCapitals.get(currentKey);
            // Insert each key int this statement and print on screen, the set is unordered
            System.out.println(currentKey + " - " + currentState);
        }
    }
}
