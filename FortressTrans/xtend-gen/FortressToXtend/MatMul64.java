package FortressToXtend;

import FortressToXtend.Async;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.IntegerRange;

@SuppressWarnings("all")
public class MatMul64 {
  private static Object lock = new Object();
  
  public static ArrayList<ArrayList<Integer>> ma16(final ArrayList<ArrayList<Integer>> a, final ArrayList<ArrayList<Integer>> b) {
    final ArrayList<ArrayList<Integer>> c = Async.make2DIntArray(16, 16);
    final Consumer<Integer> _function = (Integer i) -> {
      final Consumer<Integer> _function_1 = (Integer j) -> {
        ArrayList<Integer> _get = c.get((i).intValue());
        Integer _get_1 = a.get((i).intValue()).get((j).intValue());
        Integer _get_2 = b.get((i).intValue()).get((j).intValue());
        int _plus = ((_get_1).intValue() + (_get_2).intValue());
        _get.set((j).intValue(), Integer.valueOf(_plus));
      };
      new IntegerRange(0, 15).forEach(_function_1);
    };
    new IntegerRange(0, 15).forEach(_function);
    return c;
  }
  
  public static ArrayList<ArrayList<Integer>> ma8(final ArrayList<ArrayList<Integer>> a, final ArrayList<ArrayList<Integer>> b) {
    final ArrayList<ArrayList<Integer>> c = Async.make2DIntArray(8, 8);
    final Consumer<Integer> _function = (Integer i) -> {
      final Consumer<Integer> _function_1 = (Integer j) -> {
        ArrayList<Integer> _get = c.get((i).intValue());
        Integer _get_1 = a.get((i).intValue()).get((j).intValue());
        Integer _get_2 = b.get((i).intValue()).get((j).intValue());
        int _plus = ((_get_1).intValue() + (_get_2).intValue());
        _get.set((j).intValue(), Integer.valueOf(_plus));
      };
      new IntegerRange(0, 7).forEach(_function_1);
    };
    new IntegerRange(0, 7).forEach(_function);
    return c;
  }
  
  public static ArrayList<ArrayList<Integer>> ma4(final ArrayList<ArrayList<Integer>> a, final ArrayList<ArrayList<Integer>> b) {
    final ArrayList<ArrayList<Integer>> c = Async.make2DIntArray(4, 4);
    final Consumer<Integer> _function = (Integer i) -> {
      final Consumer<Integer> _function_1 = (Integer j) -> {
        ArrayList<Integer> _get = c.get((i).intValue());
        Integer _get_1 = a.get((i).intValue()).get((j).intValue());
        Integer _get_2 = b.get((i).intValue()).get((j).intValue());
        int _plus = ((_get_1).intValue() + (_get_2).intValue());
        _get.set((j).intValue(), Integer.valueOf(_plus));
      };
      new IntegerRange(0, 3).forEach(_function_1);
    };
    new IntegerRange(0, 3).forEach(_function);
    return c;
  }
  
  public static ArrayList<ArrayList<Integer>> ma2(final ArrayList<ArrayList<Integer>> a, final ArrayList<ArrayList<Integer>> b) {
    final ArrayList<ArrayList<Integer>> c = Async.make2DIntArray(2, 2);
    final Consumer<Integer> _function = (Integer i) -> {
      final Consumer<Integer> _function_1 = (Integer j) -> {
        ArrayList<Integer> _get = c.get((i).intValue());
        Integer _get_1 = a.get((i).intValue()).get((j).intValue());
        Integer _get_2 = b.get((i).intValue()).get((j).intValue());
        int _plus = ((_get_1).intValue() + (_get_2).intValue());
        _get.set((j).intValue(), Integer.valueOf(_plus));
      };
      new IntegerRange(0, 1).forEach(_function_1);
    };
    new IntegerRange(0, 1).forEach(_function);
    return c;
  }
  
  public static ArrayList<ArrayList<Integer>> mm2(final ArrayList<ArrayList<Integer>> a, final ArrayList<ArrayList<Integer>> b) {
    final Integer A00 = a.get(0).get(0);
    final Integer A01 = a.get(0).get(1);
    final Integer A10 = a.get(1).get(0);
    final Integer A11 = a.get(1).get(1);
    final Integer B00 = b.get(0).get(0);
    final Integer B01 = b.get(0).get(1);
    final Integer B10 = b.get(1).get(0);
    final Integer B11 = b.get(1).get(1);
    final ArrayList<Integer> res1 = new ArrayList<Integer>();
    res1.add(Integer.valueOf(((A00).intValue() * (B00).intValue())));
    res1.add(Integer.valueOf(((A01).intValue() * (B10).intValue())));
    res1.add(Integer.valueOf(((A00).intValue() * (B01).intValue())));
    res1.add(Integer.valueOf(((A01).intValue() * (B11).intValue())));
    res1.add(Integer.valueOf(((A10).intValue() * (B00).intValue())));
    res1.add(Integer.valueOf(((A11).intValue() * (B10).intValue())));
    res1.add(Integer.valueOf(((A10).intValue() * (B01).intValue())));
    res1.add(Integer.valueOf(((A11).intValue() * (B11).intValue())));
    final ArrayList<ArrayList<Integer>> resF = Async.make2DIntArray(2, 2);
    ArrayList<Integer> _get = resF.get(0);
    Integer _get_1 = res1.get(0);
    Integer _get_2 = res1.get(1);
    int _plus = ((_get_1).intValue() + (_get_2).intValue());
    _get.set(0, Integer.valueOf(_plus));
    ArrayList<Integer> _get_3 = resF.get(0);
    Integer _get_4 = res1.get(2);
    Integer _get_5 = res1.get(3);
    int _plus_1 = ((_get_4).intValue() + (_get_5).intValue());
    _get_3.set(1, Integer.valueOf(_plus_1));
    ArrayList<Integer> _get_6 = resF.get(1);
    Integer _get_7 = res1.get(4);
    Integer _get_8 = res1.get(5);
    int _plus_2 = ((_get_7).intValue() + (_get_8).intValue());
    _get_6.set(0, Integer.valueOf(_plus_2));
    ArrayList<Integer> _get_9 = resF.get(1);
    Integer _get_10 = res1.get(6);
    Integer _get_11 = res1.get(7);
    int _plus_3 = ((_get_10).intValue() + (_get_11).intValue());
    _get_9.set(1, Integer.valueOf(_plus_3));
    return resF;
  }
  
  public static ArrayList<ArrayList<Integer>> mm4(final ArrayList<ArrayList<Integer>> a, final ArrayList<ArrayList<Integer>> b) {
    final ArrayList<ArrayList<Integer>> A00 = Async.make2DIntArray(2, 2);
    final ArrayList<ArrayList<Integer>> A01 = Async.make2DIntArray(2, 2);
    final ArrayList<ArrayList<Integer>> A10 = Async.make2DIntArray(2, 2);
    final ArrayList<ArrayList<Integer>> A11 = Async.make2DIntArray(2, 2);
    final ArrayList<ArrayList<Integer>> B00 = Async.make2DIntArray(2, 2);
    final ArrayList<ArrayList<Integer>> B01 = Async.make2DIntArray(2, 2);
    final ArrayList<ArrayList<Integer>> B10 = Async.make2DIntArray(2, 2);
    final ArrayList<ArrayList<Integer>> B11 = Async.make2DIntArray(2, 2);
    final Async l1 = new Async();
    final Callable<Object> _function = () -> {
      Object _xblockexpression = null;
      {
        final int i = l1.i();
        final Async l2 = new Async();
        final Callable<Object> _function_1 = () -> {
          Integer _xblockexpression_1 = null;
          {
            final int j = l2.i();
            Integer _xsynchronizedexpression = null;
            synchronized (MatMul64.lock) {
              Integer _xblockexpression_2 = null;
              {
                A00.get(i).set(j, a.get(i).get(j));
                B00.get(i).set(j, b.get(i).get(j));
                A01.get(i).set(j, a.get(i).get((j + 2)));
                B01.get(i).set(j, a.get(i).get((j + 2)));
                A10.get(i).set(j, a.get((i + 2)).get(j));
                B10.get(i).set(j, a.get((i + 2)).get(j));
                A11.get(i).set(j, a.get((i + 2)).get((j + 2)));
                _xblockexpression_2 = B11.get(i).set(j, a.get((i + 2)).get((j + 2)));
              }
              _xsynchronizedexpression = _xblockexpression_2;
            }
            _xblockexpression_1 = _xsynchronizedexpression;
          }
          return _xblockexpression_1;
        };
        _xblockexpression = l2.For(0, 1, _function_1, 4);
      }
      return _xblockexpression;
    };
    l1.For(0, 1, _function, 4);
    l1.reset();
    ArrayList<Callable<Object>> funcs = new ArrayList<Callable<Object>>();
    final Callable<Object> _function_1 = () -> {
      return MatMul64.mm2(A00, B00);
    };
    funcs.add(_function_1);
    final Callable<Object> _function_2 = () -> {
      return MatMul64.mm2(A01, B10);
    };
    funcs.add(_function_2);
    final Callable<Object> _function_3 = () -> {
      return MatMul64.mm2(A00, B01);
    };
    funcs.add(_function_3);
    final Callable<Object> _function_4 = () -> {
      return MatMul64.mm2(A01, B11);
    };
    funcs.add(_function_4);
    final Callable<Object> _function_5 = () -> {
      return MatMul64.mm2(A10, B11);
    };
    funcs.add(_function_5);
    final Callable<Object> _function_6 = () -> {
      return MatMul64.mm2(A11, B10);
    };
    funcs.add(_function_6);
    final Callable<Object> _function_7 = () -> {
      return MatMul64.mm2(A10, B01);
    };
    funcs.add(_function_7);
    final Callable<Object> _function_8 = () -> {
      return MatMul64.mm2(A11, B11);
    };
    funcs.add(_function_8);
    final ArrayList<Object> res1 = l1.FuncFor(funcs, 8);
    funcs.clear();
    final Callable<Object> _function_9 = () -> {
      Object _get = res1.get(0);
      Object _get_1 = res1.get(1);
      return MatMul64.ma2(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_9);
    final Callable<Object> _function_10 = () -> {
      Object _get = res1.get(2);
      Object _get_1 = res1.get(3);
      return MatMul64.ma2(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_10);
    final Callable<Object> _function_11 = () -> {
      Object _get = res1.get(4);
      Object _get_1 = res1.get(5);
      return MatMul64.ma2(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_11);
    final Callable<Object> _function_12 = () -> {
      Object _get = res1.get(6);
      Object _get_1 = res1.get(7);
      return MatMul64.ma2(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_12);
    final ArrayList<Object> res2 = l1.FuncFor(funcs, 4);
    l1.reset();
    Object _get = res2.get(0);
    final ArrayList<ArrayList<Integer>> c00 = ((ArrayList<ArrayList<Integer>>) _get);
    Object _get_1 = res2.get(1);
    final ArrayList<ArrayList<Integer>> c01 = ((ArrayList<ArrayList<Integer>>) _get_1);
    Object _get_2 = res2.get(2);
    final ArrayList<ArrayList<Integer>> c10 = ((ArrayList<ArrayList<Integer>>) _get_2);
    Object _get_3 = res2.get(3);
    final ArrayList<ArrayList<Integer>> c11 = ((ArrayList<ArrayList<Integer>>) _get_3);
    final ArrayList<ArrayList<Integer>> resF = Async.make2DIntArray(4, 4);
    final Callable<Object> _function_13 = () -> {
      Object _xblockexpression = null;
      {
        final int i = l1.i();
        final Async l2 = new Async();
        final Callable<Object> _function_14 = () -> {
          Integer _xblockexpression_1 = null;
          {
            final int j = l2.i();
            resF.get(i).set(j, c00.get(i).get(j));
            resF.get((i + 2)).set(j, c10.get(i).get(j));
            resF.get(i).set((j + 2), c01.get(i).get(j));
            _xblockexpression_1 = resF.get((i + 2)).set((j + 2), c11.get(i).get(j));
          }
          return _xblockexpression_1;
        };
        _xblockexpression = l2.For(0, 1, _function_14, 2);
      }
      return _xblockexpression;
    };
    l1.For(0, 1, _function_13, 2);
    return resF;
  }
  
  public static ArrayList<ArrayList<Integer>> mm8(final ArrayList<ArrayList<Integer>> a, final ArrayList<ArrayList<Integer>> b) {
    final ArrayList<ArrayList<Integer>> A00 = Async.make2DIntArray(4, 4);
    final ArrayList<ArrayList<Integer>> A01 = Async.make2DIntArray(4, 4);
    final ArrayList<ArrayList<Integer>> A10 = Async.make2DIntArray(4, 4);
    final ArrayList<ArrayList<Integer>> A11 = Async.make2DIntArray(4, 4);
    final ArrayList<ArrayList<Integer>> B00 = Async.make2DIntArray(4, 4);
    final ArrayList<ArrayList<Integer>> B01 = Async.make2DIntArray(4, 4);
    final ArrayList<ArrayList<Integer>> B10 = Async.make2DIntArray(4, 4);
    final ArrayList<ArrayList<Integer>> B11 = Async.make2DIntArray(4, 4);
    final Async l1 = new Async();
    final Callable<Object> _function = () -> {
      Object _xblockexpression = null;
      {
        final int i = l1.i();
        final Async l2 = new Async();
        final Callable<Object> _function_1 = () -> {
          Integer _xblockexpression_1 = null;
          {
            final int j = l2.i();
            Integer _xsynchronizedexpression = null;
            synchronized (MatMul64.lock) {
              Integer _xblockexpression_2 = null;
              {
                A00.get(i).set(j, a.get(i).get(j));
                B00.get(i).set(j, b.get(i).get(j));
                A01.get(i).set(j, a.get(i).get((j + 4)));
                B01.get(i).set(j, a.get(i).get((j + 4)));
                A10.get(i).set(j, a.get((i + 4)).get(j));
                B10.get(i).set(j, a.get((i + 4)).get(j));
                A11.get(i).set(j, a.get((i + 4)).get((j + 4)));
                _xblockexpression_2 = B11.get(i).set(j, a.get((i + 4)).get((j + 4)));
              }
              _xsynchronizedexpression = _xblockexpression_2;
            }
            _xblockexpression_1 = _xsynchronizedexpression;
          }
          return _xblockexpression_1;
        };
        _xblockexpression = l2.For(0, 3, _function_1, 4);
      }
      return _xblockexpression;
    };
    l1.For(0, 3, _function, 4);
    l1.reset();
    ArrayList<Callable<Object>> funcs = new ArrayList<Callable<Object>>();
    final Callable<Object> _function_1 = () -> {
      return MatMul64.mm4(A00, B00);
    };
    funcs.add(_function_1);
    final Callable<Object> _function_2 = () -> {
      return MatMul64.mm4(A01, B10);
    };
    funcs.add(_function_2);
    final Callable<Object> _function_3 = () -> {
      return MatMul64.mm4(A00, B01);
    };
    funcs.add(_function_3);
    final Callable<Object> _function_4 = () -> {
      return MatMul64.mm4(A01, B11);
    };
    funcs.add(_function_4);
    final Callable<Object> _function_5 = () -> {
      return MatMul64.mm4(A10, B11);
    };
    funcs.add(_function_5);
    final Callable<Object> _function_6 = () -> {
      return MatMul64.mm4(A11, B10);
    };
    funcs.add(_function_6);
    final Callable<Object> _function_7 = () -> {
      return MatMul64.mm4(A10, B01);
    };
    funcs.add(_function_7);
    final Callable<Object> _function_8 = () -> {
      return MatMul64.mm4(A11, B11);
    };
    funcs.add(_function_8);
    final ArrayList<Object> res1 = l1.FuncFor(funcs, 8);
    funcs.clear();
    final Callable<Object> _function_9 = () -> {
      Object _get = res1.get(0);
      Object _get_1 = res1.get(1);
      return MatMul64.ma4(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_9);
    final Callable<Object> _function_10 = () -> {
      Object _get = res1.get(2);
      Object _get_1 = res1.get(3);
      return MatMul64.ma4(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_10);
    final Callable<Object> _function_11 = () -> {
      Object _get = res1.get(4);
      Object _get_1 = res1.get(5);
      return MatMul64.ma4(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_11);
    final Callable<Object> _function_12 = () -> {
      Object _get = res1.get(6);
      Object _get_1 = res1.get(7);
      return MatMul64.ma4(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_12);
    final ArrayList<Object> res2 = l1.FuncFor(funcs, 4);
    l1.reset();
    Object _get = res2.get(0);
    final ArrayList<ArrayList<Integer>> c00 = ((ArrayList<ArrayList<Integer>>) _get);
    Object _get_1 = res2.get(1);
    final ArrayList<ArrayList<Integer>> c01 = ((ArrayList<ArrayList<Integer>>) _get_1);
    Object _get_2 = res2.get(2);
    final ArrayList<ArrayList<Integer>> c10 = ((ArrayList<ArrayList<Integer>>) _get_2);
    Object _get_3 = res2.get(3);
    final ArrayList<ArrayList<Integer>> c11 = ((ArrayList<ArrayList<Integer>>) _get_3);
    final ArrayList<ArrayList<Integer>> resF = Async.make2DIntArray(8, 8);
    final Callable<Object> _function_13 = () -> {
      Object _xblockexpression = null;
      {
        final int i = l1.i();
        final Async l2 = new Async();
        final Callable<Object> _function_14 = () -> {
          Integer _xblockexpression_1 = null;
          {
            final int j = l2.i();
            resF.get(i).set(j, c00.get(i).get(j));
            resF.get((i + 4)).set(j, c10.get(i).get(j));
            resF.get(i).set((j + 4), c01.get(i).get(j));
            _xblockexpression_1 = resF.get((i + 4)).set((j + 4), c11.get(i).get(j));
          }
          return _xblockexpression_1;
        };
        _xblockexpression = l2.For(0, 3, _function_14, 4);
      }
      return _xblockexpression;
    };
    l1.For(0, 3, _function_13, 4);
    return resF;
  }
  
  public static ArrayList<ArrayList<Integer>> mm16(final ArrayList<ArrayList<Integer>> a, final ArrayList<ArrayList<Integer>> b) {
    final ArrayList<ArrayList<Integer>> A00 = Async.make2DIntArray(8, 8);
    final ArrayList<ArrayList<Integer>> A01 = Async.make2DIntArray(8, 8);
    final ArrayList<ArrayList<Integer>> A10 = Async.make2DIntArray(8, 8);
    final ArrayList<ArrayList<Integer>> A11 = Async.make2DIntArray(8, 8);
    final ArrayList<ArrayList<Integer>> B00 = Async.make2DIntArray(8, 8);
    final ArrayList<ArrayList<Integer>> B01 = Async.make2DIntArray(8, 8);
    final ArrayList<ArrayList<Integer>> B10 = Async.make2DIntArray(8, 8);
    final ArrayList<ArrayList<Integer>> B11 = Async.make2DIntArray(8, 8);
    final Async l1 = new Async();
    final Callable<Object> _function = () -> {
      Object _xblockexpression = null;
      {
        final int i = l1.i();
        final Async l2 = new Async();
        final Callable<Object> _function_1 = () -> {
          Integer _xblockexpression_1 = null;
          {
            final int j = l2.i();
            Integer _xsynchronizedexpression = null;
            synchronized (MatMul64.lock) {
              Integer _xblockexpression_2 = null;
              {
                A00.get(i).set(j, a.get(i).get(j));
                B00.get(i).set(j, b.get(i).get(j));
                A01.get(i).set(j, a.get(i).get((j + 8)));
                B01.get(i).set(j, a.get(i).get((j + 8)));
                A10.get(i).set(j, a.get((i + 8)).get(j));
                B10.get(i).set(j, a.get((i + 8)).get(j));
                A11.get(i).set(j, a.get((i + 8)).get((j + 8)));
                _xblockexpression_2 = B11.get(i).set(j, a.get((i + 8)).get((j + 8)));
              }
              _xsynchronizedexpression = _xblockexpression_2;
            }
            _xblockexpression_1 = _xsynchronizedexpression;
          }
          return _xblockexpression_1;
        };
        _xblockexpression = l2.For(0, 7, _function_1, 4);
      }
      return _xblockexpression;
    };
    l1.For(0, 7, _function, 4);
    l1.reset();
    ArrayList<Callable<Object>> funcs = new ArrayList<Callable<Object>>();
    final Callable<Object> _function_1 = () -> {
      return MatMul64.mm8(A00, B00);
    };
    funcs.add(_function_1);
    final Callable<Object> _function_2 = () -> {
      return MatMul64.mm8(A01, B10);
    };
    funcs.add(_function_2);
    final Callable<Object> _function_3 = () -> {
      return MatMul64.mm8(A00, B01);
    };
    funcs.add(_function_3);
    final Callable<Object> _function_4 = () -> {
      return MatMul64.mm8(A01, B11);
    };
    funcs.add(_function_4);
    final Callable<Object> _function_5 = () -> {
      return MatMul64.mm8(A10, B11);
    };
    funcs.add(_function_5);
    final Callable<Object> _function_6 = () -> {
      return MatMul64.mm8(A11, B10);
    };
    funcs.add(_function_6);
    final Callable<Object> _function_7 = () -> {
      return MatMul64.mm8(A10, B01);
    };
    funcs.add(_function_7);
    final Callable<Object> _function_8 = () -> {
      return MatMul64.mm8(A11, B11);
    };
    funcs.add(_function_8);
    final ArrayList<Object> res1 = l1.FuncFor(funcs, 8);
    funcs.clear();
    final Callable<Object> _function_9 = () -> {
      Object _get = res1.get(0);
      Object _get_1 = res1.get(1);
      return MatMul64.ma8(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_9);
    final Callable<Object> _function_10 = () -> {
      Object _get = res1.get(2);
      Object _get_1 = res1.get(3);
      return MatMul64.ma8(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_10);
    final Callable<Object> _function_11 = () -> {
      Object _get = res1.get(4);
      Object _get_1 = res1.get(5);
      return MatMul64.ma8(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_11);
    final Callable<Object> _function_12 = () -> {
      Object _get = res1.get(6);
      Object _get_1 = res1.get(7);
      return MatMul64.ma8(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_12);
    final ArrayList<Object> res2 = l1.FuncFor(funcs, 4);
    l1.reset();
    Object _get = res2.get(0);
    final ArrayList<ArrayList<Integer>> c00 = ((ArrayList<ArrayList<Integer>>) _get);
    Object _get_1 = res2.get(1);
    final ArrayList<ArrayList<Integer>> c01 = ((ArrayList<ArrayList<Integer>>) _get_1);
    Object _get_2 = res2.get(2);
    final ArrayList<ArrayList<Integer>> c10 = ((ArrayList<ArrayList<Integer>>) _get_2);
    Object _get_3 = res2.get(3);
    final ArrayList<ArrayList<Integer>> c11 = ((ArrayList<ArrayList<Integer>>) _get_3);
    final ArrayList<ArrayList<Integer>> resF = Async.make2DIntArray(16, 16);
    final Callable<Object> _function_13 = () -> {
      Object _xblockexpression = null;
      {
        final int i = l1.i();
        final Async l2 = new Async();
        final Callable<Object> _function_14 = () -> {
          Integer _xblockexpression_1 = null;
          {
            final int j = l2.i();
            resF.get(i).set(j, c00.get(i).get(j));
            resF.get((i + 8)).set(j, c10.get(i).get(j));
            resF.get(i).set((j + 8), c01.get(i).get(j));
            _xblockexpression_1 = resF.get((i + 8)).set((j + 8), c11.get(i).get(j));
          }
          return _xblockexpression_1;
        };
        _xblockexpression = l2.For(0, 7, _function_14, 4);
      }
      return _xblockexpression;
    };
    l1.For(0, 7, _function_13, 4);
    return resF;
  }
  
  public static ArrayList<ArrayList<Integer>> mm32(final ArrayList<ArrayList<Integer>> a, final ArrayList<ArrayList<Integer>> b) {
    final ArrayList<ArrayList<Integer>> A00 = Async.make2DIntArray(16, 16);
    final ArrayList<ArrayList<Integer>> A01 = Async.make2DIntArray(16, 16);
    final ArrayList<ArrayList<Integer>> A10 = Async.make2DIntArray(16, 16);
    final ArrayList<ArrayList<Integer>> A11 = Async.make2DIntArray(16, 16);
    final ArrayList<ArrayList<Integer>> B00 = Async.make2DIntArray(16, 16);
    final ArrayList<ArrayList<Integer>> B01 = Async.make2DIntArray(16, 16);
    final ArrayList<ArrayList<Integer>> B10 = Async.make2DIntArray(16, 16);
    final ArrayList<ArrayList<Integer>> B11 = Async.make2DIntArray(16, 16);
    final Async l1 = new Async();
    final Callable<Object> _function = () -> {
      Object _xblockexpression = null;
      {
        final int i = l1.i();
        final Async l2 = new Async();
        final Callable<Object> _function_1 = () -> {
          Integer _xblockexpression_1 = null;
          {
            final int j = l2.i();
            Integer _xsynchronizedexpression = null;
            synchronized (MatMul64.lock) {
              Integer _xblockexpression_2 = null;
              {
                A00.get(i).set(j, a.get(i).get(j));
                B00.get(i).set(j, b.get(i).get(j));
                A01.get(i).set(j, a.get(i).get((j + 16)));
                B01.get(i).set(j, a.get(i).get((j + 16)));
                A10.get(i).set(j, a.get((i + 16)).get(j));
                B10.get(i).set(j, a.get((i + 16)).get(j));
                A11.get(i).set(j, a.get((i + 16)).get((j + 16)));
                _xblockexpression_2 = B11.get(i).set(j, a.get((i + 16)).get((j + 16)));
              }
              _xsynchronizedexpression = _xblockexpression_2;
            }
            _xblockexpression_1 = _xsynchronizedexpression;
          }
          return _xblockexpression_1;
        };
        _xblockexpression = l2.For(0, 15, _function_1, 4);
      }
      return _xblockexpression;
    };
    l1.For(0, 15, _function, 4);
    l1.reset();
    ArrayList<Callable<Object>> funcs = new ArrayList<Callable<Object>>();
    final Callable<Object> _function_1 = () -> {
      return MatMul64.mm16(A00, B00);
    };
    funcs.add(_function_1);
    final Callable<Object> _function_2 = () -> {
      return MatMul64.mm16(A01, B10);
    };
    funcs.add(_function_2);
    final Callable<Object> _function_3 = () -> {
      return MatMul64.mm16(A00, B01);
    };
    funcs.add(_function_3);
    final Callable<Object> _function_4 = () -> {
      return MatMul64.mm16(A01, B11);
    };
    funcs.add(_function_4);
    final Callable<Object> _function_5 = () -> {
      return MatMul64.mm16(A10, B11);
    };
    funcs.add(_function_5);
    final Callable<Object> _function_6 = () -> {
      return MatMul64.mm16(A11, B10);
    };
    funcs.add(_function_6);
    final Callable<Object> _function_7 = () -> {
      return MatMul64.mm16(A10, B01);
    };
    funcs.add(_function_7);
    final Callable<Object> _function_8 = () -> {
      return MatMul64.mm16(A11, B11);
    };
    funcs.add(_function_8);
    final ArrayList<Object> res1 = l1.FuncFor(funcs, 8);
    l1.reset();
    funcs.clear();
    final Callable<Object> _function_9 = () -> {
      Object _get = res1.get(0);
      Object _get_1 = res1.get(1);
      return MatMul64.ma16(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_9);
    final Callable<Object> _function_10 = () -> {
      Object _get = res1.get(2);
      Object _get_1 = res1.get(3);
      return MatMul64.ma16(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_10);
    final Callable<Object> _function_11 = () -> {
      Object _get = res1.get(4);
      Object _get_1 = res1.get(5);
      return MatMul64.ma16(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_11);
    final Callable<Object> _function_12 = () -> {
      Object _get = res1.get(6);
      Object _get_1 = res1.get(7);
      return MatMul64.ma16(((ArrayList) _get), ((ArrayList) _get_1));
    };
    funcs.add(_function_12);
    final ArrayList<Object> res2 = l1.FuncFor(funcs, 4);
    l1.reset();
    Object _get = res2.get(0);
    final ArrayList<ArrayList<Integer>> c00 = ((ArrayList<ArrayList<Integer>>) _get);
    Object _get_1 = res2.get(1);
    final ArrayList<ArrayList<Integer>> c01 = ((ArrayList<ArrayList<Integer>>) _get_1);
    Object _get_2 = res2.get(2);
    final ArrayList<ArrayList<Integer>> c10 = ((ArrayList<ArrayList<Integer>>) _get_2);
    Object _get_3 = res2.get(3);
    final ArrayList<ArrayList<Integer>> c11 = ((ArrayList<ArrayList<Integer>>) _get_3);
    final ArrayList<ArrayList<Integer>> resF = Async.make2DIntArray(32, 32);
    final Callable<Object> _function_13 = () -> {
      Object _xblockexpression = null;
      {
        final int i = l1.i();
        final Async l2 = new Async();
        final Callable<Object> _function_14 = () -> {
          Integer _xblockexpression_1 = null;
          {
            final int j = l2.i();
            resF.get(i).set(j, c00.get(i).get(j));
            resF.get((i + 16)).set(j, c10.get(i).get(j));
            resF.get(i).set((j + 16), c01.get(i).get(j));
            _xblockexpression_1 = resF.get((i + 16)).set((j + 16), c11.get(i).get(j));
          }
          return _xblockexpression_1;
        };
        _xblockexpression = l2.For(0, 15, _function_14, 4);
      }
      return _xblockexpression;
    };
    l1.For(0, 15, _function_13, 4);
    return resF;
  }
  
  public static ArrayList<Integer> waitAndPrint(final int i, final int j) {
    final Consumer<Integer> _function = (Integer k) -> {
      try {
        InputOutput.<Integer>println(k);
        Thread.sleep(100);
      } catch (Throwable _e) {
        throw Exceptions.sneakyThrow(_e);
      }
    };
    new IntegerRange(i, j).forEach(_function);
    ArrayList<Integer> x = new ArrayList<Integer>();
    x.add(Integer.valueOf((i - j)));
    return x;
  }
  
  public static void main(final String[] args) {
    final ArrayList<ArrayList<Integer>> x = new ArrayList<ArrayList<Integer>>();
    final Consumer<Integer> _function = (Integer i) -> {
      ArrayList<Integer> _arrayList = new ArrayList<Integer>();
      x.add(_arrayList);
      final Consumer<Integer> _function_1 = (Integer j) -> {
        x.get((i).intValue()).add(Integer.valueOf(((j).intValue() + 1)));
      };
      new IntegerRange(0, 31).forEach(_function_1);
    };
    new IntegerRange(0, 31).forEach(_function);
    final ArrayList<ArrayList<Integer>> y = new ArrayList<ArrayList<Integer>>();
    final Consumer<Integer> _function_1 = (Integer i) -> {
      ArrayList<Integer> _arrayList = new ArrayList<Integer>();
      y.add(_arrayList);
      final Consumer<Integer> _function_2 = (Integer j) -> {
        y.get((i).intValue()).add(Integer.valueOf(((j).intValue() + 1)));
      };
      new IntegerRange(0, 31).forEach(_function_2);
    };
    new IntegerRange(0, 31).forEach(_function_1);
    InputOutput.<String>println("Starting Parallel MM");
    long start = System.currentTimeMillis();
    ArrayList<ArrayList<Integer>> resF = MatMul64.mm32(x, y);
    long _currentTimeMillis = System.currentTimeMillis();
    long end = (_currentTimeMillis - start);
    InputOutput.<String>println((("Operation Took " + Long.valueOf(end)) + "ms"));
    for (final ArrayList<Integer> i : resF) {
      InputOutput.<ArrayList<Integer>>println(i);
    }
  }
}
