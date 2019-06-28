package DataStructures.tree.maxheap;

import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
/**
  * Simple test cases for heaps and priority queues.
  * Write your own tests to ensure you cover all edge cases.
  *
  * @author CS 1332 TAs, updated by Tania
  * @version 1.0
  */
public class HeapPQStudentTests {

    private HeapInterface<Integer> maxHeap;
    private HeapInterface<Character> maxCharHeap;
    private PriorityQueueInterface<Integer> maxPriorityQueue;

    @Before
    public void setUp() {
        maxHeap = new MaxHeap<>();
        maxPriorityQueue = new MaxPriorityQueue<>();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullElementToHeap(){
        maxHeap.add(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveFromEmptyHeap(){
        assertEquals(0, maxHeap.size());
        maxHeap.remove();
    }

    @Test
    public void testRegrow(){
        ArrayList<Character> passedIn = new ArrayList<>();
        passedIn.add('a');
        passedIn.add('b');
        passedIn.add('c');
        passedIn.add('d');
        passedIn.add('e');
        passedIn.add('f');
        passedIn.add('g');
        passedIn.add('h');
        passedIn.add('i');
        passedIn.add('j');
        passedIn.add('k');
        passedIn.add('l');
        passedIn.add('m');
        passedIn.add('n');
        passedIn.add('o');
        passedIn.add('p');
        passedIn.add('q');
        passedIn.add('r');
        passedIn.add('s');
        passedIn.add('t');
        passedIn.add('u');
        passedIn.add('v');
        passedIn.add('w');
        passedIn.add('x');
        passedIn.add('y');
        passedIn.add('z');

        Character[] expected = new Character[53];
        expected[1] = 'z';
        expected[2] = 'w';
        expected[3] = 'y';
        expected[4] = 's';
        expected[5] = 'v';
        expected[6] = 'x';
        expected[7] = 'o';
        expected[8] = 'q';
        expected[9] = 'r';
        expected[10] = 'u';
        expected[11] = 'k';
        expected[12] = 'l';
        expected[13] = 'm';
        expected[14] = 'n';
        expected[15] = 'g';
        expected[16] = 'p';
        expected[17] = 'h';
        expected[18] = 'd';
        expected[19] = 'i';
        expected[20] = 't';
        expected[21] = 'j';
        expected[22] = 'e';
        expected[23] = 'b';
        expected[24] = 'c';
        expected[25] = 'a';
        expected[26] = 'f';

        maxCharHeap = new MaxHeap<Character>(passedIn);
        assertEquals(26, maxCharHeap.size());
        assertArrayEquals(expected,
                ((MaxHeap<Character>) maxCharHeap).getBackingArray());
    }


    @Test
    public void testBuildHeap() {
        ArrayList<Integer> passedIn = new ArrayList<>();
        passedIn.add(25);
        passedIn.add(10);
        passedIn.add(30);
        passedIn.add(35);
        passedIn.add(40);

        Integer[] expected = new Integer[11];
        expected[1] = 40;
        expected[2] = 35;
        expected[3] = 30;
        expected[4] = 25;
        expected[5] = 10;

        maxHeap = new MaxHeap<>(passedIn);
        assertEquals(5, maxHeap.size());
        assertArrayEquals(expected,
            ((MaxHeap<Integer>) maxHeap).getBackingArray());
    }

    @Test
    public void testHeap() {
        maxHeap.add(43);
        maxHeap.add(5);
        maxHeap.add(64);
        maxHeap.add(17);
        maxHeap.add(89);

        Integer[] expected = new Integer[HeapInterface.INITIAL_CAPACITY];
        expected[1] = 89;
        expected[2] = 64;
        expected[3] = 43;
        expected[4] = 5;
        expected[5] = 17;
        assertArrayEquals(expected,
                ((MaxHeap<Integer>) maxHeap).getBackingArray());

        assertEquals(new Integer(89), maxHeap.remove());
        assertEquals(new Integer(64), maxHeap.remove());
        assertEquals(3, maxHeap.size());
        assertFalse(maxHeap.isEmpty());
        assertEquals(new Integer(43), maxHeap.remove());
        assertEquals(new Integer(17), maxHeap.remove());
        assertEquals(new Integer(5), maxHeap.remove());
        assertTrue(maxHeap.isEmpty());
        Integer[] finalExpected = new Integer[HeapInterface.INITIAL_CAPACITY];
        assertArrayEquals(finalExpected,
                ((MaxHeap<Integer>) maxHeap).getBackingArray());
    }

    @Test
    public void testPriorityQueue() {
        maxPriorityQueue.enqueue(43);
        maxPriorityQueue.enqueue(5);
        maxPriorityQueue.enqueue(64);
        maxPriorityQueue.enqueue(17);
        maxPriorityQueue.enqueue(89);

        assertEquals(new Integer(89), maxPriorityQueue.dequeue());
        assertEquals(new Integer(64), maxPriorityQueue.dequeue());
        assertEquals(3, maxPriorityQueue.size());
        assertFalse(maxPriorityQueue.isEmpty());
        assertEquals(new Integer(43), maxPriorityQueue.dequeue());
        assertEquals(new Integer(17), maxPriorityQueue.dequeue());
        assertEquals(new Integer(5), maxPriorityQueue.dequeue());
        assertTrue(maxPriorityQueue.isEmpty());
    }

    @Test
    public void testResize() {
        for (int i = 0; i < HeapInterface.INITIAL_CAPACITY - 1; i++) {
            maxHeap.add(i * i * i - 74);
        }

        assertEquals(HeapInterface.INITIAL_CAPACITY - 1, maxHeap.size());
        assertEquals(HeapInterface.INITIAL_CAPACITY,
                ((MaxHeap<Integer>) maxHeap).getBackingArray().length);

        maxHeap.add(9 * 9 * (HeapInterface.INITIAL_CAPACITY - 1) - 74);

        assertEquals(HeapInterface.INITIAL_CAPACITY, maxHeap.size());
        assertEquals(HeapInterface.INITIAL_CAPACITY * 2,
                ((MaxHeap<Integer>) maxHeap).getBackingArray().length);
    }
}
