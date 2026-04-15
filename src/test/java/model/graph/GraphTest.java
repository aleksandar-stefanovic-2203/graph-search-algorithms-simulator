package model.graph;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import model.graph.exception.*;
import utilities.exception.*;

class GraphTest { // TODO: update test for new methods

    // -------------------- NODE TESTS --------------------

    @Test
    void addNodeValid() {
        Graph g = new Graph();
        g.addNode("A");

        assertTrue(g.getNeighbors("A").isEmpty());
    }

    @Test
    void addNodeDuplicate() {
        Graph g = new Graph();
        g.addNode("A");

        assertThrows(NodeFoundException.class, () -> g.addNode("A"));
    }

    @Test
    void addNodeInvalidInput() {
        Graph g = new Graph();

        assertThrows(NullArgumentException.class, () -> g.addNode(null));
        assertThrows(BlankArgumentException.class, () -> g.addNode(""));
        assertThrows(BlankArgumentException.class, () -> g.addNode("   "));
    }

    // -------------------- EDGE TESTS --------------------

    @Test
    void addEdgeValid() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");

        g.addEdge("A", "B", 5);

        List<Neighbor> neighbors = g.getNeighbors("A");

        assertEquals(1, neighbors.size());
        assertEquals("B", neighbors.get(0).getDestination());
        assertEquals(5, neighbors.get(0).getWeight());
    }

    @Test
    void addEdgeInvalidWeight() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");

        assertThrows(InvalidWeightException.class,
                () -> g.addEdge("A", "B", 0));
    }

    @Test
    void addEdgeNonExistingNodes() {
        Graph g = new Graph();
        g.addNode("A");

        assertThrows(NodeNotFoundException.class,
                () -> g.addEdge("A", "B", 5));
    }

    @Test
    void addEdgeDuplicate() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");

        g.addEdge("A", "B", 5);

        assertThrows(EdgeFoundException.class,
                () -> g.addEdge("A", "B", 10));
    }

    @Test
    void selfLoopEdge() {
        Graph g = new Graph();
        g.addNode("A");

        g.addEdge("A", "A", 3);

        assertEquals(1, g.getNeighbors("A").size());
        assertEquals("A", g.getNeighbors("A").get(0).getDestination());
    }

    // -------------------- REMOVE EDGE --------------------

    @Test
    void removeEdgeValid() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");

        g.addEdge("A", "B", 5);
        g.removeEdge("A", "B");

        assertTrue(g.getNeighbors("A").isEmpty());
    }

    @Test
    void removeEdgeNonExisting() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");

        assertThrows(EdgeNotFoundException.class,
                () -> g.removeEdge("A", "B"));
    }

    // -------------------- REMOVE NODE --------------------

    @Test
    void removeNodeValid() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");

        g.removeNode("A");

        assertThrows(NodeNotFoundException.class,
                () -> g.getNeighbors("A"));
    }

    @Test
    void removeNodeAlsoRemovesIncomingEdges() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");

        g.addEdge("A", "B", 5);
        g.removeNode("B");

        assertTrue(g.getNeighbors("A").isEmpty());
    }

    @Test
    void removeNodeNonExisting() {
        Graph g = new Graph();

        assertThrows(NodeNotFoundException.class,
                () -> g.removeNode("A"));
    }

    // -------------------- UPDATE EDGE --------------------

    @Test
    void updateEdgeWeightValid() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");

        g.addEdge("A", "B", 5);
        g.updateEdgeWeight("A", "B", 10);

        assertEquals(10, g.getNeighbors("A").get(0).getWeight());
    }

    @Test
    void updateEdgeWeightInvalidWeight() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");

        g.addEdge("A", "B", 5);

        assertThrows(InvalidWeightException.class,
                () -> g.updateEdgeWeight("A", "B", 0));
    }

    @Test
    void updateEdgeNonExisting() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");

        assertThrows(EdgeNotFoundException.class,
                () -> g.updateEdgeWeight("A", "B", 5));
    }

    // -------------------- GET NEIGHBORS --------------------

    @Test
    void getNeighborsNonExistingNode() {
        Graph g = new Graph();

        assertThrows(NodeNotFoundException.class,
                () -> g.getNeighbors("A"));
    }

    @Test
    void getNeighborsReturnsUnmodifiableList() {
        Graph g = new Graph();
        g.addNode("A");
        g.addNode("B");

        g.addEdge("A", "B", 5);

        List<Neighbor> neighbors = g.getNeighbors("A");
        assertThrows(UnsupportedOperationException.class, () -> neighbors.clear());
    }
}