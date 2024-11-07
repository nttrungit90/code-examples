package leetcode.medium;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class L215KthSmallestElement {

    /**
     * https://leetcode.com/problems/kth-largest-element-in-an-array/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=aXJ-p3Qa4TY&t=328s
     * @param nums
     * @param k
     * @return
     */
    public int findKthSmallest(int[] nums, int k) {

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // Max heap to store k smallest elements

        // Step 1: Fill the min heap with the first k elements
        for (int i = 0; i < k; i++) {
            maxHeap.offer(nums[i]);
        }

        // Step 2: For remaining elements, if smaller than the root of max heap, remove root and insert the element
        for (int i = k; i < nums.length; i++) {
            if (nums[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.offer(nums[i]);
            }
        }

        // because it is max heap, the root node is the largest element of k smallest element, so it is the largest kth
        return maxHeap.poll();

    }
}
