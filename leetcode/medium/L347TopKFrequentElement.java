package leetcode.medium;

import java.util.*;

public class L347TopKFrequentElement {

    /**
     * https://leetcode.com/problems/top-k-frequent-elements/submissions/1189556717/
     * https://www.youtube.com/watch?v=Wh3A29psE_Y&t=296s
     *
     * KtopKFrequent element in an unsorted array using Priority Queue(Min-Heap)
     */
    public static List<Integer> topKFrequent(int[] nums, int k) {

        // Step 1: Count frequency of each element
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Step 2: Create a min heap of size k
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(Map.Entry::getValue));

        // Step 3: Insert first k elements (or unique elements) into min heap
        for (Map.Entry<Integer, Integer> entry : frequencyMap.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);

            } else if (entry.getValue() > minHeap.peek().getValue()) {
                minHeap.poll();
                minHeap.offer(entry);
            }
        }

        // Step 4: Extract top k frequent elements from min heap
        List<Integer> topKFrequentElements = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            topKFrequentElements.add(minHeap.poll().getKey());
        }

        return topKFrequentElements;
    }
}
