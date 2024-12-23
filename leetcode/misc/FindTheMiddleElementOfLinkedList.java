package leetcode.misc;


/**
 * https://www.geeksforgeeks.org/write-a-c-function-to-print-the-middle-of-the-linked-list/
 */
public class FindTheMiddleElementOfLinkedList {

    /**
     * The idea is to traversing the entire linked list once to count the total number of nodes.
     * After determining the total count, traverse the list again and stop at the (count/2)th node to return its value.
     * This method requires two passes through the linked list to find the middle element.
     * Time Complexity: O(2 * n) = O(n) where n is the number of nodes in the linked list.
     * Auxiliary Space: O(1)
     * @param head
     * @return
     */
    static int getMiddle(Node head) {

        // Finding the length of the list
        int length = getLength(head);

        // traverse till we reached half of length
        int mid_index = length / 2;
        while (mid_index > 0) {
            head = head.next;
            mid_index--;
        }
        // temp will be storing middle element
        return head.data;
    }

    // Helper function to find length of linked list
    static int getLength(Node head) {

        // Variable to store length
        int length = 0;

        // Traverse the entire linked list and increment
        // length by 1 for each node
        while (head != null) {
            length++;
            head = head.next;
        }

        // Return the number of nodes in the linked list
        return length;
    }


    /**
     * We can use the Hare and Tortoise Algorithm to find the middle of the linked list.
     * Traverse linked list using a slow pointer (slow_ptr) and a fast pointer (fast_ptr ).
     * Move the slow pointer to the next node(one node forward) and the fast pointer to the next of the next node(two nodes forward).
     * When the fast pointer reaches the last node or NULL, then the slow pointer will reach the middle of the linked list.
     *
     * In case of odd number of nodes in the linked list, slow_ptr will reach the middle node when fast_ptr will reach the last node
     * and in case of even number of nodes in the linked list, slow_ptr will reach the middle node when fast_ptr will become NULL.
     * @param head
     * @return
     */
    static int getMiddleWith2Pointer(Node head) {

        // Initialize the slow and fast pointer to the head
        // of the linked list
        Node slowPtr = head;
        Node fastPtr = head;

        while (fastPtr != null && fastPtr.next != null) {

            // Move the fast pointer by two nodes
            fastPtr = fastPtr.next.next;

            // Move the slow pointer by one node
            slowPtr = slowPtr.next;
        }

        // Return the slow pointer which is currently
        // pointing to the middle node of the linked list
        return slowPtr.data;
    }


}
