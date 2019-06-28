package DataStructures.linear.StacksAndQueues;

import java.util.NoSuchElementException;

/**
 * LinkedList-Backed Queue
 * Uses a singly linked list
 * Enqueue is performed at BACK of linkedlist
 * Dequeue is performed at FRONT of linkedlist
 *
 * -Enqueue adds an element to tail of linked list in O(1) worst case time.
 *
 * -Dequeue removes an element from head of linked list in O(1) worst case
 * time
 *
 * -Peek returns data in head of linked list in O(1) worst case time.
 *
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @GTID 903397126
 * @version 1.0
 */
public class LinkedQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private LinkedNode<T> head;
    private LinkedNode<T> tail;
    private int size;

    @Override
    public T dequeue() {

        if (size == 0) {
            throw new NoSuchElementException("There is no element to dequeue "
                    + "because the queue is empty.");
        }

        T remove = head.getData();
        head = head.getNext();
        size--;
        if (size == 0) {
            head = null;
            tail = null;
        }
        return remove;
    }

    @Override
    public void enqueue(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot enqueue null data");
        }

        LinkedNode<T> newNode = new LinkedNode<T>(data);
        if (size == 0) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }
        tail.setNext(newNode);
        tail = newNode;
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
     * Returns the head of this queue.
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

    /**
     * Returns the tail of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the tail node
     */
    public LinkedNode<T> getTail() {
        // DO NOT MODIFY THIS METHOD!
        return tail;
    }
}