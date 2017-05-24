package FortressToXtend;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class Sudoko {
  private static ArrayList<ArrayList<Set<Integer>>> r = new ArrayList<ArrayList<Set<Integer>>>();
  
  private static Set<Integer> initSet() {
    Set<Integer> initSet = IterableExtensions.<Integer>toSet(new IntegerRange(1, 9));
    return initSet;
  }
  
  public static void main(final String[] args) {
    final Consumer<Integer> _function = (Integer i) -> {
      ArrayList<Set<Integer>> _arrayList = new ArrayList<Set<Integer>>();
      Sudoko.r.add(_arrayList);
      final Consumer<Integer> _function_1 = (Integer j) -> {
        Sudoko.r.get((i).intValue()).add(Sudoko.initSet());
      };
      new IntegerRange(0, 8).forEach(_function_1);
    };
    new IntegerRange(0, 8).forEach(_function);
  }
}
