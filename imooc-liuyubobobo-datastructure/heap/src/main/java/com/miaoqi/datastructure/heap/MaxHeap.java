package com.miaoqi.datastructure.heap;

import com.miaoqi.datastructure.liner.array01.MyArray;

import java.util.Random;

/**
 * 二叉最大堆, 所有节点都大于等于其孩子节点
 * 底层使用数组实现完全二叉树
 *
 * 数组中 index0 的位置空出方便计算
 * parent(i) = i / 2
 * left = i * 2
 * right = i * 2 + 1
 *
 * 数组中 index0 的位置不空出就是第一个元素根节点(本例中采用此方法)
 * parent(i) = (i - 1) / 2
 * left = (i * 2) + 1
 * right = (i * 2) + 2
 *
 * @author miaoqi
 * @date 2019-06-18
 */
public class MaxHeap<E extends Comparable> {

    private MyArray<E> data;

    public MaxHeap(int capacity) {
        this.data = new MyArray<>(capacity);
    }

    public MaxHeap() {
        this.data = new MyArray<>();
    }

    public MaxHeap(E[] arr) {
        this.data = new MyArray<>(arr);
        // 这个过程叫做 heapify
        // 将一个无序数组, 变为最大堆, 从最后一个非叶子节点(最后一个叶子节点的父节点) 向前遍历进行下沉操作
        for (int i = parent(arr.length - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * 返回完全二叉树的数组表示中, 一个索引所表示的元素的父亲节点的索引
     */
    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index-0 doesn't have parent");
        }
        return (index - 1) / 2;
    }

    /**
     * 返回完全二叉树的数组表示中, 一个索引所表示的元素的左孩子节点的索引
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * 返回完全二叉树的数组表示中, 一个索引所表示的元素的右孩子节点的索引
     */
    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 向堆中添加元素
     */
    public void add(E e) {
        data.addLast(e);
        // 维护堆的性质, 判断其是否小于等于其父亲节点
        siftUp(data.getSize() - 1);
    }

    /**
     * 上浮元素操作, 将当前元素与父亲节点比较, 如果大于父亲节点就交换
     * k: 上浮元素的索引 index
     */
    private void siftUp(int k) {
        // 非根节点并且父节点小于当前节点才进行上浮
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(parent(k), k);
            k = parent(k);
        }
    }

    /**
     * 看堆中最大的元素
     */
    public E findMax() {
        if (data.getSize() == 0) {
            throw new IllegalArgumentException("Cannot findMax when heap is empty.");
        }
        return data.get(0);
    }

    /**
     * 最大堆, 只能取出堆顶的元素即堆中最大的元素并且删除该元素
     */
    public E extractMax() {
        E ret = findMax();
        // 1. 将最后一个元素放到堆顶位置, 并且删除该元素
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        // 2. 对堆顶元素进行下沉操作
        siftDown(0);
        return ret;
    }

    /**
     * 下沉元素操作, 将当前元素与左右孩子分别进行比较, 取最大的节点进行交换
     * k: 下沉元素的索引 index
     */
    private void siftDown(int k) {
        // 至少有左孩子才进行比较
        while (leftChild(k) < data.getSize()) {
            int max = leftChild(k);
            // 如果有右孩子并且有孩子的值大于左孩子, 那么大的索引就是右孩子的索引
            if ((max + 1) < data.getSize() && data.get(max).compareTo(data.get(max + 1)) < 0) {
                max++;
            }
            // 目前为止 max 是 leftChild 和 rightChild 中的最大值的索引
            // 取两个孩子中最大的值与父节点比较, 如果大于父节点就进行交换
            if (data.get(k).compareTo(data.get(max)) >= 0) {
                break;
            }
            data.swap(k, max);
            k = max;
        }
    }

    /**
     * replace: 取出最大元素后, 放入一个新的元素
     * 方式一: 可以先 extractMax, 再 add, 两次 O(logn) 操作
     * 方式二: 可以直接将堆顶元素替换以后 SiftDown, 一次 O(logn)操作
     */
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }

    /**
     * 将任意数字整理成堆的形状
     * 方式一: 遍历数组, 调用 add 操作 O(nlogn)
     * 方式二: 从最后一个非叶子节点(最后一个叶子节点的父节点)开始向前遍历进行下沉操作  O(n)
     */
    public void heapify() {

    }

    public static void main(String[] args) {
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = maxHeap.extractMax();
        }

        for (int i = 1; i < n; i++) {
            if (arr[i - 1] < arr[i]) {
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MaxHeap completed.");
    }

}
