package main;

import java.util.*;

public class MyStack {
  ArrayList<Integer> data;
 // ArrrayList<Character> charData;

  public MyStack() {
    data = new ArrayList<>();
  }

  /*public MyCharStack(){
    charData = new ArrayList<>();
  }*/

  

  public Integer push(int x) {
    data.add(x);
    return x;
  }

  public Integer pop() {
    return data.remove(data.size()-1);
  }

  public Integer peek() {
    return data.get(data.size()-1);
  }

  /*public char peekChar(){
    return charData.get(charData.size() -1);
  }*/
}