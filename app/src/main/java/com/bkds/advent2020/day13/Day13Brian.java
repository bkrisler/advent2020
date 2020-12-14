package com.bkds.advent2020.day13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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

//////

	public static int computeMinX(int[] rem, int[] num) {
		// STEP 1
		int product = 1;
		for (int i = 0; i < num.length; i++) {
			product *= num[i];
		}

		print("Product of all numbers is: " + product);

		int partialProduct[] = new int[num.length];
		int inverse[] = new int[num.length];
		int sum = 0;

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

	public static void print(String arg) {
		System.out.println(arg);
	}

	public static int computeInverse(int a, int b) {
		int m = b, t, q;
		int x = 0, y = 1;

		if (b == 1)
			return 0;

		// Apply extended Euclid Algorithm
		while (a > 1) {
			// q is quotient
			q = a / b;
			t = b;

			// m is remainder now, process
			// same as euclid's algo
			b = a % b;
			a = t;

			t = x;

			x = y - q * x;

			y = t;
		}

		// Make x1 positive
		if (y < 0)
			y += m;

		return y;
	}
	//////

	public void solveWithCRT() {
		System.err.println(buses);
		Map<Integer, Integer> kv = new HashMap<>();

		for (int i = 0; i < buses.size(); i++) {
			if (buses.get(i).equals("x")) {
				continue;
			}
			kv.put(i, Integer.valueOf(buses.get(i)));
		}

		int[] num = kv.values().stream().mapToInt(i -> i).toArray();
		int[] rem = kv.keySet().stream().mapToInt(i -> i).toArray();
		for(int j=0; j < rem.length; j++) {
			rem[j]++;
		}
		
		System.out.println("Result: " + computeMinX(rem, num));
	}

	// Returns modulo inverse of a
	// with respect to m using extended
	// Euclid Algorithm. Refer below post for details:
	// https://www.geeksforgeeks.org/multiplicative-inverse-under-modulo-m/
	static int inv(int a, int m) {
		int m0 = m, t, q;
		int x0 = 0, x1 = 1;

		if (m == 1)
			return 0;

		// Apply extended Euclid Algorithm
		while (a > 1) {
			// q is quotient
			q = a / m;

			t = m;

			// m is remainder now, process
			// same as euclid's algo
			m = a % m;
			a = t;

			t = x0;

			x0 = x1 - q * x0;

			x1 = t;
		}

		// Make x1 positive
		if (x1 < 0)
			x1 += m0;

		return x1;
	}

	// k is size of num[] and rem[].
	// Returns the smallest number
	// x such that:
	// x % num[0] = rem[0],
	// x % num[1] = rem[1],
	// ..................
	// x % num[k-2] = rem[k-1]
	// Assumption: Numbers in num[] are pairwise
	// coprime (gcd for every pair is 1)
	static int findMinX(int num[], int rem[], int k) {
		// Compute product of all numbers
		int prod = 1;
		for (int i = 0; i < k; i++)
			prod *= num[i];

		// Initialize result
		int result = 0;

		// Apply above formula
		for (int i = 0; i < k; i++) {
			int pp = prod / num[i];
			result += rem[i] * inv(pp, num[i]) * pp;
		}

		return result % prod;
	}

	protected int closest(int x, int y) {
		int quotient = x / y;

		int n1 = y * quotient;
		int n2 = (x * y) > 0 ? (y * (quotient + 1)) : (y * (quotient - 1));

		if (Math.abs(x - n1) < Math.abs(x - n2)) {
			return n1;
		}

		return n2;
	}

	protected boolean allMatch(long startIdx, List<String> num) {
		// System.out.println("--> " + startIdx + " :: " + num);
		long index = startIdx;
		for (int i = 0; i < num.size(); i++) {
			String n = num.get(i);
			if (n.equals("x")) {
				index++;
				continue;
			} else {
				Integer nInt = Integer.valueOf(n);
//				System.out.println("Check: " + index + " % " + nInt + " = " + (index % nInt));
				if (index % nInt != 0) {
					return false;
				}
				index++;
			}
			// System.out.println(num.get(i));
		}
		return true;
	}

	public void solveC() {
		System.out.println("Buses: " + buses.size());
		int busId = Integer.valueOf(buses.get(0));
		int closest = closest(departureTime, busId);
		int lastBus = Integer.valueOf(buses.get(buses.size() - 1));
		long i = closest;
		boolean done = false;

		while (!done) {
//			System.out.println("["+i+"]  ");

			if (i % busId == 0) {
				// System.out.println("First matches! " + lastBus + " " + i+buses.size());
				if ((i + (buses.size() - 1)) % lastBus == 0) {
					System.out.println("First and Last Matches at: " + i + " and " + (i + (buses.size() - 1)));
					done = allMatch(i + 1, buses.subList(1, buses.size() - 1));
					if (done) {
						System.out.println("Solution = " + i);
					}
				}
			}

			i = i + busId;
		}
	}

	private long findJump() {
		// Get the largest bus id from the list.
		List<Integer> filtered = buses.stream().filter(x -> !x.equals("x")).map(Integer::valueOf)
				.collect(Collectors.toList());

		Integer maxBusId = Collections.max(filtered);
		int maxBusIdIndex = buses.indexOf(String.valueOf(maxBusId));
		long closestToMaxBusId = closest(departureTime, maxBusId);

		int firstBusId = Integer.valueOf(buses.get(0));
		int lastBusId = Integer.valueOf(buses.get(buses.size() - 1));

		boolean done = false;
		long crntIndex = closestToMaxBusId;
		long lastMatch = 0;
		long result = 0;
		while (!done) {
			if (crntIndex % maxBusId == 0) {
				if ((crntIndex - maxBusIdIndex) % firstBusId == 0) {
					if ((crntIndex + (buses.size() - 1)) % lastBusId == 0) {
						// System.out.println("Match: " + crntIndex);
						if (lastMatch == 0) {
							lastMatch = crntIndex;
						} else {
							result = crntIndex - lastMatch;
							done = true;
						}
					}
				}
			}

			crntIndex += maxBusId;
		}

		return result;
	}

	public void solveD() {
		// Get the largest bus id from the list.
		List<Integer> filtered = buses.stream().filter(x -> !x.equals("x")).map(Integer::valueOf)
				.collect(Collectors.toList());

		Integer maxBusId = Collections.max(filtered);
		int maxBusIdIndex = buses.indexOf(String.valueOf(maxBusId));
		long closestToMaxBusId = closest(departureTime, maxBusId);

		int firstBusId = Integer.valueOf(buses.get(0));
		int lastBusId = Integer.valueOf(buses.get(buses.size() - 1));

		System.out.println("Find");
		long jump = findJump();
		System.out.println("Found");
		long inc = maxBusId;

		boolean done = false;
		long crntIndex = closestToMaxBusId;
		while (!done) {
			if (crntIndex % maxBusId == 0) {
				if ((crntIndex - maxBusIdIndex) % firstBusId == 0) {
					if ((crntIndex + (buses.size() - 1)) % lastBusId == 0) {
						System.out.println("Match: " + crntIndex);
						inc = jump;
						done = allMatch(crntIndex + 1, buses.subList(1, buses.size() - 1));
						if (done) {
							System.out.println("Solution = " + crntIndex);
						}
					}
				}
			}
			// System.out.println("Index: " + crntIndex);
			// System.out.println("Jump: " + inc);
			crntIndex += inc;
		}
	}

	public void solveB() {
		int busId = Integer.valueOf(buses.get(0));
		long nxtId = 0;
		int matches = 0;
		int closest = closest(departureTime, busId);
		int lastBus = Integer.valueOf(buses.get(buses.size() - 1));
		int closestToLast = closest(departureTime + buses.size(), lastBus);
		long i = closest;
		boolean done = false;

		while (!done) {
			System.out.println("[" + i + "]  ");
			if (i % busId == 0) {
				matches++;
				// System.out.println("\n Check others [" + i + "]");
				for (int j = 1; j < buses.size(); j++) {
					if (buses.get(j).equals("x")) {
						// System.out.println(" Skip");
						matches++;
						continue;
					}
					// System.out.println(" [" + (i+j) +"] " + buses.get(j));
					nxtId = Integer.valueOf(buses.get(j));
					if ((i + j) % nxtId == 0) {
						matches++;
						// System.out.println(" Found (" + nxtId + ")");
					}
				}
			}
			if (matches == buses.size()) {
				System.out.println("Solution: " + i);
				done = true;
			}
			i = i + busId;
			matches = 0;
			// System.out.println();
		}
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
