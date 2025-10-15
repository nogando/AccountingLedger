?? The Riddler’s Ledger ??

Welcome, Detective.
This isn’t just another ledger — it’s The Riddler’s Terminal, where numbers hide secrets and every transaction tells a story. Built in Java, this app keeps track of deposits, payments, and reports... but beware, Batman — even data can deceive.
First Phase of Project 
<img width="1797" height="868" alt="Menu" src="https://github.com/user-attachments/assets/f0bb0259-d07f-4742-9656-cdfc7bc06a92" />
<img width="1376" height="866" alt="reportsMenuFinished" src="https://github.com/user-attachments/assets/b9a5b11e-e415-4095-878c-45604feb2ffe" />
<img width="1042" height="936" alt="ledgerMenuLoop" src="https://github.com/user-attachments/assets/e3e71893-5760-4672-b3c6-99297aae2c79" />

Second Phase of Project
<img width="607" height="222" alt="import green text and black background" src="https://github.com/user-attachments/assets/a9214562-d1a3-4323-a6fd-174e462b41a7" />
  The Recources I used:<img width="1919" height="1040" alt="resource for colored background and text" src="https://github.com/user-attachments/assets/a7aa5f14-b051-4ef4-ada2-a69bcebeab40" />
                       <img width="1915" height="1036" alt="Type writer effect " src="https://github.com/user-attachments/assets/96f262d4-5e37-47e4-b572-877db891ae2a" />



Final Phase of Project
<img width="419" height="265" alt="updated home menu" src="https://github.com/user-attachments/assets/0b73f41c-fc30-4a7d-b867-3279a36932d6" />

💡 What It Does

Add new deposits — your precious coins, perhaps?

Record payments — debts you can’t escape.

View every transaction inside the ledger.

Explore reports by date or by vendor — clues hidden in the timeline.

Everything is saved inside transactions.csv so nothing escapes my gaze.

🧩 How It Works

The Home Menu is your base of operations:
choose Deposit, Payment, Ledger, or Exit — if you dare.

The Ledger Menu lets you peek behind the mask:
choose All Entries, Deposits, Payments, or Reports — each holds a riddle of its own.

The Reports Menu dives deeper:
Month To Date, Previous Month, Year To Date, Previous Year, or Search By Vendor —
The truth is there… if you can read between the numbers.

Every message appears with a typewriter effect — slow, deliberate, like a taunt in the dark.

🗂️ Files in the Project

Main.java — The mastermind’s code. Controls menus, reports, and typing effects.

ConsoleHelper.java — Assists in questioning... I mean, prompting the user for input.

Transactions.java — Defines a single record (date, time, description, vendor, amount).

transactions.csv — Where your secrets are written. Every move you make, Batman.

⚙️ How To Run

Open the project in IntelliJ or VS Code.

Run Main.java in the terminal.

Choose your path:

D — Deposit

P — Payment

L — Ledger

X — Exit (and face my final riddle)

✨ What I Learned

How to use LocalDate and LocalTime for tracking events in time.

How to read and write CSV files with BufferedReader and BufferedWriter.

How to loop menus using while and switch.

How to build atmosphere with ANSI colors and a typewriter effect.

How to separate code into methods that work together smoothly.

🧠 Known Limitations

In January, the “Previous Month” logic doesn’t wrap to December (for now).

The Custom Search menu is waiting for you to finish it, Detective.

Vendor searches must match names correctly — even I respect precision.

💀 Future Upgrades

Add totals for deposits, payments, and net balance.

Add headers to printed reports.

Improve month wraparound logic.

“Numbers never lie, Batman. But the people who use them... now that’s another riddle entirely.” — The Riddler
