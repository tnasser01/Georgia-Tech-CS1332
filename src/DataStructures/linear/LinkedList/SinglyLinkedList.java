package DataStructures.linear.LinkedList;

/**
 * Circular Singly Linked List
 *
 * This implementation uses a head pointer only (no tail)
 * Add to Front, Add to Back, and Remove from Back can be done in O(1)
 * Adding/Removing at any other position is O(n)
 * RemoveLastOccurance is always O(n)
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @version 1.0
 */
public class SinglyLinkedList<T> implements LinkedListInterface<T> {
    // Do not add new instance variables or modify existing ones.
    private LinkedListNode<T> head;
    private int size;
    
    /**
    * Default Constructor for DataStructures.linear.LinkedList.SinglyLinkedList
    * This constructor sets head to null and size to 0
    */
    public SinglyLinkedList() {
        //Do not set head or size because they recieve default values!
    }
    @Override
    public void addAtIndex(T data, int index) {

        if (data == null) {
            throw new IllegalArgumentException("Cannot add null data to linked list");
        }

        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index of attempted add is not in range");
        }

        //list is empty
        if (head == null) {
            LinkedListNode<T> newNode = new LinkedListNode<T>(data);
            head = newNode;
            newNode.setNext(head);

        } else if (index == 0) { //add to front - O(1)
            LinkedListNode<T> newNode = new LinkedListNode<T>(head.getData());
            newNode.setNext(head.getNext());
            head.setNext(newNode);
            head.setData(data);


        } else if (index == size) {   //add to back - O(1)
            LinkedListNode<T> newNode = new LinkedListNode<T>(head.getData());
            newNode.setNext(head.getNext());
            head.setNext(newNode);
            head.setData(data);
            head = newNode;  //same as add to front, except head = newNode now

        } else { //add to middle - O(n) because we have to traverse the
            // list to find the node BEFORE index
            LinkedListNode<T> curr = head;
            for (int i = 0; i < index - 1; i++) {
                curr = curr.getNext();
            }
            LinkedListNode<T> newNode = new LinkedListNode<T>(data);
            newNode.setNext(curr.getNext());
            curr.setNext(newNode);
        }
        size++;
    }

    @Override
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null value to linked list");
        }

        //O(1)
        addAtIndex(data, 0);
    }

    @Override
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot add null value to linked list");
        }

        //O(1)
        addAtIndex(data, size);
    }

    @Override
    public T removeAtIndex(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index of attempted remove cannot be less than zero or greater than size.");
        }

        LinkedListNode<T> curr = head;

        //Remove from front - O(1)
        //The key to achieving O(1) time is to not delete the actual head
        // node.  Instead, store head's value in a tmp var, change head's
        // value to head.next, and remove head.next.  Return the original value
        // of head.
        if (index == 0) {

            curr = head.getNext();
            head.setNext(head.getNext().getNext());
            T temp = curr.getData();
            curr.setData(head.getData());
            head.setData(temp);
            size--;
            //if list is empty, set head to null
            if (size == 0) {
                head = null;
            }
            return curr.getData();
        }

        //Remove from Middle or Back - O(n) because without a pointer to
        // prev, we have to traverse the list to get the previous node
        for (int i = 0; i < index - 1; i++) {
            curr = curr.getNext();
        }
        T remove = curr.getNext().getData();
        curr.setNext(curr.getNext().getNext());
        size--;

        return remove;

    }

    @Override
    public T removeFromFront() {
        if (size == 0) {
            return null;
        }
        T data = removeAtIndex(0);
        return data;
    }

    @Override
    public T removeFromBack() {
        if (size == 0) {
            return null;
        }
        T data = removeAtIndex(size - 1);
        return data;
    }

    @Override
    public T removeLastOccurrence(T data) {
        //check if data is null
        if (data == null) {
            throw new IllegalArgumentException("Data to remove cannot be null");
        }

        LinkedListNode<T> prevFound = null;
        LinkedListNode<T> currFound = head;
        LinkedListNode<T> prev = null;
        LinkedListNode<T> curr = head;

        boolean nodeFound = false;

        //check if list is empty
        if (size == 0) {
            return null;
        }

        //iterate over all elements to find last occurance index
        for (int i = 0; i < size; i++) {
            if (curr.getData().equals(data)) {
                if (i == 0) {
                    currFound = curr;
                } else {
                    prevFound = prev;
                    currFound = curr;
                }
                nodeFound = true;
            }
            prev = curr;
            curr = curr.getNext();
        }

        //element not found
        if (!nodeFound) {
            return null;
        }

        //remove from front
        if (prevFound == null) {
            currFound = head.getNext();
            head.setNext(head.getNext().getNext());
            T temp = currFound.getData();
            currFound.setData(head.getData());
            head.setData(temp);

        } else { //remove from middle or back
            prevFound.setNext(prevFound.getNext().getNext());
        }

        size--;
        //if list is empty, set head to null
        if (size == 0) {
            head = null;
        }
        return currFound.getData();
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index of attempted get cannot be less than zero and cannot be greater than or equal to list size");
        }
        if (index == 0) { //Get at index 0 - O(1)
            return head.getData();
        }

        LinkedListNode<T> curr = head;

        for (int i = 0; i < index; i++) {  //Get from middle or back - O(n)
            curr = curr.getNext();
        }
        return curr.getData();
    }

    @Override
    public Object[] toArray() {
        T[] array = (T[]) new Object[size];
        LinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            array[i] = (T) current.getData();
            current = current.getNext();

        }
        return array;

    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY!
        return size;
    }

    @Override
    public LinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }
}
