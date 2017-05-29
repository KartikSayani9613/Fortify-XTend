package FortressToXtend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.function.Supplier;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IntegerRange;

@SuppressWarnings("all")
public class Async {
  public static AtomicInteger loopCount = new AtomicInteger(0);
  
  public List<Integer> iter = Collections.<Integer>synchronizedList(new ArrayList<Integer>());
  
  public int parallelism;
  
  public Object retVal;
  
  public Object For(final int start, final int end, final Callable<Object> myFunc) {
    this.parallelism = 1;
    this.retVal = this.For(start, end, myFunc, this.parallelism);
    return this.retVal;
  }
  
  public int i() {
    int _size = this.iter.size();
    int _minus = (_size - 1);
    return (this.iter.remove(_minus)).intValue();
  }
  
  public Object For(final int start, final int end, final Callable<Object> myFunc, final int parallelism) {
    synchronized (this.iter) {
      final Consumer<Integer> _function = (Integer i) -> {
        this.iter.add(i);
      };
      new IntegerRange(start, end).forEach(_function);
    }
    final ExecutorService pool = Executors.newFixedThreadPool(parallelism);
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
    while ((!this.<Object>allDone(m))) {
    }
    pool.shutdown();
    return this.retVal;
  }
  
  public <T extends Object> boolean allDone(final ArrayList<CompletableFuture<T>> aray) {
    for (final CompletableFuture<T> b : aray) {
      while ((!b.isDone())) {
      }
    }
    return true;
  }
}
