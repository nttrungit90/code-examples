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

    public List<String> topKFrequentWord(String[] words, int k) {
        // Step 1: Count the frequency of each word
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        // The goal is to create a min-heap where:
        // Primary Order: Lower frequencies (less frequent words) should come to the top of the heap,
        // so we can remove the least frequent word when the heap size exceeds k.
        // Secondary Order: When two words have the same frequency, we want to keep the lexicographically smaller word
        // in the heap and discard the lexicographically larger word, so larger word should be on the top of heap
        Comparator<Map.Entry<String, Integer>> comparator = (a, b) ->
                a.getValue().equals(b.getValue()) ? // smaller first
                        b.getKey().compareTo(a.getKey()) :  // Lexicographical order if frequencies are equal
                        a.getValue() - b.getValue();         // Otherwise, by frequency

        // Step 2: Create the min-heap with the comparator
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(comparator);

        // Step 3: Build the min-heap with an optimized approach
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry); // Add the first k elements
            } else if (entry.getValue() > minHeap.peek().getValue() ||
                    (entry.getValue().equals(minHeap.peek().getValue()) && entry.getKey().compareTo(minHeap.peek().getKey()) < 0)) {
                // Only add if this entry has a higher frequency or is lexicographically smaller when frequencies are the same
                minHeap.poll(); // Remove the smallest element
                minHeap.offer(entry);
            }
        }

        // Step 4: Build the result list
        List<String> result = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            result.add(minHeap.poll().getKey());
        }

        // Step 5: Reverse the result list since we want highest frequency first
        Collections.reverse(result);

        return result;
    }
}
