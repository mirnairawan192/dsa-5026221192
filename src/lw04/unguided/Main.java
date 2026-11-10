
package lw04.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    // Bubble Sort Descending
    public static void bubbleSort(int[] arr, int passes) {

        int n = arr.length;

        for (int i = 0; i < passes; i++) {

            for (int j = 0; j < n - 1 - i; j++) {

                if (arr[j] < arr[j + 1]) {

                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                }
            }
        }
    }

    // Display array
    public static void displayArray(int[] arr) {

        for (int i = 0; i < arr.length; i++) {

            System.out.print(arr[i]);

            if (i < arr.length - 1) {
                System.out.print(" ");
            }
        }

        System.out.println();
    }

    // Binary Search Descending
    public static void binarySearch(int[] arr, int target) {

        int low = 0;
        int high = arr.length - 1;
        int steps = 0;

        while (low <= high) {

            int mid = (low + high) / 2;
            steps++;

            if (arr[mid] == target) {

                System.out.println(
                    target + ": FOUND at index " + mid
                    + " (steps: " + steps + ")"
                );

                return;

            } else if (target < arr[mid]) {

                low = mid + 1;

            } else {

                high = mid - 1;

            }
        }

        System.out.println(
            target + ": NOT FOUND (steps: " + steps + ")"
        );
    }

    public static void main(String[] args) {

        try {

            // Read parcels.txt
            Scanner sc = new Scanner(
                new File("src/lw04/unguided/parcels.txt")
            );

            // Read number of parcels
            int n = sc.nextInt();

            int[] parcels = new int[n];

            // Read parcel weights
            for (int i = 0; i < n; i++) {
                parcels[i] = sc.nextInt();
            }

            // Read number of targets
            int m = sc.nextInt();

            int[] targets = new int[m];

            // Read target weights
            for (int i = 0; i < m; i++) {
                targets[i] = sc.nextInt();
            }

            sc.close();

            // Save array after 2 passes
            int[] afterTwoPasses = new int[n];

            // First 2 passes
            bubbleSort(parcels, 2);

            // Copy the array
            for (int i = 0; i < n; i++) {
                afterTwoPasses[i] = parcels[i];
            }

            // Continue sorting until fully sorted
            bubbleSort(parcels, n - 3);

            // Display after 2 passes
            System.out.println("==== After 2 Passes ====");
            displayArray(afterTwoPasses);

            // Display fully sorted array
            System.out.println();
            System.out.println("==== Fully Sorted ====");
            displayArray(parcels);

            // Search results
            System.out.println();
            System.out.println("==== Search Results ====");

            for (int i = 0; i < m; i++) {
                binarySearch(parcels, targets[i]);
            }

        } catch (FileNotFoundException e) {

            System.out.println("Error: parcels.txt not found.");

        }
    }
}
