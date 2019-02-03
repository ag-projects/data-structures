package ds.stacks;

public interface Stack<T> {

    /**
     * @param newElement
     */
    void push(T newElement);

    /**
     * @return
     */
    T pop();

    /**
     * @param element
     * @return
     */
    boolean contains(T element);

    /**
     * @param element
     * @return
     */
    T access(T element);

    /**
     * @return
     */
    int size();
}
