package core.graph.exception;

@SuppressWarnings("serial")
public class EdgeFoundException extends RuntimeException {
	public EdgeFoundException(String startNode, String endNode) {
		super(String.format("Edge (%s, %s) already exists in graph.", startNode, endNode));
	}
}
