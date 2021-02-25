import java.util.ArrayList;
import java.util.List;

public class ListExamples {
    public static void main(String[] args) {
        // Create a new list of strings using type ArrayList
        List<String> stringList = new ArrayList<>();
        // List size is 0
        System.out.println("List size: " + stringList.size());

        // Adds one list element
        stringList.add("The first string.");

        // List size should be 1
        System.out.println("List size: " + stringList.size());

        // Adds one list element
        stringList.add("The second string.");

        // List size should be 2
        System.out.println("List size: " + stringList.size());

        // Remove an element at index of 1 (2nd list element)
        stringList.remove(1);

        // List size should be 1
        System.out.println("List size: " + stringList.size());

        // Remove an element at index of 1 (2nd list element)
        stringList.remove(0);

        // List size should be 0
        System.out.println("List size: " + stringList.size());

        // Since there is no other element, throws an exception, keep indexes within bounds
        // Remove an element at index of 1 (2nd list element)
        // stringList.remove(0);

        // List size should be 0
        // System.out.println("List size: " + stringList.size());

    }
}
