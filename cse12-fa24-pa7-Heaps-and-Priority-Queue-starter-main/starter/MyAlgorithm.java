/**
 * This file contains the MyAlgorithm class
 */

import java.util.ArrayList;

/**
 * This is the MyAlgorithm class that contains
 */
public class MyAlgorithm {

    /**
     * Gets the Integer element from the list
     * at the given index
     * 
     * @param list the list to sort and search
     * @param k the index to return
     * @throws NullPointerException when list is null
     * @throws IllegalArgumentException when there are no elements in list or
     *                                  when k <= 0 or
     *                                  when k >= num of elements in list
     * @return the Integer element at the k index
     */
    public static Integer getKthLargest (ArrayList<Integer> list, int k){
        if(list == null){
            throw new NullPointerException();
        }
        if(list.size() == 0){
            throw new IllegalArgumentException();
        }
        if(k <= 0 || k >= list.size()){
            throw new IllegalArgumentException();
        }

        MyPriorityQueue<Integer> heap = new MyPriorityQueue<>();
        
        for (int num : list) {
            heap.push(num);

            if (heap.getLength() > k) {
                heap.pop();
            }
        }
        return heap.peek();
    }
}
