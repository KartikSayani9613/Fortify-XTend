package FortressToXtend

import java.util.Random
import java.util.concurrent.atomic.AtomicInteger

class Buffons {
		
	 var static hits = new AtomicInteger(0)
	 var static n = new AtomicInteger(0)
	 def static void main(String[] args) {
	 	val needleLength = 20.0d
		val numRows = 10.0d
		val tableHeight = needleLength * numRows
	 	var start = System.currentTimeMillis
	 	println("Starting Parallel Buffons")
	 	val l1 = new Async
	 	val r = new Random()
	 	l1.For(0, 3000, [
//	 	(0..3000).forEach[
	 		val delta_x = 2*r.nextDouble - 1.0
	 		val delta_y = 2*r.nextDouble - 1.0
	 		val rsq = Math.pow(delta_x, 2) + Math.pow(delta_y, 2)
	 		if (0.0 < rsq && rsq < 1.0){
	 			var y1 = tableHeight * r.nextDouble
	 			var y2 = y1 + needleLength*(delta_y/Math.sqrt(rsq))
				var y_L = Math.min(y1, y2)
				var y_H = Math.max(y1, y2)
				var temp1 = y_L/ needleLength
				var temp2 = y_H/ needleLength
				if (Math.ceil(temp1) == Math.floor(temp2)){
					hits.incrementAndGet
				}
				n.incrementAndGet
	 		}
	 	], 4)
	 	
	 	var probability = hits.floatValue/n.floatValue
	 	var pi_est = 2.0/probability
	 	var end = System.currentTimeMillis - start
	 	println("Time taken: " + end)
	 	println("Hits:\t"+hits+"\tn:\t"+n)
	 	println("Buffons: estimated Pi = "+pi_est)
	 }
}