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
  // Check status near drop position

  public boolean statusCheck(int row, int col, char c) {

    int count = 0;
    boolean win = false;

    if(row+1 < board.length && board[row+1][col] == c) {
      count = checkSouth(row,col,c);
    }
    if(count < 4 && col+1 < board[0].length && board[row][col+1] == c) {
      count = checkEast(row,col,c);
    }
    if(count < 4 && col-1 >= 0 && board[row][col-1] == c) {
      count = checkWest(row,col,c);
    }
    if(count < 4 && row-1 >= 0 && board[row-1][col] == c) {
      count = checkNorth(row,col,c);
    }

    if(count >= 4) {
      System.out.println("Winner!");
      win = true;
    }

    return win;
  }

  /////////////////////////////////////////////////
  // Methods used to count consecutive tiles.

  public int checkSouth(int row, int col, char c) {
    int count = 0;
    for(int a = row; a < board.length; a++) {
      if(board[a][col] == c) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  public int checkEast(int row, int col, char c) {
    int count = 0;
    for(int a = col; a < board[0].length; a++) {
      if(board[row][a] == c) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  public int checkWest(int row, int col, char c) {
    int count = 0;
    for(int a = col; a >= 0; a--) {
      if(board[row][a] == c) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  public int checkNorth(int row, int col, char c) {
    int count = 0;
    for(int a = row; a >= 0; a--) {
      if(board[a][col] == c) {
        count++;
      } else {
        break;
      }
    }
    return count;
  }

  public int checkNWDiag(int row, int col, char c) {
    int count = 0;

    for(int a = row; a >= 0; a--) {
      for(int b = col; b >= 0; b--) {
        if(board[a][b] == c) {
          count++;
        } else {
          break;
        }
      }
    }

    return count;
  }

  public int checkNEDiag(int row, int col, char c) {
    int count = 0;

    return count;
  }

  public int checkSWDiag(int row, int col, char c) {
    int count = 0;

    return count;
  }

  public int checkSEDiag(int row, int col, char c) {
    int count = 0;

    return count;
  }

}
