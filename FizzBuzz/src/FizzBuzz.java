// Java program to print 1 through 100
// Multiples of 3 print Fizz
// Multiples of 5 print Buzz
// Multiples of both (15) print FizzBuzz

public class FizzBuzz {
    public static void main(String args[])
    {
        int n = 100;
        // For loop for 100 times
        for (int i = 1; i <= n; i++)
        {
            if (i%15 == 0) // Multiple of both 3&5
                System.out.print("FizzBuzz" + "\n");
            else if (i%5 == 0) // Multiple of 5
                System.out.print("Buzz" + "\n");
            else if (i%3 == 0) // Multiple of 3
                System.out.print("Fizz" + "\n");
            else // Non-multiples
                System.out.print(i + "\n");
        }
    }
}
