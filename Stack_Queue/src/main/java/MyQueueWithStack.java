import java.util.Stack;

public class MyQueueWithStack<T> {
    private Stack<T> inStack = new Stack<>();
    private Stack<T> outStack = new Stack<>();

    public void enqueue(T value) {
        inStack.push(value);
    }

    public T dequeue() {
        moveIfNeeded();
        if (outStack.isEmpty()) throw new IllegalStateException("Queue is empty");
        return outStack.pop();
    }

    public T peek() {
        moveIfNeeded();
        if (outStack.isEmpty()) throw new IllegalStateException("Queue is empty");
        return outStack.peek();
    }

    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    public int size() {
        return inStack.size() + outStack.size();
    }

    private void moveIfNeeded() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}
