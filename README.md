Assignment No. 4 — Berkeley Clock Synchronization Algorithm
Aim

Implement Berkeley algorithm for clock synchronization.

1. THEORY
What is Clock Synchronization?

In distributed systems, different computers have different system times.

Example:

Computer	Time
System 1	10
System 2	12
System 3	13
System 4	11

Because clocks differ:

communication problems occur
event ordering becomes difficult
synchronization errors happen

So all clocks must be synchronized.

What is Berkeley Algorithm?

Berkeley Algorithm is a clock synchronization algorithm used in distributed systems.

It synchronizes clocks without using UTC server.

One system acts as:

Master Clock

Other systems act as:

Slave Clocks

Master collects time from all systems and calculates average time.

Then all clocks are adjusted according to average.

Real Life Example

Suppose 5 friends have watches showing different times.

One friend becomes leader.

Leader:

Checks everyone's watch
Calculates average time
Tells everyone to adjust watches

This is Berkeley Algorithm.

Important Concepts
Term	Meaning
Master Clock	Main coordinator
Slave Clock	Other systems
Time Difference	Difference between clocks
Average Time	Synchronization value
Working of Berkeley Algorithm
Step 1

Master polls all systems.

Step 2

All systems send their clock times.

Step 3

Master calculates:

Average Time
Step 4

Master computes time difference.

Step 5

Master broadcasts updated time.

Formula Used

Average Clock:

Average Clock=
Number of Systems
∑Clock Values
	​


Example

Clock Values:

10 12 13 11 14

Sum:

60

Average:

60/5 = 12

So all systems synchronize near:

12
2. PROGRAM
import java.util.ArrayList;

public class BerkeleyClockSync {

    public static void main(String[] args) {

        int[] systemClocks = {10, 12, 13, 11, 14};

        int masterClock = 0;

        System.out.print("System clocks: ");

        for (int clock : systemClocks) {
            System.out.print(clock + " ");
        }

        System.out.println();

        int sum = 0;

        for (int clock : systemClocks) {
            sum += clock;
        }

        int averageClock = sum / systemClocks.length;

        ArrayList<Integer> timeDifferences = new ArrayList<>();

        for (int clock : systemClocks) {
            timeDifferences.add(averageClock - clock);
        }

        int timeAdjustment = 0;

        for (int difference : timeDifferences) {
            timeAdjustment += difference;
        }

        timeAdjustment /= timeDifferences.size();

        masterClock = averageClock - timeAdjustment;

        System.out.print("Updated system clocks: ");

        for (int clock : systemClocks) {
            System.out.print((clock - timeAdjustment) + " ");
        }

        System.out.println();

        System.out.println("Master clock: " + masterClock);
    }
}

3. CODE EXPLANATION
int[] systemClocks
int[] systemClocks = {10,12,13,11,14};

Stores clock values of systems.

masterClock
int masterClock = 0;

Stores synchronized master time.

Printing Initial Clocks
for (int clock : systemClocks)

Displays all clock values.

Calculate Sum
sum += clock;

Adds all clock values.

Calculate Average
averageClock = sum / systemClocks.length;

Computes average time.

Formula

Average Time=
5
10+12+13+11+14
	​

=12

Time Difference
averageClock - clock

Calculates difference from average.

Store Differences
ArrayList<Integer> timeDifferences

Stores adjustment values.

Time Adjustment
timeAdjustment += difference;

Calculates overall adjustment.

Update Master Clock
masterClock = averageClock - timeAdjustment;

Updates synchronized master clock.

Updated Clocks
(clock - timeAdjustment)

Synchronizes all clocks.

4. HOW PROGRAM WORKS
Initial Clocks
10 12 13 11 14
Average
12
Differences
Clock	Difference
10	+2
12	0
13	-1
11	+1
14	-2
Updated Clocks

All clocks become synchronized near average value.

5. HOW TO RUN
Compile
javac BerkeleyClockSync.java
Execute
java BerkeleyClockSync
Expected Output
System clocks: 10 12 13 11 14
Updated system clocks: 10 12 13 11 14
Master clock: 12
6. HOW TO EXPLAIN IN VIVA

“This program implements Berkeley Clock Synchronization Algorithm. Multiple system clocks are considered. One system acts as master. Master calculates average clock value and adjusts all clocks according to average time.”

7. IMPORTANT ORAL QUESTIONS
Q1. What is Berkeley Algorithm?

Berkeley Algorithm is a clock synchronization technique used in distributed systems.

Q2. Why Clock Synchronization is Needed?

To maintain consistent time among distributed systems.

Q3. What is Master Clock?

Main system that coordinates synchronization.

Q4. What is Slave Clock?

Other systems participating in synchronization.

Q5. What is Average Time?

Average of all system clocks.

Q6. Advantage of Berkeley Algorithm?

Does not require UTC server.

Q7. Disadvantage?

If master fails synchronization stops.

Q8. Difference between Cristian and Berkeley Algorithm?
Cristian	Berkeley
Uses time server	Uses master node
External UTC	Internal synchronization
Q9. What is Distributed System?

Collection of independent systems working together.

Q10. Why Average is Calculated?

To synchronize all clocks to common time.

8. COMMON ERRORS
Error 1
ClassNotFoundException
Fix

Compile correctly:

javac BerkeleyClockSync.java
Error 2
Main method not found
Fix

Check:

public static void main(String[] args)
9. SHORT CONCLUSION

“Thus we implemented Berkeley Clock Synchronization Algorithm and synchronized distributed system clocks using average time calculation.”

10. IMPORTANT KEYWORDS

Remember:

Clock Synchronization
Master Clock
Slave Clock
Average Time
Distributed System
Time Difference
Berkeley Algorithm
SUPER IMPORTANT VIVA EXPLANATION (Best)

“In Berkeley Algorithm one node acts as master node. Master collects time from all systems, calculates average time and sends adjusted time to all nodes so that all clocks become synchronized.”

Assignment 4 completed.

Now send:

ass 5

or

next
next
Assignment No. 5 — Token Ring Mutual Exclusion Algorithm
Aim

Implement Token Ring based mutual exclusion algorithm.

1. THEORY
What is Mutual Exclusion?

Mutual Exclusion means:

Only one process can access shared resource at a time.

In distributed systems many processes may try to access:

Printer
Database
Shared File
Critical Section

If all access together:

data corruption occurs
conflicts happen

So mutual exclusion is needed.

What is Token Ring Algorithm?

Token Ring Algorithm is a distributed mutual exclusion algorithm.

Processes are connected logically in ring form.

A special message called:

TOKEN

circulates continuously in ring.

Only process having token can enter critical section.

Real Life Example

Suppose:

One microphone in meeting
Only person holding mic can speak

Mic = Token

After speaking:

mic passes to next person

Same concept in Token Ring.

Ring Structure
0 → 1 → 2 → 3 → 0

Token moves in circular manner.

Important Terms
Term	Meaning
Token	Permission to access resource
Critical Section	Shared resource
Ring	Circular arrangement
Mutual Exclusion	One process at a time
Working of Token Ring
Step 1

Processes form logical ring.

Step 2

Token circulates continuously.

Step 3

Process wanting resource waits for token.

Step 4

When token arrives:

process enters critical section
performs task
Step 5

After completion token passes to next process.

Key Rule
Without token process cannot access critical section.
2. PROGRAM
import java.io.*;
import java.util.*;

class tokenring {

    public static void main(String args[]) throws Throwable {

        Scanner scan = new Scanner(System.in);

        System.out.println("Enter the num of nodes:");

        int n = scan.nextInt();

        int m = n - 1;

        int token = 0;

        int ch = 0, flag = 0;

        for (int i = 0; i < n; i++) {
            System.out.print(" " + i);
        }

        System.out.println(" " + 0);

        do {

            System.out.println("Enter sender:");
            int s = scan.nextInt();

            System.out.println("Enter receiver:");
            int r = scan.nextInt();

            System.out.println("Enter Data:");
            int a = scan.nextInt();

            System.out.print("Token passing:");

            for (int i = token, j = token;
                 (i % n) != s;
                 i++, j = (j + 1) % n) {

                System.out.print(" " + j + "->");
            }

            System.out.println(" " + s);

            System.out.println("Sender " + s + " sending data: " + a);

            for (int i = s + 1; i != r; i = (i + 1) % n) {

                System.out.println("data " + a + " forwarded by " + i);
            }

            System.out.println("Receiver " + r + " received data: " + a + "\n");

            token = s;

            do {

                try {

                    if (flag == 1)
                        System.out.print("Invalid Input!!...");

                    System.out.print("Do you want to send again?? enter 1 for Yes and 0 for No : ");

                    ch = scan.nextInt();

                    if (ch != 1 && ch != 0)
                        flag = 1;
                    else
                        flag = 0;

                } catch (InputMismatchException e) {

                    System.out.println("Invalid Input");

                    scan.next();
                }

            } while (ch != 1 && ch != 0);

        } while (ch == 1);
    }
}

3. CODE EXPLANATION
Import Packages
import java.io.*;
import java.util.*;

Imports utility and input packages.

Scanner Object
Scanner scan = new Scanner(System.in);

Used for keyboard input.

Number of Nodes
int n = scan.nextInt();

User enters total processes.

Example:

4
Token Initialization
int token = 0;

Initially token is with process 0.

Ring Formation
for (int i = 0; i < n; i++)

Displays ring structure.

Example:

0 1 2 3 0
Sender Input
int s = scan.nextInt();

Sender process.

Receiver Input
int r = scan.nextInt();

Receiver process.

Data Input
int a = scan.nextInt();

Data to send.

Token Passing
for (int i = token; (i % n) != s; i++)

Token moves from current holder to sender.

Data Forwarding
for (int i = s + 1; i != r; i = (i + 1) % n)

Data forwarded node by node.

Receiver Receives Data
Receiver r received data

Final destination receives message.

Continue or Exit
Do you want to send again?

User chooses:

1 → Continue
0 → Exit
Exception Handling
catch (InputMismatchException e)

Handles invalid input.

4. WORKING OF PROGRAM
Example
Input
Number of nodes = 4
Sender = 1
Receiver = 3
Data = 100
Ring
0 → 1 → 2 → 3 → 0
Token Passing

Initially token at:

0

Moves to:

1
Data Forwarding
1 → 2 → 3
Final Output
Receiver 3 received data 100
5. HOW TO RUN
Compile
javac tokenring.java
Execute
java tokenring
Expected Output
Enter the num of nodes:
4

0 1 2 3 0

Enter sender:
1

Enter receiver:
3

Enter Data:
100

Token passing: 0->1

Sender 1 sending data: 100

data 100 forwarded by 2

Receiver 3 received data: 100
6. HOW TO EXPLAIN IN VIVA

“This program implements Token Ring mutual exclusion algorithm. Processes are arranged in logical ring. Token circulates among processes. Only process having token can send data or enter critical section. After operation token is passed to next process.”

7. IMPORTANT ORAL QUESTIONS
Q1. What is Token Ring Algorithm?

Token Ring is a distributed mutual exclusion algorithm where token circulates among processes.

Q2. What is Token?

Special message giving permission to access critical section.

Q3. What is Critical Section?

Shared resource accessed by processes.

Q4. Why Mutual Exclusion is Needed?

To avoid conflicts and data corruption.

Q5. Advantage of Token Ring?

No starvation.

Every process eventually gets token.

Q6. Disadvantage?

Token loss causes problem.

Q7. What happens if process doesn't have token?

Cannot enter critical section.

Q8. What is Ring Topology?

Circular arrangement of processes.

Q9. Why token initialized to 0?

Process 0 starts first.

Q10. Difference between Centralized and Token Ring?
Centralized	Token Ring
Single coordinator	Circulating token
Bottleneck possible	Distributed control
8. COMMON ERRORS
Error 1
InputMismatchException
Reason

Entered string instead of integer.

Example:

hello

instead of:

100
Fix

Enter only integers.

Error 2
ClassNotFoundException
Fix

Compile properly:

javac tokenring.java
Error 3

Infinite Loop

Reason

Wrong receiver logic.

9. SHORT CONCLUSION

“Thus we implemented Token Ring mutual exclusion algorithm where token circulates among processes and only token holder can access critical section.”

10. IMPORTANT KEYWORDS

Remember:

Token
Mutual Exclusion
Critical Section
Ring Topology
Distributed System
Token Passing
Shared Resource
SUPER IMPORTANT VIVA EXPLANATION

“In Token Ring Algorithm processes are arranged logically in circular form. A token continuously circulates in ring. Process must acquire token before entering critical section. After completion token is passed to next process.”

Assignment 5 completed.

Now send:

ass 6

or

next
next
Assignment No. 6 — Ring Election Algorithm (Leader Election)
Aim

Implement Ring algorithm for leader election in distributed systems.

1. THEORY
What is Leader Election?

In distributed systems many processes work together.

Sometimes one process must act as:

Coordinator / Leader

Leader manages:

synchronization
resource allocation
communication
coordination

If leader fails:

new leader must be selected

This process is called:

Leader Election
What is Ring Election Algorithm?

Ring Election Algorithm is a distributed election algorithm where processes are arranged in circular ring.

Each process has:

Unique ID

Election message circulates in ring.

Process with:

Highest ID

becomes coordinator.

Real Life Example

Suppose students stand in circle.

Each student says their roll number.

Highest roll number becomes:

Class Representative

Same concept used here.

Important Terms
Term	Meaning
Coordinator	Leader process
Election	Selecting new leader
Ring	Circular structure
Active Process	Running process
Inactive Process	Failed process
Working of Ring Election Algorithm
Step 1

Processes form logical ring.

Step 2

Current coordinator fails.

Step 3

One process starts election.

Step 4

Election message circulates in ring.

Step 5

Highest process ID is selected.

Step 6

New coordinator announced.

Main Rule
Highest process ID becomes coordinator.
2. PROGRAM

Your program uses:

Ring.java

PROGRAM FLOW

The program:

Takes process IDs
Sorts processes
Selects highest ID as coordinator
Performs election
Chooses new leader
3. CODE EXPLANATION
Process Object Array
Rr proc[] = new Rr[10];

Stores process details.

Object Initialization
proc[i] = new Rr();

Creates objects.

Number of Processes
int num = in.nextInt();

User enters total processes.

Example:

5
Process ID Input
proc[i].id = in.nextInt();

Stores process IDs.

Example:

10 20 30 40 50
Process State
proc[i].state = "active";

Initially all processes active.

Sorting Processes
if(proc[j].id > proc[j+1].id)

Sorts process IDs in ascending order.

Highest ID Coordinator
proc[num - 1].state = "inactive";

Highest ID process initially coordinator.

Menu
1. Election
2. Quit
Election Initiation
Enter process who initiated election

One process starts election.

Election Message Passing

Election message circulates through ring.

Each active process participates.

Coordinator Selection

Highest active ID becomes new coordinator.

4. HOW PROGRAM WORKS
Example
Process IDs
10 20 30 40 50
Initial Coordinator
50

Highest ID process.

Coordinator Failure

Process:

50

becomes inactive.

Election Started By

Example:

20
Election Flow
20 → 30 → 40
Highest Active Process
40

becomes coordinator.

Final Coordinator
40 selected as new coordinator
5. HOW TO RUN
Compile
javac Ring.java
Execute
java Ring
Expected Output
Enter number of process:
5

Enter process IDs:
10
20
30
40
50

Process 50 selected as coordinator

1. Election
2. Quit

Enter process who initiated election:
1

New coordinator is process 40
6. HOW TO EXPLAIN IN VIVA

“This program implements Ring Election Algorithm. Processes are arranged logically in ring structure. Each process has unique ID. If coordinator fails election starts. Election message circulates through ring and process with highest active ID becomes new coordinator.”

7. IMPORTANT ORAL QUESTIONS
Q1. What is Ring Election Algorithm?

Ring Election Algorithm is a distributed algorithm used to elect coordinator in ring topology.

Q2. What is Coordinator?

Leader process managing distributed system.

Q3. Why Leader Election Needed?

To select new coordinator when old coordinator fails.

Q4. Which process becomes coordinator?

Process with highest active ID.

Q5. What is Active Process?

Currently running process.

Q6. What is Inactive Process?

Failed or stopped process.

Q7. Advantage of Ring Algorithm?

Simple and distributed.

Q8. Disadvantage?

Election takes more time because message circulates entire ring.

Q9. Difference Between Bully and Ring Algorithm?
Bully Algorithm	Ring Algorithm
Highest ID dominates directly	Election message circulates
Faster	Slower
More messages sometimes	Structured communication
Q10. What is Distributed System?

Collection of independent computers working together.

8. COMMON ERRORS
Error 1
NullPointerException
Reason

Objects not initialized.

Fix
proc[i] = new Rr();
Error 2
InputMismatchException
Reason

Wrong datatype input.

Fix

Enter integers only.

Error 3
ClassNotFoundException
Fix

Compile correctly:

javac Ring.java
9. SHORT CONCLUSION

“Thus we implemented Ring Election Algorithm and selected coordinator using highest active process ID.”

10. IMPORTANT KEYWORDS

Remember:

Leader Election
Coordinator
Ring Topology
Active Process
Inactive Process
Highest ID
Distributed System
SUPER IMPORTANT VIVA EXPLANATION

“In Ring Election Algorithm processes are connected logically in circular form. When coordinator fails election starts. Election message circulates among active processes and process with highest ID becomes new coordinator.”

MOST IMPORTANT LAST-MINUTE REVISION
Assignment	Important Concept
1	RMI
3	MPI
4	Berkeley Clock Sync
5	Token Ring
6	Ring Election
IMPORTANT COMMANDS REVISION
Java Compile
javac filename.java
Java Run
java filename
MPI Compile
mpicc filename.c
MPI Run
mpirun -np 4 ./a.out
