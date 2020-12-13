/**
 * Preference file version 092020
 * 
 * @author: Your First and Last name goes here!
 */

package com.bkds.advent2020.day12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bkds.advent2020.DayBase;

public class Day12Mae extends DayBase
{
  private List<String> navInstructions = new ArrayList<>();
  private Map<String, List<String>> rCoords = new HashMap<>();
  private Map<String, List<String>> lCoords = new HashMap<>();

  public Day12Mae()
  {
    readData("day12", "mae");
    populateRCoords();
    populateLCoords();
  }

  private void populateRCoords()
  {
    this.rCoords.put("N", Arrays.asList("E", "S", "W", "N"));
    this.rCoords.put("S", Arrays.asList("W", "N", "E", "S"));
    this.rCoords.put("E", Arrays.asList("S", "W", "N", "E"));
    this.rCoords.put("W", Arrays.asList("N", "E", "S", "W"));
  }

  private void populateLCoords()
  {
    this.lCoords.put("N", Arrays.asList("W", "S", "E", "N"));
    this.lCoords.put("S", Arrays.asList("E", "N", "W", "S"));
    this.lCoords.put("E", Arrays.asList("N", "W", "S", "E"));
    this.lCoords.put("W", Arrays.asList("S", "E", "N", "W"));
  }

  public String rotateRight(String currentDirection, int degrees)
  {
    double rotateAmount = degrees / 360.0;
    int index = ((int) (4 * rotateAmount) - 1);
    String newDirection = rCoords.get(currentDirection).get(index);
    return newDirection;
  }

  public String rotateLeft(String currentDirection, int degrees)
  {
    double rotateAmount = degrees / 360.0;
    int index = ((int) (4 * rotateAmount) - 1);
    String newDirection = lCoords.get(currentDirection).get(index);
    return newDirection;
  }

  public void solvePartOne()
  {
    int northSouth = 0;
    int eastWest = 0;
    String currentDirection = "E";

    for (String nav : navInstructions)
    {
      System.out.println(nav);
      String direction = nav.substring(0, 1);
      int dD = Integer.valueOf(nav.substring(1));
      // System.out.println(direction);
      // System.out.println(dD + "\n");

      if (direction.equals("N"))
      {
        northSouth += dD;
      }
      else if (direction.equals("S"))
      {
        northSouth -= dD;
      }
      else if (direction.equals("E"))
      {
        eastWest += dD;
      }
      else if (direction.equals("W"))
      {
        eastWest -= dD;
      }
      else if (direction.equals("L"))
      {
        currentDirection = rotateLeft(currentDirection, dD);
      }
      else if (direction.equals("R"))
      {
        currentDirection = rotateRight(currentDirection, dD);
      }
      else if (direction.equals("F"))
      {
        if (currentDirection.equals("N"))
        {
          northSouth += dD;
        }
        else if (currentDirection.equals("S"))
        {
          northSouth -= dD;
        }
        else if (currentDirection.equals("E"))
        {
          eastWest += dD;
        }
        else if (currentDirection.equals("W"))
        {
          eastWest -= dD;
        }
      }
      System.out.println("moving east/west:" + eastWest);
      System.out.println("moving north/south:" + northSouth);
      System.out.println("rotating: " + currentDirection);
    }
    int manhattenDistance = Math.abs(eastWest) + Math.abs(northSouth);

    System.out.println("Manhatten distance: " + manhattenDistance);

  }

  public static void main(String[] args)
  {
    Day12Mae d12 = new Day12Mae();
    d12.solvePartOne();

  }

  public void store(String input)
  {
    // TODO Auto-generated method stub
    navInstructions.add(input);

  }

}
