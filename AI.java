
import java.util.HashSet;
import java.util.Random;

public class AI {

  Random rand;
  int[] action;

  public AI() {
    rand = new Random();
  }

  ///////////////////////////////////////////////////////////////

  public State testMove(char[][] state, int col, char c) {
    char[][] board = deepCopy(state);
    int row;

    for(row = board.length-1; row >= 0; row--) {
      if(board[row][col] == '\u0000') {
        board[row][col] = c;
        break;
      }
    }

    State s = new State(board,row,col,0);
    s.v = Problem.maxCount(s.board,row,col,c);
    return s;
  }

  public char[][] deepCopy(char[][] board) {
    char[][] copy = new char[board.length][board[0].length];

    for(int row = 0; row < board.length; row++) {
      for(int col = 0; col < board[0].length; col++) {
        copy[row][col] = board[row][col];
      }
    }

    return copy;
  }

  ///////////////////////////////////////////////////////////////

  public int alphaBetaSearch(State start) {
    int depth = 0;
    State s = maxValue(start, Integer.MIN_VALUE, Integer.MAX_VALUE,depth);
    return s.col;
  } // Find the state with the highest minmax value, then returns the column / action.

  public State maxValue(State s, int alpha, int beta, int depth) {

    if(s.v >= 4 || depth > 5) {
      return s;
    } // If we reach a terminal or end state, return utility of the state.

    // else ...
    HashSet<Integer> explored = new HashSet<>();
    State res = new State(null,0,0,Integer.MIN_VALUE);
    State tmp;
    int i = rand.nextInt(7);

    while(explored.size() < 7) {
      if(!explored.contains(i) && s.board[0][i] == '\u0000') {
        tmp = minValue(testMove(s.board,i,'C'),alpha,beta,depth+1);

        if(tmp.v >= beta) {
          return tmp;
        } // Return the state with a greater value for beta.

        if(tmp.v > alpha) {
          alpha = tmp.v;
        } // Set alpha to v if it is greater than alpha.

        if(tmp.v > res.v) {
          res.v = tmp.v;
          res.row = tmp.row;
          res.col = tmp.col;
        } // Set the desired values for the result.

      }
      explored.add(i);
      i = rand.nextInt(7);
    }

    return res;
  }

  public State minValue(State s, int alpha, int beta, int depth) {

    if(s.v >= 4 || depth > 5) {
      return s;
    } // If we reach a terminal or end state, return utility of the state.

    // else ...
    HashSet<Integer> explored = new HashSet<>();
    State res = new State(null,0,0,Integer.MAX_VALUE);
    State tmp;
    int i = rand.nextInt(7);

    while(explored.size() < 7) {

      if(!explored.contains(i) && s.board[0][i] == '\u0000') {

        tmp = maxValue(testMove(s.board,i,'P'),alpha,beta,depth+1);

        if(tmp.v <= alpha) {
          return tmp;
        }

        if(tmp.v < beta) {
          beta = tmp.v;
        }

        if(tmp.v < res.v) {
          res.v = tmp.v;
          res.row = tmp.row;
          res.col = tmp.col;
        } // Set the desired values for the result.

      }

      explored.add(i);
      i = rand.nextInt(7);
    }

    return res;
  }

}
