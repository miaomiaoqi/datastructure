package com.miaoqi.datastructure.tree.trie;

import com.miaoqi.datastructure.tree.bst.BinarySearchTree;

/**
 * 基于二分搜索树的集合实现
 *
 * @author miaoqi
 * @date 2019-06-17
 */
public class BinarySearchTreeSet<E extends Comparable> implements Set<E> {

    private BinarySearchTree bst;

    public BinarySearchTreeSet() {
        this.bst = new BinarySearchTree();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }
}
