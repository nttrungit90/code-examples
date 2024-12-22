package leetcode.misc;

public class MaximumProductSubArray {

    /**
     * https://www.geeksforgeeks.org/maximum-product-subarray/
     * @param arr
     * @return
     */
    static int maxProduct(int[] arr) {
        int n = arr.length;

        // max product ending at the current index
        int currMax = arr[0];

        // min product ending at the current index
        int currMin = arr[0];

        // Initialize overall max product
        int maxProd = arr[0];

        // Iterate through the array
        for (int i = 1; i < n; i++) {

            // Temporary variable to store the maximum product ending
            // at the current index
            int temp = max(arr[i], arr[i] * currMax, arr[i] * currMin);

            // Update the minimum product ending at the current index
            currMin = min(arr[i], arr[i] * currMax, arr[i] * currMin);

            // Update the maximum product ending at the current index
            currMax = temp;

            // Update the overall maximum product
            maxProd = Math.max(maxProd, currMax);
        }

        return maxProd;
    }

    static int max(int a, int b, int c) {
        return Math.max(a, Math.max(b, c));
    }

    static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
