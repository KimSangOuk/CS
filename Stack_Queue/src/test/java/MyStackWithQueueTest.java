import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyStackWithQueueTest {
    MyStackWithQueue<String> s;

    @BeforeEach
    void setup() {
        s = new MyStackWithQueue<>();
    }

    @Test
    void pushPopNormal() {
        s.push("one");
        s.push("two");
        s.push("three");
        assertEquals(3, s.size());
        assertFalse(s.isEmpty());
        assertEquals("three", s.pop());
        assertEquals("two", s.pop());
        assertEquals("one", s.pop());
        assertTrue(s.isEmpty());
    }

    @Test
    void topDoesNotRemove() {
        s.push("A");
        assertEquals("A", s.top());
        assertEquals(1, s.size());
    }

    @Test
    void popUnderflowThrows() {
        assertThrows(IllegalStateException.class, () -> s.pop());
    }
}