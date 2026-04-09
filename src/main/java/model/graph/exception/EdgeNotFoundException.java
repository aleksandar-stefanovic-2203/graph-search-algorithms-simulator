package model.graph.exception;

@SuppressWarnings("serial")
public class EdgeNotFoundException extends RuntimeException {
	public EdgeNotFoundException(String startNode, String endNode) {
		super(String.format("Edge (%s, %s) doesn't exist in graph.", startNode, endNode));
	}
}
