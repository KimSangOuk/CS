import java.util.LinkedList;
import java.util.Queue;

public class MyStackWithQueue<T> {
    private Queue<T> q1 = new LinkedList<>();
    private Queue<T> q2 = new LinkedList<>();

    public void push(T x) {
        q2.offer(x);
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }
        // swap q1 <-> q2
        Queue<T> temp = q1;
        q1 = q2;
        q2 = temp;
    }

    public T pop() {
        if (q1.isEmpty()) throw new IllegalStateException("Stack is empty");
        return q1.poll();
    }

    public T top() {
        if (q1.isEmpty()) throw new IllegalStateException("Stack is empty");
        return q1.peek();
    }

    public boolean isEmpty() {
        return q1.isEmpty();
    }

    public int size() {
        return q1.size();
    }
}
