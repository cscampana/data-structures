package com.ccampana.datastructures;

public class Tree<K, V> {
    class Node{
        private K key;
        private V value;
        private Node parent, left, right;

        public Node(K key, V value, Node parent){
            this.key = key;
            this.value = value;
            this.parent = parent;
        }
        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public Node getParent() {
            return parent;
        }

        public V getValue() {
            return value;
        }
    }
}
