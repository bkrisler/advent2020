package com.bkds.advent2020.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.bkds.advent2020.DayBase;
import com.bkds.advent2020.Tree;

public class Day7Brian extends DayBase {

	private Map<String, List<String>> bags = new HashMap<>();
	private Tree<BagNode> bagTree = new Tree<>(new BagNode("", 0));
	
	public Day7Brian() {
		readData("day7", "test");
		populate(bagTree, new ArrayList<>(bags.keySet()));
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

		// Tree<BagNode> treeNode = bagTree.addChild(new BagNode(bagColor, 0));

		// Get the outer bags now
		List<String> outerBagList = new ArrayList<>();
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
//				treeNode.addChild(cNode);
				outerBagList.add(cNode.getColor());
			}
		}
		bags.put(bagColor, outerBagList);
	}

	private void populate(Tree<BagNode> parent, List<String> children) {
		for (String color : children) {
			Tree<BagNode> child = parent.addChild(new BagNode(color, 0));
			if (!bags.get(color).isEmpty()) {
				populate(child, bags.get(color));
			}
		}
	}

//	private void printPath(Tree<BagNode> node, StringBuffer path) {
//		if(node.getValue() == null) {
//			return;
//		}
//		path.append(" " + node.getValue().getColor());
//		if(node.getChildren().isEmpty()) {
//			System.out.println(path);
//		} else {
//			for(Tree<BagNode> child : node.getChildren()) {
//				printPath(child, path);
//			}
//		}
//	}

	private int count = 0;
	
	private String walkTree(Tree<BagNode> node, String path, int space) {

		if(node.getChildren().size() == 0) {
			System.out.println(path);
			System.out.println("");
			count++;
		}
		
		for(Tree<BagNode> child : node.getChildren()) {
			String fwd = path;
			fwd += "\n";
			for(int i=0; i < space; i++) {
				fwd += " ";
			}
			fwd += "\u2515 ";
			fwd += child.getValue().getColor();
			walkTree(child, fwd, (space + 2));
		}

		return node.getValue().getColor();
	}

	private void getPaths(Tree<BagNode> node, String root) {
		for(Tree<BagNode> bagNode : bagTree.getChildren()) {
			if(bagNode.getValue().getColor().equals(root)) {
				walkTree(bagNode, root, 2);
				break;
			}
		}
	}

	public void solvePartOne(String bag) {
		System.out.println("Day 7");
		getPaths(bagTree, bag);
		System.out.println("Total: " + count);
	}

	public static void main(String[] args) {
		Day7Brian d7b = new Day7Brian();
		d7b.solvePartOne("shiny gold bag");
	}
}
