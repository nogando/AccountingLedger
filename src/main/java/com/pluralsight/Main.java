package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Scanner for user input in menus
        Scanner scanner = new Scanner(System.in);


        // Get transactions from the CSV file and store them in an ArrayList
        ArrayList<Transactions> transactions = importTransactions();

        // Start the Home Menu
        handleHome(scanner);
    }

    // Reads all transactions from the CSV file and loads them into an ArrayList
    public static ArrayList<Transactions> importTransactions() {
        ArrayList<Transactions> transactions = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            // Read each line until the end of the file
            while ((input = bufReader.readLine()) != null) {

                // Split the line into parts using the "|" character
                String[] split = input.split("\\|");

                // Parse each part into its correct data type
                LocalDate date = LocalDate.parse(split[0]);
                LocalTime time = LocalTime.parse(split[1]);
                String description = split[2];
                String vendor = split[3];
                double amount = Double.parseDouble(split[4]);

                // Create a Transaction object with the parsed data
                Transactions transaction = new Transactions(amount, date, description, time, vendor);
                // Add the object to the ArrayList
                transactions.add(transaction);
            }
        } catch (Exception e) {
            System.out.println("Error occurred! " + e.getLocalizedMessage());
        }
        // Return all transactions to the caller
        return transactions;
    }

    // Handles the Reports Menu and its options
    public static void handleReports(Scanner scanner) {
        boolean reportRunning = true;
        String reportMenu = """
                    \n========= Report Menu =========
                        o 1) Month To Date
                        o 2) Previous Month
                        o 3) Year To Date
                        o 4) Previous Year
                        o 5) Search by Vendor
                        o 6) Custom Search
                        o 0) Back - go back to the Ledger page
                    """;


        // Keep looping until the user chooses to go back
        while (reportRunning) {
            System.out.println(reportMenu);
            System.out.print("Enter number for Report Menu:  ");
            String input = scanner.nextLine().trim();

            // Safely convert input to an integer
            int reportChoice;
            try {
                reportChoice = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println("Error Occurred! " + e.getLocalizedMessage());
                System.out.println(reportMenu);
                continue;
            }

            // Handle each menu choice
            switch (reportChoice) {
                case 1:
                    System.out.println("\n========= Month To Date =========");
                    monthToDate(importTransactions());

                    break;

                case 2:
                    System.out.println("\n========= Previous Month =========");
                    previousMonth(importTransactions());

                    break;

                case 3:
                    System.out.println("\n========= Yearly report =========");
                    currentYear(importTransactions());

                    break;

                case 4:
                    System.out.println("\n========= Previous Year =========");
                    previousYear(importTransactions());
                    break;

                case 5:
                    System.out.println("\n========= Search By Vendor =========");
                    searchByVendor(importTransactions());
                    break;

                case 6:
                    System.out.println("Custom Search");
                    // TODO: Add method for custom searches later
                    break;

                case 0:
                    System.out.println("\nSee you Later Reports!");
                    // Exit the Reports menu loop
                    reportRunning = false;
                    break;

                default:
                    System.out.println("Invalid Entry!");
                    System.out.println(reportMenu);
                    break;
            }
        }
    }

    // Handles the Ledger Menu and its options
    public static void handleLedger(Scanner scanner) {
        boolean ledgerRunning = true;

        String ledgerMenu = """
                    \n========= Ledger Menu =========
                        o A) All Entries
                        o D) Deposits
                        o P) Payments
                        o R) Reports
                        o H) Home
                    """;


        // Keep looping until user exits to home
        while (ledgerRunning) {

            System.out.println(ledgerMenu);
            String ledgerChoice = scanner.nextLine().trim().toLowerCase();

            switch (ledgerChoice) {
                case "a":
                    // Show all transactions
                    printTransactions();
                    break;

                case "d":
                    // Show only deposits (positive amounts)
                    printDeposits();
                    break;

                case "p":
                    // Show only payments (negative amounts)
                    printPayments();
                    break;

                case "r":
                    // Go to the Reports menu
                    handleReports(scanner);

                    break;

                case "h":
                    System.out.println("See you Later");
                    // Exit the Ledger menu
                    ledgerRunning = false;
                    break;

                default:
                    System.out.println("Invalid Entry Selected!");

                    break;
            }
        }
    }

    // Handles the Home Menu and its navigation
    public static void handleHome(Scanner scanner) {
        boolean homeRunning = true;

        String homeMenu = """
                    \n=========== Bank App  ===========

                          Welcome To The
                                Future Of Banking...
                    \n========= Home Menu =========
                        o D) Add Deposit
                        o P) Make Payment(Debit)
                        o L) Ledger
                        o X) Exit
                    """;
        System.out.println(homeMenu);

        // Keep looping until user exits the app
        while (homeRunning) {
            String homeChoice = scanner.nextLine().trim().toLowerCase();
            switch (homeChoice) {
                case "d":
                    // Add a deposit transaction
                    addDeposit();
                    break;

                case "p":
                    // Add a payment transaction
                    makePayment();
                    break;

                case "l":
                    // Open the Ledger menu
                    handleLedger(scanner);
                    System.out.println(homeMenu);
                    break;

                case "x":
                    System.out.println("Goodbye!");
                    // Exit the program
                    homeRunning = false;
                    break;

                default:
                    System.out.println("Invalid Option!");
                    System.out.println(homeMenu);
                    break;
            }
        }
    }

    // Adds a new deposit entry to the CSV file
    public static void addDeposit() {
        System.out.println("========= Deposit =========\n");
        Scanner scanner = new Scanner(System.in);

        // Ask the user for date, time, description, vendor, and amount
        LocalDate localDate = ConsoleHelper.promptForDate("What is the Date of the Deposit? - YYYY-MM-DD");
        String date = localDate.toString();
        System.out.println();

        LocalTime localTime = ConsoleHelper.promptForTime("What is the Time of the Deposit? - 00:00:00");
        String time = localTime.toString();
        System.out.println();

        String description = ConsoleHelper.promptForString("What is the Description of the Deposit? - Paycheck Ect");
        System.out.println();

        String vendor = ConsoleHelper.promptForString("Who is the Deposit from? - AutoZone Ect");
        System.out.println();

        double doubleAmount = ConsoleHelper.promptForDouble("What is the Amount of the Deposit? - $0000.00");
        String amount = Double.toString(doubleAmount);
        System.out.println();

        try {
            // Open file in append mode to add a new transaction at the end
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            // Move to a new line before writing new data
            bufWriter.newLine();
            bufWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);

            bufWriter.close();
            System.out.println("Deposit Entered!");
        } catch (Exception e) {
            System.out.println("Error Occurred " + e.getLocalizedMessage());
        }

        // Reprint Home Menu after completion
        String homeMenu = """
                    \n=========== Bank App  ===========

                          Welcome To The
                                Future Of Banking...
                    \n========= Home Menu =========
                        o D) Add Deposit
                        o P) Make Payment(Debit)
                        o L) Ledger
                        o X) Exit
                    """;
        System.out.println(homeMenu);
    }

    // Adds a new payment (debit) entry to the CSV file
    public static void makePayment() {
        System.out.println("========= Payment =========\n");
        Scanner scanner = new Scanner(System.in);

        // Ask the user for date, time, description, vendor, and amount
        LocalDate localDate = ConsoleHelper.promptForDate("What is the Date of the Payment? - YYYY-MM-DD");
        String date = localDate.toString();
        System.out.println();

        LocalTime localTime = ConsoleHelper.promptForTime("What is the Time of the Payment? - 00:00:00");
        String time = localTime.toString();
        System.out.println();

        String description = ConsoleHelper.promptForString("What is the Description of the Payment? - Water Bill Ect");
        System.out.println();

        String vendor = ConsoleHelper.promptForString("Who is the Payment to? - Rent Ect");
        System.out.println();

        double doubleAmount = ConsoleHelper.promptForDouble("What is the Amount of the Payment? - $0000.00");
        // Payments are stored as negative numbers to represent money spent
        String amount = Double.toString(-doubleAmount);
        System.out.println();

        try {
            // Append the payment entry to the CSV file
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            bufWriter.newLine();
            bufWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);

            bufWriter.close();
            System.out.println("Payment Entered!");
        } catch (Exception e) {
            System.out.println("Error Occurred " + e.getLocalizedMessage());
        }

        // Reprint Home Menu after completion
        String homeMenu = """
                    \n=========== Bank App  ===========

                          Welcome To The
                                Future Of Banking...
                    \n========= Home Menu =========
                        o D) Add Deposit
                        o P) Make Payment(Debit)
                        o L) Ledger
                        o X) Exit
                    """;
        System.out.println(homeMenu);
    }

    // Shows all transactions in the CSV file
    public static void printTransactions() {
        System.out.println("========= All Entries =========\n");
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            while ((input = bufReader.readLine()) != null) {
                // Print each line exactly as it appears in the file
                System.out.println(input);
            }
        } catch (Exception e) {
            System.out.println("Error occurred! " + e.getLocalizedMessage());
        }

        // Redisplay the Ledger Menu after printing
        String ledgerMenu = """
                    \n========= Ledger Menu =========
                        o A) All Entries
                        o D) Deposits
                        o P) Payments
                        o R) Reports
                        o H) Home
                    """;
        System.out.println(ledgerMenu);
    }

    // Prints only deposits (positive amounts)
    public static void printDeposits() {
        System.out.println("========= Deposits =========\n");
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            while ((input = bufReader.readLine()) != null) {
                String[] split = input.split("\\|");
                double amount = Double.parseDouble(split[4]);

                // Print only if amount is positive
                if (amount > 0) {
                    System.out.println(input);
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred! " + e.getLocalizedMessage());
        }

    }

    // Prints only payments (negative amounts)
    public static void printPayments() {
        System.out.println("========= Payments =========\n");
        try {
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            while ((input = bufReader.readLine()) != null) {
                String[] split = input.split("\\|");
                double amount = Double.parseDouble(split[4]);

                // Print only if amount is negative
                if (amount < 0) {
                    System.out.println(input);
                }
            }
        } catch (Exception e) {
            System.out.println("Error occurred! " + e.getLocalizedMessage());
        }

    }

    // Shows all transactions from the current month
    public static void monthToDate(ArrayList<Transactions> transactions){
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();

        for(Transactions t : transactions){
            Month listMonth = t.getDate().getMonth();
            if(currentMonth == listMonth){
                System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    // Shows all transactions from the previous month
    public static void previousMonth(ArrayList<Transactions> transactions){
        LocalDate currentDate = LocalDate.now();
        int monthNumber = currentDate.getMonthValue();
        int previousMonth = monthNumber - 1;

        for(Transactions t: transactions){
            int listMonth = t.getDate().getMonthValue();
            if (listMonth == previousMonth ){
                System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    // Shows all transactions from the current year
    public static void currentYear(ArrayList<Transactions> transactions){
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        for(Transactions t : transactions){
            int listYear = t.getDate().getYear();
            if(currentYear == listYear){
                System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    // Shows all transactions from the previous year
    public static void previousYear(ArrayList<Transactions> transactions){
        LocalDate currentDate = LocalDate.now();
        int yearNumber = currentDate.getYear();
        int previousYear = yearNumber - 1;

        for(Transactions t: transactions){
            int listYear = t.getDate().getYear();
            if (listYear == previousYear ){
                System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    // Searches for transactions by vendor name
    public static void searchByVendor(ArrayList<Transactions> transactions){
        String vendor = ConsoleHelper.promptForString("Enter Vendor to Search");
        for(Transactions t : transactions){
            String listVendor = t.getVendor();
            if(listVendor.equalsIgnoreCase(vendor)){
                System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

}

