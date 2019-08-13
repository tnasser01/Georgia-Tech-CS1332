package DataStructures.linear.StacksAndQueues;

import java.util.NoSuchElementException;

/**
 * Array-Backed Stack
 * All operations (push, pop, and peek) are performed at TOP of the stack.
 * (back of the array)
 *
 * -Pop removes the last element from the back of the array in O(1)
 * worst case time.
 * -Push adds (and regrows if needed) an element to the back of the array in
 * O(1) amortized worst case time.
 * -Peek returns the data at back of array in O(1) worst case time.
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @version 1.0
 */
public class ArrayStack<T> implements StackInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new DataStructures.linear.StacksAndQueues.ArrayStack.
     */
    public ArrayStack() {
        this.backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Pop from the stack.
     *
     * Do not shrink the backing array.
     *
     * You should replace any spots that you pop from with null. Failure to do
     * so can result in a loss of points.
     *
     * @see StackInterface#pop()
     */
    @Override
    public T pop() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot pop because stack is "
                    + "empty.");
        }
        T pop = backingArray[size - 1];
        backingArray[size - 1] = null;
        size--;
        return pop;
    }

    /**
     * Push the given data onto the stack.
     *
     * If sufficient space is not available in the backing array, you should
     * regrow it to double the current length.
     *
     * @see StackInterface#push(T)
     */
    @Override
    public void push(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Data to push cannot be null");
        }

        if (size == backingArray.length) {
            regrow();
        }

        backingArray[size] = data;
        size++;

    }

    /**
     * Private helper method used to regrow backing array during an enqueue,
     * when size is equal to array length.  This implementation regrows the
     * array to twice the previous capacity and copies all elements,
     * preserving their original index, to the new array in O(n) time.
     */
    private void regrow() {
        T[] arrayCopy = (T[]) new Object[backingArray.length * 2];
        for (int i = 0; i < backingArray.length; i++) {
            arrayCopy[i] = backingArray[i];
        }
        backingArray = arrayCopy;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        }
        return backingArray[size - 1];
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
     * Returns the backing array of this stack.
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
