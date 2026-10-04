public interface Heap {
    void insert(int key);
    int extractMin();
    void decreaseKey(int oldKey,int newKey);
    int size();
    boolean isEmpty();
}
