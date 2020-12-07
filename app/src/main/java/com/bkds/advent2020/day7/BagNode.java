package com.bkds.advent2020.day7;

import java.util.ArrayList;
import java.util.List;

public class BagNode {

	private String bagColor;
	private int count = 0;
	
//	private List<BagNode> innerBags = new ArrayList<>();
	
	public BagNode(String color, int count) {
		this.bagColor = color;
		this.count = count;
	}

	public String getColor() {
		return this.bagColor;
	}
	
	public int getCount() {
		return this.count;
	}

	@Override
	public String toString() {
		return "BagNode [bagColor=" + bagColor + ", count=" + count + "]";
	}
	
	
//	public List<BagNode> getInnerBags() {
//		return innerBags;
//	}
//	
//	public boolean hasInnerBag(String color) {
//		for(BagNode bn : this.innerBags) {
//			if(bn.getColor().equals(color)) {
//				return true;
//			}
//		}
//		return false;
//	}
//	
//	public void updateInner(BagNode node) {
//		if(this.hasInnerBag(node.getColor())) {
//			for(int i=0; i < this.innerBags.size(); i++) {
//				if(this.innerBags.get(i).getColor().equals(node.getColor())) {
//					BagNode tmp = this.innerBags.get(i);
//					tmp.addInnerBags(node.getInnerBags());
//					this.innerBags.set(i, tmp);
//				}
//			}
//		}
//	}
//	
//	private void addInnerBags(List<BagNode> innerBags2) {
//		for(BagNode n : innerBags2) {
//			this.innerBags.add(n);
//		}		
//	}
//
//	public void addInnerBag(BagNode bag) {
//		this.innerBags.add(bag);
//	}
//	
//	public void walk(int indent) {
//		for(int i=0; i < indent; i++) {
//			System.out.print(" ");
//		}
//		System.out.println("> " + bagColor);
//		for(BagNode parent : innerBags) {
//			parent.walk(indent+2);
//		}
//	}
//
//	public String path(String prev) {
//		String path = prev + ":" + this.getColor();
//		for(BagNode inner : innerBags) {
//			path = inner.path(path);
//		}
//		return path;
//	}
//	
//	@Override
//	public int hashCode() {
//		final int prime = 31;
//		int result = 1;
//		result = prime * result + ((bagColor == null) ? 0 : bagColor.hashCode());
//		result = prime * result + count;
//		result = prime * result + ((innerBags == null) ? 0 : innerBags.hashCode());
//		return result;
//	}
//
//	@Override
//	public boolean equals(Object obj) {
//		if (this == obj)
//			return true;
//		if (obj == null)
//			return false;
//		if (getClass() != obj.getClass())
//			return false;
//		BagNode other = (BagNode) obj;
//		if (bagColor == null) {
//			if (other.bagColor != null)
//				return false;
//		} else if (!bagColor.equals(other.bagColor))
//			return false;
//		if (count != other.count)
//			return false;
//		return true;
//	}
//
//	@Override
//	public String toString() {
//		return "BagNode [bagColor=" + bagColor + ", count=" + count + "]";
//	}
	
}
