package com.ccampana.datastructures;

import java.util.NoSuchElementException;

public interface List<E> {
    void add(E e);

    E get(int index) throws NoSuchElementException;

    void remove(E e) throws NoSuchElementException;

    void clear();

    int indexOf(E e);

    E[] toArray();

    int size();

    boolean isEmpty();

    String toString();
}
