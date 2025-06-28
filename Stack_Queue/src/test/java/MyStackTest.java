import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyStackTest {
    MyStack<Integer> s;

    @BeforeEach
    void setup() {
        s = new MyStack<>();
    }

    @Test
    void pushPopNormal() {
        s.push(1);
        s.push(2);
        s.push(3);
        assertEquals(3, s.size());
        assertFalse(s.isEmpty());
        assertEquals(3, s.peek());
        assertEquals(3, s.pop());
        assertEquals(2, s.pop());
        assertEquals(1, s.pop());
        assertTrue(s.isEmpty());
    }

    @Test
    void peekDoesNotRemove() {
        s.push(5);
        assertEquals(5, s.peek());
        assertEquals(1, s.size());
    }

    @Test
    void popUnderflowThrows() {
        assertThrows(IllegalStateException.class, () -> s.pop());
    }

    @Test
    void peekUnderflowThrows() {
        assertThrows(IllegalStateException.class, () -> s.peek());
    }
}