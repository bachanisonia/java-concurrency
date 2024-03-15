package com.learnjava.corejava;

import java.time.LocalDateTime;
import java.util.NavigableSet;
import java.util.TreeSet;

public class NavigableExample {

	public static void main(String[] args) {
		
		NavigableSet<LocalDateTime> appointments = new TreeSet<>();

		appointments.add(LocalDateTime.of(2023, 3, 4, 10, 30));
		appointments.add(LocalDateTime.of(2023, 3, 4, 11, 0));
		appointments.add(LocalDateTime.of(2023, 3, 4, 11, 30));
		appointments.add(LocalDateTime.of(2023, 3, 4, 12, 00));
		
		LocalDateTime nextAvailAppoint = appointments.ceiling(LocalDateTime.of(2023, 3, 4, 10, 45));
		
		System.out.println("Next appointment available at : " + nextAvailAppoint);
		
	}

}
