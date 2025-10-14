package com.pluralsight;


import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    //Scanner for my Home Menu
    Scanner scanner = new Scanner(System.in);

    //Get my transactions from the csv file
    ArrayList<Transactions> transactions = importTransactions();

    handleHome(scanner);

    }



    //Import my objects or  Transactions in a ArrayList
    public static ArrayList<Transactions> importTransactions(){
        ArrayList<Transactions>transactions = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            while ((input = bufReader.readLine()) != null){

                String[] split = input.split("\\|");

                LocalDate date = LocalDate.parse(split[0]);
                LocalTime time = LocalTime.parse(split[1]);
                String description = split[2];
                String vendor = split[3];
                double amount = Double.parseDouble(split[4]);

                Transactions transaction = new Transactions(amount,date,description,time,vendor);
                transactions.add(transaction);

            }
        }catch (Exception e){
            System.out.println("Error occurred! "+ e.getLocalizedMessage());
        }
        return transactions;
    }


    //my Three Menus for Home, ledger, and Reports Menu
    public static void handleReports(Scanner scanner){
        boolean reportRunning = true;
        String reportMenu = """
                    \n========= Report Menu =========
                        o 1) Month To Date
                        o 2) Previous Month
                        o 3) Year To Date
                        o 4) Previous Year
                        o 5) Search by Vendor - prompt the user for the vendor name and display all entries for that vendor
                        o 0) Back - go back to the Ledger page
                    """;
        System.out.println(reportMenu);


        while(reportRunning){
            System.out.print("Enter number for Report Menu:  ");
            String raw = scanner.nextLine().trim();

            //parse safely
            int reportChoice;
            try{
                reportChoice = Integer.parseInt(raw);
            }catch (Exception e){
                System.out.println("Error Occurred! "+ e.getLocalizedMessage());
                System.out.println(reportMenu);
                continue;

            }
            switch(reportChoice){
                case 1:
                    System.out.println("Month to Date Report");
                    //todo month to date method
                    System.out.println(reportMenu);
                    break;

                case 2:
                    System.out.println("Previous Month Report");
                    //todo previous month method
                    System.out.println(reportMenu);
                    break;

                case 3:
                    System.out.println("Year to Date Report");
                    //todo year to date method
                    System.out.println(reportMenu);
                    break;

                case 4:
                    System.out.println("Previous year Report");
                    // todo previous year method
                    break;

                case 5:
                    System.out.println("Search by Vendor");
                    String vendor = scanner.nextLine().trim();
                    //todo search by vendor method
                    break;

                case 6:
                    System.out.println("Custom Search");
                    //todo custom search method
                    break;

                case 0:
                    System.out.println("\nSee you Later Reports!");
                    reportRunning = false; // returns to ledger screen exits this block of code
                    break;

                default:
                    System.out.println("Invalid Entry!");
                    System.out.println(reportMenu);
                    break;



            }
        }





    }
    public static void handleLedger(Scanner scanner){
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

        while(ledgerRunning){
            String ledgerChoice = scanner.nextLine().trim().toLowerCase();


            switch (ledgerChoice){
                case "a":
                    printTransactions();
                    break;

                case "d":
                    printDeposits();
                    break;

                case "p":
                    printPayments();
                    break;

                case "r":
                    handleReports(scanner);
                    System.out.println(ledgerMenu);
                    break;

                case "h":
                    System.out.println("See you Later");
                    ledgerRunning = false;
                    break;

                default:
                    System.out.println("Invalid Entry Selected!");
                    System.out.println(ledgerMenu);
                    break;
            }
        }
    }
    public static void handleHome(Scanner scanner){
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

        while(homeRunning){
            String homeChoice = scanner.nextLine().trim().toLowerCase();
            switch (homeChoice){
                case "d":
                    addDeposit();
                    break;

                case "p":

                    makePayment();
                    break;
                case "l":
                    handleLedger(scanner);
                    System.out.println(homeMenu);
                    break;

                case "x":
                    System.out.println("Goodbye!");
                    homeRunning = false;
                    break;

                default:
                    System.out.println("Invalid Option!");
                    System.out.println(homeMenu);
                    break;


            }
        }
    }



    //Methods for Home menu
    public static void addDeposit(){
        System.out.println("========= Deposit =========\n");
        Scanner scanner = new Scanner(System.in);

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



        double doubleAmount =  ConsoleHelper.promptForDouble("What is the Amount of the Deposit? - $0000.00");
        String amount = Double.toString(doubleAmount);
        System.out.println();




        try {
            //create a file writer
            //FileWriter fileWriter = new FileWriter("transactions.csv");
            FileWriter fileWriter = new FileWriter("transactions.csv",true);
            //create buffered file writer
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            // make sure it writes to the next line
            bufWriter.newLine();
            bufWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount );


            bufWriter.close();

            System.out.println("Deposit Entered!");

        }catch (Exception e){
            System.out.println("Error Occurred "+ e.getLocalizedMessage());
        }


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
    public static void makePayment(){
        System.out.println("========= Payment =========\n");
        Scanner scanner = new Scanner(System.in);

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



        double doubleAmount =  ConsoleHelper.promptForDouble("What is the Amount of the Payment? - $0000.00");
        String amount = Double.toString(-doubleAmount);
        System.out.println();




        try {
            //create a file writer
            //FileWriter fileWriter = new FileWriter("transactions.csv");
            FileWriter fileWriter = new FileWriter("transactions.csv",true);
            //create buffered file writer
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);
            // make sure it writes to the next line
            bufWriter.newLine();
            bufWriter.write(date + "|" + time + "|" + description + "|" + vendor + "|" + amount );


            bufWriter.close();

            System.out.println("Payment Entered!");

        }catch (Exception e){
            System.out.println("Error Occurred "+ e.getLocalizedMessage());
        }


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

    //Methods for my Ledger menu
    public static void printTransactions(){

        System.out.println("========= All Entries =========\n");
        try{
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;

            while ((input = bufReader.readLine()) != null){

                System.out.println(input);


            }
        }catch (Exception e){
            System.out.println("Error occurred! "+ e.getLocalizedMessage());
        }
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
    public static void printDeposits(){
        System.out.println("========= Deposits =========\n");
        try{
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            while ((input = bufReader.readLine()) != null){

                String[] split = input.split("\\|");

                double amount = Double.parseDouble(split[4]);

                if(amount > 0){
                    System.out.println(input);
                }



            }
        }catch (Exception e){
            System.out.println("Error occurred! "+ e.getLocalizedMessage());
        }
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
    public static void printPayments(){
        System.out.println("========= Payments =========\n");
        try{
            FileReader fileReader = new FileReader("transactions.csv");
            BufferedReader bufReader = new BufferedReader(fileReader);

            String input;
            while ((input = bufReader.readLine()) != null){

                String[] split = input.split("\\|");

                double amount = Double.parseDouble(split[4]);

                if(amount < 0){
                    System.out.println(input);
                }



            }
        }catch (Exception e){
            System.out.println("Error occurred! "+ e.getLocalizedMessage());
        }
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

    //methods for my Report menu

}
