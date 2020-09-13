package com.miaoqi.datastructure.set;

/**
 * 集合
 * 盛放数据的容器, 不能有重复的元素
 * 客户统计, 词汇量统计
 *
 * @author miaoqi
 * @date 2019-06-17
 */
public interface Set<E extends Comparable> {

    public void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();

}
