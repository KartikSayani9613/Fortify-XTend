package ChessGame

import org.eclipse.xtend.lib.annotations.Accessors

 class Location{
	@Accessors private int x
	@Accessors private int y
	new(int x, int y){
		this.x = x
		this.y = y
	}
	def protected boolean isWithinLimits(){
		if((0..7).contains(x) && (0..7).contains(y))
			return true
		else{
			println("Given locations are invalid. Try again.")
			return false			
		}
	}
	
}