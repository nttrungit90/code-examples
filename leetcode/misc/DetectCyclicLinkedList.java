package leetcode.misc;

import java.util.HashSet;
import java.util.Set;

public class DetectCyclicLinkedList {

    /**
     * https://www.geeksforgeeks.org/detect-loop-in-a-linked-list/
     * Given the head of a linked list that may contain a loop.
     * A loop means that the last node of the linked list is connected back to a node in the same list.
     */
    public static Node hasCycle(Node head) {
        Set<Node> visited = new HashSet<>();
        Node current = head;
        while (current != null) {
            // If the node is already in the set, there's a cycle
            if (visited.contains(current)) {
                return current;
            }
            // Add the node to the set
            visited.add(current);
            current = current.next;
        }
        // No cycle detected
        return null;
    }

    public static Node hasCycleUsingFloyd(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        // Initialize two pointers, slow and fast
        Node slow = head;
        Node fast = head;

        // Traverse the list
        while (fast != null && fast.next != null) {

            // Move slow pointer by one step
            slow = slow.next;

            // Move fast pointer by two steps
            fast = fast.next.next;

            // Detect loop
            if (slow == fast) {

                // Move slow to head, keep fast at meeting point
                slow = head;

                // Move both one step at a time until they meet again
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }

                // Return the meeting node, which is the
                // start of the loop
                return slow;
            }
        }

        // No loop found
        return null;
    }


}

