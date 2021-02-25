import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.Collection;

public class MapExamples {
    public static void main(String[] args) {
        // Creates a new map
        Map<String, Integer> populations = new HashMap<>();

        // Adds USA with population of 200M
        populations.put("USA", 200000000);

        // Adds CAN with population of 340M
        populations.put("Canada", 34000000);

        // Adds United Kingdom with population of 63M
        populations.put("United Kingdom", 63000000);

        // Adds Japan with population of 127M
        populations.put("Japan", 127000000);

        // Overwrites USA with population of 313M
        populations.put("USA", 313000000);

        // Display on screen the map size
        System.out.println("Map size is: " + populations.size());

        // Make a variable for USA population
        Integer usaPopulation = populations.get("USA");

        // Display on screen the USA population
        System.out.println(usaPopulation);

        // Overwrites USA with population of 313M
        populations.put("USA", 313000000);

        // Re-assign the value of the variable
        usaPopulation = populations.get("USA");

        // Display on screen the USA population that was overwritten
        System.out.println(usaPopulation);

        // Create a keyset
        Set<String> keys = populations.keySet();

        // Enhanced for loop
        for(String currentKey : keys) {
            // Grab a key out one by one
            Integer currentPopulation = populations.get(currentKey);
            // Insert each key int this statement and print on screen, the set is unordered
            System.out.println("The population of " + currentKey + " is " + currentPopulation);
        }

        // Create a collection of the values of populations, this is an unordered collection
        Collection<Integer> populationValues = populations.values();

        // Enhanced for loop
        for(Integer currentPopulation : populationValues) {
            // Display population values on screen
            System.out.println(currentPopulation);
        }
    }
}
