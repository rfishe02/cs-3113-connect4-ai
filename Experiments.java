public class Experiments {

  char[][] board;

  /////////////////////////////////////////////////
  // Unused code, primarily used for tests / to reserve older designs.

  /*
  if(row+1 < board.length && board[row+1][col] == c) {
    count = checkDir(row,col,c,board.length,false,true,1); //S
  }
  if(count < 4 && col+1 < board[0].length && board[row][col+1] == c) {
    count = checkDir(col,row,c,board[0].length,false,false,1); //E
  }
  if(count < 4 && col-1 >= 0 && board[row][col-1] == c) {
    count = checkDir(col,row,c,0,true,false,-1); //W
  }
  if(count < 4 && row+1 < board.length && col+1 < board[0].length && board[row+1][col+1] == c){
    count = checkDiag(row,col,c,board.length,board[0].length,false,false,1,1); //SE
  }
  if(count < 4 && row+1 < board.length && col-1 >= 0 && board[row+1][col-1] == c) {
    count = checkDiag(row,col,c,board.length,0,false,true,1,-1); //SW
  }
  if(count < 4 && row-1 >= 0 && col+1 < board[0].length && board[row-1][col+1] == c) {
    count = checkDiag(row,col,c,0,board[0].length,true,false,-1,1); //NE (tested)
  }
  if(count < 4 && row-1 >= 0 && col-1 >= 0 && board[row-1][col-1] == c) {
    count = checkDiag(row,col,c,0,0,true,true,-1,-1); //NW
  }
  */

  public int checkDir(int dyn, int stat, char c, int len, boolean condOne, boolean condTwo, int inc) {
      int count = 0;
      for(int a = dyn; cond(a,len,condOne); a+=inc) {
        if(dirCond(c,a,stat,condTwo)) {
          count++;
        } else {
          break;
        }
      }
      return count;
  }

  boolean dirCond(char c, int dyn, int stat, boolean type) {
      if(type) {
        return board[dyn][stat] == c;
      } else {
        return board[stat][dyn] == c;
      }
  }

  public int checkDiag(int row, int col, char c, int lenOne, int lenTwo, boolean condOne, boolean condTwo, int rowInc, int colInc) {
    int count = 0;
    int a = row;
    int b = col;

    while(cond(a,lenOne,condOne) && cond(b,lenTwo,condTwo)) {
      if(board[a][b] == c) {
        count++;
      } else {
        break;
      }
      a+=rowInc;
      b+=colInc;
    }

    System.out.println("Diag: "+count);

    return count;
  }

  boolean cond(int a, int len, boolean type) {
    if(type) {
      return a >= len;
    } else {
      return a < len;
    }
  }

  /////////////////////////////////////////////////
  // Original direction checking methods

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

  public int checkSEDiag(int row, int col, char c) {
    int count = 0;
    int b = row;
    for(int a = col; a < board[0].length; a++) {
      if(board[b][a] == c) {
        count++;
      } else {
        break;
      }
      b++;
    }
    return count;
  }

  public int checkSWDiag(int row, int col, char c) {
    int count = 0;
    int b = row;
    for(int a = col; a >= 0; a--) {
      if(board[b][a] == c) {
        count++;
      } else {
        break;
      }
      b++;
    }
    return count;
  }

  public int checkNEDiag(int row, int col, char c) {
    int count = 0;
    int b = row;
    for(int a = col; a < board[0].length; a++) {
      if(board[b][a] == c) {
        count++;
      } else {
        break;
      }
      b--;
    }
    return count;
  }

  public int checkNWDiag(int row, int col, char c) {
    int count = 0;
    int b = row;
    for(int a = col; a >= 0; a--) {
      if(board[b][a] == c) {
        count++;
      } else {
        break;
      }
      b--;
    }
    return count;
  }

}
