package core.graph.exception;

@SuppressWarnings("serial")
public class NodeArgumentEmptyException extends RuntimeException {
	public NodeArgumentEmptyException(int position) {
		super(String.format("Node argument at position %d cannot be an empty string.", position));
	}
}
