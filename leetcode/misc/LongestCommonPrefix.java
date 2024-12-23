package leetcode.misc;

import java.util.Arrays;

public class LongestCommonPrefix {

    /**
     * https://www.geeksforgeeks.org/longest-common-prefix-using-sorting/
     * Given an array of strings arr[], the task is to return the longest common prefix among each and every strings
     * present in the array. If there’s no prefix common in all the strings, return “”.
     * Approach:
     *
     * The idea is to sort the array of strings and find the common prefix of the first and last string of the sorted array.
     * Sorting is used in this approach because it makes it easier to find the longest common prefix.
     * When we sort the strings, the first and last strings in the sorted list will be the most different from
     * each other in terms of their characters. So, the longest common prefix for all the strings must be a prefix of
     * both the first and the last strings in the sorted list.
     *
     */
    static String longestCommonPrefix(String[] arr){

        // Sort the array of strings
        Arrays.sort(arr);

        // Get the first and last strings after sorting
        String first = arr[0];
        String last = arr[arr.length - 1];
        int minLength = Math.min(first.length(), last.length());

        // Find the common prefix between the first and last strings
        int i = 0;
        while (i < minLength && first.charAt(i) == last.charAt(i)) {
            i++;
        }

        // Return the common prefix
        return first.substring(0, i);
    }
}
