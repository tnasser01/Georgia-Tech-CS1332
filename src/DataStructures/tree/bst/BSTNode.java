package DataStructures.tree.bst;

/**
 * This class represents a node in your DataStructures.tree.bst.BST.
 * DO NOT EDIT THIS FILE.
 *
 * @version 1.0
 * @author CS 1332 TAs
 */
public class BSTNode<T extends Comparable<? super T>> {
    private T data;
    private BSTNode<T> left;
    private BSTNode<T> right;

    /**
     * Create a DataStructures.tree.bst.BST node with the given data.
     *
     * @param data the data to be stored in this node.
     */
    public BSTNode(T data) {
        this.data = data;
    }

    /**
     * Get the data in this node.
     *
     * @return data in this node.
     */
    public T getData() {
        return data;
    }

    /**
     * Set the data in this node.
     *
     * @param data data to be placed into the node.
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Get the node to the left of this node.
     *
     * @return node to the left.
     */
    public BSTNode<T> getLeft() {
        return left;
    }

    /**
     * Set the node to the left of this node.
     *
     * @param left new node to the left.
     */
    public void setLeft(BSTNode<T> left) {
        this.left = left;
    }

    /**
     * Get the node to the right of this node.
     *
     * @return node to the right.
     */
    public BSTNode<T> getRight() {
        return right;
    }

    /**
     * Set the node to the right of this node.
     *
     * @param right new node to the right.
     */
    public void setRight(BSTNode<T> right) {
        this.right = right;
    }
}
