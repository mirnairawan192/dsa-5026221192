
package lw06;

import java.util.Scanner;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner read = new Scanner(new File("treenumbers.txt"));

            BinarySearchTree bst = new BinarySearchTree();

            String numbers = read.nextLine();
            String[] values = numbers.trim().split("\\s+");

            for (String value : values) {
                bst.insert(Integer.parseInt(value));
            }

            int target = read.nextInt();

            System.out.print("Preorder : ");
            bst.preOrder();

            System.out.print("Inorder : ");
            bst.inOrder();

            System.out.print("Postorder : ");
            bst.postOrder();

            System.out.println("Search " + target + " : " + bst.search(target));
            System.out.println("Nodes : " + bst.countNodes());
            System.out.println("Leaves : " + bst.countLeaves());

            read.close();

        } catch (Exception e) {
            System.out.println("Error: " + e);
            e.printStackTrace();
        }
    }
}
