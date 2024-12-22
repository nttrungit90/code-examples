package leetcode.misc;

import java.util.ArrayList;
import java.util.List;

public class FindMaxSumSubArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4};
        List<List<Integer>> subarrays = findAllSubArrays(arr);

        // Print all subarrays
        for (List<Integer> subarray : subarrays) {
            System.out.println(subarray);
        }
    }

    /**
     * We will iterate through all possible combinations of elements in the array.
     * For each combination, we will create a subarray by picking elements from the array.
     * We will store and return all the subarrays.
     * @param arr
     * @return
     */
    public static List<List<Integer>> findAllSubArrays(int[] arr) {
        List<List<Integer>> subArrays = new ArrayList<>();

        int n = arr.length;

        // Iterate over all possible starting points
        for (int start = 0; start < n; start++) {
            // For each start point, iterate over all possible end points
            for (int end = start; end < n; end++) {

                List<Integer> subArray = new ArrayList<>();
                // Add elements from start to end to form the subarray
                for (int i = start; i <= end; i++) {
                    subArray.add(arr[i]);
                }

                subArrays.add(subArray);
            }
        }

        return subArrays;
    }


    /**
     * Function to find the maximum subarray sum using brute force
     * By iterating over all subarrays – O(n^2) Time and O(1) Space
      */
    static int maxSubarraySum(int[] arr) {
        // Initialize the result to the smallest possible value (first element of the array)
        int res = arr[0];

        // Outer loop: Iterate over all possible starting points of subarrays
        for (int i = 0; i < arr.length; i++) {
            // Initialize current sum for the subarray starting at index 'i'
            int currSum = 0;

            // Inner loop: Iterate over all possible ending points for subarray starting at 'i'
            for (int j = i; j < arr.length; j++) {
                // Add the current element to the subarray sum
                currSum += arr[j];

                // Update the maximum sum found so far
                res = Math.max(res, currSum);
            }
        }

        // Return the maximum subarray sum
        return res;
    }

    /**
     * The idea of Kadane’s algorithm is to traverse over the array from left to right and for each element,
     * find the maximum sum among all subarrays ending at that element. The result will be the maximum of all these values.
     * But, the main issue is how to calculate maximum sum among all the subarrays ending at an element in O(1) time?
     * To calculate the maximum sum of subarray ending at current element, say maxEnding, we can use the maximum sum ending at the previous element.
     * So for any element, we have two choices:
     * Choice 1: Extend the maximum sum subarray ending at the previous element by adding the current element to it. If the maximum subarray sum ending at the previous index is positive, then it is always better to extend the subarray.
     * Choice 2: Start a new subarray starting from the current element. If the maximum subarray sum ending at the previous index is negative, it is always better to start a new subarray from the current element.
     * This means that maxEnding at index i = max(maxEnding at index (i – 1) + arr[i], arr[i]) and the maximum value of maxEnding at any index will be our answer.
     * @param arr
     * @return
     */
    static int maxSubarraySumUsingKadaneAlgo(int[] arr) {
        int res = arr[0];
        int maxEnding = arr[0];

        for (int i = 1; i < arr.length; i++) {

            // Find the maximum sum ending at index i by either extending
            // the maximum sum subarray ending at index i - 1 or by
            // starting a new subarray from index i
            maxEnding = Math.max(maxEnding + arr[i], arr[i]);

            // Update res if maximum subarray sum ending at index i > res
            res = Math.max(res, maxEnding);
        }
        return res;
    }

}
