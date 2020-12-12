package com.bkds.advent2020.day11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.bkds.advent2020.DayBase;

public class Day11Brian extends DayBase {

	List<List<String>> seatLayout = new ArrayList<>();

	public Day11Brian() {
		readData("day11", "brian");
	}

	public boolean isEmpty(int row, int col, List<List<String>> model) {
		String seat = getSeat(row, col, model);
		return seat == null || (seat.equals("L") || seat.equals("."));
	}

	public boolean isOccupied(int row, int col, List<List<String>> model) {
		String seat = getSeat(row, col, model);
		return seat != null && seat.equals("#");
	}

	public boolean isFloor(int row, int col, List<List<String>> model) {
		String seat = getSeat(row, col, model);
		return seat != null && seat.equals(".");
	}

	public int countAdjacent(int row, int col, List<List<String>> model) {
		int count = 0;
		if (!isEmpty(row - 1, col - 1, model)) {
			count++;
		}

		if (!isEmpty(row - 1, col, model)) {
			count++;
		}

		if (!isEmpty(row - 1, col + 1, model)) {
			count++;
		}

		if (!isEmpty(row, col - 1, model)) {
			count++;
		}

		if (!isEmpty(row, col + 1, model)) {
			count++;
		}

		if (!isEmpty(row + 1, col - 1, model)) {
			count++;
		}

		if (!isEmpty(row + 1, col, model)) {
			count++;
		}

		if (!isEmpty(row + 1, col + 1, model)) {
			count++;
		}

		return count;
	}

	public boolean hasAdjacent(int row, int col, List<List<String>> model) {
		// Check row above
		for (int c = (col - 1); c < col + 2; c++) {
			String seat = getSeat(row - 1, c, model);
			if (seat != null && seat.equals("#")) {
				return true;
			}
		}

		// Check Left
		String seat = getSeat(row, col - 1, model);
		if (seat != null && seat.equals("#")) {
			return true;
		}

		// Check Right
		seat = getSeat(row, col + 1, model);
		if (seat != null && seat.equals("#")) {
			return true;
		}

		// Check row below
		for (int c = (col - 1); c < col + 2; c++) {
			seat = getSeat(row + 1, c, model);
			if (seat != null && seat.equals("#")) {
				return true;
			}
		}

		return false;
	}

	public boolean hasAdjacent2(int row, int col, List<List<String>> model) {

		// Check straight above
		for (int r = (row - 1); r >= 0; r--) {
			String seat = getSeat(r, col, model);
			if (seat != null) {
				if (seat.equals("#")) {
					return true;
				} else if (seat.equals("L")) {
					break;
				}
			}
		}

		// Check straight down
		for (int r = (row + 1); r <= model.size(); r++) {
			String seat = getSeat(r, col, model);
			if (seat != null) {
				if (seat.equals("#")) {
					return true;
				} else if (seat.equals("L")) {
					break;
				}
			}
		}

		int totalCols = model.get(0).size();

		// Check right
		for (int c = (col + 1); c <= totalCols; c++) {
			String seat = getSeat(row, c, model);
			if (seat != null) {
				if (seat.equals("#")) {
					return true;
				} else if (seat.equals("L")) {
					break;
				}
			}
		}

		// Check left
		for (int c = (col - 1); c >= 0; c--) {
			String seat = getSeat(row, c, model);
			if (seat != null) {
				if (seat.equals("#")) {
					return true;
				} else if (seat.equals("L")) {
					break;
				}
			}
		}

		// Check Up / Left
		int offset = 0;
		for (int r = (row - 1); r >= 0; r--) {
			offset++;
			if (col - offset >= 0) {
				String seat = getSeat(r, col - offset, model);
				if (seat != null) {
					if (seat.equals("#")) {
						return true;
					} else if (seat.equals("L")) {
						break;
					}
				}
			}
		}

		// Check Up / Right
		offset = 0;
		for (int r = (row - 1); r >= 0; r--) {
			offset++;
			if (col - offset <= totalCols) {
				String seat = getSeat(r, col + offset, model);
				if (seat != null) {
					if (seat.equals("#")) {
						return true;
					} else if (seat.equals("L")) {
						break;
					}
				}
			}
		}

		// Check Down / Left
		offset = 0;
		for (int r = (row + 1); r <= model.size(); r++) {
			offset++;
			if (col - offset >= 0) {
				String seat = getSeat(r, col - offset, model);
				if (seat != null) {
					if (seat.equals("#")) {
						return true;
					} else if (seat.equals("L")) {
						break;
					}
				}
			}
		}

		// Check Down / Right
		offset = 0;
		for (int r = (row + 1); r <= model.size(); r++) {
			offset++;
			if (col - offset <= totalCols) {
				String seat = getSeat(r, col + offset, model);
				if (seat != null) {
					if (seat.equals("#")) {
						return true;
					} else if (seat.equals("L")) {
						break;
					}
				}
			}
		}

		return false;
	}

	public int countAdjacent2(int row, int col, List<List<String>> model) {
		int resultCount = 0;
		
		// Check straight above
		for (int r = (row - 1); r >= 0; r--) {
			String seat = getSeat(r, col, model);
			if (seat != null) {
				if (seat.equals("#")) {
					resultCount++;
					break;
				} else if (seat.equals("L")) {
					break;
				}
			}
		}

		// Check straight down
		for (int r = (row + 1); r <= model.size(); r++) {
			String seat = getSeat(r, col, model);
			if (seat != null) {
				if (seat.equals("#")) {
					resultCount++;
					break;
				} else if (seat.equals("L")) {
					break;
				}
			}
		}

		int totalCols = model.get(0).size();

		// Check right
		for (int c = (col + 1); c <= totalCols; c++) {
			String seat = getSeat(row, c, model);
			if (seat != null) {
				if (seat.equals("#")) {
					resultCount++;
					break;
				} else if (seat.equals("L")) {
					break;
				}
			}
		}

		// Check left
		for (int c = (col - 1); c >= 0; c--) {
			String seat = getSeat(row, c, model);
			if (seat != null) {
				if (seat.equals("#")) {
					resultCount++;
					break;
				} else if (seat.equals("L")) {
					break;
				}
			}
		}

		// Check Up / Left
		int offset = 0;
		for (int r = (row - 1); r >= 0; r--) {
			offset++;
			if (col - offset >= 0) {
				String seat = getSeat(r, col - offset, model);
				if (seat != null) {
					if (seat.equals("#")) {
						resultCount++;
						break;
					} else if (seat.equals("L")) {
						break;
					}
				}
			}
		}

		// Check Up / Right
		offset = 0;
		for (int r = (row - 1); r >= 0; r--) {
			offset++;
			if (col - offset <= totalCols) {
				String seat = getSeat(r, col + offset, model);
				if (seat != null) {
					if (seat.equals("#")) {
						resultCount++;
						break;
					} else if (seat.equals("L")) {
						break;
					}
				}
			}
		}

		// Check Down / Left
		offset = 0;
		for (int r = (row + 1); r <= model.size(); r++) {
			offset++;
			if (col - offset >= 0) {
				String seat = getSeat(r, col - offset, model);
				if (seat != null) {
					if (seat.equals("#")) {
						resultCount++;
						break;
					} else if (seat.equals("L")) {
						break;
					}
				}
			}
		}

		// Check Down / Right
		offset = 0;
		for (int r = (row + 1); r <= model.size(); r++) {
			offset++;
			if (col - offset <= totalCols) {
				String seat = getSeat(r, col + offset, model);
				if (seat != null) {
					if (seat.equals("#")) {
						resultCount++;
						break;
					} else if (seat.equals("L")) {
						break;
					}
				}
			}
		}

		return resultCount;
	}
	
	public String getSeat(int row, int col, List<List<String>> model) {
		try {
			List<String> fullRow = model.get(row);
			return fullRow.get(col);
		} catch (IndexOutOfBoundsException e) {
			return null;
		}
	}

	@Override
	public void store(String input) {
		List<String> row = Arrays.asList(input.split(""));
		seatLayout.add(row);
	}

	public void checkAll() {
		for (int i = 0; i < seatLayout.size(); i++) {
			for (int j = 0; j < seatLayout.get(i).size(); j++) {
				boolean hasA = hasAdjacent(i, j, seatLayout);
//				System.out.println("Has: " + hasA);
				if (hasA) {
					System.out.print(" " + "x");
				} else {
					// System.out.println("Seat: " + i + ", " + j);
					System.out.print(" " + getSeat(i, j, seatLayout));
				}
			}
			System.out.println();
		}
	}

	public List<List<String>> model(List<List<String>> input) {
		List<List<String>> newLayout = new ArrayList<>();
		for (int i = 0; i < input.size(); i++) {
			List<String> row = new ArrayList<>();
			for (int j = 0; j < input.get(i).size(); j++) {
				if (isFloor(i, j, input)) {
					row.add(".");
				} else if (isEmpty(i, j, input) && !hasAdjacent(i, j, input)) {
					row.add("#");
				} else if (isOccupied(i, j, input) && (countAdjacent(i, j, input) >= 4)) {
					row.add("L");
				} else {
					row.add(getSeat(i, j, input));
				}
			}
			newLayout.add(row);
		}
		return newLayout;
	}

	//  If a seat is empty (L) and there are no occupied seats adjacent to it, 
	//      the seat becomes occupied.
	//  If a seat is occupied (#) and four or more seats adjacent to it are 
	//      also occupied, the seat becomes empty.
	//  Otherwise, the seat's state does not change.
	public List<List<String>> model2(List<List<String>> input) {
		List<List<String>> newLayout = new ArrayList<>();
		for (int i = 0; i < input.size(); i++) {
			List<String> row = new ArrayList<>();
			for (int j = 0; j < input.get(i).size(); j++) {
				if (isFloor(i, j, input)) {
					row.add(".");
				} else if (isEmpty(i, j, input) && !hasAdjacent2(i, j, input)) {
					row.add("#");
				} else if (isOccupied(i, j, input) && (countAdjacent2(i, j, input) >= 5)) {
					row.add("L");
				} else {
					row.add(getSeat(i, j, input));
				}
			}
			newLayout.add(row);
		}
		return newLayout;
	}

	public void print(List<List<String>> map) {
		System.out.println();
		for (List<String> row : map) {
			System.out.println(row);
		}
	}

	public void solveB() {
		List<String> results = new ArrayList<>();
		List<List<String>> inputModel = seatLayout;
		print(inputModel);
		boolean done = false;
		int safekill = 0;
		while (!done) {
			if(safekill == 100) {
				System.out.println("****** Safe killed ******");
				done = true;
			}
			safekill++;
			
			List<List<String>> modelResult = model2(inputModel);
			print(modelResult);
			System.out.println();

			List<String> flat = modelResult.stream().flatMap(List::stream).collect(Collectors.toList());

			String f = String.join("", flat);
			if (results.size() > 2) {
				int comp = f.compareTo(results.get(results.size() - 1));
				if (comp == 0) {
					done = true;
				}
			}
			results.add(f);
			inputModel = modelResult;
		}

//		for(String result : results) {			
//			System.out.println(result);
//		}

		int count = 0;
		String[] allseats = results.get(results.size() - 1).split("");
		for (int i = 0; i < allseats.length; i++) {
			if (allseats[i].equals("#")) {
				count++;
			}
		}

		System.out.println("Occupied seats: " + count);

	}

	public void solveA() {
		List<String> results = new ArrayList<>();
		List<List<String>> inputModel = seatLayout;
		boolean done = false;
		while (!done) {
			List<List<String>> modelResult = model(inputModel);
//			print(modelResult);
//			System.out.println();

			List<String> flat = modelResult.stream().flatMap(List::stream).collect(Collectors.toList());

			String f = String.join("", flat);
			if (results.size() > 2) {
				int comp = f.compareTo(results.get(results.size() - 1));
				if (comp == 0) {
					done = true;
				}
			}
			results.add(f);
			inputModel = modelResult;
		}

//		for(String result : results) {			
//			System.out.println(result);
//		}

		int count = 0;
		String[] allseats = results.get(results.size() - 1).split("");
		for (int i = 0; i < allseats.length; i++) {
			if (allseats[i].equals("#")) {
				count++;
			}
		}

		System.out.println("Occupied seats: " + count);
	}

	public void test() {
		List<List<String>> inputModel = seatLayout;
		print(inputModel);
		List<List<String>> modelResult = model2(inputModel);
		print(modelResult);
	
	}
	
	public static void main(String[] args) {
		Day11Brian db = new Day11Brian();
		db.solveB();
		//db.test();
		
//		db.checkAll();
//		System.out.println("Seat: " + db.getSeat(0, 0));
//		System.out.println("Test occupied: " + db.isOccupied(0, 1));
//		System.out.println("Test floor: " + db.isFloor(0, 1));	
//		System.out.println("Test has adjacent: " + db.hasAdjacent(0, 0));
	}
}