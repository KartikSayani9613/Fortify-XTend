package FortressToXtend

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.CompletableFuture
import java.util.ArrayList
import java.util.concurrent.Callable
import java.util.concurrent.atomic.AtomicReferenceArray

public class Async {

	private static var iter = new AtomicReferenceArray<Integer>
	def public <T> For(int start, int end, Callable<T> myFunc){
		var parallelism = 100
		For(start, end, myFunc, parallelism)
	}
	
	def public getIter(){
		return iter.remove(iter.size-1)
	}
	
	def public <T> For(int start, int end, Callable<T> myFunc, int parallelism){
		(start..end).forEach[i|
			iter.add(i)
		]
		val ExecutorService pool = Executors.newFixedThreadPool(parallelism)
		val m = new ArrayList<CompletableFuture<T>>()
		
		(start..end).forEach[i|
			println(i+" "+iter)
			m.add(CompletableFuture.supplyAsync([myFunc.call], pool))	
		]
		
		while(!m.allDone)
		{}
		return
		
	}
	
	

	def public <T> allDone(ArrayList<CompletableFuture<T>> aray)
	{
		for(b: aray)
			if(!b.isDone)
				return false
		return true
	}
	

}
