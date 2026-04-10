package algorithm;

import java.util.ArrayDeque;
import java.util.Queue;

import simulation.Node;

public class BFSAlgorithm extends Algorithm {

	private Queue<Node> queue = new ArrayDeque<Node>();
	
	@Override
	protected void frontierPut(Node root) {
		queue.add(root);
	}

	@Override
	protected Node frontierGet() {
		return queue.remove();
	}

	@Override
	protected boolean frontierEmpty() {
		return queue.isEmpty();
	}

}
