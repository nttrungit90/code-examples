package leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class L215KthLargestElement {

    /**
     * https://leetcode.com/problems/kth-largest-element-in-an-array/?envType=study-plan-v2&envId=top-interview-150
     * https://www.youtube.com/watch?v=aXJ-p3Qa4TY&t=328s
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Min heap to store k largest elements

        // Step 1: Fill the min heap with the first k elements
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        // Step 2: For remaining elements, if larger than the root of min heap, remove root and insert the element
        // after all, min heap will contains k largest element of the input array nums
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }

        // because it is min heap, the root node is the smallest element of k largest element, so it is the largest kth
        return minHeap.poll();

    }
}
