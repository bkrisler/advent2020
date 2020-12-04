package com.bkds.advent2020.day3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bkds.advent2020.DayBase;

public class Day3 extends DayBase
{

  private List<List<String>> rows = new ArrayList<>();

  public Day3(String person)
  {
    readData("day3", person);
  }

  @Override
  public void store(String input)
  {
    // Step 2: Store the input data into the desired data structure.
    // System.out.println(input);
    String[] row = input.split("");
    List<String> temp = new ArrayList<>();
    Collections.addAll(temp, row);
    rows.add(temp);
  }

  // Step 3: Create a method for answering the first part of the problem.
  public int solveProblem(int right, int down)
  {
    int charChecker = right;
    int treeCount = 0;
    for (int i = down; i < rows.size(); i = i + down)
    {
      List<String> row = rows.get(i);
      String positionAt = row.get(charChecker % row.size());
      charChecker += right;
      if (positionAt.equals("#"))
      {
        treeCount++;
      }
    }
    System.out.println(right + " right and " + down + " down equals "
      + treeCount + " trees");
    return treeCount;
  }

  public static void main(String[] args)
  {
    if (args.length == 0)
    {
      System.err.println("Add person name as an argument!");
      return;
    }
    String person = args[0];

    // Step 1: Construct a new class for testing and call the "solve" methods
    Day3 dayThree = new Day3(person);
    long a = dayThree.solveProblem(1, 1);
    long b = dayThree.solveProblem(3, 1);
    long c = dayThree.solveProblem(5, 1);
    long d = dayThree.solveProblem(7, 1);
    long e = dayThree.solveProblem(1, 2);
    long allDone = (a * b * c * d * e);

    System.out.println(a + " " + b + " " + c + " " + d + " " + e);
    System.out.println(allDone);

  }

}
