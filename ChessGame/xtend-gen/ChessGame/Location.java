package ChessGame;

import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.Pure;

@SuppressWarnings("all")
public class Location {
  @Accessors
  private int x;
  
  @Accessors
  private int y;
  
  public Location(final int x, final int y) {
    this.x = x;
    this.y = y;
  }
  
  protected boolean isWithinLimits() {
    if ((new IntegerRange(0, 7).contains(this.x) && new IntegerRange(0, 7).contains(this.y))) {
      return true;
    } else {
      InputOutput.<String>println("Given locations are invalid. Try again.");
      return false;
    }
  }
  
  @Pure
  public int getX() {
    return this.x;
  }
  
  public void setX(final int x) {
    this.x = x;
  }
  
  @Pure
  public int getY() {
    return this.y;
  }
  
  public void setY(final int y) {
    this.y = y;
  }
}
