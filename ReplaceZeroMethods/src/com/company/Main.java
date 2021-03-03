package com.company;

public class Main {

    public static void main(String[] args) {

        // Initialize and allocate memory for array of 8 elements
        int[] arr = new int[8];

        // Add values into array, begin at index 0
        arr[0] = 2;
        arr[1] = 3;
        arr[2] = 4;
        arr[3] = 0;
        arr[4] = 1;
        arr[5] = 8;
        arr[6] = 35;
        arr[7] = 0;

        // Find largest even number from the array
        int largest = LargestEvenNumber(arr);

        // Replace the zeros in the array with the largest even number
        ReplaceZero(arr, largest);

        }


    // Method to find the largest even number in array
    public static int LargestEvenNumber(int[] arr) {
        // Cycle through the array
        for (int i = 0; i < arr.length; i++)
            // Print the current values of the array
            System.out.println("Element at index " + i +
                    " : " + arr[i]);

        // Declare largest int variable
        int largest = Integer.MIN_VALUE;
        // Enhanced for loop to find even numbers in array
        for (int number : arr) {
            // Check if the numbers have a remainder
            if (number % 2 == 1) {
                continue;
            }
            // Check which even number is the largest
            if (number > largest) {
                // Declare the new number if it is larger than the "largest" value
                largest = number;

            }
        }
        // Return the value to use later
        return largest;
    }

    // Method to find and replace the zeros in the array
    public static void ReplaceZero(int[] arr, int largest) {
        // Check through array
        for (int i = 0; i < arr.length; i++) {
            // Find zeros in array
            if (arr[i] == 0 ) {
                // swap for largest
                arr[i] = largest;
            }
            // Print out new array values
            System.out.println(arr[i]);
        }
    }
}
