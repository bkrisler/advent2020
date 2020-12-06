package com.bkds.advent2020.day5;

import java.util.ArrayList;
import java.util.List;

import com.bkds.advent2020.DayBase;

public class Day5 extends DayBase {

	private List<String> seats = new ArrayList<>();

	public Day5() {
		readData("day5", "brian");
	}

	@Override
	public void store(String input) {
		seats.add(input);
	}

	public void maeOneSolution() {
		// Step 2: Add your solution here.
	}
	
	public void brianOneSolution() {
		
	}
	
	public static void main(String[] args) {
		// Step 1: Create a new Day5 object and call your solution method.		
	}
}
