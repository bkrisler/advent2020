package com.bkds.advent2020.day3;

import com.bkds.advent2020.DayBase;

public class Day3 extends DayBase {

	public Day3(String person) {
		readData("day3", person);
	}

	@Override
	public void store(String input) {
		// Step 2: Store the input data into the desired data structure.
	}

	// Step 3: Create a method for answering the first part of the problem.
	
	public static void main(String[] args) {
		if(args.length == 0) {
			System.err.println("Add person name as an argument!");
			return;
		}
		String person = args[0];

		// Step 1: Construct a new class for testing and call the "solve" methods
	}

}
