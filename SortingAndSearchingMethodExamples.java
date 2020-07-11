package sorting_Methods;

import java.util.Arrays;

public class Sorting_Methods {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello Java");
        int[] test = { 1, 3, 2, 5, 4, 7, 9, 8, 6 };
        int[] sortedTest = bubbleSort(test);
        System.out.println(Arrays.toString(sortedTest));
        //towerOfHanoi(4, 'a', 'c', 'b');
        String testString = "cat";
        System.out.println(reverseStringA(testString));
        printSubset("fun");
        System.out.println(binarySearch(test, 7, 0, test.length -1));

        int[] testCase1 = new int[] { 1,4,1,1,4,5 };
        mergeSort(testCase1, 0, testCase1.length - 1);
        printArray(testCase1);
        

    }

    public static void printArray(int[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
          System.out.print(inputArray[i] + " ");
        }
        System.out.println();
      }

    // 0(1) = space; 0(n^2); omega(n^2)
    public static int[] selectionSort(int[] inputArray) {
        for (int i = 0; i < inputArray.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < inputArray.length; j++) {
                if (inputArray[j] < inputArray[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = inputArray[minIndex];
            inputArray[minIndex] = inputArray[i];
            inputArray[i] = temp;
        }
        return inputArray;
    }

    
    
    // 0(1) = space; o(n^2); omega(n)
    public static int[] insertionSort(int[] inputArray){
        for (int i = 1; i < inputArray.length; i++){
            int check = inputArray[i];
            int j = i -1;

            while (j >= 0 && inputArray[j] > check){
                inputArray[j + 1] = inputArray[j];
                    j = j -1;                
            }
            inputArray[j + 1] = check;
        }
        return inputArray;
    }

     // 0(1) = space; o(n^2); omega(n^2) if optimized with boolean check omega(n)
     public static int[] bubbleSort(int[] inputArray) {
        boolean done = false;

        for (int i = 0; i < inputArray.length && !done; i++) {
            done = true;
            for (int j = 0; j < inputArray.length - i - 1; j++) {
                if (inputArray[j] > inputArray[j + 1]) {
                    int temp = inputArray[j];
                    inputArray[j] = inputArray[j + 1];
                    inputArray[j + 1] = temp;
                    done = false;
                }
            }
        }
        return inputArray;

    }
    //omega(nlog(n)), o(nlog(n))
    public static void mergeSort(int[] numbers, int startIndex, int endIndex){
        if ( startIndex >= endIndex){
            return;
        }

        int middleIndex = (startIndex + endIndex) / 2;

        mergeSort(numbers, startIndex, middleIndex);
        mergeSort(numbers, middleIndex + 1, endIndex);

        merge(numbers, startIndex, middleIndex, endIndex);
        
    }

    public static void merge(int[] numbers, int startIndex, int middleIndex, int endIndex){
        int[] firstHalf = new int[middleIndex - startIndex + 1];
        int[] secondHalf = new int[endIndex - middleIndex];

        for (int i = startIndex; i <= middleIndex; i++){
            firstHalf[i-startIndex] = numbers[i];
        }

        for (int i = middleIndex + 1; i <= endIndex; i++){
            secondHalf[i-middleIndex-1] = numbers[i];
        }

        int firstHalfPointer = 0;
        int secondHalfPointer = 0;
        
        for (int i = startIndex; i <= endIndex; i++){
            if (firstHalfPointer >= firstHalf.length){
                numbers[i] = secondHalf[secondHalfPointer];
                secondHalfPointer++;
                continue;
            }
            if (secondHalfPointer >= secondHalf.length){
                numbers[i] = firstHalf[secondHalfPointer];
                firstHalfPointer++;
                continue;
            }
            if (firstHalf[firstHalfPointer] <= secondHalf[secondHalfPointer]){
                numbers[i] = firstHalf[firstHalfPointer];
                firstHalfPointer++;
            }else{
                numbers[i] = secondHalf[secondHalfPointer];
                secondHalfPointer++;
            }
        }
    }

   //public static int[] quickSort(){

    //}

    //o(log(n)) - space when recursive
    private static boolean binarySearch(int[] numbers, int k, int lowerBound, int upperBound){
        if (numbers.length == 0){
            return false;
        }

        if (lowerBound > upperBound){
            return false;
        }

        int guessIndex = (upperBound + lowerBound) / 2;
        if (numbers[guessIndex] > k){
            return binarySearch(numbers, k, lowerBound, guessIndex - 1);
        }else if (numbers[guessIndex] < k){
            return binarySearch(numbers, k, guessIndex + 1, upperBound);
        }else{
            return true;
        }
    }

    public static int sum1toN(int n){
        int sum = 0;
        for (int num = 1; num <= n; num++){
            sum += num;
        }
        return sum;

    }

    public static int factorial(int n){
        if (n == 0){
            return 1;
        }
        return n*factorial(n-1);
    }  
    

    public static String reverseString(String string){
        char charString[] = string.toCharArray();
        String reverse = "";

        for (int i = charString.length - 1; i >= 0; i--){
            reverse += charString[i];
        }
        return reverse;
    }

    public static String reverseStringA(String string){
        if (string.isEmpty()){
            return string;
        }
        return reverseStringA(string.substring(1)) + string.charAt(0);
    }

   
   //wrong
    public static void printSubset(String string){
       int temp = 0;
       String array[] = new String[(string.length() * (string.length()+1))/2];

        for(int i = 0; i < string.length(); i++){
            for (int j = i; j < string.length(); j++){
            array[temp] = string.substring(i, j+1);
            temp++;
            }
        }
        for(int i = 0; i < array.length; i++) {  
            System.out.println(array[i]);  
        }
   }

   public static void quickSort(int[] numbers, int startIndex, int endIndex){
       if (endIndex <= startIndex){
           return;
       }

       int pivot = partition(numbers, startIndex, endIndex);
       quickSort(numbers, startIndex, pivot-1);
       quickSort(numbers, pivot+1, endIndex);

   }
   // bad
   public static int partition(int[] numbers, int low, int high){
       int pivot = numbers[low];

       while (low <= high){
           while (numbers[low] < pivot){
               low++;
           }
           while (numbers[high] > pivot){
               high--;
           }

           if (low <= high){
               int temp = numbers[low];
               numbers[low] = numbers[high];
               numbers[high] = temp;
           }
       }
       return low;
     }

     public static int partitionA(int[] numbers, int low, int high){
         int i = low+1;

         for (int j = low + 1; j <= high; j++){
             if (numbers[j] <= numbers[low]){
                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
                i++;
             }
         }
         int temp = numbers[i - 1];
         numbers[i -1] = numbers[low];
         numbers[low] = temp;
         return i-1;
     }



   
   
   public static void towerOfHanoi(int n, char from, char to, char aux){
        if ( n == 1){
            System.out.println("move disk 1 from rod " + from + " to rod " + to);
            return;
        }
        towerOfHanoi(n-1, from, aux, to);
        System.out.println("move disk " + n + " from rod " + from + " to rod " + to);
        towerOfHanoi(n-1, aux, to, from);
    }
    

}