package FortressToXtend

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.CompletableFuture
import java.util.ArrayList
import java.util.concurrent.Callable
import java.util.concurrent.atomic.AtomicInteger
import java.util.Stack

public class Async {
	public static var loopCount = new AtomicInteger(0)
	public var iter = new Stack<Integer>
	public var int parallelism
	public var Object retVal
	public var retVals = new ArrayList<Object>
	def public For(int start, int end, Callable<Object> myFunc){
		parallelism = 1
		retVal = For(start, end, myFunc, parallelism)
		return retVal
	}
	
	def reset(){
		loopCount = new AtomicInteger(0)
		iter = new Stack<Integer>
		parallelism = -1
		retVal = null
	}
	
	def int i(){
		synchronized(iter){
			return iter.pop
		}
		
	}
	
	def public For(int start, int end, Callable<Object> myFunc, int par){
		parallelism = par
		synchronized(iter){
			(end..start).forEach[i|
				iter.push(i)
			]
		}
		
		val ExecutorService pool = Executors.newFixedThreadPool(parallelism)
		val m = new ArrayList<CompletableFuture<Object>>()
		(start..end).forEach[
			m.add(CompletableFuture.supplyAsync([
					retVal = myFunc.call					
			], pool))	
		]
		m.allDone
		pool.shutdown()
		return retVal
		
	}

	def public FuncFor(ArrayList<Callable<Object>> myFuncs){
		parallelism = 1
		FuncFor(myFuncs, parallelism)
		return retVals
	}
	
	def public FuncFor(ArrayList<Callable<Object>> myFuncs, int par){
		parallelism = par
		synchronized(iter){
			(0..myFuncs.size-1).forEach[i|
				iter.push(i)
			]
		}
		val ExecutorService pool = Executors.newFixedThreadPool(parallelism)
		val m = new ArrayList<CompletableFuture<Object>>()
		(0..myFuncs.size-1).forEach[i|
			m.add(CompletableFuture.supplyAsync([
				retVals.add(myFuncs.get(i).call)					
			], pool))
		]
		m.allDone
		pool.shutdown()
		return retVals
	}
	

	def public <T> allDone(ArrayList<CompletableFuture<T>> aray)
	{
		
		for(b: aray){
			while(!b.isDone)
			{
				Thread.sleep(10)
			}
		}
			
		return true
	}
	
	def public static make2DIntArray(int... dims){
		val array = new ArrayList()
		(0..dims.get(0)-1).forEach[
			array.add(new ArrayList)
		]
		(0..dims.get(0)-1).forEach[i|
			(0..dims.get(1)-1).forEach[
				array.get(i).add(0)
			]
		]
		
		return array
	}
	
	def public static makeArray(int... vals){
		val array = new ArrayList<Integer>()
		(0..vals.size-1).forEach[i|
			array.add(vals.get(i))
		]
		return array
	}
	

}
