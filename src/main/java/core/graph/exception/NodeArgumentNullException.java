package core.graph.exception;

@SuppressWarnings("serial")
public class NodeArgumentNullException extends RuntimeException {
	public NodeArgumentNullException(int position) {
		super(String.format("Node argument at position %d cannot be null.", position));
	}
}
