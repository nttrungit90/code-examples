package leetcode.misc;

import java.util.HashSet;
import java.util.Set;

public class RemoveLoopInCyclicLinkedList {

    /**
     * https://www.geeksforgeeks.org/detect-and-remove-loop-in-a-linked-list/
     * Given the head of a linked list that may contain a loop.
     * A loop means that the last node of the linked list is connected back to a node in the same list.
     * The task is to Remove the loop from the linked list (if it exists).
     */
    public static void detectAndRemoveLoop(Node head) {
        Set<Node> visited = new HashSet<>();
        Node current = head;
        Node prev = null;
        while (current != null) {
            // If the node is already in the set, there's a cycle
            if (visited.contains(current)) {
                prev.next = null;
                return;
            }
            // Add the node to the set
            visited.add(current);
            prev = current;
            current = current.next;
        }
    }

    public static void detectAndRemoveLoopUsingFloyd(Node head) {
        if (head == null || head.next == null) {
            return;
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

                // Step 3: Remove the loop by finding previous element starting loop and remove the next link
                Node loopStart = slow;
                Node current = loopStart;
                while (current.next != loopStart) {
                    current = current.next;
                }
                current.next = null; // Break the loop

            }
        }
    }

}

