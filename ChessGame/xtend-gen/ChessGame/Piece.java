package ChessGame;

import ChessGame.Color;
import ChessGame.Location;

@SuppressWarnings("all")
public abstract class Piece {
  protected boolean isAlive = true;
  
  protected Color color;
  
  protected Location curLoc;
  
  protected Location initLoc;
  
  protected abstract boolean isValid(final Location source, final Location destination);
}
