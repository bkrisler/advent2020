package com.bkds.advent2020.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.bkds.advent2020.DayBase;
import com.bkds.advent2020.Tree;

public class Day7Brian extends DayBase {

	private Map<String, List<String>> bags = new HashMap<>();
	private Map<String, List<BagNode>> bagNodes = new HashMap<>();

	public Day7Brian() {
		readData("day7", "mae");
	}

	@Override
	public void store(String input) {
		String[] part1 = input.split(" contain ");

		// Get the Bag Color from input
		String bagColor = part1[0].trim();
		if (bagColor.endsWith("s")) {
			// Strip off the s
			bagColor = bagColor.substring(0, bagColor.length() - 1);
		}

		// Get the outer bags now
		List<String> outerBagList = new ArrayList<>();
		List<BagNode> obNList = new ArrayList<>();

		if (!(part1[1].equals("no other bags."))) {
			String[] outerBags = part1[1].split(",");
			for (String bag : outerBags) {
				bag = bag.trim();
				if (bag.endsWith(".")) {
					bag = bag.substring(0, bag.length() - 1);
				}

				String count = bag.substring(0, 1);
				String pColor = bag.substring(1, bag.length()).trim();
				if (pColor.endsWith("s")) {
					// Strip off the s
					pColor = pColor.substring(0, pColor.length() - 1);
				}

				BagNode cNode = new BagNode(pColor, Integer.valueOf(count));
				outerBagList.add(cNode.getColor());
				obNList.add(cNode);
			}
		}
		bags.put(bagColor, outerBagList);
		bagNodes.put(bagColor, obNList);
	}

	public List<Tree<BagNode>> buildAllTrees() {
		List<Tree<BagNode>> allTrees = new ArrayList<>();
		for(String color : bags.keySet()) {
			allTrees.add(buildTree(color));
		}
		return allTrees;
	}
	
	private Tree<BagNode> buildTree(String rootName) {
		Tree<BagNode> root = new Tree<>(new BagNode(rootName, 1));
		List<String> children = bags.get(rootName);
		populate(root, children);
		return root;		
	}
	
	private int countForColor(String parent, String color) {
		List<BagNode> bnList = bagNodes.get(parent);
		List<Integer> count = bnList.stream().filter(n -> n.getColor().equals(color)).map(BagNode::getCount).collect(Collectors.toList());
		return count.get(0);
	}
	
	private void populate(Tree<BagNode> parent, List<String> children) {
		for (String color : children) {
			int count = countForColor(parent.getValue().getColor(), color);
			Tree<BagNode> child = parent.addChild(new BagNode(color, count));
			if (!bags.get(color).isEmpty()) {
				populate(child, bags.get(color));
			}
		}
	}

	private int findContains(List<Tree<BagNode>> allTrees, String color) {
		int count = 0;
		for(Tree<BagNode> tree : allTrees) {
			if(!tree.getValue().getColor().equals(color)) {
				Optional<Tree<BagNode>> result = Tree.search(new BagNode(color, 0), tree);
				if(result.isPresent()) {
					count++;
				}
			}
		}
		return count;
	}


	private void addSpace(int count) {
		for(int i=0; i < count; i++) {
			System.out.print(" ");
		}
	}
	
	private int countRequiredBags(Tree<BagNode> tree, int spc) {
		int fullCount = 0;
		int childTotal = 0;
		if(tree.getChildren().size() == 0) {
			return 0;
		} else {		
//			addSpace(spc+2);
//			System.out.println(tree.getValue().getCount() + " " + tree.getValue().getColor() + " contains: ");
			for(Tree<BagNode> child : tree.getChildren()) {
//				addSpace(spc);
//				System.out.println("  \u2515 " + child.getValue().getCount() + " " + child.getValue().getColor());
				childTotal += child.getValue().getCount();
				int cResult = countRequiredBags(child, (spc+2));
				childTotal += cResult;
			}
			fullCount += childTotal * tree.getValue().getCount();
		}

//		addSpace(spc+2);
//		System.out.println("--->> " + tree.getValue().getCount() + " " + 
//		tree.getValue().getColor() + " contains = (" + childTotal + " * " + 
//				tree.getValue().getCount() + ") = " + fullCount + " bags.");
		
		return fullCount;
	}

	public static void main(String[] args) {
		Day7Brian d7b = new Day7Brian();
		List<Tree<BagNode>> allTrees = d7b.buildAllTrees();
		
		// Part One
		int count = d7b.findContains(allTrees, "shiny gold bag");
		System.out.println("shiny gold bag is in: " + count + " bags.");
		
		System.out.println("\n--------------------------\n");
		
		// Part Two
		for(Tree<BagNode> tree : allTrees) {
			if(tree.getValue().getColor().equals("shiny gold bag")) {
				//Tree.dumpTree(tree, 0);

				int requiredBags = d7b.countRequiredBags(tree, 0);
				System.out.println("You need: " + (requiredBags) + " bags.");
				break;
			}
		}
	}
}