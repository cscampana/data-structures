package com.datastructure.List;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p>
 * This is a generic implementation of the data structure ArrayList, it serves as basis for many
 * other data structures, and allows for easy manipulation of arrays.
 * </p>
 * <p>
 * It was designed to be a trimmed version, with all the essential features, and to be easily
 * customized to the needs of the project that it will be used. It implements the interface
 * List.
 * </p>
 * <p>
 * Its initial capacity is 10 as well as its growth rate is 1.5.
 * </p>
 * <p>
 * This project is licensed under Creative Commons Attribution 4.0 International License.
 * </p>
 * <p>
 * Created by <b>Caike Salles Campana - csallesc@ucsd.edu</b>
 * </p>
 *
 * @param <E> The type of the element to be added to the array list.
 * @author Caike Salles Campana
 * @version 0.1
 */
public class ArrayList<E> implements List<E>, Iterable<E> {
    /**
     * Initial Capacity of the array. Default value is 10.
     */
    final private static int INITIAL_CAPACITY = 10;
    /**
     * Growth rate of the array, as it grows. Default value is 1.5
     */
    final private static double GROWTH_FACTOR = 1.5;

    /**
     * Used in the method indexOf to denote an element that is not present in the list.
     */
    final int NOT_FOUND = -1;
    /**
     * Keeps the current number of elements in the array. Updated when an element is
     * removed or added to the main.com.datastructure.List.ArrayList.
     */
    public int size;
    /**
     * Used to indicate the current capacity of the underlining array.
     */
    int currentArrayCapacity;
    /**
     * The underlining array used to store the objects added to the main.com.datastructure.List.ArrayList.
     */
    E[] array;


    /**
     * The default constructor, it only calls the method {@link ArrayList#clear()}. This method
     * ensures the correct initialization of the fields {@link ArrayList#currentArrayCapacity},
     * {@link ArrayList#size}, and {@link ArrayList#array}.
     *
     * @see ArrayList#clear()
     */
    public ArrayList() {
        clear();
    }

    /**
     * Constructor that adds an array of elements to the main.com.datastructure.List.ArrayList using the method
     * {@link ArrayList#add(E element)}.
     *
     * @param elements an array of elements to be added into the main.com.datastructure.List.ArrayList.
     * @see ArrayList#add(E element)
     */
    public ArrayList(E[] elements) {
        clear();
        for (E element : elements) {
            add(element);
        }
    }

    /**
     * Adds an element to the ArrayList. It first checks to see if it needs to increase the size
     * of the underlining array.
     *
     * @param element Element to be added.
     */
    @Override
    public void add(E element) {
        checkGrowth();
        array[size] = element;
        ++size;
    }

    /**
     * Add an element to a specified position in the array. If the index is greater than the
     * current array capacity, then it throws an IndexOutOfBoundsException, since it is impossible
     * to add an element at such position.
     *
     * @param index the position in the array that the element will be added
     * @param element    the element to be added
     * @throws IndexOutOfBoundsException in case the index is invalid
     */
    @SuppressWarnings({"unchecked"})
    public void add(int index, E element) throws IndexOutOfBoundsException {
        if (index > currentArrayCapacity || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        checkGrowth();
        if (array[index] == null) {
            E[] arrayOld = array;
            currentArrayCapacity++;
            array = (E[]) new Object[currentArrayCapacity];
            for (int i = 0; i < array.length; i++) {

                if (i > index) {
                    array[i] = arrayOld[i - 1];
                } else {
                    array[i] = arrayOld[i];
                }

            }
        }
        array[index] = element;
        ++size;
    }

    /**
     * This private method checks to see whether is necessary to grow the array. The default
     * policy is to grow when the field loadFactor is greater than 0.75. In case that
     * condition is true, it calls the method {@link ArrayList#grow()}.
     *
     * @see ArrayList#grow()
     */
    private void checkGrowth() {
        if (size == currentArrayCapacity) {
            grow();
        }
    }


    /**
     * This method returns an element from an specified index. In case the argument given as index
     * is greater than the current capacity, it throws an ArrayIndexOutOfBounds exception, since
     * it is impossible to exist such element in the Arraylist.
     *
     * @param index index passed to return the element.
     * @return the element at the specified index.
     * @throws ArrayIndexOutOfBoundsException in case there the parameter index is greater than the
     *                                        current capacity of the array.
     */
    @Override
    public E get(int index) throws NoSuchElementException {
        if (index > currentArrayCapacity) throw new NoSuchElementException();

        return array[index];
    }


    /**
     * This method grows the array if called. It uses an array copy functionality to copy the old
     * elements to the new and increased array.
     * The size of the new array is defined by the current capacity times the growth factor.
     */
    @SuppressWarnings({"unchecked"})
    public void grow() {
        E[] arrayOld = array;
        currentArrayCapacity = (int) (currentArrayCapacity * GROWTH_FACTOR);
        array = (E[]) new Object[currentArrayCapacity];
        for (int i = 0; i < arrayOld.length; i++) array[i] = arrayOld[i];

    }


    /**
     * This method removes an specified element from the array. It first finds it using the
     * method {@link ArrayList#indexOf(E element)}.
     *
     * @param element element to be removed from the Array main.com.datastructure.List.List.
     * @throws NoSuchElementException if the element does not exist on the array.
     */
    @Override
    public void remove(E element) throws NoSuchElementException {
        int indexElement = indexOf(element);
        if (indexElement == NOT_FOUND) {
            throw new NoSuchElementException();
        }
        array[indexOf(element)] = null;
        --size;
    }


    /**
     * This method clears the array list. Eliminating all the elements.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public void clear() {
        array = (E[]) new Object[INITIAL_CAPACITY];
        size = 0;
        currentArrayCapacity = INITIAL_CAPACITY;
    }

    /**
     * Finds the index of an element, searching through the array for such element and recoding
     * its index. If it does not found, it returns -1 defined in the
     * constant {@link ArrayList#NOT_FOUND}.
     *
     * @param element element to be searched
     * @return an int containing the index, if not found -1.
     */
    @Override
    public int indexOf(E element) {
        if (element == null) return NOT_FOUND;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null && array[i].equals(element)) {
                return i;
            }
        }

        return NOT_FOUND;
    }

    /**
     * Transforms the Arraylist into a simple array and returns it.
     *
     * @return An array representing the arraylist
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public E[] toArray() {
        E[] newArray = (E[]) new Object[size()];
        for (int i = 0; i < newArray.length; i++) newArray[i] = array[i];
        return newArray;
    }


    /**
     * Returns the size of the ArrayList.
     *
     * @return the size of the array.
     */
    @Override
    public int size() {
        return size;
    }


    public int length() {
        return currentArrayCapacity;
    }

    /**
     * If size is 0, returns true. Otherwise, returns false.
     *
     * @return a boolean indicating whether the ArrayList is empty.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Overrides the toString method. Using StringBuilder, the program creates a string
     * containing all values of the ArrayList. Returns a string representing it.
     *
     * @return a string with all the values of the ArrayList.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (E element : array) {
            if (element == null) {
                continue;
            }
            sb.append(element).append(" ");
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * This method creates a new ListIterator object. It is used to iterate over the ArrayList
     * and its implementations.
     *
     * @return an Iterator through the ArrayList.
     */
    @Override
    public Iterator<E> iterator() {
        return new ListIterator<E>(this);
    }
}
