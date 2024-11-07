package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L16ThreeSumCloset {

    /**
     * https://leetcode.com/problems/3sum-closest/
     * https://www.youtube.com/watch?v=FJr3UfwtOKQ
     *
     * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
     * Return the sum of the three integers.
     * You may assume that each input would have exactly one solution.
     */
    public static int threeSum(int[] nums, int target) {

        // Step 1: Sort the input array so that we can use 2 pointer technique
        Arrays.sort(nums);

        // Initialize variables to track the closest sum and indices of the closest pair
        int closestSum = Integer.MAX_VALUE;
        // Step 2: Iterate through the array
        for (int i = 0; i < nums.length - 2; i++) {

            // Set pointers for the remaining elements in the array
            int left = i + 1;
            int right = nums.length - 1;

            // Step 3: Use two pointers to traverse remaining elements
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                // update closest sum
                int diff = Math.abs(target - sum);
                if(diff < Math.abs(target - closestSum)) {
                    closestSum = sum;
                }

                if (sum == target){
                    return sum;

                } else if (sum < target) {
                    // If the sum is less than the target, move the left pointer forward
                    left++;

                } else {
                    // If the sum is greater than the target, move the right pointer backward
                    right--;
                }
            }
        }

        return closestSum;
    }


}
