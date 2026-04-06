package core.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	public void addEdge(String start, String end, int weight) {
		if(weight < 1) throw new IllegalArgumentException("Weight argument must be a whole number greater than or equal to 1.");
		checkNodeFields(true, start, end);
		
		List<Neighbor> neighbors = adjacencyList.get(start);
		if(checkIfNeighborExists(neighbors, end) != null) throw new IllegalStateException(String.format("Edge (%s, %s) already exists in graph.", start, end));
		neighbors.add(new Neighbor(end, weight));
	}
	
	public void removeNode(String node) {
		checkNodeFields(true, node);
		
		adjacencyList.remove(node);
		for(List<Neighbor> neighbors: adjacencyList.values()) {
			Neighbor neighbor = checkIfNeighborExists(neighbors, node);
			if(neighbor != null) neighbors.remove(neighbor);
		}
	}
	
	public void removeEdge(String start, String end) {
		checkNodeFields(true, start, end);
		
		List<Neighbor> neighbors = adjacencyList.get(start);
		Neighbor neighbor = checkIfNeighborExists(neighbors, end);
		if(neighbor == null) throw new IllegalStateException(String.format("Edge (%s, %s) doesn't exist in graph.", start, end));
		
		neighbors.remove(neighbor);
	}
	
	public void updateEdgeWeight(String start, String end, int weight) {
		if(weight < 1) throw new IllegalArgumentException("Weight argument must be a whole number greater than or equal to 1.");
		checkNodeFields(true, start, end);
		
		List<Neighbor> neighbors = adjacencyList.get(start);
		Neighbor neighbor = checkIfNeighborExists(neighbors, end);
		if(neighbor == null) throw new IllegalStateException(String.format("Edge (%s, %s) doesn't exist in graph.", start, end));
		
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
			if(nodes[i] == null) throw new IllegalArgumentException(String.format("Node argument at position %d cannot be null.", i));
			if(nodes[i].trim().isEmpty()) throw new IllegalArgumentException(String.format("Node argument at position %d cannot be an empty string.", i));
			if(exists && !adjacencyList.containsKey(nodes[i])) throw new IllegalArgumentException(String.format("Node %s doesn't exist in graph.", nodes[i]));
			if(!exists && adjacencyList.containsKey(nodes[i])) throw new IllegalArgumentException(String.format("Node %s already exists in graph.", nodes[i]));
		}
	}
	
	private Map<String, List<Neighbor>> adjacencyList = new HashMap<>();
	
}
