package com.ccampana.datastructures;

/**
 * <p>
 * ArrayBlockingQueue is a generic class that extends ArrayQueue {@link ArrayQueue}.
 * The objective of this data structure is to have a Queue implemented through a Linked com.ccampana.datastructures.List
 * {@link ArrayList},which has a limited size.
 * </p>
 * <p>
 * A Queue is a FILO (First-In Last-Out) type of data structure since
 * the element entered last is the first to be removed. One of its uses is a concurrent
 * application.
 * </p>
 * <p>
 * The operations supported are all of those of the ArrayQueue since it extends.
 * Unless defined in the constructor, the default capacity is 20.
 * </p>
 *
 * <p>
 * This project is licensed under Creative Commons Attribution 4.0 International License.
 * <p>
 * Created by <b>Caike Salles Campana - csallesc@ucsd.edu</b>
 *
 * @param <E> the generic type.
 * @author Caike Salles Campana
 * @version 0.1
 */
public class ArrayBlockingQueue<E> extends ArrayQueue<E> {
    /**
     * A constant that defines the default capacity of the ArrayQueue.
     */
    final static int DEFAULT_CAPACITY = 20;
    /**
     * The capacity of the BlockingQueue.
     */
    private int capacity;

    /**
     * The default constructor initializes all the variables,
     * calls the default constructor of the class ArrayQueue and defines the capacity according
     * to the constant DEFAULT_CAPACITY, which is 20.
     *
     * @see #DEFAULT_CAPACITY
     */
    public ArrayBlockingQueue() {
        super();
        capacity = DEFAULT_CAPACITY;
    }

    /**
     * This constructor allows for default capacity to be changed.
     * It uses the parameter to set the capacity and also calls the default constructor.
     *
     * @param capacity The capacity of the Queue.
     */
    public ArrayBlockingQueue(int capacity) {
        this();
        this.capacity = capacity;
    }

    /**
     * The method enqueue allows for an object to be added (enqueue) in the data structure.
     * It checks for the capacity through the private method, checkCapacity. If the method
     * returns a false, it throws an IllegalStateException.
     * Otherwise, it adds the element to the Queue.
     *
     * @param e the element to be enqueue.
     * @see #checkCapacity()
     */
    @Override
    public void enqueue(E e) {
        if (!checkCapacity()) {
            throw new IllegalStateException();
        }
        super.enqueue(e);
    }

    /**
     * A boolean indicating whether there is remaining capacity in the Queue.
     *
     * @return A boolean indicating whether there is still remaining capacity in the Queue.
     */
    private boolean checkCapacity() {
        return (super.size() < capacity);
    }
}
