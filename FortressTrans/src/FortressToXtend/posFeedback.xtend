package FortressToXtend

import java.util.ArrayList
import java.util.concurrent.atomic.AtomicInteger
import static extension com.google.common.collect.Lists.newArrayList
import java.util.Random

class posFeedback {
	
	def static void main(String[] args) {
		
		
		var start = System.currentTimeMillis
		val l1 = new Async
		
		for(i:0..5000){
			var known = Async.makeArray(1, 5)
			var kfacts = Async.makeArray(0, 0)
			var entity = new Entity(0, known, kfacts)
			while(!entity.tick){
				
			}
//			println(i+"\t"+entity.asString)
		}
		
//		l1.For(0, 5000, [
//			var known = Async.makeArray(1, 5)
//			var kfacts = Async.makeArray(0, 0)
//			var entity = new Entity(0, known, kfacts)
//			while(!entity.tick){
//			}
////			println(l1.i+"\t"+entity.asString)
//			return null
//		], 1024)
//		
		var end = System.currentTimeMillis - start
		println(end)
//		println(Stats.asString)
		
	}
}


class Stats{
	val static maxTime = 365
	var static posOut = 1
	var static negOut = 0
	var static histogram = Async.make2DIntArray(2, 365)
	var static overAYear = new AtomicInteger(0)
	var static double scale
	
	def static asString(){
		
		var res = median((0..364), (0..364).map[i|
			histogram.get(posOut).get(i) + histogram.get(negOut).get(i)
		].newArrayList)
		var n = res.get(0) + overAYear.intValue
		var med = res.get(1)
		
		res = median((0..364), (0..364).map[i| histogram.get(posOut).get(i)].newArrayList)
		var pos = res.get(0)
		var posMed = res.get(1)
		
		res = median((0..364), (0..364).map[i| histogram.get(negOut).get(i)].newArrayList)
		var neg = res.get(0)
		var negMed = res.get(1)
		
		var int maxEntry
		
		if( histogram.get(0).max > histogram.get(1).max){
			maxEntry =  histogram.get(0).max
		}
		else{
			maxEntry =  histogram.get(1).max
		}
		
		scale = 30.0d/maxEntry
		
		var histo = '''
					Stats:
					n =  «fmt3(n)»		median = «fmt3(med)»
					pos = «fmt3(pos)»	«percent(pos, n)»%	median = «fmt3(posMed)»
					neg = «fmt3(neg)»	«percent(neg, n)»%	median = «fmt3(negMed)»
					over= «fmt3(overAYear.intValue)»	«percent(overAYear.intValue, n)»%
					Histogram:
		'''	
		for(i:0..364)
			histo = histo + showHist(i)
		
		return histo
	}
		
	def static barlen(int i){
		if(i>0){
			if(scale * i > 1)
				return (scale * i).intValue
			else 
				return 1
		}
		else
			return 0
	}
	
	def static percent(int a, int b){
		fmt3((200*a+b)/(2*b)).toString
	}
	
	def static showHist(int i) {
		val ng = histogram.get(negOut).get(i)
		val ps = histogram.get(negOut).get(i)
		var String retVal = ""
		if(ng+ps != 0){
			retVal = retVal + "\n" + fmt3(i).toString 
					+ ":" + fmt3(ps)
					+ "|" + fmt3(ng)
					
			var scn = barlen(ng)
			var scp = barlen(ps)
			retVal = retVal + repeat(32-scp, " ") + repeat(scp, "+") 
					+ "|" + repeat(scn, "-")
		}
		else
			retVal = retVal + "\n" + fmt3(i)
		return retVal
	}
	
	def static repeat(int times, String s){
		var String retS = ""
		for(i:0..times-2)
			retS = retS+s
		return retS
		
	}
	
	def static fmt3(int i){
		if(i<10)
			return "   "+i
		else if(i<100)
			return "  "+i
		else if(i<1000)
			return " "+i
		else
			return ""+i
	}
	
	def static median(IntegerRange gen, ArrayList<Integer> bucket){
		var n = gen.map[i| i*bucket.get(i)].reduce[i,j| i+j]
		var half = n/2
		var s = 0
		var int med
		for(i:gen){
			if(s<= half){
				med = i
				s = s + bucket.get(i)*i
			}
		}
		var res = new ArrayList
		res.add(n)
		res.add(med)
		return res
	}
	
	def static recordOver(){
		overAYear.andIncrement
	}
	
	def static record(int time, int result){
		if(time >= maxTime)
			println("Recording Overtime"+time)
		else{
			synchronized(histogram){
				histogram.get(result).set(time, histogram.get(result).get(time)+1)
			}
		}
	}	
}


class Entity {
	var int positivity
	var ArrayList<Integer> known
	var ArrayList<Integer> initFacts
	var time = -1
	var factNeg = -1
	var factPos = 1
	var factUnk = 0
	
	var facts = newIntArrayOfSize(10)

	new(int p, ArrayList<Integer> k, ArrayList<Integer> i){	
		positivity = p
		known = k
		initFacts = i
		filler
	}
	
	def filler(){
		val negFacts = initFacts.get(0)
		val totFacts = negFacts + initFacts.get(1)
		(0..9).forEach[i|
			if(i<negFacts)
				facts.set(i, factNeg)
			else if(i < totFacts)
				facts.set(i, factPos)
			else
				facts.set(i, factUnk)
		]
	}
	
	def asString(){
		return '''
		Entity(«positivity», [«known.get(0)» «known.get(1)»]) t=«time»
		«facts.toString»'''
	}
	
	def otherResult(int result){
		1 - result
	}
	
	def eventToResult(int kind){
		(kind+1)/2
	}
	
	def unlearn(int fact, int kind){
//		Thread.sleep(10)
		facts.set(fact, factUnk)
		var result =otherResult(eventToResult(kind))
		known.set(result, known.get(result)-1)
		if(known.get(result) < 0)
			println("Result "+result+" dipped below 0!") 
	}
	
	def learn(int fact, int kind){
//		Thread.sleep(10)
		facts.set(fact, kind)
		var result = eventToResult((kind))
		known.set(result, known.get(result)+1)
		if(known.get(0) + known.get(1) >= 10 && (known.get(result) >= 2*known.get(otherResult(result)))){
				Stats.record(time, result)
				return true
		}
		else
			return false
	}
	
	def event(int kind){
		positivity += kind
		var r = new Random
		var fact = r.nextInt(10)
		switch facts.get(fact){
			case kind:	return false
			case 0 : 	return learn(fact, kind)
			default	:	{
							unlearn(fact, kind)
							return false
						}
		}
	}
	
	def min(int a, int b){
		if(a<b)
			a
		else
			b
	}
	
	def tick(){
		
		time += 1
		var r = new Random
		var roll = r.nextInt(20)
		var bot = 0
		var top = 0
		if(positivity >=0){
			bot = 1 + positivity
			top = 19
		}
		else{
			bot = 1
			top = 19 + positivity
		}
		if(time >=365){
			Stats.recordOver
			return true
		}
		else if(roll < min(bot, 19))
			event(factPos)
		else if(roll >=top)
			event(factNeg)
		else
			false
	}
}

