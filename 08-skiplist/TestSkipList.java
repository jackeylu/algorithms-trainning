public class TestSkipList {
    public static void main(String[] args) {
        SkipList list = new SkipList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);
        list.insert(5);
        list.insert(6);
        list.insert(7);
        list.insert(8);
        list.insert(10);
        list.insert(12);
        list.printAll();
        System.out.println("value 8 is at " + list.find(8));
        System.out.println("value 1 is at " + list.find(1));
        System.out.println("value 12 is at " + list.find(12));
        System.out.println("value 13 is at " + list.find(13));
        System.out.println("value 9 is at " + list.find(9));



    }
}
