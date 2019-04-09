
import java.util.Scanner;

public class Game {

  public static void main(String[] args) {

      Scanner sc = new Scanner(System.in);
      //AI comp = new AI(7);
      AI comp = new SecondUtility(7);
      run(sc,comp,decideFirst(sc));

  }

  //============================================================================

  public static boolean decideFirst(Scanner sc) {
    boolean first = false;
    boolean one = true;
    int num = 0;

    while(one) {
      System.out.println("Who plays first?\n1. Human ("+Problem.p+") 2. AI ("+Problem.c+")");
      try {
        num = Integer.parseInt(sc.nextLine());

        if(num == 1 || num == 2) {
          if(num == 1) {
            first = true;
          }
          one = false;

        } else {
          System.out.println("Choose the number shown.");
        }
      } catch(Exception e) {
        System.out.println("Improper entry, try again.");
      }
    }

    return first;
  }

  //============================================================================

  public static void run(Scanner sc,AI comp,boolean first){
    char[][] board = new char[7][7];
    int num = 0;

    //System.out.println("Enter 777 to quit.");
    while(num != 1) {

      if(first) {
        try {
          num = playerMove(sc,board,Problem.p);
          Problem.printBoard(board);
        } catch(NumberFormatException e) {
          num = -1;
        }
      }

      if(num == -1) {
        System.out.println("\nTry again.");
      } else {

        if(num != 1) {
          num = computerMove(comp,board,Problem.c);
          Problem.printBoard(board);
        }
      }
      if(!first) {
        first = true;
      }
    }

  }

  //============================================================================

  public static int playerMove(Scanner sc, char[][] board, char c) {
    System.out.println("\nChoose a col, Player.");
    int num = Integer.parseInt(sc.nextLine());

    return moveCheck(board,num,c);
  }

  //============================================================================

  public static int computerMove(AI comp, char[][] board, char c) {
    System.out.println("\nThe Computer acts...");
    int num = comp.alphaBetaSearch(new State(board,0,0,0));

    return moveCheck(board,num+1,c);
  }

  //============================================================================

  public static int moveCheck(char[][] board, int col, char c) {
    col = col-1;
    int row = 0;
    boolean validate = false;

    if(col < 0 || col > 6) {
      validate = true;
    } else {
      for(row = board.length-1; row >= 0; row--) {
        if(board[row][col] == '\u0000') {
          board[row][col] = c;
          break;
        }
      }
    }

    if(validate || row < 0) {
      if(Problem.countSpaces(board) < 1) {
        System.out.println("It's a tie!");
        return 1;
      }
      return -1;
    } else {
      return Problem.check(board,row,col,c);
    }

  }

}
