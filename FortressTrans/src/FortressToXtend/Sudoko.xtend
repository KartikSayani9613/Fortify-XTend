package FortressToXtend

import java.util.ArrayList
import java.util.Set
import java.util.concurrent.atomic.AtomicInteger

class Sudoko {

	var private static board = new ArrayList<ArrayList<Set<Integer>>>()
	var private static unsolved = new AtomicInteger(81)
	def private static Set<Integer> initSet() {
		var initSet = (1 .. 9).toSet
		return initSet
	}

	def private static removeElement(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem) {
//		Thread.sleep(100)
		synchronized(b){
			var s = b.get(i).get(j)
			if (s.contains(elem)) {
				s.remove(elem)
					b.get(i).set(j, s)	
			}
		}
	}

	def private static propogateRow(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem) {
		(0 .. 8).forEach [ k |
			if (k != j)
				removeElement(b, i, k, elem)
		]
	}

	def private static propogateColumn(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem) {
		(0 .. 8).forEach [ k |
			if (k != i)
				removeElement(b, k, j, elem)
		]
	}

	def private static propogateSquare(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem) {
		val int starti = (Math.floor(i / 3) * 3) as int
		val int startj = (Math.floor(j / 3) * 3) as int
		(starti .. starti + 2).forEach [ k |
			(startj .. startj + 2).forEach [ l |
				if (k != i && l != j)
					removeElement(b, k, l, elem)
			]
		]
	}

	def private static propogateSingleton(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem) {
		Thread.sleep(100)
		propogateRow(b, i, j, elem)
		propogateColumn(b, i, j, elem)
		propogateSquare(b, i, j, elem)
	}

	def private static propogate(ArrayList<ArrayList<Set<Integer>>> b) {
		
		var prevUnsolved = 82
		unsolved.set(81)
		while (0 < unsolved.intValue && unsolved.intValue < prevUnsolved) {
			prevUnsolved = unsolved.intValue
			unsolved.set(0)
			
//			(0..8).forEach[i|
//				(0..8).forEach[j|
//					if(b.get(i).get(j).size == 1){
//						val elem = b.get(i).get(j).min
//						propogateSingleton(b, i, j, elem)
//					}
//					else
//						unsolved.getAndIncrement
//				]
//			]



			val l1 = new Async()
			l1.For(0, 8, [
				val l2 = new Async()
				val i = l1.i
				l2.For(0, 8, [
					val j = l2.i
					if(b.get(i).get(j).size == 1){
						val elem = b.get(i).get(j).min
						propogateSingleton(b, i, j, elem)
					}
					else
						unsolved.getAndIncrement
					return null
				], 4)
				return null
			], 4)
			println("Remaining "+unsolved)
		}
	}

	def private static createRow() {
		board.add(new ArrayList<Set<Integer>>)
		return board.size - 1
	}
	
	def static void setVal(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem){
		var x = initSet
		for(ii: x) {
			if(ii != elem){
				b.get(i).get(j).remove(ii)
			}
				
		}
	}

	def static void main(String[] args) {
		
//		(0..8).forEach[
//			val rnum = createRow()
//			(0..8).forEach[
//				board.get(rnum).add(initSet)
//			]
//		]
		
		
		
		val l1 = new Async()
		l1.For(0, 8, [
			val rnum = createRow()
			val l2 = new Async()
			l2.For(0, 8, [
				board.get(rnum).add(initSet)
				return null
			], 8)
			return null
		], 8)



		board.setVal(0, 0, 8)
		board.setVal(0, 2, 6)
		board.setVal(0, 3, 1)
		board.setVal(0, 5, 9)
		board.setVal(0, 6, 3)
		board.setVal(0, 8, 5)
		board.setVal(1, 1, 9)
		board.setVal(1, 4, 8)
		board.setVal(1, 6, 4)
		board.setVal(2, 1, 7)
		board.setVal(2, 2, 1)
		board.setVal(2, 8, 6)
		board.setVal(3, 3, 9)
		board.setVal(3, 4, 2)
		board.setVal(3, 6, 5)
		board.setVal(3, 7, 3)
		board.setVal(4, 2, 9)
		board.setVal(4, 4, 6)
		board.setVal(4, 6, 7)
		board.setVal(5, 1, 3)
		board.setVal(5, 2, 4)
		board.setVal(5, 4, 7)
		board.setVal(5, 5, 8)
		board.setVal(6, 0, 3)
		board.setVal(6, 6, 1)
		board.setVal(6, 7, 4)
		board.setVal(7, 2, 5)
		board.setVal(7, 4, 1)
		board.setVal(7, 7, 9)
		board.setVal(8, 0, 9)
		board.setVal(8, 2, 7)
		board.setVal(8, 3, 8)
		board.setVal(8, 5, 4)
		board.setVal(8, 6, 6)
		board.setVal(8, 8, 2)
		
		
		
		
		println("originally")
		(0..8).forEach[i|
			(0..8).forEach[j|
				if(board.get(i).get(j).size == 1)
					print("\t"+board.get(i).get(j))
				else
					print("\t_")	
			]
			println("")
		]
		
		var start = System.currentTimeMillis()
		propogate(board)
		
		
		
		var end = System.currentTimeMillis - start
		println("Time Taken "+end)
		(0..8).forEach[i|
			(0..8).forEach[j|
				print("\t"+board.get(i).get(j))
			]
			println("")
		]
	}
}
