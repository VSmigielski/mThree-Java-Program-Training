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
        int[] array = SmallestLargestNumber(arr);
        for (int i = 0; i < array.length; i++) {
            System.out.println(array[i] + " ");
        }

    }

    // Method to find the largest even number in array
    public static int[] SmallestLargestNumber(int[] arr) {

        // Declare largest int variable
        int largest = Integer.MIN_VALUE;
        int smallest = Integer.MAX_VALUE;
        int[] array = new int[2];
        for (int number : arr) {
            // Check which number is the smallest
            if (number < smallest) {
                // Declare the new number if it is smaller than the "smallest" value
                smallest = number;

            }
        }
        // Enhanced for loop to find largest number in array
        for (int number : arr) {
            // Check which number is the largest
            if (number > largest) {
                // Declare the new number if it is larger than the "largest" value
                largest = number;

            }
        }
        // Assign values to array
        array[0] = smallest;
        array[1] = largest;
        // Return the array to use later
        return array;
    }
}
