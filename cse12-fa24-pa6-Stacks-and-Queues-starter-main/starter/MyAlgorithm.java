/**
 * This file contains the MyAlgorithm class for MyStack or MyQueue
 */

/**
 * This class contains an algorithm utilizing a stack or queue. 
 */
public class MyAlgorithm<E> {
    /**
     * Returns whether or not the given string contains a valid arrangement of brackets
     * 
     * @param input the input string containing brackets
     * @throws NullPointerException if the given string is null
     * @return the whether or not the given string contains a valid arrangement of brackets
     */
    public static boolean isValidBrackets(String input) {
        MyStack stack = new MyStack<>(input.length());
        for(char character : input.toCharArray()){
            char openA = '(';
            char openB = '[';
            char openC = '{';
            char closedA = ')';
            char closedB = ']';
            char closedC = '}';
            if(character == openA || character == openB || character == openC){
                stack.push(character);
            }
            else if(character == closedA || character == closedB|| character == closedC){
                if(stack.size() == 0){
                    return false;
                }
                char connection = (char) stack.pop();
                if((character == ')' && connection != '(') ||
                (character == ']' && connection != '[') ||
                (character == '}' && connection != '{')){
                    return false;
                }
            }
        }
        if(stack.size() == 0){
            return true;                                                      
        }
        else{
            return false;
        }
    }
}