import java.util.ArrayList;
import java.util.List;

public class ModifiedHeap {
    private List<Integer> heap;
    private int x;  // Determines 2^x children

    public ModifiedHeap(int x) {
        this.heap = new ArrayList<>();
        this.x = x;
    }

    private int numChildren() {
        return (int) Math.pow(2, x);
    }

    public void insert(int value) {
        heap.add(value);
        heapifyUp(heap.size() - 1);
    }

    public int popMax() {
        if (heap.isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        int maxValue = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        heapifyDown(0);
        return maxValue;
    }

    private void heapifyUp(int index) {
        int parentIndex = (index - 1) / numChildren();
        while (index > 0 && heap.get(index) > heap.get(parentIndex)) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = (index - 1) / numChildren();
        }
    }

    private void heapifyDown(int index) {
        int largest = index;
        int childIndex = numChildren() * index + 1;
        int endIndex = Math.min(heap.size(), childIndex + numChildren());

        for (int i = childIndex; i < endIndex; i++) {
            if (heap.get(i) > heap.get(largest)) {
                largest = i;
            }
        }

        if (largest != index) {
            swap(index, largest);
            heapifyDown(largest);
        }
    }

    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public static void main(String[] args) {
        ModifiedHeap heap = new ModifiedHeap(2);  // Example with 2^2 = 4 children per node

        heap.insert(10);
        heap.insert(20);
        heap.insert(5);
        heap.insert(30);
        heap.insert(25);

        System.out.println(heap.popMax());  // Should return 30
        System.out.println(heap.popMax());  // Should return 25
    }
}