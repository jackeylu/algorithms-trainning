import java.util.Arrays;

public class ArrayStack {
    private String[] items;
    private int count;
    private int capacity;

    public ArrayStack(int capacity) {
        this.items = new String[capacity];
        this.count = 0;
        this.capacity = capacity;
    }

    public boolean push(String item) {
        if (count == capacity) {
            return false;
        }
        items[count] = item;
        count++;
        return true;
    }

    public String pop() {
        if (0 == count) {
            return null;
        }
        String temp = items[count - 1];
        count--;
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("stack = [");
        for (int i = 0; i < count; i++) {
            builder.append(items[i]);
            if (i != count - 1) {
                builder.append(", ");
            }
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(10);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        stack.push("d");
        stack.push("e");
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        stack.pop();
        stack.pop();
        System.out.println(stack);
    }
}
