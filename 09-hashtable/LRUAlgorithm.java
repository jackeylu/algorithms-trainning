public interface LRUAlgorithm<T> {
    void add(T object);
    T get(T object);
    void remove(T object);
    String printAll();
}
