package FortressToXtend;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class Sudoko {
  private static ArrayList<ArrayList<Set<Integer>>> board = new ArrayList<ArrayList<Set<Integer>>>();
  
  private static AtomicInteger unsolved = new AtomicInteger(81);
  
  private static Set<Integer> initSet() {
    Set<Integer> initSet = IterableExtensions.<Integer>toSet(new IntegerRange(1, 9));
    return initSet;
  }
  
  private static Set<Integer> removeElement(final ArrayList<ArrayList<Set<Integer>>> b, final int i, final int j, final int elem) {
    Set<Integer> _xsynchronizedexpression = null;
    synchronized (b) {
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
      _xsynchronizedexpression = _xblockexpression;
    }
    return _xsynchronizedexpression;
  }
  
  private static void propogateRow(final ArrayList<ArrayList<Set<Integer>>> b, final int i, final int j, final int elem) {
    final Consumer<Integer> _function = (Integer k) -> {
      if (((k).intValue() != j)) {
        Sudoko.removeElement(b, i, (k).intValue(), elem);
      }
    };
    new IntegerRange(0, 8).forEach(_function);
  }
  
  private static void propogateColumn(final ArrayList<ArrayList<Set<Integer>>> b, final int i, final int j, final int elem) {
    final Consumer<Integer> _function = (Integer k) -> {
      if (((k).intValue() != i)) {
        Sudoko.removeElement(b, (k).intValue(), j, elem);
      }
    };
    new IntegerRange(0, 8).forEach(_function);
  }
  
  private static void propogateSquare(final ArrayList<ArrayList<Set<Integer>>> b, final int i, final int j, final int elem) {
    double _floor = Math.floor((i / 3));
    double _multiply = (_floor * 3);
    final int starti = ((int) _multiply);
    double _floor_1 = Math.floor((j / 3));
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
    try {
      Thread.sleep(100);
      Sudoko.propogateRow(b, i, j, elem);
      Sudoko.propogateColumn(b, i, j, elem);
      Sudoko.propogateSquare(b, i, j, elem);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private static void propogate(final ArrayList<ArrayList<Set<Integer>>> b) {
    int prevUnsolved = 82;
    Sudoko.unsolved.set(81);
    while (((0 < Sudoko.unsolved.intValue()) && (Sudoko.unsolved.intValue() < prevUnsolved))) {
      {
        prevUnsolved = Sudoko.unsolved.intValue();
        Sudoko.unsolved.set(0);
        final Consumer<Integer> _function = (Integer i) -> {
          final Consumer<Integer> _function_1 = (Integer j) -> {
            int _size = b.get((i).intValue()).get((j).intValue()).size();
            boolean _equals = (_size == 1);
            if (_equals) {
              final Integer elem = IterableExtensions.<Integer>min(b.get((i).intValue()).get((j).intValue()));
              Sudoko.propogateSingleton(b, (i).intValue(), (j).intValue(), (elem).intValue());
            } else {
              Sudoko.unsolved.getAndIncrement();
            }
          };
          new IntegerRange(0, 8).forEach(_function_1);
        };
        new IntegerRange(0, 8).forEach(_function);
        InputOutput.<String>println(("Remaining " + Sudoko.unsolved));
      }
    }
  }
  
  private static int createRow() {
    ArrayList<Set<Integer>> _arrayList = new ArrayList<Set<Integer>>();
    Sudoko.board.add(_arrayList);
    int _size = Sudoko.board.size();
    return (_size - 1);
  }
  
  public static void setVal(final ArrayList<ArrayList<Set<Integer>>> b, final int i, final int j, final int elem) {
    Set<Integer> x = Sudoko.initSet();
    for (final Integer ii : x) {
      if (((ii).intValue() != elem)) {
        b.get(i).get(j).remove(ii);
      }
    }
  }
  
  public static void main(final String[] args) {
    long start = System.currentTimeMillis();
    final Consumer<Integer> _function = (Integer it) -> {
      final int rnum = Sudoko.createRow();
      final Consumer<Integer> _function_1 = (Integer it_1) -> {
        Sudoko.board.get(rnum).add(Sudoko.initSet());
      };
      new IntegerRange(0, 8).forEach(_function_1);
    };
    new IntegerRange(0, 8).forEach(_function);
    Sudoko.setVal(Sudoko.board, 0, 0, 8);
    Sudoko.setVal(Sudoko.board, 0, 2, 6);
    Sudoko.setVal(Sudoko.board, 0, 3, 1);
    Sudoko.setVal(Sudoko.board, 0, 5, 9);
    Sudoko.setVal(Sudoko.board, 0, 6, 3);
    Sudoko.setVal(Sudoko.board, 0, 8, 5);
    Sudoko.setVal(Sudoko.board, 1, 1, 9);
    Sudoko.setVal(Sudoko.board, 1, 4, 8);
    Sudoko.setVal(Sudoko.board, 1, 6, 4);
    Sudoko.setVal(Sudoko.board, 2, 1, 7);
    Sudoko.setVal(Sudoko.board, 2, 2, 1);
    Sudoko.setVal(Sudoko.board, 2, 8, 6);
    Sudoko.setVal(Sudoko.board, 3, 3, 9);
    Sudoko.setVal(Sudoko.board, 3, 4, 2);
    Sudoko.setVal(Sudoko.board, 3, 6, 5);
    Sudoko.setVal(Sudoko.board, 3, 7, 3);
    Sudoko.setVal(Sudoko.board, 4, 2, 9);
    Sudoko.setVal(Sudoko.board, 4, 4, 6);
    Sudoko.setVal(Sudoko.board, 4, 6, 7);
    Sudoko.setVal(Sudoko.board, 5, 1, 3);
    Sudoko.setVal(Sudoko.board, 5, 2, 4);
    Sudoko.setVal(Sudoko.board, 5, 4, 7);
    Sudoko.setVal(Sudoko.board, 5, 5, 8);
    Sudoko.setVal(Sudoko.board, 6, 0, 3);
    Sudoko.setVal(Sudoko.board, 6, 6, 1);
    Sudoko.setVal(Sudoko.board, 6, 7, 4);
    Sudoko.setVal(Sudoko.board, 7, 2, 5);
    Sudoko.setVal(Sudoko.board, 7, 4, 1);
    Sudoko.setVal(Sudoko.board, 7, 7, 9);
    Sudoko.setVal(Sudoko.board, 8, 0, 9);
    Sudoko.setVal(Sudoko.board, 8, 2, 7);
    Sudoko.setVal(Sudoko.board, 8, 3, 8);
    Sudoko.setVal(Sudoko.board, 8, 5, 4);
    Sudoko.setVal(Sudoko.board, 8, 6, 6);
    Sudoko.setVal(Sudoko.board, 8, 8, 2);
    InputOutput.<String>println("originally");
    final Consumer<Integer> _function_1 = (Integer i) -> {
      final Consumer<Integer> _function_2 = (Integer j) -> {
        int _size = Sudoko.board.get((i).intValue()).get((j).intValue()).size();
        boolean _equals = (_size == 1);
        if (_equals) {
          Set<Integer> _get = Sudoko.board.get((i).intValue()).get((j).intValue());
          String _plus = ("\t" + _get);
          InputOutput.<String>print(_plus);
        } else {
          InputOutput.<String>print("\t_");
        }
      };
      new IntegerRange(0, 8).forEach(_function_2);
      InputOutput.<String>println("");
    };
    new IntegerRange(0, 8).forEach(_function_1);
    Sudoko.propogate(Sudoko.board);
    long _currentTimeMillis = System.currentTimeMillis();
    long end = (_currentTimeMillis - start);
    InputOutput.<String>println(("Time Taken " + Long.valueOf(end)));
    final Consumer<Integer> _function_2 = (Integer i) -> {
      final Consumer<Integer> _function_3 = (Integer j) -> {
        Set<Integer> _get = Sudoko.board.get((i).intValue()).get((j).intValue());
        String _plus = ("\t" + _get);
        InputOutput.<String>print(_plus);
      };
      new IntegerRange(0, 8).forEach(_function_3);
      InputOutput.<String>println("");
    };
    new IntegerRange(0, 8).forEach(_function_2);
  }
}
