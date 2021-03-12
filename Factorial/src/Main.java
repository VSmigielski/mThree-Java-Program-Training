public class Main {
    public static void main(String[] args) {

        // Create a variable to hold return value
        int result = factorial(-2);
        // Print value on screen
        System.out.println(result);

        // Update return value
        result = factorial(3);
        // Print value on screen
        System.out.println(result);

        // Update return value
        result = factorial(5);
        // Print value on screen
        System.out.println(result);

        // Update return value
        result = factorial(0);
        // Print value on screen
        System.out.println(result);

        // Update return value
        result = factorial(1);
        // Print value on screen
        System.out.println(result);
    }

    public static int factorial(int a) {
        // checks for negative
        if (a < 0) {
            return -1;
        }

        // checks if parameter is 0 or 1
        if ((a == 0) || (a == 1)) {
            // return value of 1
            return 1;
            // For all else
        } else {
            // Multiply the numbers
            return a * factorial (a - 1);
        }
    }
}
