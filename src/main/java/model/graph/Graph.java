package model.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.graph.exception.EdgeFoundException;
import model.graph.exception.EdgeNotFoundException;
import model.graph.exception.InvalidWeightException;
import model.graph.exception.NodeArgumentEmptyException;
import model.graph.exception.NodeArgumentNullException;
import model.graph.exception.NodeFoundException;
import model.graph.exception.NodeNotFoundException;

public class Graph {
	
	public Graph() {}
	
	public Map<String, List<Neighbor>> getAdjacencyList() {
		return adjacencyList;
	}

	public void setAdjacencyList(Map<String, List<Neighbor>> adjacencyList) {
		this.adjacencyList = adjacencyList;
	}

	public List<Neighbor> getNeighbors(String node) {
		checkNodeFields(true, node);
		
		return new ArrayList<>(adjacencyList.get(node));
	}

	public void addNode(String node) {
		checkNodeFields(false, node);
		
		adjacencyList.put(node, new ArrayList<>());
	}
	
	public void addEdge(String startNode, String endNode, int weight) {
		if(weight < 1) throw new InvalidWeightException();
		checkNodeFields(true, startNode, endNode);
		
		List<Neighbor> neighbors = adjacencyList.get(startNode);
		if(checkIfNeighborExists(neighbors, endNode) != null) throw new EdgeFoundException(startNode, endNode);
		neighbors.add(new Neighbor(endNode, weight));
	}
	
	public void removeNode(String node) {
		checkNodeFields(true, node);
		
		adjacencyList.remove(node);
		for(List<Neighbor> neighbors: adjacencyList.values()) {
			Neighbor neighbor = checkIfNeighborExists(neighbors, node);
			if(neighbor != null) neighbors.remove(neighbor);
		}
	}
	
	public void removeEdge(String startNode, String endNode) {
		checkNodeFields(true, startNode, endNode);
		
		List<Neighbor> neighbors = adjacencyList.get(startNode);
		Neighbor neighbor = checkIfNeighborExists(neighbors, endNode);
		if(neighbor == null) throw new EdgeNotFoundException(startNode, endNode);
		
		neighbors.remove(neighbor);
	}
	
	public void updateEdgeWeight(String startNode, String endNode, int weight) {
		if(weight < 1) throw new InvalidWeightException();
		checkNodeFields(true, startNode, endNode);
		
		List<Neighbor> neighbors = adjacencyList.get(startNode);
		Neighbor neighbor = checkIfNeighborExists(neighbors, endNode);
		if(neighbor == null) throw new EdgeNotFoundException(startNode, endNode);
		
		neighbor.setWeight(weight);
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
			if(nodes[i] == null) throw new NodeArgumentNullException(i);
			if(nodes[i].trim().isEmpty()) throw new NodeArgumentEmptyException(i);
			if(exists && !adjacencyList.containsKey(nodes[i])) throw new NodeNotFoundException(nodes[i]);
			if(!exists && adjacencyList.containsKey(nodes[i])) throw new NodeFoundException(nodes[i]);
		}
	}
	
	private Map<String, List<Neighbor>> adjacencyList = new HashMap<>();
	
}
