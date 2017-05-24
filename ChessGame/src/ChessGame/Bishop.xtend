package ChessGame

class Bishop extends Piece{
	
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
		if(sx-dx != sy-dy)
			return false
		else{
			for(i: (sx+1..dx-1)){
				for(j: (sy+1..dy-1)){
					if(Board.onBoard(new Location(i,j)) !== null)
						return false
				}
			}
		}
		return true
	}
	
}