package com.datastructure.List;

public interface Queue<E> {
    void enqueue(E e);

    E dequeue();

    E peek();

    int size();

    boolean isEmpty();

    void clear();
}
