package com.dexhigh.ome.orderbook;

import com.dexhigh.ome.order.Node;
import com.dexhigh.ome.order.Order;

public class OrderBook {

    // Function to get the height of the tree
    private int height(Node N) {
        if (N == null)
            return 0;

        return N.height;
    }

    private int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // Function to right rotate subtree rooted with y
    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // Function to left rotate subtree rooted with x
    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // Get Balance factor of node N
    private int getBalance(Node N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    public Node addOrder(Node node, Order order) {

        /* 1. Perform the normal BST insertion */
        if (node == null)
            return (new Node(order));

        if (order.getId() < node.order.getId())
            node.left = addOrder(node.left, order);
        else if (order.getId() > node.order.getId())
            node.right = addOrder(node.right, order);
        else // Duplicate order ids not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left), height(node.right));

		/* 3. Get the balance factor of this ancestor
			node to check whether this node became
			unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && order.getId() < node.left.order.getId())
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && order.getId() > node.right.order.getId())
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && order.getId() > node.left.order.getId()) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && order.getId() < node.right.order.getId()) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // Pre-Order Traversal
    public void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.order.getId() + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    // In-Order Traversal
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.print(node.order.getId() + " ");
            inOrder(node.right);
        }
    }

    private Node minValueNode(Node node) {
        Node current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

    public Node cancelOrder(Node root, int key) {

        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // If the key to be deleted is smaller than the root's key, then it lies in left subtree
        if (key < root.order.getId()) {
            root.left = cancelOrder(root.left, key);
        }

        // If the key to be deleted is greater than the root's key, then it lies in right subtree
        else if (key > root.order.getId()) {
            root.right = cancelOrder(root.right, key);
        }

        // if key is same as root's key, then this is the node to be deleted
        else {
            // node with only one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (root.left == null) {
                    temp = root.right;
                } else {
                    temp = root.left;
                }

                // No child case
                if (temp == null) {
                    root = null;
                }
                // One child case
                else {
                    root = temp; // Copy the contents of the non-empty child
                }
            } else {
                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                Node temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.order = temp.order;

                // Delete the inorder successor
                root.right = cancelOrder(root.right, temp.order.getId());
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public Node cancelAll() {
        return null;
    }
}
