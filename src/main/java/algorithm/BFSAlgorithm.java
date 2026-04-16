package algorithm;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;

import graph.Neighbor;
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
	
	@Override
	protected List<Neighbor> sortNeighbors(List<Neighbor> neighbors) {
		List<Neighbor> sortedNeighbors = new ArrayList<Neighbor>(neighbors);
		sortedNeighbors.sort(Comparator.comparing(Neighbor::getDestination));
		return sortedNeighbors;
	}

}
