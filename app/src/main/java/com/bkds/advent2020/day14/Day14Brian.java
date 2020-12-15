package com.bkds.advent2020.day14;

import java.util.ArrayList;
import java.util.Arrays;
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
				beforePrint(mask, result, i);		
				result &= ~(1L << pos);
				afterPrint(mask, result, i);
			} else if(mask.charAt(i) == '1') {				
				beforePrint(mask, result, i);		
				result |=  (1L << pos);				
				afterPrint(mask, result, i);
			} else {
				beforePrint(mask, result, i);		
				afterPrint(mask, result, i);				
			}
		}
		System.out.println();
		System.out.println("Result: (" + result + ") " + String.format("%36s", Long.toBinaryString(result)).replace(' ', '0'));
		return result;
	}
	

	public void applyMask2Address(Long memoryLocation, String mask, Long value) {		
//		System.out.println("Mask:          " + mask);
//		System.out.println("Location: (" + memoryLocation + ") " + String.format("%36s", Long.toBinaryString(memoryLocation)).replace(' ', '0'));
//		System.out.println();
		long result = Long.valueOf(memoryLocation);
		String[] memAsStr = String.format("%36s", Long.toBinaryString(memoryLocation)).replace(' ', '0').split("");
		for(int i=mask.length()-1; i >=0; i--) {
			int pos = ((mask.length()-i)-1);	
			if(mask.charAt(i) == '0') {
				//beforePrint(mask, result, i);		
				result &= ~(1L << pos);
				//afterPrint(mask, result, i);
			} else if(mask.charAt(i) == '1') {				
				//beforePrint(mask, result, i);		
				result |= (1L << pos);				
				//afterPrint(mask, result, i);
			} else if(mask.charAt(i) == '2') {
				String keep = memAsStr[i];
				//beforePrint(mask, result, i);
				if(keep.equals("1")) {							
					//result |= (1L << pos);	
				}
				//afterPrint(mask, result, i);
			}
		}		
		//System.out.println("Result: (" + result + ")   " + String.format("%36s", Long.toBinaryString(result)).replace(' ', '0'));
		memory.put(result, value);
//		System.out.println("Location == " + result + " with value: " + value);
//		System.out.println();
	}

	private void afterPrint(String mask, long result, int i) {
		mark(mask, i);
		System.out.println("After:  " + String.format("%36s", Long.toBinaryString(result)).replace(' ', '0'));
		System.out.println();
	}

	private void beforePrint(String mask, long result, int i) {
		System.out.println("Before: " + String.format("%36s", Long.toBinaryString(result)).replace(' ', '0'));
		mark(mask, i);
		System.out.println("Mask:   " + (mask));
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
	
	private Map<Long, Long> memory = new HashMap<>();
	
	public void processFloating(Long memoryLocation, String mask, Long value) {
		//System.out.println("Starting Mask: " + mask);
		List<String> allMasks = generateMasks(mask);		
		for(String aMask : allMasks) {
			//System.out.println(aMask);
			applyMask2Address(memoryLocation, aMask, value);
		}	
		System.out.println();
	}
		
	public void solvePartTwo() {
		String currentMask = "";
		for (String input : inputData) {
			if (input.startsWith("mask")) {
				currentMask = input.split("=")[1].trim();
			} else if (input.startsWith("mem")) {
				String pos = input.substring(4, input.indexOf("]"));
				Long memoryLocation = Long.valueOf(pos);
				long value = Long.valueOf(input.split("=")[1].trim());
				//memory.put(memoryLocation, value);
				//System.out.println("Current Mask:  " + currentMask);
				processFloating(memoryLocation, currentMask, value);
			}
		}
		
		//System.out.println(memory);
		long sum = memory.values().stream().collect(Collectors.summingLong(Long::longValue));
		print("Result = " + sum);
	}
	
	static void printTheArray(int arr[], int n) 
	{ 
	    for (int i = 0; i < n; i++)  
	    { 
	        System.out.print(arr[i]+" "); 
	    } 
	    System.out.println(); 
	} 
	
	// Function to generate all binary strings 
	static void generateAllBinaryStrings(String mask[], int n,  
	                            int arr[], int i, List<String> maskList) 
	{ 
	    if (i == n)  
	    { 
	       // printTheArray(arr, n); 
	    	String newMask = Arrays.stream(arr)
				    .mapToObj(x -> ((Integer) x).toString())
				    .collect(Collectors.joining(""));
	    	maskList.add(newMask);
	        return; 
	    } 
	  
	    if(mask[i].equals("0") ) {
	    	arr[i] = 2;
	    	generateAllBinaryStrings(mask, n, arr, i + 1, maskList);
	    } else if(mask[i].equals("1")) {
	    	arr[i] = 1;
	    	generateAllBinaryStrings(mask, n, arr, i + 1, maskList);
	    } else {
		    // First assign "0" at ith position 
		    // and try for all other permutations 
		    // for remaining positions 
		    arr[i] = 0; 
		    generateAllBinaryStrings(mask, n, arr, i + 1, maskList); 
		  
		    // And then assign "1" at ith position 
		    // and try for all other permutations 
		    // for remaining positions 
		    arr[i] = 1; 
		    generateAllBinaryStrings(mask, n, arr, i + 1, maskList);
	    }
	} 
	
	public List<String> generateMasks(String theMask) {
		int n = theMask.length(); 
		  
	    int[] arr = new int[n];
	    List<String> maskList = new ArrayList<>();
	    String[] mask = theMask.split("");
	    generateAllBinaryStrings(mask, n, arr, 0, maskList);
	    	    
		return maskList;
	}
	public static void main(String[] args) {
		Day14Brian b = new Day14Brian();
		//b.solvePartOne();
		b.solvePartTwo();
		//String theMask = "0101XX01X00X1X1011X1X000000101X10001";
		//int[] results = b.generateMasks("00000");
		
//		int n = theMask.length(); 
//		  
//	    int[] arr = new int[n];
//	    String[] mask = theMask.split("");
//	    generateAllBinaryStrings(mask, n, arr, 0);
	    System.out.println();
	}
}
