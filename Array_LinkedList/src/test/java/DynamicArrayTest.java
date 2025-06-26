import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DynamicArrayTest {

    DynamicArray<Integer> arr;

    @BeforeEach
    void setUp() {
        arr = new DynamicArray<>();
    }

    @Test
    void appendAndGetIncreaseSize(){
        arr.append(1);
        arr.append(2);
        arr.append(3);
        assertEquals(3, arr.size());
        assertEquals(1, arr.get(0));
        assertEquals(2, arr.get(1));
        assertEquals(3, arr.get(2));
    }

    @Test
    void automaticResizeOnAppend() {
        int initialCap = arr.capacity();
        for (int i = 0; i < initialCap + 1; i++) arr.append(i);
        assertTrue(arr.capacity() > initialCap);
        assertEquals(initialCap + 1, arr.size());
    }

    @Test
    void getAndSetWithinBounds() {
        arr.append(10);
        arr.append(20);
        arr.set(0, 99);
        assertEquals(99, arr.get(0));
        assertThrows(IndexOutOfBoundsException.class, () -> arr.get(2));
    }

    @Test
    void manualResizeBigger() {
        arr.append(1);
        arr.resize(100);
        assertEquals(1, arr.size());
        assertEquals(100, arr.capacity());
        assertEquals(1, arr.get(0));
    }

    @Test
    void manualResizeSmallerThrows() {
        arr.append(1);
        arr.append(2);
        assertThrows(IllegalArgumentException.class, () -> arr.resize(1));
    }
}
