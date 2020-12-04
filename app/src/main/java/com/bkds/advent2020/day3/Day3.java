package com.bkds.advent2020.day3;

import com.bkds.advent2020.DayBase;

public class Day3 extends DayBase {

	public Day3(String person) {
		readData("day3", person);
	}

	public void testList() {
		CircularList cl = new CircularList();
		cl.add("1");
		cl.add("2");
		cl.add("3");
		cl.add("4");
		cl.add("5");
		cl.add("6");
		cl.add("7");
		cl.add("8");
		cl.add("9");
		cl.add("10");
		for(int i=0; i < 100; i++) {
			System.out.println(cl.getNext());
		}
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
		Day3 d3 = new Day3(person);
		d3.testList();
	}

}
