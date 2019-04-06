
import java.util.HashSet;
import java.util.Random;

public class AI {

  Random rand;
  int moves;

  public AI(int moves) {
    rand = new Random();
    this.moves = moves;
  }

  //============================================================================
  // For the default utility, the AI uses the number of consecutive tiles.
  // We want a scenario in which the player is really good, we'll reverse
  // the count, to make it a smaller value that min would prefer.

  public int getUtility(State s, char c) {
    if(c == 'P') {
      return Problem.maxCount(s.board,s.row,s.col,c) * -1;
    } else {
      return Problem.maxCount(s.board,s.row,s.col,c);
    }
  }

  //============================================================================
  // The state is a terminal state if a player wins, or if it exceeds depth.

  public boolean utilityCheck(State s, int depth) {
    if(s.v < 0) {
      return s.v < -3 || depth > 5;
    } else {
      return s.v > 3 || depth > 5;
    }
  }

  //============================================================================
  // Make a deepy copy of the board and create a new state, with a utility.

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
    s.v = getUtility(s, c);
    return s;
  }

  //============================================================================

  public char[][] deepCopy(char[][] board) {
    char[][] copy = new char[board.length][board[0].length];

    for(int row = 0; row < board.length; row++) {
      for(int col = 0; col < board[0].length; col++) {
        copy[row][col] = board[row][col];
      }
    }

    return copy;
  }

  //============================================================================
  // This is the alpha beta search algorithm.

  public int alphaBetaSearch(State start) {
    int depth = 0;
    State s = maxValue(start, Integer.MIN_VALUE, Integer.MAX_VALUE,depth);

    //Problem.printBoard(s.board);
    //System.out.println(s.v);

    return s.col;
  }

  //============================================================================
  // Choose an action at random, to avoid PCPCPC scenarios, which are fruitless.
  // For max, we return the call rather than the response. We want the
  // action that caused the state, rather than the response state.

  public State maxValue(State s, int alpha, int beta, int depth) {

    if(utilityCheck(s,depth)) {
      return s;
    }

    HashSet<Integer> explored = new HashSet<>();
    State max = new State(null,0,0,Integer.MIN_VALUE);
    State call;
    State reply;
    int i = rand.nextInt(moves);

    while(explored.size() < moves) {

      if(!explored.contains(i) && s.board[0][i] == '\u0000') {

        call = testMove(s.board,i,'C'); // Avoid returning the response move.
        reply = minValue(call,alpha,beta,depth+1);

        if(reply.v >= beta) {
          call.setValues(reply.board,reply.v);
          return call;
        }

        if(reply.v > alpha) {
          alpha = reply.v;
        }

        if(reply.v > max.v) {
          max.setValues(reply.board,call.row,call.col,reply.v);
        }

      }
      explored.add(i);
      i = rand.nextInt(moves);
    }

    return max;
  }

  //============================================================================

  public State minValue(State s, int alpha, int beta, int depth) {

    if(utilityCheck(s,depth)) {
      return s;
    }

    HashSet<Integer> explored = new HashSet<>();
    State max = new State(null,0,0,Integer.MAX_VALUE);
    State reply;
    int i = rand.nextInt(moves);

    while(explored.size() < moves) {

      if(!explored.contains(i) && s.board[0][i] == '\u0000') {

        reply = maxValue(testMove(s.board,i,'P'),alpha,beta,depth+1);

        if(reply.v <= alpha) {
          return reply;
        }

        if(reply.v < beta) {
          beta = reply.v;
        }

        if(reply.v < max.v) {
          max.setValues(reply.board,reply.row,reply.col,reply.v);
        }

      }

      explored.add(i);
      i = rand.nextInt(moves);
    }

    return max;
  }

}
