/**
 * Preference file version 092020
 * 
 * @author: Your First and Last name goes here!
 */

package com.bkds.advent2020.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.bkds.advent2020.DayBase;

public class Day10Mae extends DayBase
{
  private List<Integer> inputData = new ArrayList<>();

  public Day10Mae()
  {
    readData("day10", "mae");
    Collections.sort(inputData);
  }

  public void solvePartOne()
  {
    System.out.println(inputData);

    int jump1 = 0;
    int jump3 = 1;
    int source = 0;

    List<Integer> results = findPluggable(source);
    System.out.println(results);
    while (results != null && !results.isEmpty())
    {
      int nxtVal = results.get(0);
      if (nxtVal - source == 1)
      {
        jump1++;
      }
      else if (nxtVal - source == 3)
      {
        jump3++;
      }
      source = nxtVal;
      results = findPluggable(source);

    }
    int total = jump1 * jump3;
    System.out.println(total);

  }

  public static void main(String[] args)
  {
    Day10Mae day = new Day10Mae();
    day.solvePartOne();
  }

  // finds numbers within 0-3 of source
  public List<Integer> findPluggable(int source)
  {
    List<Integer> result =
      inputData.stream().filter(x -> (x >= source + 1 && (x <= source + 3)))
        .collect(Collectors.toList());

    return result;
  }

  public void store(String input)
  {
    // TODO Auto-generated method stub
    inputData.add(Integer.valueOf(input));

  }

}
