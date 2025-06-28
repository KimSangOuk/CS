public class MyStack<T> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] data;
    private int size;

    public MyStack() {
        data = new Object[INITIAL_CAPACITY];
        size = 0;
    }

    public void push(T value) {
        if (size == data.length) {
            resize(data.length * 2);
        }
        data[size++] = value;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        T value = (T) data[--size];
        data[size] = null;
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return (T) data[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resize(int newCapacity) {
        Object[] newData = new Object[newCapacity];
        System.arraycopy(data, 0, newData, 0, size);
        data = newData;
    }
}
