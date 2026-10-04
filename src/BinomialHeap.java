import java.util.Stack;

public class BinomialHeap implements Heap{
private static class Node{
    Node sibling;
    Node child;
    Node parent;
    int degree;
    int data;
    Node(int data){
        this.data = data;
    }
}
private Node head;
private int n;
public BinomialHeap(){
    head = null;
    n = 0;
}
private BinomialHeap createHeap(){
    return new BinomialHeap();
}
@Override public void insert(int key){
    if(head == null){
        head = new Node(key);
        n = 1;
    }else{
        BinomialHeap h = new BinomialHeap();
        h.head = new Node(key);
        h.n = 1;
        union(h);
    }
}

    @Override
    public int extractMin() {
        Stack<Node> st = new Stack<>();
        Node min = findMinKey();
        Node y = head;
        Node z = min.sibling;
        Node x = min.child;
        while(x != null){
            x.parent = null;
            st.push(x);
            x = x.sibling;
        }
        if(head == min){
            head = st.pop();
            y = head;
        }else{
            while(y.sibling != min){
                y = y.sibling;
            }
        }
        while(!st.isEmpty()){
            y.sibling = st.pop();
            y = y.sibling;
        }
        y.sibling = z;
        min.sibling = min.child = null;
        return min.data;
    }


    @Override
    public int size() {
        return 0;
    }

    private void union(BinomialHeap h){
    merge(h);
    Node ptr = this.head,next = ptr.sibling;
    while(next != null){
        if (ptr.degree != next.degree) {
            ptr = next;
        }else{
            ptr.sibling = next.sibling;
            link(ptr,next);

        }
        next = ptr.sibling;
    }
}

private void merge(BinomialHeap H){
    Node h1,h2,h;
    if((H.head.degree < this.head.degree) ||
            (H.head.degree == this.head.degree && this.head.data > H.head.data)){
        h1 = this.head;
        this.head = H.head;
        h2 = H.head.sibling;
    }else{
        h1 = this.head.sibling;
        h2 = H.head;
    }
    h = this.head;
    while(h1 != null && h2 != null){
        if((h1.degree < h2.degree) || (h1.degree == h2.degree && h1.data <h2.data)){
            h.sibling = h1;
            h1 = h1.sibling;
        }else{
            h.sibling = h2;
            h2 = h2.sibling;
        }
        h = h.sibling;
    }
    if(h1 == null){
        h.sibling = h2;
    }else{
        h.sibling = h1;
    }
    this.n += H.n;

}

private void link(Node x, Node y){
    y.parent = x;
    y.sibling = x.child;
    x.child = y;
    x.degree++;
//    head.degree--;
}

private void preorder(Node root){
    if(root != null){
        System.out.print(root.data+" ");
        preorder(root.child);
        preorder(root.sibling);
    }
}
public void traverse(){
    preorder(head);
    System.out.println();
}


// deleting process
    public Node findMinKey(){
    Node ptr,y;
    ptr = head.sibling;
    y = head;
    while(ptr != null){
        if(ptr.data < y.data){
            y = ptr;
        }
        ptr = ptr.sibling;
    }
    return y;
    }

//     find a node in heap
    public Node find(int key){
    Node ptr = head;
    while(ptr != null){
        if(ptr.data == key)
        {
            break;
        }

        if(ptr.child != null){
            ptr = ptr.child;
        }
        else if(ptr.sibling != null){
            ptr = ptr.sibling;
        }else if(ptr.parent != null){
            ptr = ptr.parent.sibling;
        }else{
            ptr = ptr.parent;
        }
    }
    return  ptr;
    }

//     decrease value
    public void decreaseValue(Node target,int min){
    if(target == null || target.data < min){
        return ;
    }
    target.data = min;
    while(target.parent != null){
        if(target.data > target.parent.data){
            break;
        }else{
            min = target.data;
            target.data = target.parent.data;
            target.parent.data = min;
            target = target.parent;
        }
    }
    }





}
