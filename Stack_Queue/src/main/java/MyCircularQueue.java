public class MyCircularQueue<T> {
    private static final int INIT_CAP = 8;
    private Object[] data;
    private int front = 0;
    private int rear  = 0;
    private int size  = 0;

    public MyCircularQueue() {
        data = new Object[INIT_CAP];
    }

    public void enqueue(T value) {
        if (size == data.length) resize(data.length * 2);
        data[rear] = value;
        rear = (rear + 1) % data.length;
        size++;
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        T value = (T) data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        return value;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) throw new IllegalStateException("Queue is empty");
        return (T) data[front];
    }

    public boolean isEmpty() { return size == 0; }
    public int size()       { return size; }

    private void resize(int newCap) {
        Object[] newData = new Object[newCap];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data  = newData;
        front = 0;
        rear  = size;
    }
}
