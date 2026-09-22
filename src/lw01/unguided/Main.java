package lw01.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(new File("src/lw01/unguided/washes.txt"));

        int totalRecords = scanner.nextInt();

        WashService[] services = new WashService[totalRecords];
        int[] units = new int[totalRecords];

        for (int i = 0; i < totalRecords; i++) {
            String type = scanner.next();
            String id = scanner.next();
            int days = scanner.nextInt();
            units[i] = scanner.nextInt();

            if (type.equals("MOTORCYCLE")) {
                services[i] = new MotorcycleWash(id, days);
            } else if (type.equals("CAR")) {
                services[i] = new CarWash(id, days);
            }
        }

        scanner.close();

        for (int i = 0; i < services.length; i++) {
            System.out.println(
                services[i].getId() + " | "
                + services[i].label() + " | "
                + services[i].calculateCharge(units[i])
            );
        }
    }
}