package Tests

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.CompletableFuture
import java.util.Set
import java.util.ArrayList
import java.util.concurrent.Callable
import java.util.concurrent.atomic.AtomicInteger

public class Async {

	var private static board = new ArrayList<ArrayList<Set<Integer>>>()	var private static board1 = new ArrayList<ArrayList<Set<Integer>>>()	var private static atom = new AtomicInteger(0)
	
	def public static <T> For(int start, int end, Callable<T> myFunc){
		var parallelism = 100
		For(start, end, myFunc, parallelism)
	}
	
	def public static <T> For(int start, int end, Callable<T> myFunc, int parallelism){
		val ExecutorService pool = Executors.newFixedThreadPool(parallelism)
		val m = new ArrayList<CompletableFuture>()
		
		(start..end).forEach[i|
			m.add(CompletableFuture.supplyAsync([myFunc.call], pool))	
		]
		
		while(!m.allDone)
		{}
		return
		
	}
	
	
	def private static Set<Integer> initSet(){
//		Thread.sleep(100)
		var initSet = (1..9).toSet
		return initSet
	}

	def public static allDone(ArrayList<CompletableFuture> aray)
	{
		for(b: aray)
			if(!b.isDone)
				return false
		return true
	}
	
	def private static createRow(){
		board.add(new ArrayList<Set<Integer>>)
		return board.size-1
	}
	def private static createRow1(){
		board1.add(new ArrayList<Set<Integer>>)
		return board1.size-1
	}
	
	def static void main(String[] args) {
		var start = System.currentTimeMillis
		
		For(0, 8, [
			val rnum = createRow()
			For(0, 8, [
					board.get(rnum).add(initSet)
					return null
					], 1)
			return null
			], 1)
		
		var end = System.currentTimeMillis - start
		println(end)
		
		start = System.currentTimeMillis()
		
		(0..8).forEach[i|
			val rnum = createRow1()
			(0..8).forEach[
				board1.get(rnum).add(initSet())
			]
		]
		
		end = System.currentTimeMillis -start
		
		println(end)

	}

}
