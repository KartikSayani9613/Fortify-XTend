package FortressToXtend

import java.util.ArrayList
import java.util.Set
import java.util.LinkedHashSet
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger
import java.util.concurrent.CompletableFuture
import java.util.stream.Collectors

class Sudoko {
	
	var private static board = new ArrayList<ArrayList<Set<Integer>>>()
	var private static emptySet = new LinkedHashSet<Integer>
	private static val ExecutorService pool = Executors.newFixedThreadPool(3)
	var static x = new AtomicInteger(5)
		

	def private static Set<Integer> initSet(){
		var initSet = (1..9).toSet
		return initSet
	}
	
	def private static removeElement(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem){
		var s = b.get(i).get(j)
		if(s.contains(elem)){
			s.remove(elem)
			b.get(i).set(j, s)
		}		
		
	}
	
	def private static propogateRow(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem){
		(0..8).forEach[k|
			if(j!=k)
				removeElement(b, i, k, elem)
		]
	}
	
	def private static propogateColumn(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem){
		(0..8).forEach[k|
			if(i!=k)
				removeElement(b, k, j, elem)
		]
	}
	
	def private static propogateSquare(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem){
		val int starti = (Math.floor(i/3)*3) as int
		val int startj = (Math.floor(i/3)*3) as int
		(starti..starti+2).forEach[k|
			(startj..startj+2).forEach[l|
				if(k!=i && l!=j)
					removeElement(b, k, l, elem)
			]
		]
	}
	
	def private static propogateSingleton(ArrayList<ArrayList<Set<Integer>>> b, int i, int j, int elem){
		propogateRow(b, i, j, elem)
		propogateColumn(b, i, j, elem)
		propogateSquare(b, i, j, elem)
	}
	
	def private static propogate(ArrayList<ArrayList<Set<Integer>>> b){
		var unsolved = 81
		var prevUnsolved = 82
		while(0 < unsolved && unsolved < prevUnsolved){
			prevUnsolved = unsolved
			unsolved = 0
			(0..8).forEach[i|
				(0..8).forEach[j|
					if(b.get(i).get(j).size == 1){
						var elem = b.get(i).get(j).min
						propogateSingleton(b, i, j, elem)
					}	
				]
			]
		}
	}
	
	def private static incX(int i){
		Thread.sleep(2000)
		println(x.andIncrement)
		return x
	}
	
	def static void main(String[] args) {
		
		
		(0..8).forEach[i|
			board.add(new ArrayList<Set<Integer>>)
			(0..8).forEach[
				board.get(i).add(initSet())
			]
		]
	}
}