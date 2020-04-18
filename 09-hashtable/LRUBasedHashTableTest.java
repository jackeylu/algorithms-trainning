import org.junit.jupiter.api.Assertions;

class LRUBasedHashTableTest {

    @org.junit.jupiter.api.Test
    void lruCache() {
        LRUBasedHashTable<String, String> lru = new LRUBasedHashTable<>();
        lru.add("1");
        lru.add("2");
        lru.add("abc");
        lru.add("ab");
        String actual = lru.printAll();
        Assertions.assertEquals("ab,abc,2,1,",actual);
        lru.add("2");
        actual = lru.printAll();
        Assertions.assertEquals("2,ab,abc,1,", actual);

        lru.add("3");
        lru.add("4");
        lru.add("5");
        lru.add("6");
        lru.add("7");
        lru.add("8");
        lru.add("9");
        lru.add("10");
        actual = lru.printAll();
        // abc, and 1 were removed
        Assertions.assertEquals("10,9,8,7,6,5,4,3,2,ab,", actual);

        Assertions.assertNull(lru.get("1"));
        Assertions.assertNull(lru.get("abc"));
        actual = lru.printAll();
        Assertions.assertEquals("10,9,8,7,6,5,4,3,2,ab,", actual);

        Assertions.assertEquals("ab", lru.get("ab"));
        actual = lru.printAll();
        Assertions.assertEquals("ab,10,9,8,7,6,5,4,3,2,", actual);

        lru.remove("3");
        actual = lru.printAll();
        Assertions.assertEquals("ab,10,9,8,7,6,5,4,2,", actual);
    }
}