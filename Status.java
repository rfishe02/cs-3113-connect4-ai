
// Author: Renae Fisher
// Class: CS 3113
// Assignment: Connect 4
// Date: 4-9-19
// Desc: Holds static methods used to look for the winning state.

public class Status {

  static char c = 'O';
  static char p = '+';

  //============================================================================
  // The rows, columns, and diagonal tiles near the drop position.

  public static int check(char[][] board, int row, int col, char c, boolean phrase) {
    int res = 0;
    if(maxCount(board,row,col,c) > 3) {
      if(phrase) {
        if(c == p) {
          System.out.println("The Player Wins!");
        } else {
          System.out.println("The Computer Wins!");
        }
      }
      res = 1;
    }
    return res;
  }

  //============================================================================
  // Get the max count from surrounding spaces.

  public static byte maxCount(char[][] board, int row, int col, char c) {
    byte count = 0;
    byte tmp;

    tmp = checkDir(board,board.length,true,row,c); // Check rows
    if(tmp > count) {
      count = tmp;
    }

    tmp = checkDir(board,board[0].length,false,col,c); // Check cols
    if(tmp > count) {
      count = tmp;
    }

    tmp = checkDiag(board,board.length,board[0].length,false,false,1,1,row,col,c); // SE
    if(tmp > count) {
      count = tmp;
    }

    tmp = checkDiag(board,board.length,0,false,true,1,-1,row,col,c); // SW
    if(tmp > count) {
      count = tmp;
    }

    return count;
  }

  //============================================================================
  // Check by rows, or cols.

  public static byte checkDir(char[][] board, int len, boolean cond, int stat, char c) {
    byte max = 0;
    byte count = 0;
    int a = 0;

    while(count < 5 && a < len) {
      if(dirCond(board,cond,a,stat,c)) {
        count++; // add to count, if there is a nearby match

        if(count > max) {
          max = count; // store the value of the longest chain
        }
      } else {
        count = 0; // start a new chain
      }
      a++;
    }
    return max;
  }

  //============================================================================

  public static boolean dirCond(char[][] board, boolean cond, int dyn, int stat, char c) {
    if(cond) {
      return board[stat][dyn] == c; // compare by row
    } else {
      return board[dyn][stat] == c; // compare by column
    }
  }

  //============================================================================
  // Move diagonally, depending on the inputs.

  public static byte checkDiag(char[][] board, int lenOne, int lenTwo, boolean condOne, boolean condTwo, int rowInc, int colInc, int row, int col, char c) {
    byte max = 0;
    byte count = 0;
    int a = row;
    int b = col;

    while(diagCond(a-rowInc,board.length-lenOne,!condOne) && diagCond(b-colInc,board[0].length-lenTwo,!condTwo)) {
      a-=rowInc;
      b-=colInc;
    } // Move to the end or beginning of the board, diagonally.

    while(count < 4 && diagCond(a,lenOne,condOne) && diagCond(b,lenTwo,condTwo)) {
      if(board[a][b] == c) {
        count++;

        if(count > max) {
          max = count;
        }
      } else {
        count = 0;
      }

      a+=rowInc;
      b+=colInc;
    }

    return max;
  }

  //============================================================================

  public static boolean diagCond(int a, int len, boolean cond) {
    if(cond) {
      return a >= len;
    } else {
      return a < len;
    }
  }

  //============================================================================
  // Used to determine if the game ends in a tie.

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
  // Just prints the board.

  public static void printBoard(char[][] board) {
    System.out.println(" "+1+"   "+2+"   "+3+"   "+4+"   "+5+"   "+6+"   "+7);
    for(int row = 0; row < board.length; row++) {
      for(int col = 0; col < board[0].length; col++) {
        System.out.print("["+board[row][col]+"] ");
      }
      System.out.println();
    }
  }

  //============================================================================
  // Test condition checking.

  public static void testDir() {
    char[][] b =
    {
      {'\u0000','\u0000','\u0000','\u0000','\u0000','Y','Y'},
      {'Y','Y','Y','Z','Z','Z','Y'},
      {'Y','Z','Z','Z','Z','Y','Y'},
      {'\u0000','\u0000','\u0000','\u0000','\u0000','Z','Y'},
      {'\u0000','\u0000','\u0000','\u0000','\u0000','Z','Z'},
      {'\u0000','\u0000','\u0000','\u0000','\u0000','Z','Z'},
      {'\u0000','\u0000','\u0000','\u0000','\u0000','Y','Z'}
    };

    Status.printBoard(b);

    System.out.println("Max chain Y in row 1: "+maxCount(b, 1, 1, 'Y'));
    System.out.println("Max chain Z in row 2: "+maxCount(b, 2, 1, 'Z'));

    System.out.println("Max chain Z in col 5: "+maxCount(b, 5, 5, 'Z'));
    System.out.println("Max chain Y in col 6: "+maxCount(b, 5, 6, 'Y'));

  }

  public static void testDiag() {
    char[][] b =
    {
      {'Z','\u0000','\u0000','\u0000','\u0000','\u0000','Y'},
      {'\u0000','Y','\u0000','\u0000','\u0000','Z','\u0000'},
      {'\u0000','\u0000','Y','\u0000','Z','\u0000','\u0000'},
      {'Y','\u0000','\u0000','Y','\u0000','\u0000','Z'},
      {'\u0000','Y','Z','\u0000','Y','Z','\u0000'},
      {'\u0000','Z','Y','\u0000','Z','Z','\u0000'},
      {'Y','\u0000','\u0000','Y','\u0000','\u0000','Z'}
    };

    Status.printBoard(b);

    //System.out.println("Max chain Z: "+maxCount(b, 6, 6, 'Z'));
    //System.out.println("Max chain Y: "+maxCount(b, 6, 0, 'Y'));

    System.out.println("Max chain Z: "+maxCount(b, 3, 6, 'Z'));
    System.out.println("Max chain Y: "+maxCount(b, 3, 3, 'Y'));

  }

}
