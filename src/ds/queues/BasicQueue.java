package ds.queues;

public class BasicQueue<T> implements Queue<T> {

    private T[] data;
    private int front;
    private int end;

    public BasicQueue() {
        this(1000);
    }

    public BasicQueue(int size) {
        front = -1;
        end = -1;
        data = (T[]) new Object[size];
    }

    @Override
    public int size() {
        if(front == -1 && end == -1) {
            return 0;
        } else {
            return end - front + 1;
        }
    }

    @Override
    public void enQueue(T element) {

        if ((end + 1) % data.length == front) {
            throw new IllegalArgumentException("Queue is full !");
        } else if (size() == 0) {
            front++;
            end++;
            data[end] = element;
        } else {
            end++;
            data[end] = element;
        }
    }

    @Override
    public T deQueue() {
        T result = null;

        if (size() == 0) {
            throw new IllegalArgumentException("Queue is empty!");
        }
        else if (front == end) {
            result = data[front];
            data[front] = null;
            front = -1;
            end = -1;
        }
        else {
            result = data[front];
            data[front] = null;
            front++;
        }
        return result;
    }

    @Override
    public boolean contains(T element) {
        boolean result = false;

        if( size() == 0) {
            return result;
        }

        for(int i=front; i < end; i++) {
            if(data[i].equals(element)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T access(int position) {
        if (size() == 0) {
            throw new IllegalArgumentException("Queue is empty");
        }

        if (position > size()) {
            throw new IllegalArgumentException("Position " + position + " is outside of the queue range/size");
        }

        int index = 0;
        for(int i=front; i < end; i++) {
            if(index == position) {
                return data[i];
            }
            index++;
        }
        throw new IllegalArgumentException("Could not find the element in the queue with " + position + " position");
    }

}
