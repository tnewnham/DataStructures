package reverseLinkedList;

public class MyLinkedList {
    private MyLinkedListNode firstNode = null;
    private MyLinkedListNode lastNode = null;
    private int length = 0;
    
    /**
     * Constructs a MyLinkedList from an integer array.
     */
    public MyLinkedList(int[] numbersArray) {
        // set length
        length = numbersArray.length;
        
        // leave the first and last nodes as null if there
        // are no elements in the array
        if (numbersArray.length == 0) {
            return;
        }
        
        // set the first node to the first number
        firstNode = new MyLinkedListNode(numbersArray[0], null, null);
        if (numbersArray.length == 1) {
            lastNode = firstNode;
            return;
        }
            
        // create all the middle nodes
        MyLinkedListNode prevNode = firstNode;
        for (int i = 1; i < numbersArray.length - 1; i++) {
            MyLinkedListNode currentNode =
                new MyLinkedListNode(numbersArray[i], prevNode, null);
            prevNode.setNext(currentNode);
            prevNode = currentNode;
        }
        
        // set the last node to the last number
        lastNode = new MyLinkedListNode(
            numbersArray[numbersArray.length-1],
            prevNode,
            null);
        prevNode.setNext(lastNode);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(length);
        
        MyLinkedListNode currentNode = firstNode;
        for (int i = 0; i < length; i++) {
            sb.append(currentNode.toString());
            if (i < length - 1) {
                sb.append(" ");
                currentNode = currentNode.getNext();
            }
        }
        
        return sb.toString();
    }
    
    /**
     * Reverses this linked list.
     */
    public void reverseA() {
        // TODO implement this
        MyLinkedListNode previous = null;
        MyLinkedListNode current = firstNode;
        MyLinkedListNode next = null;

        while (current != null){
            next = current.getNext();
            current.setNext(previous);
            current.setPrevious(next);
            
            previous = current;
            current = next;
        }
        firstNode = previous;  
    }

    
}