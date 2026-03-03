// Rahul Shrestha
// Project 3 - Data Structures
// ArrayStack.java

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<E> extends AbstractStack<E> {
    private E[] elements;
    private int size = 0; 
    private final int capacity;

    // Constructor to initialize the stack with a given capacity
    @SuppressWarnings("unchecked")
    public ArrayStack(int max) {
        if (max <= 0) throw new IllegalArgumentException("Capacity has to be higher than 0");
        this.capacity = max;
        this.elements = (E[]) new Object[max];
    }

    // Pushes an element onto the stack
    @Override
    public void push(E element) {
        if (element == null) throw new NullPointerException();
        if (isFull()) throw new IllegalStateException("Stack is full");
        elements[size++] = element;
    }

    // Pops an element from the top of the stack
    @Override
    public E pop() {
        if (isEmpty()) throw new IllegalStateException("Stack is empty");
        E element = elements[--size];
        elements[size] = null; // Help GC
        return element;
    }

    @Override
    public int depth() {
        return size;
    }

    // Clears the stack by nullifying all elements
    @Override
    public void clear() {
        for (int i = 0; i < size; i++) elements[i] = null;
        size = 0;
    }

    @Override
    public BDDStack<E> newInstance() {
        return new ArrayStack<>(capacity);
    }

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();  
    }

    // ListIterator class for ArrayStack to iterate from bottom to top
    public class ListIterator implements Iterator<E> {
        private int current = 0;  // Start from the bottom (index 0)

        @Override
        public boolean hasNext() {
            return current < size;  // Ensure there are elements to iterate
        }

        
        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            return elements[current++];  // Return the element and increment the index
        }
    }
}
