package com.ccampana.datastructures;

public class BinaryTree<K, V> implements Tree<K,V>{
    Node<K,V> root;

    public BinaryTree(){
        root = null;
    }

    public BinaryTree(K key, V value){
        this.root = new Node<>(key,value, null);
    }


    @Override
    public void addNode(K key, V value) {

    }

    @Override
    public void removeNode(K key) {

    }

    @Override
    public Node<K, V> getRoot() {
        return null;
    }

    @Override
    public V getValue(K key) {
        if(key < root.getKey()){

        }
        return null;
    }

    @Override
    public List<K> getKeys() {
        return null;
    }

    @Override
    public List<V> getValues() {
        return null;
    }

}
