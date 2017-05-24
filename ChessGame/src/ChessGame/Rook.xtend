package ChessGame

class Rook extends Piece{
	
	new(Color color, Location loc1){
		this.color = color
		this.curLoc = loc1
		this.initLoc = loc1
	}
	override protected isValid(Location source, Location destination) {
		var int sx = source.x
		var int sy = source.y
		var int dx = destination.x
		var int dy = destination.y
		if(sx != dx)
			if(sy != dy){
				println("False here")
				return false
			}
		if(sx==dx){
			for(i: (sy+1..dy-1)){
				if(Board.onBoard(new Location(sx,i)) !== null)
					return false
			}
		}
		if(sy==dy){
			for(i: (sx+1..dx-1)){
				if(Board.onBoard(new Location(i,sy)) !== null)
					return false
			}
		}
		return true 
	}
	
}