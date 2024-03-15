package com.learnjava.corejava;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;



public class CallablePrimeExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {

		ExecutorService executorService = Executors.newCachedThreadPool();

		List<Future<Integer>> futures = new ArrayList<>();
		
		while(true) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("\n I can tell you the nth prime number. Enter n: ");
			
			int n = sc.nextInt();
			if (n==0) {
				break;
			}
			
			Callable<Integer> c = new Callable<Integer>() {
				
				@Override 
				public Integer call() throws Exception {
					return PrimeNumberUtil.calculatePrime(n);
				}
				
			};
			
			Future<Integer> primeNoFuture = executorService.submit(c);
			futures.add(primeNoFuture);
			
			// Makes the main thread wait till the future object is  populated with the result
			//System.out.println("The " + n + "th Prime Number : " + primeNoFuture.get());
			
			
			// For loop may mess up the modification operation, not thread safe. Foreach is not thread safe too 
			/*for(Future<Integer> f : futures) {
				if (f.isDone()) {
					System.out.println("The " + n + "th Prime Number : " + primeNoFuture.get());
					futures.remove(f);
				}
			}*/
			
			Iterator<Future<Integer>> ir = futures.iterator();
			
			while( ir.hasNext() ) {
				
				Future<Integer> f = ir.next();
				if (f.isDone()) {
					System.out.println("The " + n + "th Prime Number : " + f.get());
					futures.remove(f);
				}
			}
		}
 
	}
	

}