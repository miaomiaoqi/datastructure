package com.miaoqi.datastructure.tree.segment;

/**
 * 线段树
 *
 * @author miaoqi
 * @date 2019-06-19
 */
public class SegmentTree<E> {

    /**
     * 线段树, 存的是区间的值
     */
    private E[] tree;
    /**
     * 原始数据, 存的是原始值
     */
    private E[] data;
    /**
     * 融合器, 更灵活的合并两个数据
     */
    private Merge<E> merge;

    public SegmentTree(E[] arr, Merge<E> merge) {
        this.merge = merge;
        this.data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++) {
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, data.length - 1);
    }

    /**
     * 在 treeIndex 位置创建表示区间 [l...r] 的线段树
     */
    private void buildSegmentTree(int treeIndex, int l, int r) {
        if (l == r) {
            this.tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);

        int mid = l + (r - l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid + 1, r);

        tree[treeIndex] = this.merge.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public int getSize() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal.");
        }
        return data[index];
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    /**
     * 返回区间[queryL, queryR]的值
     */
    public E query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= this.data.length || queryR < 0 || queryR >= this.data.length || queryL > queryR) {
            throw new IllegalArgumentException("Index is illegal");
        }
        return query(0, 0, this.data.length - 1, queryL, queryR);
    }

    /**
     * 在以 treeIndex 为根的线段树中 [l...r] 的范围里, 搜索区间 [queryL...queryR] 的值
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR) {
            return this.tree[treeIndex];
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL >= mid + 1) {
            return this.query(rightTreeIndex, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return this.query(leftTreeIndex, l, mid, queryL, queryR);
        } else {
            return this.merge.merge(this.query(leftTreeIndex, l, mid, queryL, mid), this.query(rightTreeIndex,
                    mid + 1, r, mid + 1, queryR));
        }
    }

    /**
     * 将 index 的值更新为 e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        this.data[index] = e;
        this.set(0, 0, this.data.length - 1, index, e);
    }

    /**
     * 在 treeIndex 中将 index 位置的元素更新为 e
     */
    private void set(int treeIndex, int l, int r, int index, E e) {
        if (l == r) {
            this.tree[treeIndex] = e;
            return;
        }
        int mid = l + (r - l) / 2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (index >= mid + 1) {
            set(rightTreeIndex, mid + 1, r, index, e);
        } else if (index < mid) {
            set(leftTreeIndex, l, mid, index, e);
        }
        this.tree[treeIndex] = this.merge.merge(this.tree[leftTreeIndex], this.tree[rightTreeIndex]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < this.tree.length - 1; i++) {
            if (tree[i] != null) {
                sb.append(tree[i]);
            } else {
                sb.append("null");
            }
            if (i != this.tree.length - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // 构建线段树
        Integer[] nums = {-2, 0, 3, -5, 2, -1};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(nums, Integer::sum);
        System.out.println(segmentTree);

        System.out.println(segmentTree.query(0, 5));
        System.out.println(segmentTree.query(0, 2));
        System.out.println(segmentTree.query(3, 5));
    }

}
