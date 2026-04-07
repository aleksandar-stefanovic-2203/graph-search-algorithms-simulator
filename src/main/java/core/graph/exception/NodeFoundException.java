package core.graph.exception;

@SuppressWarnings("serial")
public class NodeFoundException extends RuntimeException {
	public NodeFoundException(String node) {
		super(String.format("Node '%s' already exists in graph.", node));
	}
}
