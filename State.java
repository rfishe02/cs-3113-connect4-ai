
public class State {
  char[][] board;
  int row;
  int col;
  int v;

  public State(char[][] state, int row, int col, int v) {
    board = state;
    this.row = row;
    this.col = col;
    this.v = v;
  }

  public void setValues(char[][] board, int row, int col, int v) {
    this.board = board;
    this.row = row;
    this.col = col;
    this.v = v;
  }

  public void setValues(char[][] board, int v) {
    this.board = board;
    this.v = v;
  }

}
