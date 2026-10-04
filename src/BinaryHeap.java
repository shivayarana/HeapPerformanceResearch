public class BinaryHeap implements Heap{

    private int[] heap;
    private int index;
    public BinaryHeap(int capacity){
        heap = new int[capacity];
        index = -1;
    }

//    resizing heap
    private void resize(){
        int[] newHeap = new int[heap.length * 2];
        System.arraycopy(heap,0,newHeap,0,heap.length);
        heap = newHeap;
    }

    public boolean isFull(){
        return index == heap.length - 1;
    }

    private void swap(int parent,int child){
        int temp = heap[parent];
        heap[parent] = heap[child];
        heap[child] = temp;
    }

    @Override
    public void insert(int key){

        // resizing heap if full
        if(isFull()){
            resize();
        }
        index = index + 1;
        heap[index]  = key;

//        Heapify the binary heap
        int  child = index;
        int parent;

        while(child > 0){
            parent = child % 2 == 0? (child - 1) / 2: child / 2;
            if(heap[child] >= heap[parent]){
                return;
            }else{
                swap(parent,child);
                child = parent;
            }
        }


    }
    public  boolean isEmpty(){
        return index == -1;
    }
    @Override public int size(){
        return index+1;
    }

    @Override public int extractMin(){
        if(isEmpty()){
            return -1;
        }
        int last  = heap[index--];
        int key = heap[0];

        int root = 0, left = 1, right = 2;
        heap[0] = last;

//        heapify
        while(left <= index ){
            if(heap[root] <= heap[left] && heap[root] <= heap[right]){
                return key;
            }else if(heap[right] <= heap[left]){
                swap(root,right);
                root = right;
            }else{
                swap(root,left);
                root = left;
            }
            left = 2 * root + 1;
            right = 2 * root + 2;
        }
    return key;
    }

   public void display(){
        for(int i = 0; i < index+1; i++){
            System.out.print(heap[i]+" ");
        }
       System.out.println();
   }

}
