package FortressToXtend;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IntegerRange;

@SuppressWarnings("all")
public class Async {
  public static AtomicInteger loopCount = new AtomicInteger(0);
  
  public Stack<Integer> iter = new Stack<Integer>();
  
  public int parallelism;
  
  public Object retVal;
  
  public ArrayList<Object> retVals = new ArrayList<Object>();
  
  public Object For(final int start, final int end, final Callable<Object> myFunc) {
    this.parallelism = 1;
    this.retVal = this.For(start, end, myFunc, this.parallelism);
    return this.retVal;
  }
  
  public Object reset() {
    Object _xblockexpression = null;
    {
      AtomicInteger _atomicInteger = new AtomicInteger(0);
      Async.loopCount = _atomicInteger;
      Stack<Integer> _stack = new Stack<Integer>();
      this.iter = _stack;
      this.parallelism = (-1);
      _xblockexpression = this.retVal = null;
    }
    return _xblockexpression;
  }
  
  public int i() {
    synchronized (this.iter) {
      return (this.iter.pop()).intValue();
    }
  }
  
  public Object For(final int start, final int end, final Callable<Object> myFunc, final int par) {
    this.parallelism = par;
    synchronized (this.iter) {
      final Consumer<Integer> _function = (Integer i) -> {
        this.iter.push(i);
      };
      new IntegerRange(end, start).forEach(_function);
    }
    final ExecutorService pool = Executors.newFixedThreadPool(this.parallelism);
    final ArrayList<CompletableFuture<Object>> m = new ArrayList<CompletableFuture<Object>>();
    final Consumer<Integer> _function = (Integer it) -> {
      final Supplier<Object> _function_1 = () -> {
        try {
          return this.retVal = myFunc.call();
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      m.add(
        CompletableFuture.<Object>supplyAsync(_function_1, pool));
    };
    new IntegerRange(start, end).forEach(_function);
    this.<Object>allDone(m);
    pool.shutdown();
    return this.retVal;
  }
  
  public ArrayList<Object> FuncFor(final ArrayList<Callable<Object>> myFuncs) {
    this.parallelism = 1;
    this.FuncFor(myFuncs, this.parallelism);
    return this.retVals;
  }
  
  public ArrayList<Object> FuncFor(final ArrayList<Callable<Object>> myFuncs, final int par) {
    this.parallelism = par;
    synchronized (this.iter) {
      int _size = myFuncs.size();
      int _minus = (_size - 1);
      final Consumer<Integer> _function = (Integer i) -> {
        this.iter.push(i);
      };
      new IntegerRange(0, _minus).forEach(_function);
    }
    final ExecutorService pool = Executors.newFixedThreadPool(this.parallelism);
    final ArrayList<CompletableFuture<Object>> m = new ArrayList<CompletableFuture<Object>>();
    int _size = myFuncs.size();
    int _minus = (_size - 1);
    final Consumer<Integer> _function = (Integer i) -> {
      final Supplier<Object> _function_1 = () -> {
        try {
          return Boolean.valueOf(this.retVals.add(myFuncs.get((i).intValue()).call()));
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      };
      m.add(
        CompletableFuture.<Object>supplyAsync(_function_1, pool));
    };
    new IntegerRange(0, _minus).forEach(_function);
    this.<Object>allDone(m);
    pool.shutdown();
    return this.retVals;
  }
  
  public <T extends Object> boolean allDone(final ArrayList<CompletableFuture<T>> aray) {
    try {
      for (final CompletableFuture<T> b : aray) {
        while ((!b.isDone())) {
          Thread.sleep(10);
        }
      }
      return true;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public static ArrayList<ArrayList<Integer>> make2DIntArray(final int... dims) {
    final ArrayList<ArrayList<Integer>> array = new ArrayList<ArrayList<Integer>>();
    int _get = dims[0];
    int _minus = (_get - 1);
    final Consumer<Integer> _function = (Integer it) -> {
      ArrayList<Integer> _arrayList = new ArrayList<Integer>();
      array.add(_arrayList);
    };
    new IntegerRange(0, _minus).forEach(_function);
    int _get_1 = dims[0];
    int _minus_1 = (_get_1 - 1);
    final Consumer<Integer> _function_1 = (Integer i) -> {
      int _get_2 = dims[1];
      int _minus_2 = (_get_2 - 1);
      final Consumer<Integer> _function_2 = (Integer it) -> {
        array.get((i).intValue()).add(Integer.valueOf(0));
      };
      new IntegerRange(0, _minus_2).forEach(_function_2);
    };
    new IntegerRange(0, _minus_1).forEach(_function_1);
    return array;
  }
  
  public static ArrayList<Integer> makeArray(final int... vals) {
    final ArrayList<Integer> array = new ArrayList<Integer>();
    int _size = ((List<Integer>)Conversions.doWrapArray(vals)).size();
    int _minus = (_size - 1);
    final Consumer<Integer> _function = (Integer i) -> {
      array.add(Integer.valueOf(vals[(i).intValue()]));
    };
    new IntegerRange(0, _minus).forEach(_function);
    return array;
  }
}
