package com.pluralsight;


public class Main {
    public static void main(String[] args) {
        //Create a loop that will keep going for ever as long as i exit it

        //Home Menu
        homeMenu();




    }

    public static void homeMenu(){
        String homeMenu = """
                    ========= Product Menu =========
                        D- Add Deposit
                        P- Make Payment(Debit)
                        L- Ledger
                        X- Exit
                    """;
                System.out.println(homeMenu);
    }
}