package ds.queues;

public interface Queue<T> {
    /**
     * @return
     */
    int size();

    /**
     * @param element
     */
    void enQueue(T element);

    /**
     * @return
     */
    T deQueue();

    /**
     * @param element
     * @return
     */
    boolean contains(T element);

    /**
     * @param position
     * @return
     */
    T access(int position);
}
