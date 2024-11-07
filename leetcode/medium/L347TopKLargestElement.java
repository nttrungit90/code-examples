package leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class L347TopKLargestElement {

    /**
     * K’th smallest element in an unsorted array using Priority Queue(Min-Heap):
     * The intuition behind this approach is to maintain a minheap (priority queue) of size K while iterating through the array.
     * Doing this ensures that the min heap always contains the K largest elements encountered so far.
     * If the size of the min heap exceeds K, remove the smallest element this step ensures that the heap maintains the K largest elements encountered so far.
     * In the end, the min heap contains the K largest elements of the array.
     *
     */
    public static List<Integer> findKLargestElements(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(); // Min heap to store k largest elements

        // Step 1: Fill the min heap with the first k elements
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }

        // Step 2: For remaining elements, if larger than the root of min heap, remove root and insert the element
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }

        // Step 3: Convert min heap to list and return
        List<Integer> kLargestElements = new ArrayList<>(minHeap);
        return kLargestElements;
    }
}
