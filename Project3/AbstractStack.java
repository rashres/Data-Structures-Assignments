// Rahul Shrestha
// Project 3 - Data Structures
// AbstractStack.java

import java.util.ArrayList;

public abstract class AbstractStack<E> implements BDDStack<E> {
    // Returns true if the stack is empty (no elements)
    @Override
    public boolean isEmpty() {
        return depth() == 0;
    }

    @Override
    // Returns true if the stack is full (capacity reached)
    public boolean isFull() {
        return depth() == capacity();
    }

    // Reverses the elements in the stack by popping them into a temporary list
    @Override
    public void flip() {
        ArrayList<E> temp = new ArrayList<>();
        while (!isEmpty()) {
            temp.add(pop());
        }
        temp.forEach(this::push);
    }

    // Creates a shallow copy of the stack by pushing all elements into a new stack
    @Override
    public BDDStack<E> copy() {
        BDDStack<E> newStack = newInstance();
        forEach(newStack::push);
        return newStack;
    }
}
