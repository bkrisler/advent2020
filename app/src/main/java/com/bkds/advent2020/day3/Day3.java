package com.bkds.advent2020.day3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bkds.advent2020.DayBase;

public class Day3 extends DayBase {

	private List<List<String>> rows = new ArrayList<>();
	
	public Day3(String person) {
		readData("day3", person);		
	}
	
	@Override
	public void store(String input) {
		// Step 2: Store the input data into the desired data structure.
		String[] parts = input.split("");
		List<String> row = new ArrayList<>();
		Collections.addAll(row, parts);
		rows.add(row);
	}

	  // Step 3: Create a method for answering the first part of the problem.
	  public int solveProblem(int right, int down)
	  {
	    int charChecker = right;
	    int treeCount = 0;
	    for (int i = down; i < rows.size(); i = i + down)
	    {
	      List<String> row = rows.get(i);
	      String positionAt = row.get(charChecker % row.size());
	      charChecker += right;
	      if (positionAt.equals("#"))
	      {
	        treeCount++;
	      }
	    }
	    System.out.println(right + " right and " + down + " down equals "
	      + treeCount + " trees");
	    return treeCount;
	  }

	// Step 3: Create a method for answering the first part of the problem.
	public int solvePartOne() {
		int shift = 1;
		int trees = 0;
		int pos = shift;
		for(int i = 1; i < rows.size(); i++) {
			List<String> row = rows.get(i);
			String slot = row.get(pos % row.size());
			if(slot.equals("#")) {
				trees++;
			}
			pos = pos + shift;
		}
		return trees;
	}
	
	public long solvePartTwo() {
		long a = calcSlope(1, 1);
		System.out.println(" Right 1, Down 1: " + a);
		long b = calcSlope(3, 1);
		System.out.println(" Right 3, Down 1: " + b);
		long c = calcSlope(5, 1);
		System.out.println(" Right 5, Down 1: " + c);
		long d = calcSlope(7, 1);
		System.out.println(" Right 7, Down 1: " + d);

//		for(List<String> row : rows) {
//			System.out.println(row);
//		}
//		System.out.println("\n");
		
		long e = calcSlope(1, 2);
		System.out.println(" Right 1, Down 2: " + e);

		return a * b * c * d * e;
	}

	private int calcSlope(int right, int down) {
		int trees = 0;
		int pos = right;
		for(int i = down; i < rows.size(); i = i + down) {
//			System.out.println("Read Row: " + i);
			List<String> row = rows.get(i);
//			System.out.println("Row[" + i + "]: " + row);
			String slot = row.get(pos % row.size());
			String[] parts = row.toArray(new String[row.size()]);
			if(slot.equals("#")) {
				parts[pos % row.size()] = "X";
				trees++;
			} else {
				parts[pos % row.size()] = "O";				
			}
			List<String> tmp = new ArrayList<>();
			Collections.addAll(tmp, parts);
//			System.out.println("New[" + i + "]: " + tmp + "\n");
			pos = pos + right;
		}
		return trees;
	}
	
	public static void main(String[] args) {
		if(args.length == 0) {
			System.err.println("Add person name as an argument!");
			return;
		}
		String person = args[0];

		if(person.equals("brian")) {
			// Step 1: Construct a new class for testing and call the "solve" methods
			Day3 d3 = new Day3(person);
			int hitTrees = d3.solvePartOne();
			System.out.println("Trees hit: " + hitTrees);
			long p2 = d3.solvePartTwo();
			System.out.println("Part two: " + p2);			
		} else {
		    // Step 1: Construct a new class for testing and call the "solve" methods
		    Day3 dayThree = new Day3(person);
		    long a = dayThree.solveProblem(1, 1);
		    long b = dayThree.solveProblem(3, 1);
		    long c = dayThree.solveProblem(5, 1);
		    long d = dayThree.solveProblem(7, 1);
		    long e = dayThree.solveProblem(1, 2);
		    long allDone = (a * b * c * d * e);

		    System.out.println(a + " " + b + " " + c + " " + d + " " + e);
		    System.out.println(allDone);			
		}
	}

}
