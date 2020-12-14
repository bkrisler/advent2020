package com.bkds.advent2020.day13;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.bkds.advent2020.DayBase;

public class Day13Brian extends DayBase {

	private Integer departureTime;
	private List<String> buses = new ArrayList<>();
	
	int readIndex = 0;
	public Day13Brian() {
		readData("day13", "brian");
	}

	@Override
	public void store(String input) {
		if(readIndex == 0) {
			departureTime = Integer.valueOf(input);
		} else if(readIndex == 1) {
			for(String bus : input.split(",")) {
				buses.add(bus);
			}
		}
		readIndex++;
	}

	protected int closest(int x, int y) {
		int quotient = x / y;
		
		int n1 = y * quotient;
		int n2 = (x * y)  > 0 ? (y * (quotient + 1)) : (y * (quotient -1));
		
		if(Math.abs(x - n1) < Math.abs(x - n2)) {
			return n1;
		}
		
		return n2;
	}
	
	protected boolean allMatch(long startIdx, List<String> num) {
		//System.out.println("--> " + startIdx + " :: " + num);
		long index = startIdx;
		for(int i=0; i < num.size(); i++) {
			String n = num.get(i);
			if(n.equals("x")) {
				index++;
				continue;
			} else {
				Integer nInt = Integer.valueOf(n);
//				System.out.println("Check: " + index + " % " + nInt + " = " + (index % nInt));
				if(index % nInt != 0) {
					return false;
				}
				index++;
			}
			//System.out.println(num.get(i));
		}
		return true;		
	}
	
	public void solveC() {
		System.out.println("Buses: " + buses.size());		
		int busId = Integer.valueOf(buses.get(0));
		int closest = closest(departureTime, busId);
		int lastBus = Integer.valueOf(buses.get(buses.size()-1));
		long i = closest;
		boolean done = false;
		
		while(!done) {
//			System.out.println("["+i+"]  ");
			
			if(i % busId == 0) {
				//System.out.println("First matches! " + lastBus + " " + i+buses.size());
				if((i+(buses.size()-1)) % lastBus == 0) {
					System.out.println("First and Last Matches at: " + i + " and " + (i+(buses.size()-1)));
					done = allMatch(i+1, buses.subList(1, buses.size()-1));
					if(done) {
						System.out.println("Solution = " + i);
					}
				}
			}
			
			i = i + busId;
		}		
	}
	
	
	public void solveD() {
		// Get the largest bus id from the list.
		List<Integer> filtered = buses.stream()
				.filter(x -> !x.equals("x"))
				.map(Integer::valueOf)
				.collect(Collectors.toList());	
		
		Integer maxBusId = Collections.max(filtered);
		int maxBusIdIndex = buses.indexOf(String.valueOf(maxBusId));		
		long closestToMaxBusId = closest(departureTime, maxBusId);

		int firstBusId = Integer.valueOf(buses.get(0));
		int lastBusId = Integer.valueOf(buses.get(buses.size()-1));

		boolean done = false;
		long crntIndex = closestToMaxBusId;
		while(!done) {
			if(crntIndex % maxBusId == 0) {
				if((crntIndex-maxBusIdIndex) % firstBusId == 0) {
					if((crntIndex + (buses.size()-1)) % lastBusId == 0) {
						done = allMatch(crntIndex+1, buses.subList(1, buses.size()-1));
						if(done) {
							System.out.println("Solution = " + crntIndex);
						}
					}
				}
			}
			crntIndex += maxBusId;
		}		
	}
	
	public void solveB() {
		int busId = Integer.valueOf(buses.get(0));
		long nxtId = 0;
		int matches = 0;
		int closest = closest(departureTime, busId);
		int lastBus = Integer.valueOf(buses.get(buses.size()-1));
		int closestToLast = closest(departureTime+buses.size(), lastBus);
		long i = closest;
		boolean done = false;

		while(!done) {
			System.out.println("["+i+"]  ");
			if(i % busId == 0) {
				matches++;
				//System.out.println("\n    Check others [" + i + "]");
				for(int j=1; j < buses.size(); j++) {
					if(buses.get(j).equals("x")) {
						//System.out.println("     Skip");
						matches++;
						continue;
					}
					//System.out.println("    [" + (i+j) +"] " + buses.get(j));
					nxtId = Integer.valueOf(buses.get(j));
					if((i+j) % nxtId == 0) {
						matches++;
						//System.out.println(" Found (" + nxtId + ")");
					}
				}
			}
			if(matches == buses.size()) {
				System.out.println("Solution: " + i);
				done = true;
			}
			i = i + busId;
			matches = 0;
			//System.out.println();
		}		
	}
	
	public void solveA() {
		List<String> filtered = buses.stream().filter(x -> !x.equals("x")).collect(Collectors.toList());	
		
		for(int i=departureTime; i < (departureTime + 50); i++) {
			for(String bus : filtered) {
				int bId = Integer.valueOf(bus);
				if(i % bId == 0) {
					//System.out.println("Bus: " + bus + ", " + i);
					int result = (i - departureTime) * bId;
					System.out.println("Result: " + result);
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Day13Brian b = new Day13Brian();			
		b.solveD();
	}
}
