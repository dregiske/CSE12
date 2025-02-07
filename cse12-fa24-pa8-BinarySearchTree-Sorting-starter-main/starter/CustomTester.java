/**
 * This is the custom tester file which contains the CustomTester class
 */

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This is the CustomTester class which tests cases
 * for MyBST java file
 */
public class CustomTester {
    MyBST<Integer, Integer> tree;

    @Before
    public void setup() {
        MyBST.MyBSTNode<Integer, Integer> root =
                new MyBST.MyBSTNode<>(4, 1, null);
        MyBST.MyBSTNode<Integer, Integer> two =
                new MyBST.MyBSTNode<>(2, 1, root);
        MyBST.MyBSTNode<Integer, Integer> six =
                new MyBST.MyBSTNode<>(6, 1, root);
        MyBST.MyBSTNode<Integer, Integer> one =
                new MyBST.MyBSTNode<>(1, 2, two);
        MyBST.MyBSTNode<Integer, Integer> three =
                new MyBST.MyBSTNode<>(3, 30, two);
        MyBST.MyBSTNode<Integer, Integer> five =
                new MyBST.MyBSTNode<>(5, 50, six);

        this.tree = new MyBST<>();
        this.tree.root = root;
        root.setLeft(two);
        root.setRight(six);
        two.setLeft(one);
        two.setRight(three);
        six.setLeft(five);
        tree.size = 6;
    }

    @Test
    public void successorOnGreatestKey(){
        MyBST.MyBSTNode<Integer, Integer> greatestKey = tree.root;
        assertNull("Checks that greatest keys successor is null",
            greatestKey.getRight().successor());
    }

    @Test
    public void insertNull(){
        assertThrows("Throws error when key is null",
            NullPointerException.class,
            ()-> tree.insert(null, 30));
    }

    @Test
    public void insertOnExistingKey(){
        MyBST.MyBSTNode<Integer, Integer> treeRoot = tree.root;
        tree.insert(2, 30);
        assertSame("Checks that value is new value",
            30, treeRoot.getLeft().getValue());
        assertSame("Checks that old value is returned",
            tree.insert(2, 20), 30);
        assertSame("Checks that size is not changed",
            6, tree.size);
    }

    @Test
    public void insertOnNullRoot(){
        tree.remove(5);
        tree.remove(3);
        tree.remove(1);
        tree.remove(6);
        tree.remove(2);
        tree.remove(4);
        assertNull("Checks that null is returned when inserting on null root",
            tree.insert(10, 10));
    }

    @Test
    public void searchOnNonExistingKey(){
        assertNull("Checks that null is returned when key does not exist",
            tree.search(10));
    }

    @Test
    public void removeOnNonExistingKey(){
        assertNull("Checks that null is returned when key does not exist",
            tree.remove(10));
        
    }
}
