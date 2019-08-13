package DataStructures.linear.StacksAndQueues;

import java.util.NoSuchElementException;

/**
 * LinkedList-Backed Stack
 * All operations (push, pop, and peek) are performed at TOP of the stack.
 * (front of linked list)
 *
 * -Pop removes the element at the head of the linked list in O(1)
 * worst case time.
 * -Push adds an element to the front of the linked list in O(1) worst case time
 * -Peek returns the data at the head of the linked list in O(1) worst case time
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @version 1.0
 */
public class LinkedStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private int size;

    @Override
    public T pop() {

        if (size == 0) {
            throw new NoSuchElementException("There is no element to pop "
                    + "because the stack is empty");
        }
        T remove = head.getData();
        head = head.getNext();
        size--;
        if (size == 0) {
            head = null;
        }
        return remove;
    }

    @Override
    public void push(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot push null data onto "
                    + "stack.");
        }

        LinkedNode<T> newNode = new LinkedNode<T>(data);
        newNode.setNext(head);
        head = newNode;
        size++;

    }

    @Override
    public T peek() {

        if (size == 0) {
            return null;
        }

        return head.getData();
    }

    @Override
    public boolean isEmpty() {
        // DO NOT MODIFY THIS METHOD!
        return size == 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Returns the head of this stack.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the head node
     */
    public LinkedNode<T> getHead() {
        // DO NOT MODIFY THIS METHOD!
        return head;
    }
}
