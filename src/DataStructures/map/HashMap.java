package DataStructures.map;

import java.util.List;
import java.util.Set;
import java.util.NoSuchElementException;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * DataStructures.map.HashMap with Closed Addressed External Chaining
 *
 * @author Tania Nasser
 * @userid tnasser3
 * @GTID 903397126
 * @version 1
 */
public class HashMap<K, V> implements HashMapInterface<K, V> {

    // Do not make any new instance variables.
    private MapEntry<K, V>[] table;
    private int size;

    /**
     * Create a hash DataStructures.map with no entries. The backing array has an initial
     * capacity of {@code INITIAL_CAPACITY}.
     *
     * Do not use magic numbers!
     *
     * Use constructor chaining.
     */
    public HashMap() {
        this(INITIAL_CAPACITY);
    }

    /**
     * Create a hash DataStructures.map with no entries. The backing array has an initial
     * capacity of {@code initialCapacity}.
     *
     * You may assume {@code initialCapacity} will always be positive.
     *
     * @param initialCapacity initial capacity of the backing array
     */
    public HashMap(int initialCapacity) {
        table =  new MapEntry[initialCapacity];
    }

    @Override
    public V put(K key, V value) {

        if (key == null || value == null) {
            throw new IllegalArgumentException("Key and Value for put "
                    + "operation cannot "
                    + "be null.");
        }

        double loadFactor = (size + 1) / (table.length * 1.0);
        if (loadFactor > MAX_LOAD_FACTOR) {
            resizeBackingTable(2 * table.length + 1);
        }
        MapEntry<K, V> newEntry = new MapEntry<K, V>(key, value);
        int index = findIndex(key);

        //if null add the mapEntry to the index
        if (table[index] == null) {
            table[index] = newEntry;
            size++;
            return null;
        }

        //DataStructures.map.MapEntry exists at this index
        MapEntry<K, V> curr = table[index];
        while (curr != null) {
            //if key exists, then update the value
            if (key.equals(curr.getKey())) {
                //The key is already contained in the list
                V oldValue = curr.getValue();
                curr.setValue(value);
                return oldValue;
            }
            curr = curr.getNext();
        }
        //if key does not exist, add to front of existing entries
        newEntry.setNext(table[index]);
        table[index] = newEntry;
        size++;
        return null;

    }

    /**
     * Private helper method for the hash function which maps a given key to
     * an index value within the the table.  (hashing + compression)
     * @param key The key value which for whose hashcode we seek
     * @return the integer hasCode value of the key
     */
    private int findIndex(K key) {
        return Math.abs(key.hashCode() % table.length);
    }

    @Override
    public V remove(K key) {

        if (key == null) {
            throw new IllegalArgumentException("Cannot remove a null key.");
        }

        int index = findIndex(key);

        if (table[index] == null) {
            throw new NoSuchElementException("Key " + key + " is not " 
                    + "contained in the list.");
        }

        MapEntry<K, V> curr = table[index];
        MapEntry<K, V> prev = null;

        while (curr != null) {
            if (curr.getKey().equals(key)) {
                V remove = curr.getValue();
                if (prev == null) {
                    table[index] = curr.getNext();

                } else if (prev != null) {
                    prev.setNext(curr.getNext());
                }
                size--;
                return remove;
            }
            prev = curr;
            curr = curr.getNext();
        }

        throw new NoSuchElementException("Key " + key + " is not "
                + "contained in the list.");
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Cannot perform get operation "
                    + "with a null key.");
        }

        int index = findIndex(key);
        if (table[index] == null) {
            throw new NoSuchElementException("Key does not exist in the " 
                    + "hashmap");
        }
        MapEntry<K, V> curr = table[index];
        while (curr != null) {
            if (key.equals(curr.getKey())) {
                return curr.getValue();
            }
            curr = curr.getNext();
        }

        throw new NoSuchElementException("Key does not exist in the "
                +  "hashmap");
    }

    @Override
    public boolean containsKey(K key) {

        if (key == null) {
            throw new IllegalArgumentException("ContainsKey operation cannot "
                    + "be performed on null key");
        }

        int index = findIndex(key);

        if (table[index] ==  null) {
            return false;
        } else {
            MapEntry<K,  V> curr = table[index];
            while (curr != null) {
                if (curr.getKey().equals(key)) {
                    return true;
                }
                curr = curr.getNext();
            }
        }

        return false;
    }

    @Override
    public void clear() {
        table =  new MapEntry[INITIAL_CAPACITY];
        size = 0;
    }

    @Override
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<K>();
        for (int i = 0; i < table.length; i++) {
            if (table[i] != null) {
                MapEntry<K, V> curr = table[i];

                while (curr != null) {
                    set.add(curr.getKey());
                    curr = curr.getNext();
                }

            }
        }
        return set;

    }

    @Override
    public List<V> values() {
        List<V> list = new ArrayList<V>();
        for (int i = 0; i < table.length; i++) {

            if (table[i] != null) {
                MapEntry<K, V> curr = table[i];
                while (curr != null) {
                    list.add(curr.getValue());
                    curr = curr.getNext();
                }

            }
        }

        return list;
    }

    @Override
    public void resizeBackingTable(int length) {

        if (length < 1 || length < size) {
            throw new IllegalArgumentException("Length for resizing cannot be"
                    + " negative.");
        }
        MapEntry<K, V>[] newTable =  new MapEntry[length];
        //rehash all the elements into the  new array
        for (int i = 0; i < table.length; i++) {
            //if there is an element at this index
            if (table[i] != null) {

                MapEntry<K, V> curr = table[i];

                //iterate through the elements
                while (curr != null) {
                    MapEntry<K, V> insert = new MapEntry<K, V>(curr.getKey(),
                            curr.getValue());
                    //rehash and compress the element key
                    int index = Math.abs(curr.getKey().hashCode() % length);

                    if (newTable[index] == null) {
                        newTable[index] = insert;
                    } else {
                        insert.setNext(newTable[index]);
                        newTable[index] = insert;
                    }
                    curr = curr.getNext();

                }
            }
        }
        table = newTable;
    }
    
    @Override
    public MapEntry<K, V>[] getTable() {
        // DO NOT EDIT THIS METHOD!
        return table;
    }

}
