🏦 Accounting Ledger App 

This is my first Java project where I built a bank ledger app that runs in the terminal. It lets you add deposits, make payments, view all transactions, and explore report options like month-to-date or by vendor.

💡 What It Does

Lets you add new deposits and payments

Saves everything inside a CSV file (transactions.csv)

Uses menus to move around the app

Lets you see your Ledger and Reports easily

🧩 How It Works (Simple)

The Home Menu shows options like Deposit, Payment, Ledger, or Exit.
<img width="1797" height="868" alt="Menu" src="https://github.com/user-attachments/assets/89b43bcd-200e-430e-8f89-272a81865283" />


When you open the Ledger Menu, you can pick All, Deposits, Payments, or Reports.
<img width="1042" height="936" alt="ledgerMenuLoop" src="https://github.com/user-attachments/assets/d9f214dc-1f75-4da3-aebc-9dd539e8d002" />
I had some strugle with this and I changed it to a method with while loop and switch block.


The Reports Menu lets you pick Month-to-Date, Previous Month, or search by vendor.
<img width="1376" height="866" alt="reportsMenuFinished" src="https://github.com/user-attachments/assets/45e4bc31-0515-4275-a871-1f9cb06411d1" />
Likewise with the Ledger Menu, I had some strugle with this and I changed it to a method with while loop and switch block.

The ConsoleHelper file helps the app safely ask for a date, time, text, or number.
<img width="1372" height="944" alt="ConsoleHelper" src="https://github.com/user-attachments/assets/b0776244-90cf-4ce6-a559-609e17aae420" />


Everything runs together in your terminal!


🗂️ Files in the Project

Main.java — has all the menus and logic

ConsoleHelper.java — helps read user input

Transactions.java — defines each transaction

transactions.csv — stores all your saved data

⚙️ How to Run

Open the folder in IntelliJ or VS Code.

Run Main.java.

Use the menu options like D for deposit or L for ledger.

✨ What I Learned

How to use Dates and input

How to save and read files using BufferedWriter and BufferedReader

How to organize code into methods and menus
