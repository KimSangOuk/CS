import java.util.NoSuchElementException;

public class MyLinkedList {
    private MyNode head;

    public void addFirst(int val) {
        MyNode newNode = new MyNode(val);
        newNode.next = head;
        head = newNode;
    }

    public void addLast(int val) {
        MyNode newNode = new MyNode(val);
        if (head == null) {
            head = newNode;
            return;
        }
        MyNode cur = head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
    }

    public int removeFirst() {
        if (head == null) throw new NoSuchElementException();
        int val = head.val;
        head = head.next;
        return val;
    }

    public int removeLast() {
        if (head == null) throw new NoSuchElementException();

        if (head.next == null) {
            int val = head.val;
            head = null;
            return val;
        }

        MyNode cur = head;
        while (cur.next.next != null) {
            cur = cur.next;
        }

        int val = cur.next.val;
        cur.next = null;
        return val;
    }

    public boolean find(int val) {
        MyNode cur = head;
        while (cur != null) {
            if (cur.val == val) return true;
            cur = cur.next;
        }
        return false;
    }

    public void reverse(){
        MyNode prev = null;
        MyNode cur = head;

        while (cur != null) {
            MyNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        head = prev;
    }

    public static MyLinkedList mergeRaw(MyLinkedList first, MyLinkedList second) {
        if (first.head == null) return second;
        if (second.head == null) return first;

        MyNode cur = first.head;
        while (cur.next != null) {
            cur = cur.next;
        }
        cur.next = second.head;

        return first;
    }

    public void printList(){
        MyNode cur = head;
        while(cur!=null){
            System.out.print(cur.val + " -> ");
            cur = cur.next;
        }
        System.out.println("null");
    }
}
