package algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import model.graph.Neighbor;
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
	
	@Override
	protected List<Neighbor> sortNeighbors(List<Neighbor> neighbors) {
		List<Neighbor> sortedNeighbors = new ArrayList<Neighbor>(neighbors);
		sortedNeighbors.sort(Comparator.comparing(Neighbor::getDestination).reversed());
		return sortedNeighbors;
	}

}
