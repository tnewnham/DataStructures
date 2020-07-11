package reverseLinkedList;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
        Scanner sc = new Scanner(System.in);
        
        // Read in the list of numbers
        int[] numbers;
        String input = sc.nextLine();
        if (input.equals("")) {
            numbers = new int[0];
        } else {    
            String[] numberStrings = input.split(" ");
            numbers = new int[numberStrings.length];
            for (int i = 0; i < numberStrings.length; i++) {
                numbers[i] = Integer.parseInt(numberStrings[i]);
            }
        }
        
        // Create a MyLinkedList containing these numbers
        MyLinkedList numbersList = new MyLinkedList(numbers);
        
        // Reverse the list
        numbersList.reverseA();
        
        // Print the reversed list
        System.out.println(numbersList.toString());
    }
}