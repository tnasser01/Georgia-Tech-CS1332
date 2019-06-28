package DataStructures.tree.maxheap;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Array Backed Max Heap
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @GTID 903397126
 * @version 1.0
 */
public class MaxHeap<T extends Comparable<? super T>> implements
        HeapInterface<T> {

    private T[] backingArray;
    private int size;
    // Do not add any more instance variables. Do not change the declaration
    // of the instance variables above.

    /**
     * Creates a Heap with an initial capacity of {@code INITIAL_CAPACITY}
     * for the backing array.
     *
     * Use the constant field in the interface. Do not use magic numbers!
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the Build Heap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     *
     * The initial array before the Build Heap algorithm takes place should
     * contain the data in the same order as it appears in the ArrayList.
     *
     * The {@code backingArray} should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY from
     * the interface). Index 0 should remain empty, indices 1 to n should
     * contain the data in proper order, and the rest of the indices should
     * be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws IllegalArgumentException if data or any element in data is null
     */
    public MaxHeap(ArrayList<T> data) {

        if (data == null) {
            throw new IllegalArgumentException("List cannot be null");
        }

        backingArray = (T[]) new Comparable[2 * data.size() + 1];
        int i = 1;
        for (T elem: data) {
            if (elem != null) {
                backingArray[i] = elem;
                i++;
            } else {
                throw new IllegalArgumentException("List elements cannot be "
                        + "null");
            }
        }
        size = data.size();
        buildHeap();
    }

    /**
     * Private helper method heapify.  This method gets called when a new
     * DataStructures.tree.maxheap.MaxHeap is initiated with an ArrayList passed in as a parameter.  It
     * performs downHeap on the elements at indices size/2 - 1 to rearrange the
     * elements in backingArray into max-heap ordering.
     */
    private void buildHeap() {
        for (int j = size / 2; j > 0; j--) {
            downHeap(j);
        }
    }

    /**
     * Private helper method upHeap
     * This method is called to restore the heap order property back to the
     * DataStructures.tree if it was violated after an add.
     *
     * @param index the index of the element in backingArray to upHeap
     */
    private void upHeap(int index) {

        while (index > 1 &&  backingArray[index / 2].compareTo(backingArray[index]) < 0) {
            T tmp = backingArray[index];
            backingArray[index] = backingArray[index / 2];
            backingArray[index / 2] = tmp;
            index = index / 2;
        }

    }


    /**
     * Private helper method downHeap
     * This method is called to restore the order property back to the DataStructures.tree if
     * it was violated during a remove
     */
    public void downHeap() {
        downHeap(1);
    }

    /**
     * Private helper method downHeap
     * This method is called to restore the heap order property back to the
     * DataStructures.tree if it was violated after a remove.
     *
     * @param index the index of the element in backingArray to downHeap
     */
    public void downHeap(int index) {

        int maxChild = 0;

        //while there are more children possible
        while (index <= size / 2) {
            //there is no right child so left child is the max
            if (index * 2 + 1 > size) {
                maxChild = index * 2;
            } else {
                //#1 no children
                if (backingArray[index * 2] == null && backingArray[index * 2 + 1] == null) {
                    return;
                }
                //#2 one child
                if (backingArray[index * 2 ] == null) {
                    maxChild = index * 2 + 1;
                }

                if (backingArray[index * 2 + 1] == null) {
                    maxChild = index * 2;
                }
                //#3 two children (so compare them to find the max)
                if (backingArray[index * 2]
                        .compareTo(backingArray[index * 2 + 1]) > 0) {
                    maxChild = index * 2;
                } else if (backingArray[index * 2]
                        .compareTo(backingArray[index * 2 + 1]) < 0) {
                    maxChild = index * 2 + 1;
                }
            }

            if (backingArray[index].compareTo(backingArray[maxChild]) < 0) {
                T tmp = backingArray[index];
                backingArray[index] = backingArray[maxChild];
                backingArray[maxChild] = tmp;
            } else {
                return;
            }
            index = maxChild;
        }
    }

    /**
     * Private helper method regrow
     * This method regrows the backing array to twice the previous length of
     * the backing array
     */
    private void regrow() {

        T[] newArray = (T[]) new Comparable[backingArray.length * 2];
        for (int i = 1; i < backingArray.length; i++) {
            newArray[i] = backingArray[i];
        }
        backingArray = newArray;

    }

    @Override
    public void add(T item) {

        if (item == null) {
            throw new IllegalArgumentException("Cannot add null element to "
                    + "heap");
        }
        if (size == backingArray.length - 1) {
            regrow();
        }

        backingArray[size + 1] = item;
        upHeap(size + 1);
        size++;
    }

    @Override
    public T remove() {

        if (size == 0) {
            throw new NoSuchElementException("Cannot remove because the heap "
                    + "is empty.");
        }
        T remove = backingArray[1];
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap();
        return remove;
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

    @Override
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public Comparable[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

}
