package leetcode.medium;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class L347TopKSmallestElement {

    /**
     * K’th smallest element in an unsorted array using Priority Queue(Max-Heap):
     * The intuition behind this approach is to maintain a maxheap (priority queue) of size K while iterating through the array.
     * Doing this ensures that the max heap always contains the K smallest elements encountered so far.
     * If the size of the min heap exceeds K, remove the largest element this step ensures that the heap maintains the K smallest elements encountered so far.
     * In the end, the max heap contains the K smallest elements of the array.
     *
     */
    public static List<Integer> findKSmallestElements(int[] nums, int k) {
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

        // Step 3: Convert min heap to list and return
        List<Integer> kSmallestElements = new ArrayList<>(maxHeap);
        return kSmallestElements;
    }
}
