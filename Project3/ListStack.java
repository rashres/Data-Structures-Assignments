// Rahul Shrestha
// Project 3 - Data Structures
// ListStack.java

import java.util.ArrayList; 
import java.util.Iterator;

public class ListStack<E> extends AbstractStack<E> {
    private ArrayList<E> elements;
    private final int capacity;

    // Constructor to initialize the stack with a given capacity
    public ListStack(int max) {
        if (max <= 0) throw new IllegalArgumentException("Capacity has to be higher than 0");
        this.capacity = max;
        this.elements = new ArrayList<>(max);
    }

    //Pushes an element onto the stack
    @Override
    public void push(E element) {
        if (element == null) throw new NullPointerException();
        if (isFull()) throw new IllegalStateException("Stack is full");
        elements.add(element);
    }

    // Pops an element from the top of the stack
    @Override
    public E pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        return elements.remove(elements.size() - 1);// Remove and return the top element
    }

    @Override
    public int depth() {
        return elements.size();
    }

    @Override
    public void clear() {
        elements.clear();
    }

    @Override
    public BDDStack<E> newInstance() {
        return new ListStack<>(capacity); // Create a new stack with the same capacity
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return elements.iterator();
    }
}
