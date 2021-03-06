// Write a method which takes in a sentence. It should return a string with the same words,
// but in reverse order
// Example: Hello there => there Hello
// Another Example: Hi my name is George => George is name my Hi

public class Main {
    public static void main(String[] args) {
        // Accept the return statement into a variable
        String newSentence = reverseString("Hello there");
        // Print the reversed string on screen
        System.out.println(newSentence);

        // Update the return statement in the variable
        newSentence = reverseString("Hi my name is George");
        // Print the reversed string on screen
        System.out.println(newSentence);
    }
        public static String reverseString (String s) {
        // Check if the argument is null
        if (s == null) {
            // If null, return null
            return null;
        }

        // Create a new StringBuilder
        StringBuilder output = new StringBuilder();

        // Create an array out of the sentence using the space as a delimeter
        String[] words = s.split(" ");

        // For loop to be able to go throughout the array and swap the positions
        for (int i = words.length - 1; i >= 0; i--) {
            // Append a word into the StringBuilder
            output.append(words[i]);
            // Append a space into the StringBuilder
            output.append(" ");
        }
        // Return the output, the string will be reversed
        return output.toString().trim();
    }

}
