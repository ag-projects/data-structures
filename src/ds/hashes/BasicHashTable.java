package ds.hashes;

import java.util.HashSet;
import java.util.Set;

public class BasicHashTable <K,V> implements HashTable<K, V> {

    private HashEntry[] data;
    private int capacity;
    private int size;

    public BasicHashTable(int tableSize) {
        this.capacity = tableSize;
        this.data = new HashEntry[this.capacity];
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        int hash = calculateHash(key);

        if (data[hash] == null) {
            return null;
        } else {
            return (V) data[hash].getValue();
        }

    }

    @Override
    public void put(K key, V value) {
        int hash = calculateHash(key);
        data[hash] = new HashEntry<>(key, value);
        size++;
    }

    @Override
    public V delete(K key) {

        V value = get(key);

        if (value != null) {
            int hash = calculateHash(key);
            data[hash] = null;
            size--;

            // in care of a collision the value could be added to neighboring hashed slot
            // so start by the neighboring hashed slot and loop in through slots.
            hash = (hash + 1) % this.capacity;

            // find all the possible collisions and remove the values
            while(data[hash] != null) {
                HashEntry entry = data[hash];
                data[hash] = null;
                System.err.println("Rehashing -> " + entry.getKey() + " - " + entry.getValue());
                put((K)entry.getKey(), (V)entry.getValue());
                // entry is repositioned, and no new entry is added so take care of the size increment in put
                size--;
                hash = (hash + 1) % this.capacity;
            }
        }

        return value;
    }

    @Override
    public boolean hasKey(K key) {
        int hash = calculateHash(key);
        if(data[hash] == null) {
            return false;
        } else {
            if (data[hash].getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasValue(V value) {
        for(int i=0; i < capacity; i++) {
            HashEntry entry = data[i];
            if (entry != null && entry.getValue().equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean hasValue2(V value) {
        Set<K> keySet = getKeySet();
        for (K key : keySet) {
            int hash = calculateHash(key);
            if(data[hash] == value) {
                return true;
            }
        }
        return false;
    }




    private Set<K> getKeySet() {
        Set<K> keySet = new HashSet<>();

        for(HashEntry<K, V> entry : data) {
            keySet.add(entry.getKey());
        }
        return keySet;
    }


    private int calculateHash(K key) {
        int hash = (key.hashCode() % this.capacity);
        while (data[hash] != null && !data[hash].getKey().equals(key)) {
            hash = (hash + 1) % this.capacity;
        }
        return hash;
    }

    /**
     *
     * @param <K>
     * @param <V>
     */
    private class HashEntry<K,V> {
        private K key;
        private V value;

        public HashEntry() {
        }

        public HashEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

}
