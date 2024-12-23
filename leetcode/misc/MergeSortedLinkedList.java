package leetcode.misc;

import java.util.*;

/**
 * https://www.geeksforgeeks.org/merge-two-sorted-linked-lists/
 */
public class MergeSortedLinkedList {

    static Node mergeSortedLinkedList(Node head1, Node head2) {
        List<Integer> values = new ArrayList<>();
        while (head1 != null) {
            values.add(head1.data);
            head1 = head1.next;
        }

        while (head2 != null) {
            values.add(head2.data);
            head2 = head2.next;
        }

        Collections.sort(values);

        Node dummy = new Node(-1);
        Node tail = dummy;
        for(Integer val : values) {
            tail.next = new Node(val);
            tail = tail.next;
        }

        return dummy.next;
    }

    /**
     * The idea is to move ahead with node in the recursion whose node value is lesser. When any of the node reach the end then append the rest of the linked List.
     * Check which value is less from both the current nodes
     * The one with less value makes a recursion call by moving ahead with that pointer and simultaneously append that recursion call with the node
     * Also put two base cases to check whether one of the linked lists will reach the NULL, then append the rest of the linked list.
     * @param head1
     * @param head2
     * @return
     */
    static Node mergeSortedLinkedListRecursive(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        // Pick either a or b, and recur
        Node result;
        if (head1.data < head2.data) {
            result = head1;
            result.next = mergeSortedLinkedListRecursive(head1.next, head2);
        } else {
            result = head2;
            result.next = mergeSortedLinkedListRecursive(head1, head2.next);
        }
        return result;
    }

    static Node mergeSortedLinkedListIterative(Node head1, Node head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        Node dummy = new Node(-1);
        Node tail = dummy;
        while (head1 != null && head2 != null) {
            if (head1.data < head2.data) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }
            tail = tail.next;
        }
        if (head1 != null) {
            tail.next = head1;
        }
        if (head2 != null) {
            tail.next = head2;
        }
        return dummy.next;
    }

    static Node mergeKLists(Node[] arr, int K) {
        // Create a min-heap to store the heads of the lists
        PriorityQueue<Node> minHeap = new PriorityQueue<>((a, b) -> a.data - b.data);

        // Add the heads of all lists to the min-heap
        for (Node node : arr) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        // Create a dummy node to act as the head of the merged list
        Node dummy = new Node(-1);
        Node tail = dummy;

        // Extract the minimum node from the min-heap and add it to the merged list
        while (!minHeap.isEmpty()) {
            Node curr = minHeap.poll();
            tail.next = curr;
            tail = tail.next;

            // Add the next node of the current list to the min-heap
            if (curr.next != null) {
                minHeap.offer(curr.next);
            }
        }

        return dummy.next;
    }
}
