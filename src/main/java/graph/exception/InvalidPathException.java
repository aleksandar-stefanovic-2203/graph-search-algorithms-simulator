package graph.exception;

@SuppressWarnings("serial")
public class InvalidPathException extends RuntimeException {
	public InvalidPathException() {
		super("Path parameter must contain at least one node.");
	}
}
