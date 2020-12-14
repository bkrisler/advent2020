package com.bkds.advent2020.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		if (readIndex == 0) {
			departureTime = Integer.valueOf(input);
		} else if (readIndex == 1) {
			for (String bus : input.split(",")) {
				buses.add(bus);
			}
		}
		readIndex++;
	}

	public static long computeMinX(long[] rem, long[] num) {
		// STEP 1
		long product = 1;
		for (int i = 0; i < num.length; i++) {
			product *= num[i];
		}

		print("Product of all numbers is: " + product);

		long partialProduct[] = new long[num.length];
		long inverse[] = new long[num.length];
		long sum = 0;

		for (int i = 0; i < num.length; i++) {
			partialProduct[i] = product / num[i];// floor division
			inverse[i] = computeInverse(partialProduct[i], num[i]);
			sum += partialProduct[i] * inverse[i] * rem[i];
		}

		print("Partial product array is: " + Arrays.toString(partialProduct) + "\n");
		print("Multiplicative inverse modulo of partial product: " + Arrays.toString(inverse) + "\n");
		print("Sum = " + sum + "\n");

		return sum % product;

	}

	public static long computeInverse(long a, long b) {
		long m = b, t, q;
		long x = 0, y = 1;

		if (b == 1) {
			return 0;
		}

		// Apply extended Euclid Algorithm
		while (a > 1) {
			// q is quotient
			q = a / b;
			t = b;

			// m is remainder now, process same as euclid's algo
			b = a % b;
			a = t;

			t = x;

			x = y - q * x;

			y = t;
		}

		// Make x1 positive
		if (y < 0) {
			y += m;
		}

		return y;
	}

	public void solveWithCRT() {
		System.err.println(buses);
		Map<Integer, Integer> kv = new HashMap<>();

		for (int i = 0; i < buses.size(); i++) {
			if (buses.get(i).equals("x")) {
				continue;
			}
			
			print(buses.get(i) + " " + i + " mod = " + (Integer.valueOf(buses.get(i)) - i));
			
			kv.put(i, Integer.valueOf(buses.get(i)));
		}

		
		long[] num = kv.values().stream().mapToLong(i -> i).toArray();
		long[] rem = kv.keySet().stream().mapToLong(i -> kv.get(i)-i).toArray();

//		int[] num = new int[3];
//		int[] rem = new int[3];
//		num[0] = 17;
//		num[1] = 13;
//		num[2] = 19;
//		
//		rem[0] = 0;
//		rem[1] = 11;
//		rem[2] = 16;
		System.out.println("Result: " + computeMinX(rem, num));
	}

	public void solveA() {
		List<String> filtered = buses.stream().filter(x -> !x.equals("x")).collect(Collectors.toList());

		for (int i = departureTime; i < (departureTime + 50); i++) {
			for (String bus : filtered) {
				int bId = Integer.valueOf(bus);
				if (i % bId == 0) {
					// System.out.println("Bus: " + bus + ", " + i);
					int result = (i - departureTime) * bId;
					System.out.println("Result: " + result);
					return;
				}
			}
		}
	}

	public static void main(String[] args) {
		Day13Brian b = new Day13Brian();
		b.solveWithCRT();
	}
}
