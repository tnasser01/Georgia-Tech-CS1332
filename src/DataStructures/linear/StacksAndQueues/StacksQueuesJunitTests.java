package DataStructures.linear.StacksAndQueues;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Basic tests for the array-backed stack and queue classes.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class StacksQueuesJunitTests {

    private StackInterface<Integer> stack;
    private QueueInterface<Integer> queue;
    private StackInterface<Character> charStack;
    private QueueInterface<Character> charQueue;

    //public static final int TIMEOUT = 200;


    //ARRAY STACK
    @Test
    public void testArrayStackPush() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());

        stack.push(34);
        stack.push(29);
        stack.push(48);
        stack.push(59);

        assertEquals(4, stack.size());

        Object[] backingArray = ((ArrayStack<Integer>) stack).getBackingArray();

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }


    @Test
    public void testArrayStackPushRegrow() {
        charStack = new ArrayStack<>();
        assertEquals(0, charStack.size());

        charStack.push('n'); //0
        charStack.push('m'); //1
        charStack.push('l'); //2
        charStack.push('k'); //3
        charStack.push('j'); //4
        charStack.push('i'); //5
        charStack.push('h'); //6
        charStack.push('g'); //7
        charStack.push('f'); //8
        charStack.push('e'); //9
        charStack.push('d'); //10

        assertEquals((Character) 'd', charStack.peek());
        assertEquals(11, charStack.size());
        charStack.push('c'); //regrow
        assertEquals(12, charStack.size());
        assertEquals(22, ((ArrayStack<Character>) charStack)
                .getBackingArray().length);
        assertEquals((Character) 'c', charStack.peek());
        assertEquals((Character) 'c', charStack.pop());
        charStack.push('c');
        charStack.push('b');
        charStack.push('a');
        assertEquals(14, charStack.size());

        Object[] backingArray = ((ArrayStack<Character>) charStack)
                .getBackingArray();

        Object[] expected = new Object[charStack.INITIAL_CAPACITY*2];
        char curr = 'n';
        for (int i = 0; i < 14; i++){
            expected[i] = curr;
            curr--;
        }
        assertArrayEquals(expected, backingArray);
        assertEquals((Character) 'a', charStack.pop());
        assertEquals((Character) 'b', charStack.pop());
        assertEquals((Character) 'c', charStack.pop());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayStackPushNullDataOntoEmptyStack() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());
        stack.push(null);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testArrayStackPushNullDataOntoPopulatedStack() {
        charStack = new ArrayStack<>();
        assertEquals(0, charStack.size());
        charStack.push('d');
        charStack.push('c');
        charStack.push('b');
        charStack.push('a');
        assertEquals(4, charStack.size());
        charStack.push(null);
    }

    @Test
    public void testArrayStackPopAfterPush() {
        charStack = new ArrayStack<>();
        assertEquals(0, charStack.size());

        charStack.push('d');
        charStack.push('c');
        charStack.push('b');
        charStack.push('a');
        assertEquals((Character) 'a', charStack.pop());
        assertEquals(3, charStack.size());

        charStack.push('x');
        charStack.push('y');
        charStack.push('z');
        assertEquals(6, charStack.size());

        assertEquals((Character) 'z', charStack.pop());
        assertEquals((Character) 'y', charStack.pop());
        assertEquals((Character) 'x', charStack.pop());
        assertEquals((Character) 'b', charStack.pop());

        assertEquals(2, charStack.size());

        Object[] backingArray = ((ArrayStack<Character>) charStack)
                .getBackingArray();

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        expected[0] = 'd';
        expected[1] = 'c';
        assertArrayEquals(expected, backingArray);

    }

    @Test
    public void testArrayStackPopUntilEmpty() {
        charStack = new ArrayStack<>();
        assertEquals(0, charStack.size());

        charStack.push('g');
        charStack.push('f');
        charStack.push('e');
        charStack.push('d');
        charStack.push('c');
        charStack.push('b');
        charStack.push('a');
        assertEquals(7, charStack.size());

        assertEquals((Character) 'a', charStack.pop());
        assertEquals((Character) 'b', charStack.pop());
        assertEquals((Character) 'c', charStack.pop());
        assertEquals((Character) 'd', charStack.pop());
        assertEquals((Character) 'e', charStack.pop());
        assertEquals((Character) 'f', charStack.pop());
        assertEquals((Character) 'g', charStack.pop());

        assertEquals(0, charStack.size());

        Object[] backingArray = ((ArrayStack<Character>) charStack)
                .getBackingArray();

        Object[] expected = new Object[StackInterface.INITIAL_CAPACITY];
        assertArrayEquals(expected, backingArray);

        charStack.push('c');
        charStack.push('b');
        charStack.push('a');
        assertEquals(3, charStack.size());

        assertEquals((Character) 'a', charStack.pop());
        assertEquals((Character) 'b', charStack.pop());
        assertEquals((Character) 'c', charStack.pop());

        assertEquals(0, charStack.size());
        assertArrayEquals(expected, backingArray);
    }



    @Test(expected = NoSuchElementException.class)
    public void testArrayStackPopEmpty() {
        stack = new ArrayStack<>();
        assertEquals(0, stack.size());
        stack.pop();
    }

    @Test
    public void testArrayStackPeekNotEmpty() {
        charStack = new ArrayStack<>();
        assertEquals(0, charStack.size());

        charStack.push('g');
        charStack.push('f');
        charStack.push('e');
        charStack.push('d');
        charStack.push('c');
        charStack.push('b');
        charStack.push('a');
        assertEquals(7, charStack.size());

        assertEquals((Character) 'a', charStack.peek());
        assertEquals((Character) 'a', charStack.pop());
        assertEquals((Character) 'b', charStack.peek());
        assertEquals((Character) 'b', charStack.pop());
        assertEquals((Character) 'c', charStack.peek());
        assertEquals((Character) 'c', charStack.pop());
        assertEquals((Character) 'd', charStack.peek());
        assertEquals(4, charStack.size());

        charStack.push('z');
        assertEquals((Character) 'z', charStack.peek());
        charStack.push('y');
        assertEquals((Character) 'y', charStack.peek());
        charStack.push('x');
        assertEquals((Character) 'x', charStack.peek());
        assertEquals(7, charStack.size());

    }

    @Test
    public void testArrayStackPeekEmpty() {
        charStack = new ArrayStack<>();
        assertEquals(0, charStack.size());
        assertEquals(null, charStack.peek());
    }

    @Test
    public void testArrayStackIsEmpty() {
        charStack = new ArrayStack<>();
        assertEquals(true, charStack.isEmpty());
        charStack.push('c');
        charStack.push('b');
        charStack.push('a');
        assertEquals(false, charStack.isEmpty());
        charStack.pop();
        charStack.pop();
        charStack.pop();
        assertEquals(true, charStack.isEmpty());

    }


    //DataStructures.linear.StacksAndQueues.ArrayQueue
    @Test
    public void testArrayQueueEnqueue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(34);
        queue.enqueue(29);
        queue.enqueue(48);
        queue.enqueue(59);

        assertEquals(4, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY];
        expected[0] = 34;
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }

    @Test
    public void testArrayQueueDequeue() {
        queue = new ArrayQueue<>();
        assertEquals(0, queue.size());

        queue.enqueue(34);
        queue.enqueue(29);
        queue.enqueue(48);
        queue.enqueue(59);
        assertEquals((Integer) 34, queue.dequeue());

        assertEquals(3, queue.size());

        Object[] backingArray = ((ArrayQueue<Integer>) queue).getBackingArray();

        Object[] expected = new Object[QueueInterface.INITIAL_CAPACITY];
        expected[1] = 29;
        expected[2] = 48;
        expected[3] = 59;

        assertArrayEquals(expected, backingArray);
    }
}