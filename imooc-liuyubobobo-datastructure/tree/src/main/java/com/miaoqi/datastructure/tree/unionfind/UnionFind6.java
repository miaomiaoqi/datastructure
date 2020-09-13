package com.miaoqi.datastructure.tree.unionfind;

/**
 * 在第五版的基础上改进了路径压缩
 *
 * @author miaoqi
 * @date 2019-06-25
 */
public class UnionFind6 implements UnionFind {

    private int[] parent;
    /**
     * rank[i] 表示以 i 为根的集合所表示的树的层数
     */
    private int[] rank;

    public UnionFind6(int size) {
        this.parent = new int[size];
        this.rank = new int[size];
        for (int i = 0; i < this.parent.length; i++) {
            this.parent[i] = i;
            rank[i] = 1;
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
        if (this.rank[pRoot] < this.rank[qRoot]) {
            this.parent[pRoot] = qRoot;
        } else if (this.rank[qRoot] < this.rank[pRoot]) {
            this.parent[qRoot] = pRoot;
        } else {
            this.parent[qRoot] = pRoot;
            this.rank[pRoot] += 1;
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
            parent[p] = find(parent[p]);
        }
        return parent[p];
    }
}
