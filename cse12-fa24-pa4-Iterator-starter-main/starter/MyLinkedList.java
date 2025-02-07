/**
 * Name: Andre Giske
 * Email: agiske@ucsd.edu
 * PID: A17918324
 * 
 * This is file for the LinkedList class and it includes Node
 */
import java.util.AbstractList;
import java.util.NoSuchElementException;
import java.util.ListIterator;
import java.util.Iterator;
    /**
     * A LinkedList class that holds Nodes
     */
public class MyLinkedList<E> extends AbstractList<E> {
    int size;
    Node head;
    Node tail;

    /**
     * A Node class that holds data and references to previous and next Nodes.
     */
    protected class Node {
        E data;
        Node next;
        Node prev;

        /** 
         * Constructor to create singleton Node 
         * @param element Element to add, can be null	
         */
        public Node(E element) {
            // Initialize the instance variables
            this.data = element;
            this.next = null;
            this.prev = null;
        }

        /** 
         * Set the parameter prev as the previous node
         * @param prev new previous node
         */
        public void setPrev(Node prev) {
            this.prev = prev;		
        }

        /** 
         * Set the parameter next as the next node
         * @param next new next node
         */
        public void setNext(Node next) {
            this.next = next;
        }

        /** 
         * Set the parameter element as the node's data
         * @param element new element 
         */
        public void setElement(E element) {
            this.data = element;
        }

        /** 
         * Accessor to get the next Node in the list 
         * @return the next node
         */
        public Node getNext() {
            return this.next;
        }
        /** 
         * Accessor to get the prev Node in the list
         * @return the previous node  
         */
        public Node getPrev() {
            return this.prev;
        } 
        /** 
         * Accessor to get the Nodes Element 
         * @return this node's data
         */
        public E getElement() {
            return this.data;
        } 
    }

    //  Implementation of the MyLinkedList Class
    
    /**
     * Constructor method
     * Creates a new list and initializes all variables
     */
    public MyLinkedList() {
        this.size = 0;
        this.head = new Node(null);
        this.tail = new Node(null);
        head.next = tail;
        tail.prev = head;
    }
    /**
     * Integer of nodes in list
     * @return number of non-sentinel nodes in list
     */
    @Override
    public int size() {
        return this.size;
    }
    /**
     * Function to get data from specific node
     * @return the element at index
     * @throws IndexOutOfBoundsException when index is less than 0 or greater than or equal to size
     */
    @Override
    public E get(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node curNode = head.next;
        for(int i = 0; i < index; i++){
            curNode = curNode.next;
        }
        return curNode.data;
    }
    /**
     * Add data to specific location
     * @param index where to add data
     * @param data new data to add
     * @throws IndexOutOfBoundsException when index is less than 0 or greater than size
     * @throws NullPointerException when new data is null
     */
    @Override
    public void add(int index, E data) {
        if (index < 0 || index > size){
            throw new IndexOutOfBoundsException();
        }
        if (data.equals(null)){
            throw new NullPointerException();
        }
        Node addNode = new Node(data);
        Node curNode = head.next;
        for(int i = 0; i < index; i++){
            curNode = curNode.next;
        }
        addNode.setNext(curNode);
        addNode.setPrev(curNode.prev);
        addNode.prev.setNext(addNode);
        addNode.next.setPrev(addNode);
        addNode.setElement(data);
        size++;
    }
    /**
     * Adds a node at the end
     * @param data new data to add at the end
     * @return true when successful
     * @throws NullPointerException when new data is null
     */
    @Override
    public boolean add(E data) {
        if (data.equals(null)){
            throw new NullPointerException();
        }
        Node addNode = new Node(data);
        Node curNode = tail.prev;
        curNode.setNext(addNode);
        curNode.next.setPrev(curNode);
        curNode.next.setNext(tail);
        curNode.next.next.setPrev(addNode);
        size++;
        if(addNode.next.equals(tail)){
            return true;
        }
        return false;
    }
    /**
     * Replace data in specific node
     * @param index which node to replace data
     * @param data new data to replace with
     * @return the data that was replaced
     * @throws IndexOutOfBounds when index is less than 0 or greater than or equal to size
     * @throws NullPointerException when new data is null
     */
    @Override
    public E set(int index, E data) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        if (data.equals(null)){
            throw new NullPointerException();
        }
        Node curNode = head.next;
        for(int i = 0; i < index; i++){
            curNode = curNode.next;
        }
        E removedNode = curNode.data;
        curNode.setElement(data);
        return removedNode;
    }
    /**
     * Removes a node at specified index
     * @param index which node to remove
     * @return data from the removed node
     * @throws IndexOutOfBoundsException when index is less than 0 or greater than size
     */
    @Override
    public E remove(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node removedNode;
        Node curNode = head.next;
        for(int i = 0; i < index; i++){
            curNode = curNode.next;
        }
        removedNode = curNode;
        curNode.prev.setNext(curNode.next);
        curNode.next.setPrev(curNode.prev);
        size--;
        return removedNode.data;
    }
    /**
     * Remove all (non-sentinel) nodes from list
     * Reset size to 0
     */
    @Override
    public void clear() {
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }
    /**
     * Checks if list is empty
     * @return true if empty, false if not
     */
    @Override
    public boolean isEmpty() {
        if (size == 0){
            return true;
        }
        return false;
    }
    /**
     * Helper method
     * @param index which index to return
     * @return Node at specified index
     * @throws IndexOutOfBoundsException when index is less than 0 or greater than or equal to size
     */
    protected Node getNth(int index) {
        if (index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        }
        Node curNode = head.next;
        for(int i = 0; i < index; i++){
            curNode = curNode.next;
        }
        System.out.println(curNode);
        return curNode;
    }
    /**
     * Checks for data in specified domain
     * @param data what data to look for in node
     * @param start where to start search
     * @param end where to end search
     * @return true if data is within search domain, false if not
     * @throws IndexOutOfBoundsException when start is less than 0
     * or when start is greater than or equal to size
     * or when end is less than 0
     * or when end is greater than or equal to size
     */
    public boolean contains(E data, int start, int end) {
        if (start < 0 || start >= size || end < 0 || end > size){
            throw new IndexOutOfBoundsException();
        }
        if (end <= start){
            return false;
        }
        Node curNode = head.next;
        for(int i = 0; i < start; i++){
            curNode = curNode.next;
        }
        for(int i = start; i < end; i++){
            if (curNode.data.equals(data)){
                return true;
            }
            curNode = curNode.next;
        }
        return false; 
    }
    /**
     * Finds the index of a specified data
     * @param data which element to find
     * @return the index of the element, returns -1 when multiple
     * or when not found
     * @throws NullPointerException when data to find is null
     */
    public int indexOfElement(E data) {
        if (data.equals(null)){
            throw new NullPointerException();
        }
        int count = 0;
        int index = 0;
        Node curNode = head.next;
        for(int i = 0; i < size; i++){
            if(curNode.data.equals(data)){
                count++;
                index = i;
            }
            curNode = curNode.next;
        }
        if(count == 1){
            return index;
        }
        return -1;
    }

    public ListIterator<E> listIterator() {
        return new MyListIterator();
    }
    public Iterator<E> iterator() {
        return new MyListIterator();
    }

    /**
     * This is the MyListIterator class that contains all the methods for
     * the node iterator
     */
    protected class MyListIterator implements ListIterator<E> {
        Node left;
        Node right;
        int idx;
        boolean forward;
        boolean canRemoveOrSet;

        /**
         * This is the constructor that defaults the iterator
         * to the beginning, at head
         */
        public MyListIterator(){
            left = head;
            right = head.next;
            idx = 0;
            forward = true;
            canRemoveOrSet = false;
        }
        /**
         * Checks if next pointer leads to element node
         * sentinel nodes do not count
         * @return true if there is a node at next, false otherwise
         */
        public boolean hasNext() {
            if(idx == size){
                return false;
            }
            return true;
        }
        /**
         * Gets the element node in next pointer
         * and increase idx by 1
         * @return element of next node
         * @throws NoSuchElementException when there is no next
         */
        public E next(){
            if(idx == size){
                throw new NoSuchElementException();
            }
            left = right;
            right = right.next;
            idx++;
            forward = true;
            canRemoveOrSet = true;
            return left.data;
        }
        /**
         * Checks if previous pointer leads to element node,
         * sentinel nodes do not count
         * @return true if previous node exists, false otherwise
         */
        public boolean hasPrevious(){
            if(idx == 0){
                return false;
            }
            return true;
        }
        /**
         * Gets the element node in previous pointer
         * and moves idx back by one
         * @return element node in previous pointer
         * @throws NoSuchElementException when there is no previous
         */
        public E previous(){
            if(idx == 0){
                throw new NoSuchElementException();
            }
            right = left;
            left = left.prev;
            idx--;
            forward = false;
            canRemoveOrSet = true;
            return right.data;
        }
        /**
         * Gets the index of next node,
         * returns size if idx is at max
         * @return idx of next node or size otherwise
         */
        public int nextIndex(){
            if(idx == size){
                return size;
            }
            return idx;
        }
        /**
         * Gets index of previous node,
         * returns -1 if idx is at beginning
         * @return idx of previous node or -1 otherwise
         */
        public int previousIndex(){
            if(idx == 0){
                return -1;
            }
            idx--;
            return idx;
        }
        /**
         * Adds an element at left or right element node
         * depending on previous method call
         * @param element new element to add
         * @throws NullPointerException when element is null
         */
        public void add(E element){
            if(element.equals(null)){
                throw new NullPointerException();
            }
            Node newNode = new Node(element);
            newNode.prev = left;
            newNode.next = right;
            newNode.prev.next = newNode;
            newNode.next.prev = newNode;
            left = newNode;
            idx++;
            size++;
            canRemoveOrSet = false;
        }
        /**
         * Replaces data of a element node
         * depending on previous method call
         * @param element new element to set
         * @throws NullPointerException when element is null
         * @throws IllegalStateException when neither next or previous was called
         */
        public void set(E element){
            if(element.equals(null)){
                throw new NullPointerException();
            }
            if(canRemoveOrSet == false){
                throw new IllegalStateException();
            }
            if(forward == true){
                left.data = element;
            }
            if(forward == false){
                right.data = element;
            }
        }
        /**
         * Removes a element node
         * depending on previous method call
         * @throws IllegalStateException when neither next or previous was called
         */
        public void remove(){
            if(canRemoveOrSet == false){
                throw new IllegalStateException();
            }
            if(forward == true){
                left.prev.next = right;
                right.prev = left.prev;
                left = right.prev;
                idx--;
            }
            if(forward == false){
                left.next = right.next;
                right.next.prev = left;
                right = left.next;
            }
            size--;
            canRemoveOrSet = false;
        }
    }
}
