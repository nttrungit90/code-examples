package leetcode.misc;

import java.util.HashSet;

public class Check2Sum {

    /**
     * https://www.geeksforgeeks.org/check-if-pair-with-given-sum-exists-in-array/#better-approach-using-hashset-on-time-and-on-space
     * @param arr
     * @param target
     * @return
     */
    public boolean twoSum(int[] arr, int target){
        // Create a HashSet to store the elements
        HashSet<Integer> set = new HashSet<>();

        // Iterate through each element in the array
        for(int elem : arr) {
            // Calculate the complement that added to current elem, equals the target
            int complement = target - elem;

            // Check if the complement exists in the set
            if(set.contains(complement)) {
                return true;
            }
            // Add the current element to the set
            set.add(elem);
        }
        // If no pair is found
        return false;
    }
}
