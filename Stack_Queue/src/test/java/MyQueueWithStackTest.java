import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyQueueWithStackTest {
    MyQueueWithStack<String> q;

    @BeforeEach
    void setup() {
        q = new MyQueueWithStack<>();
    }

    @Test
    void enqueueDequeueNormal() {
        q.enqueue("a");
        q.enqueue("b");
        q.enqueue("c");
        assertEquals(3, q.size());
        assertFalse(q.isEmpty());
        assertEquals("a", q.peek());
        assertEquals("a", q.dequeue());
        assertEquals("b", q.dequeue());
        assertEquals("c", q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    void peekDoesNotRemove() {
        q.enqueue("x");
        assertEquals("x", q.peek());
        assertEquals(1, q.size());
    }

    @Test
    void dequeueUnderflowThrows() {
        assertThrows(IllegalStateException.class, () -> q.dequeue());
    }

    @Test
    void peekUnderflowThrows() {
        assertThrows(IllegalStateException.class, () -> q.peek());
    }
}
