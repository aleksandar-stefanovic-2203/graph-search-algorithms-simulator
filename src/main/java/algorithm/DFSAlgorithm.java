package algorithm;

import java.util.Stack;

import simulation.Node;

public class DFSAlgorithm extends Algorithm {

	private Stack<Node> stack = new Stack<Node>();
	
	@Override
	protected void frontierPut(Node root) {
		stack.push(root);
	}

	@Override
	protected Node frontierGet() {
		return stack.pop();
	}

	@Override
	protected boolean frontierEmpty() {
		return stack.isEmpty();
	}

}
