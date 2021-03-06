package ChessGame;

import ChessGame.Color;
import ChessGame.Location;
import ChessGame.Piece;

@SuppressWarnings("all")
public class Queen extends Piece {
  public Queen(final Color color, final Location loc1) {
    this.color = color;
    this.curLoc = loc1;
    this.initLoc = loc1;
  }
  
  @Override
  protected boolean isValid(final Location source, final Location destination) {
    return true;
  }
}
