package DataStructures.linear.StacksAndQueues;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class LinkedQueueTests {
    private LinkedQueue<Integer> queue;

    public static final int TIMEOUT = 200;

    @Before
    public void setUp() {
        queue = new LinkedQueue<>();
    }

    // Tests correct exception is thrown for null element on enqueue
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testPushNullException() {
        queue.enqueue(null);
    }

    // Tests correct exception is thrown for empty queue on dequeue
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testPopEmptyException() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.dequeue();
    }

    // Tests null is returned for empty queue on peek
    @Test(timeout = TIMEOUT)
    public void testPeekEmptyReturnValue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        assertNull(queue.peek());
    }

    // Tests enqueue
    @Test(timeout = TIMEOUT)
    public void testEnqueueGeneral() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        assertEquals(5, queue.size());

        LinkedNode<Integer> current = queue.getHead();
        assertEquals(Integer.valueOf(465), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(134), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(234), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(129), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(472), current.getData());
        current = current.getNext();
        assertNull(current);
    }

    // Tests peek with an empty queue
    @Test(timeout = TIMEOUT)
    public void testPeekEmpty() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        assertNull(queue.peek());
    }

    // Tests peek
    @Test(timeout = TIMEOUT)
    public void testPeekGeneral() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        assertEquals(5, queue.size());

        assertEquals(Integer.valueOf(465), queue.peek());

        LinkedNode<Integer> current = queue.getHead();
        assertEquals(Integer.valueOf(465), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(134), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(234), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(129), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(472), current.getData());
        current = current.getNext();
        assertNull(current);
    }

    // Tests dequeue with one element
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testDequeueOneElement() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        queue.enqueue(913);
        assertEquals(1, queue.size());
        assertEquals(new Integer(913), queue.dequeue());
        assertEquals(0, queue.size());
        assertNull(queue.getHead());
        assertNull(queue.getTail());
        queue.dequeue();
    }

    // Tests dequeue
    @Test(timeout = TIMEOUT)
    public void testDequeueGeneral() {
        assertEquals(0, queue.size());
        queue.enqueue(465);
        queue.enqueue(134);
        queue.enqueue(234);
        queue.enqueue(129);
        queue.enqueue(472);
        assertEquals(5, queue.size());

        assertEquals(new Integer(465), queue.dequeue());
        assertEquals(new Integer(134), queue.dequeue());
        assertEquals(new Integer(234), queue.dequeue());

        assertEquals(2, queue.size());

        LinkedNode<Integer> current = queue.getHead();
        assertEquals(Integer.valueOf(129), current.getData());
        current = current.getNext();
        assertEquals(Integer.valueOf(472), current.getData());
        current = current.getNext();
        assertNull(current);
    }
}
