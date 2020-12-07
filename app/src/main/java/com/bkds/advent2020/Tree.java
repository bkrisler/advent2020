package com.bkds.advent2020;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;

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

	public static <T> Optional<Tree<T>> search(T value, Tree<T> root) {
		Queue<Tree<T>> queue = new ArrayDeque<>();
		queue.add(root);
		while(!queue.isEmpty()) {
			Tree<T> currentNode = queue.remove();
			if(currentNode.children.isEmpty()) {
				return Optional.of(currentNode);
			} else {
			    queue.addAll(currentNode.getChildren());
			}
		}
		return Optional.empty();
	}
	
	
}
