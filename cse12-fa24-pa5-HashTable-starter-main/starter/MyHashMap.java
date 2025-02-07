/**
 * Name: Andre Giske
 * Email: agiske@ucsd.edu
 * PID: A17918324
 * 
 * This is the MyHashMap file which contains
 * node functions and hashmap functions
 */

public class MyHashMap<K,V> {
    private static final int DEFAULT_CAPACITY = 5;
    private static final double LOAD_FACTOR = 0.8;
    private static final int EXPAND_CAPACITY_RATIO = 2;

    Node[] hashTable;
    int size;

    /**
     * Inizializes the hashtable with
     * capacity set to default (5)
     */
    public MyHashMap() {
        hashTable = new Node[DEFAULT_CAPACITY];
        size = 0;
    }
    /**
     * Initializes that hashtable with
     * capacity set to given num
     * @param initialCapacity
     */
    public MyHashMap(int initialCapacity) {
        if(initialCapacity <= 0){
            throw new IllegalArgumentException();
        }
        hashTable = new Node[initialCapacity];
        size = 0;
    }
    /**
     * Gets the value linked to the key
     * @param key unique data in hash table
     * @return value linked to key
     * @throws NullPointerException when key is null
     */
    public V get(K key) {
        if (key.equals(null)){
            throw new NullPointerException();
        }
        int i = getHash(key, hashTable.length);
        Node curNode = hashTable[i];
        while(curNode != null){
            if(curNode.getKey().equals(key)){
                return (V) curNode.getValue();
            }
            curNode = curNode.getNext();
        }   
        return null;
    }
    /**
     * If key exists, replaces value with new value
     * if not, adds key and value to hash table
     * @param key unique data in hash table
     * @param value data linked to key
     * @return previous value or key or null if key DNE
     * @throws NullPointerException if value or key is null
     */
    public V put(K key, V value) {
        if (key.equals(null) || value.equals(null)){
            throw new NullPointerException();
        }
        if(size >= LOAD_FACTOR * hashTable.length){
            expandCapacity();
        }
        int i = getHash(key, hashTable.length);
        Node curNode = hashTable[i];
        while(curNode != null){
            if(curNode.getKey().equals(key)){
                V temp = (V) curNode.value;
                curNode.setValue(value);
                return (V) temp;
            }
            if (curNode.next == null){
                curNode.next = new Node<K,V>(key, value);
                size++;
                return null;
            }
            curNode = curNode.getNext();
        }
        hashTable[i] = new Node<K,V>(key, value);
        size++;
        return null;
    }
    /**
    * Removes data using key address
     * @param key unique data in hash table
     * @return previous data before being removed
     * or null if key DNE
     * @throws NullPointerException if key is null
     */
    public V remove(K key) {
        if (key.equals(null)){
            throw new NullPointerException();
        }
        int i = getHash(key, hashTable.length);
        Node curNode = hashTable[i];
        while(curNode != null){
            if(curNode.getKey().equals(key)){
                V temp = (V) curNode.value;
                hashTable[i] = curNode.getNext();
                size--;
                return temp;
            }
            if(curNode.getNext().getKey().equals(key)){
                V temp = (V) curNode.getNext().value;
                curNode.next = curNode.getNext().getNext();
                size--;
                return (V) temp;
            }
            curNode = curNode.getNext();
        }
        return null;
    }
    /**
     * Gets the num of values in array
     * @return size of array
     */
    public int size() {
        return size;
    }
    /**
     * Gets num of total space of array
     * @return capacity of array
     */
    public int getCapacity() {
        return hashTable.length;
    }
    /**
     * Removes all mappings from hash map
     */
    public void clear() {
        hashTable = new Node[hashTable.length];
        size = 0;
    }
    /**
     * Checks in hash map is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        if(size == 0){
            return true;
        }
        return false;
    }
    /**
     * Double the capacity of hash table
     * and put the nodes in new respective hash places
     */
    public void expandCapacity() {
        int expandedCapacity = hashTable.length * EXPAND_CAPACITY_RATIO;
        Node[] temp = new Node[expandedCapacity];
        for(int i = 0; i < hashTable.length; i++){
            Node curNode = hashTable[i]; 
            while(curNode != null){ 
                int num = getHash((K) curNode.getKey(), expandedCapacity);
                if(temp[num] != null){
                    Node tempNode = temp[num];
                    while(tempNode.next != null){
                        tempNode = tempNode.getNext();
                    }
                    tempNode.next = new Node<K,V>((K) curNode.getKey(), (V) curNode.getValue());
                }
                else{
                    temp[num] = new Node<K,V>((K) curNode.getKey(), (V) curNode.getValue());
                }
                curNode = curNode.getNext();
            }
        }
        hashTable = temp;
    }
    /**
     * Verify key and capacity to be valid
     * @param key unique data in hash table
     * @param capacity num of total array space
     * @return the hash number of the key
     * @throws NullPointerException when key is null
     * @throws IllegalArgumentException when capacity is <= 0
     */
    public int getHash(K key, int capacity) {
        if (key.equals(null)){
            throw new NullPointerException();
        }
        if(capacity <= 0){
            throw new IllegalArgumentException();
        }
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    /**
     * A Node class that holds (key, value) pairs and references to the next node in the linked list
     */
    protected class Node<K,V> {
        K key;
        V value;
        Node next;

        /**
         * Constructor to create a single node
         * @param key key to store in this node
         * @param value value to store in this node
         */
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        /**
         * Accessor to get the next node in the list
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }

        /**
         * Set the next node in the list
         * @param node the new next node
         */
        public void setNext(Node n) {
            next = n;
        }

        /**
         * Accessor to get the node's key
         * @return this node's key
         */
        public K getKey() {
            return this.key;
        }

        /**
         * Set the node's key
         * @param key the new key
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Accessor to get the node's value
         * @return this node's value
         */
        public V getValue() {
            return this.value;
        }

        /**
         * Set the node's value
         * @param value the new value
         */
        public void setValue(V value) {
            this.value = value;
        }

        /**
         * Check if this node is equal to another node
         * @param other the other node to check equality with
         * @return whether or not this node is equal to the other node
         */
        public boolean equals(Node<K, V> other) {
            return this.key.equals(other.key) && this.value.equals(other.value);
        }
    }
}
