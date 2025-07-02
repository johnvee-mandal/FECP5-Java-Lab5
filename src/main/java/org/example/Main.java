package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of students: ");
        int numStudents;
        try {
            numStudents = Integer.parseInt(scanner.nextLine());
            if (numStudents <= 0) {
                System.out.println("Error: Number of students must be positive.");
                scanner.close();
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid input. Please enter a whole number.");
            scanner.close();
            return;
        }

        String[] names = new String[numStudents];
        int[] scores = new int[numStudents];
        double totalScore = 0;
        int[] gradeCounts = {0, 0, 0, 0, 0}; // Index: 0=A, 1=B, 2=C, 3=D, 4=F
        int highScore = -1;

        for (int i = 0; i < numStudents; i++) {
            System.out.print("Enter name of student " + (i + 1) + ": ");
            names[i] = scanner.nextLine();

            while (true) {
                System.out.print("Enter score for " + names[i] + ": ");
                try {
                    int score = Integer.parseInt(scanner.nextLine());
                    if (score >= 0 && score <= 100) {
                        scores[i] = score;
                        break; // Exit loop if score is valid
                    } else {
                        System.out.println("Invalid score. Please enter a value between 0 and 100.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a numeric score.");
                }
            }

            char grade;
            if (scores[i] >= 90) {
                grade = 'A';
                gradeCounts[0]++;
            } else if (scores[i] >= 80) {
                grade = 'B';
                gradeCounts[1]++;
            } else if (scores[i] >= 70) {
                grade = 'C';
                gradeCounts[2]++;
            } else if (scores[i] >= 60) {
                grade = 'D';
                gradeCounts[3]++;
            } else {
                grade = 'F';
                gradeCounts[4]++;
            }
            System.out.println(names[i] + " got grade: " + grade);
            System.out.println(); 

            totalScore += scores[i];
            if (scores[i] > highScore) {
                highScore = scores[i];
            }
        }
        

        double averageScore = (numStudents > 0) ? totalScore / numStudents : 0.0;

        List<String> topStudentNames = new ArrayList<>();
        if (highScore != -1) {
            for (int i = 0; i < numStudents; i++) {
                if (scores[i] == highScore) {
                    topStudentNames.add(names[i]);
                }
            }
        }

        System.out.println("----- Class Summary -----");
        System.out.printf("Average Score: %.2f\n", averageScore);
        System.out.printf("Grade Counts: A:%d B:%d C:%d D:%d F:%d\n",
                gradeCounts[0], gradeCounts[1], gradeCounts[2], gradeCounts[3], gradeCounts[4]);
        
        if (!topStudentNames.isEmpty()) {
            String topStudents = String.join(", ", topStudentNames);
            System.out.println("Top Student(s): " + topStudents + " (" + highScore + ")");
        }
    }
}