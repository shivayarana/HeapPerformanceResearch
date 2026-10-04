public class Main {
    public static void main(String[] args) {
        BinomialHeap h = new BinomialHeap();
        h.insert(12);
        h.insert(34);
        h.insert(11);
        h.insert(30);
        h.traverse();
        System.out.println(h.extractMin());
        System.out.println(h.extractMin());
        h.traverse();

    }
}
