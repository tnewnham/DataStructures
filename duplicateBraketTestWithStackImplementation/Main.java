package main;

import java.util.*;
class main {
  public static void main(String[] args) {
     //Sample.testSample();
    testMyQueue();
    // testMyStack();
    testBonusQuestions();
  }

  public static void testMyQueue() {
    MyQueue q = new MyQueue();
    q.add(10);
    q.add(20);
    q.add(30);
    System.out.println("Peek: " + q.peek());
    System.out.println("Remove: " + q.remove());
    System.out.println("Remove: " + q.remove());
  }

  public static void testMyStack() {
    MyStack s = new MyStack();
    s.push(10);
    s.push(20);
    s.push(30);
    System.out.println("Peek: " + s.peek());
    System.out.println("Pop: " + s.pop());
    System.out.println("Pop: " + s.pop());
  }

  public static void testBonusQuestions() {
    System.out.println("Testing duplicate brackets:");
    System.out.println("Should be true: " + duplicateBrackets("((x)) + y"));
    System.out.println("Should be false: " + duplicateBrackets("((x + y) + z)"));
    System.out.println("Should be false: " + duplicateBrackets("(x) + (y)"));

    System.out.println("Testing balanced paren:");
    System.out.println("Should be true: " + testBalancedParen("(())()"));
    System.out.println("Should be false: " + testBalancedParen(")()("));
    System.out.println("Should be true: " + testBalancedParen("()"));
  }

  // Given a balanced expression, return a boolean if there are duplicate brackets. A set of parenthesis are duplicate if the same subexpression is surrounded by multiple parentheses.
  // Examples:
  // (x+y) + z ==> false
  // ((x+y)) + z ==> true 
  // ((x+y) + z) ==> false
  public static boolean duplicateBrackets(String exp) {
    // TODO
    Stack<Character> stack = new Stack<>();

    char[] string = exp.toCharArray();
    for (char ch : string){
      if (ch == ')'){
        char top = stack.peek();
        stack.pop();
        int elementsInside = 0;
        while (top != '('){
          elementsInside++;
          top = stack.peek();
          stack.pop();
        }
        if (elementsInside < 1){
          return true;
        }
      }
      else{
        stack.push(ch);
      }
    }
    return false;

  }

  // Given a string of only ( and ), return if it's balanced. Your function must use a stack!
  public static boolean testBalancedParen(String exp) {
    char[] str = exp.toCharArray();
   
    Stack<Character> stack = new Stack<>();
    
    for (int i = 0; i < str.length; i++){
      char current = str[i];
      if (current == '(' ){
        stack.push(current);
      }else if (current == ')'){
        if(stack.isEmpty() || stack.pop() != '('){
          return false;
        }
      }      
    }
    return true;
  }
}  
