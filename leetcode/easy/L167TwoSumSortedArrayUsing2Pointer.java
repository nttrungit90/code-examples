package leetcode.easy;

public class L167TwoSumSortedArrayUsing2Pointer {


    /**
     * Leetcode 167
     * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/description/?envType=study-plan-v2&envId=top-interview-150
     * It initializes two pointers (left and right) at the beginning and end of the sorted array.
     * It iterates through the array using the two pointers approach:
     * If the sum of the elements at the pointers equals the target, it finds the indices of these elements in the original array and returns them.
     * If the sum is less than the target, it moves the left pointer forward to increase the sum.
     * If the sum is greater than the target, it moves the right pointer backward to decrease the sum.
     * If no solution is found after traversing the entire array, it returns an empty array.
     */
    public int[] twoSumUsingPointer(int[] nums, int target) {
        // Initialize two pointers
        int left = 0;
        int right = nums.length - 1;

        // Iterate until the pointers meet
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                // If the pair sums up to the target, find the indices in the original array
                return new int[] {left + 1, right + 1};
            } else if (sum < target) {
                // If the sum is less than the target, move the left pointer forward
                left++;
            } else {
                // If the sum is greater than the target, move the right pointer backward
                right--;
            }
        }
        // If no solution is found, return an empty array
        return new int[0];
    }


    /**
     * Find the closest pair from 1 sorted arrays
     * https://www.geeksforgeeks.org/given-sorted-array-number-x-find-pair-array-whose-sum-closest-x/
     * https://www.youtube.com/watch?v=QMWBRnolFCU
     *
     * Given one sorted arrays and a number x,
     * the task is to find the pair arr[i] + arr[j] such that absolute value of (arr[i] + arr[j] – target) is minimum.
     */
    public static int[] findClosestPairIndices(int[] nums, int target) {
        if (nums == null || nums.length < 2)
            throw new IllegalArgumentException("Array should contain at least two elements");

        // Initialize two pointers
        int left = 0;
        int right = nums.length - 1;

        // Initialize variables to track the closest sum and indices of the closest pair
        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[2];

        // Iterate through the array until the pointers meet
        while (left < right) {
            // Calculate the sum of the current pair
            int sum = nums[left] + nums[right];

            // Calculate the absolute difference between the sum and the target
            int diff = Math.abs(sum - target);

            // Update the closest sum and indices if the current sum is closer to the target
            if (diff < minDiff) {
                minDiff = diff;
                result[0] = left;
                result[1] = right;
            }

            // Move the pointers based on the comparison with the target
            if (sum == target) {
                // If the sum is equal to the target, return the indices immediately
                return new int[]{left, right};

            } else if (sum < target) {
                left++;

            } else {
               right--;
            }
        }

        // Return the indices of the closest pair
        return result;
    }

    /**
     * Find the closest pair from 2 sorted arrays
     * https://www.geeksforgeeks.org/given-two-sorted-arrays-number-x-find-pair-whose-sum-closest-x/
     * Given two sorted arrays arr1[0…m-1] and arr2[0..n-1], and a number x,
     * the task is to find the pair arr1[i] + arr2[j] such that absolute value of (arr1[i] + arr2[j] – x) is minimum.
     * @param nums1
     * @param nums2
     * @param target
     * @return
     */
    public static int[] findClosestPairIndicesFromTwoSortedArray(int[] nums1, int[] nums2, int target) {
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0)
            throw new IllegalArgumentException("Arrays should not be null or empty");

        // Initialize two pointers
        int left = 0;
        int right = nums2.length - 1;

        // Initialize variables to track the closest sum and indices of the closest pair
        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[2];

        // Iterate through the array until the pointers meet
        while (left < nums1.length && right >= 0) {
            // Calculate the sum of the current pair
            int sum = nums1[left] + nums2[right];

            // Calculate the absolute difference between the sum and the target
            int diff = Math.abs(sum - target);

            // Update the closest sum and indices if the current sum is closer to the target
            if (diff < minDiff) {
                minDiff = diff;
                result[0] = left;
                result[1] = right;
            }

            // Move the pointers based on the comparison with the target
            if (sum == target) {
                // If the sum is equal to the target, return the indices immediately
                return new int[]{left, right};

            } else if (sum < target) {
                left++;

            } else {
                right--;
            }
        }

        // Return the indices of the closest pair
        return result;
    }


}
