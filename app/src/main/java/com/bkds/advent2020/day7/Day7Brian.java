package com.bkds.advent2020.day7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.bkds.advent2020.DayBase;
import com.bkds.advent2020.Tree;

public class Day7Brian extends DayBase {

	private Map<String, List<String>> bags = new HashMap<>();
	private Tree<BagNode> bagTree = new Tree<>(new BagNode("", 0));
	private int count = 0;
	private List<String> colors = new ArrayList<>();
	
	public Day7Brian() {
		readData("day7", "brian");
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
	
	private String walkTree(Tree<BagNode> node, String path, int space) {

		if(node.getChildren().size() == 0) {
			System.out.println(path);
			System.out.println("");
			System.out.println("--> " + node.getValue().getColor());
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

	private void walkTreeFlat(Tree<BagNode> node, String path, int space) {
		
		if(node.getChildren().size() == 0) {
			String[] cp = node.getValue().getColor().split(" ");			
			colors.add(cp[1].trim());
			System.out.println(path);
			count++;
		}
		
		for(Tree<BagNode> child : node.getChildren()) {
			String fwd = path + " > ";
			fwd += "[" + child.getValue().getColor() + "]";
			String[] cp = node.getValue().getColor().split(" ");			
			colors.add(cp[1].trim());
			walkTreeFlat(child, fwd, (space + 2));
		}
	}

		
	private void getPaths(Tree<BagNode> node, String root) {
		for(Tree<BagNode> bagNode : bagTree.getChildren()) {
			if(bagNode.getValue().getColor().equals(root)) {
//				walkTree(bagNode, root, 2);
				walkTreeFlat(bagNode, "[" + root + "]", 2);
				break;
			}
		}
	}

	public void solvePartOne(String bag) {
		getPaths(bagTree, bag);
		System.out.println("Total: " + count);
		if(colors.contains("gold")) {
			colors.remove("gold");
		}
		List<String> unique = new ArrayList<>(new HashSet<>(colors));

		System.out.println("All Colors: [" + colors.size() + "] " + colors);
		System.out.println("Unique Colors: [" + unique.size() + "] " + unique);
		
	}

	private List<String> containers = new ArrayList<>();
	
	public void all() {
		List<String> unique = new ArrayList<>(new HashSet<>(containers));
		System.out.println(unique);
		System.out.println("Size: " + unique.size());
	}
	
	private void canContain(String string) {
		for(String color : bags.keySet()) {
			if(bags.get(color).contains(string)) {
				if(!containers.contains(color)) {
					containers.add(color);
					canContain(color);
				}
			}
		}
	}

	public static void main(String[] args) {
		Day7Brian d7b = new Day7Brian();
		d7b.canContain("shiny gold bag");
		d7b.all();
//		d7b.solvePartOne("shiny gold bag");
	}

}
