package core.graph.exception;

@SuppressWarnings("serial")
public class NodeNotFoundException extends RuntimeException {
	public NodeNotFoundException(String node) {
		super(String.format("Node '%s' doesn't exist in graph.", node));
	}
}
