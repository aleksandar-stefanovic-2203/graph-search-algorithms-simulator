package core.graph.exception;

@SuppressWarnings("serial")
public class InvalidWeightException extends RuntimeException {
	public InvalidWeightException() {
		super("Weight argument must be a whole number greater than or equal to 1.");
	}
}
