public class HashTable<K, V> {
    private static final int DEFAULT_INITIAL_CAPACITY = 8;
    private static final float LOAD_FACTOR = 0.75f;

    private Entry<K, V>[] table;
    private int elemNum = 0;
    private int indexNum = 0;

    public HashTable() {
        table = new Entry[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * @return number of different keys
     */
    public int size() {
        return elemNum;
    }


    static final class Entry<K, V> {
        K key;
        V value;

        Entry<K, V> next;

        Entry(K key, V value, Entry next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public void put(K key, V value){
        int index = hash(key);
        // 位置未被引用，创建哨兵节点
        if (table[index] == null) {
            table[index] = new Entry<>(null, null, null);
        }

        Entry tmp = table[index];
        if (tmp.next == null) {
            tmp.next = new Entry(key, value, null);
            elemNum++;
            indexNum++;
            // 动态扩容
            if (indexNum >= table.length * LOAD_FACTOR){
                resize();
            }
        } else {
            // 解决散列表冲突，这里使用链表法
            do {
                tmp = tmp.next;
                // key 相同，则覆盖旧的数据
                if (tmp.key == key){
                    tmp.value = value;
                    return;
                }
            } while (tmp.next != null);
            // 到达这里，代表是一个冲突的新元素，tmp指向当前链表的末尾
            //-----
            // temp指向该链表的第一个元素
            Entry temp = table[index].next;
            // 将该行链表头部插入新加入的元素
            table[index].next = new Entry<>(key, value, temp);
            elemNum++;
        }
    }

    public void remove(K key){
        int index = hash(key);
        Entry e = table[index];
        if (e == null || e.next == null){
            return;
        }

        Entry head = table[index];
        Entry pre;
        do {
            pre = e;
            e = e.next;
            if (key == e.key){
                pre.next = e.next;
                elemNum--;
                if (head.next == null){
                    indexNum--;
                }
                return;
            }
        } while (e.next != null);
    }

    public V get(K key){
        int index = hash(key);
        Entry<K, V> e = table[index];
        if (e == null || e.next == null){
            return null;
        }
        while (e.next != null){
            e = e.next;
            if (key == e.key){
                return e.value;
            }
        }
        return null;
    }

    private void resize() {
        Entry<K, V>[] oldTable = table;
        table = (Entry<K, V>[]) new Entry[table.length * 2];
        indexNum = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if (oldTable[i] == null || oldTable[i].next == null){
                continue;
            }
            Entry<K, V> node = oldTable[i];
            while (node.next != null){
                node = node.next;
                // 计算原来链表中每一个K在扩容后的数组中的位置
                int newIndex = hash(node.key);
                if (table[newIndex] == null){
                    indexNum++;
                    // 创建哨兵节点
                    table[newIndex] = new Entry<>(null, null, null);
                }
                // 将值拷贝到新的位置中
                table[newIndex].next = new Entry<>(node.key, node.value, table[newIndex].next);
            }
        }
    }

    private int hash(K key) {
        int h;
        return (key == null) ? 0: ((h=key.hashCode()) ^ (h >>> 16)) % table.length;
    }
}
