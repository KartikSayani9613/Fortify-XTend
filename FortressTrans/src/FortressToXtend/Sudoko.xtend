package FortressToXtend

import java.util.ArrayList
import java.util.Set

class Sudoko {

	var private static board = new ArrayList<ArrayList<Set<Integer>>>()

	def private static Set<Integer> initSet() {
		var initSet = (1 .. 9).toSet
		return initSet
	}

	def private static removeElement(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem) {
		var s = b.get(i).get(j)
		if (s.contains(elem)) {
			s.remove(elem)
			b.get(i).set(j, s)
		}

	}

	def private static propogateRow(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem) {
		(0 .. 8).forEach [ k |
			if (j != k)
				removeElement(b, i, k, elem)
		]
	}

	def private static propogateColumn(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem) {
		(0 .. 8).forEach [ k |
			if (i != k)
				removeElement(b, k, j, elem)
		]
	}

	def private static propogateSquare(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem) {
		val int starti = (Math.floor(i / 3) * 3) as int
		val int startj = (Math.floor(i / 3) * 3) as int
		(starti .. starti + 2).forEach [ k |
			(startj .. startj + 2).forEach [ l |
				if (k != i && l != j)
					removeElement(b, k, l, elem)
			]
		]
	}

	def private static propogateSingleton(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem) {
		propogateRow(b, i, j, elem)
		propogateColumn(b, i, j, elem)
		propogateSquare(b, i, j, elem)
	}

	def private static propogate(ArrayList<ArrayList<Set<Integer>>> b) {
		var unsolved = 81
		var prevUnsolved = 82
		while (0 < unsolved && unsolved < prevUnsolved) {
			prevUnsolved = unsolved
			unsolved = 0

			val async = new Async()
			async.For(0, 5, [
				val async2 = new Async()
				async2.For(0, 5, [
					println("Hello"+" "+async.iter+" "+async2.iter)

//					if (b.get(async.iter).get(async2.iter).size == 1) {
//						var elem = b.get(async.iter).get(async2.iter).min
//						propogateSingleton(b, async.iter, async.iter, elem)
//						println(async.iter+" "+async2.iter)
//						return null
//					}
				], 100)
				return null
			], 100) //
			
		}
	}

	def private static createRow() {
		board.add(new ArrayList<Set<Integer>>)
		return board.size - 1
	}

	def static void main(String[] args) {
		
		val i = new Async()
		i.For(0, 5, [
			val j = new Async()
			j.For(0, 5, [
//				println(i.iter+" "+j.iter)
				return null	
			])
			return null
		])
		
//		val i = new Async()
//		i.For(0, 8, [
//			val rnum = createRow()
//			val j = new Async()
//			j.For(0, 8, [
//					board.get(rnum).add(initSet)
//					return null
//					], 100)
//			return null
//			], 100)
//		
//		board.get(0).get(0).removeAll(1, 2, 3, 4 ,5 ,6 ,7, 8)
//		propogate(board)
//		println(board)
	}
}
