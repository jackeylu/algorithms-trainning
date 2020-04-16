public class ListStack<T> {
    private Node<T> top = null;

    public void push(T value){
        if (null == top) {
            top = new Node(value, null);
        } else {
            Node newNode = new Node(value, null);
            newNode.next = top;
            top = newNode;
        }
    }

    public T pop() {
        if (null == top) {
            return null;
        }
        T value = (T) top.value;
        top = top.next;
        return value;
    }



    private static class Node<T> {
        private T value;
        private Node next;

        Node(T value, Node next){
            this.value = value;
            this.next = next;
        }
    }
}
