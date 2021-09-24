import java.util.Scanner;

public class ConnectFour
{
    public static void main (String args[])
    {
        char[][] board;
        board = new char[6][15];
        int turns = 0;
        boolean gameWin = false;

        board = initializeBoard(board);

        while(turns < 43 || !gameWin)
        {
            displayBoard(board);
            redDiskDrop(board);
            turns++;
            gameWin = isHorizontalWin(board, gameWin);
            gameWin = isVerticalWin(board, gameWin);
            gameWin = isForwardDiagonalWin(board, gameWin);
            gameWin = isBackwardDiagonalWin(board, gameWin);

            if(gameWin == true)
            {
                break;
            }

            displayBoard(board);
            yellowDiskDrop(board);
            turns++;
            gameWin = isHorizontalWin(board, gameWin);
            gameWin = isVerticalWin(board, gameWin);
            gameWin = isForwardDiagonalWin(board, gameWin);
            gameWin = isBackwardDiagonalWin(board, gameWin);

            if(gameWin == true)
            {
                break;
            }
        }
        if(turns % 2 == 1)
        {
            System.out.println("Red Player Won!");
            displayBoard(board);
        }
        else if(turns % 2 == 0)
        {
            System.out.println("Yellow Player Won!");
            displayBoard(board);
        }
        else if(turns >= 42)
        {
            System.out.println("Tie Game");
            displayBoard(board);
        }
    }

    public static char[][] initializeBoard(char[][] board)
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[i].length; j++)
            {
                if(j % 2 == 0)
                {
                    board[i][j] = '|';
                }
                else if(j % 2 == 1)
                {
                    board[i][j] = ' ';
                }
            }
        }
        return board;
    }

    public static void displayBoard(char[][] board)
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
        Scanner input = new Scanner(System.in);
       System.out.println("\nPlease enter a number (0-6) to drop the Red disk: ");
       int choice = input.nextInt();
       while(choice > 6 || choice < 0)
       {
           System.out.println("Error: please enter a number  between 0-6 to drop RED disk: ");
           choice = input.nextInt();
       }

       choice = 2 * choice + 1; //convert users choice to match column

       if(board[0][choice] == 'R' || board[0][choice] == 'Y' ) //checking if column picked is full
       {
           System.out.println("That column is full please choose another column 0-6: ");

           choice = input.nextInt();
           choice = 2 * choice + 1;

           for(int i = 5 ; i < board.length; i--)
           {
               if(board[i][choice] == ' ')
               {
                       board[i][choice] = 'R';
                       break;
               }
           }

       }
       else //if not let them drop the disk
       {
           for(int i = 5 ; i < board.length; i--)//search array from bottom of column upwards
           {
               if(board[i][choice] == ' ')
               {
                   board[i][choice] = 'R';
                   break;
               }
           }

       }

    }

    public static void yellowDiskDrop(char[][] board)
    {
        Scanner input = new Scanner(System.in);
        System.out.println("\nPlease enter a number (0-6) to drop the YELLOW disk: ");
        int choice = input.nextInt();
        while(choice > 6 || choice < 0)
        {
            System.out.println("Error: please enter a number  between 0-6 to drop YELLOW disk: ");
            choice = input.nextInt();
        }

        choice = 2 * choice + 1;
        if(board[0][choice] == 'R' || board[0][choice] == 'Y' )
        {
            System.out.println("That column is full please choose another column 0-6: ");
            choice = input.nextInt();
            choice = 2 * choice + 1;
            for(int i = 5 ; i < board.length; i--)
            {
                if(board[i][choice] == ' ')
                {
                        board[i][choice] = 'Y';
                        break;
                }
            }
        }
        else
        {
            for(int i = 5 ; i < board.length; i--)
            {
                if(board[i][choice] == ' ')
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
               }
           }
       }
       return gameWin;
    }

    public static boolean isVerticalWin(char[][] board, boolean gameWin)
    {
        for(int j = 0; j < board[0].length; j++)
        {
            for(int i = 0; i < 3; i++)
            {
                if(board[i][j] == board[i+1][j] && board[i][j] == board[i+2][j] && board[i][j] == board[i+3][j]
                    && board[i][j] != ' ' && board[i][j] != '|')
                {
                    gameWin = true;
                }
            }
        }
        return gameWin;
    }

    public static boolean isForwardDiagonalWin(char[][] board, boolean gameWin)
    {
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                if (board[i][j + 1] == board[i + 1][j + 3] && board[i][j + 1] == board[i + 2][j + 5]
                        && board[i][j + 1] == board[i + 3][j + 7] && board[i][j + 1] != '|' && board[i][j + 1] != ' ')
                {
                    gameWin = true;
                }
            }
        }
        return gameWin;
    }

    public static boolean isBackwardDiagonalWin(char[][] board, boolean gameWin)
    {
        for(int i = 5; i > 3; i--)
        {
            for(int j = 0; j < 7; j++)
            {
                if(board[i][j + 1] == board[i - 1][j + 3] && board[i][j+1] == board[i - 2][j +5]
                   && board[i][j + 1] == board[i - 3][j + 7] && board[i][j+1] != '|' && board[i][j + 1] != ' ')
                {
                    gameWin = true;
                }
            }
        }
        return gameWin;
    }
}









