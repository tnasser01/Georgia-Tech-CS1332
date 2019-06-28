package Graphs.GraphAlgorithmsApplied;

import Graphs.AdjacencyList.Vertex;

/**
 * Class representing a vertex.
 *
 * DO NOT EDIT THIS CLASS!!!
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class ConsistencyVertex<T> extends Vertex<T> {

    private T data;
    private char label;

    /**
     * Creates a Graphs.AdjacencyList.Vertex object holding the given data.
     *
     * @param data the object that is stored in this Graphs.AdjacencyList.Vertex
     * @throws IllegalArgumentException if data is null
     */

    public ConsistencyVertex(T data) {

        super(data);

        if (data == null) {
            throw new IllegalArgumentException("Data cannot be null.");
        }
        this.data = data;
    }

    public void setLabel(char label) {
        this.label = label;
    }

    public char getLabel() {
        return label;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof ConsistencyVertex) {
            return data.equals(((ConsistencyVertex<?>) o).data);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return data.hashCode();
    }

    /**
     * Gets the data in this vertex.
     *
     * @return the data in this vertex
     */
    public T getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }


}
