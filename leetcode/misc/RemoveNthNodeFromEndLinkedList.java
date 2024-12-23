package leetcode.misc;

public class RemoveNthNodeFromEndLinkedList {


    static Node removeNthFromEnd(Node head, int N) {

        // Calculate the length of the linked list
        int length = 0;
        Node curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }

        // Calculate the position to remove from front
        int target = length - N + 1;

        // If target is 1, remove the head node
        if (target == 1) {
            return head.next;
        }

        // Traverse to the node just before the target
        curr = head;
        for (int i = 1; i < target - 1; i++) {
            curr = curr.next;
        }

        // Remove the target node
        curr.next = curr.next.next;

        return head;
    }

    /**
     * The idea is to first move the fast pointer N steps ahead, then move both fast and slow pointers together
     * until fast reaches the end. The slow pointer will then be just before the node to be removed,
     * allowing to update the next pointer to skip the target node.
     *
     * @param head
     * @param n
     * @return
     */
    public static Node removeNthFromEndUsing2Pointer(Node head, int n) {
        // Step 1: Create a dummy node pointing to the head
        Node dummy = new Node(0);
        dummy.next = head;

        // Step 2: Use two pointers: `first` and `second`
        Node first = dummy;
        Node second = dummy;

        // Step 3: Move `first` pointer `n + 1` steps ahead
        for (int i = 0; i <= n; i++) {
            first = first.next;
        }

        // Step 4: Move both pointers until `first` reaches the end
        while (first != null) {
            first = first.next;
            second = second.next;
        }

        // Step 5: Remove the nth node
        second.next = second.next.next;

        // Return the updated list
        return dummy.next;
    }

}
