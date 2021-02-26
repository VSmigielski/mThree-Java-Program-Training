public class ReplaceZero {
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

        // accessing the elements of the specified array
        for (int i = 0; i < arr.length; i++)
            System.out.println("Element at index " + i +
                    " : "+ arr[i]);

        int largest = Integer.MIN_VALUE;
        for (int number : arr)
        {
            if (number % 2 == 1) {
                continue;
            }
            if (number > largest) {
                largest = number;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] ==0 ) {
                arr[i] = largest;
            }
            System.out.println(arr[i]);
        }

    }
}
