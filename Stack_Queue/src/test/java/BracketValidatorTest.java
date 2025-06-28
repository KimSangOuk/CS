import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class BracketValidatorTest {

    @Test
    void validSimplePairs() {
        assertTrue(BracketValidator.isValid("()"));
        assertTrue(BracketValidator.isValid("[]"));
        assertTrue(BracketValidator.isValid("{}"));
    }

    @Test
    void validMixedAndNested() {
        assertTrue(BracketValidator.isValid("()[]{}"));
        assertTrue(BracketValidator.isValid("{[()()]}"));
        assertTrue(BracketValidator.isValid("({[]})"));
    }

    @Test
    void invalidMismatched() {
        assertFalse(BracketValidator.isValid("(]"));
        assertFalse(BracketValidator.isValid("([)]"));
        assertFalse(BracketValidator.isValid("{[(])}"));
    }

    @Test
    void invalidUnbalanced() {
        assertFalse(BracketValidator.isValid("("));
        assertFalse(BracketValidator.isValid("]"));
    }

    @Test
    void emptyStringIsValid() {
        assertTrue(BracketValidator.isValid(""));
    }
}
