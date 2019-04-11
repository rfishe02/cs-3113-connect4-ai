
// Author: Renae Fisher
// Class: CS 3113
// Assignment: Connect 4
// Date: 4-9-19
// Desc: The class used to pass information in alphaBetaSearch.

public class State {
  char[][] board;
  int row;
  int col;
  int v; // The weighted value.

  int base; // The base value, without weight.

  public State(char[][] state, int row, int col) {
    board = state;
    this.row = row;
    this.col = col;
  }

  public State(char[][] state, int row, int col, int v) {
    board = state;
    this.row = row;
    this.col = col;
    this.v = v;
  }

  public void setValues(char[][] board, int v) {
    this.board = board;
    this.v = v;
  }

  public void setValues(char[][] board, int row, int col, int v) {
    this.board = board;
    this.row = row;
    this.col = col;
    this.v = v;
  }

}
