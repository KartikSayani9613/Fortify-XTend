package FortressToXtend;

import FortressToXtend.Async;
import FortressToXtend.Entity;
import java.util.ArrayList;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IntegerRange;

@SuppressWarnings("all")
public class posFeedback {
  public static void main(final String[] args) {
    long start = System.currentTimeMillis();
    final Async l1 = new Async();
    IntegerRange _upTo = new IntegerRange(0, 5000);
    for (final Integer i : _upTo) {
      {
        ArrayList<Integer> known = Async.makeArray(1, 5);
        ArrayList<Integer> kfacts = Async.makeArray(0, 0);
        Entity entity = new Entity(0, known, kfacts);
        while ((!entity.tick())) {
        }
      }
    }
    long _currentTimeMillis = System.currentTimeMillis();
    long end = (_currentTimeMillis - start);
    InputOutput.<Long>println(Long.valueOf(end));
  }
}
