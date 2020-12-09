package com.bkds.advent2020;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

import com.bkds.advent2020.day7.BagNode;

public class Tree<T> {

	private T value;
	private List<Tree<T>> children;
	
	public Tree(T value) {
		this.value = value;
		this.children = new ArrayList<>();
	}

	public Tree<T> addChild(T value) {
		Tree<T> newChild = new Tree<>(value);
		children.add(newChild);
		return newChild;
	}
	
	
	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
	}

	public List<Tree<T>> getChildren() {
		return children;
	}

	public void setChildren(List<Tree<T>> children) {
		this.children = children;
	}

	public void visit(Tree<T> root, List<String> bagColors, int spc) {
		BagNode bn = (BagNode) root.getValue();
		if(!bagColors.contains(bn.getColor())) {
			bagColors.add(bn.getColor());
		}
		for(int i=0; i < spc; i++) {
			System.out.print(" ");
		}
		System.out.println(bn.getColor());
		for(Tree<T> child : root.getChildren()) {
			visit(child, bagColors, spc+2);
		}
	}
	
	public static <T> void dumpTree(Tree<T> root, int spc) {
		BagNode bn = (BagNode) root.getValue();
		for(int i=0; i < spc; i++) {
			if(i % 2 == 0) {
				System.out.print("\u2506");				
			} else {
				System.out.print(" ");
			}
		}
		
		System.out.println("\u251C\u2500 " + bn.getColor());
		for(Tree<T> child : root.getChildren()) {
			dumpTree(child, (spc + 2));			
		}		
	}
	
	public static <T> Optional<Tree<T>> search(T value, Tree<T> root) {
		Queue<Tree<T>> queue = new ArrayDeque<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Tree<T> currentNode = queue.remove();
			if(currentNode.getValue().equals(value)) {
				return Optional.of(currentNode);
			} else {
			    queue.addAll(currentNode.getChildren());
			}
		}
		return Optional.empty();
	}
	
	
}
