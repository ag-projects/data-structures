package ds.hashes;

public interface HashTable<K, V> {

    /**
     * @return
     */
    int size();

    /**
     * @param key
     * @return
     */
    V get(K key);

    /**
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     * @param key
     * @return
     */
    V delete(K key);

    /**
     * @param key
     * @return
     */
    boolean hasKey(K key);

    /**
     * @param value
     * @return
     */
    boolean hasValue(V value);

    /**
     * @param value
     * @return
     */
    boolean hasValue2(V value);
}
