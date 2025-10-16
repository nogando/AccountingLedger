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

        // color codes for green text on black background
        String GREEN_TEXT = "\u001B[32m"; // 32m = green text , 30m = black text , 31m = red text
        String BLACK_BG = "\u001B[40m"; // 40m = black background , 41m = red background , 42m = green background
        // 30–37 are the standard text color codes, and 40–47 are the matching background codes.
        // \u001b[ = telling the terminal I got Instructions
        // 32 = make the text green
        // m = end the instruction that is all I have

        // Apply colors to the console output
        System.out.print(BLACK_BG + GREEN_TEXT);

        // Scanner reads user input from the keyboard
        Scanner scanner = new Scanner(System.in);

        // Load transactions from the CSV file into memory
        ArrayList<Transactions> transactions = importTransactions();

        // Start the app at the Home menu
        handleHome(scanner);
    }



/// METHOD FOR IMPORTING DATA
    // Reads all transactions from the CSV file and loads them into an ArrayList
    public static ArrayList<Transactions> importTransactions() {
        // This list will hold all the transactions we read
        ArrayList<Transactions> transactions = new ArrayList<>();
        try {
            // Open the file for reading
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            // Read each line until the end of the file
            while ((input = bufReader.readLine()) != null) {

                // Split the line into parts using the "|" character
                String[] split = input.split("\\|");

                // Convert text to the right data types
                LocalDate date = LocalDate.parse(split[0]);
                LocalTime time = LocalTime.parse(split[1]);
                String description = split[2];
                String vendor = split[3];
                double amount = Double.parseDouble(split[4]);

                // Build a Transactions object from this line
                Transactions transaction = new Transactions(amount, date, description, time, vendor);
                // Add it to the list
                transactions.add(transaction);
            }
        } catch (Exception e) {
            // If anything goes wrong, show a simple message and continue
            System.out.println("Error occurred! " + e.getLocalizedMessage());
        }
        // Give the full list back to the caller
        return transactions;
    }






/// MENUS
    // Handles the Reports Menu and its options
    public static void handleReports(Scanner scanner) throws InterruptedException {
        // Controls the loop for this menu
        boolean reportRunning = true;
        String reportMenu = """
                \n========= The Riddler's Report =========
                """;
        System.out.println(reportMenu);

        // Dramatic intro text
        typeWriter("Ah, Batman… always chasing data,\nalways searching for the truth buried in numbers.\nTell me, detective, will your logic save you this time?\n", 20);

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

        // Keep showing the menu until the user goes back
        while (reportRunning) {
            System.out.println(reportMenu2);
            System.out.print("Enter number for Report Menu:  ");
            String input = scanner.nextLine().trim();

            // Try to convert the text choice into a number
            int reportChoice;
            try {
                reportChoice = Integer.parseInt(input);
            } catch (Exception e) {
                // If the input is not a number, tease and continue
                System.out.println();
                System.out.println("Error Occurred! " + e.getLocalizedMessage());
                typeWriter("\nNot so Smart Batman!\n", 60);
                continue;
            }

            // Handle each menu choice
            switch (reportChoice) {
                case 1:
                    // Show current month results
                    typeWriter("\nThe present… but how long will it last\n", 60);
                    System.out.println("\n========= Month To Date =========");
                    monthToDate(importTransactions());
                    break;

                case 2:
                    // Show previous month results
                    typeWriter("\nLooking backward to move forward?\n", 60);
                    System.out.println("\n========= Previous Month =========");
                    previousMonth(importTransactions());
                    break;

                case 3:
                    // Show current year results
                    typeWriter("\nA timeline of triumphs or failures?\n", 60);
                    System.out.println("\n========= Yearly report =========");
                    currentYear(importTransactions());
                    break;

                case 4:
                    // Show previous year results
                    typeWriter("\nGhosts of ledgers past…\n", 60);
                    System.out.println("\n========= Previous Year =========");
                    previousYear(importTransactions());
                    break;

                case 5:
                    // Search by exact vendor name
                    typeWriter("\nSeeking the name behind the mask?\n", 60);
                    System.out.println("\n========= Search By Vendor =========");
                    searchByVendor(importTransactions());
                    break;

                case 6:
                    // Placeholder for a custom search feature you can add later
                    typeWriter("\nBuild your own trap, perhaps?\n", 60);
                    // TODO: Add method for custom searches later
                    break;

                case 0:
                    // Leave the reports menu
                    typeWriter("\nDetective ill see you later!\n", 30);
                    reportRunning = false;
                    break;

                default:
                    // Any other number is invalid here
                    typeWriter("\nNot so smart detective! Try Again!\n", 60);
                    break;
            }
        }
    }

    // Handles the Ledger Menu and its options
    public static void handleLedger(Scanner scanner) throws InterruptedException {
        // Controls the loop for this menu
        boolean ledgerRunning = true;
        String ledgerMenu = """
                \n========= The Riddler's Ledger =========
                """;
        System.out.println(ledgerMenu);

        // Intro line for flavor
        typeWriter("\nWell, well, well... still chasing patterns, are we, Batman?\nLet’s see if your detective’s mind can keep up with my numbers.\n", 30);

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

        // Loop for Ledger menu actions
        while (ledgerRunning) {

            System.out.println(ledgerMenu2);
            String ledgerChoice = scanner.nextLine().trim().toLowerCase();

            switch (ledgerChoice) {
                case "a":
                    // Print all lines as they are in the file
                    typeWriter("Dare to see everything, detective?\n", 60);
                    printTransactions(importTransactions());
                    break;

                case "d":
                    // Show only positive amounts
                    typeWriter("Money in, but what’s the cost?\n", 60);
                    printDeposits(importTransactions());
                    break;

                case "p":
                    // Show only negative amounts
                    typeWriter("Debts, or confessions in disguise?\n", 60);
                    printPayments(importTransactions());
                    break;

                case "r":
                    // Jump to the Reports menu
                    typeWriter("Data or deception? Only you can tell.\n", 60);
                    handleReports(scanner);
                    break;

                case "h":
                    // Leave the Ledger menu
                    typeWriter("Or is it escape? Ha! Tell me!\n", 60);
                    ledgerRunning = false;
                    break;

                default:
                    // Any other letter is invalid here
                    typeWriter("Not so smart detective! Try Again!\n", 60);
                    break;
            }
        }
    }

    // Handles the Home Menu and its navigation
    public static void handleHome(Scanner scanner) throws InterruptedException {
        // Controls the app until the user exits
        boolean homeRunning = true;

        String homeMenu = """
                \n=========== The Riddler's Terminal ===========
                """;
        System.out.println(homeMenu);

        // Opening lines with the typewriter effect
        typeWriter(" Well, well, well... Batman...\nWelcome to my little game of numbers.\n", 60);

        String homeMenu2 = """
                \n========= Your Choices, Detective =========
                        o D) Deposit your precious coins
                        o P) Pay your debts... if you dare
                        o L) Look into the Ledger of Lies
                        o X) Exit
                """;

        // Loop for Home menu actions
        while (homeRunning) {
            System.out.println(homeMenu2);
            String homeChoice = scanner.nextLine().trim().toLowerCase();

            switch (homeChoice) {
                case "d":
                    // Add a new positive transaction
                    typeWriter("\nTell me, what grows smaller the more you spend it?\n", 60);
                    addDeposit();
                    break;

                case "p":
                    // Add a new negative transaction
                    typeWriter("\nEvery action has a cost… but can you afford the truth?\n", 60);
                    makePayment();
                    break;

                case "l":
                    // Go to the Ledger menu
                    handleLedger(scanner);
                    break;

                case "x":
                    // Exit gate with a riddle challenge
                    typeWriter("Giving Up Detective! \nRiddle Me This?\n\n", 25);
                    String theRiddle = """
                                       I have no body, but I can perform all the tasks of one. 
                                       I'm created with logic, not flesh and bone, yet I'm essential for many. 
                                       I can be built from nothing or have many layers. What am I?
                            """;

                    // Keep asking until the riddle is solved
                    boolean riddleRunning = true;

                    while (riddleRunning) {
                        typeWriter(theRiddle, 40);
                        String riddle = scanner.nextLine().trim();

                        if (riddle.equalsIgnoreCase("a program") || riddle.equalsIgnoreCase("program")) {
                            // Correct answer ends the app
                            typeWriter("You've solved my riddle... but beware... Even when you win, I’m still in your system. Goodbye... for now.", 80);
                            homeRunning = false;
                            riddleRunning = false;
                        } else {
                            // Wrong answer repeats
                            System.out.println("Wrong guess. Try again, detective.\n\n");
                        }
                    }
                    break;

                default:
                    // If the user types anything else
                    System.out.println("Invalid Option!");
                    System.out.println(homeMenu);
                    break;
            }
        }
    }








/// Home Methods
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

        String vendor = ConsoleHelper.promptForString("Who is the Deposit from? - Wayne Enterprises Ect");
        System.out.println();

        double doubleAmount = ConsoleHelper.promptForDouble("What is the Amount of the Deposit? - $0000.00");
        String amount = Double.toString(doubleAmount);
        System.out.println();

        try {
            // Append a new line to the CSV file
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            // Start on a new line then write the fields separated by |
            bufWriter.newLine();
            bufWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);

            bufWriter.close();
            System.out.println("Deposit Entered!");
        } catch (Exception e) {
            System.out.println("Error Occurred " + e.getLocalizedMessage());
        }

        // After adding, you return to the menu loop in handleHome
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
            // Append a new line to the CSV file
            FileWriter fileWriter = new FileWriter("transactions.csv", true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            bufWriter.newLine();
            bufWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount);

            bufWriter.close();
            System.out.println("Payment Entered!");
        } catch (Exception e) {
            System.out.println("Error Occurred " + e.getLocalizedMessage());
        }

        // After adding, you return to the menu loop in handleHome
    }







/// Ledger Methods
    // Shows all transactions in the CSV file
    public static void printTransactions(ArrayList<Transactions> transactions) {
        System.out.println("========= All Entries =========\n");
        for (Transactions t : transactions) {
            System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());

        }

    }

    // Prints only deposits (positive amounts)
    public static void printDeposits(ArrayList<Transactions> transactions) {
        for (Transactions t : transactions) {
            if (t.getAmount() > 0) {
                System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }

    // Prints only payments (negative amounts)l
    public static void printPayments(ArrayList<Transactions> transactions) {
        for (Transactions t : transactions) {
            if (t.getAmount() < 0) {
                System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
            }
        }
    }







/// Report Methods
        // Shows all transactions from the current month
        public static void monthToDate (ArrayList < Transactions > transactions) {
            // Get the current month
            LocalDate currentDate = LocalDate.now();
            Month currentMonth = currentDate.getMonth();

            // Print lines where the month matches
            for (Transactions t : transactions) {
                Month listMonth = t.getDate().getMonth();
                if (currentMonth == listMonth) {
                    System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
                }
            }
        }

        // Shows all transactions from the previous month
        public static void previousMonth (ArrayList < Transactions > transactions) {
            // Get the number for the current month
            LocalDate currentDate = LocalDate.now();
            int monthNumber = currentDate.getMonthValue();
            int previousMonth = monthNumber - 1;

            // Print lines where the month value matches previousMonth
            // Note: In January this becomes 0. You can improve this later.
            for (Transactions t : transactions) {
                int listMonth = t.getDate().getMonthValue();
                if (listMonth == previousMonth) {
                    System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
                }
            }
        }

        // Shows all transactions from the current year
        public static void currentYear (ArrayList < Transactions > transactions) {
            // Get the current year
            LocalDate currentDate = LocalDate.now();
            int currentYear = currentDate.getYear();

            // Print lines where the year matches
            for (Transactions t : transactions) {
                int listYear = t.getDate().getYear();
                if (currentYear == listYear) {
                    System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
                }
            }
        }

        // Shows all transactions from the previous year
        public static void previousYear (ArrayList < Transactions > transactions) {
            // Get the last year number by subtracting 1
            LocalDate currentDate = LocalDate.now();
            int yearNumber = currentDate.getYear();
            int previousYear = yearNumber - 1;

            // Print lines where the year equals previousYear
            for (Transactions t : transactions) {
                int listYear = t.getDate().getYear();
                if (listYear == previousYear) {
                    System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
                }
            }
        }

        // Searches for transactions by vendor name
        public static void searchByVendor (ArrayList < Transactions > transactions) {
            // Ask which vendor to search for
            String vendor = ConsoleHelper.promptForString("Enter Vendor to Search");
            for (Transactions t : transactions) {
                String listVendor = t.getVendor();
                // Match ignoring case
                if (listVendor.equalsIgnoreCase(vendor)) {
                    System.out.println(t.getDate() + "|" + t.getTime() + "|" + t.getDescription() + "|" + t.getVendor() + "|" + t.getAmount());
                }
            }
        }



/// TypeWriter Method
        // Prints text one character at a time to create a typing effect
        public static void typeWriter (String text,int delay) throws InterruptedException {
            for (char c : text.toCharArray()) {
                System.out.print(c);
                Thread.sleep(delay);
                // Thread is adding a second thread like do this while main thread or main method is working
                // That's why it throws A Interrupted Exception because its Interrupting something in the main method or thread
                // Sleep is saying hey pause before every character in text.toCharArray()
            }
        }
}

