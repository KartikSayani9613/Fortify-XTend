package FortressToXtend

import java.util.ArrayList
import java.util.concurrent.Callable

class MatMul64 {

	var static lock = new Object
	
	def static ma16(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b){
		val c = Async.make2DIntArray(16, 16)
		(0..15).forEach[i|
			(0..15).forEach[j|
				c.get(i).set(j, a.get(i).get(j) + b.get(i).get(j))
			]
		]
		return c
	}
	
	def static ma8(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b){
		val c = Async.make2DIntArray(8, 8)
		(0..7).forEach[i|
			(0..7).forEach[j|
				c.get(i).set(j, a.get(i).get(j) + b.get(i).get(j))
			]
		]
		
		return c
	}
	
	def static ma4(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b){
		val c = Async.make2DIntArray(4, 4)
		(0..3).forEach[i|
			(0..3).forEach[j|
				c.get(i).set(j, a.get(i).get(j) + b.get(i).get(j))
			]
		]
		
		return c
	}
	
	def static ma2(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b) {
	
		val c = Async.make2DIntArray(2, 2)
		(0..1).forEach[i|
			(0..1).forEach[j|
				c.get(i).set(j, a.get(i).get(j) + b.get(i).get(j))
			]
		]
		
		return c	
	}
	
	def static mm2(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b) {
		val A00 = a.get(0).get(0)
		val A01 = a.get(0).get(1)
		val A10 = a.get(1).get(0)
		val A11 = a.get(1).get(1)
		val B00 = b.get(0).get(0)
		val B01 = b.get(0).get(1)
		val B10 = b.get(1).get(0)
		val B11 = b.get(1).get(1)
		
		val res1 = new ArrayList<Integer>
		res1.add(A00 * B00)
		res1.add(A01 * B10)
		res1.add(A00 * B01)
		res1.add(A01 * B11)
		res1.add(A10 * B00)
		res1.add(A11 * B10)
		res1.add(A10 * B01)
		res1.add(A11 * B11)
		
		val resF = Async.make2DIntArray(2,2)
		resF.get(0).set(0, res1.get(0)+res1.get(1))
		resF.get(0).set(1, res1.get(2)+res1.get(3))
		resF.get(1).set(0, res1.get(4)+res1.get(5))
		resF.get(1).set(1, res1.get(6)+res1.get(7))
		return resF
	}
	
	def static mm4(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b) {
		val A00 = Async.make2DIntArray(2, 2)
		val A01 = Async.make2DIntArray(2, 2)
		val A10 = Async.make2DIntArray(2, 2)
		val A11 = Async.make2DIntArray(2, 2)
		val B00 = Async.make2DIntArray(2, 2)
		val B01 = Async.make2DIntArray(2, 2)
		val B10 = Async.make2DIntArray(2, 2)
		val B11 = Async.make2DIntArray(2, 2)
		
		val l1 = new Async()
		l1.For(0, 1, [
			val i = l1.i
			val l2 = new Async
			l2.For(0, 1, [
				val j = l2.i
				synchronized (lock) {
					A00.get(i).set(j,a.get(i).get(j))
					B00.get(i).set(j,b.get(i).get(j))
					A01.get(i).set(j,a.get(i).get(j + 2))
					B01.get(i).set(j,a.get(i).get(j + 2))
					A10.get(i).set(j,a.get(i + 2).get(j))
					B10.get(i).set(j,a.get(i + 2).get(j))
					A11.get(i).set(j,a.get(i + 2).get(j + 2))
					B11.get(i).set(j,a.get(i + 2).get(j + 2))
				}
			], 4)
		], 4)
		
		l1.reset
		var funcs = new ArrayList<Callable<Object>>
		
		funcs.add([mm2(A00, B00)])
		funcs.add([mm2(A01, B10)])
		funcs.add([mm2(A00, B01)])
		funcs.add([mm2(A01, B11)])
		funcs.add([mm2(A10, B11)])
		funcs.add([mm2(A11, B10)])
		funcs.add([mm2(A10, B01)])
		funcs.add([mm2(A11, B11)])
		val res1 = l1.FuncFor(funcs, 8)
		funcs.clear
//		println("mm4 "+res1.size)
		
		funcs.add([ma2(res1.get(0) as ArrayList, res1.get(1) as ArrayList)])
		funcs.add([ma2(res1.get(2) as ArrayList, res1.get(3) as ArrayList)])
		funcs.add([ma2(res1.get(4) as ArrayList, res1.get(5) as ArrayList)])
		funcs.add([ma2(res1.get(6) as ArrayList, res1.get(7) as ArrayList)])
		val res2 = l1.FuncFor(funcs, 4)
		l1.reset
		
		val c00 = res2.get(0) as ArrayList<ArrayList<Integer>>
		val c01 = res2.get(1) as ArrayList<ArrayList<Integer>>
		val c10 = res2.get(2) as ArrayList<ArrayList<Integer>>
		val c11 = res2.get(3) as ArrayList<ArrayList<Integer>>
		
		val resF = Async.make2DIntArray(4, 4)
		l1.For(0, 1, [
			val i = l1.i
			val l2 = new Async
			l2.For(0, 1, [
				val j = l2.i
				resF.get(i).set(j, c00.get(i).get(j))
				resF.get(i+2).set(j, c10.get(i).get(j))
				resF.get(i).set(j+2, c01.get(i).get(j))
				resF.get(i+2).set(j+2, c11.get(i).get(j))
			], 2)
		], 2)
		
		return resF
		
	}
	
	def static mm8(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b) {
		val A00 = Async.make2DIntArray(4, 4)
		val A01 = Async.make2DIntArray(4, 4)
		val A10 = Async.make2DIntArray(4, 4)
		val A11 = Async.make2DIntArray(4, 4)
		val B00 = Async.make2DIntArray(4, 4)
		val B01 = Async.make2DIntArray(4, 4)
		val B10 = Async.make2DIntArray(4, 4)
		val B11 = Async.make2DIntArray(4, 4)
		
		val l1 = new Async()
		l1.For(0, 3, [
			val i = l1.i
			val l2 = new Async
			l2.For(0, 3, [
				val j = l2.i
				synchronized (lock) {
					A00.get(i).set(j,a.get(i).get(j))
					B00.get(i).set(j,b.get(i).get(j))
					A01.get(i).set(j,a.get(i).get(j + 4))
					B01.get(i).set(j,a.get(i).get(j + 4))
					A10.get(i).set(j,a.get(i + 4).get(j))
					B10.get(i).set(j,a.get(i + 4).get(j))
					A11.get(i).set(j,a.get(i + 4).get(j + 4))
					B11.get(i).set(j,a.get(i + 4).get(j + 4))
				}
			], 4)
		], 4)
		
		l1.reset
		var funcs = new ArrayList<Callable<Object>>
		
		funcs.add([mm4(A00, B00)])
		funcs.add([mm4(A01, B10)])
		funcs.add([mm4(A00, B01)])
		funcs.add([mm4(A01, B11)])
		funcs.add([mm4(A10, B11)])
		funcs.add([mm4(A11, B10)])
		funcs.add([mm4(A10, B01)])
		funcs.add([mm4(A11, B11)])
		val res1 = l1.FuncFor(funcs, 8)
		funcs.clear
//		println("mm8 "+res1.size)
		
		funcs.add([ma4(res1.get(0) as ArrayList, res1.get(1) as ArrayList)])
		funcs.add([ma4(res1.get(2) as ArrayList, res1.get(3) as ArrayList)])
		funcs.add([ma4(res1.get(4) as ArrayList, res1.get(5) as ArrayList)])
		funcs.add([ma4(res1.get(6) as ArrayList, res1.get(7) as ArrayList)])
		val res2 = l1.FuncFor(funcs, 4)
		l1.reset
		
		val c00 = res2.get(0) as ArrayList<ArrayList<Integer>>
		val c01 = res2.get(1) as ArrayList<ArrayList<Integer>>
		val c10 = res2.get(2) as ArrayList<ArrayList<Integer>>
		val c11 = res2.get(3) as ArrayList<ArrayList<Integer>>
		
		val resF = Async.make2DIntArray(8, 8)
		l1.For(0, 3, [
			val i = l1.i
			val l2 = new Async
			l2.For(0, 3, [
				val j = l2.i
				resF.get(i).set(j, c00.get(i).get(j))
				resF.get(i+4).set(j, c10.get(i).get(j))
				resF.get(i).set(j+4, c01.get(i).get(j))
				resF.get(i+4).set(j+4, c11.get(i).get(j))
			], 4)
		], 4)
		
		return resF
		
		
	}

	def static mm16(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b) {
		
		val A00 = Async.make2DIntArray(8, 8)
		val A01 = Async.make2DIntArray(8, 8)
		val A10 = Async.make2DIntArray(8, 8)
		val A11 = Async.make2DIntArray(8, 8)
		val B00 = Async.make2DIntArray(8, 8)
		val B01 = Async.make2DIntArray(8, 8)
		val B10 = Async.make2DIntArray(8, 8)
		val B11 = Async.make2DIntArray(8, 8)
		
		val l1 = new Async()
		l1.For(0, 7, [
			val i = l1.i
			val l2 = new Async
			l2.For(0, 7, [
				val j = l2.i
				synchronized (lock) {
					A00.get(i).set(j,a.get(i).get(j))
					B00.get(i).set(j,b.get(i).get(j))
					A01.get(i).set(j,a.get(i).get(j + 8))
					B01.get(i).set(j,a.get(i).get(j + 8))
					A10.get(i).set(j,a.get(i + 8).get(j))
					B10.get(i).set(j,a.get(i + 8).get(j))
					A11.get(i).set(j,a.get(i + 8).get(j + 8))
					B11.get(i).set(j,a.get(i + 8).get(j + 8))
				}
			], 4)
		], 4)
		
		l1.reset
		var funcs = new ArrayList<Callable<Object>>
		
		funcs.add([mm8(A00, B00)])
		funcs.add([mm8(A01, B10)])
		funcs.add([mm8(A00, B01)])
		funcs.add([mm8(A01, B11)])
		funcs.add([mm8(A10, B11)])
		funcs.add([mm8(A11, B10)])
		funcs.add([mm8(A10, B01)])
		funcs.add([mm8(A11, B11)])
		val res1 = l1.FuncFor(funcs, 8)
		funcs.clear
//		println("mm16 "+res1.size)
		
		funcs.add([ma8(res1.get(0) as ArrayList, res1.get(1) as ArrayList)])
		funcs.add([ma8(res1.get(2) as ArrayList, res1.get(3) as ArrayList)])
		funcs.add([ma8(res1.get(4) as ArrayList, res1.get(5) as ArrayList)])
		funcs.add([ma8(res1.get(6) as ArrayList, res1.get(7) as ArrayList)])
		val res2 = l1.FuncFor(funcs, 4)
		l1.reset
		
		val c00 = res2.get(0) as ArrayList<ArrayList<Integer>>
		val c01 = res2.get(1) as ArrayList<ArrayList<Integer>>
		val c10 = res2.get(2) as ArrayList<ArrayList<Integer>>
		val c11 = res2.get(3) as ArrayList<ArrayList<Integer>>
		
		val resF = Async.make2DIntArray(16, 16)
		l1.For(0, 7, [
			val i = l1.i
			val l2 = new Async
			l2.For(0, 7, [
				val j = l2.i
				resF.get(i).set(j, c00.get(i).get(j))
				resF.get(i+8).set(j, c10.get(i).get(j))
				resF.get(i).set(j+8, c01.get(i).get(j))
				resF.get(i+8).set(j+8, c11.get(i).get(j))
			], 4)
		], 4)
		
		return resF
		
	}

	def static mm32(ArrayList<ArrayList<Integer>> a, ArrayList<ArrayList<Integer>> b) {

		val A00 = Async.make2DIntArray(16, 16)
		val A01 = Async.make2DIntArray(16, 16)
		val A10 = Async.make2DIntArray(16, 16)
		val A11 = Async.make2DIntArray(16, 16)
		val B00 = Async.make2DIntArray(16, 16)
		val B01 = Async.make2DIntArray(16, 16)
		val B10 = Async.make2DIntArray(16, 16)
		val B11 = Async.make2DIntArray(16, 16)
		
		
		val l1 = new Async
		l1.For(0, 15, [
			val i = l1.i
			val l2 = new Async
			l2.For(0, 15, [
				val j = l2.i
				synchronized (lock) {
					A00.get(i).set(j, a.get(i).get(j))
					B00.get(i).set(j,b.get(i).get(j))
					A01.get(i).set(j,a.get(i).get(j + 16))
					B01.get(i).set(j,a.get(i).get(j + 16))
					A10.get(i).set(j,a.get(i + 16).get(j))
					B10.get(i).set(j,a.get(i + 16).get(j))
					A11.get(i).set(j,a.get(i + 16).get(j + 16))
					B11.get(i).set(j,a.get(i + 16).get(j + 16))
				}
			], 4)
		], 4)
		
				
		l1.reset
		var funcs = new ArrayList<Callable<Object>>
		
		funcs.add([mm16(A00, B00)])
		funcs.add([mm16(A01, B10)])
		funcs.add([mm16(A00, B01)])
		funcs.add([mm16(A01, B11)])
		funcs.add([mm16(A10, B11)])
		funcs.add([mm16(A11, B10)])
		funcs.add([mm16(A10, B01)])
		funcs.add([mm16(A11, B11)])
		val res1 = l1.FuncFor(funcs, 8)
		
//		println("mm32 "+res1.size)
		
		l1.reset
		funcs.clear
		funcs.add([ma16(res1.get(0) as ArrayList, res1.get(1) as ArrayList)])
		funcs.add([ma16(res1.get(2) as ArrayList, res1.get(3) as ArrayList)])
		funcs.add([ma16(res1.get(4) as ArrayList, res1.get(5) as ArrayList)])
		funcs.add([ma16(res1.get(6) as ArrayList, res1.get(7) as ArrayList)])
		val res2 = l1.FuncFor(funcs, 4)
		l1.reset
		
//		println(res2.size)
		
		val c00 = res2.get(0) as ArrayList<ArrayList<Integer>>
		val c01 = res2.get(1) as ArrayList<ArrayList<Integer>>
		val c10 = res2.get(2) as ArrayList<ArrayList<Integer>>
		val c11 = res2.get(3) as ArrayList<ArrayList<Integer>>
		
		val resF = Async.make2DIntArray(32, 32)
		l1.For(0, 15, [
			val i = l1.i
			val l2 = new Async
			l2.For(0, 15, [
				val j = l2.i
				resF.get(i).set(j, c00.get(i).get(j))
				resF.get(i+16).set(j, c10.get(i).get(j))
				resF.get(i).set(j+16, c01.get(i).get(j))
				resF.get(i+16).set(j+16, c11.get(i).get(j))
			], 4)
		], 4)
		
		return resF
	}

	def static waitAndPrint(int i, int j){
		(i..j).forEach[k|
			println(k)
			Thread.sleep(100)
		]
		var x = new ArrayList<Integer>
		x.add(i-j)
		return x
	}

	def static void main(String[] args) {

		val x = new ArrayList<ArrayList<Integer>>()
		(0 .. 31).forEach [ i |
			x.add(new ArrayList<Integer>)
			(0 .. 31).forEach [ j |
				x.get(i).add(j+1)
			]
		]

		val y = new ArrayList<ArrayList<Integer>>()
		(0 .. 31).forEach [ i |
			y.add(new ArrayList<Integer>)
			(0 .. 31).forEach [j|
				y.get(i).add(j+1)
			]
		]
		
		println("Starting Parallel MM")
		var start = System.currentTimeMillis
		var resF = mm32(x, y)
		var end = System.currentTimeMillis - start 
		println("Operation Took "+end+"ms")
		for(i: resF){
			println(i)
		}
		

	}
}
