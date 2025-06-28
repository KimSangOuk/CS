import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MyCircularQueueTest {
    MyCircularQueue<Integer> q;

    @BeforeEach
    void setup() {
        q = new MyCircularQueue<>();
    }

    @Test
    void enqueueDequeueNormal() {
        q.enqueue(1);
        q.enqueue(2);
        q.enqueue(3);
        assertEquals(3, q.size());
        assertEquals(1, q.peek());
        assertEquals(1, q.dequeue());
        assertEquals(2, q.dequeue());
        assertEquals(3, q.dequeue());
        assertTrue(q.isEmpty());
    }

    @Test
    void peekDoesNotRemove() {
        q.enqueue(10);
        assertEquals(10, q.peek());
        assertEquals(1, q.size());
    }

    @Test
    void dynamicResize() {
        int initialCap = 8;
        for (int i = 0; i < initialCap * 2; i++) {
            q.enqueue(i);
        }
        assertEquals(initialCap * 2, q.size());
        for (int i = 0; i < initialCap * 2; i++) {
            assertEquals(i, q.dequeue());
        }
        assertTrue(q.isEmpty());
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
