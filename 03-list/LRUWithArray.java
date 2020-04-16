import java.util.HashMap;
import java.util.Map;

/**
 * * 基于数组实现的LRU缓存
 *  * 1. 空间复杂度为O(n)
 *  * 2. 时间复杂度为O(n)
 *  * 3. 不支持null的缓存
 */
public class LRUWithArray<T> {
    private static final int DEFAULT_CAPACITY = (1<<3);
    private int capacity;
    private int count;
    private T[] value;
    private Map<T, Integer> holder;

    public LRUWithArray() {
        this(DEFAULT_CAPACITY);
    }

    public LRUWithArray(int capacity) {
        this.capacity = capacity;
        value = (T[]) new Object[capacity];
        count = 0;
        holder = new HashMap<>(capacity);
    }


}
