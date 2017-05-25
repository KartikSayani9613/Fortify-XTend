package FortressToXtend;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReferenceArray;

@SuppressWarnings("all")
public class Async {
  private static AtomicReferenceArray<Integer> iter /* Skipped initializer because of errors */;
  
  public <T extends Object> void For(final int start, final int end, final Callable<T> myFunc) {
    int parallelism = 100;
    this.<T>For(start, end, myFunc, parallelism);
  }
  
  public Object getIter() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method remove(Object) is undefined for the type AtomicReferenceArray<Integer>"
      + "\nThe method or field size is undefined for the type AtomicReferenceArray<Integer>"
      + "\n- cannot be resolved");
  }
  
  public <T extends Object> void For(final int start, final int end, final Callable<T> myFunc, final int parallelism) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method add(Integer) is undefined for the type AtomicReferenceArray<Integer>");
  }
  
  public <T extends Object> boolean allDone(final ArrayList<CompletableFuture<T>> aray) {
    for (final CompletableFuture<T> b : aray) {
      boolean _isDone = b.isDone();
      boolean _not = (!_isDone);
      if (_not) {
        return false;
      }
    }
    return true;
  }
}
