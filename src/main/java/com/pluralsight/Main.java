package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
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
                        o 5) Search by Vendor - prompt the user for the vendor name and display all entries for that vendor
                        o 6) Custom Search
                        o 0) Back - go back to the Ledger page
                    """;
        System.out.println(reportMenu);

        // Keep looping until the user chooses to go back
        while (reportRunning) {
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
                    System.out.println("Month to Date Report");
                    // TODO: Add code later to generate month-to-date report
                    System.out.println(reportMenu);
                    break;

                case 2:
                    System.out.println("Previous Month Report");
                    // TODO: Add code later to generate previous-month report
                    System.out.println(reportMenu);
                    break;

                case 3:
                    System.out.println("Year to Date Report");
                    // TODO: Add code later to generate year-to-date report
                    System.out.println(reportMenu);
                    break;

                case 4:
                    System.out.println("Previous year Report");
                    // TODO: Add code later to generate previous-year report
                    break;

                case 5:
                    System.out.println("Search by Vendor");
                    String vendor = scanner.nextLine().trim();
                    // TODO: Add method to search by vendor name
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
        System.out.println(ledgerMenu);

        // Keep looping until user exits to home
        while (ledgerRunning) {
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
                    System.out.println(ledgerMenu);
                    break;

                case "h":
                    System.out.println("See you Later");
                    // Exit the Ledger menu
                    ledgerRunning = false;
                    break;

                default:
                    System.out.println("Invalid Entry Selected!");
                    System.out.println(ledgerMenu);
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

        // Redisplay the Ledger Menu
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

        // Redisplay the Ledger Menu
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

}

