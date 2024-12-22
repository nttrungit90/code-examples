package leetcode.misc;

import java.util.Arrays;

public class ChocolateDistribution {

    /**
     *
     * Problem: https://www.geeksforgeeks.org/chocolate-distribution-problem/
     * Given an array arr[] of n integers where arr[i] represents the number of chocolates in ith packet
     * Each packet can have a variable number of chocolates. There are m students, the task is to distribute chocolate packets such that:
     * Each student gets exactly one packet.
     * The difference between the maximum and minimum number of chocolates in the packets given to the students is minimized
     *
     * Solution:
     * The idea is to use sliding window technique and choose consecutive elements from a sorted array to minimize the difference.
     * After sorting the array, the difference between the maximum and minimum values in any window of size m is minimized.
     * First sort the array arr[] and then use two pointers to maintain a window of size m to find the subarray with
     * the minimum difference between its last and first elements.
     */
    static int findMinDiff(int[] arr, int m) {
        int n = arr.length;

        // Sort the given packets
        Arrays.sort(arr);

        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i + m - 1 < n; i++) {

            // calculate difference of current window
            int diff = arr[i + m - 1] - arr[i];

            // if current difference is smaller
            // then update the minimum difference
            if (diff < minDiff)
                minDiff = diff;
        }
        return minDiff;
    }

}
