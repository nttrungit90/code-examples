package leetcode.cache;

import java.util.*;

/**
 * https://leetcode.com/problems/lru-cache/description/
 *
 * To design a data structure for an LRU Cache that adheres to the given constraints, we can use a combination of:
 *  HashMap for O(1) average-time key lookup.
 *  Custom doubly linked list for maintaining the order of usage (most recently used to least recently used) with efficient updates (O(1))
 * Approach:
 * Use a HashMap to store the key-value pairs, where the value is a reference to a node in the doubly linked list.
 * Use a Doubly Linked List to maintain the order of keys:
 *  The most recently used (MRU) node is at the head.
 *  The least recently used (LRU) node is at the tail.
 * When a key is accessed or updated:
 *  Move its corresponding node to the head of the list.
 * When the capacity is exceeded:
 *  Remove the tail node (LRU) and delete its entry from the HashMap.
 *
 * LinkedList vs Custom DoublyLinkedList
 * - In an LRU Cache: When you're using a LinkedList to track the usage order, every time you want to move a node (for example, when a key is accessed
 * and you want to move it to the front), you need to remove that node and reinsert it. If you're using a general-purpose LinkedList,
 * this operation will take O(n) time because it needs to traverse the list to find the node.
 * - Custom DoublyLinkedList: With a custom doubly linked list, you can directly access the node and remove it in O(1) time, which is much more efficient, especially for large caches.
 */
class LRUCacheUsingCustomDoublyLinkedList {
    private final int capacity;
    private final Map<Integer, Node> cache;
    private final Node head, tail; // custom doubly linked list to track usage order

    // Node class to hold key, value, and pointers to the previous and next nodes
    private static class Node {
        int key, value;
        Node prev, next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCacheUsingCustomDoublyLinkedList(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        // Dummy head and tail nodes for easier handling of edges
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1; // Key not found
        }
        Node node = cache.get(key);
        moveToHead(node); // Mark as recently used
        return node.value;
    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            // Update the value
            Node node = cache.get(key);
            node.value = value;
            moveToHead(node);

        } else {
            if (cache.size() >= capacity) {
                // Evict the least recently used node
                Node lru = tail.prev;
                removeNode(lru);
                cache.remove(lru.key);
            }
            // Add new node
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToHead(newNode);
        }
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }

    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    public static void main(String[] args) {
        LRUCacheUsingCustomDoublyLinkedList lruCache = new LRUCacheUsingCustomDoublyLinkedList(2);

        lruCache.put(1, 1); // Cache: {1=1}
        lruCache.put(2, 2); // Cache: {1=1, 2=2}
        System.out.println(lruCache.get(1)); // Returns 1. Cache: {2=2, 1=1}
        lruCache.put(3, 3); // Evicts key 2. Cache: {1=1, 3=3}
        System.out.println(lruCache.get(2)); // Returns -1 (not found)
        lruCache.put(4, 4); // Evicts key 1. Cache: {3=3, 4=4}
        System.out.println(lruCache.get(1)); // Returns -1 (not found)
        System.out.println(lruCache.get(3)); // Returns 3. Cache: {4=4, 3=3}
        System.out.println(lruCache.get(4)); // Returns 4. Cache: {3=3, 4=4}
    }
}



class LRUCacheUsingLinkedList {
    private int capacity;

    // Stores key-value pairs
    private Map<Integer, Integer> cacheMap;

    // Stores keys in the order of access
    private LinkedList<Integer> lruList;

    // Constructor to initialize the cache with a given
    // capacity
    LRUCacheUsingLinkedList(int capacity) {
        this.capacity = capacity;
        this.cacheMap = new HashMap<>();
        this.lruList = new LinkedList<>();
    }

    // Function to get the value for a given key
    public int get(int key) {
        if (!cacheMap.containsKey(key)) {
            return -1;
        }

        // Move the accessed key to the front (most recently used position)
        lruList.remove(key);
        lruList.addFirst(key);

        return cacheMap.get(key);
    }

    // Function to put a key-value pair into the cache
    public void put(int key, int value) {
        if (cacheMap.containsKey(key)) {

            // Update the value
            cacheMap.put(key, value);

            // Move the accessed key to the front (most recently used position)
            lruList.remove(key);
            lruList.addFirst(key);

        } else {

            // Add new key-value pair
            if (cacheMap.size() >= capacity) {
                // Remove the least recently used item
                int leastUsedKey = lruList.removeLast();
                cacheMap.remove(leastUsedKey);
            }
            cacheMap.put(key, value);
            lruList.addFirst(key);
        }
    }
}

/**
 * Using Java's LinkedHashMap is a clean and efficient way to implement an LRU Cache.
 * The LinkedHashMap class provides built-in support for maintaining insertion or access order
 * and allows customization of eviction policies, making it an ideal candidate for an LRU Cache implementation.
 */
class LRUCacheUsingLinkedHashMap {
    private int capacity;
    private Map<Integer, Integer> cache;
    public LRUCacheUsingLinkedHashMap(int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return size() > capacity;
            }
        };
    }

    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
}


