import java.util.LinkedList;

public class MyHashMap<K, V> {
    private static final int INITIAL_CAPACITY = 8;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private LinkedList<Node<K, V>>[] table;
    private int size;

    @SuppressWarnings("unchecked")
    public MyHashMap() {
        table = new LinkedList[INITIAL_CAPACITY];
    }

    public void put(K key, V value) {
        int index = getIndex(key);

        if (table[index] == null) {
            table[index] = new LinkedList<>();
        }

        for (Node<K, V> node : table[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        table[index].add(new Node<>(key, value));
        size++;

        if ((double) size / table.length > LOAD_FACTOR_THRESHOLD) {
            resize();
        }
    }

    public V get(K key) {
        int index = getIndex(key);

        if (table[index] != null) {
            for (Node<K, V> node : table[index]) {
                if (node.key.equals(key)) {
                    return node.value;
                }
            }
        }
        return null;
    }

    public V remove(K key) {
        int index = getIndex(key);

        if (table[index] != null) {
            for (Node<K, V> node : table[index]) {
                if (node.key.equals(key)) {
                    table[index].remove(node);
                    size--;
                    return node.value;
                }
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private int getIndex(K key) {
        // 01111111 11111111 11111111 11111111 값을 비트 & 연산을 통해 맨 왼쪽 비트를 0으로 바꿔 부호 삭제
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        LinkedList<Node<K, V>>[] oldTable = table;
        table = new LinkedList[oldTable.length * 2];
        size = 0;

        for (LinkedList<Node<K, V>> bucket : oldTable) {
            if (bucket != null) {
                for (Node<K, V> node : bucket) {
                    put(node.key, node.value);
                }
            }
        }
    }

    private static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
