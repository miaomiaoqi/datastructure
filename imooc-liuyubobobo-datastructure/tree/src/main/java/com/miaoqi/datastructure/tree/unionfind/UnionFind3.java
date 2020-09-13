package com.miaoqi.datastructure.tree.unionfind;

/**
 * 第三版的 UnionFind, 优化 size, 让小的树指向大的树
 *
 * @author miaoqi
 * @date 2019-06-25
 */
public class UnionFind3 implements UnionFind {

    private int[] parent;
    /**
     * sz[i] 表示以 i 为根的集合中元素的个数
     */
    private int[] sz;

    public UnionFind3(int size) {
        this.parent = new int[size];
        this.sz = new int[size];
        for (int i = 0; i < this.parent.length; i++) {
            this.parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return this.parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return this.find(p) == this.find(q);
    }

    /**
     * 基于第二版改进, 维护一个 size, 让少的指向多的
     *
     * @author miaoqi
     * @date 2019-06-25
     * @param p
     * @param q
     * @return
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = this.find(p);
        int qRoot = this.find(q);
        if (pRoot == qRoot) {
            return;
        }
        // 元素少的指向元素多的
        if (this.sz[pRoot] < this.sz[qRoot]) {
            this.parent[pRoot] = qRoot;
            this.sz[qRoot] += this.sz[pRoot];
        } else {
            this.parent[qRoot] = pRoot;
            this.sz[pRoot] += this.sz[qRoot];
        }
    }

    /**
     * 查询元素 p 所对应的集合编号, 即根节点的值
     * O(h)复杂度, h 为树的高度
     */
    private int find(int p) {
        if (p < 0 || p >= parent.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        while (p != parent[p]) {
            p = parent[p];
        }
        return p;
    }
}
