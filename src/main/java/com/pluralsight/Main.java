package com.pluralsight;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {



        homeMenu();// Placed my home menu Outside of loop so that way it prints once
        //after the loop is running i can always call my home menu method when i need it

        //Create a loop that will keep going for ever as long as i exit it
        boolean running = true;

        while(running == true){
            //create scanner to get input for if else statement to make interactive menu
            Scanner scanner = new Scanner(System.in);

            String choice;
            //create simple Home Menu with if else statement

            choice = scanner.nextLine();



            if(choice.trim().equalsIgnoreCase("d")){
                System.out.println("Adding Deposit");
                //todo prompt user for the deposit information and save it to the csv file
                //todo make the method
            }
            else if(choice.trim().equalsIgnoreCase("p")){
                System.out.println("Making Payment");
                //todo prompt user for the debit information and save it to the csv file
                //todo make the method
            }
            else if(choice.trim().equalsIgnoreCase("l")){
                System.out.println("Showing Ledger");
                //todo display the ledger screen

                ledgerMenu();

                boolean ledgerRunning = true;

                while(ledgerRunning == true){
                    String ledgerChoice= scanner.nextLine();

                    if(ledgerChoice.trim().equalsIgnoreCase("a")){
                        System.out.println("All Entries");
                        //todo make a all entries method
                    }
                    else if(ledgerChoice.trim().equalsIgnoreCase("d")){
                        System.out.println("Making Deposit");
                        //todo make a deposit method
                    }
                    else if(ledgerChoice.trim().equalsIgnoreCase("p")){
                        System.out.println("Making Payment");
                        //todo make a payment method
                    }
                    else if(ledgerChoice.trim().equalsIgnoreCase("r")){
                        System.out.println("Report Menu");

                        boolean reportRunning = true;
                        //create a sub screen that shows
                        reportMenu();
                        while(reportRunning == true){


                            int reportChoice = scanner.nextInt();
                            scanner.nextLine();
                            // 1) Month To Date
                            if(reportChoice == 1){

                                System.out.println("Month to Date Report");
                                reportMenu();
                                //todo create a method that reports month to date
                            }// 2) Previous Month
                            else if(reportChoice == 2){

                                System.out.println("Previous Month Report");
                                reportMenu();
                                //todo create a method that reports previous month report
                            }// 3) Year To Date
                            else if(reportChoice == 3){

                                System.out.println("Year To Date Report");
                                reportMenu();
                                //todo create a method that reports Year to date Report
                            }// 4) Previous Year
                            else if(reportChoice == 4){

                                System.out.println("Previous Year Report");
                                reportMenu();
                                //todo create a method that reports previous year
                            }// 5) Search by Vendor - prompt the user for the vendor name and display all entries for that vendor
                            else if (reportChoice == 5) {
                                System.out.println("Searching by Vendor");
                                reportMenu();
                                //todo create a method that searches by vendor
                            }// 0) Back - go back to the Ledger page
                            else if (reportChoice == 0) {
                                System.out.println("See you later Reports!");
                                reportRunning = false;
                                ledgerMenu();

                            }
                            else {
                                System.out.println("Invalid Entry!");
                                reportMenu();
                            }
                        }










                    }
                    else if(ledgerChoice.trim().equalsIgnoreCase("h")){

                        System.out.println("See You Later!");
                        ledgerRunning = false;
                        homeMenu();

                    }
                    else{
                        System.out.println("Invalid Entry Selected!");

                        ledgerMenu();
                    }


                }




            } else if (choice.trim().equalsIgnoreCase("x")) {
                running = false;
                System.out.println("Goodbye!");

            }
            else{
                System.out.println("Invalid Entry Selected");
            }
        }





    }

    public static void homeMenu(){
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
    public static void ledgerMenu(){
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
    public static void reportMenu(){
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
    }
}
