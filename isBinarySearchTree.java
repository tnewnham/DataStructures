package app;

import java.util.Arrays;
import java.util.Scanner;


class Node {
    int tree;
    Node left; 
    Node right;

    public Node(int item){
        tree = item;
        left = right = null;
    }
}

public class App {

    Node root;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // parse the array representing the binary search tree
        int[] binaryTree;
        String input = sc.nextLine();
        if (input.equals("")) {
            binaryTree = new int[0];
        } else {
            String[] binaryTreeStrings = input.split(" ");
            binaryTree = new int[binaryTreeStrings.length];
            for (int i = 0; i < binaryTreeStrings.length; i++) {
                binaryTree[i] = Integer.parseInt(binaryTreeStrings[i]);
            }
        }
        // check if this is a binary search tree; print the result
        System.out.println(isBinarySearchTree(binaryTree));

    }

    public static boolean isBinarySearchTree(int[] binaryTree) {

        int n = binaryTree.length;
        int left = 0;
        int right = 0;
        for (int i = 0; i < n; i++) {
            isValidTraversal(binaryTree[0], binaryTree[i * 2 + 1], binaryTree[i * 2 + 2], n);
        }
        return true;
    }

    public static boolean isValidTraversal(int root, int left, int right, int n) {
        if (left > n)

            if (left > root)
                return false;
        if (right < root)
            return false;
        return true;
    }

    public boolean isBst() {
        return isBSTUtil(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

    }

    boolean isBSTUtil(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.tree < min || node.tree > max) {
            return false;
        }
        return (isBSTUtil(node.left,min, node.tree-1)&& isBSTUtil(node.right, node.tree+1,max));
    }


    


}

// [10, 5, 15, 2, 7, 11, 25, 1]
// 0 1 2 3 4 5 6 7
// ^ L R L L R R L
// i = 0 \
// root = 10 [0]
// space count = 1
// OgRoot = array[0] = n
// i = 0 = check root
// call first check
// current left node[i+1] = i left space node = [1] < root ((5<10) ok)
// current right [i+2] = i + right space count = [2] > root ((15 > 10) ok)
// left root = left node

// left root = [1]
// right root = [i+2]

// left space count = 1
// rigt space count = 2

// checkLeft(left root, leftNodeLeft, LeftNodeRight, ogRoot )
// call check left (leftroot, (left node + left space count + 1), (right node +
// left space count + 2, og root)
// check root = left node = [1]
// leftNodeLeft + left space count + 1 = 1+1+1 = [3] = 2 (ok)
// left right node to check = left node + left space count + 2 = 1 + 1 + 2 = [4]
// = 7 (ok)
// call check left on left left node & left right node

// call check right(root, left node + rigth space count + 1, right node, + right
// + space count + 2 )

// check root = right node =
// right left node to check = right node + right space count + 1 = 2 + 2 + 1 =
// [5] = 11 (ok)
// right right node to check = right node + right space count + 2 = 2 + 2 + 2 =
// [6] = 25 (ok)

// call check rigt on right left node
// call check right on right right node

// how to end before out of bounds error?
