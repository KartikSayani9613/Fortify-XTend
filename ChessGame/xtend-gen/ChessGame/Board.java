package ChessGame;

import ChessGame.Bishop;
import ChessGame.Color;
import ChessGame.King;
import ChessGame.Knight;
import ChessGame.Location;
import ChessGame.Pawn;
import ChessGame.Piece;
import ChessGame.Player;
import ChessGame.Queen;
import ChessGame.Rook;
import ChessGame.Square;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;

@SuppressWarnings("all")
public class Board {
  private static ArrayList<ArrayList<Square>> board = new ArrayList<ArrayList<Square>>();
  
  private static Player Player1 = new Player(Color.WHITE);
  
  private static Player Player2 = new Player(Color.BLACK);
  
  private static ArrayList<Rook> WRook = new ArrayList<Rook>();
  
  private static ArrayList<Rook> BRook = new ArrayList<Rook>();
  
  private static ArrayList<Knight> WKnight = new ArrayList<Knight>();
  
  private static ArrayList<Knight> BKnight = new ArrayList<Knight>();
  
  private static ArrayList<Bishop> WBishop = new ArrayList<Bishop>();
  
  private static ArrayList<Bishop> BBishop = new ArrayList<Bishop>();
  
  private static ArrayList<Pawn> WPawns = new ArrayList<Pawn>();
  
  private static ArrayList<Pawn> BPawns = new ArrayList<Pawn>();
  
  private static King WKing;
  
  private static King BKing;
  
  private static Queen WQueen;
  
  private static Queen BQueen;
  
  public static boolean move(final Location s, final Location d) {
    Square _get = Board.board.get(d.getX()).get(d.getY());
    _get.piece = Board.onBoard(s);
    Piece _onBoard = Board.onBoard(s);
    _onBoard.isAlive = false;
    Square _get_1 = Board.board.get(s.getX()).get(s.getY());
    _get_1.piece = null;
    Piece _onBoard_1 = Board.onBoard(d);
    _onBoard_1.curLoc = d;
    return true;
  }
  
  public static Piece onBoard(final Location l) {
    return Board.board.get(l.getX()).get(l.getY()).piece;
  }
  
  public static boolean checkOnDestination(final Player p, final Location d) {
    Piece _onBoard = Board.onBoard(d);
    boolean _tripleEquals = (_onBoard == null);
    if (_tripleEquals) {
      return true;
    }
    Color _color = p.getcolor();
    boolean _notEquals = (!Objects.equal(Board.onBoard(d).color, _color));
    if (_notEquals) {
      return true;
    } else {
      return false;
    }
  }
  
  public static Piece checkOnSource(final Player p, final Location s) {
    if (((Board.onBoard(s) == null) || (Board.onBoard(s).color != p.getcolor()))) {
      return null;
    } else {
      return Board.onBoard(s);
    }
  }
  
  public static void turn(final Player p) {
    Color _color = p.getcolor();
    String _plus = ("Player " + _color);
    String _plus_1 = (_plus + " makes the move");
    InputOutput.<String>println(_plus_1);
    Scanner inp = new Scanner(System.in);
    InputOutput.<String>println("Enter \'Source Destination\'");
    final Function1<String, Integer> _function = (String i) -> {
      return Integer.valueOf(Integer.parseInt(i));
    };
    List<Integer> locs = ListExtensions.<String, Integer>map(IterableExtensions.<String>toList(((Iterable<String>)Conversions.doWrapArray(inp.nextLine().replace(" ", "").split("")))), _function);
    Integer _get = locs.get(0);
    Integer _get_1 = locs.get(1);
    Location source = new Location((_get).intValue(), (_get_1).intValue());
    Integer _get_2 = locs.get(2);
    Integer _get_3 = locs.get(3);
    Location destination = new Location((_get_2).intValue(), (_get_3).intValue());
    if (((!source.isWithinLimits()) || (!destination.isWithinLimits()))) {
      Board.turn(p);
    }
    Piece pieceOnSquare = Board.checkOnSource(p, source);
    if ((pieceOnSquare == null)) {
      InputOutput.<String>println("Nothing on selected square");
      Board.turn(p);
    } else {
      String _simpleName = pieceOnSquare.getClass().getSimpleName();
      String _plus_2 = (_simpleName + " selected");
      InputOutput.<String>println(_plus_2);
      boolean _checkOnDestination = Board.checkOnDestination(p, destination);
      boolean _not = (!_checkOnDestination);
      if (_not) {
        InputOutput.<String>println("Same colored piece on Destination. Try again.");
        Board.turn(p);
      }
      boolean _isValid = pieceOnSquare.isValid(source, destination);
      if (_isValid) {
        Board.move(source, destination);
      } else {
        InputOutput.<String>println("Invalid move for this piece. Try again");
        Board.turn(p);
      }
    }
  }
  
  public static void main(final String[] args) {
    final Consumer<Integer> _function = (Integer i) -> {
      Location _location = new Location(0, (0 + (7 * (i).intValue())));
      Rook _rook = new Rook(Color.WHITE, _location);
      Board.WRook.add(_rook);
      Location _location_1 = new Location(0, (1 + (5 * (i).intValue())));
      Knight _knight = new Knight(Color.WHITE, _location_1);
      Board.WKnight.add(_knight);
      Location _location_2 = new Location(0, (2 + (3 * (i).intValue())));
      Bishop _bishop = new Bishop(Color.WHITE, _location_2);
      Board.WBishop.add(_bishop);
      Location _location_3 = new Location(7, (0 + (7 * (i).intValue())));
      Rook _rook_1 = new Rook(Color.BLACK, _location_3);
      Board.BRook.add(_rook_1);
      Location _location_4 = new Location(7, (1 + (5 * (i).intValue())));
      Knight _knight_1 = new Knight(Color.BLACK, _location_4);
      Board.BKnight.add(_knight_1);
      Location _location_5 = new Location(7, (2 + (3 * (i).intValue())));
      Bishop _bishop_1 = new Bishop(Color.BLACK, _location_5);
      Board.BBishop.add(_bishop_1);
    };
    new IntegerRange(0, 1).forEach(_function);
    final Consumer<Integer> _function_1 = (Integer i) -> {
      Location _location = new Location(1, (i).intValue());
      Pawn _pawn = new Pawn(Color.WHITE, _location);
      Board.WPawns.add(_pawn);
      Location _location_1 = new Location(6, (7 * (i).intValue()));
      Pawn _pawn_1 = new Pawn(Color.BLACK, _location_1);
      Board.BPawns.add(_pawn_1);
    };
    new IntegerRange(0, 7).forEach(_function_1);
    Location _location = new Location(0, 4);
    King _king = new King(Color.WHITE, _location);
    Board.WKing = _king;
    Location _location_1 = new Location(0, 3);
    Queen _queen = new Queen(Color.WHITE, _location_1);
    Board.WQueen = _queen;
    Location _location_2 = new Location(7, 4);
    King _king_1 = new King(Color.BLACK, _location_2);
    Board.BKing = _king_1;
    Location _location_3 = new Location(7, 3);
    Queen _queen_1 = new Queen(Color.BLACK, _location_3);
    Board.BQueen = _queen_1;
    final Consumer<Integer> _function_2 = (Integer i) -> {
      ArrayList<Square> _arrayList = new ArrayList<Square>();
      Board.board.add(_arrayList);
      final Consumer<Integer> _function_3 = (Integer j) -> {
        ArrayList<Square> _get = Board.board.get((i).intValue());
        Square _square = new Square();
        _get.add(_square);
        Location l = new Location((i).intValue(), (j).intValue());
        boolean _matched = false;
        if (((l.getX() == 0) && (l.getY() == 0))) {
          _matched=true;
          Square _get_1 = Board.board.get((i).intValue()).get((j).intValue());
          _get_1.piece = Board.WRook.get(0);
        }
        if (!_matched) {
          if (((l.getX() == 0) && (l.getY() == 1))) {
            _matched=true;
            Square _get_2 = Board.board.get((i).intValue()).get((j).intValue());
            _get_2.piece = Board.WKnight.get(0);
          }
        }
        if (!_matched) {
          if (((l.getX() == 0) && (l.getY() == 2))) {
            _matched=true;
            Square _get_3 = Board.board.get((i).intValue()).get((j).intValue());
            _get_3.piece = Board.WBishop.get(0);
          }
        }
        if (!_matched) {
          if (((l.getX() == 0) && (l.getY() == 3))) {
            _matched=true;
            Square _get_4 = Board.board.get((i).intValue()).get((j).intValue());
            _get_4.piece = Board.WQueen;
          }
        }
        if (!_matched) {
          if (((l.getX() == 0) && (l.getY() == 4))) {
            _matched=true;
            Square _get_5 = Board.board.get((i).intValue()).get((j).intValue());
            _get_5.piece = Board.WKing;
          }
        }
        if (!_matched) {
          if (((l.getX() == 0) && (l.getY() == 5))) {
            _matched=true;
            Square _get_6 = Board.board.get((i).intValue()).get((j).intValue());
            _get_6.piece = Board.WBishop.get(1);
          }
        }
        if (!_matched) {
          if (((l.getX() == 0) && (l.getY() == 6))) {
            _matched=true;
            Square _get_7 = Board.board.get((i).intValue()).get((j).intValue());
            _get_7.piece = Board.WKnight.get(1);
          }
        }
        if (!_matched) {
          if (((l.getX() == 0) && (l.getY() == 7))) {
            _matched=true;
            Square _get_8 = Board.board.get((i).intValue()).get((j).intValue());
            _get_8.piece = Board.WRook.get(1);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 0))) {
            _matched=true;
            Square _get_9 = Board.board.get((i).intValue()).get((j).intValue());
            _get_9.piece = Board.BRook.get(0);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 1))) {
            _matched=true;
            Square _get_10 = Board.board.get((i).intValue()).get((j).intValue());
            _get_10.piece = Board.BKnight.get(0);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 2))) {
            _matched=true;
            Square _get_11 = Board.board.get((i).intValue()).get((j).intValue());
            _get_11.piece = Board.BBishop.get(0);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 3))) {
            _matched=true;
            Square _get_12 = Board.board.get((i).intValue()).get((j).intValue());
            _get_12.piece = Board.BQueen;
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 4))) {
            _matched=true;
            Square _get_13 = Board.board.get((i).intValue()).get((j).intValue());
            _get_13.piece = Board.BKing;
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 5))) {
            _matched=true;
            Square _get_14 = Board.board.get((i).intValue()).get((j).intValue());
            _get_14.piece = Board.BBishop.get(1);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 6))) {
            _matched=true;
            Square _get_15 = Board.board.get((i).intValue()).get((j).intValue());
            _get_15.piece = Board.BKnight.get(1);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 7))) {
            _matched=true;
            Square _get_16 = Board.board.get((i).intValue()).get((j).intValue());
            _get_16.piece = Board.BRook.get(1);
          }
        }
        if (!_matched) {
          if (((l.getX() == 1) && (l.getY() == 1))) {
            _matched=true;
            Square _get_17 = Board.board.get((i).intValue()).get((j).intValue());
            _get_17.piece = Board.WPawns.get(1);
          }
        }
        if (!_matched) {
          if (((l.getX() == 1) && (l.getY() == 2))) {
            _matched=true;
            Square _get_18 = Board.board.get((i).intValue()).get((j).intValue());
            _get_18.piece = Board.WPawns.get(2);
          }
        }
        if (!_matched) {
          if (((l.getX() == 1) && (l.getY() == 3))) {
            _matched=true;
            Square _get_19 = Board.board.get((i).intValue()).get((j).intValue());
            _get_19.piece = Board.WPawns.get(3);
          }
        }
        if (!_matched) {
          if (((l.getX() == 1) && (l.getY() == 4))) {
            _matched=true;
            Square _get_20 = Board.board.get((i).intValue()).get((j).intValue());
            _get_20.piece = Board.WPawns.get(4);
          }
        }
        if (!_matched) {
          if (((l.getX() == 1) && (l.getY() == 5))) {
            _matched=true;
            Square _get_21 = Board.board.get((i).intValue()).get((j).intValue());
            _get_21.piece = Board.WPawns.get(5);
          }
        }
        if (!_matched) {
          if (((l.getX() == 1) && (l.getY() == 6))) {
            _matched=true;
            Square _get_22 = Board.board.get((i).intValue()).get((j).intValue());
            _get_22.piece = Board.WPawns.get(6);
          }
        }
        if (!_matched) {
          if (((l.getX() == 1) && (l.getY() == 7))) {
            _matched=true;
            Square _get_23 = Board.board.get((i).intValue()).get((j).intValue());
            _get_23.piece = Board.WPawns.get(7);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 0))) {
            _matched=true;
            Square _get_24 = Board.board.get((i).intValue()).get((j).intValue());
            _get_24.piece = Board.BPawns.get(0);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 1))) {
            _matched=true;
            Square _get_25 = Board.board.get((i).intValue()).get((j).intValue());
            _get_25.piece = Board.BPawns.get(1);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 2))) {
            _matched=true;
            Square _get_26 = Board.board.get((i).intValue()).get((j).intValue());
            _get_26.piece = Board.BPawns.get(2);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 3))) {
            _matched=true;
            Square _get_27 = Board.board.get((i).intValue()).get((j).intValue());
            _get_27.piece = Board.BPawns.get(3);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 4))) {
            _matched=true;
            Square _get_28 = Board.board.get((i).intValue()).get((j).intValue());
            _get_28.piece = Board.BPawns.get(4);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 5))) {
            _matched=true;
            Square _get_29 = Board.board.get((i).intValue()).get((j).intValue());
            _get_29.piece = Board.BPawns.get(5);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 6))) {
            _matched=true;
            Square _get_30 = Board.board.get((i).intValue()).get((j).intValue());
            _get_30.piece = Board.BPawns.get(6);
          }
        }
        if (!_matched) {
          if (((l.getX() == 7) && (l.getY() == 7))) {
            _matched=true;
            Square _get_31 = Board.board.get((i).intValue()).get((j).intValue());
            _get_31.piece = Board.BPawns.get(7);
          }
        }
      };
      new IntegerRange(0, 7).forEach(_function_3);
    };
    new IntegerRange(0, 7).forEach(_function_2);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("INSTRUCTIONS");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("The Board is numbered 0 to 7 from left to Right and 0 to 7 from bottom to top.");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("So the leftmost square of the bottom most row is (0,0)");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("Castling is currently not supported");
    _builder.newLine();
    String Instructions = _builder.toString();
    InputOutput.<String>println(Instructions);
    while (true) {
      {
        Board.turn(Board.Player1);
        Board.turn(Board.Player2);
      }
    }
  }
}
