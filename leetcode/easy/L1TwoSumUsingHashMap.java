package leetcode.easy;

import java.util.*;

public class L1TwoSumUsingHashMap {

    /**
     * https://leetcode.com/problems/two-sum/?envType=study-plan-v2&envId=top-interview-150
     * This solution using Single-Pass Hash Table. Two-Pass Hash Table is also ok and more easier to understand.
     */
    public int[] twoSumUsingHashMap(int[] nums, int target) {

        // Create a HashMap to store the elements and indices of visited elements
        Map<Integer, Integer> map = new HashMap<>();

        // iterate through the array
        for(int i = 0; i < nums.length; i++) {
            // Calculate the complement needed to reach the target
            int complement = target - nums[i];

            // check if the complement exists in the map
            if(map.containsKey(complement)) {
                // If the complement exists, return the indices of the two elements whose sum equals the target
                return new int[]{map.get(complement), i};
            }

            // If the complement doesn't exist, store the current element and its index in the hashmap
            map.put(nums[i], i);

        }

        // If no solution is found, return an empty array
        return new int[0];

    }

}
