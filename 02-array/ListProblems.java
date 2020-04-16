/**
 * 单链表反转
 * 链表中环的检测
 * 两个有序的链表合并
 * 删除链表倒数第 n 个结点
 * 求链表的中间结点
 */
public class ListProblems {

    public static Node reverseList(Node head) {
        Node current = head;
        Node prev = null;
        while (current != null){
            Node next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    /**
     * 单链表有环，代表着逻辑上的尾节点指向链表中的某一个节点，有环的链表实际上是没有尾节点的。
     * 设计两个slow、fast指针，slow指针每次走一步，fast指针每次走两步。
     * 无环时，走了n次，则fast = 2*n + 1, slow = n，fast - slow = n + 1
     * 如果有环，那么
     * @param head
     * @return
     */
    public static boolean detectCycle(Node head) {
        if (null == head) {
            return false;
        }

        Node fast = head.next;
        Node slow = head;

        while (fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;

            if (slow == fast) {
                return true;
            }
        }

        return false;
    }

    public static Node mergeTwoOrdLists(Node l1, Node l2) {
        Node soldier = new Node(-1, null);
        Node p = soldier;

        while (l1 != null && l2 != null){
            if (l1.data < l2.data) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }

        if (null != l1) {
            p.next = l1;
        }
        if (null != l2){
            p.next = l2;
        }

        return soldier.next;
    }

    public static Node removeLastKNode(Node head, int k){
        Node fast = head;
        int i = 1;
        while (fast != null && i < k) {
            fast = fast.next;
            i++;
        }

        if (null == fast) {
            return null;
        }

        Node slow = head;
        Node prev = null;
        while (fast.next != null){
            fast = fast.next;
            prev = slow;
            slow = slow.next;
        }

        prev.next = prev.next.next;
        System.out.println("rm " + prev);
        return prev;
    }

    public static Node findMiddleNode(Node head){
        if (null == head) return null;

        Node fast = head;
        Node slow = head;

        while (null != fast && null != fast.next) {
            fast = fast.next.next;
            slow = slow.next;
        }

        return slow;
    }

    public static void printList(Node head){
        Node p = head;
        while (p != null){
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
        int[] data = {1, 2, 3};
        Node head = buildList(data);
        Node mid = ListProblems.findMiddleNode(head);
        System.out.println(mid);
        printList(head);

        Node rm = ListProblems.removeLastKNode(head, 2);
        System.out.println(rm);
        printList(head);

        int[] d1 = {1, 4, 8};
        int[] d2 = {1, 2,3,7,9,10};
        Node sorted = ListProblems.mergeTwoOrdLists(buildList(d1), buildList(d2));
        printList(sorted);

        System.out.println(ListProblems.detectCycle(buildCycleList()));

        printList(ListProblems.reverseList(buildList(d2)));
    }

    private static class Node {
        private int data;
        private Node next;

        Node(int data, Node next){
            this.data = data;
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }


    private static Node buildList(int[] data) {
        if (null == data || data.length == 0)
            return null;
        Node head = new Node(data[0], null);
        Node p = head;
        for (int i = 1; i < data.length; i++) {
            p.next = new Node(data[i], null);
            p = p.next;
        }
        return head;
    }

    private static Node buildCycleList() {
        Node head = new Node(0,
                new Node(1, new Node(2,
                        new Node(3,
                                new Node(4, new Node(5, null))))));
        head.next.next.next.next = head.next.next;
        return head;
    }
}
