package ds.trees;

public interface BinaryTree<T extends Comparable<T>> {
    int size();

    void add(T item);

    boolean delete(T item);

    boolean contains(T item);
}
