package com.pluralsight;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class ConsoleHelper {
    public static LocalDate promptForDate(String prompt){

        Scanner scanner = new Scanner(System.in);
        LocalDate input = null;
        boolean isInvalid = true;

        do {
            try{
                System.out.println(prompt + ": ");
                input = LocalDate.parse(scanner.nextLine());

                isInvalid = false;
            }
            catch (Exception ex){
                System.out.println("Invalid entry");
            }

        }
        while (isInvalid);

        return input;
    }
    public static LocalTime promptForTime(String prompt){

        Scanner scanner = new Scanner(System.in);
        LocalTime input = null;
        boolean isInvalid = true;

        do {
            try{
                System.out.println(prompt + ": ");
                input = LocalTime.parse(scanner.nextLine());

                isInvalid = false;
            }
            catch (Exception ex){

                System.out.println("Invalid entry");
            }

        }
        while (isInvalid);

        return input;
    }
    public static String promptForString(String prompt){

        Scanner scanner = new Scanner(System.in);
        String input = null;
        boolean isInvalid = true;

        do {
            try{
                System.out.println(prompt + ": ");
                input = scanner.nextLine();

                isInvalid = false;
            }
            catch (Exception ex){

                System.out.println("Invalid entry");
            }

        }
        while (isInvalid);

        return input;
    }
    public static double promptForDouble(String prompt){

        Scanner scanner = new Scanner(System.in);
        double input = 0;
        boolean isInvalid = true;

        do {
            try{
                System.out.println(prompt + ": ");
                input = Double.parseDouble(scanner.nextLine());

                isInvalid = false;
            }
            catch (Exception ex){
                System.out.println("Invalid entry");
            }

        }
        while (isInvalid);

        return input;
    }


}


