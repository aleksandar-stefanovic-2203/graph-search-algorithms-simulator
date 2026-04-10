package utilities.exception;

@SuppressWarnings("serial")
public class NullArgumentException extends RuntimeException {
	public NullArgumentException(int position) {
		super(String.format("Argument at position %d cannot be null.", position));
	}
}
