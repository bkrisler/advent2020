/**
 * Preference file version 092020
 * 
 * @author: Your First and Last name goes here!
 */

package com.bkds.advent2020.day9;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.bkds.advent2020.DayBase;

public class Day9Mae extends DayBase
{
  public static int SIZE = 25;
  private List<Long> inputData = new ArrayList<>();

  public Day9Mae()
  {
    readData("day9", "mae");
  }

  public void store(String input)
  {
    // System.out.println(input);
    inputData.add(Long.valueOf(input));

  }

  public void solvePartOne(List<Long> input)
  {
    Long target = input.get(input.size() - 1);
    // System.out.println("Target: " + target);
    for (int i = 0; i < SIZE; i++)
    {
      for (int j = i + 1; j < SIZE; j++)
      {
        long addTwo = input.get(i) + input.get(j);
        // System.out.println(input.get(i) + " " + input.get(j));
        // System.out.println(addTwo);
        if (target == addTwo)
        {
          // System.out.println("valid");
          return;
        }
      }
    }
    System.out.println("Not valid " + target);
  }

  public void solve()
  {
    sliding(inputData, SIZE + 1).forEach(this::solvePartOne);

  }

  private static <T> Stream<List<T>> sliding(List<T> list, int size)
  {
    if (size > list.size())
    {
      return Stream.empty();
    }
    else
    {
      return IntStream.range(0, list.size() - size + 1)
        .mapToObj(start -> list.subList(start, start + size));
    }
  }

  public static void main(String[] args)
  {
    Day9Mae day = new Day9Mae();
    day.solve();
    day.solveTwo();
  }

  public void solveTwo()
  {
    long target = 1398413738;
    List<Long> range = new ArrayList<>();
    for (int i = 0; i < inputData.size(); i++)
    {
      long sum = inputData.get(i);
      range.add(inputData.get(i));
      for (int j = i + 1; j < inputData.size(); j++)
      {
        range.add(inputData.get(j));
        sum += inputData.get(j);
        if (sum > target)
        {
          // start over
          break;
        }
        else if (sum == target)
        {
          // all over
          Collections.sort(range);
          long answer = range.get(0) + range.get(range.size() - 1);
          System.out.println("The answer is " + range);
          System.out.println("The second answer is " + answer);
          return;
        }
      }
      range.clear();
    }

  }

}
