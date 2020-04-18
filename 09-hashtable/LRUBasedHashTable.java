import java.io.PrintStream;
import java.util.HashMap;

/**
 * 基于散列表的LRU算法
 */
public class LRUBasedHashTable<K, V> implements LRUAlgorithm<V> {
    @Override
    public void add(V object) {
        K key = (K) object;
        add(key, object);
    }

    @Override
    public V get(V object) {
        K key = (K) object;
        return _get(key);
    }

    @Override
    public void remove(V object) {
        K key = (K) object;
        _remove(key);
    }

    @Override
    public String printAll() {
        Node<K, V> node = head.next;
        StringBuilder sb = new StringBuilder();
        while (node.next != null){
            sb.append(node.value + ",");
            node = node.next;
        }
        return sb.toString();
    }

    ///////////////////////////////
    private final static int DEFAULT_CAPACITY = 10;
    private Node<K, V> head;
    private Node<K, V> tail;
    // length of the double linked list
    private int lengthOfList;
    // the capacity of hashtable
    private int capacity;
    private HashMap<K, Node<K, V> > table;

    public LRUBasedHashTable(int capacity){
        this.lengthOfList = 0;
        this.capacity = capacity;
        head = new Node<>();
        tail = new Node<>();
        head.next = tail;
        tail.pre = head;
        table = new HashMap<>(capacity);
    }

    public LRUBasedHashTable(){
        this(DEFAULT_CAPACITY);
    }

    private void add(K key, V value) {
        Node<K, V> node = table.get(key);
        if (node == null) {
            Node<K,V> newNode = new Node(key, value);
            table.put(key, newNode);
            addNodeAtBegin(newNode);

            if (++lengthOfList > capacity) {
                Node<K, V> tail = popTail();
                table.remove(tail.key);
                lengthOfList--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * add the new node to the head position
     * @param node the new node
     */
    private void addNodeAtBegin(Node<K, V> node){
        node.next = head.next;
        node.pre = head;

        head.next.pre = node;
        head.next = node;
    }

    private Node<K, V> popTail() {
        Node<K, V> node = tail.pre;
        removeNode(node);
        return node;
    }

    private void removeNode(Node<K, V> node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    /**
     * move given node to the head position
     * @param node node to be moved
     */
    private void moveToHead(Node<K, V> node){
        removeNode(node);
        addNodeAtBegin(node);
    }

    private V _get(K key) {
        Node<K, V> node = table.get(key);
        if (node == null){
            return null;
        }
        moveToHead(node);

        return node.value;
    }

    private void _remove(K key) {
        Node<K, V> node = table.get(key);
        if (node == null) {
            return;
        }
        removeNode(node);
        lengthOfList--;
        table.remove(node.key);
    }

    static final class Node<K, V>{
        private K key;
        private V value;
        private Node<K, V> pre;
        private Node<K, V> next;

        Node(K key, V value){
            this.key = key;
            this.value = value;
        }

        Node() {
        }
    }
}
