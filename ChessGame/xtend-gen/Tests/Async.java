package Tests;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class Async {
  private static ArrayList<ArrayList<Set<Integer>>> board = new ArrayList<ArrayList<Set<Integer>>>();
  
  private static ArrayList<ArrayList<Set<Integer>>> board1 = new ArrayList<ArrayList<Set<Integer>>>();
  
  private static AtomicInteger atom = new AtomicInteger(0);
  
  public static <T extends Object> void For(final int start, final int end, final Callable<T> myFunc) {
    int parallelism = 100;
    Async.<T>For(start, end, myFunc, parallelism);
  }
  
  public static <T extends Object> void For(final int start, final int end, final Callable<T> myFunc, final int parallelism) {
    final ExecutorService pool = Executors.newFixedThreadPool(parallelism);
    final ArrayList<CompletableFuture> m = new ArrayList<CompletableFuture>();
    final Consumer<Integer> _function = (Integer i) -> {
      final Supplier<Object> _function_1 = () -> {
        try {
          return myFunc.call();
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      m.add(CompletableFuture.<Object>supplyAsync(_function_1, pool));
    };
    new IntegerRange(start, end).forEach(_function);
    while ((!Async.allDone(m))) {
    }
    return;
  }
  
  private static Set<Integer> initSet() {
    Set<Integer> initSet = IterableExtensions.<Integer>toSet(new IntegerRange(1, 9));
    return initSet;
  }
  
  public static boolean allDone(final ArrayList<CompletableFuture> aray) {
    for (final CompletableFuture b : aray) {
      boolean _isDone = b.isDone();
      boolean _not = (!_isDone);
      if (_not) {
        return false;
      }
    }
    return true;
  }
  
  private static int createRow() {
    ArrayList<Set<Integer>> _arrayList = new ArrayList<Set<Integer>>();
    Async.board.add(_arrayList);
    int _size = Async.board.size();
    return (_size - 1);
  }
  
  private static int createRow1() {
    ArrayList<Set<Integer>> _arrayList = new ArrayList<Set<Integer>>();
    Async.board1.add(_arrayList);
    int _size = Async.board1.size();
    return (_size - 1);
  }
  
  public static void main(final String[] args) {
    long start = System.currentTimeMillis();
    final Callable<Object> _function = () -> {
      final int rnum = Async.createRow();
      final Callable<Object> _function_1 = () -> {
        Async.board.get(rnum).add(Async.initSet());
        return null;
      };
      Async.<Object>For(0, 8, _function_1, 1);
      return null;
    };
    Async.<Object>For(0, 8, _function, 1);
    long _currentTimeMillis = System.currentTimeMillis();
    long end = (_currentTimeMillis - start);
    InputOutput.<Long>println(Long.valueOf(end));
    start = System.currentTimeMillis();
    final Consumer<Integer> _function_1 = (Integer i) -> {
      final int rnum = Async.createRow1();
      final Consumer<Integer> _function_2 = (Integer it) -> {
        Async.board1.get(rnum).add(Async.initSet());
      };
      new IntegerRange(0, 8).forEach(_function_2);
    };
    new IntegerRange(0, 8).forEach(_function_1);
    long _currentTimeMillis_1 = System.currentTimeMillis();
    long _minus = (_currentTimeMillis_1 - start);
    end = _minus;
    InputOutput.<Long>println(Long.valueOf(end));
  }
}
