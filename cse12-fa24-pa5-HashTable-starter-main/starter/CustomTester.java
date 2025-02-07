/*
 * Name: Casey Hild
 * Email: child@ucsd.edu
 * PID: A16953257
 * Sources Used: JDK 17 Doc
 *
 * IMPORTANT: Do not change the method headers. Your tests will be run against
 * good and bad implementations of Student/Course/Sanctuary. You will only
 * receive points if your test passes when run on a good implementation and
 * fails for the corresponding bad implementation.
 */

import static org.junit.Assert.*;
import org.junit.*;

/**
 * The custom tester for PA5, which covers some basic test cases.
 */
public class CustomTester {
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
    public void setup() {
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
     * Test MyHashMap constructor with an invalid initial capacity
     */
    @Test
    public void testMyHashMapConstructorInvalidCapacity() {
        assertThrows(IllegalArgumentException.class, ()->
        new MyHashMap<String, Integer>(0));
    }

    /**
     * Test MyHashMap get with a null key
     */
    @Test
    public void testMyHashMapGetNullKey() {
        assertThrows(NullPointerException.class, ()->
            threeElementMap.get(null));
    }

    /**
     * Test MyHashMap get with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashMapGetKeyDoesNotExist() {
        assertNull(threeElementMap.get("R"));
    }

    /**
     * Test MyHashMap put with a null key
     */
    @Test
    public void testMyHashMapPutNullKey() {
        assertThrows(NullPointerException.class, ()->
            threeElementMap.put(null, 6));
    }

    /**
     * Test MyHashMap put with a key that already exists in the hash table
     */
    @Test
    public void testMyHashMapPutKeyAlreadyExists() {
        assertSame("Checks that previous value of key A was 1",
            threeElementMap.get("A"), 1);
        assertSame("Checks that put function returns previous value of same key",
            threeElementMap.put("A", 6), 1);
        threeElementMap.put("A", 6);
        assertSame("Checks that size didn't change",
            threeElementMap.size(), 3);
    }

    /**
     * Test MyHashMap remove with a null key
     */
    @Test
    public void testMyHashMapRemoveNullKey() {
        assertThrows(NullPointerException.class, ()->
            threeElementMap.remove(null));
    }

    /**
     * Test MyHashMap remove with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashMapRemoveKeyDoesNotExist() {
        assertNull(threeElementMap.remove("R"));
    }

    /**
     * Test MyHashMap getHash with a null key
     */
    @Test
    public void testMyHashMapGetHashNullKey() {
        assertThrows(NullPointerException.class, ()->
            threeElementMap.getHash(null, 5));
    }

    // ----------------MyHashSet class----------------

    /**
     * Test MyHashMap put with a key that already exists in the hash table
     */
    @Test
    public void testMyHashSetAddAlreadyExists() {
        assertSame("Checks that A is already in the set",
            threeElementMap.get("A"), 1);
        assertFalse(threeElementSet.add("A"));
    }

    /**
     * Test MyHashSet remove with a key that does not exist in the hash table
     */
    @Test
    public void testMyHashSetRemoveDoesNotExist() {
        assertNull("Checks that R does not exist in the set",
            threeElementMap.remove("R"));
        assertFalse(threeElementSet.remove("R"));
    }
}