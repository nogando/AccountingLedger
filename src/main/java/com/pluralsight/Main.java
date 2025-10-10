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
            }
            else if(choice.trim().equalsIgnoreCase("p")){
                System.out.println("Making Payment");
                //todo prompt user for the debit information and save it to the csv file
            }
            else if(choice.trim().equalsIgnoreCase("l")){
                System.out.println("Showing Ledger");
                //todo display the ledger screen

                //create a sub screen that shows

                //o A) All - Display all entries
                //o D) Deposits - Display only the entries that are deposits into the account
                //o P) Payments - Display only the negative entries (or payments)
                //o R) Reports - A new screen that allows the user to run pre-defined reports or to run a custom search

                    //create a sub screen that shows

                    // 1) Month To Date
                    // 2) Previous Month
                    // 3) Year To Date
                    // 4) Previous Year
                    // 5) Search by Vendor - prompt the user for the vendor name and display all entries for that vendor
                    // 0) Back - go back to the Ledger page

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
                    \n========= Product Menu =========
                        D- Add Deposit
                        P- Make Payment(Debit)
                        L- Ledger
                        X- Exit
                    """;
                System.out.println(homeMenu);
    }
}