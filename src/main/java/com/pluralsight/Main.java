package com.pluralsight;


import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    printTransactions();

    handleHome(scanner);

    }

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
                    System.out.println("All Entries");
                    //todo show all entries method
                    break;

                case "d":
                    System.out.println("Deposits");
                    //todo show all deposits method
                    break;

                case "p":
                    System.out.println("Payments");
                    //todo show payments method
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
                    System.out.println("Add Deposit");
                    //todo Make a deposit method
                    break;

                case "p":
                    System.out.println("Make a payment");
                    //todo make a payment method
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
    public static void printTransactions(){
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

             System.out.println(date);
             System.out.println(time);
             System.out.println(description);
             System.out.println(vendor);
             System.out.println(amount);
            }
        }catch (Exception e){
            System.out.println("Error occurred! "+ e.getLocalizedMessage());
        }
    }
}
