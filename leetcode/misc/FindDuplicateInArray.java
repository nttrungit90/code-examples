package leetcode.misc;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/find-duplicates-in-on-time-and-constant-extra-space/?ref=ml_lbp
 */
public class FindDuplicateInArray {

    public static void main(String[] args) {

    }


    /**
     * Problem: Given an array of n elements that contains elements from 0 to n-1,
     * with any of these numbers appearing any number of times. Find these repeating numbers in O(n)
     *
     * Solution: Use the input array to store the frequency of each element. While Traversing the array, if an element x is encountered
     * then check if its frequency is greater than 1 , then put it in the result array.
     * Time complexity: O(n)
     * Auxiliary Space: O(n), The extra space is used for the hash and array to be returned.
     * @param arr
     * @return
     */
    public static List<Integer> findDuplicatesUsingHashMap(int[] arr) {
        List<Integer> duplicates = new ArrayList<>();
        HashMap<Integer, Integer> freqMap = new HashMap<>();
        for(int value : arr) {
            freqMap.put(value, freqMap.getOrDefault(value, 0) + 1);
        }

        for(Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            if(entry.getValue() > 1) {
                duplicates.add(entry.getKey());
            }
        }

        return duplicates;
    }

    public static List<Integer> findDuplicatesUsingHashSet(int[] arr) {
        Set<Integer> seen = new HashSet<>();
        List<Integer> duplicates = new ArrayList<>();

        for (int num : arr) {
            if (seen.contains(num)) {
                // Add to duplicates if it's already seen
                if (!duplicates.contains(num)) {
                    duplicates.add(num);
                }
            } else {
                // Mark the number as seen
                seen.add(num);
            }
        }

        return duplicates;
    }


    /**
     * Approach
     * The array contains numbers from 0 to n-1.
     * We'll iterate through the array and mark visited elements by negating the value at the index corresponding to the current element.
     * If we encounter an already negative value at an index, the current element is a duplicate.
     * Time complexity: O(n)
     * Auxiliary Space: O(1), leverage the input array itself as a storage to mark visits, achieving O(1) extra space.
     * @param arr
     * @return
     */
    public static List<Integer> findDuplicatesUsingNegativeMarking(int[] arr) {
        List<Integer> repeatingNumbers = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            // Find the absolute value of the current element
            int index = Math.abs(arr[i]);

            // If the value at index is already negative, it's a duplicate
            if (arr[index] < 0) {
                if(!repeatingNumbers.contains(index)) {
                    repeatingNumbers.add(index);
                }
            } else {
                // Mark the value at index as visited (negate it)
                arr[index] = -arr[index];
            }
        }

        // Restore the array (optional step)
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.abs(arr[i]);
        }

        return repeatingNumbers;
    }

    /**
     * Finds and prints all repeating numbers in the given array.
     *
     * @param arr The input array containing elements in the range [0, n-1].
     * @param n   The size of the array.
     *
     * The algorithm works by:
     * 1. Iterating through the array and using arr[i] % n as an index.
     * 2. Incrementing the value at that index by n to mark occurrences.
     * 3. In a second pass, identifying indices where arr[i] / n >= 2,
     *    indicating that the number has appeared more than once.
     * 4. Restoring the original array by taking arr[i] % n.
     */
    public static List<Integer> findDuplicatesUsingPlusNMarking(int[] arr, int n) {

        // Step 1: Mark occurrences by incrementing values at specific indices
        for (int i = 0; i < n; i++) {
            int index = arr[i] % n; // Get the index within range [0, n-1]
            arr[index] += n;        // Increment the value at that index by n
        }

        // Step 2: Identify repeating elements
        List<Integer> repeatingNumbers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            // If the value at arr[i] is >= 2 * n, the number i has appeared more than once
            if ((arr[i] / n) >= 2) {
                repeatingNumbers.add(i);
            }
        }

        // Step 3: Restore the original array
        for (int i = 0; i < n; i++) {
            arr[i] = arr[i] % n; // Restore original value by taking modulo n
        }

        return repeatingNumbers;
    }

    /**
     * Problem: Given an array arr[] containing n integers where each integer is between 1 and (n-1) (inclusive).
     * There is only one duplicate element, find the duplicate element in O(n) time complexity and O(1) space.
     *
     * Solution: The problem can be solved using the Floyd's Tortoise and Hare (Cycle Detection) algorithm,
     * which is commonly used for detecting cycles in linked lists. The approach works efficiently in
     * 𝑂(𝑛) time complexity and O(1) space complexity.
     *
     * Explanation
     * Concept:
     * The array values act as "pointers" to the indices.
     * Treat the array as a linked list where arr[i] points to arr[arr[i]].
     * The problem reduces to finding the start of the cycle in this "linked list," which represents the duplicate value.
     *
     * Steps:
     * Use two pointers: a slow pointer and a fast pointer.
     * The slow pointer moves one step at a time, while the fast pointer moves two steps at a time.
     * When the two pointers meet, it indicates a cycle exists.
     * Reset one pointer to the start of the array and move both pointers one step at a time. The point where they meet is the duplicate element.
     *
     * @param arr The input array containing integers from 1 to (n-1).
     * @return The duplicate element.
     */
    public static int findDuplicateUsingFloyd(int[] arr) {
        // Step 1: Use Floyd's Tortoise and Hare to detect a cycle
        int slow = arr[0];
        int fast = arr[0];

        do {
            slow = arr[slow]; // Move slow by one step
            fast = arr[arr[fast]]; // Move fast by two steps
        } while (slow != fast);

        // Step 2: Find the entrance to the cycle (duplicate element)
        slow = arr[0]; // Reset slow to the start of the array
        while (slow != fast) {
            slow = arr[slow]; // Move slow by one step
            fast = arr[fast]; // Move fast by one step
        }

        return slow; // The duplicate element
    }




}
