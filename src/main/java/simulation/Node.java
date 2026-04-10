package simulation;

import java.util.ArrayList;
import java.util.List;

import utilities.ValidationUtils;

public class Node { // TODO: add parent node
	
	public Node(String name, int seqNum, int value) {
		ValidationUtils.requireNonBlank(name);
		this.name = name;
		this.seqNum = seqNum;
		this.value = value;
		this.path = new ArrayList<Node>();
		path.add(this);
		this.children = new ArrayList<Node>();
	}
	
	public Node(String name, int seqNum) {
		this(name, seqNum, -1);
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
	
	public List<Node> getPath() {
		return path;
	}	
	
	public List<Node> getChildren() {
		return children;
	}	
	
	public void setSeqNum(int number) {
		this.seqNum = number;
	}
	
	protected void setPath(List<Node> path) {
		this.path = path;
	}
	
	public void addChild(Node node) {
		ValidationUtils.requireNonNull(node);
		
		this.children.add(node);
		
		List<Node> newPath = new ArrayList<Node>(this.path);
		newPath.add(node);
		node.setPath(newPath);
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
	
	private List<Node> path;
	private List<Node> children;
}
