package leetcode.cache;

import java.util.*;

/**
 * To implement an LFU (Least Frequently Used) cache, we need to ensure that the operations get and put run in O(1) time on average. We can achieve this by using a combination of a few data structures:
 *
 * HashMap for Cache: This will store the key-value pairs. We will store the keys and their corresponding values in this map.
 * HashMap for Frequency Counts: This will map a frequency to a doubly linked list of keys that have that frequency. The frequency of each key will be tracked here.
 * Doubly Linked List: This will help maintain the order of the least recently used keys within the same frequency group.
 * The key idea is to keep track of the frequency of access for each key and maintain the least recently used keys in case of a tie in frequency.
 *
 * Steps to implement:
 * get(int key):
 *
 * If the key exists in the cache, we retrieve its value, increment its frequency, and move it to the appropriate frequency list.
 * If the key doesn't exist, return -1.
 * put(int key, int value):
 *
 * If the key already exists, update its value and increment its frequency.
 * If the key doesn't exist and the cache is at capacity, remove the least frequently used (LFU) key. In case of a tie in frequency, remove the least recently used key.
 * Insert the new key-value pair into the cache and initialize its frequency.
 *
 */
class LFUCacheUsingLinkedHashSet {

    private final int capacity;
    private int size;
    private final Map<Integer, Integer> cache;  // key -> value
    private final Map<Integer, Integer> freq;   // key -> frequency
    private final Map<Integer, LinkedHashSet<Integer>> freqList; // frequency -> set of keys
    private int minFreq;

    public LFUCacheUsingLinkedHashSet(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.cache = new HashMap<>();
        this.freq = new HashMap<>();
        this.freqList = new HashMap<>();
        this.minFreq = -1;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1;
        }

        // Get the current value and frequency of the key
        int currentValue = cache.get(key);
        int currentFreq = freq.get(key);

        // Remove the key from the current frequency list
        freqList.get(currentFreq).remove(key);
        if (freqList.get(currentFreq).isEmpty() && currentFreq == minFreq) {
            minFreq++;
        }

        // Increment the frequency and add the key to the new frequency list
        freq.put(key, currentFreq + 1);
        freqList.computeIfAbsent(currentFreq + 1, k -> new LinkedHashSet<>()).add(key);

        return currentValue;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (cache.containsKey(key)) {
            // Update the value and frequency if key exists
            cache.put(key, value);
            get(key);  // This will update the frequency as well
            return;
        }

        if (size == capacity) {
            // Remove the least frequently used (LFU) key
            LinkedHashSet<Integer> leastFreqKeys = freqList.get(minFreq);
            int keyToRemove = leastFreqKeys.iterator().next(); // remove first not which is the least recently used of this frequency
            leastFreqKeys.remove(keyToRemove);
            cache.remove(keyToRemove);
            freq.remove(keyToRemove);
            size--;
        }

        // Insert the new key
        cache.put(key, value);
        freq.put(key, 1);
        freqList.computeIfAbsent(1, k -> new LinkedHashSet<>()).add(key);
        minFreq = 1;
        size++;
    }
}


