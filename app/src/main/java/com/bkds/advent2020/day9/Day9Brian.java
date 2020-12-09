package com.bkds.advent2020.day9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.bkds.advent2020.DayBase;

public class Day9Brian extends DayBase {

	public static final Integer PREAMBLE_SIZE = 25;
	private List<Long> inputCode = new ArrayList<>();
	
	public Day9Brian() {
		readData("day9", "brian");
	}

	@Override
	public void store(String input) {
		inputCode.add(Long.valueOf(input));		
	}

	private static <T> Stream<List<T>> sliding(List<T> list, int size) {
		if (size > list.size()) {
			return Stream.empty();
		} else {
			return IntStream.range(0,  list.size() - size + 1).mapToObj(start -> list.subList(start, start + size));
		}
	}
		
	private void doSomething(List<Long> window) {
		List<Long> sumVals = window.subList(0, window.size()-1);
		Long target = window.get(window.size()-1);
		
		//System.out.println("Window: " + sumVals + " Target: " + target);

		boolean foundMatch = false;
		for(int i=0; i < sumVals.size(); i++) {
			for(int j=i+1; j < sumVals.size(); j++) {
				if(i==j) continue;  // Don't sum a number with itself!
				long x = sumVals.get(i);
				long y = sumVals.get(j);
				long result = x+y;
				if(result == target) {
//					System.out.println("Found the pair: " + x + ", " + y);
					foundMatch = true;
				}
			}
		}
		if(!foundMatch) {
			System.out.println("No match for: " + target);
		}
		//System.out.println();
	}
	
	private void findSum(List<Long> window, Long target) {
		List<Long> range = new ArrayList<>();
		for(int i=0; i < window.size(); i ++) {
			range.add(window.get(i));
			long sum = window.get(i);
			for(int j=i+1; j < window.size(); j++) {
				range.add(window.get(j));
				sum += window.get(j);
//				System.out.println("First: " + first + " Last: " + last + " Sum: " + sum);
				if(sum == target) {
					Collections.sort(range);
					//System.out.println("Solution Range: " + range);
					System.out.println("Answer: " + (range.get(0) + range.get(range.size()-1)));
					return;
				} else if(sum > target) {
					break;
				}
			}
			range.clear();
		}
	}
	
	public void solvePartOne() {
//		System.out.println("Start: " + inputCode.get(PREAMBLE_SIZE));
		sliding(inputCode, PREAMBLE_SIZE+1).forEach(this::doSomething);
	}
	
	public void solvePartTwo() {
		long target = 41682220;
		findSum(inputCode, target);
	}
	
	public static void main(String[] args) {
		Day9Brian day = new Day9Brian();
		day.solvePartOne();
		day.solvePartTwo();
	}
}
