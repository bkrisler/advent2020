package com.bkds.advent2020.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.bkds.advent2020.DayBase;
import com.bkds.advent2020.Tree;

public class Day10Brian extends DayBase
{

  int CHARGING_OUTLET_JOLTS = 0;

  List<Integer> data = new ArrayList<>();

  public Day10Brian()
  {
    readData("day10", "brian");
    Collections.sort(data);
  }

  @Override
  public void store(String input)
  {
    data.add(Integer.valueOf(input));
  }

  public void solve()
  {
    int oneJoltDiff = 0;
    int threeJoltDiff = 1; // The built in is always 3 higher than the max, so
                           // start at 1.
    int source = CHARGING_OUTLET_JOLTS;
    List<Integer> result = findPluggable(source);
    while (result != null && !result.isEmpty())
    {
      int selected = result.get(0);
      if (selected - source == 1)
      {
        oneJoltDiff++;
      }
      else if (selected - source == 3)
      {
        threeJoltDiff++;
      }
      source = selected;
      result = findPluggable(result.get(0));
    }

    System.out.println("1 Jolt Difference: " + oneJoltDiff);
    System.out.println("3 Jolt Difference: " + threeJoltDiff);
    System.out.println("Result = " + oneJoltDiff * threeJoltDiff);
  }

  Map<Integer, Tree<CNode>> monoMap = new HashMap<>();
  long childCount = 1;

  public void addChildren(Tree<CNode> treeNode)
  {
    List<Integer> paths = getPaths(treeNode.getValue().value);
    for (Integer node : paths)
    {
      Tree<CNode> tNode = null;
      if (monoMap.containsKey(node))
      {
        tNode = monoMap.get(node);
        treeNode.addChild(tNode);
      }
      else
      {
        CNode cn = new CNode(node);
        tNode = treeNode.addChild(cn);
        monoMap.put(node, tNode);
        addChildren(tNode);
      }
    }
    if (paths.size() == 0)
    {
      treeNode.getValue().leafBelowCount = 0;
      treeNode.getValue().isLeaf = true;
    }
    else
    {
      for (Tree<CNode> tc : treeNode.getChildren())
      {
        if (tc.getValue().isLeaf)
        {
          treeNode.getValue().leafBelowCount++;
        }
        else
        {
          treeNode.getValue().leafBelowCount += tc.getValue().leafBelowCount;
        }
      }
    }
  }

  private List<Integer> getPaths(Integer start)
  {
    List<Integer> paths =
      data.stream().filter(x -> (x > start) && (x <= start + 3))
        .collect(Collectors.toList());
    return paths;
  }

  public void solve2()
  {
    CNode start = new CNode(0);
    Tree<CNode> tree = new Tree<>(start);
    System.out.println();
    addChildren(tree);
    // Tree.dumpTree(tree, 0);
    System.out.println("Child Count: " + tree.getValue().leafBelowCount);
  }

  // public long factorial(int n) {
  // return LongStream.rangeClosed(1, n).reduce(1, (long x, long y) -> x * y);
  // }
  //
  // public long combination(int n, int k) {
  // long result = (factorial(n) / factorial(k) * (factorial(n - k)));
  // return result;
  // }

  public List<Integer> findPluggable(int source)
  {
    List<Integer> result =
      data.stream().filter(x -> (x >= source + 1 && (x <= source + 3)))
        .collect(Collectors.toList());

    return result;
  }

  public List<Integer> findPluggable2(int source, List<Integer> d)
  {
    List<Integer> result =
      d.stream().filter(x -> (x >= source + 1 && (x <= source + 3)))
        .collect(Collectors.toList());

    return result;
  }

  public int buildInJoltage()
  {
    IntSummaryStatistics stats =
      data.stream().mapToInt((x) -> x).summaryStatistics();
    int max = stats.getMax();
    return max + 3;
  }

  public static void main(String[] args)
  {
    Day10Brian b = new Day10Brian();
    b.solve2();
    System.out.println("Done");
  }

  class CNode
  {
    public int value = 0;
    public long leafBelowCount = 0;
    public boolean isLeaf = false;

    public CNode(int value)
    {
      this.value = value;
    }

    @Override
    public String toString()
    {
      return "CNode [value=" + value + ", leafBelowCount=" + leafBelowCount
        + "]";
    }
  }
}
