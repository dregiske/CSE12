# CSE 12 PA5: Hash Tables

**Due date: Thursday, November 7th @ 11:59 PM PST**

There is an FAQ post on Piazza. Please read that post first if you have any questions.


## **Learning goals:**



* Implement data structures similar to Java’s HashMap and HashSet with generic types
* Write JUnit tests to verify proper implementation



## Testing and Implementation of HashMap and HashSet [100 points]

You will implement a HashSet and HashMap as well as write JUnit tests to ensure that your implementation functions correctly.

**Read the entire write-up before you start coding.**

Download the starter code and put it in a directory in your working environment.

You will find the following files:

```
+-- PA5
|   +-- MyHashMap.java       Edit this file
|   +-- MyHashSet.java       Edit this file
|   +-- PublicTester.java
|   +-- CustomTester.java    Edit this file
```


### Part 1: JUnit Testing (20 points)

We provide two test files:

* `PublicTester.java`
    * This contains all the public test cases we will use to grade your MyHashMap and MyHashSet implementations (visible on Gradescope).
* `CustomTester.java`
    * Contains some of the headers and description to the hidden test cases we will use to grade your MyListIterator implementation (hidden until the PA is graded). ⚠️There are hidden tests on Gradescope not described in the write-up. Make sure to write additional tests to verify your implementation's correctness! ⚠️

Your task: Implement the missing unit tests in `CustomTester.java` based on the description in the Tests table below.

* Your tests will be graded by checking if they pass on a good implementation and fail on a bad implementation. If a test fails on a good implementation, then the test is written incorrectly. If a test passes on a bad implementation, it may be written incorrectly or may be not be rigorous enough (try adding more asserts).
* Some of your tests will be run on several bad implementations. You will receive 2 pts for every bad implementation your test fails (if your test also passes on the good implementation).
* DO NOT CHANGE THE TEST HEADERS!


#### Tests Table: List of test cases to write and their description
<table>
  <tr>
    <td>
      <strong>Test Cases</strong>
    </td>
    <td>
      <strong>Description</strong>
    </td>
    <td>
      <strong>Point Value</strong>
    </td>
  </tr>

  <tr>
   	<td>
   	  <code>testMyHashMapConstructorInvalidCapacity</code>
   	</td>
   	<td>
        Call <code>MyHashMap</code> constructor with an invalid value for the initial capacity.
   	</td>
   	<td>2</td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testMyHashMapGetNullKey</code>
   	</td>
   	<td>
        Call <code>get</code> in MyHashMap with a null value for the key.
   	</td>
   	<td>2</td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testMyHashMapGetKeyDoesNotExist</code>
   	</td>
   	<td>
        Call <code>get</code> in MyHashMap with a key that does not exist in the hash table.
   	</td>
   	<td>2</td>
  </tr>
  
  <tr>
    <td>
      <code>testMyHashMapPutNullKey</code>
    </td>
    <td>
      Call <code>put</code> in MyHashMap with a null value for the key.
    </td>
    <td>2</td>
  </tr>

  <tr>
    <td>
      <code>testMyHashMapPutKeyAlreadyExists</code>
    </td>
    <td>
      Call <code>put</code> in MyHashMap with a key that already exists in the hash table.
    </td>
    <td>2</td>
  </tr>

  <tr>
    <td>
       <code>testMyHashMapRemoveNullKey</code>
    </td>
    <td>
      Call <code>remove</code> in MyHashMap with a null value for the key.
    </td>
    <td>2</td>
  </tr>
  
  <tr>
    <td>
       <code>testMyHashMapRemoveKeyDoesNotExist</code>
    </td>
    <td>
      Call <code>remove</code> in MyHashMap with a key that does not exist in the hash table.
    </td>
    <td>2</td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testMyHashMapGetHashNullKey</code>
   	</td>
   	<td>
        Call <code>getHash</code> in MyHashMap with a null value for the key.
   	</td>
   	<td>2</td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testMyHashSetAddAlreadyExists</code>
   	</td>
   	<td>
        Call <code>add</code> in MyHashSet with an element that already exists in the set.
   	</td>
   	<td>2</td>
  </tr>
  
  <tr>
   	<td>
   	  <code>testMyHashSetRemoveDoesNotExist</code>
   	</td>
   	<td>
        Call <code>remove</code> in MyHashSet with an element that does not exist in the set.
   	</td>
   	<td>2</td>
  </tr>

</table>

#### How to compile and run the testers:
Running the tester on UNIX based systems (including a mac):

* Compile: `javac -cp ../libs/junit-4.13.2.jar:../libs/hamcrest-2.2.jar:. PublicTester.java`
* Execute: `java -cp ../libs/junit-4.13.2.jar:../libs/hamcrest-2.2.jar:. org.junit.runner.JUnitCore PublicTester`

Running the tester on Windows systems:

* Compile: `javac -cp ".;..\libs\junit-4.13.2.jar;..\libs\hamcrest-2.2.jar" PublicTester.java`
* Execute: `java -cp ".;..\libs\junit-4.13.2.jar;..\libs\hamcrest-2.2.jar" org.junit.runner.JUnitCore PublicTester`

You should run the above commands in the starter directory. To run the custom tester, replace references to PublicTester with CustomTester in the above commands.

⚠️Your code must first compile in order to receive credit for the different test cases. You will receive a zero if your code doesn’t compile.


### Part 2: Implementation of HashMap and HashSet (75 points)

**What you will do:**

* Read through the method descriptions on the following table for the MyHashMap and MyHashSet classes. Understand well the behavior of each method.
* Implement these two classes with all of the following methods.

#### MyHashMap<K,V>

You will first complete the methods to implement the `MyHashMap` class in `MyHashMap.java`.

`MyHashMap` should use 2 generic types:
* K - the type of keys maintained by this map
* V - the type of mapped values

**Note**: `MyHashMap` objects will be used in the `MyHashSet` class.

**Note**: `MyHashMap` will **NOT** store null keys or null values.

#### Node Inner Class

In the starter code you can see that the `MyHashMap` class uses a nested class (that is, a class inside a class) to represent a node in your hash table. To accomplish this, you can’t declare a `Node` class as public, but you can include it in the same file (and even in the same class) as `MyHashMap`. This is necessary for us to store (key, value) pairs in the hash table since we will use separate chaining via a linked list (similar to PA3) for collision resolution. Please **do not use sentinel nodes** and keep in mind that this is a **singly** linked list. The node class has been implemented for you already.

#### MyHashMap<K,V>

|Instance Variable|Description|
|--- |--- |
|`Node[] hashTable`|The underlying array for the hash table.|
|`int size`|The number of (key, value) pairs currently in the hash table.|



You will be required to implement the following methods.



|Method Name|Description|Exceptions to Throw|
|--- |--- |--- |
|`public MyHashMap()`|Initialize the hash table with a default initial capacity of 5.||
|`public MyHashMap(int initialCapacity)`|Initialize the hash table with the initial capacity given.|Throw an `IllegalArgumentException` if initialCapacity is 0 or negative.|
|`public V get(K key)`|Returns the value to which the specified key is mapped, or null if this key is not in the hash map.|Throw a `NullPointerException` if key is null.|
|`public V put(K key, V value)`|Associates the specified value with the specified key in this map. At the start of this method, before attempting to add this mapping to the hash table, check if the hash table is at or above its maximum load of 80% of its capacity (i.e. if size is at least 80% of the current capacity). If so, expand the capacity to double its current capacity. Return the value previously mapped to this key or null if this key was not previously in the hash map.|Throw a `NullPointerException` if key is null or if value is null.|
|`public V remove(K key)`|Removes the mapping for the specified key from this map if present. Return the value previously mapped to this key or null if this key was not in the hash map. Adjust the Node linked list appropriately to reflect this removal.|Throw a `NullPointerException` if key is null.|
|`public int size()`|Return the number of (key, value) pairs in the hash map.||
|`public int getCapacity()`|Return the capacity of the hash map.||
|`public void clear()`|Removes all of the mappings from this hash map.||
|`public boolean isEmpty()`|If the map is empty, return `true`. Otherwise, return `false`.||
|`public void expandCapacity()`|Double the capacity of the hash table and rehash all (key, value) pairs into the new hash table. This method should only be called when the map is at or above its maximum load.||
|`public int getHash(K key, int capacity)`|Verify the given key and capacity are valid. Use the given hash function in the starter code to compute hash codes. This hash function uses the Object class's hashCode() method and returns the hash value computed as: (key.hashCode() & 0x7fffffff) % capacity. (This hash function ensures the hash code obtained is between 0 and capacity - 1.)|Throw a `NullPointerException` if key is null. Throw an `IllegalArgumentException` if capacity is 0 or negative.|



#### MyHashSet\<E\>

Now, you will complete the methods to implement the `MyHashSet` class in `MyHashSet.java`.

`MyHashSet` should be of generic type E.

The `MyHashSet` class is an implementation of the set abstract data type using a `MyHashMap` object. The `MyHashSet` object should have keys of type E (whatever type of element is stored in the hash set) and values of type Object, which will be used as a placeholder with a default value.

**Note**: `MyHashSet` will **NOT** store null elements.



|Instance Variable|Description|
|--- |--- |
|`public static final Object DEFAULT_OBJECT`|A default Object to use as a placeholder value in the underlying hashMap.|
|`MyHashMap hashMap`|The underlying hashMap for the hash set.|



You will be required to implement the following methods.



|Method Name|Description|Exceptions to Throw|
|--- |--- |--- |
|`public MyHashSet()`|Initialize the hash map with a default capacity of 5.||
|`public MyHashSet(int initialCapacity)`|Initialize the hash map with the initial capacity given.|Throw an `IllegalArgumentException` if initialCapacity is 0 or negative.|
|`public boolean add(E element)`|Adds the specified element to this set if it is not already present. Return `true` if the set did not already contain the specified element or `false` otherwise.|Throw a `NullPointerException` if element is null.|
|`public boolean remove(E element)`|Removes the specified element from this set if it is present. Return `true` if the set contained the specified element or `false` otherwise.|Throw a `NullPointerException` if element is null.|
|`public int size()`|Returns the number of elements in this set (its cardinality).||
|`public void clear()`|Removes all of the elements from this set.||
|`public boolean isEmpty()`|Returns true if this set contains no elements.||

**NOTE**: The methods in `MyHashSet` should be relatively simple if you have implemented the methods in the `MyHashMap` class correctly.

### **Part 3: Coding Style (5 points)**

Coding style is an important part of ensuring readability and maintainability of your code. We will grade your code style in all submitted code files according to the style guidelines. Namely, there are a few things you must have in each file/class/method:

* File header
* Class header
* Method header(s)
* Inline comments
* Proper indentation
* Descriptive variable names
* No magic numbers (Exception: Magic numbers can be used for testing.)
* Reasonably short methods (if you have implemented each method according to the specification in this write-up, you’re fine). This is not enforced as strictly.
* Lines shorter than 80 characters
* Javadoc conventions (`@param`, `@return` tags, `/** comments */`, etc.)


A full style guide can be found [here](https://github.com/CaoAssignments/style-guide/blob/main/README.md) and a sample styled file can be found [here](https://github.com/CaoAssignments/guides/blob/main/resources/SampleFile.java). If you need any clarifications, feel free to ask on Piazza.


## Submission Instructions

#### Turning in your code

* Submit all of the following files to Gradescope
    * `MyHashMap.java`
    * `MyHashSet.java`
    * `CustomTester.java`

**Important**: Even if your code does not pass all the tests, you will still be able to submit your homework to receive partial points for the tests that you passed. Make sure your code compiles in order to receive partial credit.

#### How your assignment will be evaluated [100 points]

* **Correctness** [95 points] You will earn points based on the autograder tests that your code passes. If the autograder tests are not able to run (e.g., your code does not compile or it does not match the specifications in this writeup), you may not earn credit.
    * Tester [20 points]
        * The autograder will test your implementation of the JUnit tests. Your unit tests are expected to fail or pass based on the tests above.
        * This section has a maximum of 20 pts.
    * HashMap/HashSet Implementation [75 points]
        * The autograder will test your implementation on the public test cases given in `PublicTester.java` and hidden test cases not described in this PA writeup.
* **Coding Style** [5 points]
    * `MyHashMap.java` and `MyHashSet.java` will be graded on style. `CustomTester.java` will be graded on file, class, method headers and indentation.


## Appendix

### **Example of MyHashMap Methods** 

We use an array of type `Node` as the underlying data structure. Assume all hashes are generated from a call to `getHash()`. We use separate chaining with a linked list for collisions (note that this is a singly linked list with no sentinel nodes, which is different from PA3).

**MyHashMap should look like after initialization:**

![initial](https://github.com/user-attachments/assets/2e0f4512-955a-4fda-aea3-4922bdfab6e8)

After calling the constructor, `size` should be 0. All of the indices of our array should be `null`.

**After calling `put("A", 1)`:**

![insert-A](https://github.com/user-attachments/assets/123a81ab-388d-48a3-9896-5dc274893e7c)

`null` should be returned. `size` is 1.

**After calling `put("G", 7)`:**

![insert-G](https://github.com/user-attachments/assets/6a3b320c-f6ef-4cd6-96d8-6d68467df030)

`null` should be returned. `size` is 2.

**After calling `put("B", 2)`:**

![insert-B](https://github.com/user-attachments/assets/16481de6-cca3-4743-8bfd-f5dd2c38a539)

`null` should be returned. `size` is 3.

**After calling `remove("G")`:**

![remove-G](https://github.com/user-attachments/assets/25b9713c-9bff-4362-bddd-54b495662b9b)

`7` should be returned. `size` is 2.

**After calling `put("F", 6)`:**

![insert-F](https://github.com/user-attachments/assets/aee81ff2-4a4a-452d-a0c4-b933d138749d)

`null` should be returned. `size` is 3.

**After calling `put("A", 2)`:**

![replace-A](https://github.com/user-attachments/assets/f2af7694-78a2-48c7-9573-7655797d5ff7)

The value at node A is replaced. `1` should be returned. `size` is 3.

**After manually calling `expandCapacity()`:**

![expand](https://github.com/user-attachments/assets/a5ec0fef-320e-4f4f-8d74-2d5e0f447b1d)

All nodes, including nodes on the same linked list, are re-hashed.
