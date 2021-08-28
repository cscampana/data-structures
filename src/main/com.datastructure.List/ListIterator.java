package com.datastructure.List;

import java.util.Iterator;

/**
 * This class provides an implementation for the Iterator.
 * Furthermore, it is implemented by the ArrayList and its subclasses.
 *
 * @param <E> The type of the element.
 */
public class ListIterator<E> implements Iterator<E> {
    E current;
    ArrayList<E> list;
    private int index;

    /**
     * This constructor provides the initialization for the variables: index, and list.
     *
     * @param list the list being iterated.
     */
    public ListIterator(ArrayList<E> list) {
        index = 0;
        this.list = list;
    }

    /**
     * If it has a next element in the list, it will return true. False, otherwise.
     * @return a boolean indicating if there is another element after the current.
     */
    @Override
    public boolean hasNext() {
        return index < list.length() - 1;
    }

    /**
     * Provides the next element.
     * @return the next element of type E.
     */
    @Override
    public E next() {
        index++;
        current = list.get(index);
        return current;
    }
}
