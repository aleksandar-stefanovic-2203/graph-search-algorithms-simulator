package utilities;

import utilities.exception.BlankArgumentException;
import utilities.exception.NullArgumentException;

public final class ValidationUtils {
	public static void requireNonBlank(String... strings) {
		if(strings == null) throw new IllegalArgumentException("Argument array must not be null.");
		for(int i = 0; i < strings.length; i++) {
			if(strings[i] == null) throw new NullArgumentException(i);
			if(strings[i].trim().isEmpty()) throw new BlankArgumentException(i);
		}
	}
	
	public static void requireNonNull(Object... objects) {
		if(objects == null) throw new IllegalArgumentException("Argument array must not be null.");
		for(int i = 0; i < objects.length; i++) {
			if(objects[i] == null) throw new NullArgumentException(i);
		}
	}
	
	private ValidationUtils() {
	    throw new AssertionError("No instances");
	}
}
