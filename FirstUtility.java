
public class FirstUtility extends AI {

  public FirstUtility(int moves) {
    super(moves);
  }

  //============================================================================
  // For the default utility, the AI uses the number of consecutive tiles.
  // We want a scenario in which the opponent is really good, so we reverse
  // the count for the player. It becomes a smaller value that min would prefer.

  public int getUtility(State s, char c) {
    if(c == Problem.p) {
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

}
