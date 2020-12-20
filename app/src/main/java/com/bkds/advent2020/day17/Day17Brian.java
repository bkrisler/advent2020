package com.bkds.advent2020.day17;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.bkds.advent2020.DayBase;

public class Day17Brian extends DayBase {

	private ArrayList<ArrayList<String>> current = new ArrayList<>();

	public Day17Brian() {
		readData("day17", "sample");
	}

	@Override
	public void store(String input) {
		ArrayList<String> row = new ArrayList<String>(Arrays.asList(input.split("")));
		current.add(row);
	}

	private void grow(ArrayList<ArrayList<String>> space) {
		for(ArrayList<String> row : space) {
			row.add(0, ".");
			row.add(space.size()+1, ".");
		}
		ArrayList<String> emptyRow = new ArrayList<>();
		for(int i=0; i<space.get(0).size(); i++) {
			emptyRow.add(".");
		}
		space.add(0, new ArrayList<>(emptyRow));
		space.add(space.size(), new ArrayList<>(emptyRow));
	}
		
	private void solvePart1() {
		
		ArrayList<ArrayList<String>> result = new ArrayList<>();
		for(ArrayList<String> row : current) {
			result.add(new ArrayList<>(row));
		}
		
		for(int i=0; i < 3; i++) {
			System.out.println("Cycle: " + i);
			 result = solve2D(result);
			 System.out.println();
		}
		
		System.out.println();
		dumpSpace(current);
	}
	
	private ArrayList<ArrayList<String>> solve2D(ArrayList<ArrayList<String>> input) {
		grow(input);
		//dumpSpace(input);
		ArrayList<ArrayList<String>> next = new ArrayList<>();
		for (int i = 0; i < input.size(); i++) {
			ArrayList<String> rows = input.get(i);
			ArrayList<String> nxtRow = new ArrayList<>();
			for (int j = 0; j < rows.size(); j++) {
				// Check neighbors
				int numberActive = 0;
				for (int x = -1; x <= 1; x++) {
					try {
						ArrayList<String> neighbor = input.get(i + x);
						for (int y = -1; y <= 1; y++) {
							try {
								String nValue = neighbor.get(j + y);
								if (nValue.equals("#")) {
									numberActive++;
								}
							} catch (Exception e) {
							}
						}
					} catch (IndexOutOfBoundsException e) {
					}
				}
				
				String cellState = rows.get(j);
				// Subtract itself!
				if (cellState.equals("#")) {
					numberActive -= 1;
					if(numberActive == 2 | numberActive == 3) {
						nxtRow.add("#");
					} else {
						nxtRow.add(".");
					}
				} else {
					if(numberActive == 3) {
						nxtRow.add("#");
					} else {
						nxtRow.add(".");							
					}
					//System.out.println("Cell is not active.");
				}
				//System.err.println("Cell: [" + i + "," + j + "] has " + numberActive + " neighbors");
			}
			next.add(nxtRow);
		}
		System.out.println();
		dumpSpace(next);
		return next;
	}

	public void dumpSpace(ArrayList<ArrayList<String>> space) {
		for(List<String> row : space) {
			for(String entry : row) {
				System.out.print(entry);
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		Day17Brian db = new Day17Brian();		
		db.solvePart1();
	}

}
