
// Author: Renae Fisher
// Class: CS 3113
// Assignment: Connect 4
// Date: 4-9-19
// Desc: Overides getUtility and utilityCheck of the AI class.
// The AI makes moves that reduce the number of connected tiles for the player,
// or moves that maximize its number of connected tiles.

public class FirstUtility extends AI {

  public FirstUtility(int moves, char play, char opp) {
    super(moves,play,opp);
  }

  //============================================================================
  // For the first utility, the AI uses the number of consecutive tiles.
  // We want a scenario in which the opponent is really good, so we reverse
  // the count for the player. It becomes a smaller value that min would prefer.

  public int getUtility(State s, char c) {
    if(c == opp) {
      return Status.maxCount(s.board,s.row,s.col,c) * -1;
    } else {
      return Status.maxCount(s.board,s.row,s.col,c);
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
