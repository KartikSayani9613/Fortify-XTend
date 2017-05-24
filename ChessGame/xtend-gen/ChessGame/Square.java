package ChessGame;

import ChessGame.Piece;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class Square {
  @Accessors
  public Piece piece;
  
  @Pure
  public Piece getPiece() {
    return this.piece;
  }
  
  public void setPiece(final Piece piece) {
    this.piece = piece;
  }
}
