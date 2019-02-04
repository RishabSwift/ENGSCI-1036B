package rbhatt23.lab01;

import java.util.Scanner;

public class rbhatt23_lab01_q3 {

    public static void main(String[] args) {

        System.out.println("******************************************************");
        System.out.println("ES1036b: Lab 01 Q3");
        System.out.println("Date: January 28, 2019");
        System.out.println("Name: Rishab Bhatt");
        System.out.println("Student Number: 251028696");
        System.out.println(" Program’s mission: This program will break the current year into subcomponents.");
        System.out.println("******************************************************");
        System.out.println();

        System.out.println("Please enter the current year:");

        Scanner scan = new Scanner(System.in);
        String year = scan.next();

        String millenniums, centuries, decades, singleYears;
        millenniums = year.substring(0, 1);
        centuries = year.substring(1, 2);
        decades = year.substring(2, 3);
        singleYears = year.substring(3);

        System.out.printf("The current year is composed of %s millennium(s), %s centuries, %s decade(s), and %s single year(s).", millenniums, centuries, decades, singleYears);
    }
}
