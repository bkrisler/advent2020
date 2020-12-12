package com.bkds.advent2020.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bkds.advent2020.DayBase;

public class Day12Brian extends DayBase {

	private List<String> navInstructions = new ArrayList<>();
	private Map<String, List<String>> rCoords = new HashMap<>();
	private Map<String, List<String>> lCoords = new HashMap<>();
	
	public Day12Brian() {
		readData("day12", "brian");
		populateRCoords();
		populateLCoords();
	}

	@Override
	public void store(String input) {
		navInstructions.add(input);		
	}
	
	public List<String> getNavInstructions() {
		return this.navInstructions;
	}

	private void populateRCoords() {
		this.rCoords.put("N", Arrays.asList("E", "S", "W", "N"));
		this.rCoords.put("S", Arrays.asList("W", "N", "E", "S"));
		this.rCoords.put("E", Arrays.asList("S", "W", "N", "E"));
		this.rCoords.put("W", Arrays.asList("N", "E", "S", "W"));		
	}

	private void populateLCoords() {
		this.lCoords.put("N", Arrays.asList("W", "S", "E", "N"));
		this.lCoords.put("S", Arrays.asList("E", "N", "W", "S"));
		this.lCoords.put("E", Arrays.asList("N", "W", "S", "E"));
		this.lCoords.put("W", Arrays.asList("S", "E", "N", "W"));		
	}

	public String rotateRight(String currentDirection, int degrees) {
		double rotateAmount = degrees / 360.0;
		int index = ((int)(4 * rotateAmount) - 1);
		String newDirection = rCoords.get(currentDirection).get(index);
		return newDirection;
	}

	public String rotateLeft(String currentDirection, int degrees) {
		double rotateAmount = degrees / 360.0;
		int index = ((int)(4 * rotateAmount) - 1);
		String newDirection = lCoords.get(currentDirection).get(index);
		return newDirection;
	}

	public void solveA(List<String> instructions) {
		Map<String, Integer> coords = new HashMap<>();
		coords.put("N", 0);
		coords.put("S", 0);
		coords.put("E", 0);
		coords.put("W", 0);
		
		int northSouth = 0;
		int eastWest = 0;
		
		String currentDirection = "E";
		
		int count = 0;
		for(String instruction : instructions) {
			String direction = instruction.substring(0, 1);
			int distance = Integer.valueOf(String.join("", instruction.subSequence(1, instruction.length())));
			//System.out.println("Instruction: " + instruction + " (" + direction + ") (" + distance + ")");
			switch(direction) {
			case "N":
				northSouth += distance;
				break;
			case "S":
				northSouth -= distance;
				break;			
			case "E":
				eastWest += distance;
				break;
			case "W":
				eastWest -= distance;
				break;
			case "L":
				currentDirection = rotateLeft(currentDirection, distance);
				break;
			case "R":
				currentDirection = rotateRight(currentDirection, distance);
				break;
			case "F":
				if(currentDirection.equals("N")) {
					northSouth += distance;
				} else if(currentDirection.equals("S")) {
					northSouth -= distance;
				} else if(currentDirection.equals("E")) {
					eastWest += distance;
				} else if(currentDirection.equals("W")) {
					eastWest -= distance;
				}
				break;
			default:
				System.err.println("Unknown: " + direction);
				break;
			}
			
//			System.out.print("Index: " + count);
//			System.out.print("  N/S: " + northSouth);
//			System.out.print("  E/W: " + eastWest);		
//			System.out.println();
			count++;
		}
		
		System.out.println("Final coords: ");
		System.out.println("  N/S: " + northSouth);
		System.out.println("  E/W: " + eastWest);	
		int mDist = Math.abs(northSouth) + Math.abs(eastWest);
		System.out.println(" --> Manhattan distance: " + mDist);
 	}

	public static void main(String[] args) {
		Day12Brian db = new Day12Brian();
		db.solveA(db.getNavInstructions());
	}
}
