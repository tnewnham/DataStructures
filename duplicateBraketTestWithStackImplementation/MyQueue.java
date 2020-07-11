package main;
import java.util.*;

public class MyQueue {
  LinkedList<Integer> data;

  public MyQueue() {
    data = new LinkedList<>();
  }

  public Integer add(int x) {
    data.add(x);
    return x;
  }

  public Integer remove() {
    return data.remove(0);
  }

  public Integer peek() {
    return data.get(0);
  }
}