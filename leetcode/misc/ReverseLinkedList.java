package leetcode.misc;

public class ReverseLinkedList {
    /**
     * https://www.geeksforgeeks.org/reverse-a-linked-list/
     */
    static Node reverseLinkedListIterative(Node head) {
        // Initialize three pointers: curr, prev and next
        Node curr = head, prev = null, next;

        // Traverse all the nodes of Linked List
        while (curr != null) {

            // Store next
            next = curr.next;

            // Reverse current node's next pointer
            curr.next = prev;

            // Move pointers one position ahead
            prev = curr;
            curr = next;
        }

        // Return the head of reversed linked list
        return prev;

    }

    static Node reverseLinkedListRecursive(Node head) {
        // If we have reached the last node or the linked list is empty, simply return the head of linked list
        if (head == null || head.next == null) {
            return head;
        }

        // reverse the rest of linked list and put the first
        // element at the end
        Node rest = reverseLinkedListRecursive(head.next);

        // Make the current head as last node of remaining
        // linked list
        head.next.next = head;

        // Update next of current head to NULL
        head.next = null;

        // Return the reversed linked list
        return rest;
    }

}

class Node {
    int data;
    Node next;

    Node(int new_data) {
        data = new_data;
        next = null;
    }
}