/**
 * This is the MyQueue file which contains the MyQueue class
 * that implements the QueueInterface file
 */

/**
 * This is the MyQueue class that contains methods for a Queue data structure
 */
public class MyQueue<E> implements QueueInterface<E> {
    MyDeque<E> theQueue;

    /**
     * Constructor to create new MyQueue that holds a MyDeque.
     *
     * @param initialCapacity The max amount of elements this data structure
     *                        can hold.
     */
    public MyQueue(int initialCapacity) {
        theQueue = new MyDeque<>(initialCapacity);
    }

    /**
     * Checks if the queue is empty.
     *
     * @return True if there are no elements in the queue, false otherwise.
     */
    @Override
    public boolean empty() {
        if(size() == 0){
            return true;
        }
        return false;
    }

    /**
     * Adds the specified element to the tail of this MyQueue.
     *
     * @param element the element to add to the queue
     */
    @Override
    public void enqueue(E element) {
        theQueue.addLast(element);
    }

    /**
     * Removes the element at the head of this MyQueue.
     * Returns the element removed, or null if there was no such
     * element.
     *
     * @return the element removed, or null if the size was zero.
     */
    @Override
    public E dequeue() {
        return theQueue.removeFirst();
    }

    /**
     * Returns the element at the head of this MyQueue,
     * or null if there was no such element.
     *
     * @return the element at the head, or null if the size was zero.
     */
    @Override
    public E peek() {
        return theQueue.peekFirst();
    }

    /**
     * Returns the number of elements in this queue.
     *
     * @return the number of elements in the queue
     */
    @Override
    public int size() {
        return theQueue.size();
    }
}
