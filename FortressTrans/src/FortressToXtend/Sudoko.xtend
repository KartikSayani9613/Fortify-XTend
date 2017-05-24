package FortressToXtend

import java.util.ArrayList
import java.util.Set

class Sudoko {
	var private static r = new ArrayList<ArrayList<Set<Integer>>>()
	def private static Set<Integer> initSet(){
		var initSet = (1..9).toSet
		return initSet
	}
	
	def static void main(String[] args) {
		
		(0..8).forEach[i|
			r.add(new ArrayList<Set<Integer>>)
			(0..8).forEach[j|
				r.get(i).add(initSet())
			]
		]
	}
}