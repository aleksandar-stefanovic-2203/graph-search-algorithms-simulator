package algorithm;

import java.util.ArrayList;
import java.util.List;

import algorithm.exception.AlgorithmFinishedException;
import model.graph.Graph;
import model.graph.Neighbor;
import model.graph.exception.NodeNotFoundException;
import simulation.Node;
import simulation.Step;
import utilities.ValidationUtils;

public abstract class Algorithm {
	public final List<Step> execute(Graph graph, String startNode, String endNode){
		validateInputs(graph, startNode, endNode);
		
		List<Step> steps = new ArrayList<Step>();
		Node root = new Node(startNode, -1, -1);
		
		updateSteps(steps, root);
		
		frontierPut(root);
		
		int seqNum = 1;
		try {
			while(!frontierEmpty()) {
				Node currNode = frontierGet();
				currNode.setSeqNum(seqNum);
				
				checkCondition(currNode, endNode);
				
				List<Neighbor> neighbors = graph.getNeighbors(currNode.getName());
				List<Neighbor> sortedNeighbors = sortNeighbors(neighbors);
				
				for(Neighbor neighbor: sortedNeighbors) {
					if(onPath(currNode.getPath(), neighbor.getDestination())) continue;
					
					int value = generateValue(currNode, neighbor);
					Node newNode = new Node(neighbor.getDestination(), -1, value);
					currNode.addChild(newNode);
					frontierPut(newNode);
				}
				
				updateSteps(steps, root);
				seqNum++;
			}
		} catch (AlgorithmFinishedException e) {
			updateSteps(steps, root);
		}
		
		return steps;
	}	

	protected void checkCondition(Node currNode, String endNode) throws AlgorithmFinishedException {
		if(currNode.getName().equals(endNode)) throw new AlgorithmFinishedException();
	}
	
	protected List<Neighbor> sortNeighbors(List<Neighbor> neighbors){
		return neighbors;
	}

	protected int generateValue(Node currNode, Neighbor neighbor) {
		return -1;
	}

	protected abstract void frontierPut(Node root);
	
	protected abstract Node frontierGet();
	
	protected abstract boolean frontierEmpty();

	private void validateInputs(Graph graph, String startNode, String endNode) {
		ValidationUtils.requireNonNull(graph);
		if(!graph.nodeExists(startNode)) throw new NodeNotFoundException(startNode);
		if(!graph.nodeExists(endNode)) throw new NodeNotFoundException(endNode);
	}

	private void updateSteps(List<Step> steps, Node root) {
		Node treeCopy = root.cloneTree();
		Step step = new Step(treeCopy);
		steps.add(step);
	}
	
	private boolean onPath(List<Node> path, String node) {
		for(Node pathNode: path)
			if(pathNode.getName().equals(node)) return true;
		
		return false;
	}
}
