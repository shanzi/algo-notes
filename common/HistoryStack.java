import java.util.*;

class StackNode<T> {
    T val;
    StackNode<T> next;

    StackNode(T val) {
        this.val = val;
    }
}

public class HistoryStack <T>{
    StackNode<T> head = null;
    ArrayList<StackNode<T>> history = new ArrayList<StackNode<T>>();

    HistoryStack() {
        history.add(head);
    }

    public void push(T val) {
        StackNode<T> node = new StackNode<T>(val);
        node.next = head;
        head = node;
        history.add(head);
    }

    public T pop() {
        T ret = null;

        if (head != null) {
            ret = head.val;
            head = head.next;
            return ret;
        }

        history.add(head);
        return ret;
    }

    public List<T> query(int time) {
        if (time >= history.size()) return null;
        StackNode<T> p = history.get(time);
        LinkedList<T> status = new LinkedList<T>();
        while (p != null) {
            status.addLast(p.val);
            p = p.next;
        }
        return status;
    }
}
