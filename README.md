# NQueens-HillClimbing
Solved the N-Queens Problem Through Hill Climbing and Its Variants

Problem Statement:
The problem is to experiment with the n-queens problem by using hill-climbing search and its variants. Using a programming language, implement the followings:
1 Hill climbing search
2 Hill-climbing search with sideways move
3 Random-restart hill-climbing with sideways move
4 Random-restart hill-climbing without sideways move

Description :
N queen problem is a problem of placing N number of Queens in a Chess game of N X N chessboard so that no two queens can attack each other. We have to check that no two queens are placed in same row, column or any diagonal. The solutions exist for all natural numbers N with the exception of N = 2 and N = 3.
We can solve the N queen problems using two main methods:
1. Backtracking
2. Hill climbing search
In this project, we are using Hill climbing search to solve the N queen’s problem.

Global variable used:

1. success- success rate
2. failure- failure rate
3. n- no. of queens
4. count-
5. m- type of algorithm
6. curr- current state of heuristic function
7. hvalue- heuristic value
8. steps- no. of steps till solution
9. stepsuccess- average no. of steps required to successfully complete the algorithm
10. fail- average no. of steps in failure
11. randomcount- random count
12. loop_break- Boolean loop break
13. i- iteration
14. adjacentsquare- variable used to generate new board by moving one queen to that variable 15. startBoard- initial board with random queens
16. newstate- new state
17. currhvalue- cuurent heuristic value
18. best- optimal heuristic value
19. temp- temporary value
20. temp_length- temporary length
21. column- variable used to store the column no.
22. currentState- current state
23. neighbourState- neighbouring state
Local variable used:

1. board_temp- board used to store temporary variable
2. i- iteration
3. l- iterating variable
4. r- row
5. c- column

Functions Used:

1. genboard()
function used to generate a random board of n queens
2. displayfunction()
used to display the board
3. move_side_way()
it contains iterative loops for sideways movement
4. generate_next_board()
used to generate new board by moving one queen at a time
5. random_move_one_queen()
randomly moves one queen
6. calc_hfunction()
calculates heuristic function
7. conflictverify()
function to reserve two conflicting queens in same row, column or diagonal.

Result :

Algorithm                           | Success Rate | Failure Rate | Avg No. of Steps in Success | Avg No. of Steps in Failure
Hill Climbing                       | 72%          | 27%          | 1                           | 2
Hill Climbing with Sideways Move    | 100%         | 0%           | 1                           | 0
Random Restart with Sideways Move   | 91%          | 9%           | 38                          | 31
Random Restart without Sideways Move| 93%          | 7%           | 602                         | 30
