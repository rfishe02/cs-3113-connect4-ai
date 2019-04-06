
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

}
