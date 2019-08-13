package DataStructures.tree.avl;

import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * DataStructures.tree.avl.AVL Tree
 * This implementation of DataStructures.tree.avl.AVL DataStructures.tree is built with DataStructures.tree.avl.AVLNode and supports
 * generic data types.
 *
 * DataStructures.tree.avl.AVL Tree contains the following key private helper methods:
 *    1. updateHeightAndBalanceFactor(DataStructures.tree.avl.AVLNode)
 *    2. rebalance(DataStructures.tree.avl.AVLNode)
 *    3. rightRotate(DataStructures.tree.avl.AVLNode)
 *    4. leftRotate(DataStructures.tree.avl.AVLNode)
 *    (plus private recursive methods for operations add,remove,get,etc...)
 *
 *    DataStructures.tree.avl.AVL Tree is a balanced BST which has a
 *    --height-balance property--:
 *      for every node, the heights of the left and right child will differ
 *      by a max of 1.
 *
 *    If any ancestor of the the node updated become unbalanced after
 *    an add or remove operation, balance is restored through one of 4 possible
 *    rotations:
 *      1. left
 *      2. right
 *      3. right-left
 *      4. left-right
 *
 *    Right rotation is performed when BF of a node == 2 and the BF of its
 *    right child >=0.
 *
 *    Left rotation is performed when BF of a node == -2 and the BF of
 *    its left child <= 0.
 *
 *    Right-Left rotation is performed when BF of a node == -2 and the BF of
 *    its right child == 1 (right elbow)
 *
 *    Left-right rotation is performed when BF of a node == 2 and the BF of
 *    its left child == -1 (left elbow)
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @version 1.0
 */
public class AVL<T extends Comparable<? super T>> implements AVLInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private AVLNode<T> root;
    private int size;

    /**
     * A no argument constructor that should initialize an empty DataStructures.tree.avl.AVL DataStructures.tree.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public AVL() {
    }

    /**
     * Initializes the DataStructures.tree.avl.AVL DataStructures.tree with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the DataStructures.tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public AVL(Collection<T> data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to " 
                    + "DataStructures.tree.avl.AVL Tree");
        }

        for (T elem: data) {
            if (elem == null) {
                throw new IllegalArgumentException("Cannot add null data to " 
                        + "DataStructures.tree.avl.AVL Tree");
            }
            add(elem);
        }
    }

    @Override
    public void add(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to DataStructures.tree.avl.AVL");
        }
        root = add(data, root);

    }

    /**
     * Private recursive helper method for add
     * This add method is similar to the add for BST.
     *
     * If the root is null, this is the place where the new node should be
     * added.  We create a new node with the data T and return it.
     *
     * If the root is not null we binary search left or right for the proper
     * position.  If T is < root.data, we set root.left to the return
     * value of recursing left.  If T > root, we set root.right to the return
     * value of recursing right.
     *
     * The next part is specific to the DataStructures.tree.avl.AVL Tree.
     * To maintain the AVLTrees balance,  we update the height and balance of
     * the current node and rebalance it.
     *
     * @param data The value of the node to add
     * @param root The root of the current subtree
     * @return The (potentially updated) child of the parent node that should
     * be set once the
     * recursive call returns
     */
    private AVLNode<T> add(T data, AVLNode<T> root) {

        if (root == null) {
            AVLNode<T> newNode = new AVLNode<T>(data);
            size++;
            return newNode;
        }

        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(add(data, root.getLeft()));
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(add(data, root.getRight()));
        }

        updateHeightAndBalanceFactor(root);
        return rebalance(root);

    }

    /**
     * Private rebalance method
     * This method gets called from the update operations (add, remove,
     * removeSuccessor) anytime a node's height and BF are updated.
     *
     * This method determines if the subtree of a given node is
     * unbalanced, and what type of rotation needs to be performed to restore
     * balance to the DataStructures.tree.
     *
     *    Right rotation is performed when BF of a node == 2 and the BF of its
     *    right child >=0.
     *
     *    Left rotation is performed when BF of a node == -2 and the BF of
     *    its left child <= 0.
     *
     *    Right-Left rotation is performed when BF of a node == -2 and the BF of
     *    its right child == 1 (right elbow)
     *
     *    Left-right rotation is performed when BF of a node == 2 and the BF of
     *    its left child == -1 (left elbow)
     *
     * @param root the node of the subtree to check for rebalancing
     * @return the new root of the new rebalanced node, or the original root
     * if no rebalance was done
     */
    private AVLNode<T> rebalance(AVLNode<T> root) {

        if (Math.abs(root.getBalanceFactor()) >= 2) {
            //Right Rotation
            if (root.getLeft() != null && root.getBalanceFactor() == 2 
                    && root.getLeft().getBalanceFactor() >= 0) {
                return rightRotate(root);
            }
            //Left Rotation
            if (root.getRight() != null && root.getBalanceFactor() == -2 
                    && root.getRight().getBalanceFactor() <= 0) {
                return leftRotate(root);
            }
            //Right Left Rotation
            if (root.getRight() != null && root.getBalanceFactor() == -2 
                    && root.getRight().getBalanceFactor() == 1) {
                root.setRight(rightRotate(root.getRight()));
                return leftRotate(root);
            }
            //Left Right Rotation
            if (root.getLeft() != null && root.getBalanceFactor() == 2 
                    && root.getLeft().getBalanceFactor() == -1) {
                root.setLeft(leftRotate(root.getLeft()));
                return rightRotate(root);
            }
        }

        return root;
    }

    /**
     * Private updateHeightAndBalanceFactor method
     * This method calculates and updates the height and BF of a given node.
     *
     * The height of a node is the max height of its left and right children + 1
     * The Balance Factor of a node = Height left node - Height right node
     * @param node The node which will have its height and BF updated.
     */
    private void updateHeightAndBalanceFactor(AVLNode<T> node) {
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight()))
                + 1);
        node.setBalanceFactor(height(node.getLeft()) - height(node.getRight()));
    }

    /**
     * Private height helper method
     * This method returns -1 if a node is null.  Otherwise, it returns the
     * height of the node.
     * @param node The node whose height we want to find
     * @return the height of the given node
     */
    private int height(AVLNode<T> node) {

        if (node == null) {
            return -1;
        }
        return node.getHeight();
    }

    /**
     * Private helper method right rotate
     * This method gets called when a node has a BF of 2 and its left child
     * has a BF >= 0.  It performs a trinode restructuring which will restore
     * balance to the subtree, and result in oldRoot.left becoming the new root.
     *
     * After the restructure we must update height and BF for the oldroot and
     * newroot, as they may have changed.  We must update the old root first,
     * as it is lowest in the new subtree after restructuring.
     *
     * @param oldRoot the original root of the unbalanced tri
     * @return The new root of the balanced subtree
     */
    private AVLNode<T> rightRotate(AVLNode<T> oldRoot) {
        AVLNode<T> newRoot = oldRoot.getLeft();
        AVLNode<T> newRootRightSubTree = newRoot.getRight();

        newRoot.setRight(oldRoot);
        oldRoot.setLeft(newRootRightSubTree);

        updateHeightAndBalanceFactor(oldRoot);
        updateHeightAndBalanceFactor(newRoot);

        return newRoot;
    }

    /**
     * Private helper method left rotate
     * This method gets called when a node has a BF of -2 and its left child
     * has a BF <= 0.  It performs a trinode restructuring which will restore
     * balance to the subtree, and result in oldRoot.left becoming the new root.
     *
     * After the restructure we must update height and BF for the oldroot and
     * newroot, as they may have changed.  We must update the old root first,
     * as it is lowest in the new subtree after restructuring.
     *
     * @param oldRoot the original root of the unbalanced tri
     * @return The new root of the balanced subtree
     */
    private AVLNode<T> leftRotate(AVLNode<T> oldRoot) {
        AVLNode<T> newRoot = oldRoot.getRight();
        AVLNode<T> newRootLeftSubTree = newRoot.getLeft();

        newRoot.setLeft(oldRoot);
        oldRoot.setRight(newRootLeftSubTree);

        updateHeightAndBalanceFactor(oldRoot);
        updateHeightAndBalanceFactor(newRoot);

        return newRoot;
    }

    @Override
    public T remove(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data.");
        }

        AVLNode<T> dummy = new AVLNode<T>(null);
        root = remove(data, root, dummy);
        size--;

        if (size == 0) {
            root = null;
        }
        return dummy.getData();


    }

    /**
     * Private remove helper method
     * We update the height/BF and check for rebalancing anytime set is
     * called on a node's left or right child, because that subtree  may have
     * changed.
     *
     * After a deletion, there can be at most one unbalanced node on the path
     * from the child of the removed node back up to the root.
     *
     * After rebalancing the unbalanced node, the height-balance property is
     * restored locally to the subtree of the new balanced root.
     *
     * But rebalancing may cause another node on the path to become
     * unbalanced, so we continue traversing up and re-updating and
     * rebalancing if needed until we reach the root.
     *
     * This is called a search and repair strategy.
     *
     * @param data The value of the node to remove
     * @param node The root of the current subtree we are recursing on
     * @param dummy This node will hold the data value of the node to remove
     *             so it can be returned in the original remove method.
     * @return The child of the parent node that should be set once the
     * recursive call returns
     */
    private AVLNode<T> remove(T data, AVLNode<T> node, AVLNode<T> dummy) {

        if (node == null) {
            throw new NoSuchElementException("Remove failed because data is "
                    + "not in the DataStructures.tree.");
        }

        if (data.compareTo(node.getData()) == 0) {

            dummy.setData(node.getData());
            if (node.getLeft() == null) {
                return node.getRight();
            }

            if (node.getRight() == null) {
                return node.getLeft();
            }

            //node has two children. find the successor
            AVLNode<T> tmp = new AVLNode<T>(null);
            node.setRight(removeSuccessor(node.getRight(), tmp));
            node.setData(tmp.getData());
            updateHeightAndBalanceFactor(node);
            return rebalance(node);

        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(data, node.getLeft(), dummy));


        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(data, node.getRight(), dummy));
        }

        updateHeightAndBalanceFactor(node);
        return rebalance(node);

    }

    /**
     * Private removeSuccessor helper method
     * We update the height/BF and check for rebalancing after each time
     * a node's left or right child is set, because that subtree may have
     * changed.
     * @param node Root of the subtree we are examining to find the
     *             successor of the removed element
     * @param dummy We will copy the value of the successor into this node
     *              before the successor gets removed/replaced with its
     *              child node.
     * @return The replacement child of the DataStructures.tree.avl.AVLNode which called
     * removeSuccessor.  The child be replaced with a new node or the same
     * child, depending on where it is called in the chain of recursion.
     */
    private AVLNode<T> removeSuccessor(AVLNode<T> node, AVLNode<T> dummy) {

        if (node.getLeft() == null) {
            //found successor
            dummy.setData(node.getData());
            return node.getRight();
        }

        node.setLeft(removeSuccessor(node.getLeft(), dummy));
        updateHeightAndBalanceFactor(node);
        return rebalance(node);

    }

    @Override
    public T get(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot get null data from DataStructures.tree.avl.AVL"
                    +  " Tree.");
        }

        AVLNode<T> found = get(data, root);
        return found.getData();
    }

    /**
     * Private recursive helper method for Get
     * This method returns a the DataStructures.tree.avl.AVLNode containing the given data, if it is
     * in the DataStructures.tree.
     *
     * Base Cases:
     * We stop recursing when either the current root is null (data not found)
     * or if data == root.data (data elem found)
     *
     * Recursive Cases:
     * Otherwise we search recurse left if the data < root.data
     * We recurse right if the data > root.data
     *
     * @param data The value of the node we seek to find
     * @param root The root of the subtree we are examining to find the given
     *            data
     * @return The DataStructures.tree.avl.AVLNode that contains the given data
     */

    public AVLNode<T> get(T data, AVLNode<T> root) {

        if (root == null) {
            throw new NoSuchElementException("Data not found in DataStructures.tree.avl.AVL Tree");
        }
        if (data.compareTo(root.getData()) == 0) {
            return root;
        }
        if (data.compareTo(root.getData()) < 0) {
            return get(data, root.getLeft());
        } else if (data.compareTo(root.getData()) > 0) {
            return get(data, root.getRight());
        }

        return root;

    }

    @Override
    public boolean contains(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot pass in null data to "
                    + "contains method.");
        }

        return contains(data, root);

    }

    /**
     * Private recursive helper method for contains
     * This method returns a boolean which indicates if the AVLTree contains
     * the given data.
     *
     * Base Cases:
     * We stop recursing when either the current root is null (data not found)
     * or if data == root.data (data elem found)
     *
     * Recursive Cases:
     * Otherwise we search recurse left if the data < root.data
     * We recurse right if the data > root.data
     *
     * @param data The value of the node we are seeking
     * @param root The root of the subtree we are examining
     * @return Boolean value indicating if the DataStructures.tree.avl.AVL contains the given value.
     */
    public boolean contains(T data, AVLNode<T> root) {

        if (root == null) {
            return false;
        }

        if (data.compareTo(root.getData()) == 0) {
            return true;
        }

        if (data.compareTo(root.getData()) < 0) {
            return contains(data, root.getLeft());
        } else if (data.compareTo(root.getData()) > 0) {
            return contains(data, root.getRight());
        }

        return false;
    }


    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public T getSecondLargest() {

        if (size < 2) {
            throw new NoSuchElementException("There is not currently a second "
                    + "largest element in the DataStructures.tree.avl.AVL Tree");
        }

        return getSecondLargest(root).getData();
    }


    /**
     * Private recursive helper method for getSecondLargest.
     * It might be intuitive to think that since the largest elem is the
     * rightmost node, the 2nd largest elem is in the 2nd to right most node.
     *
     * This works some of the time.  But when the max node has a
     * left child, the second largest elem is the max's left child.  There are
     * 2 situations this can happen:
     *
     * 1. The DataStructures.tree contains only 2 elems, and the root is the largest.
     * 2. The DataStructures.tree contains > 2 nodes, and the rightmost elem has a left child.
     *
     * We need to rule out those two scenarios first, before we can assume
     * that the 2nd largest is the 2nd to right most elem.
     *
     * So in this method we check:
     * 1. If the DataStructures.tree.avl.AVL is size 2, and root has a left child, the 2nd largest
     * elem == root.left
     *
     * 2. Otherwise for any size DataStructures.tree, if the largest elem (rightmost elem)
     * has a left child, 2nd largest elem = largestElem.left
     *
     * 3. Otherwise 2nd largest elem = The 2nd rightmost elem
     *
     * @param node Root of the subtree we are examining for its 2nd largest.
     * @return The DataStructures.tree.avl.AVLNode which contains the 2nd largest element.
     */
    public AVLNode<T> getSecondLargest(AVLNode<T> node) {
        if (node.getRight() == null &&  size == 2) {
            return node.getLeft();
        }

        if (node.getRight() != null && node.getRight().getRight() == null) {
            if (node.getRight().getLeft() != null) {
                return node.getRight().getLeft();
            }
            return node;
        }

        if (node.getRight() != null) {
            return getSecondLargest(node.getRight());
        } else {
            return getSecondLargest(node.getLeft());
        }

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AVL) {
            if (this.size != ((AVL<T>) obj).size) {
                return false;
            }

            return equals(root, ((AVL<T>) obj).root);
        }
        return false;
    }

    /**
     * Private recursive helper method for equals
     * This method determines if two DataStructures.tree.avl.AVL Tree instances are equal.
     * Equality is determined by all nodes being structurally in the same
     * place with the same value.  Heights and BF are not considered.
     *
     * The original equals method ensures that both AVLs passed into this
     * method have the same size.
     *
     * This method traverses both AVLS and determines for each node that the
     * value is the same.
     * @param root1 The root of the first DataStructures.tree.avl.AVL subtree we are comparing
     * @param root2 The root of the second DataStructures.tree.avl.AVL subtree we are comparing
     * @return The boolean value that indicates if the two subtrees are equal
     */
    public boolean equals(AVLNode root1, AVLNode root2) {

        if (root1 == null && root2 == null) {
            return true;
        }
        if (root1 == null || root2 == null) {
            return false;
        }
        if (!root1.getData().equals(root2.getData())) {
            return false;
        }

        return equals(root1.getLeft(), root2.getLeft())
                && equals(root1.getRight(), root2.getRight());

    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {
        if (size == 0) {
            return -1;
        }
        return root.getHeight();
    }

    @Override
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
