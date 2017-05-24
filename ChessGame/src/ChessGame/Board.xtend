package ChessGame

import java.util.ArrayList
import java.util.Scanner
import static extension java.lang.Integer.parseInt

class Board {
	var static board = new ArrayList<ArrayList<Square>>
	var static Player1 = new Player(Color.WHITE)
	var static Player2 = new Player(Color.BLACK)
	var static WRook = new ArrayList<Rook>
	var static BRook = new ArrayList<Rook>
	var static WKnight = new ArrayList<Knight>
	var static BKnight = new ArrayList<Knight>
	var static WBishop = new ArrayList<Bishop>
	var static BBishop = new ArrayList<Bishop>
	var static WPawns = new ArrayList<Pawn>
	var static BPawns = new ArrayList<Pawn>
	var static King WKing
	var static King BKing
	var static Queen WQueen
	var static Queen BQueen

	def static move(Location s, Location d) {
		
		board.get(d.x).get(d.y).piece = s.onBoard
		s.onBoard.isAlive = false
		board.get(s.x).get(s.y).piece = null
		d.onBoard.curLoc = d
		return true
	}
	
	def static onBoard(Location l){
		return board.get(l.x).get(l.y).piece
	}

	def static checkOnDestination(Player p, Location d){
		if(d.onBoard === null)
			return true
		if(d.onBoard.color != p.getcolor)
			return true
		else
			return false
	}
	
	def static checkOnSource(Player p,Location s) {
		if( s.onBoard === null || s.onBoard.color !== p.getcolor)
			return null
		else
			return s.onBoard
	}

	def static void turn(Player p) {
		println("Player " + p.getcolor + " makes the move")
		var Scanner inp = new Scanner(System.in)
		println("Enter 'Source Destination'")
		var locs = inp.nextLine().replace(" ", "").split("").toList.map[i|i.parseInt]
		var source = new Location(locs.get(0), locs.get(1))
		var destination = new Location(locs.get(2), locs.get(3))
		if(!source.isWithinLimits || !destination.isWithinLimits)
			p.turn
		var Piece pieceOnSquare = p.checkOnSource(source)
		if (pieceOnSquare === null) {
			println("Nothing on selected square")
			p.turn
		} else {
			println(pieceOnSquare.class.simpleName+" selected")
			if(!p.checkOnDestination(destination)){
				println("Same colored piece on Destination. Try again.")
				p.turn	
			}
			if(pieceOnSquare.isValid(source, destination)){
				move(source, destination)
//				println(pieceOnSquare.isAlive)		
//				println(destination.onBoard.curLoc.x+" "+destination.onBoard.curLoc.y)		
			}
			else{
				println("Invalid move for this piece. Try again")
				p.turn
			}
		}
	}

	def static void main(String[] args) {
		 
		 
		 
		// Instantiate Pieces
		(0 .. 1).forEach [ i |
			WRook.add(new Rook(Color.WHITE, new Location(0, 0 + 7 * i)))
			WKnight.add(new Knight(Color.WHITE, new Location(0, 1 + 5 * i)))
			WBishop.add(new Bishop(Color.WHITE, new Location(0, 2 + 3 * i)))

			BRook.add(new Rook(Color.BLACK, new Location(7, 0 + 7 * i)))
			BKnight.add(new Knight(Color.BLACK, new Location(7, 1 + 5 * i)))
			BBishop.add(new Bishop(Color.BLACK, new Location(7, 2 + 3 * i)))
		]

		(0 .. 7).forEach [ i |
			WPawns.add(new Pawn(Color.WHITE, new Location(1, i)))
			BPawns.add(new Pawn(Color.BLACK, new Location(6, 7 * i)))
		]
		WKing = new King(Color.WHITE, new Location(0, 4))
		WQueen = new Queen(Color.WHITE, new Location(0, 3))
		BKing = new King(Color.BLACK, new Location(7, 4))
		BQueen = new Queen(Color.BLACK, new Location(7, 3))

		// Initialize the board with empty pieces
		(0 .. 7).forEach [ i |
			board.add(new ArrayList)
			(0 .. 7).forEach [ j |
				board.get(i).add(new Square)
				var Location l = new Location(i,j)
				switch l{
					case l.x==0 && l.y==0:	board.get(i).get(j).piece = WRook.get(0)
					case l.x==0 && l.y==1:	board.get(i).get(j).piece = WKnight.get(0)
					case l.x==0 && l.y==2:	board.get(i).get(j).piece = WBishop.get(0)
					case l.x==0 && l.y==3:	board.get(i).get(j).piece = WQueen
					case l.x==0 && l.y==4:	board.get(i).get(j).piece = WKing
					case l.x==0 && l.y==5:	board.get(i).get(j).piece = WBishop.get(1)
					case l.x==0 && l.y==6:	board.get(i).get(j).piece = WKnight.get(1)
					case l.x==0 && l.y==7:	board.get(i).get(j).piece = WRook.get(1)
					case l.x==7 && l.y==0:	board.get(i).get(j).piece = BRook.get(0)
					case l.x==7 && l.y==1:	board.get(i).get(j).piece = BKnight.get(0)
					case l.x==7 && l.y==2:	board.get(i).get(j).piece = BBishop.get(0)
					case l.x==7 && l.y==3:	board.get(i).get(j).piece = BQueen
					case l.x==7 && l.y==4:	board.get(i).get(j).piece = BKing
					case l.x==7 && l.y==5:	board.get(i).get(j).piece = BBishop.get(1)
					case l.x==7 && l.y==6:	board.get(i).get(j).piece = BKnight.get(1)
					case l.x==7 && l.y==7:	board.get(i).get(j).piece = BRook.get(1)
//					case l.x==1 && l.y==0:	board.get(i).get(j).piece = WPawns.get(0)
					case l.x==1 && l.y==1:	board.get(i).get(j).piece = WPawns.get(1)
					case l.x==1 && l.y==2:	board.get(i).get(j).piece = WPawns.get(2)
					case l.x==1 && l.y==3:	board.get(i).get(j).piece = WPawns.get(3)
					case l.x==1 && l.y==4:	board.get(i).get(j).piece = WPawns.get(4)
					case l.x==1 && l.y==5:	board.get(i).get(j).piece = WPawns.get(5)
					case l.x==1 && l.y==6:	board.get(i).get(j).piece = WPawns.get(6)
					case l.x==1 && l.y==7:	board.get(i).get(j).piece = WPawns.get(7)
					case l.x==7 && l.y==0:	board.get(i).get(j).piece = BPawns.get(0)
					case l.x==7 && l.y==1:	board.get(i).get(j).piece = BPawns.get(1)
					case l.x==7 && l.y==2:	board.get(i).get(j).piece = BPawns.get(2)
					case l.x==7 && l.y==3:	board.get(i).get(j).piece = BPawns.get(3)
					case l.x==7 && l.y==4:	board.get(i).get(j).piece = BPawns.get(4)
					case l.x==7 && l.y==5:	board.get(i).get(j).piece = BPawns.get(5)
					case l.x==7 && l.y==6:	board.get(i).get(j).piece = BPawns.get(6)
					case l.x==7 && l.y==7:	board.get(i).get(j).piece = BPawns.get(7) 			
				}
				 
			]
		]

		var Instructions = '''
			INSTRUCTIONS
				The Board is numbered 0 to 7 from left to Right and 0 to 7 from bottom to top.
				So the leftmost square of the bottom most row is (0,0)
				Castling is currently not supported
			'''
		println(Instructions)

		while (true) {
			Player1.turn
			Player2.turn
		}

	}

}
