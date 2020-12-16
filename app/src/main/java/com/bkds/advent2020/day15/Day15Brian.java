package com.bkds.advent2020.day15;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.bkds.advent2020.DayBase;

public class Day15Brian extends DayBase {

	List<Integer> startingNumbers = new ArrayList<>();

	public Day15Brian() {
		readData("day15", "brian");
	}

	@Override
	public void store(String input) {
		for (String in : input.split(",")) {
			startingNumbers.add(Integer.valueOf(in));
		}
	}

	public long solve(int findWord) {
		int spokenNumber = -1;
		int turn = 0;
		LinkedList<Integer> seenNumbers = new LinkedList<>();		

		for(int i=0; i < startingNumbers.size(); i++) {
			spokenNumber = startingNumbers.get(i);
			seenNumbers.add(spokenNumber);
			turn++;
		}
		
		// Done with the start numbers. Let the games begin.
		while(turn < findWord) {
			int lastSpoken = seenNumbers.getLast();
			long seenCount = seenNumbers.stream().filter(x -> x == lastSpoken).count();
			
			if(seenCount > 1) {
				for(int i=seenNumbers.size()-2; i >= 0; i--) {
					if(seenNumbers.get(i) == lastSpoken) {
						int age = (seenNumbers.size() - 1) - i;
						spokenNumber = age;
						break;
					}
				}
			} else {
				spokenNumber = 0;
			}
			
			seenNumbers.add(spokenNumber);
			turn++;
			//System.out.println("Turn " + turn + " spoken number: " + spokenNumber);
		}
		long result = seenNumbers.get(findWord-1);
		//System.out.println(findWord + ": " + result);
		//System.out.println("Seen: " + seenNumbers);
		return result;
	}

	
	private int lastIndexOfMapped(Map<Integer, Integer> idxMap, int listSize, int number) {
		int result = -1;
		if(idxMap.containsKey(number)) {
			result = idxMap.get(number);			
		}
		idxMap.put(number, listSize);

		return result;
	}

	public long solveFaster(int findWord) {
		int spokenNumber = -1;
		Map<Integer, Integer> lastIndex = new HashMap<>();
		int count = 0;
		
		for(int i=0; i < startingNumbers.size(); i++) {
			spokenNumber = startingNumbers.get(i);
			lastIndex.put(spokenNumber, count);
			count++;
		}
		
		// Done with the start numbers. Let the games begin.
		while(count < findWord) {
			int last = lastIndexOfMapped(lastIndex, count-1, spokenNumber);
			if(last != -1) {
				spokenNumber = (count-1) - last;			
			} else {
				spokenNumber = 0;
			}
			count++;

//			if(count % 10000 == 0) {
//				System.out.println("---> " + count);
//			}
		}
		
		return spokenNumber;
	}
	
	public static void main(String[] args) {
		long result = 0;
		Day15Brian b = new Day15Brian();
//		result = b.solve3(2020);
//		System.out.println("2020: " + result);
		result = b.solveFaster(30000000);
		System.out.println("30000000: " + result);
	}

}
