# LRU Cache

Two ways to implement LRU Cache, one way is implementing our own double linked list,
the other is to make use of `TreeMap`.


Implementing double linked list:

```java
class DoubleLinkedListNode {
    int key;
    int value;
    DoubleLinkedListNode prev;
    DoubleLinkedListNode next;
    
    DoubleLinkedListNode(int k, int v) {
        key = k;
        value = v;
    }
}

public class LRUCache {
    int capacity;
    HashMap<Integer, DoubleLinkedListNode> map;
    
    int size = 0;
    DoubleLinkedListNode head = new DoubleLinkedListNode(0, -1);
    DoubleLinkedListNode tail = new DoubleLinkedListNode(0, -1);
    
    private void removeListNode(DoubleLinkedListNode node) {
        DoubleLinkedListNode prev = node.prev;
        DoubleLinkedListNode next = node.next;
        
        prev.next = next;
        next.prev = prev;
        
        node.next = null;
        node.prev = null;
        
        size--;
    }
    
    private void addListNode(DoubleLinkedListNode node) {
        while (size >= capacity) {
            pollListNode();
        }
        node.next = tail;
        node.prev = tail.prev;
        tail.prev.next = node;
        tail.prev = node;
        size++;
    }
    
    private void pollListNode() {
        if (size <= 0) return;
        DoubleLinkedListNode firstNode = head.next;
        removeListNode(firstNode);
        map.remove(firstNode.key);
    }
    
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
        map = new HashMap<Integer, DoubleLinkedListNode>(capacity);
    }
    
    public int get(int key) {
        if (map.containsKey(key)) {
            DoubleLinkedListNode valNode = map.get(key);
            removeListNode(valNode);
            addListNode(valNode);
            return valNode.value;
        }
        return -1;
    }
    
    public void set(int key, int value) {
        if (get(key) < 0) {
            DoubleLinkedListNode node = new DoubleLinkedListNode(key, value);
            addListNode(node);
            map.put(key, node);
        } else {
            map.get(key).value = value;
        }
    }
}
```

Using `TreeMap`
```java
class Entry {
    int key;
    int value;
    
    Entry(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

public class LRUCache {
    HashMap<Integer, Long> keyTimeMap;
    TreeMap<Long, Entry> timeValueMap;
    long time = 0;
    int capacity;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        keyTimeMap = new HashMap<Integer, Long>(capacity);
        timeValueMap = new TreeMap<Long, Entry>();
    }
    
    public int get(int key) {
        if (keyTimeMap.containsKey(key)) {
            Entry entry = timeValueMap.remove(keyTimeMap.get(key));
            keyTimeMap.put(key, ++time);
            timeValueMap.put(time, entry);
            return entry.value;
        }
        return -1;
    }
    
    private void removeLeastRecentlyUsed() {
        while (timeValueMap.size() > capacity) {
            Map.Entry<Long, Entry> first = timeValueMap.pollFirstEntry();
            Entry entry = first.getValue();
            keyTimeMap.remove(entry.key);
        }
    }
    
    public void set(int key, int value) {
        
        if (keyTimeMap.containsKey(key)) {
            timeValueMap.remove(keyTimeMap.remove(key));
        }
        
        keyTimeMap.put(key, ++time);
        timeValueMap.put(time, new Entry(key, value));
        
        removeLeastRecentlyUsed();
    }
}
```
