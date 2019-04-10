
// Author: Renae Fisher
// Class: CS 3113
// Assignment: Connect 4
// Date: 4-9-19
// Desc: Holds the methods the AI uses to think.

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

  public int getUtility(State s, char c) {
    return 0;
  }

  //============================================================================

  public boolean utilityCheck(State s, int depth) {
    return true;
  }

  //============================================================================
  // Use this method to generate potential states.

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
  // This is the start of the alpha beta search algorithm.

  public int alphaBetaSearch(State start) {
    int depth = 0;
    State s = maxValue(start, Integer.MIN_VALUE, Integer.MAX_VALUE,depth);
    System.out.println();

    if(s.board == null) {
      return -1;
    } else {
      return s.col;
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

    State max = new State(null,0,0,Integer.MIN_VALUE);
    State call;
    State reply;

    // Create an array of choices. As we make choices, swap them with the last
    // element and decrement the end.

    int[] choices = {0,0,1,2,3,4,5,6};
    int end = choices.length;
    int tmp;
    int i = 0;

    while(end > 0) {
      i = rand.nextInt(end);

      if(s.board[0][choices[i]] == '\u0000') {

        call = testMove(s.board,choices[i],Status.c); // Return the call, rather than the response.
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

      tmp = choices[end-1];
      choices[end-1] = choices[i];
      choices[i] = tmp;
      end--;
    }

    if(max.board == null) {
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

    State max = new State(null,0,0,Integer.MIN_VALUE);
    State reply;

    int[] choices = {0,0,1,2,3,4,5,6};
    int end = choices.length;
    int tmp;
    int i = 0;

    while(end > 0) {
      i = rand.nextInt(end);

      if(s.board[0][choices[i]] == '\u0000') {

        reply = maxValue(testMove(s.board,choices[i],Status.p),alpha,beta,depth+1);

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

      tmp = choices[end-1];
      choices[end-1] = choices[i];
      choices[i] = tmp;
      end--;
    }

    if(max.board == null) {
      return s;
    } else {
      return max;
    }
  }

}
