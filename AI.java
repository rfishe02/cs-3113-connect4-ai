
// Author: Renae Fisher
// Class: CS 3113
// Assignment: Connect 4
// Date: 4-9-19
// Desc: Holds the methods the AI uses to think.

import java.util.Random;

public class AI {

  Random rand;
  int moves;
  char p;
  char o;

  public AI(int moves, char p, char o) {
    rand = new Random();
    this.moves = moves;
    this.p = p;
    this.o = o;
  }

  //============================================================================

  public void setUtility(State s, char c) {

  }

  //============================================================================

  public boolean utilityCheck(State s, int depth) {
    return true;
  }

  //============================================================================
  // This is the start of the alpha beta search algorithm.

  public int alphaBetaSearch(char[][] board) {
    int depth = 0;
    State s = new State(board,0,0,0);
    State x = maxValue(s, Integer.MIN_VALUE, Integer.MAX_VALUE, depth);

    if(x == null) {
      return -1; // Will flag the moveCheck
    } else {
      return x.col;
    }

  }

  //============================================================================
  // Choose an action at random, to avoid PCPCPC scenarios, which are fruitless.
  // For max, we return the call action rather than the response action.
  // We want the action that caused the state, rather than the response state.

  public State maxValue(State s, int alpha, int beta, int depth) {
    if(utilityCheck(s,depth)) {
      return s;
    }

    State max = null;
    State call;
    State reply;

    // Create an array of choices. As we make choices, swap them with the last
    // element and decrement the end.

    int[] choices = {0,0,1,2,3,4,5,6};
    int end = choices.length;
    int tmp;
    int x = 0;

    while(end > 0) {
      x = rand.nextInt(end);

      if(s.board[0][choices[x]] == '\u0000') {

        call = simMove(s.board,choices[x],p);
        reply = minValue(call,alpha,beta,depth+1);

        if(max == null) {
          max = new State(null,call.row,call.col,reply.v);
        }
        if(reply.v > max.v) {
          max.setValues(null,call.row,call.col,reply.v);
        }

        if(reply.v >= beta) {
          return max; // Return the call from max, rather than the response.
        }

        if(reply.v > alpha) {
          alpha = reply.v;
        }

      }
      tmp = choices[end-1];
      choices[end-1] = choices[x];
      choices[x] = tmp;
      end--;
    }

    if(max == null) {
      return s;
    } else {
      return max;
    }

  }

  //============================================================================

  public State minValue(State s, int alpha, int beta, int depth) {
    if(utilityCheck(s,depth)) {
      return s;
    }

    State max = null;
    State reply;

    int[] choices = {0,0,1,2,3,4,5,6};
    int end = choices.length;
    int tmp;
    int x = 0;

    while(end > 0) {
      x = rand.nextInt(end);

      if(s.board[0][choices[x]] == '\u0000') {

        reply = maxValue(simMove(s.board,choices[x],o),alpha,beta,depth+1);

        if(max == null) {
          max = new State(null,reply.row,reply.col,reply.v);
        }
        if(reply.v < max.v) {
          max.setValues(null,reply.row,reply.col,reply.v);
        }

        if(reply.v <= alpha) {
          return max;
        }

        if(reply.v < beta) {
          beta = reply.v;
        }

      }
      tmp = choices[end-1];
      choices[end-1] = choices[x];
      choices[x] = tmp;
      end--;
    }

    if(max == null) {
      return s;
    } else {
      return max;
    }
  }

  //============================================================================
  // Use this method to generate potential states.

  public State simMove(char[][] state, int col, char c) {
    char[][] board = deepCopy(state);
    int row;

    for(row = board.length-1; row >= 0; row--) {
      if(board[row][col] == '\u0000') {
        board[row][col] = c;
        break;
      }
    }

    State s = new State(board,row,col);
    setUtility(s, c);
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

}
