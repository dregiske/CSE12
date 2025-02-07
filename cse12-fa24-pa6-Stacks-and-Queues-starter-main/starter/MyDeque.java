/**
 * This is the MyDeque file which contains the MyDeque class
 * that implements the DequeInterface file
 */

/**
 * This is the MyDeque class which contains the methods for Deque data structure
 */
public class MyDeque<E> implements DequeInterface<E> {

    Object[] data;
    int size;
    int rear;
    int front;

    /**
     * Constructor method that inizializes data array with
     * length of input capacity
     * 
     * @param initialCapacity input capacity for new array
     * @throws IllegalArgumentException when initialCapacity is negative
     */
    public MyDeque(int initialCapacity){
        if(initialCapacity < 0){
            throw new IllegalArgumentException();
        }
        data = new Object[initialCapacity];
        size = 0;
        rear = 0;
        front = 0;
    }

    /**
     * Returns the number of elements in this DequeInterface.
     * 
     * @return the number of elements in this DequeInterface
     */
    public int size(){
        return size;
    }

    /**
     * Doubles the capacity of this DequeInterface. If the capacity
     * is 0, set capacity to some default capacity. No elements in this
     * DequeInterface should change after the expansion.
     */
    public void expandCapacity(){
        int DEFAULT_CAPACITY = 10;
        int EXPAND_CAPACITY_RATIO = 2;
        int expandedCapacity = data.length * EXPAND_CAPACITY_RATIO;

        if(data.length == 0){
            data = new Object[DEFAULT_CAPACITY];
            size = 0;
            front = 0;
            rear = 0;
        }
        else{
            Object[] newData = new Object[expandedCapacity];
            for(int i = 0; i < data.length; i++){
                newData[i] = data[i];
            }
            data = newData;
        }
    }

    /**
     * Adds the specified element to the front of this DequeInterface. If this
     * DequeInterface is at capacity, expandCapacity is called to double the
     * size of this container.
     *
     * @param element the element to add to the front of the array
     * @throws NullPointerException if the specified element is null.
     */
    public void addFirst(E element){
        if(element == null){
            throw new NullPointerException();
        }
        if(size == data.length){
            expandCapacity();
        }
        if(front == 0 && data[front] != null){
            front = data.length - 1;
            data[front] = element;
            size++;
        }
        else if(front == 0 && data[front] == null){
            data[front] = element;
            size++;
        }
        else if(data[front - 1] == null){
            front--;
            data[front] = element;
            size++;
        }
        else if(data[front - 1] != null){
            front = data.length - 1;
            data[front] = element;
            size++;
        }
    }

    /**
     * Adds the specified element to the back of this DequeInterface. If the
     * DequeInterface is at capacity, expandCapacity is called to double the
     * size of this container.
     * 
     * @param element the element to add to the back of the array
     * @throws NullPointerException if the specified element is null.
     */
    public void addLast(E element){
        if(element == null){
            throw new NullPointerException();
        }
        if(size == data.length){
            expandCapacity();
        }

        if(rear == 0 && data[rear] == null){
            data[rear] = element;
            size++;
        }
        else if(rear == 0 && data[rear] != null){
            rear++;
            data[rear] = element;
            size++;
        }
        else if(rear == data.length - 1){
            rear = 0;
            data[rear] = element;
            size++;
        }
        else if(data[rear + 1] == null){
            rear++;
            data[rear] = element;
            size++;
        }
        else if(data[rear + 1] != null){
            rear = data.length - 1;
            data[rear] = element;
            size++;
        }
    }

    /**
     * Removes the element at the front of this DequeInterface, and returns the
     * element removed, or null if there was no such element.
     *
     * @return the element removed, or null if there's none.
     */
    public E removeFirst(){
        if(data.length == 0){
            return null;
        }
        if(data[front] == null){
            return null;
        }
        E returnData = (E) data[front];
        data[front] = null;
        size--;
        front++;
        if(front <= data.length){
            for(int i = 0; i < data.length; i++){
                if(data[i] != null){
                    front = i;
                    return returnData;
                }
            }
        }
        return returnData;
    }

    /**
     * Removes the element at the back of this DequeInterface, and returns the
     * element removed, or null if there was no such element.
     *
     * @return the element removed, or null if there's none.
     */
    public E removeLast(){
        if(data.length == 0){
            return null;
        }
        if(data[rear] == null){
            return null;
        }
        E returnData = (E) data[rear];
        data[rear] = null;
        size--;
        rear--;
        if(rear <= 0){
            for(int i = data.length - 1; i > 0; i--){
                if(data[i] != null){
                    rear = i;
                    return returnData;
                }
            }
        }
        return returnData;
    }

    /**
     * Returns the element at the front of this DequeInterface,
     * or null if there was no such element.
     * 
     * @return the element at the front, or null if there's none.
     */
    public E peekFirst(){
        if(data.length == 0){
            return null;
        }
        if(data[front] == null){
            return null;
        }
        return (E) data[front];
    }

    /**
     * Returns the element at the back of this DequeInterface,
     * or null if there was no such element.
     * 
     * @return the element at the back, or null if there's none.
     */
    public E peekLast(){
        if(data.length == 0){
            return null;
        }
        if(data[rear] == null){
            return null;
        }
        return (E) data[rear];
    }
}