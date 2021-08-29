package com.ccampana.datastructures;

import java.util.NoSuchElementException;

/**
 * <p>
 * ArrayQueue is an implementation of the queue through an array list.
 * The queue is a data structure that provides a FILO (First-In, Last-Out);
 * elements are placed in the last position and removed from the first position.
 * </p>
 * <p>
 * This class implementation uses an array list as a supporting data structure.
 * There are two constructors. The default only initializes the variable size and the array list.
 * Whereas the other allows for the input of an array containing objects to be added to the queue.
 * </p>
 * <p>
 * The supported operations are:
 * <ul>
 * <li>{@link ArrayQueue#enqueue(E e)} - Adds an element to the end of the queue.</li>
 * <li>{@link ArrayQueue#dequeue()} - Retrieves and removes the element at position 0.</li>
 * <li>{@link ArrayQueue#peek()} - Retrieves but does not remove an element in front of the queue.</li>
 * <li>{@link ArrayQueue#size()} - Returns the size of the queue.</li>
 * <li>{@link ArrayQueue#isEmpty()} - Checks to see if the queue is empty.</li>
 * <li>{@link ArrayQueue#clear()} - Clears the queue.</li>
 * </ul>
 * </p>
 * <p>
 * This project is licensed under Creative Commons Attribution 4.0 International License.
 * </p>
 * <p>
 * Created by <b>Caike Salles Campana - csallesc@ucsd.edu</b>
 * </p>
 * @param <E> The type of the element to be added to the queue.
 * @see ArrayList
 * @see Queue
 * @author Caike Salles Campana
 * @version 0.1
 */
public class ArrayQueue<E> implements Queue<E> {
    /**
     * The underlining array list to support the queue implementation.
     */
    ArrayList<E> queue;
    /**
     * The size of the queue.
     */
    private int size;

    /**
     * Default constructor, it initializes all the variables.
     */
    public ArrayQueue() {
        queue = new ArrayList<>();
        size = 0;
    }

    /**
     * This overloaded constructor allows for the enqueue of an array of elements as part of its
     * initialization. It also calls the default constructor.
     * @param elements array of elements of type E to be enqueued.
     */
    public ArrayQueue(E[] elements) {
        this();
        for (E e : elements) {
            enqueue(e);
        }
    }

    /**
     * Enqueue allows for the addition of elements to the queue. It adds to the last position.
     * @param e the element to be enqueued. Must be of type E.
     */
    @Override
    public void enqueue(E e) {
        ++size;
        queue.add(queue.size(), e);
    }

    /**
     * Dequeue retrieves and removes an element from the queue. It is always the element at position
     * 0. In case it is empty, it throws the exception {@link NoSuchElementException}.
     * @return The element removed.
     */
    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();

        }
        int i = 0;
        while (queue.get(i) == null) {
            ++i;
        }
        E dequeueElement = queue.get(i);
        queue.remove(dequeueElement);
        --size;
        return dequeueElement;
    }

    /**
     * Peek allows for the retrieval without removal of the first element in the array list.
     * @return The element in position 0.
     */
    @Override
    public E peek() {
        return queue.get(0);
    }

    /**
     * Returns current size of the queue.
     * @return the size of the queue.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * In case size is equal to 0, returns true. Otherwise, returns false.
     * @return a boolean indicating whether the queue is empty or not.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the queue through a call to the  {@link ArrayList#clear()} method.
     */
    @Override
    public void clear() {
        queue.clear();
    }

    /**
     * Overrides the toString to provide a sensible return uses the array list implementation.
     * @return A string containing all the elements currently in the queue.
     */
    @Override
    public String toString() {
        return queue.toString();
    }
}
