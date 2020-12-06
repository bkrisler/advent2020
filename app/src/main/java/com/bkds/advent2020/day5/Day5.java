package com.bkds.advent2020.day5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bkds.advent2020.DayBase;

public class Day5 extends DayBase {

	private List<String> rows = new ArrayList<>();
	private List<Integer> seatIds = new ArrayList<>();

	public Day5() {
		readData("day5", "mae");
	}

	@Override
	public void store(String input) {
		rows.add(input);
	}

	public void maeOneSolution() {
		// Step 2: Add your solution here.
		int maxSeatId = 0;
		List<Integer> seatIds = new ArrayList<>();
		for (String row : rows) {

			int minRow = 0;
			int maxRow = 127;
			int finalRow = 0;
			int finalCol = 0;
			String[] parts = row.split("");
			System.out.println("");
			for (int i = 0; i < 7; i++) {
				String part = parts[i];
				if (part.equals("F")) {
					maxRow = (maxRow + minRow) / 2;
				} else {
					minRow = 1 + ((maxRow + minRow) / 2);
				}
				System.out.println(part + " " + minRow + " " + maxRow);
			}
			// System.out.println(row);
			finalRow = maxRow;
			int minCol = 0;
			int maxCol = 7;
			for (int i = 7; i < 10; i++) {
				String part = parts[i];
				if (part.equals("L")) {
					maxCol = (maxCol + minCol) / 2;
				} else {
					minCol = 1 + ((maxCol + minCol) / 2);
				}
				System.out.println(part + " " + minCol + " " + maxCol);
			}
			finalCol = maxCol;
			int seatId = (finalRow * 8 + finalCol);
			if (seatId > maxSeatId) {
				maxSeatId = seatId;
			}
			System.out.println(seatId);
			seatIds.add(seatId);
		}
		System.out.println(maxSeatId);
		System.out.println("\n Part Two: \n");
		Collections.sort(seatIds);
		System.out.println(seatIds);
		int current = seatIds.get(0);
		for (int i = 0; i < seatIds.size(); i++) {
			int seat = seatIds.get(i);
			if (seat == current) {
				current++;
			} else {
				System.out.println("Found my seat! " + (seat - 1));
				break;
			}

		}
	}

	public void brianOneSolution() {
		int maxSeatId = 0;

		for (String row : rows) {
			int finalRow = -1;
			int finalCol = -1;

			int minSeat = 0;
			int maxSeat = 127;
			String[] parts = row.split("");
			// System.out.println(row);
			for (int i = 0; i < 6; i++) {
				String part = parts[i];
				if (part.equals("F")) {
					maxSeat = ((maxSeat - minSeat) / 2) + minSeat;
				} else if (part.equals("B")) {
					minSeat = 1 + ((maxSeat - minSeat) / 2) + minSeat;
				}
				// System.out.println("pos: " + part + " min: " + minSeat + " max: " + maxSeat);
			}
			String last = parts[6];
			if (last.equals("F")) {
				finalRow = minSeat;
			} else {
				finalRow = maxSeat;
			}

			// System.out.println("Row is: " + finalRow);

			int mnC = 0;
			int mxC = 7;
			for (int i = 7; i < 9; i++) {
				String part = parts[i];
				if (part.equals("L")) {
					// System.out.print("Left: mxC = ((" + mxC + " - " + mnC + ") / 2) + " + mnC);
					mxC = ((mxC - mnC) / 2) + mnC;
					// System.out.println(" = " + mxC);
				} else if (part.equals("R")) {
					// System.out.print("Right: mnC = ((" + mxC + " - " + mnC + ") / 2) + " + mnC);
					mnC = 1 + ((mxC - mnC) / 2) + mnC;
					// System.out.println(" = " + mnC);
				}
				// System.out.println(part + " : " + mnC + ", " + mxC);
			}

			String fc = parts[9];
			if (fc.equals("L")) {
				finalCol = mnC;
			} else {
				finalCol = mxC;
			}

			int seatId = (8 * finalRow) + finalCol;
			this.seatIds.add(seatId);

			if (seatId > maxSeatId) {
				maxSeatId = seatId;
			}
			// System.out.println(row + " is seat row " + finalRow + ", column " + finalCol
			// + ", seat ID: " + seatId);
		}
		System.out.println("Max Seat ID: " + maxSeatId);
	}

	public void solvePartTwo() {
		Collections.sort(seatIds);
		int idx = seatIds.get(0);
		for (int i = 0; i < seatIds.size(); i++) {
			if (seatIds.get(i) == idx) {
				idx++;
			} else {
				System.out.println("Found it: " + idx);
				break;
			}
		}
	}

	public static void main(String[] args) {
		Day5 five = new Day5();
		five.brianOneSolution();
		five.solvePartTwo();
		five.maeOneSolution();
	}

}
