package leetcode.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lfu-cache/description/
 * Problem:
 * Design and implement a data structure for a Least Frequently Used (LFU) cache.
 * Implement the LFUCache class:
 * LFUCache(int capacity) Initializes the object with the capacity of the data structure.
 * int get(int key) Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
 * void put(int key, int value) Update the value of the key if present, or inserts the key if not already present. When the cache reaches its capacity, it should invalidate and remove the least frequently used key before inserting a new item. For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used key would be invalidated.
 * To determine the least frequently used key, a use counter is maintained for each key in the cache. The key with the smallest use counter is the least frequently used key.
 * When a key is first inserted into the cache, its use counter is set to 1 (due to the put operation). The use counter for a key in the cache is incremented either a get or put operation is called on it.
 * The functions get and put must each run in O(1) average time complexity.
 *
 * Solution:
 *To design an LFU Cache with O(1) average time complexity for get and put, we can use a combination of:
 * HashMaps for fast lookups:
 *  One map to store the key-value pairs.
 *  Another map to track the frequency of each key.
 * Doubly Linked Lists to maintain order within frequency groups (ensuring ties are resolved by recency).
 *
 * Approach
 * Use a HashMap (keyToNode) to map keys to their corresponding nodes.
 * Use another HashMap (freqToDLL) to map frequencies to doubly linked lists, where each list contains all keys with the same frequency.
 * Maintain a minFreq variable to track the minimum frequency in the cache.
 * Node structure: Stores the key, value, and frequency.
 * Operations:
 *  get(key): Retrieve the value of the key and increment its frequency.
 *  put(key, value): Insert or update the key. If the cache exceeds capacity, remove the least frequently used key.
 */

class LFUUsingCustomDoublyLinkedList {

    private final int capacity;
    private int minFreq;
    private final Map<Integer, Node> keyToNode;
    private final Map<Integer, DoublyLinkedList> freqToDLL;

    public LFUUsingCustomDoublyLinkedList(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyToNode = new HashMap<>();
        this.freqToDLL = new HashMap<>();
    }

    private static class Node {
        int key, value, frequency;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.frequency = 1; // New nodes start with frequency 1
        }
    }

    private static class DoublyLinkedList {
        Node head, tail;

        DoublyLinkedList() {
            head = new Node(0, 0); // Dummy head
            tail = new Node(0, 0); // Dummy tail
            head.next = tail;
            tail.prev = head;
        }

        void addNode(Node node) {
            // Add node right after head
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        boolean isEmpty() {
            return head.next == tail;
        }

        Node removeLastNode() {
            if (isEmpty()) return null;
            Node lastNode = tail.prev;
            removeNode(lastNode);
            return lastNode;
        }
    }


    public int get(int key) {
        if (!keyToNode.containsKey(key)) {
            return -1;
        }
        Node node = keyToNode.get(key);
        updateNodeFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyToNode.containsKey(key)) {
            Node node = keyToNode.get(key);
            node.value = value; // Update value
            updateNodeFrequency(node);
        } else {
            if (keyToNode.size() >= capacity) {
                // Evict least frequently used node
                DoublyLinkedList minFreqList = freqToDLL.get(minFreq);
                Node toRemove = minFreqList.removeLastNode();
                keyToNode.remove(toRemove.key);
            }

            // Insert new node
            Node newNode = new Node(key, value);
            keyToNode.put(key, newNode);
            freqToDLL.computeIfAbsent(1, k -> new DoublyLinkedList()).addNode(newNode);
            minFreq = 1; // Reset min frequency
        }
    }

    private void updateNodeFrequency(Node node) {
        int freq = node.frequency;
        DoublyLinkedList list = freqToDLL.get(freq);
        list.removeNode(node);

        if (freq == minFreq && list.isEmpty()) {
            minFreq++;
        }

        node.frequency++;
        freqToDLL.computeIfAbsent(node.frequency, k -> new DoublyLinkedList()).addNode(node);
    }

    public static void main(String[] args) {
        LFUUsingCustomDoublyLinkedList lfuCache = new LFUUsingCustomDoublyLinkedList(2);

        lfuCache.put(1, 1); // Cache: {1=1}
        lfuCache.put(2, 2); // Cache: {1=1, 2=2}
        System.out.println(lfuCache.get(1)); // Returns 1. Cache: {2=2, 1=1}
        lfuCache.put(3, 3); // Evicts key 2. Cache: {1=1, 3=3}
        System.out.println(lfuCache.get(2)); // Returns -1 (not found)
        System.out.println(lfuCache.get(3)); // Returns 3. Cache: {1=1, 3=3}
        lfuCache.put(4, 4); // Evicts key 1. Cache: {3=3, 4=4}
        System.out.println(lfuCache.get(1)); // Returns -1 (not found)
        System.out.println(lfuCache.get(3)); // Returns 3. Cache: {4=4, 3=3}
        System.out.println(lfuCache.get(4)); // Returns 4. Cache: {3=3, 4=4}
    }
}

