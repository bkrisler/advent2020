package com.bkds.advent2020.day1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bkds.advent2020.DayBase;

public class DayOneMae extends DayBase
{

  private List<Integer> expenses = new ArrayList<>();

  public DayOneMae(String person)
  {
    super();
    this.readData("day1", person);

  }

  @Override
  public void store(String input) {
  	expenses.add(Integer.valueOf(input));	
  }

  public void dumpData()
  {
    for (Integer expense : expenses)
    {
      System.out.println(expense);
    }
  }

  /**
   * Solve the Day 1 problem. Objective:
   * 
   * Find the two numbers that add up to 2020,
   * multiply them and return the result.
   * 
   */
  public int solvePart1()
  {
    // Step 1: Sort the list
    Collections.sort(expenses);
    dumpData();
    Integer result = 0;

    // Step 2: Create 3 temporary lists:
    ArrayList<Integer> lessThan = new ArrayList<>();
    ArrayList<Integer> moreThan = new ArrayList<>();
    ArrayList<Integer> equalTo = new ArrayList<>();

    for (Integer expense : expenses)
    {
      if (expense < 2020 / 2)
      {
        lessThan.add(expense);
      }

      if (expense > 2020 / 2)
      {
        moreThan.add(expense);
      }

      if (expense == 2020 / 2)
      {
        equalTo.add(expense);
      }
    }

    for (Integer lList : lessThan)
    {
      for (Integer mList : moreThan)
      {
        if (lList + mList == 2020)
        {
          return lList * mList;
        }
      }
    }

    System.out.println(lessThan.size());
    System.out.println(moreThan.size());
    System.out.println(equalTo.size());

    return result;
  }

  public int solvePart2()
  {
    ArrayList<Integer> greaterThanHalf = new ArrayList<>();
    ArrayList<Integer> lessThanHalf = new ArrayList<>();

    for (Integer expense : expenses)
    {
      if (expense > 2020 / 2)
      {
        greaterThanHalf.add(expense);
      }

      if (expense < 2020 / 2)
      {
        lessThanHalf.add(expense);
      }

    }

    for (Integer gEntry : greaterThanHalf)
    {
      for (Integer lentry : lessThanHalf)
      {
        if (gEntry + lentry < 2020)
        {
          int diff = 2020 - (gEntry + lentry);
          if (lessThanHalf.contains(diff))
          {
            int thirdNumber = lessThanHalf.get(lessThanHalf.indexOf(diff));
            return gEntry * lentry * thirdNumber;
          }

        }
      }
    }
    return 0;
  }

  public static void main(String[] args)
  {
    String person = "unknown";
    if (args.length > 0)
    {
      person = args[0];
    }

    DayOneMae dOne = new DayOneMae(person);
    int answer1 = dOne.solvePart1();
    System.out.println("The answer for part one is: " + answer1);

    int answer2 = dOne.solvePart2();
    System.out.println("The answer for part two is: " + answer2);

  }

}
