/**
 * This is the MyBST file that contains the MyBST class
 */

import java.util.ArrayList;

/**
 * This is the MyBST class
 */
public class MyBST<K extends Comparable<K>, V> {
    MyBSTNode<K, V> root = null;
    int size = 0;

    /**
     * Gets num of elements in tree
     * 
     * @return size of tree
     */
    public int size() {
        return size;
    }

    /**
     * Put a new node with given key and value.
     * If key already exists, replace value with new value.
     * Update size accordingly
     * 
     * @param key where to insert
     * @param value what to insert
     * @throws NullPointerException when key is null
     * @return null if no value was replaced,
     *         or the previous value if it was replaced
     */
    public V insert(K key, V value) {
        if(key == null){
            throw new NullPointerException();
        }

        MyBST.MyBSTNode<K, V> curNode = root;

        if(curNode == null){
            MyBSTNode newRoot = new MyBSTNode<K,V>(key, value, null);
            root = newRoot;
            size++;
            return null;
        }
        while(curNode != null && curNode.getKey() != null){
            if(curNode.getKey() == key){
                V temp = curNode.getValue();
                curNode.setValue(value);
                return temp;
            }
            if(key.compareTo(curNode.getKey()) < 0){
                if(curNode.getLeft() == null){
                    MyBSTNode newNodeLeft = new MyBSTNode<K,V>(key, value, curNode);
                    curNode.setLeft(newNodeLeft);
                    size++;
                    return null;
                }
                curNode = curNode.getLeft();
            }
            else if(key.compareTo(curNode.getKey()) > 0){
                if(curNode.getRight() == null){
                    MyBSTNode newNodeRight = new MyBSTNode<K,V>(key, value, curNode);
                    curNode.setRight(newNodeRight);
                    size++;
                    return null;
                }
                curNode = curNode.getRight();
            }
        }
        return null;
    }

    /**
     * Search node by key and return value
     * 
     * @param key where to search
     * @return value of key
     */
    public V search(K key) {

        MyBST.MyBSTNode<K, V> curNode = root;

        while(key != curNode.getKey()){
            if(key.compareTo(curNode.getKey()) < 0){
                if(curNode.getLeft().getKey() == null){
                    return null;
                }
                else{
                    curNode = curNode.getLeft();
                }    
            }
            else if(key.compareTo(curNode.getKey()) > 0){
                if(curNode.getRight() == null){
                    return null;
                }
                else{
                    curNode = curNode.getRight();
                }
            }
        }
        return curNode.getValue();
    }

    /**
     * Remove the node with the given key
     * and fix all references accordingly
     * 
     * @param key what to remove
     * @return the value of the removed node
     */
    public V remove(K key) {

        MyBST.MyBSTNode<K, V> curNode = root;
        
        if(search(key) == null){
            return null;
        }
        while(key != curNode.getKey()){
            if(key.compareTo(curNode.getKey()) < 0){
                curNode = curNode.getLeft();
            }    
            else if(key.compareTo(curNode.getKey()) > 0){
                curNode = curNode.getRight();
            }
        }
        size--;
        V temp = curNode.getValue();

        if(curNode.getLeft() == null && curNode.getRight() == null){
            if(curNode.getParent() == null){
                curNode.setKey(null);
                curNode.setValue(null);
                return null;
            }
            if(curNode.getParent().getKey().compareTo(curNode.getKey()) > 0){
                curNode.getParent().setLeft(null);
            }
            if(curNode.getParent().getKey().compareTo(curNode.getKey()) < 0){
                curNode.getParent().setRight(null);
            }
            return temp;
        }
        else if(curNode.getLeft() != null && curNode.getRight() != null){
            curNode.getLeft().setParent(curNode.getParent());
            curNode.getRight().setParent(curNode.getLeft());
            return temp;
        }
        else if(curNode.getLeft() != null && curNode.getRight() == null){
            if(curNode.getParent().getKey().compareTo(curNode.getKey()) > 0){
                curNode.getParent().setLeft(curNode.getLeft());
                curNode.getLeft().setParent(curNode.getParent());
            }
            if(curNode.getParent().getKey().compareTo(curNode.getKey()) < 0){
                curNode.getParent().setRight(curNode.getLeft());
                curNode.getLeft().setParent(curNode.getParent());
            }
            return temp;
        }
        else if(curNode.getLeft() == null && curNode.getRight() != null){
            if(curNode.getParent().getKey().compareTo(curNode.getKey()) > 0){
                curNode.getParent().setLeft(curNode.getRight());
                curNode.getRight().setParent(curNode.getParent());
            }
            if(curNode.getParent().getKey().compareTo(curNode.getKey()) < 0){
                curNode.getParent().setRight(curNode.getRight());
                curNode.getRight().setParent(curNode.getParent());
            }
            return temp;
        }
        return null;
    }

    /**
     * Do an in order traversal of the tree,
     * adding each element to the array
     * 
     * @return the array list, empty array list
     *         if no nodes exist
     */
    public ArrayList<MyBSTNode<K, V>> inorder() {

        ArrayList<MyBSTNode<K, V>> nodeArray = new ArrayList<>();

        if(size == 0){
            ArrayList<MyBSTNode<K, V>> emptyArray = new ArrayList<>();
            return emptyArray;
        }
        return nodeArray;
    }

    /**
     * Copy and paste the BST
     * 
     * @return the copied BST
     */
    public MyBST<K, V> copy() {
        return null;
    }

    static class MyBSTNode<K, V>{
        private static final String TEMPLATE = "Key: %s, Value: %s";
        private static final String NULL_STR = "null";

        private K key;
        private V value;
        private MyBSTNode<K, V> parent;
        private MyBSTNode<K, V> left = null;
        private MyBSTNode<K, V> right = null;

        /**
         * Creates a MyBSTNode<K,V> storing specified data
         *
         * @param key    the key the MyBSTNode<K,V> will
         * @param value  the data the MyBSTNode<K,V> will store
         * @param parent the parent of this node
         */
        public MyBSTNode(K key, V value, MyBSTNode<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        /**
         * Return the key stored in the the MyBSTNode<K,V>
         *
         * @return the key stored in the MyBSTNode<K,V>
         */
        public K getKey() {
            return key;
        }

        /**
         * Set the key stored in the MyBSTNode<K,V>
         *
         * @param newKey the key to be stored
         */
        public void setKey(K newKey) {
            this.key = newKey;
        }

        /**
         * Return data stored in the MyBSTNode<K,V>
         *
         * @return the data stored in the MyBSTNode<K,V>
         */
        public V getValue() {
            return value;
        }

        /**
         * Set the data stored in the MyBSTNode<K,V>
         *
         * @param newValue the data to be stored
         */
        public void setValue(V newValue) {
            this.value = newValue;
        }

        /**
         * Return the parent
         *
         * @return the parent
         */
        public MyBSTNode<K, V> getParent() {
            return parent;
        }

        /**
         * Set the parent
         *
         * @param newParent the parent
         */
        public void setParent(MyBSTNode<K, V> newParent) {
            this.parent = newParent;
        }

        /**
         * Return the left child
         *
         * @return left child
         */
        public MyBSTNode<K, V> getLeft() {
            return left;
        }

        /**
         * Set the left child
         *
         * @param newLeft the new left child
         */
        public void setLeft(MyBSTNode<K, V> newLeft) {
            this.left = newLeft;
        }

        /**
         * Return the right child
         *
         * @return right child
         */
        public MyBSTNode<K, V> getRight() {
            return right;
        }

        /**
         * Set the right child
         *
         * @param newRight the new right child
         */
        public void setRight(MyBSTNode<K, V> newRight) {
            this.right = newRight;
        }

        /**
         * Gets the smallest key which is still larger
         * than the key of the current node. If there
         * is no larger key, return null
         * 
         * @return the key above current key
         *         and null if none exists
         */
        public MyBSTNode<K, V> successor() {

            MyBST.MyBSTNode<K, V> curNode = this;

            if(curNode.getRight() != null){
                curNode = curNode.getRight();
                while(curNode.getLeft() != null){
                    curNode = curNode.getLeft();
                }
                return curNode;
            }
            else if((Integer) curNode.getParent().getKey() < (Integer) curNode.getKey()){
                while(curNode.getParent() != null){
                    if((Integer) curNode.getParent().getKey() > (Integer) curNode.getKey()){
                        return curNode.getParent();
                    }
                    curNode = curNode.getParent();
                }
                if(curNode.getParent() == null){
                    return null;
                }
            }
            else if((Integer) curNode.getParent().getKey() > (Integer) curNode.getKey()
                    && curNode.getRight() == null){
                return curNode.getParent();
            }
            return null; 
        }

        /**
         * This method compares if two node objects are equal.
         *
         * @param obj The target object that the currect object compares to.
         * @return Boolean value indicates if two node objects are equal
         */
        public boolean equals(Object obj) {
            if (!(obj instanceof MyBSTNode))
                return false;

            MyBSTNode<K, V> comp = (MyBSTNode<K, V>) obj;

            return ((this.getKey() == null ? comp.getKey() == null :
                    this.getKey().equals(comp.getKey()))
                    && (this.getValue() == null ? comp.getValue() == null :
                    this.getValue().equals(comp.getValue())));
        }

        /**
         * This method gives a string representation of node object.
         *
         * @return "Key:Value" that represents the node object
         */
        public String toString() {
            return String.format(
                    TEMPLATE,
                    this.getKey() == null ? NULL_STR : this.getKey(),
                    this.getValue() == null ? NULL_STR : this.getValue());
        }
    }
}
