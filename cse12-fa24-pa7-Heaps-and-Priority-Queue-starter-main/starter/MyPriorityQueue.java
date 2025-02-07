/**
 * This is the MyPriorityQueue file
 * that contains the MyPriorityQueue class
 */

import java.util.Collection;

/**
 * This is the MyPriorityQueue class
 * that extends the Comparable interface
 */
public class MyPriorityQueue<E extends Comparable<E>>{

    protected MyMinHeap<E> heap;

    /**
     * No arg constructor that initializes heap
     * to be a new empty MyMinHeap
     */
    public MyPriorityQueue(){
        heap = new MyMinHeap<>();
    }

    /**
     * Arg constructor that initializes heap
     * with collection
     * 
     * @param collection elements to inizialize with
     */
    public MyPriorityQueue(Collection<? extends E> collection){
        heap = new MyMinHeap<>(collection);
    }

    /**
     * Add an element to PriorityQueue
     * 
     * @param element new element to add
     * @throws NullPointerException when element is null
     */
    public void push(E element){
        if(element == null){
            throw new NullPointerException();
        }
        heap.insert(element);
    }
    
    /**
     * Gets element of highest priority
     * 
     * @return first element in heap
     *         or null when heap is empty
     */
    public E peek(){
        return heap.getMin();
    }

    /**
     * Remove element of highest priority
     * 
     * @return removed element in heap
     *         or null when heap is empty
     */
    public E pop(){
        return heap.remove();
    }

    /**
     * Gets length of heap
     * 
     * @return num of elements
     */
    public int getLength(){
        return heap.size();
    }

    /**
     * Clears heap of all elements
     */
    public void clear(){
        heap.clear();
    }
}
