package com.learnjava.corejava;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallableExample {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Runnable r = () -> {
			System.out.println("Printing from the Runnable");
		};

		Callable<String> c = new Callable<String>() {
			
			@Override
			public String call() throws Exception {
			
				System.out.println("Printing from the Callable");
				Thread.sleep(1000);
				return "value from callable	";
			}
		}; 
		
		// Does not work, callable can only be sent to an ExecutorService
		//Thread t = new Thread(c);
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		Future<String> ret = executorService.submit(c);
		
		System.out.println("Executing things in the main thread");
		System.out.println("Executing more things in the main thread");
		// Waits for the ExecutorService to populate the result in the Future object
		String retStr = ret.get();
		System.out.println("Return value from the callable : " + retStr);
		
		
	}

}
