package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L15ThreeSum {

    /**
     * https://leetcode.com/problems/3sum/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=VuWCJXeiwy8
     *
     * Given an integer array nums, return all the triplets [nums[i], nums[j], nums[k]]
     * such that i != j, i != k, and j != k, and nums[i] + nums[j] + nums[k] == 0.
     * Notice that the solution set must not contain duplicate triplets.
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        // Step 1: Sort the input array so that we can use 2 pointer technique
        Arrays.sort(nums);

        // Step 2: Iterate through the array
        for (int i = 0; i < nums.length - 2; i++) {
            // skip current nums[i] element as we processed the same element before
            if(i != 0 && nums[i] == nums[i - 1]) continue;

            // Set pointers for the remaining elements in the array
            int left = i + 1;
            int right = nums.length - 1;

            // Step 3: Use two pointers to find 2 other elements which make a triple
            while (left < right) {
                if(left != i + 1 && nums[left] == nums[left - 1]) {
                    // skip current nums[left] element as we processed the same element before
                    left++;
                    continue;
                }

                if(right != nums.length - 1 && nums[right] == nums[right + 1]) {
                    // skip current nums[right] element as we processed the same element before
                    right--;
                    continue;
                }

                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    // If triplet sum is zero, add it to the result list
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // Move pointers to next unique elements
                    left++;
                    right--;

                } else if (sum < 0) {
                    // If the sum is less than the target, move the left pointer forward
                    left++;

                } else {
                    // If the sum is greater than the target, move the right pointer backward
                    right--;
                }
            }
        }

        return result;
    }


}
