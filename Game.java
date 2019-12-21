
// Author: Renae Fisher
// Class: CS 3113
// Assignment: Connect 4
// Date: 4-9-19
// Desc: The executable class that starts the game.

import java.util.Scanner;
import java.util.Random;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Game {

  public static void main(String[] args) {

      //Status.testDir();
      //Status.testDiag();

      /*
      AI comp = new FirstUtility(7,Status.p,Status.c);
      AI comp2 = new SecondUtility(7,Status.c,Status.p);
      runTest(comp,comp2,300);*/

      /*
      Scanner sc = new Scanner(System.in);
      AI comp2 = new SecondUtility(7,Status.c,Status.p);
      playGame(sc,comp2,decideFirst(sc));
      sc.close();*/
      
      Scanner sc = new Scanner(System.in);
      AI comp2 = new FirstUtility(7,Status.c,Status.p);
      playGame(sc,comp2,decideFirst(sc));
      sc.close();

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
  // -1: Improper move.
  // 0: Starting state.
  // 1: A player won.
  // 2: There was a tie.

  public static void playGame(Scanner sc,AI comp,boolean first){
    char[][] board = new char[7][7];
    int num = 0;

    while(num < 1) {

      if(first) {
        try {
          num = playerMove(sc,board,Status.p);
          Status.printBoard(board);
        } catch(NumberFormatException e) {
          num = -1;
        }
      }

      if(num == -1) {
        System.out.println("\nTry again.");
      } else {
        if(num < 1) {
          num = computerMove(comp,board,comp.p,true);
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
  // -1: Improper move.
  // 0: Starting state.
  // 1: A player won.
  // 2: There was a tie.

  public static void runTest(AI comp,AI comp2,int rounds){

    try {

      PrintWriter pw = new PrintWriter(new FileWriter("outcome3.txt"));
      Random rand = new Random();
      char[][] board;
      int winner = 0;
      int num = 0;
      boolean first;

      pw.write("round,win\n");

      for(int i = 0; i < rounds; i++) {

        board = new char[7][7];
        num = 0;
        winner = 0;
        first = rand.nextBoolean();

        while(num < 1) {

          if(first) {
            num = computerMove(comp,board,comp.p,false);
            if(num == 1) {
              winner = 1;
            }
          }

          if(num != 1) {
            num = computerMove(comp2,board,comp2.p,false);
            //num = randomMove(board,comp2.p);
            if(num == 1) {
              winner = 2;
            }
          }
          if(!first) {
            first = true;
          }

        }

        pw.write(i+","+winner+"\n");

      }

      pw.close();

    } catch(IOException Ex) {
      Ex.printStackTrace();
    }

  }

  //============================================================================

  public static int playerMove(Scanner sc, char[][] board, char c) {
    System.out.println("\nChoose a col, Player.");
    int num = Integer.parseInt(sc.nextLine());

    return moveCheck(board,num,c,true);
  }

  //============================================================================

  public static int computerMove(AI comp, char[][] board, char c, boolean phrase) {
    if(phrase) {
      System.out.println("\nThe computer acts...");
    }
    int num = comp.alphaBetaSearch(board);

    return moveCheck(board,num+1,c,phrase);
  }

  //============================================================================

  public static int randomMove(char[][] board, char c) {

    Random rand = new Random();
    int[] moves = new int[8];
    int size = 0;

    for(int i = 0; i < board.length; i++) {
      if(board[0][i] == '\u0000') {
        moves[size+1] = i+1;
        size++;
      }
    }

    return moveCheck(board,moves[rand.nextInt(size)],c,false);
  }

  //============================================================================
  // Check to see if the move is valid. If it's valid, see if it caused a
  // winning state. Else, the player typed the wrong response, or it's a tie.

  public static int moveCheck(char[][] board, int col, char c, boolean phrase) {
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
        if(phrase) {
          System.out.println("It's a tie!");
        }
        return 2;
      }
      return -1;
    } else {
      return Status.check(board,row,col,c,phrase);
    }

  }

}
