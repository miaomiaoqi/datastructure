package com.miaoqi.datastructure.tree.unionfind;

/**
 * 第二版的 UnionFind, 改进 unionElements, 使用树形结构
 *
 * @author miaoqi
 * @date 2019-06-25
 */
public class UnionFind2 implements UnionFind {

    private int[] parent;

    public UnionFind2(int size) {
        this.parent = new int[size];
        for (int i = 0; i < this.parent.length; i++) {
            this.parent[i] = i;
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
     * 使用树形结构, 但是永远会让 p 指向 q
     *
     * @author miaoqi
     * @date 2019-06-25
     */
    @Override
    public void unionElements(int p, int q) {
        int pRoot = this.find(p);
        int qRoot = this.find(q);
        if (pRoot == qRoot) {
            return;
        }
        this.parent[pRoot] = qRoot;
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
