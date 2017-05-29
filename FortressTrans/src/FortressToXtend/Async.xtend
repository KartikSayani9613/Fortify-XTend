package FortressToXtend

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.CompletableFuture
import java.util.ArrayList
import java.util.concurrent.Callable
import java.util.concurrent.atomic.AtomicInteger
import java.util.Collections

public class Async {

	public static var loopCount = new AtomicInteger(0)
	public var iter = Collections.synchronizedList(new ArrayList<Integer>)
	public var int parallelism
	public var Object retVal
	def public For(int start, int end, Callable<Object> myFunc){
		parallelism = 1
		retVal = For(start, end, myFunc, parallelism)
		return retVal
	}
	
	def int i(){
		return iter.remove(iter.size-1)
	}
	
	def public For(int start, int end, Callable<Object> myFunc, int parallelism){
		
		synchronized(iter){
			(start..end).forEach[i|
				iter.add(i)
			]
		}
		
		val ExecutorService pool = Executors.newFixedThreadPool(parallelism)
		val m = new ArrayList<CompletableFuture<Object>>()
		
		(start..end).forEach[
			m.add(CompletableFuture.supplyAsync([
				retVal = myFunc.call
			], pool))	
		]
		
		while(!m.allDone)
		{}
		pool.shutdown()
		return retVal
		
	}

	
	

	def public <T> allDone(ArrayList<CompletableFuture<T>> aray)
	{
		for(b: aray)
			while(!b.isDone)
			{
				
//				println("We'll wait for " + Thread.currentThread)
			}
		return true
	}
	

}
