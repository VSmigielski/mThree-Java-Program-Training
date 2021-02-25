import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class SimpleFileIO {
    public static void main(String[] args) throws Exception {
        // This will not append but rather overwrite the file that is written
        // Create a PrintWriter & new file if there is not a file with the name
        PrintWriter out = new PrintWriter(new FileWriter("OutFile.txt"));

        // Write first line to file
        out.println("this is the first line in my file...");

        // Write second line to file
        out.println("this is the second line in my file...");

        // Write third line to file
        out.println("this is the third line in my file...");

        // Forces everything that hasn't been written to be written to file
        out.flush();

        // Important to close to make sure there is no leaks or overuse of resources
        // Closes file
        out.close();

        // Create a new scanner to read from the file
        Scanner sc = new Scanner(new BufferedReader(new FileReader("OutFile.txt")));

        // Use a while loop to read the lines in the file until the end
        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            System.out.println(currentLine);
        }

        System.out.println("That is all the content in the file.");

    }
}
