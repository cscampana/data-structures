package com.ccampana.datastructure.test;
import org.junit.Test;

import com.ccampana.datastructures.BinaryTree;

import static org.junit.Assert.*;

public class TestBinaryTree {
    @Test
    public void testCreateAndAdd(){
        BinaryTree<Integer, Integer> test = new BinaryTree<>(12,1);
        test.addNode(5,2);
        test.addNode(2,3);
        test.addNode(9,4);
        System.out.println("test");
    }
    @Test
    public void testGetVal(){
        BinaryTree<Integer, Integer> test = new BinaryTree<>(12,1);
        test.addNode(5,2);
        test.addNode(2,3);
        test.addNode(9,4);
        Integer key = test.getValue(9);
        assertEquals(java.util.Optional.of(4),java.util.Optional.of(key));
    }

    @Test
    public void testGetAllKeys(){
        BinaryTree<Integer, Integer> test = new BinaryTree<>(12,1);
        test.addNode(5,2);
        test.addNode(2,3);
        test.addNode(9,4);
        System.out.println(test.getKeys());
    }
    @Test
    public void testGetAllValues(){
        BinaryTree<Integer, Integer> test = new BinaryTree<>(12,1);
        test.addNode(5,2);
        test.addNode(2,3);
        test.addNode(9,4);
        System.out.println(test.getValues());
    }
}
