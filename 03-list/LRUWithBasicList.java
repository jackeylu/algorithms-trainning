import java.util.Scanner;

/**
 * 基于单向链表实现LRU算法
 */
public class LRUWithBasicList<T> {
    private final static int DEFAULT_CAPACITY = 10;
    private SNode<T> head;
    private int length;
    private int capacity;

    public LRUWithBasicList() {
        head = new SNode();
        capacity = DEFAULT_CAPACITY;
        length = 0;
    }

    public void add(T e) {
        SNode preNode = findPreNode(e);
        if (null != preNode){
            deleteNextNode(preNode);
            insertElementAtBegin(e);
        } else {
            if (length >= capacity) {
                deleteElemAtEnd();
            }
            insertElementAtBegin(e);
        }
    }

    private void deleteElemAtEnd() {
        SNode node = head;
        if (node.getNext() == null){
            return;
        }

        while (node.getNext().getNext() != null) {
            node = node.getNext();
        }
        node.setNext(null);
        length --;
    }

    private SNode findPreNode(T e) {
        SNode node = head;
        while (node.getNext() != null){
            if (e.equals(node.getNext().getElement())){
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    private void insertElementAtBegin(T e) {
        SNode next = head.getNext();
        head.setNext(new SNode(e, next));
        length++;
    }

    private void deleteNextNode(SNode node) {
        SNode next = node.getNext();
        node.setNext(next.getNext());
        next = null;
        length--;
    }


    private class SNode<T> {
        private T element;
        private SNode next;

        SNode(T element) {
            this.element = element;
        }

        SNode(T e, SNode next){
            element = e;
            this.next = next;
        }

        SNode(){
            element = null;
            next = null;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public SNode getNext() {
            return next;
        }

        public void setNext(SNode next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUWithBasicList list = new LRUWithBasicList();
        Scanner sc = new Scanner(System.in);
        while (true) {
            list.add(sc.nextInt());
            list.printAll();
        }
    }

    private void printAll() {
        SNode node = head.getNext();
        while (node != null) {
            System.out.println(node.getElement() + ", ");
            node = node.getNext();
        }
        System.out.println();
    }
}
