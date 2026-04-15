package algorithm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import model.graph.Graph;
import simulation.ExecutionTrace;
import simulation.Node;

public class AlgorithmTest {

    static Graph graph;

    @BeforeAll
    static void init() {
        graph = new Graph();

        graph.addNode("S");
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");
        graph.addNode("F");
        graph.addNode("G");

        addUndirectedEdge("S", "A", 2);
        addUndirectedEdge("S", "B", 4);

        addUndirectedEdge("A", "C", 4);
        addUndirectedEdge("A", "E", 1);

        addUndirectedEdge("B", "G", 11);

        addUndirectedEdge("C", "D", 1);
        addUndirectedEdge("C", "E", 1);
        addUndirectedEdge("C", "F", 3);

        addUndirectedEdge("F", "G", 2);

        addUndirectedEdge("E", "G", 10);
    }

    @Test
    void bfsFromSToG() {
        Algorithm bfs = new BFSAlgorithm();
        ExecutionTrace trace = bfs.execute(graph, "S", "G");

        assertFalse(trace.getSteps().isEmpty());
        assertNotNull(trace.getPath());
        List<String> nodes = new ArrayList<String>();
        for(Node node: trace.getPath()) {
        	nodes.add(node.getName());
        }
        assertEquals(List.of("S", "B", "G"), nodes);
        assertEquals(15, trace.getPrice());
    }

    @Test
    void dfsFromSToG() {
    	Algorithm dfs = new DFSAlgorithm();
        ExecutionTrace trace = dfs.execute(graph, "S", "G");

        assertFalse(trace.getSteps().isEmpty());
        assertNotNull(trace.getPath());
        List<String> nodes = new ArrayList<String>();
        for(Node node: trace.getPath()) {
        	nodes.add(node.getName());
        }
        assertEquals(List.of("S", "A", "C", "E", "G"), nodes);
        assertEquals(17, trace.getPrice());
    }

    private static void addUndirectedEdge(String from, String to, int weight) {
        graph.addEdge(from, to, weight);
        graph.addEdge(to, from, weight);
    }
}
