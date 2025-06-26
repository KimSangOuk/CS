import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class MyLinkedListTest {
    MyLinkedList list;

    @BeforeEach
    void init() {
        list = new MyLinkedList();
    }

    @Test
    void addFirstAndAddLastBuildsCorrectOrder() {
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(3);
        assertTrue(list.find(1));
        assertTrue(list.find(2));
        assertTrue(list.find(3));
        assertFalse(list.find(99));
    }

    @Test
    void removeFirstAndRemoveLastReturnCorrectValues() {
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        assertEquals(10, list.removeFirst());
        assertEquals(30, list.removeLast());
        assertEquals(20, list.removeFirst());
        assertThrows(NoSuchElementException.class, list::removeFirst);
    }

    @Test
    void reverseChangesOrder() {
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.reverse();
        assertTrue(list.find(3));
        assertEquals(3, list.removeFirst());
        assertEquals(2, list.removeFirst());
        assertEquals(1, list.removeFirst());
    }

    @Test
    void mergeRawAppendsSecondList() {
        MyLinkedList a = new MyLinkedList();
        a.addLast(1); a.addLast(2);
        MyLinkedList b = new MyLinkedList();
        b.addLast(3); b.addLast(4);
        MyLinkedList merged = MyLinkedList.mergeRaw(a, b);
        assertEquals(1, merged.removeFirst());
        assertEquals(2, merged.removeFirst());
        assertEquals(3, merged.removeFirst());
        assertEquals(4, merged.removeFirst());
        assertThrows(NoSuchElementException.class, merged::removeFirst);
    }
}
