package ds.lists;

public interface LinkedList<T> {
    int size();

    // adds a node to the end
    void add(T data);

    void insert(T data, int position);

    // removes from the beginning of a lsit
    T remove();

    T get(int position);

    // returns position of the item
    int find(T data);

    @Override
    String toString();

    T removeAt(int position);
}
