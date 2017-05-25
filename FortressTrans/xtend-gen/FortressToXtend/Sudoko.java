package FortressToXtend;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure2;

@SuppressWarnings("all")
public class Sudoko {
  private static ArrayList<ArrayList<Set<Integer>>> board = new ArrayList<ArrayList<Set<Integer>>>();
  
  private static LinkedHashSet<Integer> emptySet = new LinkedHashSet<Integer>();
  
  private static Set<Integer> initSet() {
    Set<Integer> initSet = IterableExtensions.<Integer>toSet(new IntegerRange(1, 9));
    return initSet;
  }
  
  private static Set<Integer> removeElement(final ArrayList<ArrayList<Set<Integer>>> b, final int i, final int j, final int elem) {
    Set<Integer> _xblockexpression = null;
    {
      Set<Integer> s = b.get(i).get(j);
      Set<Integer> _xifexpression = null;
      boolean _contains = s.contains(Integer.valueOf(elem));
      if (_contains) {
        Set<Integer> _xblockexpression_1 = null;
        {
          s.remove(Integer.valueOf(elem));
          _xblockexpression_1 = b.get(i).set(j, s);
        }
        _xifexpression = _xblockexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  private static void propogateRow(final ArrayList<ArrayList<Set<Integer>>> b, final int i, final int j, final int elem) {
    final Consumer<Integer> _function = (Integer k) -> {
      if ((j != (k).intValue())) {
        Sudoko.removeElement(b, i, (k).intValue(), elem);
      }
    };
    new IntegerRange(0, 8).forEach(_function);
  }
  
  private static void propogateColumn(final ArrayList<ArrayList<Set<Integer>>> b, final int i, final int j, final int elem) {
    final Consumer<Integer> _function = (Integer k) -> {
      if ((i != (k).intValue())) {
        Sudoko.removeElement(b, (k).intValue(), j, elem);
      }
    };
    new IntegerRange(0, 8).forEach(_function);
  }
  
  private static void propogateSquare(final ArrayList<ArrayList<Set<Integer>>> b, final int i, final int j, final int elem) {
    double _floor = Math.floor((i / 3));
    double _multiply = (_floor * 3);
    final int starti = ((int) _multiply);
    double _floor_1 = Math.floor((i / 3));
    double _multiply_1 = (_floor_1 * 3);
    final int startj = ((int) _multiply_1);
    final Consumer<Integer> _function = (Integer k) -> {
      final Consumer<Integer> _function_1 = (Integer l) -> {
        if ((((k).intValue() != i) && ((l).intValue() != j))) {
          Sudoko.removeElement(b, (k).intValue(), (l).intValue(), elem);
        }
      };
      new IntegerRange(startj, (startj + 2)).forEach(_function_1);
    };
    new IntegerRange(starti, (starti + 2)).forEach(_function);
  }
  
  private static void propogateSingleton(final ArrayList<ArrayList<Set<Integer>>> b, final int i, final int j, final int elem) {
    Sudoko.propogateRow(b, i, j, elem);
    Sudoko.propogateColumn(b, i, j, elem);
    Sudoko.propogateSquare(b, i, j, elem);
  }
  
  private static Object propogate(final ArrayList<ArrayList<Set<Integer>>> b) {
    return null;
  }
  
  public static void main(final String[] args) {
    final Consumer<Integer> _function = (Integer i) -> {
      ArrayList<Set<Integer>> _arrayList = new ArrayList<Set<Integer>>();
      Sudoko.board.add(_arrayList);
      final Consumer<Integer> _function_1 = (Integer it) -> {
        Sudoko.board.get((i).intValue()).add(Sudoko.initSet());
      };
      new IntegerRange(0, 8).forEach(_function_1);
    };
    new IntegerRange(0, 8).forEach(_function);
    final Procedure2<Integer, Integer> _function_1 = (Integer i, Integer j) -> {
    };
    IterableExtensions.<Integer>forEach(new IntegerRange(0, 8), _function_1);
  }
}
