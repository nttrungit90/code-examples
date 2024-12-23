package leetcode.misc;

public class DeleteLastOccurrenceOfItemInLinkedList {
    /**
     * https://www.geeksforgeeks.org/delete-last-occurrence-of-an-item-from-linked-list/
     *
     */
    public void deleteLastOccurrence(Node head, int key) {
        if (head == null) return; // Empty list

        Node lastOccurrence = null;
        Node lastOccurrencePrev = null;
        Node current = head;
        Node prev = null;

        // Traverse the list to find the last occurrence
        while (current != null) {
            if (current.data == key) {
                lastOccurrence = current;
                lastOccurrencePrev = prev;
            }
            prev = current;
            current = current.next;
        }

        // If the key was not found, do nothing
        if (lastOccurrence == null) return;

        // If the last occurrence is the head node
        if (lastOccurrence == head) {
            head = head.next;
        } else {
            lastOccurrencePrev.next = lastOccurrence.next;
        }
    }
}
