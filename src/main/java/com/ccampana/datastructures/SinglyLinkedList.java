package com.ccampana.datastructures;

import java.util.NoSuchElementException;

/**
 * This class is an implementation of a generic singly linked list.
 * A linked list is a data structure that connects linearly object nodes.
 * The first node (Head) connects exclusively to the next node. Thus, creating a chain of nodes.
 * The current version is designed to be a trimmed-down version of JDK's reference implementation.
 * Only the essential features are represented here.
 * It implements the interface {@Link main.java.com.ccampana.datastructures.List}.
 * <p>
 * This project is licensed under GNU General Public License v3.0.
 * <p>
 * Created by Caike Salles Campana - csallesc@ucsd.edu
 *
 * @version 0.1
 * @Author: Caike Salles Campana
 * @See: main.java.com.ccampana.datastructures.List
 */
public class SinglyLinkedList<E> implements List<E> {
    private final static int NOT_FOUND = -1;
    private Node head;
    private int size;

    /**
     * The default constructor, it initializes the head node.
     */
    public SinglyLinkedList() {
        head = null;
    }

    /**
     * Adds a value to the singly linked list. It checks to see if the head node still null. In case
     * the head is not null,it finds the tail of the list, and add the new node there.
     *
     * @param e element to be added to the list.
     */
    @Override
    public void add(E e) {
        ++size;
        if (head == null) {
            head = new Node(e, null);
            return;
        }
        Node iterate = head;
        while (iterate.next != null) {
            iterate = iterate.next;
        }
        iterate.next = new Node(e, null);
    }

    /**
     * This method removes an element e in the list. It checks if the element exists, otherwise
     * it throws the exception NoSuchElementException.
     *
     * @param e the element to be removed.
     * @throws NoSuchElementException in case the element does not exist.
     */
    @Override
    public void remove(E e) throws NoSuchElementException {
        Node iterate = head, prev = null;

        while (iterate != null && !iterate.value.equals(e)) {
            prev = iterate;
            iterate = iterate.next;
        }

        if (iterate == null) throw new NoSuchElementException();

        prev.next = iterate.next;
        --size;
    }


    /**
     * This method finds the element e, but it does not remove it. It uses an index to find the
     * element. It checks to see if the index is valid, otherwise it throws
     * a ArrayIndexOutOfBoundsException.
     * This is useful to iterate through the list.
     *
     * @param index the index of the element.
     * @return the element at the index.
     * @throws ArrayIndexOutOfBoundsException in case the element does not exist.
     */
    @Override
    public E get(int index) throws NoSuchElementException {
        if (index > size) throw new NoSuchElementException();
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.value;
    }


    /**
     * It clears the Singly linked list, by setting the head to null.
     */
    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * It finds the index of some element e in the singly linked list. In case it does not
     * find, it returns -1, denoted by the constant NOT_FOUND.
     *
     * @param e to be found in the singly linked list.
     * @return an int with the position within the linked list, otherwise -1.
     */
    @Override
    public int indexOf(E e) {
        Node temp = head;
        int index = 0;
        boolean found = false;
        while (head != null) {
            if (head.value.equals(e)) {

                found = true;
                break;
            }
            head = head.next;
            index++;

        }
        if (!found) return NOT_FOUND;
        return index;
    }

    /**
     * This method creates an array from the linked list. It uses a for loop to iterate
     * through the list and assign the value to the Array.
     *
     * @return An array with all the values of the linked list.
     */
    @Override
    @SuppressWarnings({"unchecked"})
    public E[] toArray() {
        E[] array = (E[]) new Object[size];
        Node iterate = head;
        for (int i = 0; i < size; i++) {
            array[i] = iterate.value;
            iterate = iterate.next;
        }
        return array;
    }

    /**
     * Returns the size of the linked list.
     *
     * @return an int with the linked list current size.
     */
    @Override
    public int size() {
        return size;
    }



    /**
     * Indicates whether the linked list is empty or not.
     *
     * @return a boolean, indicating if the linked list is empty or not.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * This overrides the method toString from the Object class. Used to return the
     * linked list values as a string. We use String builder to facilitate and optimize the
     * process.
     *
     * @return a string containing all the values of the linked list, nicely formatted.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node iterate = head;
        while (iterate != null) {
            sb.append(iterate.value);
            iterate = iterate.next;
        }
        return sb.toString();
    }


    /**
     * This is an inner class that defines the node of the linked list. It contains only
     * the constructor which assigns the value and the reference to the next node fields.
     */
    class Node {
        Node next;
        E value;

        /**
         * This is the default constructor of the inner class Node. It only assigns the parameters
         * values to the fields.
         *
         * @param value the value of the object.
         * @param next  A reference to the next node.
         */
        public Node(E value, Node next) {
            this.next = next;
            this.value = value;
        }
    }
}
