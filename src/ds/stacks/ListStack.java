package ds.stacks;

import java.util.ArrayList;
import java.util.List;

public class ListStack<T> implements Stack<T>{

    private List<T> data;

    public ListStack() {
        data = new ArrayList<>();
    }

    @Override
    public void push(T newElement) {
        data.add(newElement);
    }

    @Override
    public T pop() {
        int index = data.size() - 1;
        return data.remove(index);
    }

    @Override
    public boolean contains(T element) {
        return data.contains(element);
    }

    @Override
    public T access(T element) {
        for(int i=0; i< data.size(); i++) {
            if(data.get(i).equals(element)) {
                return data.get(i);
            }
        }
        throw new IllegalArgumentException("Could not find the element -> " + element);
    }

    @Override
    public int size() {
        return data.size();
    }
}
