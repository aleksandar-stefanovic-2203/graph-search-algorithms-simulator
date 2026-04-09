package io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import model.graph.Graph;

public abstract class GraphIO {
	
	public final Graph load(String filePath) throws IOException {
		String content = Files.readString(Path.of(filePath));
		Graph graph = parse(content);
		
		return graph;
	}
	
	public final void save(String filePath, Graph graph, boolean force) throws IOException {
		String content = stringify(graph);
		
		if (force) {
			Files.writeString(Path.of(filePath), content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
		} else {
			Files.writeString(Path.of(filePath), content, StandardOpenOption.CREATE_NEW);
		}
	}
	
	protected abstract Graph parse(String content) throws IOException;
	protected abstract String stringify(Graph graph) throws IOException;
}
