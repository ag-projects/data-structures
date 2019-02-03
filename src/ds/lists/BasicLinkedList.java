package ds.lists;

public class BasicLinkedList<T> implements LinkedList<T> {

    private Node first;
    private Node last;
    private int nodeCount;

    public BasicLinkedList() {
        this.first = null;
        this.last = null;
        this.nodeCount = 0;
    }

    @Override
    public int size() {
        return nodeCount;
    }

    // adds a node to the end
    @Override
    public void add(T data) {
        Node newNode = new Node(data);

        // adding when list is empty
        if(first == null) {
            first = newNode;
            last = first;
        }
        // adding @ the end
        if(first != null) {
            last.next = newNode;
            last = newNode;
        }
        nodeCount++;
    }

    @Override
    public void insert(T data, int position) {

        if (size() < position) {
            throw new IllegalArgumentException("Linked list is smaller than the " + position + " position!");
        }

        Node current = first;
        for(int i=0; i < position && current != null; i++) {
            current = current.next;
        }

        Node newNode = new Node(data);
        Node nextNode = current.next;
        // TODO move them up&down
        current.next = newNode;
        newNode.next = nextNode;
        nodeCount++;
    }

    // removes from the beginning of a lsit
    @Override
    public T remove() {
        // if list is empty
        if (first == null) {
            throw new IllegalArgumentException("The linkedList is empty and there are no data to return!");
        }

        // if there are nodes in a list
        T temp = first.data;
        first = first.next;
        nodeCount--;

        return temp;
    }

    @Override
    public T get(int position) {

        if (size() < position) {
            throw new IllegalArgumentException("The position " + position + "is greater than the size of the linkedList!");
        }

        Node current = first;
        for(int i=0; i < position && current != null; i++) {
            if(i == position) {
                return current.data;
            }
            current = current.next;
        }
        return null;
    }

    // returns position of the item
    @Override
    public int find(T data) {

        if(first == null) {
            throw new IllegalArgumentException("There are no items in the linkedList!");
        }

        Node current = first;
        for (int i = 0; i < size() && current != null; i++) {
            if(current.data == data) {
               return i;
            }
            current = current.next;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuffer contents = new StringBuffer();
        Node current = first;

        while(current != null) {
            contents.append(current.data);
            current = current.next;
            if (current != null) {
                contents.append(", ");
            }
        }
        return contents.toString();
    }

    @Override
    public T removeAt(int position) {

        if (size() < position) {
            throw new IllegalArgumentException("LinkedList is smaller than the " + position + " position!");
        }

        Node current = first;
        Node previous = first;
        for(int i = 0; i < position && current != null; i++) {
            previous = current;
            current = current.next;
        }
        T removedData = current.data;
        previous.next = current.next;
        nodeCount--;

        return removedData;
    }

    /**
     * Inner class: Node
     */
    private class Node {
        private Node next;
        private T data;

        public Node(T data) {
            this.next = null;
            this.data = data;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public T getData() {
            return data;
        }
    }
}
