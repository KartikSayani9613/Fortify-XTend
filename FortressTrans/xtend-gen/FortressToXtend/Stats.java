package FortressToXtend;

import FortressToXtend.Async;
import com.google.common.collect.Lists;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.Functions.Function2;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IntegerRange;
import org.eclipse.xtext.xbase.lib.IterableExtensions;

@SuppressWarnings("all")
public class Stats {
  private final static int maxTime = 365;
  
  private static int posOut = 1;
  
  private static int negOut = 0;
  
  private static ArrayList<ArrayList<Integer>> histogram = Async.make2DIntArray(2, 365);
  
  private static AtomicInteger overAYear = new AtomicInteger(0);
  
  private static double scale;
  
  public static String asString() {
    IntegerRange _upTo = new IntegerRange(0, 364);
    final Function1<Integer, Integer> _function = (Integer i) -> {
      Integer _get = Stats.histogram.get(Stats.posOut).get((i).intValue());
      Integer _get_1 = Stats.histogram.get(Stats.negOut).get((i).intValue());
      return Integer.valueOf(((_get).intValue() + (_get_1).intValue()));
    };
    ArrayList<Integer> res = Stats.median(_upTo, Lists.<Integer>newArrayList(IterableExtensions.<Integer, Integer>map(new IntegerRange(0, 364), _function)));
    Integer _get = res.get(0);
    int _intValue = Stats.overAYear.intValue();
    int n = ((_get).intValue() + _intValue);
    Integer med = res.get(1);
    IntegerRange _upTo_1 = new IntegerRange(0, 364);
    final Function1<Integer, Integer> _function_1 = (Integer i) -> {
      return Stats.histogram.get(Stats.posOut).get((i).intValue());
    };
    res = Stats.median(_upTo_1, Lists.<Integer>newArrayList(IterableExtensions.<Integer, Integer>map(new IntegerRange(0, 364), _function_1)));
    Integer pos = res.get(0);
    Integer posMed = res.get(1);
    IntegerRange _upTo_2 = new IntegerRange(0, 364);
    final Function1<Integer, Integer> _function_2 = (Integer i) -> {
      return Stats.histogram.get(Stats.negOut).get((i).intValue());
    };
    res = Stats.median(_upTo_2, Lists.<Integer>newArrayList(IterableExtensions.<Integer, Integer>map(new IntegerRange(0, 364), _function_2)));
    Integer neg = res.get(0);
    Integer negMed = res.get(1);
    int maxEntry = 0;
    Integer _max = IterableExtensions.<Integer>max(Stats.histogram.get(0));
    Integer _max_1 = IterableExtensions.<Integer>max(Stats.histogram.get(1));
    boolean _greaterThan = (_max.compareTo(_max_1) > 0);
    if (_greaterThan) {
      maxEntry = (IterableExtensions.<Integer>max(Stats.histogram.get(0))).intValue();
    } else {
      maxEntry = (IterableExtensions.<Integer>max(Stats.histogram.get(1))).intValue();
    }
    Stats.scale = (30.0d / maxEntry);
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Stats:");
    _builder.newLine();
    _builder.append("n =  ");
    String _fmt3 = Stats.fmt3(n);
    _builder.append(_fmt3);
    _builder.append("\t\tmedian = ");
    String _fmt3_1 = Stats.fmt3((med).intValue());
    _builder.append(_fmt3_1);
    _builder.newLineIfNotEmpty();
    _builder.append("pos = ");
    String _fmt3_2 = Stats.fmt3((pos).intValue());
    _builder.append(_fmt3_2);
    _builder.append("\t");
    String _percent = Stats.percent((pos).intValue(), n);
    _builder.append(_percent);
    _builder.append("%\tmedian = ");
    String _fmt3_3 = Stats.fmt3((posMed).intValue());
    _builder.append(_fmt3_3);
    _builder.newLineIfNotEmpty();
    _builder.append("neg = ");
    String _fmt3_4 = Stats.fmt3((neg).intValue());
    _builder.append(_fmt3_4);
    _builder.append("\t");
    String _percent_1 = Stats.percent((neg).intValue(), n);
    _builder.append(_percent_1);
    _builder.append("%\tmedian = ");
    String _fmt3_5 = Stats.fmt3((negMed).intValue());
    _builder.append(_fmt3_5);
    _builder.newLineIfNotEmpty();
    _builder.append("over= ");
    String _fmt3_6 = Stats.fmt3(Stats.overAYear.intValue());
    _builder.append(_fmt3_6);
    _builder.append("\t");
    String _percent_2 = Stats.percent(Stats.overAYear.intValue(), n);
    _builder.append(_percent_2);
    _builder.append("%");
    _builder.newLineIfNotEmpty();
    _builder.append("Histogram:");
    _builder.newLine();
    String histo = _builder.toString();
    IntegerRange _upTo_3 = new IntegerRange(0, 364);
    for (final Integer i : _upTo_3) {
      String _showHist = Stats.showHist((i).intValue());
      String _plus = (histo + _showHist);
      histo = _plus;
    }
    return histo;
  }
  
  public static int barlen(final int i) {
    if ((i > 0)) {
      if (((Stats.scale * i) > 1)) {
        return Double.valueOf((Stats.scale * i)).intValue();
      } else {
        return 1;
      }
    } else {
      return 0;
    }
  }
  
  public static String percent(final int a, final int b) {
    return Stats.fmt3((((200 * a) + b) / (2 * b))).toString();
  }
  
  public static String showHist(final int i) {
    final Integer ng = Stats.histogram.get(Stats.negOut).get(i);
    final Integer ps = Stats.histogram.get(Stats.negOut).get(i);
    String retVal = "";
    if ((((ng).intValue() + (ps).intValue()) != 0)) {
      String _string = Stats.fmt3(i).toString();
      String _plus = ((retVal + "\n") + _string);
      String _plus_1 = (_plus + ":");
      String _fmt3 = Stats.fmt3((ps).intValue());
      String _plus_2 = (_plus_1 + _fmt3);
      String _plus_3 = (_plus_2 + "|");
      String _fmt3_1 = Stats.fmt3((ng).intValue());
      String _plus_4 = (_plus_3 + _fmt3_1);
      retVal = _plus_4;
      int scn = Stats.barlen((ng).intValue());
      int scp = Stats.barlen((ps).intValue());
      String _repeat = Stats.repeat((32 - scp), " ");
      String _plus_5 = (retVal + _repeat);
      String _repeat_1 = Stats.repeat(scp, "+");
      String _plus_6 = (_plus_5 + _repeat_1);
      String _plus_7 = (_plus_6 + "|");
      String _repeat_2 = Stats.repeat(scn, "-");
      String _plus_8 = (_plus_7 + _repeat_2);
      retVal = _plus_8;
    } else {
      String _fmt3_2 = Stats.fmt3(i);
      String _plus_9 = ((retVal + "\n") + _fmt3_2);
      retVal = _plus_9;
    }
    return retVal;
  }
  
  public static String repeat(final int times, final String s) {
    String retS = "";
    IntegerRange _upTo = new IntegerRange(0, (times - 2));
    for (final Integer i : _upTo) {
      retS = (retS + s);
    }
    return retS;
  }
  
  public static String fmt3(final int i) {
    if ((i < 10)) {
      return ("   " + Integer.valueOf(i));
    } else {
      if ((i < 100)) {
        return ("  " + Integer.valueOf(i));
      } else {
        if ((i < 1000)) {
          return (" " + Integer.valueOf(i));
        } else {
          return ("" + Integer.valueOf(i));
        }
      }
    }
  }
  
  public static ArrayList<Integer> median(final IntegerRange gen, final ArrayList<Integer> bucket) {
    final Function1<Integer, Integer> _function = (Integer i) -> {
      Integer _get = bucket.get((i).intValue());
      return Integer.valueOf(((i).intValue() * (_get).intValue()));
    };
    final Function2<Integer, Integer, Integer> _function_1 = (Integer i, Integer j) -> {
      return Integer.valueOf(((i).intValue() + (j).intValue()));
    };
    Integer n = IterableExtensions.<Integer>reduce(IterableExtensions.<Integer, Integer>map(gen, _function), _function_1);
    int half = ((n).intValue() / 2);
    int s = 0;
    int med = 0;
    for (final Integer i : gen) {
      if ((s <= half)) {
        med = (i).intValue();
        Integer _get = bucket.get((i).intValue());
        int _multiply = ((_get).intValue() * (i).intValue());
        int _plus = (s + _multiply);
        s = _plus;
      }
    }
    ArrayList<Integer> res = new ArrayList<Integer>();
    res.add(n);
    res.add(Integer.valueOf(med));
    return res;
  }
  
  public static int recordOver() {
    return Stats.overAYear.getAndIncrement();
  }
  
  public static Object record(final int time, final int result) {
    Object _xifexpression = null;
    if ((time >= Stats.maxTime)) {
      _xifexpression = InputOutput.<String>println(("Recording Overtime" + Integer.valueOf(time)));
    } else {
      Integer _xsynchronizedexpression = null;
      synchronized (Stats.histogram) {
        ArrayList<Integer> _get = Stats.histogram.get(result);
        Integer _get_1 = Stats.histogram.get(result).get(time);
        int _plus = ((_get_1).intValue() + 1);
        _xsynchronizedexpression = _get.set(time, Integer.valueOf(_plus));
      }
      _xifexpression = _xsynchronizedexpression;
    }
    return _xifexpression;
  }
}
