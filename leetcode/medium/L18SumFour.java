package leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class L18SumFour {

    /**
     * https://leetcode.com/problems/4sum/
     * https://www.youtube.com/watch?v=8_bJLk5up_M
     *
     * @param nums
     * @param target
     * @return
     */
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> quadruplets = new ArrayList<>();
        Arrays.sort(nums);

        for(int i = 0; i < nums.length - 3; i++) {
            if(i != 0 && nums[i] == nums[i - 1]) continue;

            for(int j = i + 1; j < nums.length - 2; j++) {
                if(j != i + 1 && nums[j] == nums[j - 1]) continue;

                int left = j + 1;
                int right = nums.length - 1;

                // Step 3: Use two pointers to find quadruplet
                while (left < right) {
                    if(left != j + 1 && nums[left] == nums[left - 1]) {
                        // skip current nums[left] element as we processed the same element before
                        left++;
                        continue;
                    }

                    if(right != nums.length - 1 && nums[right] == nums[right + 1]) {
                        // skip current nums[right] element as we processed the same element before
                        right--;
                        continue;
                    }

                    long sum = (long)nums[i] + (long)nums[j] + (long)nums[left] + (long)nums[right];
                    if (sum == target) {
                        // If quadruplet sum is zero, add it to the result list
                        quadruplets.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));

                        // Move pointers to next unique elements
                        left++;

                    } else if (sum < target) {
                        // If the sum is less than the target, move the left pointer forward
                        left++;

                    } else {
                        // If the sum is greater than the target, move the right pointer backward
                        right--;
                    }
                }

            }
        }

        return quadruplets;
    }
}
