package com.pluralsight;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Create a loop that will keep going for ever as long as i exit it

        boolean running = true;
        while(running == true){
            //create scanner to get input for if else statement to make interactive menu
            Scanner scanner = new Scanner(System.in);

            String choice;
            //create simple Home Menu with if else statement
            homeMenu();
            choice = scanner.nextLine();



            if(choice.trim().equalsIgnoreCase("d")){
                System.out.println("Adding Deposit");
            }
            else if(choice.trim().equalsIgnoreCase("p")){
                System.out.println("Making Payment");
            }
            else if(choice.trim().equalsIgnoreCase("l")){
                System.out.println("Showing Ledger");

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