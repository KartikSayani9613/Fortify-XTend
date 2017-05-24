package ChessGame;

import ChessGame.Color;

@SuppressWarnings("all")
public class Player {
  private Color color;
  
  public Player(final Color c) {
    this.color = c;
  }
  
  public Color getcolor() {
    return this.color;
  }
}
