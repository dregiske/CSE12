/**
 * Name: Andre Giske
 * Email: agiske@ucsd.edu
 * PID: A17918324
 * 
 * This file is a custom tester for the MyListIterator methods
 */
import static org.junit.Assert.*;
import org.junit.*;
import java.util.*;

/**
 * This class contains all the custom test cases for MyListIterator
 * listLen1 is a linkedlist of length 1
 * and listLen2 is a linkedlist of length 2.
 */
public class MyListIteratorCustomTester {
    private MyLinkedList listLen1, listLen2;
    private MyLinkedList.MyListIterator listLen1Iter, listLen2Iter;

    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test.
     */
    @Before
    public void setUp() {
        listLen1 = new MyLinkedList();
        listLen1.add("Christine");
        listLen1Iter = listLen1.new MyListIterator();

        listLen2 = new MyLinkedList();
        listLen2.add("Paul");
        listLen2.add("Cao");
        listLen2Iter = listLen2.new MyListIterator();
    }

    /**
     * Aims to test the next() method when iterator is at end of the list 
     */
    @Test
    public void testNextEnd() {
        listLen1Iter.next();
        assertEquals("Checking that idx is at the end",
            1, listLen1Iter.idx);
        assertThrows("Calling next() when there is no next",
            NoSuchElementException.class, ()-> listLen1Iter.next());
    }

    /**
     * Aims to test the previous() method when iterator is at the start of the 
     * list 
     */
    @Test
    public void testPreviousStart() {
        assertEquals("Checking that idx of list is at 0",
            0, listLen1Iter.idx);
        assertThrows("Calling previous() when iterator is at beginning",
            NoSuchElementException.class, ()-> listLen1Iter.previous());
    }

    /**
     * Aims to test the add(E e) method when an invalid element is added
     */
    @Test
    public void testAddInvalid() {
        assertThrows("Checks add(E e) when user inputs invalid element",
            NullPointerException.class, ()-> listLen1Iter.add(null));
    }

    /**
     * Aims to test the set(E e) method when canRemoveOrSet is false
     */
    @Test
    public void testCantSet() {
        listLen1Iter.add("10");
        assertFalse("Checks that canRemoveOrSet is currently false",
            listLen1Iter.canRemoveOrSet);
        assertThrows("Checks set(E e) when canRemoveOrSet is false",
            IllegalStateException.class, ()-> listLen1Iter.set("B250"));
    }

    /**
     * Aims to test the remove() method when canRemoveOrSet is false
     */
    @Test
    public void testCantRemove() {
        listLen1Iter.add("10");
        assertFalse("Checks that canRemoveOrSet is currently false",
            listLen1Iter.canRemoveOrSet);
        assertThrows("Checks remove() when canRemoveOrSet is false",
            IllegalStateException.class, ()-> listLen1Iter.remove());
    }

    /**
     * Aims to tests the hasNext() method at the end of a list
     */
    @Test
    public void testHasNextEnd() {
        listLen1Iter.next();
        assertEquals("Checking that idx is at the end",
            1, listLen1Iter.idx);
        assertFalse("Calling hasNext() when there is no next",
            listLen1Iter.hasNext());
    }

    /**
     * Aims to test the hasPrevious() method at the start of a list
     */
    @Test
    public void testHasPreviousStart() {
        assertEquals("Checking that idx of list is at 0",
            0, listLen1Iter.idx);
        assertFalse("Calling hasPrevious() when iterator is at beginning",
            listLen1Iter.hasPrevious());
    }

    /**
     * Aims to test the previousIndex() method at the start of a list
     */
    @Test
    public void testPreviousIndexStart() {
        assertEquals("Checking that idx of list is at 0",
            0, listLen1Iter.idx);
        assertEquals("Checks previousIndex() when idx is at beginning",
            -1, listLen1Iter.previousIndex());
    }

    /**
     * Aims to test the nextIndex() method at the end of a list
     */
    @Test
    public void testNextIndexEnd() {
        listLen1Iter.next();
        assertEquals("Checks to see that idx of list is at end",
            1, listLen1Iter.idx);
        assertEquals("Checks to see that nextIndex() returns size when at the end",
            1, listLen1Iter.nextIndex());
    }
}
