package com.ccampana.datastructures;

import java.util.NoSuchElementException;

public class BinaryTree<K extends Comparable<K>, V extends Comparable<V>> implements Tree<K, V> {
    Node<K, V> root;

    public BinaryTree() {
        root = null;
    }

    public BinaryTree(K key, V value) {
        this.root = new Node<K, V>(key, value, null);
    }


    @Override
    public void addNode(K key, V value) {
        Node<K, V> holding = null;
        Node<K, V> temp = root;
        while (temp != null) {
            holding = temp;
            if (temp.getKey().compareTo(key) < 0) temp = temp.getRight();
            else temp = temp.getLeft();
        }
        Node<K, V> newNode = new Node<K, V>(key, value, holding);
        if(holding == null){
            holding = newNode;
        }
        else if (holding.getKey().compareTo(key) < 0) {
            holding.setRight(newNode);
        } else {
            holding.setLeft(newNode);
        }
    }

    @Override
    public void removeNode(K key) {

    }

    @Override
    public Node<K, V> getRoot() {
        return root;
    }

    @Override
    public V getValue(K key) {
        Node<K, V> target = getValuesNode(key, root);
        if (target == null) throw new NoSuchElementException();
        return target.getValue();
    }

    private Node<K, V> getValuesNode(K key, Node<K, V> node) {
        if (node == null || node.getKey().compareTo(key) == 0) return node;
        if (key.compareTo(node.getKey()) < 0) return getValuesNode(key, node.getLeft());
        else return getValuesNode(key, node.getRight());
    }

    @Override
    public List<K> getKeys() {
        List<K> keys = new ArrayList<>();
        addToKeysList(root, keys);
        return keys;
    }

    private void addToKeysList(Node<K, V> node, List<K> keys) {
        if (node == null) return;
        keys.add(node.getKey());
        addToKeysList(node.getLeft(), keys);
        addToKeysList(node.getRight(), keys);
    }

    @Override
    public List<V> getValues() {
        List<V> values = new ArrayList<>();
        addToValuesList(root, values);
        return values;
    }

    private void addToValuesList(Node<K, V> node, List<V> values) {
        if (node == null) return;
        values.add(node.getValue());
        addToValuesList(node.getLeft(), values);
        addToValuesList(node.getRight(), values);
    }

}
