package utilities;

import org.junit.jupiter.api.Test;
import utilities.exception.BlankArgumentException;
import utilities.exception.NullArgumentException;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ValidationUtilsTest {

    @Test
    void requireNonBlankValidStrings() {
        assertDoesNotThrow(() -> ValidationUtils.requireNonBlank("A", "B", "C"));
    }

    @Test
    void requireNonBlankNullArray() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> ValidationUtils.requireNonBlank((String[]) null)
        );

        assertEquals("Argument array must not be null.", ex.getMessage());
    }

    @Test
    void requireNonBlankNullStringThrowsAtCorrectPosition() {
        NullArgumentException ex = assertThrows(
                NullArgumentException.class,
                () -> ValidationUtils.requireNonBlank("A", null, "C")
        );

        assertEquals("Argument at position 1 cannot be null.", ex.getMessage());
    }

    @Test
    void requireNonBlankBlankStringThrowsAtCorrectPosition() {
        BlankArgumentException ex = assertThrows(
                BlankArgumentException.class,
                () -> ValidationUtils.requireNonBlank("A", "   ", "C")
        );

        assertEquals("String argument at position 1 cannot be blank.", ex.getMessage());
    }

    @Test
    void requireNonNullValidObjects() {
        assertDoesNotThrow(() -> ValidationUtils.requireNonNull("A", 1, new Object()));
    }

    @Test
    void requireNonNullNullArray() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> ValidationUtils.requireNonNull((Object[]) null)
        );

        assertEquals("Argument array must not be null.", ex.getMessage());
    }

    @Test
    void requireNonNullNullObjectThrowsAtCorrectPosition() {
        NullArgumentException ex = assertThrows(
                NullArgumentException.class,
                () -> ValidationUtils.requireNonNull("A", null, "C")
        );

        assertEquals("Argument at position 1 cannot be null.", ex.getMessage());
    }
}
