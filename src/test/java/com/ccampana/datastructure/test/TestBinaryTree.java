package com.ccampana.datastructure.test;
import org.junit.Test;

import com.ccampana.datastructures.BinaryTree;

import static org.junit.Assert.*;

public class TestBinaryTree {
    @Test
    public void testCreateAndAdd(){
        BinaryTree<Integer, Integer> test = new BinaryTree<>(2,3);
        System.out.println(test.getValue(2));
    }
}
