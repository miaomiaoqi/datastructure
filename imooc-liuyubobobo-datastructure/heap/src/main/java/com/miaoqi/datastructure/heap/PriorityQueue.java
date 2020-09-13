package com.miaoqi.datastructure.heap;

import com.miaoqi.datastructure.liner.queue03.Queue;

/**
 * 使用堆实现优先队列
 *
 * 在 N 个元素中选出前 M 个元素  M 远小于 N
 * leetcode 347
 *
 * @author miaoqi
 * @date 2019-06-19
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {

    private MaxHeap<E> maxHeap;

    public PriorityQueue() {
        this.maxHeap = new MaxHeap();
    }

    @Override
    public int getSize() {
        return this.maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return this.maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        this.maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return this.maxHeap.extractMax();
    }

    @Override
    public E getFront() {
        return this.maxHeap.findMax();
    }
}
