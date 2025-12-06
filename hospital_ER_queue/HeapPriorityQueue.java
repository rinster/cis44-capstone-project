package hospital_ER_queue;

import java.util.ArrayList;
import java.util.List;

class HeapPriorityQueue {
    private List<Patient> heap = new ArrayList<>();

    // Helper methods
    protected int parent(int j) {
        return (j - 1) / 2;
    }

    protected int left(int j) {
        return 2 * j + 1;
    }

    protected int right(int j) {
        return 2 * j + 2;
    }

    public int size() {
        return heap.size();
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    private void swap(int i, int j) {
        Patient temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void insert(Patient key) {
        heap.add(key);
        upheap(heap.size() - 1);
    }

    public Patient removeMin() {
        if (isEmpty())
            return null;
        Patient answer = heap.get(0);
        // Move last element to root
        Patient last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            downheap(0);
        }
        return answer;
    }

    public Patient min() {
        return isEmpty() ? null : heap.get(0);
    }

    private void upheap(int j) {
        while (j > 0) {
            int p = this.parent(j);
            if (heap.get(j).compareTo(heap.get(p)) >= 0)
                break;
            swap(j, p);
            j = p;
        }
    }

    private void downheap(int j) {
        // Find smaller child, swap if child < parent, move down
        int n = heap.size();
        while (left(j) < n) {
            int left = left(j);
            int right = right(j);
            int smallChild = left;
            if (right < n && heap.get(right).compareTo(heap.get(left)) < 0) {
                smallChild = right;
            }

            if (heap.get(smallChild).compareTo(heap.get(j)) >= 0)
                break;

            swap(j, smallChild);
            j = smallChild;
        }
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
