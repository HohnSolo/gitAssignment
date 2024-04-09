

public class PriorityQueue<T extends Comparable<T>> extends ArrayHeap<T> {

    // initialize the priority queue
    public PriorityQueue() {
        super();
    }

    // initialize with given capacity
    public PriorityQueue(int capacity) {
        super(capacity);
    }

    // insert a new elem into the queue
    public void insert(T elem) {
        addElement(elem);
    }

    // remove and return the elem with the highest priority, which should be min elem
    public T remove() {
        return removeMin();
    }

    // return a string of the queue
    @Override
    public String toString() {
        return super.toString();
    }
}

