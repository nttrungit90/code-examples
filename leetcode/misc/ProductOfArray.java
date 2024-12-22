package leetcode.misc;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/a-product-array-puzzle/
 */
public class ProductOfArray {

    /**
     * Function to calculate the product of all elements except the current element ussing Nested Loops – O(n^2) Time and O(1) Space
     * @param arr
     * @return
     */
    static int[] productExceptSelfUsingNestedLoop(int[] arr) {
        int n = arr.length;

        // Initialize the product array as 1
        int[] prod = new int[n];
        Arrays.fill(prod, 1);
        for (int i = 0; i < n; i++) {

            // Compute the product of all except arr[i]
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    prod[i] *= arr[j];
                }
            }
        }

        return prod;
    }


    /**
     * Left and Right Arrays:
     *
     * Left Array: left[i] contains the product of all elements to the left of arr[i].
     * Right Array: right[i] contains the product of all elements to the right of arr[i].
     * These arrays help compute the result in two separate phases: one for the left side and one for the right side.
     *
     * Final Product Calculation:
     * Once both left[] and right[] arrays are populated, the final result is computed by multiplying left[i] and right[i]
     * for each index i to get the desired product except the element at index i.
     *
     * Using Prefix and Suffix Array – O(n) Time and O(n) Space
     *
     * @param arr
     * @return
     */
    static int[] productExceptSelfUsingPrefixSuffixArray(int[] arr) {
        int n = arr.length;

        // If only one element, return a vector with 1
        if (n == 1) {
            return new int[]{1};
        }

        int[] left = new int[n], right = new int[n], prod = new int[n];

        // Construct the left array
        left[0] = 1; // The product of all elements to the left of arr[0] is 1 (nothing to the left)
        for (int i = 1; i < n; i++) {
            left[i] = arr[i - 1] * left[i - 1];
        }

        // Construct the right array
        right[n - 1] = 1; // The product of all elements to the left of arr[n - 1] is 1 (nothing to the right)
        for (int j = n - 2; j >= 0; j--) {
            right[j] = arr[j + 1] * right[j + 1];
        }

        // Construct the product array using left[] and right[]
        for (int i = 0; i < n; i++) {
            prod[i] = left[i] * right[i];
        }

        return prod;
    }

    /**
     * Create an array prod[] to store the result.
     * First pass (Left Products):
     * Iterate from left to right, and for each index i, store the product of all elements before i in prod[i].
     * Second pass (Right Products):
     * Iterate from right to left, and for each index i, multiply the value in prod[i] by the product of all elements after i.
     * O(n) time complexity and O(1) extra space, excluding the output array.
     * @param arr
     * @return
     */
    public static int[] constructProductArray(int[] arr) {
        int n = arr.length;
        int[] prod = new int[n];

        // Step 1: Compute the left products and store in prod[]
        prod[0] = 1; // The product of all elements to the left of arr[0] is 1 (nothing to the left)
        for (int i = 1; i < n; i++) {
            prod[i] = prod[i - 1] * arr[i - 1];
        }

        // Step 2: Compute the right products and multiply them with the left products
        int rightProduct = 1; // The product of all elements to the right of arr[n-1] is 1 (nothing to the right)
        for (int i = n - 1; i >= 0; i--) {
            prod[i] = prod[i] * rightProduct;
            rightProduct *= arr[i]; // Update the right product for the next element to the left
        }

        return prod;
    }

}
