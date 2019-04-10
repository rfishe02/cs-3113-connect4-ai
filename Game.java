
// Author: Renae Fisher
// Class: CS 3113
// Assignment: Connect 4
// Date: 4-9-19
// Desc: The executable class that starts the game.

import java.util.Scanner;
import java.util.Random;

public class Game {

  public static void main(String[] args) {

      //Status.testDir();
      //Status.testDiag();

      Scanner sc = new Scanner(System.in);

      AI comp = new FirstUtility(7);
      AI comp2 = new FirstUtility(7);
      //AI comp = new SecondUtility(7);

      Random rand = new Random();
      runTest(comp,comp2,rand.nextBoolean());

  }

  //============================================================================
  // Ask the player who goes first.

  public static boolean decideFirst(Scanner sc) {
    boolean first = false;
    boolean one = true;
    int num = 0;

    while(one) {
      System.out.println("Who plays first?\n1. Human ("+Status.p+") 2. AI ("+Status.c+")");
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
  // Launches the game. The player may go first, or the AI may go first.

  public static void playGame(Scanner sc,AI comp,boolean first){
    char[][] board = new char[7][7];
    int num = 0;

    //System.out.println("Enter 777 to quit.");
    while(num != 1) {

      if(first) {
        try {
          num = playerMove(sc,board,Status.p);
          //num = computerMove(comp,board,Status.p);
          Status.printBoard(board);
        } catch(NumberFormatException e) {
          num = -1;
        }
      }

      if(num == -1) {
        System.out.println("\nTry again.");
      } else {

        if(num != 1) {
          num = computerMove(comp,board,Status.c);
          Status.printBoard(board);
        }
      }
      if(!first) {
        first = true;
      }
    }

  }

  //============================================================================
  // Test the AI against another AI.

  public static void runTest(AI comp,AI comp2,boolean first){
    char[][] board = new char[7][7];
    int num = 0;

    //System.out.println("Enter 777 to quit.");
    while(num != 1) {

      if(first) {
        num = computerMove(comp,board,Status.p);
        Status.printBoard(board);
      }

      if(num != 1) {
        num = computerMove(comp,board,Status.c);
        Status.printBoard(board);
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
  // Check to see if the move is valid. If it's valid, see if it caused a
  // winning state. Else, the player typed the wrong response, or it's a tie.

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
      if(Status.countSpaces(board) < 1) {
        System.out.println("It's a tie!");
        return 1;
      }
      return -1;
    } else {
      return Status.check(board,row,col,c);
    }

  }

}
