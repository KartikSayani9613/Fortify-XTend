package ChessGame;

import ChessGame.Board;
import ChessGame.Color;
import ChessGame.Location;
import ChessGame.Piece;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IntegerRange;

@SuppressWarnings("all")
public class Rook extends Piece {
  public Rook(final Color color, final Location loc1) {
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
    if ((sx != dx)) {
      if ((sy != dy)) {
        InputOutput.<String>println("False here");
        return false;
      }
    }
    if ((sx == dx)) {
      IntegerRange _upTo = new IntegerRange((sy + 1), (dy - 1));
      for (final Integer i : _upTo) {
        Location _location = new Location(sx, (i).intValue());
        Piece _onBoard = Board.onBoard(_location);
        boolean _tripleNotEquals = (_onBoard != null);
        if (_tripleNotEquals) {
          return false;
        }
      }
    }
    if ((sy == dy)) {
      IntegerRange _upTo_1 = new IntegerRange((sx + 1), (dx - 1));
      for (final Integer i_1 : _upTo_1) {
        Location _location_1 = new Location((i_1).intValue(), sy);
        Piece _onBoard_1 = Board.onBoard(_location_1);
        boolean _tripleNotEquals_1 = (_onBoard_1 != null);
        if (_tripleNotEquals_1) {
          return false;
        }
      }
    }
    return true;
  }
}
