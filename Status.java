
// Author: Renae Fisher
// Class: CS 3113
// Assignment: Connect 4
// Date: 4-9-19
// Desc: Holds static methods used to look for the winning state.

public class Status {

  static char c = 'O';
  static char p = 'X';

  //============================================================================
  // Check the near the drop position, if the count is < 4.

  public static int check(char[][] board, int row, int col, char c) {
    int res = 0;
    if(maxCount(board,row,col,c) > 3) {
      if(c == p) {
        System.out.println("The Player Wins!");
      } else {
        System.out.println("The Computer Wins!");
      }
      res = 1;
    }
    return res;
  }

  //============================================================================

  public static byte maxCount(char[][] board, int row, int col, char c) {
    byte count = 0;
    byte tmp;

    tmp = checkDir(board,col,c,board.length,true); // Check rows
    if(tmp > count) {
      count = tmp;
    }

    tmp = checkDir(board,row,c,board[0].length,false); // Check cols
    if(tmp > count) {
      count = tmp;
    }

    tmp = checkDiag(board,row,col,c,board.length,board[0].length,false,false,1,1); // SE
    if(tmp > count) {
      count = tmp;
    }

    tmp = checkDiag(board,row,col,c,board.length,0,false,true,1,-1); // SW
    if(tmp > count) {
      count = tmp;
    }

    return count;
  }

  //============================================================================
  // Methods used to count consecutive tiles.

  public static byte checkDir(char[][] board, int stat, char c, int len, boolean cond) {
    byte count = 0;
    int a = 0;
    boolean flag = false;

    while(count < 4 && a < len) {
      if(dirCond(board,c,a,stat,cond)) {
        count++;
        flag = true;
      } else if(flag) {
        count = 0;
        flag = false;
      }
      a++;
    }
    return count;
  }

  //============================================================================

  public static boolean dirCond(char[][] board, char c, int dyn, int stat, boolean type) {
    if(type) {
      return board[dyn][stat] == c; // Move rows
    } else {
      return board[stat][dyn] == c; // Move cols
    }
  }

  //============================================================================

  public static byte checkDiag(char[][] board, int row, int col, char c, int lenOne, int lenTwo, boolean condOne, boolean condTwo, int rowInc, int colInc) {
    byte count = 0;
    int a = row;
    int b = col;
    boolean flag = false;
    while(diagCond(a-rowInc,board.length-lenOne,!condOne) && diagCond(b-colInc,board[0].length-lenTwo,!condTwo)) {
      a-=rowInc;
      b-=colInc;
    } // Move to the end or beginning of the board, diagonally.
    while(count < 4 && diagCond(a,lenOne,condOne) && diagCond(b,lenTwo,condTwo)) {
      if(board[a][b] == c) {
        count++;
        flag = true;
      } else if(flag) {
        count = 0;
        flag = false;
      }
      a+=rowInc;
      b+=colInc;
    } // Count the number of consecutive tiles.
    return count;
  }

  //============================================================================

  public static boolean diagCond(int a, int len, boolean type) {
    if(type) {
      return a >= len;
    } else {
      return a < len;
    }
  }

  //============================================================================

  public static int countSpaces(char[][] board) {
    int spaces = 0;
    for(int row = 0; row < board.length; row++) {
      for(int col = 0; col < board[0].length; col++) {
        if(board[row][col] == '\u0000') {
          spaces++;
        }
      }
    }
    return spaces;
  }

  //============================================================================

  public static void printBoard(char[][] board) {
    for(int row = 0; row < board.length; row++) {
      for(int col = 0; col < board[0].length; col++) {
        System.out.print("["+board[row][col]+"] ");
      }
      System.out.println();
    }
  }

}
