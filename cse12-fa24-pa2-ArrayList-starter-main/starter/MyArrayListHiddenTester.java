import static org.junit.Assert.*;

import org.junit.*;

public class MyArrayListHiddenTester {
    // Do not change the method signatures!
    /**
     * This sets up the test fixture. JUnit invokes this method before
     * every testXXX method. The @Before tag tells JUnit to run this method
     * before each test 
     */

    static final int DEFAULT_CAPACITY = 5;
    static final int MY_CAPACITY = 3;

    Object[] nullArray = new Object[10];
    Integer[] intArray = { 1, 2, 3 };
    Integer[] size1Array = {1, null, null}; // NOTE: LIST OF SIZE ONE
    Integer[] multipleArray = { 1, 2, 3, 1 };

    private MyArrayList listEmpty, listNonEmpty, listDefaultCap, 
            listCustomCapacity, listWithNull, listWithInt, listWithMultiple;

    @Before
    public void setUp() throws Exception {
        listEmpty = new MyArrayList();
        listNonEmpty = new MyArrayList<>(size1Array);
        listNonEmpty.size = 1;
        listDefaultCap = new MyArrayList(DEFAULT_CAPACITY);
        listCustomCapacity = new MyArrayList(MY_CAPACITY);
        listWithNull = new MyArrayList(nullArray);
        listWithInt = new MyArrayList<Integer>(intArray);
        listWithMultiple = new MyArrayList<Integer>(multipleArray);
    }

    /**
     * Aims to test the constructor when the input argument
     * is not valid
     */
    @Test
    public void testConstructorInvalidArg(){
        MyArrayList<Integer> test1 = new MyArrayList<>(0);
        assertThrows(IllegalArgumentException.class,()-> new MyArrayList<>(-1));
    }

    /**
     * Aims to test the constructor when the input argument is null
     */
    @Test
    public void testConstructorNullArg(){
        MyArrayList<Integer> test1 = new MyArrayList<>(null);
        assertArrayEquals(new Integer[]{ null, null, null, null, null }, test1.data);
    }

    /**
     * Aims to test the constructor when the input array has null elements
     */
    @Test
    public void testConstructorArrayWithNull(){
        MyArrayList<Integer> test1 = new MyArrayList<>(DEFAULT_CAPACITY);
        assertArrayEquals(new Integer[] {null, null, null, null, null}, test1.data);
        assertEquals(0, test1.size);
    }

    /**
     * Aims to test the append method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendAtCapacity(){
        listWithInt.append(4);
        assertArrayEquals(new Integer[] {1, 2, 3, 4, null, null}, listWithInt.data);
        assertEquals(4, listWithInt.size);
    }

    /**
     * Aims to test the append method when null is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testAppendNull(){
        listWithInt.append(null);
        assertArrayEquals(new Integer[] {1, 2, 3, null, null, null}, listWithInt.data);
        assertEquals(3, listWithInt.size);
    }

    /**
     * Aims to test the prepend method when an element is added to a full list
     * Check reflection on size and capacity
     */
    @Test
    public void testPrependAtCapacity(){
        listWithInt.prepend(1);
        assertArrayEquals(new Integer []{ 1, 1, 2, 3, null, null }, listWithInt.data);
        assertEquals(4, listWithInt.size);
    }
    
    /**
     * Aims to test the prepend method when a null element is added
     * Checks reflection on size and capacity
     * Checks whether null was added successfully
     */
    @Test
    public void testPrependNull(){
        listWithInt.prepend(null);
        assertArrayEquals(new Integer[] { null, 1, 2, 3, null, null }, listWithInt.data);
        assertEquals(3, listWithInt.size);
    }
    
    /**
     * Aims to test the insert method when input index is out of bounds
     */
    @Test
    public void testInsertOutOfBounds(){
        assertThrows(IndexOutOfBoundsException.class, ()-> listWithInt.insert(-1, 1));
    }

    /**
     * Insert multiple (eg. 1000) elements sequentially beyond capacity -
     * Check reflection on size and capacity
     * Hint: for loop could come in handy
     */
    @Test
    public void testInsertMultiple(){
        listWithInt.expandCapacity(13);
        for (int i = 0; i < 10; i++){
            listWithInt.insert(0, 1);
        }
        assertArrayEquals(new Integer[]
        { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 3 }, listWithInt.data);
        
    }

    /**
     * Aims to test the get method when input index is out of bound
     */
    @Test
    public void testGetOutOfBound(){
        MyArrayList<Integer> test1 = new MyArrayList<>(DEFAULT_CAPACITY);
        assertThrows(IndexOutOfBoundsException.class, ()-> test1.get(-1));
    }

    /**
     * Aims to test the set method when input index is out of bound
     */
    @Test
    public void testSetOutOfBound(){
        MyArrayList<Integer> test1 = new MyArrayList<>(DEFAULT_CAPACITY);
        assertThrows(IndexOutOfBoundsException.class, ()-> test1.set(-1, 1));
    }

    /**
     * Aims to test the remove method when removing multiple items from a list
     */
    @Test
    public void testRemoveMultiple(){
        listWithMultiple.remove(0);
        assertArrayEquals(new Integer[]{ 2, 3, 1, null }, listWithMultiple.data);
        assertEquals(3, listWithMultiple.size);
    }

    /**
     * Aims to test the remove method when input index is out of bound
     */
    @Test
    public void testRemoveOutOfBound(){
        MyArrayList<Integer> test1 = new MyArrayList<>(DEFAULT_CAPACITY);
        assertThrows(IndexOutOfBoundsException.class, ()-> test1.remove(-1));
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is strictly less than the current capacity
     */
    @Test
    public void testExpandCapacitySmaller(){
        assertThrows(IllegalArgumentException.class, ()-> listWithInt.expandCapacity(1));
    }

    /**
     * Aims to test the expandCapacity method when 
     * requiredCapacity is greater than current capacity * 2 and default capacity
     */
    @Test
    public void testExpandCapacityLarge(){
        listWithInt.expandCapacity(11);
        assertArrayEquals(new Integer[]{ 1, 2, 3, null, null, null, null, null, null, null, null}, listWithInt.data);
    }

    /**
     * Aims to test the rotate method when 
     * input numPositions is out of bounds
     */
    @Test
    public void testRotateOutOfBound() {
        assertThrows(IndexOutOfBoundsException.class, ()-> listWithInt.rotate(-1));
    }

    /**
     * Aims to test the find method when 
     * there are multiple of the input element
     */
    @Test
    public void testFindMultiple(){
	   assertEquals(3, listWithMultiple.find(1));
    }

    /**
     * Aims to test the find method when 
     * input element does not exist in the list
     */
    @Test
    public void testFindDoesNotExist(){
        assertEquals(-1, listWithInt.find(10));
    }
}