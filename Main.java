import java.util.*;

public class Main {
    private static int n = 0, count = 0, m = 0, curr, hvalue, steps = 0, stepsuccess = 0, fail = 0, randomcount = 0, totalcount = 0,sucessrate = 0, failurerate = 0;
    private static boolean loop_break = false;

    public static void main(String[] args) {
        int success = 0, failure = 0;

        Scanner scan = new Scanner(System.in);

        //while (true) {
            System.out.println("Enter no of queens:");
            n = scan.nextInt();

           if(n<=3){
                System.out.println("no. of queens should be 4 or more");
		System.exit(0);
           }
           System.out.println("Choose the hill climbing variant:");
           System.out.println("1. Hill Climbing");
           System.out.println("2. Sideways Move");
           System.out.println("3. Random Restart without Sideways Move");
           System.out.println("4. Random Restart with Sideways Move");
           m = scan.nextInt();

           if(m>4 || m<0){
		System.out.println("Please enter a value between 1-4");
	    	System.exit(0);
	   }	

        for (int i = 0; i < 750; i++) { // Going through 1000 iterations
            randomcount = 0;
            steps = 0;
            count++;

            queen[] currstate = genboard();
            queen[] adjacentsquare = currstate;

            curr = calc_hfunction(currstate);

            if (m == 4) { // Function of random restart with sideways
                move_side_way(adjacentsquare, currstate);

                if (curr != 0) {
                    adjacentsquare = genboard();
                    hvalue = calc_hfunction(adjacentsquare);
                    randomcount++;
                    totalcount = randomcount + totalcount;

                    move_side_way(adjacentsquare, currstate);

                    randomcount = 0;

                    if (curr != 0) {
                        failure++;
                        steps++;
                        loop_break = false;
                        fail = fail + steps;

                        System.out.println("\n No solution can be derived!!!");
                        displayfunction(currstate);
                        System.out.println("Steps Still Solution: " + steps);
                    }
                }
            } else if (m == 2) { // Sideways move
                move_side_way(adjacentsquare, currstate);

                if (curr != 0) {
                    failure++;
                    steps++;
                    loop_break = false;
                    fail = fail + steps;

                    if (count <= 4) {
                        System.out.println("\n No solution can be derived!!!");
                        displayfunction(currstate);
                        System.out.println("steps till solution: " + steps);
                    }
                }
            } else {
                while (curr != 0) {
                    adjacentsquare = generate_next_board(adjacentsquare); //  New board

                    if (loop_break) {
                        if (m == 1) {
                            failure++;
                            steps++;
                            loop_break = false;
                            fail = fail + steps;

                            if (count <= 4) {
                                System.out.println("\n No solution can be derived!!!");
                                displayfunction(currstate);
                                System.out.println("steps till solution: " + steps);
                            }
                            break;
                        }
                    } else {
                        curr = hvalue;
                    }
                    steps++;
                }
            }
            if (curr == 0) {
                stepsuccess = stepsuccess + steps;
                success++;

                if (count <= 4) {
                    System.out.println("steps till solution: " + steps);
                }
            }
        }
            sucessrate = (success * 100) / (success + failure);
            failurerate = (failure * 100) / (success + failure);

            System.out.println("\n\nSuccess Rate = " + sucessrate + "%");
            System.out.println("Failure Rate = " + failurerate + "%");

            if (m == 3) {
                System.out.println("Avg no. of random restarts without Sideways move = " + Math.round(totalcount / 100));
                System.out.println("Avg no. of steps required without Sideways move = " + Math.round(stepsuccess / success));
            } else if (m == 4) {
                System.out.println("Avg no. of random restarts with Sideways move = " + totalcount);
                System.out.println("Avg no. of steps required with Sideways move = " + Math.round(stepsuccess / success));
            } else {
                System.out.println("Avg no. of steps in success = " + Math.round(success / success));
                System.out.println("Avg no. of steps in failure = " + Math.round(fail / failure));
            }
        }

    //Generates a Random Board of N-Queens
    public static queen[] genboard() {
        queen[] startBoard = new queen[n];
        Random rndm = new Random();

        for (int i = 0; i < n; i++) {
            startBoard[i] = new queen(rndm.nextInt(n), i);
        }

        return startBoard;
    }

    //Print the N-Queen State
    private static void displayfunction(queen[] state) {
        int[][] board_temp = new int[n][n];

        for (int i = 0; i < n; i++) {
            board_temp[state[i].getRow()][state[i].getColumn()] = 1;
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board_temp[i][j] + " ");
            }
            System.out.println();
        }
    }

    //Iterate loops for Sideways moves
    public static void move_side_way(queen[] neighbourState, queen[] currentState) {
        for (int l = 0; l < 100; l++) {
            while (curr != 0) {
                neighbourState = generate_next_board(neighbourState);

                if (loop_break) {
                    loop_break = false;
                    break;
                } else {
                    curr = hvalue;
                }
                steps++;
            }

            if (curr == 0) {
                break;
            }

            currentState = random_move_one_queen(neighbourState);
            hvalue = calc_hfunction(currentState);
        }
    }

    // Function to generate new board by moving one queen at a time
    public static queen[] generate_next_board(queen[] currstate) {
        queen[] adjacentsquare = new queen[n];
        queen[] newstate = new queen[n];

        int currhvalue = calc_hfunction(currstate);
        int best = currhvalue;
        int temp;

        for (int i = 0; i < n; i++) {
            adjacentsquare[i] = new queen(currstate[i].getRow(), currstate[i].getColumn());
            newstate[i] = adjacentsquare[i];
        }
        for (int i = 0; i < n; i++) { //  Traverse each column
            if (i > 0) {
                newstate[i - 1] = new queen(currstate[i - 1].getRow(), currstate[i - 1].getColumn());
            }
            newstate[i] = new queen(0, newstate[i].getColumn());

            for (int j = 0; j < n; j++) { //  Traverse each row
                temp = calc_hfunction(newstate);

                if (temp <= best) {
                    best = temp;
                    for (int k = 0; k < n; k++) {
                        adjacentsquare[k] = new queen(newstate[k].getRow(), newstate[k].getColumn());
                    }
                }

                if (newstate[i].getRow() != n - 1) {
                    if (best != 0) {
                        newstate[i].queen_move();
                    }
                }
            }
        }

        if (best == curr) {
            adjacentsquare = newstate;
            hvalue = best;

            if (m == 3) {
                if (count <= 4) {
                    System.out.println("Random Start");
                }

                adjacentsquare = genboard();
                hvalue = calc_hfunction(adjacentsquare);
                randomcount++;
                totalcount = randomcount + totalcount;
            } else {
                loop_break = true;
            }
        } else {
            hvalue = best;
        }

        if (count <= 4) {
            displayfunction(adjacentsquare);
            System.out.println("hvalue Value = " + hvalue);

            if (hvalue == 0) {
                System.out.println("\nSolution Found:");
                displayfunction(adjacentsquare);
            }
        }

        return adjacentsquare;
    }

    //Randomly move one queeen
    public static queen[] random_move_one_queen(queen[] currstate) {
        Random random = new Random();

        int temp_length = currstate.length;
        int column = random.nextInt(temp_length);
        currstate[column] = new queen(random.nextInt(temp_length), column);

        return currstate;
    }

    //Calculates hvalue for the currstate provided
    public static int calc_hfunction(queen[] currstate) {
        int hvalue = 0;

        for (int i = 0; i < currstate.length; i++) {
            for (int j = i + 1; j < currstate.length; j++) {
                if (currstate[i].conflictverify(currstate[j])) {
                    hvalue++;
                }
            }
        }
        return hvalue;
    }
}

class queen {
    private int r;
    private int c;

    public queen(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public int getRow() {
        return r;
    }

    public void setRow(int r) {
        this.r = r;
    }

    public int getColumn() {
        return c;
    }

    public void setColumn(int c) {
        this.c = c;
    }

    public void queen_move() {
        r++;
    }

    //Function to Resolve Two Queens in the same row,column or diagonal
    public boolean conflictverify(queen q) {
        if (r == q.getRow() || c == q.getColumn()) //  Check the values of rows and columns
            return true;
        else if (Math.abs(c - q.getColumn()) == Math.abs(r - q.getRow())) //  Check the values of diagonals
            return true;
        return false;
    }
}
