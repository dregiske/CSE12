/**
 * This is a custom tester file which contains the CustomTester class
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * This is a custom test class that tests the methods of
 * MyDeque, MyStack, MyQueue, and MyAlgorithm
 */
public class CustomTester {
    /**
     * Helper method to initialize all instance variables of MyDeque
     * @param deque The deque to initialize
     * @param data The data array
     * @param size The value for size
     * @param front The value for front
     * @param rear The value for rear
     */
    static void initDeque(MyDeque<Integer> deque, Object[] data, int size,
                          int front, int rear) {
        deque.data = data;
        deque.size = size;
        deque.front = front;
        deque.rear = rear;
    }
    // ------------ Deque ---------------

    /**
     * Tests constructor when initialCapacity is negative
     */    
    @Test
    public void negativeInitialCapacity(){
        assertThrows("Throw error when initialCapacity is negative",
            IllegalArgumentException.class,
            ()-> new MyDeque(-1));
    }

    /**
     * Tests constructor when initialCapacity is valid
     */
    @Test
    public void validInitialCapacity(){
        MyDeque<Integer> deque = new MyDeque<>(100);
        assertEquals("Capacity should be initialized to 100",
            100, deque.data.length);
        assertEquals("Size should be initialized to 0",
            0, deque.size);
        assertEquals("Front should be initialized to 0",
            0, deque.front);
        assertEquals("Rear should be initialized to 0",
            0, deque.rear);
    }

    /**
     * Tests expandCapacity when capacity is 0
     */
    @Test
    public void capacityIsZero(){
        MyDeque<Integer> deque = new MyDeque<>(0);
        deque.expandCapacity();
        assertEquals("Initializes new array when capacity is 0",
            10, deque.data.length);
        assertEquals("Checks that the size is 0",
            0, deque.size);
        assertEquals("Checks that front is 0",
            0, deque.front);
        assertEquals("Checks that rear is 0",
            0, deque.rear);
    }

    /**
     * Tests expandCapacity on capacity of 5
     */
    @Test
    public void expandCapacity(){
        MyDeque<Integer> deque = new MyDeque<>(5);

        deque.expandCapacity();

        assertEquals("Checks that new capacity is 10",
            10, deque.data.length);
    }

    /**
     * Tests addFirst when element is null
     */
    @Test
    public void nullElementAddFirst(){
        MyDeque<Integer> deque = new MyDeque<>(5);
        assertThrows("Throws error when element is null",
            NullPointerException.class,
            ()-> deque.addFirst(null));
    }

    /**
     * Tests addFirst when capacity is full
     */
    @Test
    public void fullCapacityAddFirst(){
        MyDeque<Integer> deque = new MyDeque<>(5);

        deque.addFirst(1);
        deque.addFirst(2);
        deque.addFirst(3);
        deque.addFirst(4);
        deque.addFirst(5);

        assertSame("Checks that capacity is full",
            deque.size, deque.data.length);

        deque.addFirst(6);

        assertEquals("Checks that capacity was doubled after adding new element",
            10, deque.data.length);
        assertEquals("Checks that size is now 6",
            6, deque.size);
        assertEquals("Checks that front is at end",
            9, deque.front);
        assertEquals("Checks that rear hasn't changed",
            0, deque.rear);
        assertSame("Checks that front element is correct",
            6, deque.peekFirst());
        assertSame("Checks that rear element is correct",
            1, deque.peekLast());
    }

    /**
     * Tests that front wraps in addFirst
     */
    @Test
    public void wrapAroundFrontAddFirst(){
        MyDeque<Integer> deque = new MyDeque<>(5);

        deque.addLast(1);
        deque.addLast(2);
        deque.addFirst(0);

        assertEquals("Checks that size incremented",
            3, deque.size);
        assertEquals("Checks that front is in correct index",
            4, deque.front);
        assertEquals("Checks that rear is in correct index",
            1, deque.rear);
        assertSame("Checks that rear element is correct",
            2, deque.peekLast());
        assertSame("Checks that front element is correct",
            0, deque.peekFirst());
    }

    /**
     * Test addLast when element is null
     */
    @Test
    public void nullElementAddLast(){
        MyDeque<Integer> deque = new MyDeque<>(5);
        assertThrows("Throws error when element is null",
            NullPointerException.class,
            ()-> deque.addLast(null));   
    }

    /**
     * Tests that capacity is doubled when full
     */
    @Test
    public void fullCapacityAddLast(){
        MyDeque<Integer> deque = new MyDeque<>(5);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.addLast(5);

        assertSame("Checks that capacity is equal to size",
            deque.size, deque.data.length);

        deque.addLast(6);

        assertEquals("Checks that capacity was doubled after adding new element",
            10, deque.data.length);
        assertEquals("Checks that size is now 6",
            6, deque.size);
        assertEquals("Checks that front has not changed",
            0, deque.front);
        assertEquals("Checks that rear is in correct index",
            5, deque.rear);
        assertSame("Checks that front is correct element",
            1, deque.peekFirst());
        assertSame("Checks that rear is correct element",
            6, deque.peekLast());
    }

    /**
     * Tests that rear wraps around in addLast
     */
    @Test
    public void wrapAroundRearAddLast(){
        MyDeque<Integer> deque = new MyDeque<>(5);
        Integer[] orig = {null, null, null, 1, 2};
        initDeque(deque, orig, 2, 3, 4);

        deque.addLast(1);

        assertEquals("Checks that rear is in correct index",
            0, deque.rear);
        assertEquals("Checks that size incremented",
            3, deque.size);
        assertEquals("Checks that front has not changed",
            3, deque.front);
        assertSame("Checks that front is correct element",
            1, deque.peekFirst());
        assertSame("Checks that rear is correct element",
            1, deque.peekLast());
    }

    /**
     * Tests removeFirst on empty array
     */
    @Test
    public void removeFirstEmpty(){
        MyDeque<Integer> deque = new MyDeque<>(5);
        assertEquals("Checks that this is an empty array",
            0, deque.size);
        assertNull("Returns null when removing on null element",
            deque.removeFirst());
    }

    /**
     * Tests that the front goes to correct index when at end of array
     */
    @Test
    public void removeFirstFrontIdx(){
        MyDeque<Integer> deque = new MyDeque<>(5);

        deque.addLast(1);
        deque.addFirst(2);
        deque.removeFirst();

        assertEquals("Checks that size decreased",
            1, deque.size);
        assertEquals("Checks that front is in correct index",
            0, deque.front);
        assertEquals("Checks that rear has not changed",
            0, deque.rear);
        assertSame("Checks that front element is correct",
            1, deque.peekFirst());
        assertSame("Checks that rear element is correct",
            1, deque.peekLast());
    }

    /**
     * Tests removeLast on empty array
     */
    @Test
    public void removeLastEmpty(){
        MyDeque<Integer> deque = new MyDeque<>(5);
        assertEquals("Checks that this is an empty array",
            0, deque.size);
        assertNull("Returns null when removing on null element",
            deque.removeLast());
    }

    /**
     * Tests that the rear goes to correct index when at front of array
     */
    @Test
    public void removeLastRearIdx(){
        MyDeque<Integer> deque = new MyDeque<>(5);

        deque.addLast(1);
        deque.addFirst(2);
        deque.removeLast();

        assertEquals("Checks that size decreased",
            1, deque.size);
        assertEquals("Checks that front is in correct index",
            4, deque.front);
        assertEquals("Checks that rear is in correct index",
            4, deque.rear);
        assertSame("Checks that front is in correct index",
            2, deque.peekFirst());
        assertSame("Checks that rear element is correct",
            2, deque.peekLast());
    }
    

    // ------------ Stack ---------------

    /**
     * Tests that top element gets removed from pop
     */
    @Test
    public void popAtTopElement(){
        MyStack<Integer> stack = new MyStack<>(5);

        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals("Check that current size is 3",
            3, stack.theStack.size);
        assertSame("Checks that rear element is 3",
            3, stack.theStack.peekLast());
        assertSame("Checks that pop returns correct element",
            3, stack.pop());
        assertEquals("Check that size is decreased",
            2, stack.theStack.size);
        assertSame("Checks that front element is correct",
            1, stack.theStack.peekFirst());
        assertSame("Checks that rear element is correct",
            2, stack.theStack.peekLast());
    }

    /**
     * Tests that top element gets added from push
     */
    @Test
    public void pushInEmpty(){
        MyStack<Integer> stack = new MyStack<>(5);

        stack.push(1);

        assertEquals("Checks that size incremented",
            1, stack.theStack.size);
        assertSame("Checks that element is in top (end)",
            1, stack.theStack.peekLast());

        stack.push(2);

        assertEquals("Checks that size is now 2",
            2, stack.theStack.size);
        assertSame("Checks that new element is at top (end)",
            2, stack.theStack.peekLast());

    }
    
    // ------------ Queue ---------------

    /**
     * Tests that enqueue adds at the end
     */
    @Test
    public void enqueue(){
        MyQueue<Integer> queue = new MyQueue<>(5);

        queue.enqueue(0);
        queue.enqueue(1);
        queue.enqueue(2);

        assertEquals("Checks size is now 3",
            3, queue.theQueue.size);
        assertSame("Checks that last element is correct",
            2, queue.theQueue.peekLast());
        assertSame("Checks that first element is correct",
            0, queue.theQueue.peekFirst());
    }

    /**
     * Tests that dequeue removes from front
     */
    @Test
    public void dequeue(){
        MyQueue<Integer> queue = new MyQueue<>(5);

        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);

        assertEquals("Checks that size is 3",
            3, queue.theQueue.size);
        assertSame("Checks that current first element is 1",
            1, queue.theQueue.peekFirst());
        assertSame("Checks that removed element gets returned",
            1, queue.dequeue());
        assertSame("Checks that last element is 3",
            3, queue.theQueue.peekLast());
        assertEquals("Checks that size is decreased",
            2, queue.theQueue.size);
        assertSame("Checks that current first element is now 2",
            2, queue.theQueue.peekFirst());
    }
    
    // ------------ Algorithm ---------------

    /**
     * Tests MyAlgorithm for invalid string of brackets
     */
    @Test
    public void invalidInputMyAlgorithm(){
        String input = "{a}[[g])";
        String input2 = ")(";
        assertFalse("This is an invalid string of brackets",
            MyAlgorithm.isValidBrackets(input));
        assertFalse("This checks for switched brackets",
            MyAlgorithm.isValidBrackets(input2));
    }

    /**
     * Tests MyAlgorithm for valid string of brackets
     */
    @Test
    public void validInputMyAlgorithm(){
        String input1 = "(CSE12)[IS]{MY}{{FAVORITE}}";
        String input2 = "({}[])";
        String input3 = "(abc)";
        String input4 = "";
        String input5 = "ab(c{}d(abcdef(abc){a})ab[a()])a";
        String input5simple = "({}((){})[()])";
        assertTrue("This is a valid string of brackets",
            MyAlgorithm.isValidBrackets(input1));
        assertTrue("This is a valid string of brackets",
            MyAlgorithm.isValidBrackets(input2));
        assertTrue("This is a valid string of brackets",
            MyAlgorithm.isValidBrackets(input3));
        assertTrue("This is a valid string of brackets",
            MyAlgorithm.isValidBrackets(input4));
        assertTrue("This is a valid string of brackets",
            MyAlgorithm.isValidBrackets(input5));
        assertTrue("This is a valid string of brackets",
            MyAlgorithm.isValidBrackets(input5simple));
    }

    /**
     * Tests MyAlgorithm for null input
     */
    @Test
    public void nullInputMyAlgorithm(){
        String input = null;
        assertThrows(NullPointerException.class,
            ()-> MyAlgorithm.isValidBrackets(input));
    }
}