package io;

import core.graph.Graph;
import core.graph.Neighbor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IOTest {

	private static void assertGraphEquals(Graph expected, Graph actual) {
		assertEquals(expected.getAdjacencyList().keySet(), actual.getAdjacencyList().keySet());
		for (String node : expected.getAdjacencyList().keySet()) {
			List<Neighbor> eSorted = sortedByDestination(expected.getNeighbors(node));
			List<Neighbor> aSorted = sortedByDestination(actual.getNeighbors(node));
			assertEquals(eSorted.size(), aSorted.size());
			for (int i = 0; i < eSorted.size(); i++) {
				assertEquals(eSorted.get(i).getDestination(), aSorted.get(i).getDestination());
				assertEquals(eSorted.get(i).getWeight(), aSorted.get(i).getWeight());
			}
		}
	}

	private static List<Neighbor> sortedByDestination(List<Neighbor> neighbors) {
		List<Neighbor> copy = new ArrayList<>(neighbors);
		copy.sort(Comparator.comparing(Neighbor::getDestination));
		return copy;
	}

	@Test
	void jsonGraphIOSingleton() {
		assertSame(JSONGraphIO.getInstance(), JSONGraphIO.getInstance());
	}

	@Test
	void jsonRoundTripEmptyGraph(@TempDir Path tempDir) throws IOException {
		Graph original = new Graph();
		Path file = tempDir.resolve("empty.json");

		JSONGraphIO.getInstance().save(file.toString(), original, true);
		Graph loaded = JSONGraphIO.getInstance().load(file.toString());

		assertGraphEquals(original, loaded);
	}

	@Test
	void jsonRoundTripWithNodesAndEdges(@TempDir Path tempDir) throws IOException {
		Graph original = new Graph();
		original.addNode("A");
		original.addNode("B");
		original.addNode("C");
		original.addEdge("A", "B", 5);
		original.addEdge("B", "C", 3);
		original.addEdge("A", "C", 10);

		Path file = tempDir.resolve("graph.json");
		JSONGraphIO io = JSONGraphIO.getInstance();
		io.save(file.toString(), original, true);
		Graph loaded = io.load(file.toString());

		assertGraphEquals(original, loaded);
	}

	@Test
	void saveWithoutForceFailsWhenFileExists(@TempDir Path tempDir) throws IOException {
		Graph g = new Graph();
		g.addNode("X");
		Path file = tempDir.resolve("exists.json");

		JSONGraphIO.getInstance().save(file.toString(), g, true);

		assertThrows(FileAlreadyExistsException.class,
				() -> JSONGraphIO.getInstance().save(file.toString(), g, false));
	}

	@Test
	void saveWithForceOverwritesExistingFile(@TempDir Path tempDir) throws IOException {
		Graph first = new Graph();
		first.addNode("A");
		Path file = tempDir.resolve("overwrite.json");

		JSONGraphIO io = JSONGraphIO.getInstance();
		io.save(file.toString(), first, true);

		Graph second = new Graph();
		second.addNode("B");
		second.addNode("C");
		second.addEdge("B", "C", 7);

		io.save(file.toString(), second, true);
		Graph loaded = io.load(file.toString());

		assertGraphEquals(second, loaded);
	}

	@Test
	void loadMissingFileThrows(@TempDir Path tempDir) {
		Path missing = tempDir.resolve("nope.json");

		assertThrows(NoSuchFileException.class,
				() -> JSONGraphIO.getInstance().load(missing.toString()));
	}

	@Test
	void loadInvalidJsonThrows(@TempDir Path tempDir) throws IOException {
		Path file = tempDir.resolve("bad.json");
		Files.writeString(file, "not json {");

		assertThrows(IOException.class, () -> JSONGraphIO.getInstance().load(file.toString()));
	}
}
