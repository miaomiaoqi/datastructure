package com.miaoqi.datastructure.tree.unionfind;

/**
 * 第一版并查集
 *
 * @author miaoqi
 * @date 2019-06-24
 */
public class UnionFind1 implements UnionFind {

    /**
     * 每个元素对应一个编号, 同一编号代表可以连接
     */
    private int[] id;

    public UnionFind1(int size) {
        this.id = new int[size];
        // 初始化时第 0 个元素对应集合的编号是 0, 第 1 个元素对应的集合编号是 1, 没有任何一个元素是相连的
        for (int i = 0; i < this.id.length; i++) {
            this.id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return this.id.length;
    }

    /**
     * 查看元素 p 和元素 q 是否是同一集合(Quick Find)
     * O(1)
     */
    @Override
    public boolean isConnected(int p, int q) {
        return this.find(p) == this.find(q);
    }

    /**
     * 合并元素 p 和元素 q 的集合, 需要遍历一遍数组
     * O(n)
     */
    @Override
    public void unionElements(int p, int q) {
        int pId = this.find(p);
        int qId = this.find(q);
        if (pId == qId) {
            return;
        }
        for (int i = 0; i < this.id.length; i++) {
            if (this.id[i] == pId) {
                this.id[i] = qId;
            }
        }
    }

    /**
     * 查找元素 p 所对应的集合编号
     */
    private int find(int p) {
        if (p < 0 || p >= this.id.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        return this.id[p];
    }
}
