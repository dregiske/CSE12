public class MyArrayList<E> implements MyList<E> {

    // instance variables
    Object[] data;
    int size;
    private static final int DEFAULT_CAPACITY = 5;

    // constructors
    public MyArrayList() { // no arg
        this.data = new Object[5];
        this.size = 0;
    }
    public MyArrayList(int intialCapacity) { // number initial cap arg
        if (intialCapacity < 0) {
            throw new IllegalArgumentException();
        }
        this.data = new Object[intialCapacity];
        this.size = 0;
    }
    public MyArrayList (E[] arr) { // arr arg
        if (arr != null){ // if arg is not null then set data to arg
            this.data = new Object[arr.length];
        }
        if(arr == null){ // if arg is null then set it to default cap, 5
            this.data = new Object[DEFAULT_CAPACITY];
        }
        else{ // else, copy
            for (int i = 0 ; i < arr.length; i++){
                this.data[i] = arr[i];
            }
            this.size = arr.length;
        }
    }

    /**
     * Increase the capacity of underlying array if needed
     *
     * @param requiredCapacity minimum capacity after expanding
     */
    public void expandCapacity(int requiredCapacity) {
        int EXPAND_CAPACITY = data.length * 2; // local variable for length x 2
        if (requiredCapacity < data.length){ // any required cap under length is invalid
            throw new IllegalArgumentException();
        }
        if(data.length == 0){ // when length is zero, set to default
            data = new Object[DEFAULT_CAPACITY];
        }
        else if(data.length > 0){ // when length is over 0, either paste array into expanded array, or required length array
            if (requiredCapacity > EXPAND_CAPACITY){
                E[] temp = (E[]) new Object[requiredCapacity];
                System.arraycopy(data, 0, temp, 0, data.length);
                data = temp;
            }
            else{
                E[] temp = (E[]) new Object[EXPAND_CAPACITY];
                System.arraycopy(data, 0, temp, 0, data.length);
                data = temp;
            }
        }
    }

    /**
     * Get the amount of elements array can hold
     *
     * @return number of elements that can be held
     */
    public int getCapacity() {
        return data.length; // returns length of data array
    }

    /**
     * Add an element at the specified index
     *
     * @param index   position to insert the element
     * @param element the element to insert
     */
    public void insert(int index, E element) {
        if (index < 0 || index > data.length){ // catches any invalid inputs for index
            throw new IndexOutOfBoundsException();
        }
        for(int i = size - 1; i >= index; i--){ // for loop starting at the back of array
            if(size >= data.length){ // increase size accordingly
                expandCapacity(size + 1);
            }
            data[i + 1] = data[i]; // pushes everything up
        }
        data[index] = element; // replaces element at index
        if(data[index] != null){ // increments size accordingly
            size++;
        }
    }

    /**
     * Add an element to the end of the list
     *
     * @param element the element to append
     */
    public void append(E element) {
        if(size >= data.length){ // increases capacity if size is greater or equal to length
            expandCapacity(size + 1);
        }
        data[size] = element; // sets final size to element
        if(element != null){ // increments size accordingly
            size++;
        }
    }

    /**
     * Add an element to the beginning of the list 
     *
     * @param element the element to prepend
     */
    public void prepend(E element) {
        if(size >= data.length){ // increase capacity if size is greater or equal to length
            expandCapacity(size + 1);
        }
        for(int i = size - 1; i >= 0; i--){ // pushes everything up
            data[i + 1] = data[i];
        }
        data[0] = element; // sets the first index to element

        if(element != null){ // increments size accordingly
            size++;
        }
    }

    /**
     * Get the element at the given index
     *
     * @param index the index at which to return the element
     * @return the element at the index
     */
    public E get(int index) {
        if (index < 0 || index > size){ // catches invalid index
            throw new IndexOutOfBoundsException();
        }
        for (int i = 0; i < data.length; i++){ // for loop to find element with index
            if(i == index){
                return (E) data[i]; // returns element at the index
            }
        }
        return null; // not found
    }

    /**
     * Replaces an element at the specified index with a new element and return
     * the original element
     *
     * @param index   the index at which to replace
     * @param element the element with which to replace
     * @return the original element
     */
    public E set(int index, E element) {
        if (index < 0 || index > data.length){ // catches invalid index
            throw new IndexOutOfBoundsException();
        }
        E originalElement = (E) data[index]; // saves original element
        data[index] = element; // sets index to new element
        return originalElement; // returns original element
    }

    /**
     * Remove the element at the specified index and return the removed element
     *
     * @param index the index at which to remove the element
     * @return the removed element
     */
    public E remove(int index) {
        if (index < 0 || index > data.length){ // catches invalid index options
            throw new IndexOutOfBoundsException();
        }
        E originalElement = (E) data[index]; // saves original element
        for(int i = index + 1; i <= data.length; i++){ // for loop to push everything down
            if(i >= data.length){
                data[i - 1] = null;
            }
            else{
                data[i - 1] = (E) data[i];
            }
        }
        size--; // decrease size after removal
        return originalElement; // returns original element
    }

    /**
     * Get the number of elements in the list
     *
     * @return number of elements in the list
     */
    public int size() { // returns size
        return size;
    }

    /**
     * Rotate the list by numPositions number of positions to the right
     * 
     * @param numPositions the number of positions to rotate by
     */
    public void rotate(int numPositions) {
        if (numPositions < 0 || numPositions > data.length) { // catch errors
            throw new IndexOutOfBoundsException();
        }
        numPositions = numPositions % size; // calculate for wrap around
    
        if (numPositions == 0) { // no rotation stays the same
            return;
        }
        E[] newArray = (E[]) new Object[data.length]; // create a new array
    
        for (int i = 0; i < size; i++) { // for loop to copy elements in rotated position
            int newIndex = (i + numPositions) % size;
            newArray[newIndex] = (E) data[i];
        }
        System.arraycopy(newArray, 0, data, 0, data.length); // put new array back to data array
    }

    /**
     * Find the element in the list and return its index
     * 
     * @param element the element to find
     * @return the index of the last occurrence of element (-1 if not found)
     */
    public int find(E element) { // loops from back to return the last occurance of element
        for (int i = data.length - 1; i >= 0; i--){ 
            if (data[i].equals(element)){
                return i; // returns index
            }
        }
        return -1; // not found
    }
}