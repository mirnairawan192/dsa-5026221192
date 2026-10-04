package lw03.prelab;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // =========================
        // PROBLEM 1 - PLAYLIST
        // =========================

        List<String> playlist = new ArrayList<>();

        Scanner playlistScanner = new Scanner(
            new File("src/lw03/prelab/playlist.txt")
        );

        while (playlistScanner.hasNextLine()) {
            String line = playlistScanner.nextLine();

            String[] parts = line.split(" ", 2);
            String operation = parts[0];

            if (operation.equals("ADD")) {
                playlist.add(parts[1]);

            } else if (operation.equals("INSERT")) {
                String[] insertParts = line.split(" ", 3);

                int index = Integer.parseInt(insertParts[1]);
                String song = insertParts[2];

                playlist.add(index, song);

            } else if (operation.equals("REMOVE")) {
                String song = parts[1];

                if (playlist.contains(song)) {
                    playlist.remove(song);
                }
            }
        }

        playlistScanner.close();

        System.out.println("===== Problem 1 =====");
        System.out.println("Total songs: " + playlist.size());

        for (int i = 0; i < playlist.size(); i++) {
            System.out.println((i + 1) + ": " + playlist.get(i));
        }


        // =========================
        // PROBLEM 2 - PARTICIPANTS
        // =========================

        Set<String> participants = new LinkedHashSet<>();
        int duplicateRegistrations = 0;

        Scanner participantScanner = new Scanner(
            new File("src/lw03/prelab/participants.txt")
        );

        while (participantScanner.hasNextLine()) {
            String name = participantScanner.nextLine();

            if (participants.contains(name)) {
                duplicateRegistrations++;
            } else {
                participants.add(name);
            }
        }

        participantScanner.close();

        System.out.println("===== Problem 2 =====");
        System.out.println("Unique participants: " + participants.size());

        int participantNumber = 1;

        for (String name : participants) {
            System.out.println(participantNumber + ". " + name);
            participantNumber++;
        }

        System.out.println(
            "Duplicate registrations: " + duplicateRegistrations
        );


        // =========================
        // PROBLEM 3 - INVENTORY
        // =========================

        Map<String, Integer> inventory = new LinkedHashMap<>();
        int failedSales = 0;

        Scanner inventoryScanner = new Scanner(
            new File("src/lw03/prelab/inventory.txt")
        );

        while (inventoryScanner.hasNextLine()) {
            String line = inventoryScanner.nextLine();

            String[] parts = line.split(" ");

            String type = parts[0];
            String product = parts[1];
            int quantity = Integer.parseInt(parts[2]);

            if (type.equals("ADD")) {

                if (inventory.containsKey(product)) {
                    int currentStock = inventory.get(product);
                    inventory.put(product, currentStock + quantity);
                } else {
                    inventory.put(product, quantity);
                }

            } else if (type.equals("SELL")) {

                if (inventory.containsKey(product)) {

                    int currentStock = inventory.get(product);

                    if (currentStock >= quantity) {
                        inventory.put(product, currentStock - quantity);
                    } else {
                        failedSales++;
                    }

                } else {
                    failedSales++;
                }
            }
        }

        inventoryScanner.close();

        System.out.println("===== Problem 3 =====");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        System.out.println("Failed sales: " + failedSales);
    }
}