/**
 * This is the MinHeapInterface file that contains
 * the MinHeapInterface interface
 */

/** 
 * This is the MinHeapInterface that gets implemented
 * into the MyMinHeap file
 */
public interface MinHeapInterface<E extends Comparable<E>> {
    void insert(E element);
    E getMin();
    E remove();
    int size();
    void clear();
}