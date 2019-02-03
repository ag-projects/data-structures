package ds.stacks;

public class BasicStack<T> implements Stack<T>{

    private static final int MAX_STACK_SIZE = 1000;
    private T[] data;
    private int stackPointer;

    public BasicStack() {
        this.stackPointer = 0;
        data = (T[]) new Object[MAX_STACK_SIZE];
    }

    @Override
    public void push(T newElement) {
        data[stackPointer++] = newElement;
    }

    @Override
    public T pop() {
        if(stackPointer == 0) {
            throw new IllegalArgumentException("Stack is empty!");
        }
        return data[--stackPointer];
    }

    @Override
    public boolean contains(T element) {
        boolean result = false;

        for(int i=0; i< stackPointer; i++) {
            if(data[i].equals(element)) {
                result = true;
                break;
            }
        }
        return result;
    }

    @Override
    public T access(T element) {
        while (stackPointer > 0) {
            T poppedElement = pop();
            if(poppedElement.equals(element)) {
                return poppedElement;
            }
        }
        throw new IllegalArgumentException("The item is not found in the stack " + element);
    }

    @Override
    public int size() {
        return stackPointer;
    }
}
