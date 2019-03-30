public class Board {

  char[][] board;

  public Board(int x, int y) {
    board = new char[x][y];
  }

  public void printBoard() {
    for(int row = 0; row < board.length; row++) {
      for(int col = 0; col < board[0].length; col++) {
        System.out.print("["+board[row][col]+"] ");
      }
      System.out.println();
    }
  }

  public boolean add(int col, char c) {
    col = col-1;
    int row;

    for(row = board.length-1; row >= 0; row--) {
      if(board[row][col] == '\u0000') {
        board[row][col] = c;
        break;
      }
    }

    return statusCheck(row,col,c);
  }

  /////////////////////////////////////////////////
  // Check near the drop position, within bounds,
  // if there is one consecutive tile
  // and the count is < 4.

  public boolean statusCheck(int row, int col, char c) {
    int count = 0;
    boolean win = false;
    int tmp = col;

    if(col > 3) {
      tmp = board[0].length-col;
    }

    count = checkDir(col,c,board.length,true); // Check rows

    if(count < 4) {
      count = checkDir(row,c,board[0].length,false); // Check cols
    }
    if(count < 4) {
      System.out.println("SE");
      count = checkDiag(row,col,c,board.length,board[0].length,false,false,1,1); // SE
    }
    if(count < 4) {
      System.out.println("SW");
      count = checkDiag(row,col,c,board.length,0,false,true,1,-1); // SW
    }

    if(count >= 4) {
      System.out.println("Winner!");
      win = true;
    }

    return win;
  }

  /////////////////////////////////////////////////
  // Methods used to count consecutive tiles.

  public int checkDir(int stat, char c, int len, boolean cond) {
    int count = 0;
    boolean flag = false;
    for(int a = 0; dirLenCond(a,len); a++) {
      if(dirCond(c,a,stat,cond)) {
        count++;
        flag = true;
      } else if(flag) {
        count = 0;
        flag = false;
      }
    }
    return count;
  }

  boolean dirLenCond(int a, int len) {
    return a < len;
  }

  boolean dirCond(char c, int dyn, int stat, boolean type) {
    if(type) {
      return board[dyn][stat] == c; // Move rows
    } else {
      return board[stat][dyn] == c; // Move cols
    }
  }

  public int checkDiag(int row, int col, char c, int lenOne, int lenTwo, boolean condOne, boolean condTwo, int rowInc, int colInc) {
    int count = 0;
    int a = row;
    int b = col;
    boolean flag = false;
    while(diagCond(a-rowInc,board.length-lenOne,!condOne) && diagCond(b-colInc,board[0].length-lenTwo,!condTwo)) {
      a-=rowInc;
      b-=colInc;
    } // Move to the end / beginning of the board, diagonally.
    while(diagCond(a,lenOne,condOne) && diagCond(b,lenTwo,condTwo)) {
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

  boolean diagCond(int a, int len, boolean type) {
    if(type) {
      return a >= len;
    } else {
      return a < len;
    }
  }

}
