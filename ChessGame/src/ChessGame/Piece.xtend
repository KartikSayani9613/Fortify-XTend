package ChessGame

abstract class Piece {
	var protected boolean isAlive = true
	var protected Color color
	var protected Location curLoc
	var protected Location initLoc
	def abstract protected boolean isValid(Location source, Location destination)
	
}