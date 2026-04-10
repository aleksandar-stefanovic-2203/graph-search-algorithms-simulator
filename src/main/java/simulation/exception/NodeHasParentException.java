package simulation.exception;

@SuppressWarnings("serial")
public class NodeHasParentException extends RuntimeException {
	public NodeHasParentException(String node) {
		super(String.format("Node '%s' already has a parent.", node));
	}
}
