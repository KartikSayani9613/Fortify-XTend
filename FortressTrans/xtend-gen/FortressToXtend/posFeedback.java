package FortressToXtend;

import FortressToXtend.Async;
import FortressToXtend.Entity;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class posFeedback {
  public static void main(final String[] args) {
    long start = System.currentTimeMillis();
    final Async l1 = new Async();
    final Callable<Object> _function = () -> {
      ArrayList<Integer> known = Async.makeArray(1, 5);
      ArrayList<Integer> kfacts = Async.makeArray(0, 0);
      Entity entity = new Entity(0, known, kfacts);
      while ((!entity.tick())) {
      }
      return null;
    };
    l1.For(0, 5000, _function, 32);
    long _currentTimeMillis = System.currentTimeMillis();
    long end = (_currentTimeMillis - start);
    InputOutput.<Long>println(Long.valueOf(end));
  }
}
