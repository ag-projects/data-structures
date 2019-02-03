package ds.queues;

import java.util.ArrayList;
import java.util.List;

public class ListQueue<T> implements Queue<T> {

    private int front;
    private int end;
    private List<T> data;

    public ListQueue() {
        data = new ArrayList<>();
        front = -1;
        end = -1;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void enQueue(T element) {

        // if ((end + 1) % data.length == front)

        if (end == size()) {
            throw new IllegalArgumentException("Queue is full");
        }
        else if (size() == 0) {
            front++;
            end++;
            data.add(element);
        }
        else {
            end++;
            data.add(element);
        }

    }

    @Override
    public T deQueue() {
        T result = null;
        if(size() == 0) {
            throw new IllegalArgumentException("Queue is empty!");
        }
        else if (front == end) {
            result = data.get(front);
            data.remove(front);
            front = -1;
            end = -1;
        }
        else {
            result = data.get(front);
            data.remove(front);
            front++;
        }
        return result;
    }

    @Override
    public boolean contains(T element) {
        boolean found = false;

        if (size() == 0) {
            return found;
        }

        for(int i = front; i < end; i++) {
            if(data.get(i).equals(element)) {
                found = true;
                break;
            }
        }
        return found;
    }

    @Override
    public T access(int position) {
        T result = null;

        if (size() == 0) {
            throw new IllegalArgumentException("Queue is empty");
        }

        if (position > size()) {
            throw new IllegalArgumentException("Position is greater than the size of the queue");
        }

        int index = 0;
        for(int i = front; i < end; i++) {
            if(index == position) {
                result = data.get(index);
                return result;
            }
            index++;
        }
        throw new IllegalArgumentException("Could not find the element in the queue with " + position + " position");
    }
}
