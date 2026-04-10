package utilities.exception;

@SuppressWarnings("serial")
public class BlankArgumentException extends RuntimeException {
	public BlankArgumentException(int position) {
		super(String.format("String argument at position %d cannot be blank.", position));
	}
}
