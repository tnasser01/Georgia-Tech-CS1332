package DataStructures.linear.StacksAndQueues;

import java.util.NoSuchElementException;

/**
 * Array-Backed Queue
 * Uses a circular array structure
 * Enqueue is performed from BACK of array
 * Dequeue is performed at FRONT of array
 *
 * -Enqueue adds an element to back of array (and grows array if needed) in O
 * (1) amortized worst case time.
 * Back: next open index to enqueue at
 * back = (front + size) mod N
 *
 * -Dequeue removes an element from front of array in O(1) worst case time.
 * Front: index at which next element will be removed
 * front = (font + 1) mod N
 *
 * -Peek returns the element at front of array in O(1) worst case time.
 *
 * Front gets reset to zero when queue becomes empty or during regrow
 * operation
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @version 1.0
 */
public class ArrayQueue<T> implements QueueInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int front;
    private int size;

    /**
     * Constructs a new DataStructures.linear.StacksAndQueues.ArrayQueue.
     */
    public ArrayQueue() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        front = 0;
        size = 0;
    }

    /**
     * Dequeue from the front of the queue.
     *
     * Do not shrink the backing array.
     * If the queue becomes empty as a result of this call, you should
     * explicitly reset front to 0.
     *
     * You should replace any spots that you dequeue from with null. Failure to
     * do so can result in a loss of points.
     *
     * See the homework pdf for more information on implementation details.
     *
     * @see QueueInterface#dequeue()
     */
    @Override
    public T dequeue() {

        if (size == 0) {
            throw new NoSuchElementException("Cannot perform dequeue operation "
                    + "because the queue is empty");
        }

        T remove = backingArray[front];
        backingArray[front] = null;
        front = (front + 1) % backingArray.length;
        size--;
        if (size == 0) {
            front = 0;
        }
        return remove;

    }

    /**
     * Add the given data to the queue.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length. If a regrow is necessary,
     * you should copy elements to the front of the new array and reset
     * front to 0.
     *
     * @see QueueInterface#enqueue(T)
     */
    @Override
    public void enqueue(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot enqueue null data");
        }

        if (size == backingArray.length) {
            regrow();
        }

        int index = (front + size) % backingArray.length;

        backingArray[index] = data;
        size++;
    }

    /**
     * Private helper method used to regrow backing array during an enqueue,
     * when size is equal to array length.  This implementation regrows the
     * array to twice the previous capacity.
     * This method uses modular arithmetic to reset the queue such
     * that front is equal to 0 during the copying of elements to the new array.
     */
    private void regrow() {

        T[] arrayCopy = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            arrayCopy[i] = backingArray[ (i + front) % backingArray.length];
        }

        backingArray = arrayCopy;
        front = 0;
    }
    @Override
    public T peek() {
        return backingArray[front];
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
     * Returns the backing array of this queue.
     * Normally, you would not do this, but we need it for grading your work.
     *
     * DO NOT USE THIS METHOD IN YOUR CODE.
     *
     * @return the backing array
     */
    public Object[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }
}
