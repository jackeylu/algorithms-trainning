/**
 * 判定输入的内容是否是回文，用单链表来实现
 */
public class PalindromWithBasicList<T> {
    private class SNode<T> {
        private T element;
        private SNode next;

        SNode(T element) {
            this.element = element;
            next = null;
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

    private SNode head;
    public PalindromWithBasicList(T[] data){
        head = new SNode();
        SNode node = head;
        for (int i = 0; i < data.length; i++) {
            node.setNext(new SNode(data[i]));
        }
    }

    public boolean isPalindrom() {
        SNode fast = head.getNext();
        SNode slow = head;

        while (fast.getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        SNode middle = slow;

        SNode reversedRightHead = new SNode();
        while (slow.getNext() != null){
            reversedRightHead.setNext(slow);
            slow = slow.getNext();
        }

        SNode left = head;
        SNode right = reversedRightHead;
        while (left.getNext() != null && right.getNext() != null){
            if (!left.getElement().equals(right.getElement())){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] data = {1,2,3,2,1};
        PalindromWithBasicList list = new PalindromWithBasicList(data);
        System.out.println(list.isPalindrom());

        data = new Integer[]{1, 2, 2, 1};
        list = new PalindromWithBasicList(data);
        System.out.println(list.isPalindrom());

        data = new Integer[]{1, 2, 3, 1};
        list = new PalindromWithBasicList(data);
        System.out.println(list.isPalindrom());
    }
}
