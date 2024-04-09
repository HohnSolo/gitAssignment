
// create the class for the SLL
public class SLL<T> {
    // references for head, tail, and variable to track length of list.
    private Node<T> head;
    private Node<T> tail;
    private int length;

    class Node<T> {
        // value to be stored in nodes
        T value;
        // reference for next pointer for the nodes
        Node<T> next;

        // constructor to create new node
        Node(T value) {
            this.value = value;
        }
    }

    // constructor for SLL, and initialize and empty list
    public SLL() {
        head = null;
        tail = null;
        length = 0;
    }

    // returns length of list
    public int getLength() { return length; }

    // method to add to rear of list
    public void addRear(T value) {
        // create new node
        Node<T> newNode = new Node<T>(value);

        // check if list is empty, and if so then head and tail point to new node
        if (length == 0) {
            head = newNode;
            tail = newNode;
            // else add new node to end of list and shift the tail
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        // increment length
        length++;
    }

    // method to add to front
    public void addFront(T value) {
        Node<T> newNode = new Node<T>(value);

        // if length is 0 then head and tail point to newnode
        if (length == 0) {
            head = newNode;
            tail = newNode;

            // else add new node at front and shift head to the new node
        } else {
            newNode.next = head;
            head = newNode;
        }
        // increase length by 1
        length++;
    }

    // method to get a node
    public Node<T> get(int index) {
        // check validity of the index and throw error if out of range
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node<T> temp = head;
        // iterate to the node we want
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        // return the node
        return temp;
    }

    // method to set a node. takes an index and new node
    public void setNode(int index, Node newNode) {
        // check validity of index range. throw exception if out of bounds
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }
        // if index 0 then add to front
        if (index == 0) {
            newNode.next = head.next;
            head = newNode;

            // else get index and insert
        } else {
            Node prev = get(index - 1);
            newNode.next = prev.next.next;
            prev.next = newNode;
        }
    }

    // method to remove last node
    public T removelast() {
        // if list is empty throw an exception
        if (length == 0) throw new IndexOutOfBoundsException("list already empty");
        Node<T> temp = head;
        Node<T> prev = head;

        // iterate to end
        while(temp.next != null) {
            prev = temp;
            temp = temp.next;
        }
        // update tail to second to last node
        tail = prev;
        // if one element
        if (temp == head) {
            head = null;
            tail = null;
        } else {
            // set the next pointer of new pointer to null
            tail.next = null;
        }
        // decrement length and return value
        length--;
        return temp.value;
    }

    // method to remove first value
    public T removeFirst() {
        // throw exception if list is empty
        if (length == 0) throw new IndexOutOfBoundsException("list already empty");
        Node<T> temp = head;
        // shift head
        head = head.next;
        // set previous head to point to null
        temp.next = null;
        //decrement length
        length--;
        // update tail if list is empty after
        if (length == 0) tail = null;
        //return value
        return temp.value;
    }

    // method to delete a node
    public T delete(int index) {
        // check validity of index and throw exception if necessary
        if (index < 0 || index >= length) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        // if first element call removeFirst()
        if (index == 0) return removeFirst();
        // if last call removeLast()
        if (index == length - 1) return removelast();

        // get node prev to the one that will be deleted
        Node<T> prev = get(index - 1);
        // temp will be set to the node we delete
        Node<T> temp = prev.next;
        // shift pointers to remove the node and make it null
        prev.next = temp.next;
        temp.next = null;
        // decrement length and return value
        length--;
        return temp.value;
    }

    // method to convert list to string
    @Override
    public String toString() {
        // return empty brackets if list is empty
        if (head == null) {
            return "[]";
        }

        // use stringbuilder to create string
        StringBuilder sb = new StringBuilder();
        // add first bracket
        sb.append("[");
        // iterate through the lists and append the values to the string
        Node current = head;
        while (current != null) {
            sb.append(current.value);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        // add last bracket and return the string
        sb.append("]");
        return sb.toString();
    }

    // method to swap the nodes for the shellsort method
    public void swapNodes(int index1, int index2) {
        // if indices are same
        if (index1 == index2) return;

        // initialize dummy nodes
        Node<T> prev1 = null, prev2 = null;
        Node<T> node1 = head, node2 = head;

        // Finding nodes and their previous nodes
        for (int i = 0; node1 != null && i < index1; i++) {
            prev1 = node1;
            node1 = node1.next;
        }

        for (int i = 0; node2 != null && i < index2; i++) {
            prev2 = node2;
            node2 = node2.next;
        }

        // if null and out of bounds
        if (node1 == null || node2 == null) throw new IndexOutOfBoundsException("Invalid index");


        // Swapping nodes and updating pointers
        if (prev1 != null) prev1.next = node2;
        else head = node2;

        if (prev2 != null) prev2.next = node1;
        else head = node1;

        Node<T> temp = node1.next;
        node1.next = node2.next;
        node2.next = temp;

        // update tail if needed.
        if (node1 == tail) {
            tail = node2;
        } else if (node2 == tail) {
            tail = node1;
        }
    }
}
