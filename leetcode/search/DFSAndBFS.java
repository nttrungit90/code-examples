package leetcode.search;


import javax.swing.tree.TreeNode;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class DFSAndBFS {

    /**
     * DFS recursive on binary tree
     * @param root
     */
    public void traversePreOrderRecursive(Node root) {
        if(root != null) {
            // visit root node
            visit(root);

            // Recursively visit the left subtree
            traversePreOrderRecursive(root.left);

            // Recursively visit the right subtree
            traversePreOrderRecursive(root.right);
        }
    }

    /**
     * DFS on binary tree using stack
     * @param root
     */
    public void traversePreOrder(Node root) {
        // Create a stack to store nodes to be visited
        Stack<Node> stack = new Stack<>();

        // Push the root node onto the stack
        stack.push(root);

        // Traverse the binary tree using stack
        while (!stack.isEmpty()) {
            // Pop a node from the stack
            Node current = stack.pop();

            // visit the popped node
            visit(current);


            // Push the right child onto the stack first
            // so that left child is processed first (DFS)
            if (current.right != null) {
                stack.push(current.right);
            }

            if (current.left != null) {
                stack.push(current.left);
            }
        }
    }

    /**
     * Function to perform Depth First Search recursively
      */
    public void dfsRecursive(List<List<Integer>> graph, int start, boolean[] visited) {
        // Mark the current node as visited
        visited[start] = true;
        visit(start);

        // Traverse neighbors of the current node
        for (int neighbor : graph.get(start)) {
            // If the neighbor is not visited, recursively call dfs
            if (!visited[neighbor]) {
                dfsRecursive(graph, neighbor, visited);
            }
        }
    }


    /**
     *  Function to perform Depth First Search using stack
     */
    public void dfs(List<List<Integer>> graph, int start) {
        // Array to keep track of visited nodes
        boolean[] visited = new boolean[graph.size()];

        // Stack to store nodes to be visited
        Stack<Integer> stack = new Stack<>();

        // Push the starting node onto the stack
        stack.push(start);

        // Traverse the graph
        while (!stack.isEmpty()) {
            // Pop the top node from the stack
            int currentNode = stack.pop();

            // If the node has not been visited yet
            if (!visited[currentNode]) {
                // Mark the node as visited and visit it
                visited[currentNode] = true;
                visit(currentNode);

                // Traverse neighbors of the current node
                for (int neighbor : graph.get(currentNode)) {
                    // Push unvisited neighbors onto the stack
                    if (!visited[neighbor]) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }


    /**
     * Breadth First Search on binary tree using queue
     */
    public void bfs(Node root) {
        if (root == null) {
            return;
        }

        Queue<Node> queue = new LinkedList<>(); // Queue for BFS
        queue.add(root); // Enqueue the root node

        while (!queue.isEmpty()) {
            Node node = queue.poll(); // Dequeue a node
            visit(node);

            // Enqueue the left child if exists
            if (node.left != null) {
                queue.add(node.left);
            }

            // Enqueue the right child if exists
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }



    /**
     *  Function to perform Breadth First Search
     */
    public void bfs(List<List<Integer>> graph, int start) {

        // Array to keep track of visited nodes
        boolean[] visited = new boolean[graph.size()];

        // Queue to store nodes to be visited
        Queue<Integer> queue = new LinkedList<>();

        // Add the starting node to the queue and mark it as visited
        queue.offer(start);
        visited[start] = true;

        // Traverse the graph
        while (!queue.isEmpty()) {
            // Remove and retrieve the front node from the queue
            int node = queue.poll();

            // Print the visited node
            visit(node);

            // Traverse neighbors of the current node
            for (int neighbor : graph.get(node)) {
                // Add unvisited neighbors to the queue and mark them as visited
                if (!visited[neighbor]) {
                    queue.offer(neighbor);
                    visited[neighbor] = true;
                }
            }
        }
    }







    private void visit(Node node) {
    }
    private void visit(int node) {
    }



    class Node {
        int value;
        Node left;
        Node right;
    }

    private void visit(GraphNode node) {
    }
    class GraphNode {
        int value;
        List<GraphNode> adjacents;
    }
}
