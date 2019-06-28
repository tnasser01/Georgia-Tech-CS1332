package DataStructures.tree.bst;

import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Collection;

/**
 * Binary Search Tree
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @GTID 903397126
 * @version 1.0
 */
public class BST<T extends Comparable<? super T>> implements BSTInterface<T> {
    // DO NOT ADD OR MODIFY INSTANCE VARIABLES.
    private BSTNode<T> root;
    private int size;

    /**
     * A no-argument constructor that should initialize an empty DataStructures.tree.bst.BST.
     * DO NOT IMPLEMENT THIS CONSTRUCTOR!
     */
    public BST() {
    }

    /**
     * Initializes the DataStructures.tree.bst.BST with the data in the Collection. The data
     * should be added in the same order it is in the Collection.
     *
     * @param data the data to add to the DataStructures.tree
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public BST(Collection<T> data) {

        if (data == null) {
            throw new IllegalArgumentException("Collection passed into DataStructures.tree.bst.BST "
                    + "constructor cannot be null.");
        }
        for (T elem : data) {
            if (elem == null) {
                throw new IllegalArgumentException("Collection passed into DataStructures.tree.bst.BST "
                        + "constructor cannot be null.");
            }
            this.add(elem);
        }
    }

    @Override
    public void add(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to DataStructures.tree.bst.BST");
        }

        root = add(root, data);

    }


    /**
     * Private helper method to add the data as a leaf to the DataStructures.tree.bst.BST.
     * The method considers three possible scenarios:
     *
     * 1. root == null
     * 2. data < root.data
     * 2. data > root.data
     *
     * If #1 occurs, this is the null position where data should be added.
     * The method creates a new node with the data and returns the node, where
     * it is set to its parent in the calling statement.
     *
     * If #2 occurs, the position to add to is in the left subtree of root.
     * The method assigns root.left to the return value of a new
     * recursive call to add, passing in root.left and data.
     *
     * If #3 occurs, the position to add to is in the right subtree of root.
     * The method assigns root.right to the return value of a new
     * recursive call to add, passing in root.right and data.
     *
     * All nodes in traversal path will have been re-assigned after all
     * recursive add calls are done, but the leaf node which
     * contains the add position will have a new child.  All other
     * interior nodes will have the same children, after
     * re-assignment.
     *
     * @param root  root of subtree that data will be added to
     * @param data the data to be added
     * @return The root after assignment update to its left, right child, or
     * itself.
     */
    private BSTNode<T> add(BSTNode<T> root, T data) {

        //scenario 1
        if (root == null) {
            root = new BSTNode<T>(data);
            size++;
            //return root after updating it from null to the newly added node
            return root;
        }

        //scenario 2
        if (data.compareTo(root.getData()) < 0) {
            root.setLeft(add(root.getLeft(), data));

        //scenario 3
        } else if (data.compareTo(root.getData()) > 0) {
            root.setRight(add(root.getRight(), data));
        }

        //return root after updating either its left or right child which
        // recursive return value
        return root;
    }

    @Override
    public T remove(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot remove null data.");
        }
        //we pass in a dummy node that will hold the removed data value
        //we do this because since we are using pointer reinforcement,
        //we are already using the return value for the left or right child
        BSTNode<T> dummy = new BSTNode<T>(null);
        root = remove(data, root, dummy);
        size--;
        return dummy.getData();
    }

    /**
     * Private helper method to remove node containing given data from DataStructures.tree.bst.BST.
     *
     * After traversing the DataStructures.tree and finding the node to remove, there are 3
     * scenarios to consider regarding its children:
     *
     * 1. node has no children
     * 2. node has one child
     * 3. node has two children
     *
     * If #1 occurs, the method removes it by returning null.
     *
     * If #2 occurs, the method replaces the node with its child, by
     * returning the child.
     *
     * If #3 occurs, the method replaces the node with its predecessor, by
     * making a call to removePredecessor.
     *
     * All nodes in traversal path will have been re-assigned with their
     * updated nodes, after all recursive remove calls are done.
     *
     * @throws NoSuchElementException if the data is not found in DataStructures.tree.bst.BST
     * @param data  root of subtree that data will be removed from
     * @param node the data to be removed
     * @param dummy holds the original value of the removed node to be
     *              returned in the public remove method
     * @return The node after assignment update to its left, right child, or
     * itself.
     */
    private BSTNode<T> remove(T data, BSTNode<T> node, BSTNode<T> dummy) {

        if (node == null) {
            throw new NoSuchElementException("Data not found in DataStructures.tree.bst.BST.");
        }

        if (data.compareTo(node.getData()) < 0) {
            node.setLeft(remove(data, node.getLeft(), dummy));

        } else if (data.compareTo(node.getData()) > 0) {
            node.setRight(remove(data, node.getRight(), dummy));

        }

        if (data.compareTo(node.getData()) == 0) {
            //dummy holds removal node data value
            dummy.setData(node.getData());

            //node has either 0 or 1 child
            if (node.getLeft() == null) {
                return node.getRight();
            }

            if (node.getRight() == null) {
                return node.getLeft();
            }
            //node has two children
            //Replace it with its predecessor (in left subtree)
            BSTNode<T> tmp = new BSTNode<T>(null); //tmp holds pred value
            //pred is in left subtree, so we set left child
            //if node did not have a left subtree, there is no pred
            node.setLeft(removePredecessor(node.getLeft(), tmp));
            //transfer the value of the predecessor into the deleted node
            node.setData(tmp.getData());
            return node;
        }

        return node;
    }

    /**
     * Private helper method to remove the predecessor of a given node
     *
     * On the initial call of removePredecessor, the left child of
     * the node who's predecessor we are seeking will be passed in.
     *
     * This recursive method considers 2 scenarios regarding the node's right
     * child:
     *
     * 1. If the node has a right child, we have not found the predecessor yet.
     *
     * 2. If the node does not have a right child, then the node is the
     * predecessor.
     *
     * If #1 occurs, the method saves the value of the predecessor in
     * tmp and replaces the node with its left child by returning the left
     * child. (child could be null).
     *
     * If #2 occurs, we set the right child to the return value of a new
     * recursive call to removePredecessor, passing in node's right child
     *
     * All nodes in traversal path will have been re-assigned with their
     * updated nodes, after all recursive calls are done.
     *
     * @param node the data to be removed
     * @param tmp holds the value of the predecessor that will replace the
     *            node to be removed
     * @return The node that will replace the original node in the calling
     * statement.
     */
    private BSTNode<T> removePredecessor(BSTNode<T> node, BSTNode<T> tmp) {

        //Base Case:  If right child is null, pre
                    //Set pred in tmp value
                    //Replace pred with its left child
        if (node.getRight() == null) {
            tmp.setData(node.getData());  //holds the predecessor data value
            return node.getLeft();  //either null or left child`
        }
        //Recurse if ther
        node.setRight(removePredecessor(node.getRight(), tmp)); //recurse

        return node;

    }

    @Override
    public T get(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data argument cannot be null");
        }

        return get(root, data).getData();

    }

    /**
     * Private helper method for the public method get, that returns a node
     * containing the given data or throws an exception if the data is not in
     * the DataStructures.tree.bst.BST
     *
     * @throws NoSuchElementException if the root is null
     * @param root the root of the subtree to search
     * @param data value of node to look for
     * @return The node that will replace the original node in the calling
     * statement.
     */
    private BSTNode<T> get(BSTNode<T> root, T data) {

        if (root == null) {
            throw new NoSuchElementException("Data was not found in the DataStructures.tree.bst.BST");
        }

        //base case
        if (root.getData().compareTo(data) == 0) {
            return root;
        }

        //recursive cases
        if (data.compareTo(root.getData()) < 0) {
            return get(root.getLeft(), data);
        } else if (data.compareTo(root.getData()) > 0) {
            return get(root.getRight(), data);
        }

        return null;
    }

    @Override
    public boolean contains(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data parameter cannot be null");
        }

        return contains(root, data);

    }

    /**
     * Private helper method for the public method contains, that returns
     * true if a node containing the given data exists or a false if it
     * does not exist in the DataStructures.tree.bst.BST.
     *
     * @param root the root of the subtree to search
     * @param data the data to search for
     * @return a boolean that indicates if this DataStructures.tree.bst.BST contains the data passed in
     */
    private boolean contains(BSTNode<T> root, T data) {

        //base case 1
        if (root == null) {
            return false;
        }
        //base case 2
        if (root.getData().compareTo(data) == 0) {
            return true;
        }

        //recursive case 1
        if (data.compareTo(root.getData()) < 0) {
            return contains(root.getLeft(), data);
        } else if (data.compareTo(root.getData()) > 0) {
            return contains(root.getRight(), data);
        }

        return false;
    }


    @Override
    public List<T> preorder() {
        ArrayList<T> arr = new ArrayList<T>();
        preorder(root, arr);
        return arr;
    }

    /**
     * Private helper method for the public method preorder, that returns a
     * List containing the preorder traversal of elements of the subtree
     * rooted at node.
     *
     * @param node the root of the subtree to traversed
     * @param list the list that will hold the preorder data
     * @return the preorder list of data
     */
    private void preorder(BSTNode<T> node, List<T> list) {

        if (node != null) {
            list.add(node.getData());
            preorder(node.getLeft(), list);
            preorder(node.getRight(), list);
        }

    }


    @Override
    public List<T> postorder() {

        return postorder(root, new ArrayList<T>());
    }

    /**
     * Private helper method for the public method postorder, that returns a
     * List containing the postorder traversal of elements of the subtree
     * rooted at node.
     *
     * @param node the root of the subtree to traversed
     * @param list the list that will hold the inorder data
     * @return the postorder list of data
     */
    private List<T> postorder(BSTNode<T> node, List<T> list) {

        if (node != null) {
            postorder(node.getLeft(), list);
            postorder(node.getRight(), list);
            list.add(node.getData());
        }
        return list;
    }

    @Override
    public List<T> inorder() {

        return inorder(root, new ArrayList<T>());
    }

    /**
     * Private helper method for the public method inorder, that returns a
     * List containing the inorder traversal of elements of the subtree
     * rooted at node.
     *
     * @param node the root of the subtree to be traversed
     * @param list the list that will hold the inorder data
     * @return the inorder list of data
     */
    private List<T> inorder(BSTNode<T> node, List<T> list) {

        if (node != null) {
            inorder(node.getLeft(), list);
            list.add(node.getData());
            inorder(node.getRight(), list);
        }

        return list;
    }

    @Override
    public List<T> levelorder() {
        Queue<BSTNode<T>> queue = new LinkedList<>();
        List<T> list = new ArrayList<T>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            BSTNode<T> dequeued = queue.poll();
            if (dequeued != null) {
                list.add(dequeued.getData());
                queue.offer(dequeued.getLeft());
                queue.offer(dequeued.getRight());
            }
        }

        return list;
    }



    @Override
    public int distanceBetween(T data1, T data2) {

        if (data1 == null || data2 == null) {
            throw new IllegalArgumentException("Data1 and data2 cannot be "
                    + "null");
        }

        if (size <= 1) {
            throw new NoSuchElementException("One or more of the elements is "
                    + "not in the DataStructures.tree.");
        }

        BSTNode<T> ancestor = ancestor(root, data1, data2);

        return distanceBetween(ancestor, data1, 0)
                + distanceBetween(ancestor, data2, 0);
    }

    /**
     * Private helper method for the public method distanceBetween.
     *
     * @throws NoSuchElementException if ancestor is null
     * @param ancestor the ancestor at start of path
     * @param data the data contained in node at end of path
     * @param distance the distance between ancestor and node containing data
     * @return the distance from the ancestor to the node containing data.
     */
    private int distanceBetween(BSTNode<T> ancestor, T data, int distance) {

        if (ancestor == null) {
            throw new NoSuchElementException("One or more of the elements is "
                    + "not in the DataStructures.tree.");
        }
        if (ancestor.getData().compareTo(data) == 0) {
            return distance;
        }

        if (ancestor.getData().compareTo(data) > 0) {
            return distanceBetween(ancestor.getLeft(), data, distance + 1);
        } else if (ancestor.getData().compareTo(data) < 0) {
            return distanceBetween(ancestor.getRight(), data, distance + 1);
        }

        throw new NoSuchElementException("One or more of the elements is "
                + "not in the DataStructures.tree.");
    }

    /**
     * Private helper method which finds and returns the deepest common
     * ancestor relative to the nodes that contain data1 and
     * data2.  This method is only called by the distanceBetween method.
     *
     * The initial call to ancestor passes in the root node.
     *
     * If data1 and data2 are both > node.data, return a new recursive call
     * that passes in node.getLeft()
     *
     * If data1 and data2 are both < node.data, return a new recursive call
     * that passes in node.getRight()
     *
     * @throws NoSuchElementException if node is null
     * @param node the method examines this node to determine if it is
     *             their deepest common ancestor.
     * @param data1 the value of the first node
     * @param data2 the value of the second node
     * @return The ancestor node of data1 and data2, or the root
     * of the subtree in which to search for it.
     */
    private BSTNode<T> ancestor(BSTNode<T> node, T data1, T data2) {

        if (node == null) {
            throw new NoSuchElementException("One or more of the elements is "
                    + "not in the DataStructures.tree.");
        }

        if (node.getData().compareTo(data1) > 0 &&  node.getData()
                .compareTo(data2) > 0) {
            return ancestor(node.getLeft(), data1, data2);
        } else if (node.getData().compareTo(data1) < 0 && node.getData()
                .compareTo(data2) < 0) {
            return ancestor(node.getRight(), data1, data2);
        }

        return node;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int height() {

        return height(root, 0);
    }

    /**
     * Private helper method for the public method height.
     *
     * Base Case 1:
     * node is null
     * method subtracts 1 from the height and returns it
     *
     * Base Case 2:
     * the node is a leaf node
     * method returns 0 (because leaves always have a height of 0 in BSTs)
     *
     * Recursive Case:
     * return the maximum height of node's left and right children + 1
     *
     * @param node node for which method finds height
     * @param height holds the value of height
     * @return The height of the node in subtree which is rooted at node
     */
    private int height(BSTNode<T> node, int height) {

        if (node == null) {
            return height - 1;
        }

        return  Math.max(height(node.getLeft(), height),
                height(node.getRight(), height)) + 1;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD
        return size;
    }

    @Override
    public BSTNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }
}
