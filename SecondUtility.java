
// Author: Renae Fisher
// Class: CS 3113
// Assignment: Connect 4
// Date: 4-9-19
// Desc: Overides getUtility and utilityCheck of the AI class.

public class SecondUtility extends AI {

  public SecondUtility(int moves, char play, char opp) {
    super(moves,play,opp);
  }

  //============================================================================
  // For the first utility, the AI uses the number of consecutive tiles.
  // We want a scenario in which the opponent is really good, so we reverse
  // the count for the player. It becomes a smaller value that min would prefer.

  public int getUtility(State s, char c) {

    int res = Status.maxCount(s.board,s.row,s.col,c);

    if(res == 3) {
      s.weight= 3;
    } else if(res == 2 || res == 4) {
      s.weight= 2;
    } else if(res == 1 || res == 5) {
      s.weight= 1;
    } else {
      s.weight= 0;
    }

    if(c == o) {
      s.weight = s.weight * -1;
      return res * -1;
    }

    return res;
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
