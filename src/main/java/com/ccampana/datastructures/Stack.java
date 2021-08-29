package com.ccampana.datastructures;

import java.util.NoSuchElementException;

/**
 * <p>
 * The Stack is a FIFO (First-In, First-Out) data structure.
 * Where the first element to be added will be the first one to be removed.
 * Since this implementation can be treated as a list that has its first
 * element removed and added repeatedly, we shall extend the class ArrayList
 * and use it as a backbone.
 * </p>
 * <p>
 * Supported Operations:
 * <ul>
 * <li>{@link Stack#peek()}: Returns but does not remove the first element.</li>
 * <li>{@link Stack#pop()}: Returns and removes the first element.</li>
 * <li>{@link Stack#push(E element)}: adds an element to the Stack.</li>
 * <li>{@link Stack#search(E element)}: searches for an element within the Stack.</li>
 * <li>{@link Stack#toString()}: overrides the toString and provides a concise String with
 * all the elements.</li>
 * </ul>
 * </p>
 * Created by <b>Caike Salles Campana - csallesc@ucsd.edu</b>
 * </p>
 *
 * @param <E> The type of the element to be added to the stack.
 * @see ArrayList
 * @author Caike Salles Campana
 * @version 0.1
 */
public class Stack<E> extends ArrayList<E> {

    /**
     * Retrieves and removes the element from the stack.
     * @return the element removed.
     */
    public E pop() {
        E element = get(0);
        remove(element);
        return element;
    }

    /**
     * Retrieves but does not remove the element from the stack.
     * @return the element .
     */
    public E peek() {
        return get(0);
    }

    /**
     * Adds an element to the stack.
     * @param element to be added to the stack.
     */
    public void push(E element) {
        add(0, element);
    }

    /**
     * Searches an element within the stack and retrieves its position. In case it does not found
     * it throws an NoSuchElement Exception
     * @param element to be searched
     * @return the position within the stack of the element.
     * @see NoSuchElementException
     */
    public int search(E element) {
        for (int i = 0; i < length(); i++) {
            if (get(i) != null && get(i).equals(element)) {
                return i;
            }
        }

        throw new NoSuchElementException();
    }

    /**
     * The implementation of the toString method.
     * @return Returns a sensible String containing
     * all the elements in the stack.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length(); i++) {
            if (get(i) == null) continue;
            sb.append(get(i));
        }
        return sb.toString();
    }
}
