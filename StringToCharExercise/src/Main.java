import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args) {
        // Use first method to auto-print on screen
        stringTo2CharString("Hello");

        // Use first method to auto-print on screen
        stringTo2CharString("Hello there");
        // Use first method to auto-print on screen
        stringTo2CharString("There");

        // Create a new variable to store the return value
        String result = stringTo2CharString2("Hello");
        // Print return value on screen
        System.out.println(result);

        // Update value in the variable
        result = stringTo2CharString2("Hello there");
        // Print return value on screen
        System.out.println(result);

        // Update value in the variable
        result = stringTo2CharString2("There");
        // Print return value on screen
        System.out.println(result);
    }

    public static void stringTo2CharString(String string) {
        // Swap the string into an array
        char[] stringArray = string.toCharArray();
        // Until the array is fully cycled through, continue the loop
        for (int i = 0; i < stringArray.length; i++) {
            // Print the char on screen
            System.out.print(stringArray[i]);
            // Print the char on screen (again)
            System.out.print(stringArray[i]);
        }
        // Start on a fresh line
        System.out.println("\r");
    }

    public static String stringTo2CharString2(String string) {
        // Check if the string is empty
        if (string == null) {
            // If empty, return null
            return null;
        }

        // Create a new string builder
        StringBuilder output = new StringBuilder();

        // Convert the string to a char array
        char[] stringArray = string.toCharArray();

        // Cycle through the array as long as the length isn't exceeded
        for (int i = 0; i < stringArray.length; i++) {
            // Append each character to the stringbuilder
            output.append(stringArray[i]);
            // Append each character to the stringbuilder (again)
            output.append(stringArray[i]);
        }
        // Return the completed string
        return output.toString().trim();
    }
}
