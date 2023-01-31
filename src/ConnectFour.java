/*
Project: Connect Four Game
Created by: Christopher Jackson
This program replicates the Classic Game of Connect Four
created: 9/24/2021
*/
import java.util.InputMismatchException;
import java.util.Scanner;

public class ConnectFour
{
    public static void main (String[] args)
    {
        char[][] board;
        board = new char[6][15]; //declare a 2d array of chars
        int turns = 0;           //keep track of turns
        boolean gameWin = false; //boolean to used to test status of game.

        initializeBoard(board); //initialize board

        while(turns < 42) //loop continues as long as there are still turns left. (21 for each player)
        {
            displayBoard(board);
            redDiskDrop(board);
            turns++; //increment the turn after each disk drop
            gameWin = isHorizontalWin(board, gameWin);//following methods check for a winning game
            gameWin = isVerticalWin(board, gameWin);
            gameWin = isForwardDiagonalWin(board, gameWin);
            gameWin = isBackwardDiagonalWin(board, gameWin);

            if(gameWin) //if a game winning condition was met, break out of the loop.
            {
                break;
            }

            displayBoard(board); //repeat of above code but for the Yellow player
            yellowDiskDrop(board);
            turns++;
            gameWin = isHorizontalWin(board, gameWin);
            gameWin = isVerticalWin(board, gameWin);
            gameWin = isForwardDiagonalWin(board, gameWin);
            gameWin = isBackwardDiagonalWin(board, gameWin);

            if(gameWin)
            {
                break;
            }
        }
        //after a game win has broken loop or all turns used up, determine winner or tie.
        if(turns % 2 == 1)  //if turn was odd, Red player won.
        {
            System.out.println("Red Player Won!");
            displayBoard(board);
        }
        else if(turns % 2 == 0 && turns != 42) //if turns was even, Yellow player won.
        {
            System.out.println("Yellow Player Won!");
            displayBoard(board);
        }
        else if(turns >= 42) //if turns surpassed 42, all disks used and tie game.
        {
            System.out.println("Tie Game");
            displayBoard(board);
        }
    }

    public static void initializeBoard(char[][] board) //method to initialize board with | and spaces
    {
        for(int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[i].length; j++)
            {
                if (j % 2 == 0)
                {
                    board[i][j] = '|';
                } else
                {
                    board[i][j] = ' ';
                }
            }
        }
    }

    public static void displayBoard(char[][] board) //method to display the board
    {
        System.out.println(" 0 1 2 3 4 5 6");
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
        System.out.print("---------------");
    }

    public static void redDiskDrop(char[][] board) //this method drops the red disk
    {
        int choice = 0;
        Scanner input = new Scanner(System.in);
        boolean retry;

        do
        {
            retry = false;
            System.out.println("\nPlease enter a number (0-6) to drop the Red disk: ");
            try
            {
                choice = input.nextInt();
                while (choice > 6 || choice < 0) //make sure user enters a number 0-6
                {
                   System.out.println("Error: please enter a number  between 0-6 to drop RED disk: ");
                   choice = input.nextInt();
                }
            }
            catch(InputMismatchException ex) //catch input mismatch
            {
                System.out.println("Error wrong input, please try again");
                retry = true;   //activate loop again if exception occurred.
                input.nextLine();
            }
        }while(retry); //controls loop, only activates if exception.

        choice = 2 * choice + 1; //convert users choice to match column

        if (board[0][choice] == 'R' || board[0][choice] == 'Y') //checking if column picked is full
        {
            do
            {
                retry = false;
                System.out.println("That column is full please choose another column 0-6: "); //let user know column full
                try //input mismatch validation for this input (if column was full)
                {
                    choice = input.nextInt(); //get a new choice form the user

                    choice = 2 * choice + 1; //convert choice

                    for (int i = 5; i >= 0; i--)
                    {
                        if (board[i][choice] == ' ')
                        {
                            board[i][choice] = 'R';
                            break;
                        }
                    }
                }
                catch(InputMismatchException ex)
                {
                    System.out.println("Error wrong input, please try again!");
                    retry = true;
                    input.nextLine();
                }
            }while(retry); //loop if an input mismatch occurred.
        }
        else //if not let them drop the disk
        {
            for (int i = 5; i >= 0; i--)//search array from bottom of column upwards
            {
                if (board[i][choice] == ' ') //if space place disk
                {
                    board[i][choice] = 'R';
                    break;
                }
            }
        }
    }


    public static void yellowDiskDrop(char[][] board) //this method drops the yellow disk same code as red disk
    {
        int choice = 0;
        Scanner input = new Scanner(System.in);
        boolean retry;

        do
        {
            retry = false;
            System.out.println("\nPlease enter a number (0-6) to drop the YELLOW disk: ");
            try
            {
                choice = input.nextInt();
                while (choice > 6 || choice < 0)
                {
                    System.out.println("Error: please enter a number  between 0-6 to drop YELLOW disk: ");
                    choice = input.nextInt();
                }
            }
            catch(InputMismatchException ex)
            {
                System.out.println("Error wrong input, please try again");
                retry = true;
                input.nextLine();
            }
        }while(retry);

        choice = 2 * choice + 1;

        if (board[0][choice] == 'R' || board[0][choice] == 'Y')
        {
            do
            {
                retry = false;
                System.out.println("That column is full please choose another column 0-6: ");
                try
                {
                    choice = input.nextInt();

                    choice = 2 * choice + 1;

                    for (int i = 5; i > 0; i--)
                    {
                        if (board[i][choice] == ' ')
                        {
                            board[i][choice] = 'Y';
                            break;
                        }
                    }
                }
                catch(InputMismatchException ex)
                {
                    System.out.println("Error wrong input, please try again!");
                    retry = true;
                    input.nextLine();
                }
            }while(retry);
        }
        else
        {
            for (int i = 5; i >= 0; i--)
            {
                if (board[i][choice] == ' ')
                {
                    board[i][choice] = 'Y';
                    break;
                }
            }
        }
    }

    public static boolean isHorizontalWin(char[][] board, boolean gameWin) //checks for a horizontal connect four
    {
       for(int i = 0; i < board.length; i++)
       {
           for(int j = 0; j < 7; j++)
           {
               if(board[i][j+1] == board[i][j+3] && board[i][j+1] == board[i][j+5] && board[i][j+1] == board[i][j+7]
                   && board[i][j+1] != ' ' && board[i][j+1] != '|') //checks for 4 R or Y chars in a row
               {
                   gameWin = true;
                   break;
               }
           }
       }
       return gameWin;
    }

    public static boolean isVerticalWin(char[][] board, boolean gameWin) //checks for a vertical win
    {
        for(int j = 0; j < board[0].length; j++)
        {
            for(int i = 0; i < 3; i++)
            {
                if(board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && board[i][j] == board[i+3][j]
                    && board[i][j] != ' ' && board[i][j] != '|') //check the array for this pattern
                {
                    gameWin = true;
                    break;
                }
            }
        }
        return gameWin;
    }

    public static boolean isForwardDiagonalWin(char[][] board, boolean gameWin) //checks for \ diagonal win.
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if (board[i][j + 1] == board[i + 1][j + 3] && board[i][j + 1] == board[i + 2][j + 5]
                        && board[i][j + 1] == board[i + 3][j + 7] && board[i][j + 1] != '|' && board[i][j + 1] != ' ')
                {
                    gameWin = true;
                    break;
                }
            }
        }
        return gameWin;
    }

    public static boolean isBackwardDiagonalWin(char[][] board, boolean gameWin) //checks for / diagonal win.
    {
        for(int i = 5; i > 3; i--)
        {
            for(int j = 0; j < 7; j++)
            {
                if(board[i][j + 1] == board[i - 1][j + 3] && board[i][j+1] == board[i - 2][j +5]
                   && board[i][j + 1] == board[i - 3][j + 7] && board[i][j+1] != '|' && board[i][j + 1] != ' ')
                {
                    gameWin = true;
                    break;
                }
            }
        }
        return gameWin;
    }
}









