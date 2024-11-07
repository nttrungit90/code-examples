package leetcode.sort;

public class InsertionSort {

    public static void insertionSort(int [] arr) {

        int n = arr.length;
        // Iterate over the array starting from the second element
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // Current element to be inserted
            int j = i - 1;

            // Move elements of arr[0..i-1], that are greater than key,
            // to one position ahead of their current position
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j]; // Shift element to the right
                j = j - 1; // Move to the previous element
            }
            arr[j + 1] = key; // Place the key at its correct position
        }
    }
}
