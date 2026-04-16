package graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import graph.exception.EdgeFoundException;
import graph.exception.EdgeNotFoundException;
import graph.exception.InvalidPathException;
import graph.exception.InvalidWeightException;
import graph.exception.NodeFoundException;
import graph.exception.NodeNotFoundException;
import utilities.ValidationUtils;

public class Graph {
	
	public Graph() {}
	
	public Map<String, List<Neighbor>> getAdjacencyList() { // TODO: remove this (if possible because of Jackson library)
		return adjacencyList;
	}

	public void setAdjacencyList(Map<String, List<Neighbor>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public List<Neighbor> getNeighbors(String node) {
		ValidationUtils.requireNonBlank(node);
		checkNodeFields(true, node);
		
		return Collections.unmodifiableList(adjacencyList.get(node));
	}
	
	public int getPathPrice(List<String> nodes) {
		ValidationUtils.requireNonNull(nodes);
		if(nodes.size() == 0) throw new InvalidPathException();
		ValidationUtils.requireNonBlank(nodes.toArray(String[]::new));
		
		String prev = nodes.get(0);
		String next = null;
		int price = 0;
		for(int i = 1; i < nodes.size(); i++) {
			next = nodes.get(i);
			price += getEdgeWeight(prev, next);
			
			prev = next;
		}
		
		return price;
	}
	
	public int getEdgeWeight(String startNode, String endNode) {
		ValidationUtils.requireNonBlank(startNode, endNode);
		checkNodeFields(true, startNode, endNode);
		
		List<Neighbor> neighbors = adjacencyList.get(startNode);
		Neighbor neighbor = checkIfNeighborExists(neighbors, endNode);
		
		return neighbor.getWeight();
	}

	public void addNode(String node) {
		ValidationUtils.requireNonBlank(node);
		checkNodeFields(false, node);
		
		adjacencyList.put(node, new ArrayList<>());
	}
	
	public void addEdge(String startNode, String endNode, int weight) {
		if(weight < 1) throw new InvalidWeightException();
		ValidationUtils.requireNonBlank(startNode, endNode);
		checkNodeFields(true, startNode, endNode);
		
		List<Neighbor> neighbors = adjacencyList.get(startNode);
		if(checkIfNeighborExists(neighbors, endNode) != null) throw new EdgeFoundException(startNode, endNode);
		neighbors.add(new Neighbor(endNode, weight));
	}
	
	public void removeNode(String node) {
		ValidationUtils.requireNonBlank(node);
		checkNodeFields(true, node);
		
		adjacencyList.remove(node);
		for(List<Neighbor> neighbors: adjacencyList.values()) {
			Neighbor neighbor = checkIfNeighborExists(neighbors, node);
			if(neighbor != null) neighbors.remove(neighbor);
		}
	}
	
	public void removeEdge(String startNode, String endNode) {
		ValidationUtils.requireNonBlank(startNode, endNode);
		checkNodeFields(true, startNode, endNode);
		
		List<Neighbor> neighbors = adjacencyList.get(startNode);
		Neighbor neighbor = checkIfNeighborExists(neighbors, endNode);
		if(neighbor == null) throw new EdgeNotFoundException(startNode, endNode);
		
		neighbors.remove(neighbor);
	}
	
	public void updateEdgeWeight(String startNode, String endNode, int weight) {
		if(weight < 1) throw new InvalidWeightException();
		ValidationUtils.requireNonBlank(startNode, endNode);
		checkNodeFields(true, startNode, endNode);
		
		List<Neighbor> neighbors = adjacencyList.get(startNode);
		Neighbor neighbor = checkIfNeighborExists(neighbors, endNode);
		if(neighbor == null) throw new EdgeNotFoundException(startNode, endNode);
		
		neighbor.setWeight(weight);
	}
	
	public boolean nodeExists(String node) {
		ValidationUtils.requireNonBlank(node);
		return adjacencyList.containsKey(node);
	}
	
	@Override
	public String toString() {
		return adjacencyList.toString();
	}
	
	protected Neighbor checkIfNeighborExists(List<Neighbor> neighbors, String name) {
		for(Neighbor neighbor: neighbors) if(name.equals(neighbor.getDestination())) return neighbor;
		return null;
	}
	
	protected void checkNodeFields(boolean exists, String... nodes) {
		for(int i = 0; i < nodes.length; i++) {
			if(exists && !adjacencyList.containsKey(nodes[i])) throw new NodeNotFoundException(nodes[i]);
			if(!exists && adjacencyList.containsKey(nodes[i])) throw new NodeFoundException(nodes[i]);
		}
	}
	
	private Map<String, List<Neighbor>> adjacencyList = new HashMap<>();
	
}
