package com.bkds.advent2020.day14;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bkds.advent2020.DayBase;

public class Day14Brian extends DayBase {

	List<String> inputData = new ArrayList<>();

	public Day14Brian() {
		readData("day14", "brian");
	}

	@Override
	public void store(String input) {
		inputData.add(input);
	}

	String STRING_FORMAT = "%-10s = %s";
	public static final String OUTPUT_FORMAT = "%64s";

	public void viewMask(long number) {
		System.out.println(String.format(STRING_FORMAT, "Input " + number, printBinary(number)));
		// System.out.println("Mask: " + String.format("%36s",
		// Integer.toBinaryString(1)).replace(' ', '0'));
	}

	public int createMask(int param) {
		return (1 << param);
	}
	
	public static String printBinary(long number) {
		return printBinary(number, 8, "|");
	}

	public static String printBinary(long number, int blockSize, String separator) {

        // pad leading zero
        String pBinary = String
                .format(OUTPUT_FORMAT, Long.toBinaryString(number))
                .replace(" ", "0");

        // split by blockSize
        List<String> result = new ArrayList<>();
        int index = 0;
        while (index < pBinary.length()) {
            result.add(pBinary.substring(index, Math.min(index + blockSize, pBinary.length())));
            index += blockSize;
        }

        return result.stream().collect(Collectors.joining(separator));
    }
	
	public void solvePartOne() {
		Map<Integer, Long> memoryMap = new HashMap<>();
		String currentMask = "";
		for (String input : inputData) {
			if (input.startsWith("mask")) {
				currentMask = input.split("=")[1].trim();
			} else if (input.startsWith("mem")) {
				String pos = input.substring(4, input.indexOf("]"));
				int value = Integer.valueOf(input.split("=")[1].trim());
				memoryMap.put(Integer.valueOf(pos), applyMask(value, currentMask));
			}
		}
		System.out.println(memoryMap);
		
		long sum = memoryMap.values().stream().collect(Collectors.summingLong(Long::longValue));
		print("Result = " + sum);
	}

	private void mark(String mask, int i) {
		//print(i + " " + mask.charAt(i) + " edit pos: " + pos);
		System.out.print("        ");
		for(int p=0; p < i; p++) {
			System.out.print(" ");
		}
		System.out.println("|");
	}
	
	public long applyMask(long value, String mask) {
		System.out.println("Value: (" + value + ") " + String.format("%36s", Long.toBinaryString(value)).replace(' ', '0'));
		long result = value;
		for(int i=mask.length()-1; i >=0; i--) {
			int pos = ((mask.length()-i)-1);
			System.out.println("--[ " + pos + " ]--[ " + (Long.SIZE - Long.numberOfLeadingZeros(result)) + " ]--");			
			if(mask.charAt(i) == '0') {
				System.out.println("Before: " + String.format("%36s", Long.toBinaryString(result)).replace(' ', '0'));
				mark(mask, i);
				System.out.println("Mask:   " + (mask));		
				result &= ~(1L << pos);
				mark(mask, i);
				System.out.println("After:  " + String.format("%36s", Long.toBinaryString(result)).replace(' ', '0'));
			} else if(mask.charAt(i) == '1') {				
				System.out.println("Before: " + String.format("%36s", Long.toBinaryString(result)).replace(' ', '0'));
				mark(mask, i);
				System.out.println("Mask:   " + (mask));		
				result |=  (1L << pos);				
				mark(mask, i);
				System.out.println("After:  " + String.format("%36s", Long.toBinaryString(result)).replace(' ', '0'));
			} else {
				System.out.println("Before: " + String.format("%36s", Long.toBinaryString(result)).replace(' ', '0'));
				mark(mask, i);
				System.out.println("Mask:   " + (mask));		
				mark(mask, i);
				System.out.println("After:  " + String.format("%36s", Long.toBinaryString(result)).replace(' ', '0'));				
			}
		}
		System.out.println();
		System.out.println("Result: (" + result + ") " + String.format("%36s", Long.toBinaryString(result)).replace(' ', '0'));
		return result;
	}
	
	public void testMask(long value) {
		//viewMask(value);
		String mask = "0101XX01X00X1X1011X1X000000101X10001";
		System.out.println();
		System.out.println(mask);
		System.out.println();
		long result = applyMask(value, mask);
		//viewMask(result);
		print("Result: " + result);
		
	}
	
	public void solvePartTwo() {
		Map<Integer, Long> memoryMap = new HashMap<>();
		String currentMask = "";
		for (String input : inputData) {
			if (input.startsWith("mask")) {
				currentMask = input.split("=")[1].trim();
			} else if (input.startsWith("mem")) {
				String pos = input.substring(4, input.indexOf("]"));
				int value = Integer.valueOf(input.split("=")[1].trim());
				memoryMap.put(Integer.valueOf(pos), applyMask(value, currentMask));
			}
		}
		System.out.println(memoryMap);
		
		long sum = memoryMap.values().stream().collect(Collectors.summingLong(Long::longValue));
		print("Result = " + sum);
	}
	
	public static void main(String[] args) {
		Day14Brian b = new Day14Brian();
		//b.testMask(333450);
		b.solvePartOne();
		// b.viewMask(0);
		//b.viewMask(x);		
		//b.viewMask(b.createMask(2));
		
		
//		int x = 0;
//		print("Before: " + x);
//		b.viewMask(x);
//		String mask = "XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X";
//		print(mask);
//		int result = b.applyMask(x, mask);
//		print("After: " + result);
//		b.viewMask(result);
	}
}
