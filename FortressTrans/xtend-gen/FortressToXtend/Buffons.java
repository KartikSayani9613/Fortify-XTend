package FortressToXtend;

import FortressToXtend.Async;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.xtext.xbase.lib.InputOutput;

@SuppressWarnings("all")
public class Buffons {
  private static AtomicInteger hits = new AtomicInteger(0);
  
  private static AtomicInteger n = new AtomicInteger(0);
  
  public static void main(final String[] args) {
    final double needleLength = 20.0d;
    final double numRows = 10.0d;
    final double tableHeight = (needleLength * numRows);
    long start = System.currentTimeMillis();
    InputOutput.<String>println("Starting Parallel Buffons");
    final Async l1 = new Async();
    final Random r = new Random();
    final Callable<Object> _function = () -> {
      int _xblockexpression = (int) 0;
      {
        double _nextDouble = r.nextDouble();
        double _multiply = (2 * _nextDouble);
        final double delta_x = (_multiply - 1.0);
        double _nextDouble_1 = r.nextDouble();
        double _multiply_1 = (2 * _nextDouble_1);
        final double delta_y = (_multiply_1 - 1.0);
        double _pow = Math.pow(delta_x, 2);
        double _pow_1 = Math.pow(delta_y, 2);
        final double rsq = (_pow + _pow_1);
        int _xifexpression = (int) 0;
        if (((0.0 < rsq) && (rsq < 1.0))) {
          int _xblockexpression_1 = (int) 0;
          {
            double _nextDouble_2 = r.nextDouble();
            double y1 = (tableHeight * _nextDouble_2);
            double _sqrt = Math.sqrt(rsq);
            double _divide = (delta_y / _sqrt);
            double _multiply_2 = (needleLength * _divide);
            double y2 = (y1 + _multiply_2);
            double y_L = Math.min(y1, y2);
            double y_H = Math.max(y1, y2);
            double temp1 = (y_L / needleLength);
            double temp2 = (y_H / needleLength);
            double _ceil = Math.ceil(temp1);
            double _floor = Math.floor(temp2);
            boolean _equals = (_ceil == _floor);
            if (_equals) {
              Buffons.hits.incrementAndGet();
            }
            _xblockexpression_1 = Buffons.n.incrementAndGet();
          }
          _xifexpression = _xblockexpression_1;
        }
        _xblockexpression = _xifexpression;
      }
      return Integer.valueOf(_xblockexpression);
    };
    l1.For(0, 3000, _function, 100);
    float _floatValue = Buffons.hits.floatValue();
    float _floatValue_1 = Buffons.n.floatValue();
    float probability = (_floatValue / _floatValue_1);
    double pi_est = (2.0 / probability);
    long _currentTimeMillis = System.currentTimeMillis();
    long end = (_currentTimeMillis - start);
    InputOutput.<String>println(("Time taken: " + Long.valueOf(end)));
    InputOutput.<String>println(((("Hits:\t" + Buffons.hits) + "\tn:\t") + Buffons.n));
    InputOutput.<String>println(("Buffons: estimated Pi = " + Double.valueOf(pi_est)));
  }
}
