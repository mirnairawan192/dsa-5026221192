package lw02.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        // 1. Store all orders
        LinkedList<String[]> orders = new LinkedList<>();

        Scanner scanner = new Scanner(new File("src/lw02/unguided/orders.txt"));

        while (scanner.hasNext()) {
            String name = scanner.next();
            String food = scanner.next();
            String drink = scanner.next();
            String table = scanner.next();

            String[] order = {name, food, drink, table};
            orders.add(order);
        }

        scanner.close();

        // 2. Store food stock
        LinkedList<String[]> foods = new LinkedList<>();

        foods.add(new String[]{"Bakso", "2"});
        foods.add(new String[]{"Sate", "1"});
        foods.add(new String[]{"Soto", "2"});

        // 3. Store drink stock
        LinkedList<String[]> drinks = new LinkedList<>();

        drinks.add(new String[]{"EsTeh", "4"});
        drinks.add(new String[]{"EsJeruk", "2"});

        // 4. Store successful orders
        LinkedList<String[]> successfulOrders = new LinkedList<>();

        // 5. Move orders into Queue
        Queue<String[]> queue = new LinkedList<>();

        while (!orders.isEmpty()) {
            queue.add(orders.removeFirst());
        }

        // 6. Stack for failed orders
        Stack<String[]> failedOrders = new Stack<>();

        // 7. Process orders using FIFO
        while (!queue.isEmpty()) {

            String[] order = queue.poll();

            String food = order[1];
            String drink = order[2];

            boolean foodAvailable = true;
            boolean drinkAvailable = true;

            // Check food stock
            if (!food.equals("-")) {
                for (String[] foodRecord : foods) {
                    if (foodRecord[0].equals(food)) {
                        int stock = Integer.parseInt(foodRecord[1]);

                        if (stock <= 0) {
                            foodAvailable = false;
                        }
                    }
                }
            }

            // Check drink stock
            if (!drink.equals("-")) {
                for (String[] drinkRecord : drinks) {
                    if (drinkRecord[0].equals(drink)) {
                        int stock = Integer.parseInt(drinkRecord[1]);

                        if (stock <= 0) {
                            drinkAvailable = false;
                        }
                    }
                }
            }

            // If all required items are available
            if (foodAvailable && drinkAvailable) {

                // Reduce food stock
                if (!food.equals("-")) {
                    for (String[] foodRecord : foods) {
                        if (foodRecord[0].equals(food)) {
                            int stock = Integer.parseInt(foodRecord[1]);
                            foodRecord[1] = String.valueOf(stock - 1);
                        }
                    }
                }

                // Reduce drink stock
                if (!drink.equals("-")) {
                    for (String[] drinkRecord : drinks) {
                        if (drinkRecord[0].equals(drink)) {
                            int stock = Integer.parseInt(drinkRecord[1]);
                            drinkRecord[1] = String.valueOf(stock - 1);
                        }
                    }
                }

                successfulOrders.add(order);

            } else {
                // Failed order goes to Stack
                failedOrders.push(order);
            }
        }

        // 8. Display successful orders
        System.out.println("=== Successfully Processed Orders ===");

        for (String[] order : successfulOrders) {
            System.out.println(
                order[0] + " " +
                order[1] + " " +
                order[2] + " " +
                order[3]
            );
        }

        // 9. Display remaining food stock
        System.out.println("=== Remaining Food Stock ===");

        for (String[] foodRecord : foods) {
            System.out.println(foodRecord[0] + " : " + foodRecord[1]);
        }

        // 10. Display remaining drink stock
        System.out.println("=== Remaining Drink Stock ===");

        for (String[] drinkRecord : drinks) {
            System.out.println(drinkRecord[0] + " : " + drinkRecord[1]);
        }

        // 11. Display failed orders in LIFO order
        System.out.println("=== Failed Orders ===");

        while (!failedOrders.isEmpty()) {
            String[] order = failedOrders.pop();

            System.out.println(
                order[0] + " " +
                order[1] + " " +
                order[2] + " " +
                order[3]
            );
        }
    }
}