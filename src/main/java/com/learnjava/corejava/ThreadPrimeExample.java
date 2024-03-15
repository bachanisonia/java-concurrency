package com.learnjava.corejava;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ThreadPrimeExample {

	public static void main(String[] args) {
		
		List<Thread> threads = new ArrayList<>();
		
		Runnable statusReporter = new Runnable() {
			
			@Override
			public void run() {
			
				try {
					while(true) {		
						
						// Pause for 5 secs
						Thread.sleep(5000);
						printThreads(threads);
					}
				}
				catch(InterruptedException e) {
					//e.printStackTrace();
					System.out.println("Interrupted Exception !!!");
				}
			}
		};
		
		Thread reporterThread = new Thread(statusReporter);
		// When interrupting, you may not need to set the thread as daemon
		reporterThread.setDaemon(true);
		reporterThread.start();
		
		while(true) {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("\n I can tell you the nth prime number. Enter n: ");
			
			int n = sc.nextInt();
			if (n==0) {

				reporterThread.interrupt();
				
				try {
					System.out.println("Waiting for threads to finish execution");
					waitForThreads(threads);
					System.out.println("Done ! " + threads.size() + " threads finished calculating the prime number");
				} catch (InterruptedException e) {
					System.out.println("Got Interrupted, will try again later...");
				}
				
				break;
			}
			
			Runnable r = new Runnable() {
				
				@Override
				public void run() {
					
					int number = PrimeNumberUtil.calculatePrime(n);
					System.out.println("\n Value of the " + n + "th prime number is : " +  number);
					
				}
			};	
			
			Thread t = new Thread(r);
			threads.add(t);
			t.setDaemon(true); // Turning it into a daemon thread
			t.start();
			
		}
 
	}
	
	public static void printThreads(List<Thread> threads) {
		
		System.out.println("\n Thread Status : ");
		
		for(Thread s : threads) {
			System.out.println(s.getState());
		}
	}

	private static void waitForThreads(List<Thread> threads) throws InterruptedException {
		
		for(Thread t : threads) {
			t.join();
		}
		
	}
	
	
}
