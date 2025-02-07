/**
 * Name: Andre Giske
 * Email: agiske@ucsd.edu
 * PID: A17918324
 * 
 * This is the MyHashSet file which contains
 * the methods for MyHashMap
 */
public class MyHashSet<E> {
    public static final Object DEFAULT_OBJECT = new Object();
    
    MyHashMap<E, Object> hashMap;
    /**
     * Initialize hashMap with default capacity (5)
     */
    public MyHashSet() {
        hashMap = new MyHashMap<>();
    }
    /**
     * Initialize hashMap with specified
     * capacity
     * @param initialCapacity num of space needed
     * @throws IllegalArgumentException when initial capacity is <= 0
     */
    public MyHashSet(int initialCapacity) {
        if(initialCapacity <= 0){
            throw new IllegalArgumentException();
        }
        hashMap = new MyHashMap<>(initialCapacity);
    }
    /**
     * Adds specified element to set if not already
     * @param element new data to add
     * @return true if element wasn't in set,
     * false otherwise
     * @throws NullPointerException if element is null
     */
    public boolean add(E element) {
        if(element == null){
            throw new NullPointerException();
        }
        if(hashMap.put(element, DEFAULT_OBJECT) != null){
            return false;
        }
        return true;
    }
    /**
     * Removes specified element from set if in set
     * @param element data to remove
     * @return true if element was in set,
     * false otherwise
     * @throws NullPointerException if element is null
     */
    public boolean remove(E element) {
        if(element == null){
            throw new NullPointerException();
        }
        if(hashMap.remove(element) == null){
            return false;
        }
        return true;
    }
    /**
     * Gives the number of elements in set
     * @return the size
     */
    public int size() {
        return hashMap.size;
    }
    /**
     * Clears items from the set
     */
    public void clear() {
       hashMap = new MyHashMap<>();
       hashMap.size = 0;
    }
    /**
     * Checks if the set is empty
     * @return true if set has no elements,
     * false otherwise
     */
    public boolean isEmpty() {
        if(size() == 0){
            return true;
        }
        return false;
    }
}