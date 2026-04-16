package algorithm;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import graph.Neighbor;
import simulation.Node;

public class BranchAndBoundAlgorithm extends Algorithm {

	Queue<Node> priorityQueue = new PriorityQueue<Node>(Comparator.comparing(Node::getValue).thenComparing(Node::getName));
	
	@Override
	protected void frontierPut(Node root) {
		priorityQueue.add(root);
	}

	@Override
	protected Node frontierGet() {		
		return priorityQueue.remove();
	}

	@Override
	protected boolean frontierEmpty() {
		return priorityQueue.isEmpty();
	}
	
	@Override
	protected int generateValue(Node currNode, Neighbor neighbor) {
		int previousValue = currNode.getValue() >= 0 ? currNode.getValue() : 0;
		return previousValue + neighbor.getWeight();
	}

}
