/**
 * This is a custom tester file for PA3
 * It contains cases for MyLinkedList and Node classes
 */
import static org.junit.Assert.*;

import org.junit.*;

/**
 * This is a custom tester to test the methods from MyLinkedList
 */

public class MyLinkedListCustomTester {

	private MyLinkedList<Integer> emptyIntegerList;
    private MyLinkedList<String> threeStringList;
    private String[] strData = {"CSE 12", "Paul Cao", "Christine Alvarado"};

    /**
     * Standard Test Fixture. An empty list, a list with one entry (0) and
     * a list with several entries (0,1,2)
     */
    @Before
    public void setUp() {
        emptyIntegerList = new MyLinkedList<Integer>();
        threeStringList = new MyLinkedList<>();
		this.populateLinkedList();
    }

    /**
     * One way to test the code is to manually populate the list to ensure 
     * the internal state is correct. In this way, potential errors in add() 
     * won't cause failures for other methods.
     *
     * However, for you own custom tester, feel free to populate the list 
     * in whatever way you want.
     */
    private void populateLinkedList() {
        MyLinkedList<String>.Node node0 =  
            this.threeStringList.new Node(this.strData[0]);
        MyLinkedList<String>.Node node1 =  
            this.threeStringList.new Node(this.strData[1]);
        MyLinkedList<String>.Node node2 =  
            this.threeStringList.new Node(this.strData[2]);

        this.threeStringList.head.next = node0;
        node0.prev = this.threeStringList.head;
        node0.next = node1;
        node1.prev = node0;
        node1.next = node2;
        node2.prev = node1;
        node2.next = this.threeStringList.tail;
        this.threeStringList.tail.prev = node2;
        this.threeStringList.size = 3;
    }

	/**
	 * Aims to test the add(E data) method with a valid argument.
	 */
	@Test
	public void testCustomAdd() {
		assertTrue(this.threeStringList.add("10"));
		assertEquals("Checks that added node has correct data",
			"10", this.threeStringList.tail.prev.data);
		assertSame("Checks that next pointer is correct",
			this.threeStringList.head.next.next.next.next.next, this.threeStringList.tail);
		assertSame("Checks that previous pointer is correct",
			this.threeStringList.tail.prev.prev, this.threeStringList.head.next.next.next);
		assertEquals("Checks that size is correct",
			4, this.threeStringList.size());
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the beginning of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToStart() {
		this.threeStringList.add(0, "10");
		assertEquals("Checks that first node has correct data",
			"10", this.threeStringList.head.next.data);
		assertSame("Checks that next pointer is correct",
			this.threeStringList.head.next.next, this.threeStringList.tail.prev.prev.prev);
		assertSame("Checks that the previous pointer is correct",
			this.threeStringList.head, this.threeStringList.tail.prev.prev.prev.prev.prev);
		assertEquals("Checks that size is correct",
			4, this.threeStringList.size());
	}

	/**
	 * Aims to test the add(int index, E data) method.
	 * Add a valid argument to the middle of MyLinkedList.
	 */
	@Test
	public void testCustomAddIdxToMiddle() {
		this.threeStringList.add(1, "10");
		assertEquals("Checks that new node at index has correct data",
			"10", this.threeStringList.head.next.next.data);
		assertSame("Checks that next pointer is correct",
			this.threeStringList.head.next.next.next, this.threeStringList.tail.prev.prev);
		assertSame("Checks that previous pointer is correct",
			this.threeStringList.head.next, this.threeStringList.tail.prev.prev.prev.prev);
		assertEquals("Checks that the size is correct",
			4, this.threeStringList.size());
	}

	/**
	 * Aims to test the remove(int index) method. Remove from an empty list.
	 */
	@Test
	public void testCustomRemoveFromEmpty() {
		assertThrows(IndexOutOfBoundsException.class, ()-> this.emptyIntegerList.remove(0));
		assertEquals("Checks that size is 0",
			0, this.emptyIntegerList.size());
		assertSame("Checks that head points to tail",
			this.emptyIntegerList.head.next, this.emptyIntegerList.tail);
		assertSame("Checks that tail points to head",
			this.emptyIntegerList.tail.prev, this.emptyIntegerList.head);
	}

	/**
	 * Aims to test the remove(int index) method.
	 * Remove a valid argument from the middle of MyLinkedList.
	 */
	@Test
	public void testCustomRemoveFromMiddle() {
		String removedNodeString = this.threeStringList.remove(1);
		assertEquals("Checks that data at index is correct data",
			"Christine Alvarado", this.threeStringList.head.next.next.data);
		assertSame("Checks that index-1 next pointer is correct",
			this.threeStringList.head.next.next, this.threeStringList.tail.prev);
		assertSame("Checks that index next pointer is correct",
			this.threeStringList.head.next.next, this.threeStringList.tail.prev);
		assertSame("Checks that index previous pointer is correct",
			this.threeStringList.tail.prev.prev, this.threeStringList.head.next);
		assertSame("Checks that index+1 previous pointer is correct",
			this.threeStringList.tail.prev, this.threeStringList.head.next.next);
		assertEquals("Removed node should be returned",
			"Paul Cao", removedNodeString);
		assertEquals("Checks that size is decreased",
			2, this.threeStringList.size());
	}

	/**
	 * Aims to test the set(int index, E data) method.
	 * Set an out-of-bounds index with a valid data argument.
	 */
	@Test
	public void testCustomSetIdxOutOfBounds() {
		assertThrows(IndexOutOfBoundsException.class, ()-> this.threeStringList.set(4, "10"));
		assertEquals("Checks that the size is 3",
			3, this.threeStringList.size());
	}

	/**
	 * Aims to test the contains(E data, int start, int end) method.
	 * Data argument exists in the list but outside the given range. 
	 */
	@Test
	public void testCustomContainsExistsOutOfRange() {
		boolean containsTester = this.threeStringList.contains("CSE 12", 1, 2);
		assertEquals("Checks that the element couldn't be found within the given range",
			containsTester, false);
		assertEquals("Checks that the size is 3",
			3, this.threeStringList.size());
		assertSame("Checks that head next pointer didn't change",
			this.threeStringList.head.next, this.threeStringList.tail.prev.prev.prev);
		assertSame("Checks that head previous pointer didn't change",
			this.threeStringList.head.prev, null);
		assertSame("Checks that tail next pointer didn't change",
			this.threeStringList.tail.next, null);
		assertSame("Checks that tail previous pointer didn't change",
			this.threeStringList.tail.prev, this.threeStringList.head.next.next.next);
		
	}

	/**
	 * Aims to test the indexOfElement(E data) method.
	 * Data argument does not exist in the list.
	 */
	@Test
	public void testCustomIndexOfElementDoesNotExist() {
		assertEquals("Checks that element does not exist in the list",
			-1, this.threeStringList.indexOfElement("10"));
		assertEquals("Checks that size is the same",
			3, this.threeStringList.size());
	}
}
