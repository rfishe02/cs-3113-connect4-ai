
import java.util.Scanner;

public class Test {

  public static void main(String[] args) {

      AI comp = new AI();
      char[][] board = new char[7][7];

      Scanner sc = new Scanner(System.in);
      boolean one = true;
      boolean two = true;
      boolean first = false;
      int num;

      while(one) {
        System.out.println("Who plays first?");
        System.out.println("1. Human 2. AI");
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

      while(two) {

        if(first) {

          System.out.println("Choose a row");
          num = Integer.parseInt(sc.nextLine());
          two = !playerMove(board,num,'P');

          if(two){
            System.out.println("The computer is thinking...");
            num = comp.alphaBetaSearch(new State(board,0,0,0));
            two = !playerMove(board,num+1,'C');
          }

        } else {

          System.out.println("The computer is thinking...");
          num = comp.alphaBetaSearch(new State(board,0,0,0));
          two = !playerMove(board,num+1,'C');

          if(two){
            System.out.println("Choose a row");
            num = Integer.parseInt(sc.nextLine());
            two = !playerMove(board,num,'P');
          }

        }

        printBoard(board);

      }

  }

  public static void printBoard(char[][] board) {
    for(int row = 0; row < board.length; row++) {
      for(int col = 0; col < board[0].length; col++) {
        System.out.print("["+board[row][col]+"] ");
      }
      System.out.println();
    }
  }

  public static boolean playerMove(char[][] board, int col, char c) {
    col = col-1;
    int row;

    for(row = board.length-1; row >= 0; row--) {
      if(board[row][col] == '\u0000') {
        board[row][col] = c;
        break;
      }
    }

    return Problem.check(board,row,col,c);
  }

}
