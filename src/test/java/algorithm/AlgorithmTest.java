package algorithm;

import java.util.List;

import model.graph.Graph;
import simulation.Step;

public class AlgorithmTest {
	public static void main(String[] args) {
		Graph graph = new Graph();
		graph.addNode("A");
		graph.addNode("B");
		graph.addEdge("A", "B", 5);
		
		Algorithm algorithm = new BFSAlgorithm();
		List<Step> steps = algorithm.execute(graph, "A", "B");
		
		System.out.println(steps);
	}
}
