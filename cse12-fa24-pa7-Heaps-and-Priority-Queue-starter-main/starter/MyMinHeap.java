/**
 * This is the MyMinHeap file that contains the MyMinHeap class
 */

import java.util.ArrayList;
import java.util.ArrayList;
import java.util.Collection;

/**
 * This is the MyMinHeap class which
 * implements MinHeapInterface
 */
public class MyMinHeap<E extends Comparable<E>> implements MinHeapInterface<E> {

    protected ArrayList<E> data;

    /**
     * No arg constructor that inizializes data into
     * an empty ArrayList
     */
    public MyMinHeap(){
        data = new ArrayList<>(10);
    }

    /**
     * Arg constructor that inizializes data with ArrayList()
     * and percolates each element accordingly
     * 
     * @param collection new data in ArrayList
     * @throws NullPointerException when collection, or anything in
     *                              collection is null.
     */
    public MyMinHeap(Collection<? extends E> collection){
        if(collection == null){
            throw new NullPointerException();
        }
        data = new ArrayList<>(collection);
        for(int i = collection.size() - 1; i >= 0; i--){
            percolateDown(i);
        }
    }

    /**
     * Insert an element to end of the ArrayList
     * and perlocate the element up until it
     * satisfies the MinHeap requirements
     * 
     * @param element new element to insert
     * @throws NullPointerException when element is null
     */
    public void insert(E element) {
        if(element == null){
            throw new NullPointerException();
        }
        data.add(element);
        percolateUp(size() - 1);
    }

    /**
     * Gets the root (smallest element)
     * of the heap, returns null
     * if size = 0
     * 
     * @return null if no elements
     *         or the root element
     */
    public E getMin() {
        if(data.size() == 0){
            return null;
        }
        return data.get(0);
    }

    /**
     * Removes the root of the heap
     * and returns it. If heap is empty,
     * return null
     * 
     * @return null if no elements
     *         or the index of removed element
     */
    public E remove() {
        if(data.size() == 0){
            return null;
        }
        return deleteIndex(0);
    }

    /**
     * Gets the num of elements in the heap
     * 
     * @return num of size
     */
    public int size() {
        int size = 0;
        for(int i = 0; i < data.size(); i++){
            size++;
        }
        return size;
    }

    /**
     * Clear the entire heap
     * and reset size
     */
    public void clear() {
        data = new ArrayList(0);
    }

    // ------ Helper Methods ------

    /**
     * Swap the element at index from and to
     * 
     * @param from index to swap from
     * @param to index to swap to
     */
    protected void swap(int from, int to){
        E dataFrom = data.get(from);
        E dataTo = data.get(to);

        data.set(to, dataFrom);
        data.set(from, dataTo);
    }

    /**
     * Gets the parent index from given index
     * 
     * @param index num of index to start at
     * @return index of parent
     */
    protected static int getParentIdx(int index){
        int parentIndex = (index - 1) / 2;
        return parentIndex;
    }

    /**
     * Gets the left child index from given index
     * 
     * @param index num of index to start at
     * @return index of left child
     */
    protected static int getLeftChildIdx(int index){
        int leftChildIndex = (index * 2) + 1;
        return leftChildIndex;
    }

    /**
     * Gets the right child index from given index
     * 
     * @param index num of index to start at
     * @return index of right child
     */
    protected static int getRightChildIdx(int index){
        int rightChildIndex = (index * 2) + 2;
        return rightChildIndex;
    }

    /**
     * Gets the index of smaller child
     * 
     * @param index num of index to start at
     * @return index of smaller element
     */
    protected int getMinChildIdx(int index){
        if (getLeftChildIdx(index) >= data.size()){
            return -1;
        }
        else if(getRightChildIdx(index) >= data.size() && 
                getLeftChildIdx(index) < data.size()){
            return getLeftChildIdx(index);
        }
        else if(data.get(getLeftChildIdx(index)).compareTo
                (data.get(getRightChildIdx(index))) > 0){
            return getRightChildIdx(index);
        }
        else if(data.get(getLeftChildIdx(index)).compareTo
                (data.get(getRightChildIdx(index))) < 0){
            return getLeftChildIdx(index);
        }
        return getLeftChildIdx(index);
    }

    /**
     * Bring the index element up to parent index
     * when child < parent
     * 
     * @param index num of index to swap
     */
    protected void percolateUp(int index){
        while(index > 0){
            int parentIdx = getParentIdx(index);
            if(data.get(parentIdx).compareTo(data.get(index)) <= 0){
                break;
            }

            swap(index, parentIdx);
            index = parentIdx;
        }
    }

    /**
     * Bring the index element down to child
     * when parent > child
     * 
     * @param index num of index to swap
     */
    protected void percolateDown(int index){
        while(true){
            int minChild = getMinChildIdx(index);

            if(minChild == -1){
                break;
            }
            if(data.get(minChild).compareTo(data.get(index)) >= 0){
                break;
            }
        
            swap(minChild, index);
            index = minChild;
        }
    }

    /**
     * Remove element at index and return it
     * and fixes the heap accordingly
     * 
     * @param index num of index to delete
     * @return the removed element at index
     */
    protected E deleteIndex(int index){
        E deletedElem = data.get(index);

        if(index == size() - 1){
            data.set(index, null);
            return deletedElem;
        }

        while(true){
            int replaceChild = getMinChildIdx(index);
            if(replaceChild == -1){
                break;
            }
            data.set(index, data.get(replaceChild));
            index = replaceChild;
        }
        data.set(index, data.get(size() - 1));
        data.remove(size() - 1);
        return deletedElem;
    }
}
