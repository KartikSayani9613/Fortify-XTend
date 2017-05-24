package ChessGame;

import ChessGame.Board;
import ChessGame.Color;
import ChessGame.Location;
import ChessGame.Piece;
import org.eclipse.xtext.xbase.lib.IntegerRange;

@SuppressWarnings("all")
public class Bishop extends Piece {
  public Bishop(final Color color, final Location loc1) {
    this.color = color;
    this.curLoc = loc1;
    this.initLoc = loc1;
  }
  
  @Override
  protected boolean isValid(final Location source, final Location destination) {
    int sx = source.getX();
    int sy = source.getY();
    int dx = destination.getX();
    int dy = destination.getY();
    if (((sx - dx) != (sy - dy))) {
      return false;
    } else {
      IntegerRange _upTo = new IntegerRange((sx + 1), (dx - 1));
      for (final Integer i : _upTo) {
        IntegerRange _upTo_1 = new IntegerRange((sy + 1), (dy - 1));
        for (final Integer j : _upTo_1) {
          Location _location = new Location((i).intValue(), (j).intValue());
          Piece _onBoard = Board.onBoard(_location);
          boolean _tripleNotEquals = (_onBoard != null);
          if (_tripleNotEquals) {
            return false;
          }
        }
      }
    }
    return true;
  }
}
