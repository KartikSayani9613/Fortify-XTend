package FortressToXtend;

import FortressToXtend.Stats;
import com.google.common.base.Objects;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Consumer;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IntegerRange;

@SuppressWarnings("all")
public class Entity {
  private int positivity;
  
  private ArrayList<Integer> known;
  
  private ArrayList<Integer> initFacts;
  
  private int time = (-1);
  
  private int factNeg = (-1);
  
  private int factPos = 1;
  
  private int factUnk = 0;
  
  private int[] facts = new int[10];
  
  public Entity(final int p, final ArrayList<Integer> k, final ArrayList<Integer> i) {
    this.positivity = p;
    this.known = k;
    this.initFacts = i;
    this.filler();
  }
  
  public void filler() {
    final Integer negFacts = this.initFacts.get(0);
    Integer _get = this.initFacts.get(1);
    final int totFacts = ((negFacts).intValue() + (_get).intValue());
    final Consumer<Integer> _function = (Integer i) -> {
      boolean _lessThan = (i.compareTo(negFacts) < 0);
      if (_lessThan) {
        this.facts[(i).intValue()] = this.factNeg;
      } else {
        if (((i).intValue() < totFacts)) {
          this.facts[(i).intValue()] = this.factPos;
        } else {
          this.facts[(i).intValue()] = this.factUnk;
        }
      }
    };
    new IntegerRange(0, 9).forEach(_function);
  }
  
  public String asString() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("Entity(");
    _builder.append(this.positivity);
    _builder.append(", [");
    Integer _get = this.known.get(0);
    _builder.append(_get);
    _builder.append(" ");
    Integer _get_1 = this.known.get(1);
    _builder.append(_get_1);
    _builder.append("]) t=");
    _builder.append(this.time);
    _builder.newLineIfNotEmpty();
    String _string = ((List<Integer>)Conversions.doWrapArray(this.facts)).toString();
    _builder.append(_string);
    return _builder.toString();
  }
  
  public int otherResult(final int result) {
    return (1 - result);
  }
  
  public int eventToResult(final int kind) {
    return ((kind + 1) / 2);
  }
  
  public String unlearn(final int fact, final int kind) {
    String _xblockexpression = null;
    {
      this.facts[fact] = this.factUnk;
      int result = this.otherResult(this.eventToResult(kind));
      Integer _get = this.known.get(result);
      int _minus = ((_get).intValue() - 1);
      this.known.set(result, Integer.valueOf(_minus));
      String _xifexpression = null;
      Integer _get_1 = this.known.get(result);
      boolean _lessThan = ((_get_1).intValue() < 0);
      if (_lessThan) {
        _xifexpression = InputOutput.<String>println((("Result " + Integer.valueOf(result)) + " dipped below 0!"));
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public boolean learn(final int fact, final int kind) {
    this.facts[fact] = kind;
    int result = this.eventToResult(kind);
    Integer _get = this.known.get(result);
    int _plus = ((_get).intValue() + 1);
    this.known.set(result, Integer.valueOf(_plus));
    if (((((this.known.get(0)).intValue() + (this.known.get(1)).intValue()) >= 10) && ((this.known.get(result)).intValue() >= (2 * (this.known.get(this.otherResult(result))).intValue())))) {
      Stats.record(this.time, result);
      return true;
    } else {
      return false;
    }
  }
  
  public boolean event(final int kind) {
    int _positivity = this.positivity;
    this.positivity = (_positivity + kind);
    Random r = new Random();
    int fact = r.nextInt(10);
    int _get = this.facts[fact];
    boolean _matched = false;
    if (Objects.equal(_get, kind)) {
      _matched=true;
      return false;
    }
    if (!_matched) {
      if (Objects.equal(_get, 0)) {
        _matched=true;
        return this.learn(fact, kind);
      }
    }
    {
      this.unlearn(fact, kind);
      return false;
    }
  }
  
  public int min(final int a, final int b) {
    int _xifexpression = (int) 0;
    if ((a < b)) {
      _xifexpression = a;
    } else {
      _xifexpression = b;
    }
    return _xifexpression;
  }
  
  public boolean tick() {
    boolean _xblockexpression = false;
    {
      int _time = this.time;
      this.time = (_time + 1);
      Random r = new Random();
      int roll = r.nextInt(20);
      int bot = 0;
      int top = 0;
      if ((this.positivity >= 0)) {
        bot = (1 + this.positivity);
        top = 19;
      } else {
        bot = 1;
        top = (19 + this.positivity);
      }
      boolean _xifexpression = false;
      if ((this.time >= 365)) {
        Stats.recordOver();
        return true;
      } else {
        boolean _xifexpression_1 = false;
        int _min = this.min(bot, 19);
        boolean _lessThan = (roll < _min);
        if (_lessThan) {
          _xifexpression_1 = this.event(this.factPos);
        } else {
          boolean _xifexpression_2 = false;
          if ((roll >= top)) {
            _xifexpression_2 = this.event(this.factNeg);
          } else {
            _xifexpression_2 = false;
          }
          _xifexpression_1 = _xifexpression_2;
        }
        _xifexpression = _xifexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
}
