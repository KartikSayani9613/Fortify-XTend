package ChessGame

class Knight extends Piece{
	
	new(Color color, Location loc1){
		this.color = color
		this.curLoc = loc1
		this.initLoc = loc1
	}
	override protected isValid(Location source, Location destination) {
		return true
	}
	
}