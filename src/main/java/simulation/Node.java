package simulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import simulation.exception.NodeHasParentException;
import utilities.ValidationUtils;

public class Node {
	
	public Node(String name, int seqNum, int value) {
		ValidationUtils.requireNonBlank(name);
		this.name = name;
		this.seqNum = seqNum;
		this.value = value;
		this.parent = null;
		this.children = new ArrayList<Node>();
	}
	
	public String getName() {
		return name;
	}

	public int getSeqNum() {
		return seqNum;
	}

	public int getValue() {
		return value;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public List<Node> getPath() {
		List<Node> path = new ArrayList<Node>();
		Node current = this;
		
		while(current != null) {
			path.add(current);
			current = current.parent;
		}
		
		Collections.reverse(path);
		
		return path;
	}
	
	public List<Node> getChildren() {
		return Collections.unmodifiableList(children);
	}
	
	public void setSeqNum(int number) {
		this.seqNum = number;
	}
	
	public void addChild(Node node) {
		ValidationUtils.requireNonNull(node);
		if(node.parent != null) throw new NodeHasParentException(node.getName());
		
		this.children.add(node);
		node.parent = this;
	}	

	public Node cloneTree() {
		Node copy = new Node(this.name, this.seqNum, this.value);
		
		for(Node child: children) {
			copy.addChild(child.cloneTree());
		}
		
		return copy;
	}

	private final String name;
	private int seqNum;
	private final int value;
	
	private Node parent;
	private List<Node> children;
}
