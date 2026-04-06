package io;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.graph.Graph;

public class JSONGraphIO extends GraphIO {	
	
	public static JSONGraphIO getInstance() {
		return instance;
	}
	
	@Override
	protected Graph parse(String content) throws IOException {
		return mapper.readValue(content, Graph.class);
	}
	
	@Override
	protected String stringify(Graph graph) throws IOException {
		return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(graph);
	}

	private final ObjectMapper mapper = new ObjectMapper();
	
	private static final JSONGraphIO instance = new JSONGraphIO();
	private JSONGraphIO() {}
	
	
	
}
