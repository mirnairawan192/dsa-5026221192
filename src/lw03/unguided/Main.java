package lw03.unguided;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        // Store unique registered students
        Set<String> registeredStudents = new HashSet<>();

        Scanner registrationScanner = new Scanner(
            new File("src/lw03/unguided/registrations.txt")
        );

        while (registrationScanner.hasNextLine()) {
            String studentId = registrationScanner.nextLine();
            registeredStudents.add(studentId);
        }

        registrationScanner.close();

        // Store students who successfully checked in
        Set<String> checkedInStudents = new HashSet<>();

        // Store check-in results in original order
        List<String> results = new ArrayList<>();

        int rejectedAttempts = 0;

        Scanner checkinScanner = new Scanner(
            new File("src/lw03/unguided/checkins.txt")
        );

        while (checkinScanner.hasNextLine()) {

            String studentId = checkinScanner.nextLine();

            if (!registeredStudents.contains(studentId)) {

                results.add(
                    studentId + ": Rejected (not registered)"
                );

                rejectedAttempts++;

            } else if (checkedInStudents.contains(studentId)) {

                results.add(
                    studentId + ": Rejected (already checked in)"
                );
                rejectedAttempts++;
            } else {
                checkedInStudents.add(studentId);

                results.add(
                    studentId + ": Checked in"
                );
            }
        }

        checkinScanner.close();
        // Display check-in results
        System.out.println("===== Event Check-In Results =====");

        for (String result : results) {
            System.out.println(result);
        }
        // Calculate absent students
        int absentStudents =
            registeredStudents.size() - checkedInStudents.size();
        // Display final summary
        System.out.println("===== Final Event Summary =====");
        System.out.println(
            "Registered students: " + registeredStudents.size()
        );
        System.out.println(
            "Successful check-ins: " + checkedInStudents.size()
        );
        System.out.println(
            "Absent students: " + absentStudents
        );
        System.out.println(
            "Rejected attempts: " + rejectedAttempts
        );
    }
}