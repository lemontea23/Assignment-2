Assignment No. 1 — RMI (Remote Method Invocation)
Aim

Implement multi-threaded client/server process communication using RMI.

1. THEORY (Important for Viva)
What is RMI?

RMI stands for Remote Method Invocation.
It is a Java API that allows one Java object to call methods of another Java object located on another machine or another JVM.

In simple words:

Client sends request
Server processes request
Server returns response

RMI is used in distributed systems where programs communicate over a network.

Real Life Example

Suppose:

Your phone app = Client
Bank server = Server

When you check balance:

App sends request
Bank server processes it
Server sends balance back

This is similar to RMI communication.

Components of RMI
1. Remote Interface

Defines methods that client can call remotely.

Example:

public String query(String search)
2. Stub Object (Client Side)

Stub acts like a proxy on client side.

Work:

Accept request from client
Convert request into network format
Send to server
3. Skeleton Object (Server Side)

Skeleton receives request from stub.

Work:

Accept request
Call actual server method
Send result back
4. RMI Registry

Registry stores remote object references.

Client uses registry to locate server object.

Command:

rmiregistry
Architecture Flow
Client → Stub → Network → Skeleton → Server Object
2. PROGRAM FILES

Your assignment contains:

Search.java
SearchQuery.java
Server.java
Client.java

3. CODE EXPLANATION
FILE 1 — Search.java
import java.rmi.*;

public interface Search extends Remote {
    public String query(String search) throws RemoteException;
}
Explanation
import java.rmi.*;

Imports RMI package.

interface Search

Creates remote interface.

This interface contains methods that client can call remotely.

extends Remote

Makes interface remote.

Without this:

RMI will not work.
query(String search)

Method for searching text.

Returns:

String
throws RemoteException

Handles network-related errors.

Very important in RMI.

Viva Explanation

“Search.java is a remote interface. It defines methods that can be accessed remotely by the client. It extends Remote interface and methods throw RemoteException.”

FILE 2 — SearchQuery.java
import java.rmi.*;
import java.rmi.server.*;

public class SearchQuery extends UnicastRemoteObject implements Search {
Explanation
UnicastRemoteObject

Used to create remote server object.

implements Search

Implements remote interface.

public SearchQuery() throws RemoteException {
    super();
}

Constructor initializes remote object.

public String query(String search)

Method implementation.

if(search.equals("Reflection in Java"))

Checks whether entered string matches.

result = "Found";

If matched.

Else:

result = "Not Found";
Viva Explanation

“SearchQuery.java implements the remote interface and provides actual implementation of remote methods. UnicastRemoteObject exports object so client can access it remotely.”

FILE 3 — Server.java
private ServerSocket server = null;

Creates server socket.

server = new ServerSocket(port);

Starts server at specific port.

Example:

5000
socket = server.accept();

Waits for client connection.

DataInputStream

Receives data from client.

while (!"Over".equals(line))

Runs until client sends:

Over
line = in.readUTF();

Reads message.

System.out.println(line);

Displays received message.

Viva Explanation

“Server program waits for client connection using ServerSocket. After connection establishment it continuously receives data from client until ‘Over’ message is received.”

FILE 4 — Client.java
socket = new Socket(address, port);

Connects client to server.

BufferedReader

Reads keyboard input.

DataOutputStream

Sends data to server.

line = d.readLine();

Reads message from keyboard.

out.writeUTF(line);

Sends message to server.

Viva Explanation

“Client program establishes connection with server using socket. It reads user input and sends messages to server.”

4. HOW CLIENT-SERVER COMMUNICATION WORKS
Step-by-Step
Step 1

Server starts.

Step 2

Server waits for client.

Step 3

Client connects.

Step 4

Client sends message.

Step 5

Server receives message.

Step 6

Communication continues until:

Over
5. HOW TO RUN PROGRAM
Step 1 — Compile Interface
javac Search.java
Step 2 — Compile Implementation
javac SearchQuery.java
Step 3 — Generate Stub/Skeleton
rmic SearchQuery

Step 4 — Start Registry
rmiregistry &

Step 5 — Compile Server
javac Server.java
Step 6 — Run Server
java Server
Step 7 — Compile Client

Open second terminal.

javac Client.java
Step 8 — Run Client
java Client
Expected Output
Server Side
Server started
Waiting for a client ...
Client accepted
Hello
DS practical
Client Side
Connected
Done with 1st program of DS
Hello
DS practical
Over
6. IMPORTANT VIVA QUESTIONS
Q1. What is RMI?

RMI is a Java API that allows methods of remote objects to be invoked from another JVM.

Q2. Why use RMI?

Used for communication between distributed applications.

Q3. What is Stub?

Stub is client-side proxy object.
It forwards client request to server.

Q4. What is Skeleton?

Skeleton receives request from stub and invokes actual server method.

Q5. What is rmiregistry?

It stores remote object references for clients.

Q6. Why RemoteException is used?

To handle communication/network failures.

Q7. Difference between Client and Server?
Client	Server
Sends request	Processes request
Uses Stub	Uses Skeleton
Initiates communication	Waits for requests
Q8. Why interface is required?

Interface defines remote methods accessible by client.

7. COMMON ERRORS
Error 1
Connection refused
Reason

Server not started.

Fix

Run:

java Server
Error 2
rmiregistry not found
Fix

Install Java 8 properly.

Error 3
ClassNotFoundException
Fix

Compile all files again.

javac *.java
8. SHORT CONCLUSION

“Thus we implemented client-server communication using Java RMI and studied remote method invocation in distributed systems.”

9. HOW TO EXPLAIN COMPLETE PROGRAM IN VIVA (Best Answer)

“First we create a remote interface using Search.java. Then SearchQuery.java implements that interface and provides method definition. Server.java creates server socket and waits for client requests. Client.java connects to server and sends messages. RMI registry helps client locate remote object. Communication happens using stub and skeleton objects.”

10. SUPER IMPORTANT LAST-MINUTE POINTS

Remember these keywords:

RMI
Remote Interface
Stub
Skeleton
RemoteException
rmiregistry
Client-Server Architecture
JVM
Distributed System

Assignment 1 complete.

Now send:

Assignment 3 code
or type:
Assignment No. 3 — MPI (Message Passing Interface)
Aim

Develop a distributed system to find sum of N elements in an array by distributing N/n elements to n number of processors using MPI.

1. THEORY
What is MPI?

MPI stands for Message Passing Interface.
It is used for communication between multiple processes in distributed systems.

MPI helps:

Parallel processing
Faster execution
Process communication
Distributed computing
Real Life Example

Suppose:

1 student solves 100 sums → takes long time
4 students solve 25 sums each → faster

Same concept is used in MPI.

Task is divided among processors.

Important MPI Concepts
Term	Meaning
Process	Independent running program
Rank	Unique ID of process
Master Process	Rank 0
Worker Process	Other ranks
MPI_Send	Send message
MPI_Recv	Receive message
MPI_Init	Starts MPI
MPI_Finalize	Ends MPI
Rank in MPI

Each process gets unique rank.

Example:

0,1,2,3

If 4 processors are used:

Rank 0 → Master
Rank 1 → Worker
Rank 2 → Worker
Rank 3 → Worker

2. PROGRAMS IN ASSIGNMENT

You have 3 programs:

hello.c
send_recv.c
sum_array.c

PROGRAM 1 — hello.c
#include <stdio.h>
#include "mpi.h"

int main(int argc, char* argv[])
{
    int rank, size;

    MPI_Init(&argc, &argv);

    MPI_Comm_rank(MPI_COMM_WORLD, &rank);

    MPI_Comm_size(MPI_COMM_WORLD, &size);

    printf("Hello, world, I am %d of %d\n", rank, size);

    MPI_Finalize();

    return 0;
}
Code Explanation
#include "mpi.h"

Includes MPI library.

Required for MPI functions.

MPI_Init(&argc, &argv);

Starts MPI environment.

Without this MPI cannot work.

MPI_Comm_rank(MPI_COMM_WORLD, &rank);

Gets process rank.

Example:

0
1
2
3
MPI_Comm_size(MPI_COMM_WORLD, &size);

Gets total number of processes.

printf

Displays:

Hello world I am 0 of 4

Meaning:

Rank = 0
Total processes = 4
MPI_Finalize();

Ends MPI execution.

Viva Explanation

“This program demonstrates basic MPI communication. MPI_Init initializes MPI environment. MPI_Comm_rank gets rank of current process. MPI_Comm_size gets total number of processes. Each process prints its rank.”

HOW TO RUN
Compile
mpicc hello.c
Execute with 4 Processes
mpirun -np 4 ./a.out
Expected Output
Hello world I am 0 of 4
Hello world I am 1 of 4
Hello world I am 2 of 4
Hello world I am 3 of 4
PROGRAM 2 — send_recv.c
Purpose

Demonstrates message passing between processes.

Rank 0 sends data to Rank 1.

CODE FLOW
int num = 10;

Data to send.

Sender Side
if(rank == 0)

Rank 0 acts as sender.

MPI_Send(&num, 1, MPI_INT, 1, 1, MPI_COMM_WORLD);

Explanation:

Parameter	Meaning
&num	Data
1	Number of elements
MPI_INT	Integer type
1	Destination rank
1	Tag
MPI_COMM_WORLD	Communicator
Receiver Side
MPI_Recv(&num, 1, MPI_INT, 0, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

Receives data from Rank 0.

Viva Explanation

“This program demonstrates communication between processes using MPI_Send and MPI_Recv. Rank 0 sends integer data to Rank 1.”

HOW TO RUN
Compile
mpicc send_recv.c
Execute
mpirun -np 4 ./a.out
Expected Output
Sending message containing: 10 from rank 0
At rank 1
Received message containing: 10 at rank 1
PROGRAM 3 — sum_array.c
MOST IMPORTANT PROGRAM

This is main assignment program.

Aim of Program

Add 20 numbers using 4 processors.

Each processor calculates partial sum.

Master combines all sums.

WORKING

Array:

1 to 20

Distributed among:

4 processors

Each processor gets:

5 numbers
DISTRIBUTION
Rank	Numbers
0	1-5
1	6-10
2	11-15
3	16-20
CODE EXPLANATION
Array Creation
int num[20];

Stores 20 numbers.

Initialize Array
for(int i=0; i<20; i++)
num[i] = i + 1;

Stores:

1 2 3 ... 20
MASTER PROCESS
if(rank == 0)

Rank 0 acts as master.

Sending Data
MPI_Send(&num[i*5], 5, MPI_INT, i, 1, MPI_COMM_WORLD);

Sends 5 numbers to each worker.

LOCAL SUM
local_sum += num[i];

Each processor calculates local sum.

RECEIVE SUMS
MPI_Recv(&s[i], 1, MPI_INT, i, 1, MPI_COMM_WORLD, MPI_STATUS_IGNORE);

Master receives sums.

FINAL SUM
sum += s[i];

Adds all local sums.

Worker Process

Workers:

Receive numbers
Compute local sum
Send local sum back
Example Calculation
Rank 0
1+2+3+4+5 = 15
Rank 1
6+7+8+9+10 = 40
Rank 2
11+12+13+14+15 = 65
Rank 3
16+17+18+19+20 = 90
Final Sum
15+40+65+90 = 210
HOW TO RUN
Compile
mpicc sum_array.c
Execute
mpirun -np 4 ./a.out
Expected Output
Distribution at rank 0
local sum at rank 0 is 15
local sum at rank 1 is 40
local sum at rank 2 is 65
local sum at rank 3 is 90
final sum = 210
HOW TO EXPLAIN COMPLETE ASSIGNMENT IN VIVA

“This assignment demonstrates distributed processing using MPI. MPI divides work among multiple processors. Rank 0 acts as master process while remaining ranks act as worker processes. Data is distributed using MPI_Send. Each processor calculates local sum and returns result using MPI_Recv. Finally master process computes total sum.”

IMPORTANT ORAL QUESTIONS
Q1. What is MPI?

MPI stands for Message Passing Interface. It is used for communication between distributed processes.

Q2. What is Rank?

Rank is unique ID assigned to each process.

Q3. What is MPI_Init?

Initializes MPI environment.

Q4. What is MPI_Finalize?

Terminates MPI execution.

Q5. What is MPI_Send?

Used to send data between processes.

Q6. What is MPI_Recv?

Used to receive data.

Q7. Why MPI is used?

For parallel processing and faster execution.

Q8. What is Master Process?

Process with Rank 0.

Q9. What is Parallel Processing?

Executing multiple tasks simultaneously.

Q10. Difference between Sequential and Parallel Processing?
Sequential	Parallel
One task at time	Multiple tasks
Slower	Faster
Single processor	Multiple processors
COMMON ERRORS
Error 1
mpicc command not found
Fix

Install OpenMPI.

Error 2
mpirun not found
Fix

Set PATH properly.

Error 3

Program hangs.

Reason

MPI_Send or MPI_Recv mismatch.

Installation Commands
sudo apt-get update
sudo apt install gcc

Compile Command
mpicc filename.c
Execute Command
mpirun -np 4 ./a.out

SHORT CONCLUSION

“Thus we implemented distributed computing using MPI and performed parallel sum calculation using multiple processors.”

SUPER IMPORTANT KEYWORDS

Remember these:

MPI
Rank
Parallel Processing
MPI_Send
MPI_Recv
Master Process
Worker Process
Distributed System
Local Sum
Final Sum



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
