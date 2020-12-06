package com.bkds.advent2020.day5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bkds.advent2020.DayBase;

public class Day5 extends DayBase
{

  private List<String> rows = new ArrayList<>();

  public Day5()
  {
    readData("day5", "mae");
  }

  @Override
  public void store(String input)
  {
    rows.add(input);
  }

  public void maeOneSolution()
  {
    // Step 2: Add your solution here.
    int maxSeatId = 0;
    List<Integer> seatIds = new ArrayList<>();
    for (String row : rows)
    {

      int minRow = 0;
      int maxRow = 127;
      int finalRow = 0;
      int finalCol = 0;
      String[] parts = row.split("");
      System.out.println("");
      for (int i = 0; i < 7; i++)
      {
        String part = parts[i];
        if (part.equals("F"))
        {
          maxRow = (maxRow + minRow) / 2;
        }
        else
        {
          minRow = 1 + ((maxRow + minRow) / 2);
        }
        System.out.println(part + " " + minRow + " " + maxRow);
      }
      // System.out.println(row);
      finalRow = maxRow;
      int minCol = 0;
      int maxCol = 7;
      for (int i = 7; i < 10; i++)
      {
        String part = parts[i];
        if (part.equals("L"))
        {
          maxCol = (maxCol + minCol) / 2;
        }
        else
        {
          minCol = 1 + ((maxCol + minCol) / 2);
        }
        System.out.println(part + " " + minCol + " " + maxCol);
      }
      finalCol = maxCol;
      int seatId = (finalRow * 8 + finalCol);
      if (seatId > maxSeatId)
      {
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
    for (int i = 0; i < seatIds.size(); i++)
    {
      int seat = seatIds.get(i);
      if (seat == current)
      {
        current++;
      }
      else
      {
        System.out.println("Found my seat! " + (seat - 1));
        break;
      }

    }
  }

  public void brianOneSolution()
  {

  }

  public static void main(String[] args)
  {
    // Step 1: Create a new Day5 object and call your solution method.
    Day5 d5 = new Day5();
    d5.maeOneSolution();
  }
}
