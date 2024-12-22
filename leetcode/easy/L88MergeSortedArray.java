package leetcode.easy;

/**
 https://leetcode.com/problems/merge-sorted-array/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class L88MergeSortedArray {
    /**
     * Two-pointer approach: Start comparing elements from the end of both arrays (nums1 and nums2).
     * This avoids overwriting elements in nums1 as the array is filled from the back.
     * Placement in nums1: The larger of the two elements (from nums1 or nums2) is placed in the last available position in nums1.
     * Handle remaining elements in nums2: If any elements are left in nums2 after the comparison loop, copy them directly to nums1.
     * There's no need to handle leftover elements in nums1 since they are already sorted and in place.
     * Time Complexity: O(m+n): Each element in nums1 and nums2 is processed once.
     * Space Complexity: O(1): The merging is done in place, requiring no additional space.
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Pointers for nums1, nums2, and the position to fill in nums1
        int p1 = m - 1; // Last element in the initialized portion of nums1
        int p2 = n - 1; // Last element in nums2
        int p = m + n - 1; // Last position in nums1

        // Iterate while there are elements to compare in nums1 and nums2
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1]; // Place the larger element
                p1--; // Move pointer in nums1
            } else {
                nums1[p] = nums2[p2]; // Place the larger element
                p2--; // Move pointer in nums2
            }
            p--; // Move the pointer in nums1 for the next position
        }

        // If there are remaining elements in nums2, copy them to nums1
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }

        // No need to copy remaining elements from nums1, as they're already in place
    }
}
