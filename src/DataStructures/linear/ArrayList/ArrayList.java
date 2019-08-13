package DataStructures.linear.ArrayList;

/**
 * My implementation of DataStructures.linear.ArrayList.ArrayList.
 * Allows for:
 *       Adding to: front, back, or index.
 *       Removal from: front, back, or index.
 * Adding or removing from anywhere except back may require
 * shifting of elements.
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @version 1
 */
public class ArrayList<T> implements DataStructures.linear.ArrayList.ArrayListInterface<T> {

    // Do not add new instance variables.
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new DataStructures.linear.ArrayList.ArrayList.
     *
     * You may add statements to this method.
     */
    public ArrayList() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
    }

    /**
     * Internal helper method that regrows the DataStructures.linear.ArrayList.ArrayList by copying
     * all elements of the backingArray to a new array that has a
     * capacity of 2x current length.
     * This method takes approx O(n) time
     *
     */
    private void regrowArray(int index) {

        int newCapacity = backingArray.length * 2;

        T[] arrayCopy = (T[]) new Object[newCapacity];
        for (int i = 0; i < index; i++) {
            arrayCopy[i] = backingArray[i];
        }
        for (int i = index; i < backingArray.length; i++){
                arrayCopy[i + 1] = backingArray[i];
        }

        backingArray = arrayCopy;

    }
    
    @Override
    public void addAtIndex(int index, T data) {
        //is data null?
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null object to "
                    + "DataStructures.linear.ArrayList.ArrayList");
        }

        //is index out of bounds?
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index of attempted add is "
                    + "not in range.");
        }

        //is array already at max capacity?
        if (size == backingArray.length) {
            regrowArray(index);
            backingArray[index] = data;

        //otherwise,
        } else {
            //does index of attempted add already have an elem?
            if (backingArray[index] != null) {
               //if yes, then shift the elems over
                for (int i = size; i > index; i--) {
                    backingArray[i] = backingArray[i - 1];
                }
            }
            //if index is empty, simply add the elem
            backingArray[index] = data;
        }
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null "
                    + "object to DataStructures.linear.ArrayList.ArrayList");
        }
        addAtIndex(0, data);
    }

    @Override
    public void addToBack(T data) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot add null "
                    + "object to DataStructures.linear.ArrayList.ArrayList");
        }
        addAtIndex(size, data);
    }

    @Override
    public T removeAtIndex(int index) {

        if (size == 0) {
            return null;
        }

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index of remove is not in "
                    + "range.");
        }

        T item = backingArray[index];

        if (index != size - 1) {

            for (int i = index; i < size - 1; i++) {
                backingArray[i] = backingArray[i + 1];
            }

        }
        backingArray[size - 1] = null;
        size--;

        return item;
    }

    @Override
    public T removeFromFront() {
//        if (size == 0) {
//            return null;
//        }
//        T item = backingArray[0];
//
//        for (int i = 0; i < size - 1; i++) {
//            backingArray[i] = backingArray[i + 1];
//        }
//        backingArray[size - 1] = null;
//        size--;
//
//        return item;
        return removeAtIndex(0);
    }

    @Override
    public T removeFromBack() {
//        if (size == 0) {
//            return null;
//        }
//
//        T item = backingArray[size - 1];
//        backingArray[size - 1] = null;
//        size--;
//        return item;
        return removeAtIndex(size-1);
    }

    @Override
    public T get(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index of attempted operation "
                    + "is not in range.");
        }

        return backingArray[index];

    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        backingArray = (T[]) new Object[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Object[] getBackingArray() {
        // DO NOT MODIFY.
        return backingArray;
    }
}
