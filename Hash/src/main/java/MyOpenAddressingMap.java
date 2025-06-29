public class MyOpenAddressingMap<K, V> {
    private static final int INIT_CAPACITY = 8;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private Entry<K, V>[] table;
    private int size;
    private int capacity;

    @SuppressWarnings("unchecked")
    public MyOpenAddressingMap() {
        capacity = INIT_CAPACITY;
        table = new Entry[capacity];
        size = 0;
    }

    public void put(K key, V value) {
        if ((double) size / capacity > LOAD_FACTOR_THRESHOLD) {
            resize();
        }

        int index = findSlot(key);
        if (table[index] == null || table[index].tombstone) {
            table[index] = new Entry<>(key, value, false);
            size++;
        } else {
            table[index].value = value;
        }
    }

    public V get(K key) {
        int index = probe(key);
        if (index == -1) return null;
        return table[index].value;
    }

    public V remove(K key) {
        int index = probe(key);
        if (index == -1) return null;

        V value = table[index].value;
        table[index].tombstone = true;
        size--;
        return value;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int findSlot(K key) {
        int index = hash(key);
        while (table[index] != null && (!table[index].tombstone && !table[index].key.equals(key))) {
            index = (index + 1) % capacity;
        }
        return index;
    }

    private int probe(K key) {
        int index = hash(key);
        int start = index;

        while (table[index] != null) {
            if (!table[index].tombstone && table[index].key.equals(key)) {
                return index;
            }
            index = (index + 1) % capacity;
            if (index == start) break;
        }
        return -1;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        Entry<K, V>[] oldTable = table;
        capacity *= 2;
        table = new Entry[capacity];
        size = 0;

        for (Entry<K, V> entry : oldTable) {
            if (entry != null && !entry.tombstone) {
                put(entry.key, entry.value);
            }
        }
    }

    private static class Entry<K, V> {
        K key;
        V value;
        // 삭제 여부를 표시 안해두면 탐색 시, 다음 키를 찾지 못하는 중단 문제가 발생할 수 있음.
        boolean tombstone;

        Entry(K key, V value, boolean tombstone) {
            this.key = key;
            this.value = value;
            this.tombstone = tombstone;
        }
    }
}
