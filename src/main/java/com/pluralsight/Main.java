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
    public static void main(String[] args) throws InterruptedException {

        String GREEN_TEXT = "\u001B[32m";
        String BLACK_BG = "\u001B[40m";

        System.out.print(BLACK_BG + GREEN_TEXT);


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
    public static void handleReports(Scanner scanner) throws InterruptedException {
        boolean reportRunning = true;
        String reportMenu = """
        \n========= The Riddler's Report =========
        """;
        System.out.println(reportMenu);
        typeWriter("Ah, Batman… always chasing data,\nalways searching for the truth buried in numbers.\nTell me, detective, will your logic save you this time?\n",20);

        String reportMenu2 = """
              
              o 1) Month To Date — The present stands still, 
                   yet every second dies. What am I?
                   
              o 2) Previous Month — A shadow of days gone by… 
                   can you learn from ghosts, detective?
                   
              o 3) Year To Date — A line from start to now but tell me, 
                   when does “now” end?
                   
              o 4) Previous Year — The past never sleeps. 
                   Which memory keeps you awake, Batman?
                   
              o 5) Search by Vendor — Faces behind numbers, masks behind deals. 
                   Who profits from your ignorance?
                   
              o 6) Custom Search — Build your own path, but beware 
                   the maze remembers every turn.
                   
              o 0) Back — Turn away, retreat to safety… or so you think.

        """;



        // Keep looping until the user chooses to go back
        while (reportRunning) {
            System.out.println(reportMenu2);
            System.out.print("Enter number for Report Menu:  ");
            String input = scanner.nextLine().trim();

            // Safely convert input to an integer
            int reportChoice;
            try {
                reportChoice = Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println();
                System.out.println("Error Occurred! " + e.getLocalizedMessage());
                typeWriter("\nNot so Smart Batman!\n",60);
                continue;
            }

            // Handle each menu choice
            switch (reportChoice) {
                case 1:
                    typeWriter("\nThe present… but how long will it last\n",60);
                    System.out.println("\n========= Month To Date =========");
                    monthToDate(importTransactions());

                    break;

                case 2:
                    typeWriter("\nLooking backward to move forward?\n",60);
                    System.out.println("\n========= Previous Month =========");
                    previousMonth(importTransactions());

                    break;

                case 3:
                    typeWriter("\nA timeline of triumphs or failures?\n",60);
                    System.out.println("\n========= Yearly report =========");
                    currentYear(importTransactions());

                    break;

                case 4:
                    typeWriter("\nGhosts of ledgers past…\n",60);
                    System.out.println("\n========= Previous Year =========");
                    previousYear(importTransactions());
                    break;

                case 5:
                    typeWriter("\nSeeking the name behind the mask?\n",60);
                    System.out.println("\n========= Search By Vendor =========");
                    searchByVendor(importTransactions());
                    break;

                case 6:
                    typeWriter("\nBuild your own trap, perhaps?\n",60);
                    // TODO: Add method for custom searches later
                    break;

                case 0:
                    typeWriter("\nDetective ill see you later!\n",30);
                    // Exit the Reports menu loop
                    reportRunning = false;
                    break;

                default:
                    typeWriter("\nNot so smart detective! Try Again!\n",60);

                    break;
            }
        }
    }

    // Handles the Ledger Menu and its options
    public static void handleLedger(Scanner scanner) throws InterruptedException {
        boolean ledgerRunning = true;
        String ledgerMenu = """
        \n========= The Riddler's Ledger =========
        """;
        System.out.println(ledgerMenu);

        typeWriter("\nWell, well, well... still chasing patterns, are we, Batman?\nLet’s see if your detective’s mind can keep up with my numbers.\n",30);

        String ledgerMenu2 = """
        
             o A) All Entries — Everything and nothing, seen at once. 
                             What holds every secret, yet tells no lies?
                             
             o D) Deposits — The more you take, the heavier I grow. 
                             What am I, Batman?
                             
             o P) Payments — A price for every choice… tell me,
                             what vanishes the moment it’s shared?
                             
             o R) Reports — Cold facts, warm lies. 
                             Which will you believe, Dark Knight?
                             
             o H) Home — The place you flee to, but can never escape from.
                             Where do even heroes hide?

        """;



        // Keep looping until user exits to home
        while (ledgerRunning) {

            System.out.println(ledgerMenu2);
            String ledgerChoice = scanner.nextLine().trim().toLowerCase();

            switch (ledgerChoice) {
                case "a":
                    typeWriter("Dare to see everything, detective?\n",60);
                    // Show all transactions
                    printTransactions();
                    break;

                case "d":
                    typeWriter("Money in, but what’s the cost?\n",60);
                    // Show only deposits (positive amounts)
                    printDeposits();
                    break;

                case "p":
                    typeWriter("Debts, or confessions in disguise?\n",60);
                    // Show only payments (negative amounts)
                    printPayments();
                    break;

                case "r":
                    typeWriter("Data or deception? Only you can tell.\n",60);
                    // Go to the Reports menu
                    handleReports(scanner);

                    break;

                case "h":
                    typeWriter("Or is it escape? Ha! Tell me!\n",60);
                    // Exit the Ledger menu
                    ledgerRunning = false;
                    break;

                default:
                    typeWriter("Not so smart detective! Try Again!\n",60);

                    break;
            }
        }
    }

    // Handles the Home Menu and its navigation
    public static void handleHome(Scanner scanner) throws InterruptedException {
        boolean homeRunning = true;

        String homeMenu = """
                \n=========== The Riddler's Terminal ===========
                """;
        System.out.println(homeMenu);
        typeWriter(" Well, well, well... Batman...\nWelcome to my little game of numbers.\n",60);
        String homeMenu2 = """
                \n========= Your Choices, Detective =========
                        o D) Deposit your precious coins
                        o P) Pay your debts... if you dare
                        o L) Look into the Ledger of Lies
                        o X) Exit
                """;
        // Keep looping until user exits the app
        while (homeRunning) {
            System.out.println(homeMenu2);
            String homeChoice = scanner.nextLine().trim().toLowerCase();
            switch (homeChoice) {
                case "d":
                    typeWriter("\nTell me, what grows smaller the more you spend it?\n",60);
                    // Add a deposit transaction
                    addDeposit();
                    break;

                case "p":
                    typeWriter("\nEvery action has a cost… but can you afford the truth?\n",60);
                    // Add a payment transaction
                    makePayment();
                    break;

                case "l":
                    // Open the Ledger menu
                    handleLedger(scanner);

                    break;

                case "x":
                    typeWriter("Giving Up Detective! \nRiddle Me This?\n\n",25);
                    String theRiddle = """
                                       I have no body, but I can perform all the tasks of one. 
                                       I'm created with logic, not flesh and bone, yet I'm essential for many. 
                                       I can be built from nothing or have many layers. What am I?
                            """;

                    // Exit the program
                    boolean riddleRunning = true;

                    while (riddleRunning) {
                        typeWriter(theRiddle,40);
                        String riddle = scanner.nextLine().trim();

                        if (riddle.equalsIgnoreCase("a program") || riddle.equalsIgnoreCase("program")) {
                            typeWriter("You've solved my riddle... but beware... Even when you win, I’m still in your system. Goodbye... for now.",80);
                            homeRunning = false;
                            riddleRunning = false;
                        } else {
                            System.out.println("Wrong guess. Try again, detective.\n\n");

                        }

                    }

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
        System.out.println("\n========= Deposit =========");
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

    }

    // Adds a new payment (debit) entry to the CSV file
    public static void makePayment() {
        System.out.println("\n========= Payment =========");
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
    public static void monthToDate(ArrayList<Transactions> transactions) {
        LocalDate currentDate = LocalDate.now();
        Month currentMonth = currentDate.getMonth();

        for (Transactions t : transactions) {
            Month listMonth = t.getDate().getMonth();
            if (currentMonth == listMonth) {
                System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    // Shows all transactions from the previous month
    public static void previousMonth(ArrayList<Transactions> transactions) {
        LocalDate currentDate = LocalDate.now();
        int monthNumber = currentDate.getMonthValue();
        int previousMonth = monthNumber - 1;

        for (Transactions t : transactions) {
            int listMonth = t.getDate().getMonthValue();
            if (listMonth == previousMonth) {
                System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    // Shows all transactions from the current year
    public static void currentYear(ArrayList<Transactions> transactions) {
        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();

        for (Transactions t : transactions) {
            int listYear = t.getDate().getYear();
            if (currentYear == listYear) {
                System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    // Shows all transactions from the previous year
    public static void previousYear(ArrayList<Transactions> transactions) {
        LocalDate currentDate = LocalDate.now();
        int yearNumber = currentDate.getYear();
        int previousYear = yearNumber - 1;

        for (Transactions t : transactions) {
            int listYear = t.getDate().getYear();
            if (listYear == previousYear) {
                System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    // Searches for transactions by vendor name
    public static void searchByVendor(ArrayList<Transactions> transactions) {
        String vendor = ConsoleHelper.promptForString("Enter Vendor to Search");
        for (Transactions t : transactions) {
            String listVendor = t.getVendor();
            if (listVendor.equalsIgnoreCase(vendor)) {
                System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    public static void typeWriter(String text, int delay) throws InterruptedException {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            Thread.sleep(delay);
        }
    }
}

