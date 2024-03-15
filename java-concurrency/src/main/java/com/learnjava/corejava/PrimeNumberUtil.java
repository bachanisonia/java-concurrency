package com.learnjava.corejava;

public class PrimeNumberUtil {
	
	private static int outputNo;
	
	public static int calculatePrime(int inputNo) {
		
		int numOfPrimesFound = 0;
		int i;
		int number = 1;
		
		while (numOfPrimesFound < inputNo) {
			
			number++;
			
			for (i=2; i <= number && number%i != 0; i++) {
			}
			
			if (i == number) {
				numOfPrimesFound++;
			}
			
		}
		
		return number;
		
	}
	
}
