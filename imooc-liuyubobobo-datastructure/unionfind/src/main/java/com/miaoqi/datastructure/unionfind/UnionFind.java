package com.miaoqi.datastructure.unionfind;

/**
 * 并查集, 一种很不一样的树形结构, 由孩子指向父亲的树形结构, 可以非常高效的回答连接问题
 *
 * @author miaoqi
 * @date 2019-06-24
 */
public interface UnionFind {
    int getSize();

    /**
     * 判断两个元素是否连接
     *
     * @author miaoqi
     * @date 2019-06-24
     * @param p
     * @param q
     * @return
     */
    boolean isConnected(int p, int q);

    /**
     * 连接两个元素
     *
     * @author miaoqi
     * @date 2019-06-24
     * @param p
     * @param q
     * @return
     */
    void unionElements(int p, int q);
}
