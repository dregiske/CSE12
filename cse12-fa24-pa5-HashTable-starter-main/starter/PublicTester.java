/*
 * Name: Casey Hild
 * Email: child@ucsd.edu
 * PID: A16953257
 * Sources Used: JDK 17 Doc
 *
 * This is the public tester for CSE 12 PA5 in Fall 2024.
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * The public tester for PA5, which covers some basic test cases.
 */
public class PublicTester {
    MyHashMap<String, Integer> emptyMap;
    MyHashMap<String, Integer> threeElementMap;
    MyHashSet<String> emptySet;
    MyHashSet<String> threeElementSet;

    /**
	 * This sets up the test fixture. JUnit invokes this method before
	 * every testXXX method. The @Before tag tells JUnit to run this method
	 * before each test.
	 */
    @Before
    public void setup() throws Exception {
        // Empty map
        emptyMap = new MyHashMap<String, Integer>();

        // Map with 3 elements
        threeElementMap = new MyHashMap<String, Integer>();

        MyHashMap<String, Integer>.Node<String, Integer> mapElement1 =  
            threeElementMap.new Node<String, Integer>("A", 1);
        MyHashMap<String, Integer>.Node<String, Integer> mapElement2 =  
            threeElementMap.new Node<String, Integer>("B", 2);
        MyHashMap<String, Integer>.Node<String, Integer> mapElement3 =  
            threeElementMap.new Node<String, Integer>("F", 6);
        
        threeElementMap.hashTable[0] = mapElement1;
        threeElementMap.hashTable[1] = mapElement2;
        mapElement1.setNext(mapElement3);

        threeElementMap.size = 3;

        // Empty set
        emptySet = new MyHashSet<String>();

        //Set with 3 elements
        threeElementSet = new MyHashSet<String>();
        
        MyHashMap<String, Object>.Node<String, Object> setElement1 =  
            threeElementSet.hashMap.new Node<String, Object>("A", MyHashSet.DEFAULT_OBJECT);
        MyHashMap<String, Object>.Node<String, Object> setElement2 =  
            threeElementSet.hashMap.new Node<String, Object>("B", MyHashSet.DEFAULT_OBJECT);
        MyHashMap<String, Object>.Node<String, Object> setElement3 =  
            threeElementSet.hashMap.new Node<String, Object>("F", MyHashSet.DEFAULT_OBJECT);

        threeElementSet.hashMap.hashTable[0] = setElement1;
        threeElementSet.hashMap.hashTable[1] = setElement2;
        setElement1.setNext(setElement3);

        threeElementSet.hashMap.size = 3;
    }

    // ----------------MyHashMap class----------------

    /**
     * Test MyHashMap default constructor with valid argument
     */
    @Test
    public void testMyHashMapDefaultConstructor() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<String, Integer>();
        assertArrayEquals(new String[]{null, null, null, null, null}, hashMap.hashTable);
        assertEquals(0, hashMap.size());
        assertEquals(5, hashMap.hashTable.length);
    }

    /**
     * Test MyHashMap 1 arg constructor with valid argument
     */
    @Test
    public void testMyHashMapConstructor() {
        MyHashMap<String, Integer> hashMap = new MyHashMap<String, Integer>(10);
        assertArrayEquals(new String[]{null, null, null, null, null, null, null, null, null, null}, hashMap.hashTable);
        assertEquals(0, hashMap.size());
        assertEquals(10, hashMap.hashTable.length);
    }

    /**
     * Test MyHashMap get with a valid key
     */
    @Test
    public void testMyHashMapGet() {
        int value = threeElementMap.get("A");

        // Assert correct state of hash table
        assertTrue(threeElementMap.hashTable[0].equals(threeElementMap.new Node<String, Integer>("A", 1)));
        assertTrue(threeElementMap.hashTable[0].getNext().equals(threeElementMap.new Node<String, Integer>("F", 6)));
        assertNull(threeElementMap.hashTable[0].getNext().getNext());
        assertTrue(threeElementMap.hashTable[1].equals(threeElementMap.new Node<String, Integer>("B", 2)));
        assertNull(threeElementMap.hashTable[1].getNext());
        assertNull(threeElementMap.hashTable[2]);
        assertNull(threeElementMap.hashTable[3]);
        assertNull(threeElementMap.hashTable[4]);
        assertEquals(3, threeElementMap.size());

        // Assert correct return value
        assertEquals(1, value);

        value = threeElementMap.get("B");

        // Assert correct state of hash table
        assertTrue(threeElementMap.hashTable[0].equals(threeElementMap.new Node<String, Integer>("A", 1)));
        assertTrue(threeElementMap.hashTable[0].getNext().equals(threeElementMap.new Node<String, Integer>("F", 6)));
        assertNull(threeElementMap.hashTable[0].getNext().getNext());
        assertTrue(threeElementMap.hashTable[1].equals(threeElementMap.new Node<String, Integer>("B", 2)));
        assertNull(threeElementMap.hashTable[1].getNext());
        assertNull(threeElementMap.hashTable[2]);
        assertNull(threeElementMap.hashTable[3]);
        assertNull(threeElementMap.hashTable[4]);
        assertEquals(3, threeElementMap.size());

        // Assert correct return value
        assertEquals(2, value);

        value = threeElementMap.get("F");

        // Assert correct state of hash table
        assertTrue(threeElementMap.hashTable[0].equals(threeElementMap.new Node<String, Integer>("A", 1)));
        assertTrue(threeElementMap.hashTable[0].getNext().equals(threeElementMap.new Node<String, Integer>("F", 6)));
        assertNull(threeElementMap.hashTable[0].getNext().getNext());
        assertTrue(threeElementMap.hashTable[1].equals(threeElementMap.new Node<String, Integer>("B", 2)));
        assertNull(threeElementMap.hashTable[1].getNext());
        assertNull(threeElementMap.hashTable[2]);
        assertNull(threeElementMap.hashTable[3]);
        assertNull(threeElementMap.hashTable[4]);
        assertEquals(3, threeElementMap.size());

        // Assert correct return value
        assertEquals(6, value);
    }

    /**
     * Test MyHashMap put with a valid key and value
     */
    @Test
    public void testMyHashMapPut() {
        Integer value = emptyMap.put("A", 1);

        // Assert correct state of hash table
        assertTrue(emptyMap.hashTable[0].equals(emptyMap.new Node<String, Integer>("A", 1)));
        assertNull(emptyMap.hashTable[0].getNext());
        assertNull(emptyMap.hashTable[1]);
        assertNull(emptyMap.hashTable[2]);
        assertNull(emptyMap.hashTable[3]);
        assertNull(emptyMap.hashTable[4]);
        assertEquals(1, emptyMap.size());

        // Assert correct return value
        assertEquals(null, value);
        
        value = emptyMap.put("B", 2);

        // Assert correct state of hash table
        assertTrue(emptyMap.hashTable[0].equals(emptyMap.new Node<String, Integer>("A", 1)));
        assertNull(emptyMap.hashTable[0].getNext());
        assertTrue(emptyMap.hashTable[1].equals(emptyMap.new Node<String, Integer>("B", 2)));
        assertNull(emptyMap.hashTable[1].getNext());
        assertNull(emptyMap.hashTable[2]);
        assertNull(emptyMap.hashTable[3]);
        assertNull(emptyMap.hashTable[4]);
        assertEquals(2, emptyMap.size());

        // Assert correct return value
        assertEquals(null, value);

        value = emptyMap.put("F", 6);

        // Assert correct state of hash table
        assertTrue(emptyMap.hashTable[0].equals(emptyMap.new Node<String, Integer>("A", 1)));
        assertTrue(emptyMap.hashTable[0].getNext().equals(emptyMap.new Node<String, Integer>("F", 6)));
        assertNull(emptyMap.hashTable[0].getNext().getNext());
        assertTrue(emptyMap.hashTable[1].equals(emptyMap.new Node<String, Integer>("B", 2)));
        assertNull(emptyMap.hashTable[1].getNext());
        assertNull(emptyMap.hashTable[2]);
        assertNull(emptyMap.hashTable[3]);
        assertNull(emptyMap.hashTable[4]);
        assertEquals(3, emptyMap.size());

        // Assert correct return value
        assertEquals(null, value);
    }

    /**
     * Test MyHashMap remove with a valid key
     */
    @Test
    public void testMyHashMapRemove() {
        int value = threeElementMap.remove("A");

        // Assert correct state of hash table
        assertTrue(threeElementMap.hashTable[0].equals(threeElementMap.new Node<String, Integer>("F", 6)));
        assertNull(threeElementMap.hashTable[0].getNext());
        assertTrue(threeElementMap.hashTable[1].equals(threeElementMap.new Node<String, Integer>("B", 2)));
        assertNull(threeElementMap.hashTable[1].getNext());
        assertNull(threeElementMap.hashTable[2]);
        assertNull(threeElementMap.hashTable[3]);
        assertNull(threeElementMap.hashTable[4]);
        assertEquals(2, threeElementMap.size());

        // Assert correct return value
        assertEquals(1, value);

        value = threeElementMap.remove("B");

        // Assert correct state of hash table
        assertTrue(threeElementMap.hashTable[0].equals(threeElementMap.new Node<String, Integer>("F", 6)));
        assertNull(threeElementMap.hashTable[0].getNext());
        assertNull(threeElementMap.hashTable[1]);
        assertNull(threeElementMap.hashTable[2]);
        assertNull(threeElementMap.hashTable[3]);
        assertNull(threeElementMap.hashTable[4]);
        assertEquals(1, threeElementMap.size());

        // Assert correct return value
        assertEquals(2, value);

        value = threeElementMap.remove("F");

        // Assert correct state of hash table
        assertNull(threeElementMap.hashTable[0]);
        assertNull(threeElementMap.hashTable[1]);
        assertNull(threeElementMap.hashTable[2]);
        assertNull(threeElementMap.hashTable[3]);
        assertNull(threeElementMap.hashTable[4]);
        assertEquals(0, threeElementMap.size());

        // Assert correct return value
        assertEquals(6, value);

        assertNull(threeElementMap.hashTable[0]);
        assertEquals(0, threeElementMap.size());
        assertEquals(6, value);
    }

    /**
     * Test MyHashMap size
     */
    @Test
    public void testMyHashMapSize() {
        assertEquals(0, emptyMap.size());
        assertEquals(3, threeElementMap.size());
    }

    /**
     * Test MyHashMap getCapacity
     */
    @Test
    public void testMyHashMapGetCapacity() {
        assertEquals(5, emptyMap.getCapacity());
        assertEquals(5, threeElementMap.getCapacity());
    }

    /**
     * Test MyHashMap clear
     */
    @Test
    public void testMyHashMapClear() {
        threeElementMap.clear();
        assertArrayEquals(new String[]{null, null, null, null, null}, threeElementMap.hashTable);
        assertEquals(0, threeElementMap.size());
    }

    /**
     * Test MyHashMap isEmpty
     */
    @Test
    public void testMyHashMapIsEmpty() {
        assertTrue(emptyMap.isEmpty());
        assertFalse(threeElementMap.isEmpty());
    }

    /**
     * Test MyHashMap expandCapacity
     */
    @Test
    public void testMyHashMapExpandCapacity() {
        threeElementMap.expandCapacity();
        assertTrue(threeElementMap.hashTable[0].equals(threeElementMap.new Node<String, Integer>("F", 6)));
        assertNull(threeElementMap.hashTable[1]);
        assertNull(threeElementMap.hashTable[2]);
        assertNull(threeElementMap.hashTable[3]);
        assertNull(threeElementMap.hashTable[4]);
        assertTrue(threeElementMap.hashTable[5].equals(threeElementMap.new Node<String, Integer>("A", 1)));
        assertTrue(threeElementMap.hashTable[6].equals(threeElementMap.new Node<String, Integer>("B", 2)));
        assertNull(threeElementMap.hashTable[7]);
        assertNull(threeElementMap.hashTable[8]);
        assertNull(threeElementMap.hashTable[9]);
        assertEquals(3, threeElementMap.size());
        assertEquals(10, threeElementMap.getCapacity());
    }

    /**
     * Test MyHashMap getHash with a valid key
     */
    @Test
    public void testMyHashMapGetHash() {
        int hash = threeElementMap.getHash("A", threeElementMap.getCapacity());
        assertEquals(0, hash);

        hash = threeElementMap.getHash("B", threeElementMap.getCapacity());
        assertEquals(1, hash);

        hash = threeElementMap.getHash("F", threeElementMap.getCapacity());
        assertEquals(0, hash);
    }

    // ----------------MyHashSet class----------------

    /**
     * Test MyHashSet default constructor with valid argument
     */
    @Test
    public void testMyHashSetDefaultConstructor() {
        MyHashSet<String> hashSet = new MyHashSet<String>();
        assertArrayEquals(new String[]{null, null, null, null, null}, hashSet.hashMap.hashTable);
        assertEquals(5, hashSet.hashMap.getCapacity());
        assertEquals(0, hashSet.size());
    }

    /**
     * Test MyHashSet 1 arg constructor with valid argument
     */
    @Test
    public void testMyHashSetConstructor() {
        MyHashSet<String> hashSet = new MyHashSet<String>(10);
        assertArrayEquals(new String[]{null, null, null, null, null, null, null, null, null, null}, hashSet.hashMap.hashTable);
        assertEquals(10, hashSet.hashMap.getCapacity());
        assertEquals(0, hashSet.size());
    }

    /**
     * Test MyHashMap put with a valid element
     */
    @Test
    public void testMyHashSetAdd() {
        boolean success = emptySet.add("A");

        // Assert correct state of hash table
        assertTrue(emptySet.hashMap.hashTable[0].equals(emptySet.hashMap.new Node<String, Object>("A", MyHashSet.DEFAULT_OBJECT)));
        assertNull(emptySet.hashMap.hashTable[0].getNext());
        assertNull(emptySet.hashMap.hashTable[1]);
        assertNull(emptySet.hashMap.hashTable[2]);
        assertNull(emptySet.hashMap.hashTable[3]);
        assertNull(emptySet.hashMap.hashTable[4]);
        assertEquals(1, emptySet.size());

        // Assert correct return value
        assertTrue(success);

        success = emptySet.add("B");

        // Assert correct state of hash table
        assertTrue(emptySet.hashMap.hashTable[0].equals(emptySet.hashMap.new Node<String, Object>("A", MyHashSet.DEFAULT_OBJECT)));
        assertNull(emptySet.hashMap.hashTable[0].getNext());
        assertTrue(emptySet.hashMap.hashTable[1].equals(emptySet.hashMap.new Node<String, Object>("B", MyHashSet.DEFAULT_OBJECT)));
        assertNull(emptySet.hashMap.hashTable[1].getNext());
        assertNull(emptySet.hashMap.hashTable[2]);
        assertNull(emptySet.hashMap.hashTable[3]);
        assertNull(emptySet.hashMap.hashTable[4]);
        assertEquals(2, emptySet.size());

        // Assert correct return value
        assertTrue(success);

        success = emptySet.add("F");

        // Assert correct state of hash table
        assertTrue(emptySet.hashMap.hashTable[0].equals(emptySet.hashMap.new Node<String, Object>("A", MyHashSet.DEFAULT_OBJECT)));
        assertTrue(emptySet.hashMap.hashTable[0].getNext().equals(emptySet.hashMap.new Node<String, Object>("F", MyHashSet.DEFAULT_OBJECT)));
        assertNull(emptySet.hashMap.hashTable[0].getNext().getNext());
        assertTrue(emptySet.hashMap.hashTable[1].equals(emptySet.hashMap.new Node<String, Object>("B", MyHashSet.DEFAULT_OBJECT)));
        assertNull(emptySet.hashMap.hashTable[1].getNext());
        assertNull(emptySet.hashMap.hashTable[2]);
        assertNull(emptySet.hashMap.hashTable[3]);
        assertNull(emptySet.hashMap.hashTable[4]);
        assertEquals(3, emptySet.size());

        // Assert correct return value
        assertTrue(success);
    }

    /**
     * Test MyHashSet remove with a valid element
     */
    @Test
    public void testMyHashSetRemove() {
        boolean success = threeElementSet.remove("A");

        // Assert correct state of hash table
        assertTrue(threeElementSet.hashMap.hashTable[0].equals(threeElementSet.hashMap.new Node<String, Object>("F", MyHashSet.DEFAULT_OBJECT)));
        assertNull(threeElementSet.hashMap.hashTable[0].getNext());
        assertTrue(threeElementSet.hashMap.hashTable[1].equals(threeElementSet.hashMap.new Node<String, Object>("B", MyHashSet.DEFAULT_OBJECT)));
        assertNull(threeElementSet.hashMap.hashTable[1].getNext());
        assertNull(threeElementSet.hashMap.hashTable[2]);
        assertNull(threeElementSet.hashMap.hashTable[3]);
        assertNull(threeElementSet.hashMap.hashTable[4]);
        assertEquals(2, threeElementSet.size());

        // Assert correct return value
        assertTrue(success);

        success = threeElementSet.remove("B");

        // Assert correct state of hash table
        assertTrue(threeElementSet.hashMap.hashTable[0].equals(threeElementSet.hashMap.new Node<String, Object>("F", MyHashSet.DEFAULT_OBJECT)));
        assertNull(threeElementSet.hashMap.hashTable[0].getNext());
        assertNull(threeElementSet.hashMap.hashTable[1]);
        assertNull(threeElementSet.hashMap.hashTable[2]);
        assertNull(threeElementSet.hashMap.hashTable[3]);
        assertNull(threeElementSet.hashMap.hashTable[4]);
        assertEquals(1, threeElementSet.size());

        // Assert correct return value
        assertTrue(success);

        success = threeElementSet.remove("F");

        // Assert correct state of hash table
        assertNull(threeElementSet.hashMap.hashTable[0]);
        assertNull(threeElementSet.hashMap.hashTable[1]);
        assertNull(threeElementSet.hashMap.hashTable[2]);
        assertNull(threeElementSet.hashMap.hashTable[3]);
        assertNull(threeElementSet.hashMap.hashTable[4]);
        assertEquals(0, threeElementSet.size());

        // Assert correct return value
        assertTrue(success);
    }

    /**
     * Test MyHashSet size
     */
    @Test
    public void testMyHashSetSize() {
        assertEquals(0, emptySet.size());
        assertEquals(3, threeElementSet.size());
    }

    /**
     * Test MyHashSet clear
     */
    @Test
    public void testMyHashSetClear() {
        threeElementSet.clear();
        assertArrayEquals(new String[]{null, null, null, null, null}, threeElementSet.hashMap.hashTable);
        assertEquals(0, threeElementSet.size());
    }

    /**
     * Test MyHashSet isEmpty
     */
    @Test
    public void testMyHashSetIsEmpty() {
        assertTrue(emptySet.isEmpty());
        assertFalse(threeElementSet.isEmpty());
    }
}
